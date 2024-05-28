package destiny.thornsinyou.registry;

import destiny.thornsinyou.ThornsInYou;
import destiny.thornsinyou.world.configuration.FlowerConfiguration;
import destiny.thornsinyou.world.feature.FlowerFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomesFeatures
{
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, ThornsInYou.MODID);

    public static final RegistryObject<Feature<FlowerConfiguration>> FLOWER = FEATURES.register("flower", () -> new FlowerFeature(FlowerConfiguration.CODEC));
}