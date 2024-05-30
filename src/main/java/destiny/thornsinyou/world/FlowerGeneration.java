package destiny.thornsinyou.world;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class FlowerGeneration {
    public static ResourceKey<ConfiguredFeature<?, ?>> FEATURE_SCARLET_ROSE;
    public static ResourceKey<PlacedFeature> PATCH_SCARLET_ROSE;

    public FlowerGeneration() {
    }

    public static void load() {
    }

    static {
        FEATURE_SCARLET_ROSE = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation("thornsinyou", "patch_scarlet_rose"));
        PATCH_SCARLET_ROSE = ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation("thornsinyou", "patch_scarlet_rose"));
    }
}
