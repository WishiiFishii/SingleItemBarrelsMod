package wishiifishii.sibm;

import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.state.DirectionProperty;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

/*
 * whao sick transfer brah
 * 
 * The strategy is as follows:
 * 
 * We hold one actual itemstack and one dummy itemstack
 * The actual itemstack is to track what item we're storing. We hold the actual count as m_count.
 * We use the dummy itemstack for hoppers and similar tech- hoppers take an itemstack then increase or decrease it.
 * Thus, to enable hoppers, we supply a fake copy of our itemstack (the dummy itemstack), and let the hopper increase/decrease it
 * Then, we monitor this dummy itemstack. Any changes are factored into m_count, and the itemstack is reset to some value above 1 and
 * less than 64 (so our itemstack can increase/decrease). I'm using half the max stack size.
 */
public class OpinionatedBarrelTileEntity extends TileEntity implements IInventory, ITickableTileEntity{
	//private Logger LOGGER = LogManager.getLogger();

	//private static final int MAX_AMOUNT = Integer.MAX_VALUE - 1; //offset by 1 as a precaution
	private static final int MAX_AMOUNT = ((Integer.MAX_VALUE / 64) - 10) * 64; //A very large amount of items. The 10 is just a dummy thing incase
	//there's some weeeeeird mod that puts in too many items at once.
	//private static final int PIVOT_AMOUNT = 32;
	private static final float PIVOT_RATIO = 0.5f;
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	public static final Item NULL_ITEM = Items.AIR;

	private ItemStack m_itemstack = ItemStack.EMPTY;
	private ItemStack m_itemstack_placeholder = ItemStack.EMPTY;
	private int m_count;
	
	public OpinionatedBarrelTileEntity(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}
	
	public OpinionatedBarrelTileEntity() {
		this(ModEntities.ENTITY_BARREL);
	}
	

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		CompoundNBT itemNBT = new CompoundNBT();
		if(this.m_itemstack != null) {
			this.m_itemstack.write(itemNBT);
			compound.put("curItem", itemNBT);
			compound.putInt("curCount", this.m_count);
		} else {
			ItemStack nullstack = ItemStack.EMPTY;
			nullstack.write(itemNBT);
			compound.put("curItem", itemNBT);
			compound.putInt("curCount", 0);
			
		}
		return compound;
	}
	
	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		super.read(state, nbt);
		if(nbt.contains("curItem")) {
			this.m_count = nbt.getInt("curCount");
			this.m_itemstack = ItemStack.read(nbt.getCompound("curItem"));
			this.m_itemstack_placeholder = this.m_itemstack.copy();
			//this.m_itemstack_placeholder.setCount(this.PIVOT_AMOUNT);
			this.m_itemstack_placeholder.setCount(this.getPivotAmount());
		}
	}
	
	//Math.ceil ensures we get at least 1 for any value over 0, right? lol I forget
	private int getPivotAmount() {
		return this.m_itemstack != null && !this.m_itemstack.isEmpty() ? (int) Math.max(1, Math.ceil(this.m_itemstack.getItem().getMaxStackSize() * PIVOT_RATIO)) : 0;
	}

	public Item getItem() {
		Item tItem = this.m_itemstack.getItem();
		return this.m_count > 0 ? tItem : this.NULL_ITEM;
	}
	
	public ItemStack getItemStack() {
		Item tItem = this.m_itemstack.getItem();
		return this.m_count > 0 ? this.m_itemstack : ItemStack.EMPTY;
	}
	
	public void notifyForUpdate() {
		world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 0);
	}
	
	public ItemStack addItem(ItemStack itemstack) {
		ItemStack retStack = itemstack;
		if(this.m_itemstack == null || this.m_count <= 0 || itemstack.isEmpty()) {
			//0% chance 
			m_count += itemstack.getCount();
			this.m_itemstack = itemstack;
			this.m_itemstack_placeholder = this.m_itemstack.copy();
			//this.m_itemstack_placeholder.setCount(this.PIVOT_AMOUNT);
			this.m_itemstack_placeholder.setCount(this.getPivotAmount());
			this.notifyForUpdate();
			retStack = ItemStack.EMPTY;
		} else if(this.m_itemstack.getItem() == itemstack.getItem()) {
			//this will leave a buffer of 1 stack before we hit INT_MAX.
			//Note that this can result in some weird but non-item-eating behavior
			//from the player's perspective, but tbh, if you are  hitting int_max amounts of
			//cobblestone or w/e, you're using an automated solution and we don't need to worry
			//about the player's experience as much.
			int returnToPlayer = 0;
			int addToStack = 0;
			int freeSpace = MAX_AMOUNT - this.m_count;
			if(freeSpace > itemstack.getItem().getMaxStackSize()) {
				//take the whole stack
				addToStack = itemstack.getCount();
			} else {
				addToStack = freeSpace;
				returnToPlayer = itemstack.getCount() - freeSpace;
			}
			if(returnToPlayer <= 0) {
				retStack = ItemStack.EMPTY;
			} else {
				itemstack.setCount(returnToPlayer);
			}
			m_count += addToStack;
			this.notifyForUpdate();
		}
		return retStack;
	}
	
	public void giveItem(PlayerEntity player, Hand hand) {
		if(this.m_itemstack != null && !this.m_itemstack.isEmpty()) {
			ItemStack itemStack = new ItemStack(m_itemstack.getItem()); //we do it this way to prevent us from accidentally messing up our stored itemstack
			int giveCount = Math.min(m_count, m_itemstack.getItem().getMaxStackSize());
			int newCount = m_count - giveCount;
			//LOGGER.debug("Giving " + itemStack + "to Dev w/ quant " + giveCount + " from " + m_count + " newcount " + newCount);
			this.m_count = newCount;
			itemStack.setCount(giveCount);
			//this.markDirty();
			
			if(this.m_count <= 0) {
				this.clear();
			} else { 
				this.notifyForUpdate(); 
			}
			
			player.setHeldItem(hand, itemStack);
		}
	}
    
	@Override
	@Nullable
	public SUpdateTileEntityPacket getUpdatePacket() {
		CompoundNBT nbtTagCompound = new CompoundNBT();
		write(nbtTagCompound);
		int tileEntityType = 42;  // arbitrary number; only used for vanilla TileEntities.  You can use it, or not, as you want.
		return new SUpdateTileEntityPacket(this.pos, tileEntityType, nbtTagCompound);
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		BlockState blockState = world.getBlockState(pos);
		read(blockState, pkt.getNbtCompound());   // read from the nbt in the packet
	}

	@Override
	public CompoundNBT getUpdateTag()
	{
		CompoundNBT nbtTagCompound = new CompoundNBT();
		write(nbtTagCompound);
		return nbtTagCompound;
	}
	
	@Override
	public void handleUpdateTag(BlockState blockState, CompoundNBT parentNBTTagCompound)
	{
		this.read(blockState, parentNBTTagCompound);
	}
	
	public void remove() {
		if(!this.m_itemstack.isEmpty()) {
			for(int i = 0; i < this.m_count / 64; i++) {
				Block.spawnAsEntity(world, this.getPos(), new ItemStack(this.getItem(), 64));
				this.m_count -= 64;
			}
			if(this.m_count > 0) {
				Block.spawnAsEntity(world, this.getPos(), new ItemStack(this.getItem(), this.m_count));
			}
			this.clear();
		}
		
		super.remove();
	}
	
	
	/*
	 * All below is for handling dispenser behavior, probably tube behavior as well...
	 */
	
	//if there's a placeholder itemstack (used to feign hopper compatibility), merge the stacks and tick.
	public void tick() {
		this.mergePlacementStackAndNotify();
	}
	
	private void mergePlacementStackAndNotify() {
		if(this.m_itemstack_placeholder != null && 
				this.m_itemstack != null && 
				!this.m_itemstack.isEmpty() && 
				!this.m_itemstack_placeholder.isEmpty() &&
				this.m_itemstack.getItem() == this.m_itemstack_placeholder.getItem()) {
			
			/*
			 * Two behavior cases: the normal case and the "almost out of free space" logic.
			 * First is normal, then is almost out.
			 */
			int newcount = this.m_itemstack_placeholder.getCount();
			int pivot_amount = this.getPivotAmount();
			//this.m_count += newcount - this.PIVOT_AMOUNT;
			//this.m_itemstack_placeholder.setCount(this.PIVOT_AMOUNT);
			this.m_count += newcount - pivot_amount;
			this.m_itemstack_placeholder.setCount(pivot_amount);
			
			if(this.m_count <= 0) {
				this.clear();
			} else {
				this.notifyForUpdate();
			}
			
		}
	}

	@Override
	public void markDirty() {
		super.markDirty();
		this.mergePlacementStackAndNotify();
	}
	
	//from the iinventory or whatever interface
	@Override
	public void clear() {
		this.m_count = 0;
		this.m_itemstack = ItemStack.EMPTY;
		this.m_itemstack_placeholder = ItemStack.EMPTY;
		this.notifyForUpdate();
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public boolean isEmpty() {
		return this.m_count <= 0 || this.m_itemstack.isEmpty();
	}

	//hopper works by getting an itemstack and increasing that one
	//if we're reaching a problematic amount (int overflow risk amount), we
	//return a full stack to prevent count-increasing behavior, like what
	//hoppers do. Otherwise, we return the placeholder stack itself.
	@Override
	public ItemStack getStackInSlot(int index) {
		ItemStack retStack = this.m_itemstack_placeholder;
		int buffersize = this.m_itemstack != null && !this.m_itemstack.isEmpty() ? this.m_itemstack.getMaxStackSize() : 64;
		//int pivotAmount = this.getPivotAmount();
		if(this.m_count >= MAX_AMOUNT - 64) {
			//You **NEED** copy to prevent our tick() behavior from
			//auto-counting the extra items into our total count. A crash can result if you remove the .copy().
			retStack = this.m_itemstack_placeholder.copy(); 
			retStack.setCount(this.m_itemstack_placeholder.getItem().getMaxStackSize());
		}
		return this.m_itemstack != null ? retStack : ItemStack.EMPTY;
	}
	
	
	//called by hopper to decrease this stack and move it to its own inventory
	@Override
	public ItemStack decrStackSize(int index, int count) {
		//LOGGER.debug("decrStackSize");
		ItemStack retStack = this.m_itemstack.copy();
		if(count > this.m_count) {
			retStack.setCount(this.m_count);
		} else {
			retStack.setCount(count);
		}
		this.m_count = Math.max(0, this.m_count - count);
		if(this.m_count == 0) {
			this.clear();
		} else {
			this.notifyForUpdate();
		}
		return retStack;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		// TODO Auto-generated method stub
		//return null;
		//LOGGER.debug("Remove from slot");
		return this.m_itemstack;
	}

	//this is what's called to put an item in a slot IF the slot is empty
	//we init a new placeholder stack in this case
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		//LOGGER.debug("setInventorySlotContents");
		this.m_count += stack.getCount();
		this.m_itemstack = stack;
		this.m_itemstack_placeholder = this.m_itemstack.copy();
		//this.m_itemstack_placeholder.setCount(this.PIVOT_AMOUNT);
		this.m_itemstack_placeholder.setCount(this.getPivotAmount());
		this.notifyForUpdate();
	}

	@Override
	public boolean isUsableByPlayer(PlayerEntity player) {
		return true;
	}

	public Direction getDirection() {
		return this.getBlockState().get(FACING);
	}

	public int getCount() {
		return this.m_count;
	}

	public int getMaxStackCount() {
		return this.m_itemstack != null ? this.m_itemstack.getMaxStackSize() : 0;
	}
	
	/*
	public int getLightLevel() {
		return world.getLightValue(this.pos.up());
	}
	*/
	
	/*
	public int addItemStack(ItemStack stack) {
		if (this.m_itemstack != null && this.m_itemstack.getItem() == stack.getItem()) {
			return 0;
		} else if (this.m_itemstack == null || this.m_itemstack.getItem() == this.NULL_ITEM) {
			return 0;
		}
		
		return -1;
	}
	*/
}
