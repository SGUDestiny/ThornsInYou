package destiny.thornsinyou.registry;

import destiny.thornsinyou.ThornsInYou;
import destiny.thornsinyou.block.entity.CopperKettleBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ThornsInYou.MODID);

    public static final RegistryObject<BlockEntityType<CopperKettleBlockEntity>> COPPER_KETTLE = TILES.register("copper_kettle",
            () -> BlockEntityType.Builder.of(CopperKettleBlockEntity::new, ModBlockRegistry.COPPER_KETTLE.get()).build(null));
}
