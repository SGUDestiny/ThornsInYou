package destiny.thornsinyou.registry;

import destiny.thornsinyou.ThornsInYou;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;

public class ModDamageTypeRegistry {
    public static final ResourceKey<DamageType> SCARLET_ROSE_STING = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(ThornsInYou.MODID, "scarlet_rose_sting"));

    public static DamageSource getSimpleDamageSource(Level level, ResourceKey<DamageType> type) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(type));
    }
}