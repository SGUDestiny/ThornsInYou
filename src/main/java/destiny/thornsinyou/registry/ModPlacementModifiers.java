package destiny.thornsinyou.registry;

import com.mojang.serialization.Codec;
import destiny.thornsinyou.world.filter.BiomeTagFilter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModPlacementModifiers {
    public static final DeferredRegister<PlacementModifierType<?>> PLACEMENT_MODIFIERS;
    public static final RegistryObject<PlacementModifierType<BiomeTagFilter>> BIOME_TAG;

    public ModPlacementModifiers() {
    }

    private static <P extends PlacementModifier> PlacementModifierType<P> typeConvert(Codec<P> codec) {
        return () -> {
            return codec;
        };
    }

    static {
        PLACEMENT_MODIFIERS = DeferredRegister.create(BuiltInRegistries.PLACEMENT_MODIFIER_TYPE.key(), "thornsinyou");
        BIOME_TAG = PLACEMENT_MODIFIERS.register("biome_tag", () -> {
            return typeConvert(BiomeTagFilter.CODEC);
        });
    }
}
