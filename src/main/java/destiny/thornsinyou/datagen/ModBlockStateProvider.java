package destiny.thornsinyou.datagen;

import destiny.thornsinyou.ThornsInYou;
import destiny.thornsinyou.registry.ModBlockRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ThornsInYou.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(ModBlockRegistry.SCARLET_ROSE.get(), models().cross(blockTexture(ModBlockRegistry.SCARLET_ROSE.get()).getPath(),
                blockTexture(ModBlockRegistry.SCARLET_ROSE.get())).renderType("cutout"));
        simpleBlockWithItem(ModBlockRegistry.POTTED_SCARLET_ROSE.get(), models().singleTexture("potted_scarlet_rose", new ResourceLocation("flower_pot_cross"), "plant",
                blockTexture(ModBlockRegistry.SCARLET_ROSE.get())).renderType("cutout"));
    }
}
