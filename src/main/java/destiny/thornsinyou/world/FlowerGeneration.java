package destiny.thornsinyou.world;

import destiny.thornsinyou.ThornsInYou;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class FlowerGeneration {

    public static ResourceKey<ConfiguredFeature<?, ?>> FEATURE_PATCH_SCARLET_ROSES = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(ThornsInYou.MODID, "patch_scarlet_roses"));

    public static ResourceKey<PlacedFeature> PATCH_SCARLET_ROSES = ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(ThornsInYou.MODID, "patch_scarlet_roses"));

    public static void load() {
    }
}
