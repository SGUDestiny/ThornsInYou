package destiny.thornsinyou.registry;

import destiny.thornsinyou.ThornsInYou;
import destiny.thornsinyou.block.CopperKettleBlock;
import destiny.thornsinyou.block.ScarletRoseBushBlock;
import destiny.thornsinyou.block.ScarletRoseBlock;
import destiny.thornsinyou.block.ScarletRoseCropBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ThornsInYou.MODID);

    public static final RegistryObject<Block> SCARLET_ROSE = registerBlock("scarlet_rose",
                () -> new ScarletRoseBlock(
                    BlockBehaviour.Properties.of()
                            .noOcclusion()
                            .noCollission()
                            .instabreak()
                            .sound(SoundType.SWEET_BERRY_BUSH)
                            .offsetType(BlockBehaviour.OffsetType.XZ)
                            .ignitedByLava()
                            .pushReaction(PushReaction.DESTROY)
                ));

    public static final RegistryObject<Block> SCARLET_ROSE_BUSH = registerBlock("scarlet_rose_bush",
            () -> new ScarletRoseBushBlock(
                    BlockBehaviour.Properties.of()
                            .noOcclusion()
                            .noCollission()
                            .instabreak()
                            .sound(SoundType.SWEET_BERRY_BUSH)
                            .offsetType(BlockBehaviour.OffsetType.XZ)
                            .ignitedByLava()
                            .pushReaction(PushReaction.DESTROY)
            ));

    public static final RegistryObject<Block> POTTED_SCARLET_ROSE = BLOCKS.register("potted_scarlet_rose",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlockRegistry.SCARLET_ROSE,
                    BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH).noOcclusion()));

    public static final RegistryObject<Block> SCARLET_ROSE_CROP = BLOCKS.register("scarlet_rose_crop",
            () -> new ScarletRoseCropBlock(
                    BlockBehaviour.Properties.of()
                            .noOcclusion()
                            .noCollission()
                            .instabreak()
                            .sound(SoundType.SWEET_BERRY_BUSH)
                            .offsetType(BlockBehaviour.OffsetType.XZ)
                            .ignitedByLava()
                            .pushReaction(PushReaction.DESTROY)
            ));

    public static final RegistryObject<Block> COPPER_KETTLE = registerBlock("copper_kettle",
            () -> new CopperKettleBlock(
                    BlockBehaviour.Properties.of()
                            .sound(SoundType.COPPER)
                            .pushReaction(PushReaction.IGNORE)
                            .destroyTime(20)
                            .noOcclusion()
            ));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, Supplier<T> block){
        return ModItemRegistry.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
