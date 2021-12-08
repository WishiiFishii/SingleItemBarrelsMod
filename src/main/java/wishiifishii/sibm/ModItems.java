package wishiifishii.sibm;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;

/*
"oak", 
"birch", BIRCH
"acacia", ACACIA
"spruce", SPRUCE
"jungle", JUNGLE
"darkoak", DARKOAK
"warped", WARPED
"crimson", CRIMSON
"iron", IRON
"haystack", HAYSTACK
 */
public class ModItems {

	public final static Item ITEM_BARREL_OAK = new BlockItem(ModBlocks.BLOCK_BARREL_OAK, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS))
			.setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "itembarreloak"));
	
	public final static Item ITEM_BARREL_BIRCH = new BlockItem(ModBlocks.BLOCK_BARREL_BIRCH, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS))
			.setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "itembarrelbirch"));
	
	public final static Item ITEM_BARREL_ACACIA = new BlockItem(ModBlocks.BLOCK_BARREL_ACACIA, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS))
			.setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "itembarrelacacia"));
	
	public final static Item ITEM_BARREL_SPRUCE = new BlockItem(ModBlocks.BLOCK_BARREL_SPRUCE, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS))
			.setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "itembarrelspruce"));
	
	public final static Item ITEM_BARREL_JUNGLE = new BlockItem(ModBlocks.BLOCK_BARREL_JUNGLE, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS))
			.setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "itembarreljungle"));
	
	public final static Item ITEM_BARREL_DARKOAK = new BlockItem(ModBlocks.BLOCK_BARREL_DARKOAK, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS))
			.setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "itembarreldarkoak"));
	
	public final static Item ITEM_BARREL_WARPED = new BlockItem(ModBlocks.BLOCK_BARREL_WARPED, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS))
			.setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "itembarrelwarped"));
	
	public final static Item ITEM_BARREL_CRIMSON = new BlockItem(ModBlocks.BLOCK_BARREL_CRIMSON, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS))
			.setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "itembarrelcrimson"));
	
	public final static Item ITEM_BARREL_IRON = new BlockItem(ModBlocks.BLOCK_BARREL_IRON, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS))
			.setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "itembarreliron"));
	
	public final static Item ITEM_BARREL_HAYSTACK = new BlockItem(ModBlocks.BLOCK_BARREL_HAYSTACK, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS))
			.setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "itembarrelhaystack"));
}
