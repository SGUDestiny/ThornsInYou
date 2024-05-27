package destiny.thornsinyou.block;

import destiny.thornsinyou.registry.ModDamageTypeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class ScarletRoseBushBlock extends TallFlowerBlock {
    public ScarletRoseBushBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity && entity.getType() != EntityType.BEE) {
            entity.makeStuckInBlock(state, new Vec3((double)0.8F, 0.75D, (double)0.8F));
            if (!level.isClientSide && (entity.xOld != entity.getX() || entity.zOld != entity.getZ())) {
                double d0 = Math.abs(entity.getX() - entity.xOld);
                double d1 = Math.abs(entity.getZ() - entity.zOld);
                if (d0 >= (double)0.003F || d1 >= (double)0.003F) {
                    entity.hurt(ModDamageTypeRegistry.getSimpleDamageSource(level, ModDamageTypeRegistry.SCARLET_ROSE_STING), 1.0F);
                }
            }

        }
    }
}
