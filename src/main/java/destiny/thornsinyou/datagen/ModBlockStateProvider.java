package destiny.thornsinyou.datagen;

import destiny.thornsinyou.ThornsInYou;
import destiny.thornsinyou.block.ScarletRoseCropBlock;
import destiny.thornsinyou.registry.ModBlockRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Function;

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

        makeScarletRoseCrop((CropBlock) ModBlockRegistry.SCARLET_ROSE_CROP.get(), "scarlet_rose_stage_", "scarlet_rose_stage_");
    }

    public void makeScarletRoseCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> scarletRoseStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] scarletRoseStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().cross(modelName + state.getValue(((ScarletRoseCropBlock) block).getAgeProperty()),
                new ResourceLocation(ThornsInYou.MODID, "block/" + textureName + state.getValue(((ScarletRoseCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }
}
