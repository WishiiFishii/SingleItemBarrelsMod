package wishiifishii.sibm;

import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
//import net.minecraft.block.IBlockState;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.block.Block;

public class OpinionatedBlockBarrel extends Block implements ITileEntityProvider {
	private Item m_item;
	private int m_count;
	//private Logger LOGGER = LogManager.getLogger();

	public static final DirectionProperty PROPERTY_FACING = BlockStateProperties.FACING;

	public OpinionatedBlockBarrel(Properties builder) {
		super(builder);
		//LOGGER.debug("duh-huh!");
		//LOGGER.debug(PROPERTY_FACING);
		this.setDefaultState(this.stateContainer.getBaseState().with(PROPERTY_FACING, Direction.NORTH));
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.FACING);
	}
	
	private OpinionatedBarrelTileEntity getTileEntity(World worldIn, BlockPos pos) {
		TileEntity tileentity = worldIn.getTileEntity(pos);
		if(tileentity instanceof OpinionatedBarrelTileEntity) {
			return (OpinionatedBarrelTileEntity) tileentity;
		}
		return null;
	}
	
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if (worldIn.isRemote) {
			return ActionResultType.SUCCESS;
		} else {
			TileEntity tileent = this.getTileEntity(worldIn, pos);
			if(tileent instanceof OpinionatedBarrelTileEntity) {
				//LOGGER.debug("Light level "+ getLightLevel2(worldIn, pos));
				OpinionatedBarrelTileEntity barrelent = (OpinionatedBarrelTileEntity) tileent;
				//LOGGER.debug("What did we REALLY find? " + tileent);
				//LOGGER.debug("What are we holding? " + player.getHeldItemMainhand());
				if(player.getHeldItemMainhand().getItem() == Items.AIR) {
					LOGGER.debug("GIB!");
					barrelent.giveItem(player, handIn);
				} else {
					LOGGER.debug(player.getHeldItemMainhand().getItem().getRegistryName());
					ItemStack retStack = barrelent.addItem(player.getHeldItemMainhand());
					if(retStack != player.getHeldItemMainhand()) {
						player.setHeldItem(handIn, retStack);
					}
				}
			}

			return ActionResultType.CONSUME;
		}
	}

	//which one do i need lol
	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return (TileEntity) new OpinionatedBarrelTileEntity();
	}
	
	public TileEntity createTileEntity(BlockState state, IBlockReader worldIn) {
		return (TileEntity) new OpinionatedBarrelTileEntity();
	}

	/*
    public static boolean setOpaque(BlockState state, IBlockReader reader, BlockPos pos) {
       return false;
    }
    */

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		if (stack.hasDisplayName()) {
			TileEntity tileentity = worldIn.getTileEntity(pos);
			if (tileentity instanceof OpinionatedBarrelTileEntity) {
				//((OpinionatedBarrelTileEntity)tileentity).setCustomName(stack.getDisplayName());
			}
		}

	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(PROPERTY_FACING, rot.rotate(state.get(PROPERTY_FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.toRotation(state.get(PROPERTY_FACING)));
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		LOGGER.debug(context.getNearestLookingDirections().toString());
		for(Direction dir: context.getNearestLookingDirections()) {
			LOGGER.debug("direxon " + dir);
			if(dir == Direction.NORTH ||dir == Direction.EAST ||dir == Direction.SOUTH ||dir == Direction.WEST ) {
				return this.getDefaultState().with(PROPERTY_FACING, dir.getOpposite());
			}
		}
		return this.getDefaultState().with(PROPERTY_FACING, context.getNearestLookingDirection().getOpposite());
	}
}
