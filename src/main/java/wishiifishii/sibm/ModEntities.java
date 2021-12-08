package wishiifishii.sibm;

import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;

/*
"oak", 
"birch",
"acacia",
"spruce",
"jungle",
"darkoak",
"warped",
"crimson",
"iron",
"haystack",
 */
public class ModEntities {

	public final static TileEntityType<OpinionatedBarrelTileEntity> ENTITY_BARREL = (TileEntityType<OpinionatedBarrelTileEntity>) TileEntityType.Builder
			.create(OpinionatedBarrelTileEntity::new, ModBlocks.BLOCK_BARREL_OAK,
					ModBlocks.BLOCK_BARREL_BIRCH,
					ModBlocks.BLOCK_BARREL_ACACIA,
					ModBlocks.BLOCK_BARREL_SPRUCE,
					ModBlocks.BLOCK_BARREL_JUNGLE,
					ModBlocks.BLOCK_BARREL_DARKOAK,
					ModBlocks.BLOCK_BARREL_WARPED,
					ModBlocks.BLOCK_BARREL_CRIMSON,
					//ModBlocks.BLOCK_BARREL_IRON,
					ModBlocks.BLOCK_BARREL_HAYSTACK).build(null).setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "tebarrel"));

	//public final static TileEntityType<OpinionatedBarrelTileEntity> ENTITY_BARREL_BIRCH = (TileEntityType<OpinionatedBarrelTileEntity>) TileEntityType.Builder
	//		.create(OpinionatedBarrelTileEntity::new, ModBlocks.BLOCK_BARREL_BIRCH).build(null).setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "tebarrelbirch"));

	/*
	public final static TileEntityType<OpinionatedBarrelTileEntity> ENTITY_BARREL_ACACIA = (TileEntityType<OpinionatedBarrelTileEntity>) TileEntityType.Builder
			.create(OpinionatedBarrelTileEntity::new, ModBlocks.BLOCK_BARREL_ACACIA).build(null).setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "tebarrelacacia"));

	public final static TileEntityType<OpinionatedBarrelTileEntity> ENTITY_BARREL_SPRUCE = (TileEntityType<OpinionatedBarrelTileEntity>) TileEntityType.Builder
			.create(OpinionatedBarrelTileEntity::new, ModBlocks.BLOCK_BARREL_SPRUCE).build(null).setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "tebarrelspruce"));

	public final static TileEntityType<OpinionatedBarrelTileEntity> ENTITY_BARREL_JUNGLE = (TileEntityType<OpinionatedBarrelTileEntity>) TileEntityType.Builder
			.create(OpinionatedBarrelTileEntity::new, ModBlocks.BLOCK_BARREL_JUNGLE).build(null).setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "tebarreljungle"));

	public final static TileEntityType<OpinionatedBarrelTileEntity> ENTITY_BARREL_DARKOAK = (TileEntityType<OpinionatedBarrelTileEntity>) TileEntityType.Builder
			.create(OpinionatedBarrelTileEntity::new, ModBlocks.BLOCK_BARREL_DARKOAK).build(null).setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "tebarreldarkoak"));

	public final static TileEntityType<OpinionatedBarrelTileEntity> ENTITY_BARREL_OAK = (TileEntityType<OpinionatedBarrelTileEntity>) TileEntityType.Builder
			.create(OpinionatedBarrelTileEntity::new, ModBlocks.BLOCK_BARREL_DARKOAK).build(null).setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "tebarreldarkoak"));

	public final static TileEntityType<OpinionatedBarrelTileEntity> ENTITY_BARREL_WARPED = (TileEntityType<OpinionatedBarrelTileEntity>) TileEntityType.Builder
			.create(OpinionatedBarrelTileEntity::new, ModBlocks.BLOCK_BARREL_WARPED).build(null).setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "tebarrelwarped"));

	public final static TileEntityType<OpinionatedBarrelTileEntity> ENTITY_BARREL_CRIMSON = (TileEntityType<OpinionatedBarrelTileEntity>) TileEntityType.Builder
			.create(OpinionatedBarrelTileEntity::new, ModBlocks.BLOCK_BARREL_CRIMSON).build(null).setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "tebarrelcrimson"));

	/*
	public final static TileEntityType<OpinionatedBarrelTileEntity> ENTITY_BARREL_IRON = (TileEntityType<OpinionatedBarrelTileEntity>) TileEntityType.Builder
			.create(OpinionatedBarrelTileEntity::new, ModBlocks.BLOCK_BARREL_IRON).build(null).setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "tebarreliron"));
	*/
	/*
	public final static TileEntityType<OpinionatedBarrelTileEntity> ENTITY_BARREL_HAYSTACK = (TileEntityType<OpinionatedBarrelTileEntity>) TileEntityType.Builder
			.create(OpinionatedBarrelTileEntity::new, ModBlocks.BLOCK_BARREL_HAYSTACK).build(null).setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "tebarrelhaystack"));
	*/
}
