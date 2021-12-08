package wishiifishii
		.sibm;

import net
		.minecraft
		.block
		.AbstractBlock;
import net
		.minecraft
		.block
		.SoundType;
import net
		.minecraft
		.block
		.material
		.Material;
import net
		.minecraft
		.util
		.ResourceLocation;
import net
		.minecraftforge
		.common
		.ToolType;

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
public class ModBlocks {

	public static final float HARDNESS = 3.0F;
	public static final float RESISTANCE = 6.0F;

	public final static OpinionatedBlockBarrel BLOCK_BARREL_OAK = (OpinionatedBlockBarrel) new OpinionatedBlockBarrel(AbstractBlock
			.Properties
			.create(Material.WOOD)
			.setRequiresTool()
			.hardnessAndResistance(HARDNESS, RESISTANCE)
			.harvestTool(ToolType.AXE)
			.sound(SoundType.WOOD)
			.notSolid()
		)
		.setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "barrelblockoak"));

	public final static OpinionatedBlockBarrel BLOCK_BARREL_BIRCH = (OpinionatedBlockBarrel) new OpinionatedBlockBarrel(AbstractBlock
			.Properties
			.create(Material.WOOD)
			.setRequiresTool()
			.hardnessAndResistance(HARDNESS, RESISTANCE)
			.harvestTool(ToolType.AXE)
			.sound(SoundType.WOOD)
			.notSolid()
		)
		.setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "barrelblockbirch"));

	public final static OpinionatedBlockBarrel BLOCK_BARREL_ACACIA = (OpinionatedBlockBarrel) new OpinionatedBlockBarrel(AbstractBlock
			.Properties
			.create(Material.WOOD)
			.setRequiresTool()
			.hardnessAndResistance(HARDNESS, RESISTANCE)
			.harvestTool(ToolType.AXE)
			.sound(SoundType.WOOD)
			.notSolid()
		)
		.setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "barrelblockacacia"));

	public final static OpinionatedBlockBarrel BLOCK_BARREL_SPRUCE = (OpinionatedBlockBarrel) new OpinionatedBlockBarrel(AbstractBlock
			.Properties
			.create(Material.WOOD)
			.setRequiresTool()
			.hardnessAndResistance(HARDNESS, RESISTANCE)
			.harvestTool(ToolType.AXE)
			.sound(SoundType.WOOD)
			.notSolid()
		)
		.setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "barrelblockspruce"));

	public final static OpinionatedBlockBarrel BLOCK_BARREL_JUNGLE = (OpinionatedBlockBarrel) new OpinionatedBlockBarrel(AbstractBlock
			.Properties
			.create(Material.WOOD)
			.setRequiresTool()
			.hardnessAndResistance(HARDNESS, RESISTANCE)
			.harvestTool(ToolType.AXE)
			.sound(SoundType.WOOD)
			.notSolid()
		)
		.setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "barrelblockjungle"));

	public final static OpinionatedBlockBarrel BLOCK_BARREL_DARKOAK = (OpinionatedBlockBarrel) new OpinionatedBlockBarrel(AbstractBlock
			.Properties
			.create(Material.WOOD)
			.setRequiresTool()
			.hardnessAndResistance(HARDNESS, RESISTANCE)
			.harvestTool(ToolType.AXE)
			.sound(SoundType.WOOD)
			.notSolid()
		)
		.setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "barrelblockdarkoak"));

	public final static OpinionatedBlockBarrel BLOCK_BARREL_WARPED = (OpinionatedBlockBarrel) new OpinionatedBlockBarrel(AbstractBlock
			.Properties
			.create(Material.WOOD)
			.setRequiresTool()
			.hardnessAndResistance(HARDNESS, RESISTANCE)
			.harvestTool(ToolType.AXE)
			.sound(SoundType.WOOD)
			.notSolid()
		)
		.setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "barrelblockwarped"));

	public final static OpinionatedBlockBarrel BLOCK_BARREL_CRIMSON = (OpinionatedBlockBarrel) new OpinionatedBlockBarrel(AbstractBlock
			.Properties
			.create(Material.WOOD)
			.setRequiresTool()
			.hardnessAndResistance(HARDNESS, RESISTANCE)
			.harvestTool(ToolType.AXE)
			.sound(SoundType.WOOD)
			.notSolid()
		)
		.setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "barrelblockcrimson"));

	public final static OpinionatedBlockBarrel BLOCK_BARREL_IRON = (OpinionatedBlockBarrel) new OpinionatedBlockBarrel(AbstractBlock
			.Properties
			.create(Material.WOOD)
			.setRequiresTool()
			.hardnessAndResistance(HARDNESS, RESISTANCE)
			.harvestTool(ToolType.AXE)
			.sound(SoundType.WOOD)
			.notSolid()
		)
		.setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "barrelblockiron"));

	public final static OpinionatedBlockBarrel BLOCK_BARREL_HAYSTACK = (OpinionatedBlockBarrel) new OpinionatedBlockBarrel(AbstractBlock
			.Properties	
			.create(Material.ORGANIC)
			.setRequiresTool()
			.hardnessAndResistance(0.5F)
			.harvestTool(ToolType.HOE)
			.sound(SoundType.PLANT)
			.notSolid()
		)
		.setRegistryName(new ResourceLocation(SingleItemsBarrelMod.MODID, "barrelblockhaystack"));
}
