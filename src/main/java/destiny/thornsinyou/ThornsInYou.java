package destiny.thornsinyou;

import com.mojang.logging.LogUtils;
import destiny.thornsinyou.client.ClientSetup;
import destiny.thornsinyou.registry.*;
import destiny.thornsinyou.world.FlowerGeneration;
import destiny.thornsinyou.world.configuration.FlowerConfiguration;
import destiny.thornsinyou.world.feature.FlowerFeature;
import net.minecraft.client.Minecraft;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;

import static destiny.thornsinyou.registry.ModBlockRegistry.BLOCKS;
import static destiny.thornsinyou.registry.ModCreativeTabs.CREATIVE_MODE_TABS;
import static destiny.thornsinyou.registry.ModItemRegistry.ITEMS;

@Mod(ThornsInYou.MODID)
public class ThornsInYou
{
    public static final String MODID = "thornsinyou";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final RecipeBookType RECIPE_TYPE_BREWING = RecipeBookType.create("BREWING");

    public ThornsInYou()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        if(FMLEnvironment.dist.isClient()){
            modEventBus.addListener(ClientSetup::init);
        }

        BLOCKS.register(modEventBus);
        ModBlockEntityTypes.TILES.register(modEventBus);
        ModMenuTypes.MENU_TYPES.register(modEventBus);
        ModRecipeTypes.RECIPE_TYPES.register(modEventBus);
        ModRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);
        ITEMS.register(modEventBus);
        ModLootFunctions.LOOT_FUNCTIONS.register(modEventBus);
        ModBiomeFeatures.FEATURES.register(modEventBus);
        ModBiomeModifiers.BIOME_MODIFIER_SERIALIZERS.register(modEventBus);
        ModPlacementModifiers.PLACEMENT_MODIFIERS.register(modEventBus);
        FlowerGeneration.load();

        CREATIVE_MODE_TABS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("HELLO FROM COMMON SETUP");

        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlockRegistry.SCARLET_ROSE.getId(), ModBlockRegistry.POTTED_SCARLET_ROSE);
        });
    }
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("HELLO from server starting");
    }
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
