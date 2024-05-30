package destiny.thornsinyou.registry;

import destiny.thornsinyou.world.configuration.FlowerConfiguration;
import destiny.thornsinyou.world.feature.FlowerFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBiomeFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES;
    public static final RegistryObject<Feature<FlowerConfiguration>> FLOWER;

    public ModBiomeFeatures() {
    }

    static {
        FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, "thornsinyou");
        FLOWER = FEATURES.register("flower", () -> {
            return new FlowerFeature(FlowerConfiguration.CODEC);
        });
    }
}