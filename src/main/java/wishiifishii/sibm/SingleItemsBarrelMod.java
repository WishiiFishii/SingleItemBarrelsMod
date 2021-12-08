package wishiifishii.sibm;

import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tags.TagCollectionManager;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.data.ForgeRegistryTagsProvider;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SingleItemsBarrelMod.MODID)
public class SingleItemsBarrelMod
{
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "sibm";

	
    public SingleItemsBarrelMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
    	ClientRegistry.bindTileEntityRenderer(ModEntities.ENTITY_BARREL, OpinionatedBarrelRenderer::new);
    	
    	/*
    	Map<ResourceLocation, ?> tagMap = TagCollectionManager.getManager().getItemTags().getIDTagMap();
    	for(ResourceLocation resLoc: tagMap.keySet()) {
    		LOGGER.debug(resLoc.toString() + " -> " + tagMap.get(resLoc));
    	}
    	*/
    }
	
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD, modid=SingleItemsBarrelMod.MODID)
    public static class RegistryEvents {
    	
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            IForgeRegistry<Block> reg = blockRegistryEvent.getRegistry();
    		reg.register(ModBlocks.BLOCK_BARREL_ACACIA);
    		reg.register(ModBlocks.BLOCK_BARREL_BIRCH);
    		reg.register(ModBlocks.BLOCK_BARREL_CRIMSON);
    		reg.register(ModBlocks.BLOCK_BARREL_DARKOAK);
    		reg.register(ModBlocks.BLOCK_BARREL_HAYSTACK);
    		reg.register(ModBlocks.BLOCK_BARREL_JUNGLE);
    		reg.register(ModBlocks.BLOCK_BARREL_OAK);
    		reg.register(ModBlocks.BLOCK_BARREL_SPRUCE);
    		reg.register(ModBlocks.BLOCK_BARREL_WARPED);
        }
        
        @SubscribeEvent
        public static void onTileEntitiesRegistry(final RegistryEvent.Register<TileEntityType<?>> tileEntityRegisterEvent) {
        	IForgeRegistry<TileEntityType<?>> reg = tileEntityRegisterEvent.getRegistry();
        	reg.register(ModEntities.ENTITY_BARREL);
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
            IForgeRegistry<Item> reg = itemRegistryEvent.getRegistry();
            reg.register(ModItems.ITEM_BARREL_ACACIA);
            reg.register(ModItems.ITEM_BARREL_BIRCH);
            reg.register(ModItems.ITEM_BARREL_CRIMSON);
            reg.register(ModItems.ITEM_BARREL_DARKOAK);
            reg.register(ModItems.ITEM_BARREL_HAYSTACK);
            reg.register(ModItems.ITEM_BARREL_JUNGLE);
            reg.register(ModItems.ITEM_BARREL_OAK);
            reg.register(ModItems.ITEM_BARREL_SPRUCE);
            reg.register(ModItems.ITEM_BARREL_WARPED);
        }
    }
}
