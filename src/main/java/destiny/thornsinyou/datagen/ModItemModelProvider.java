package destiny.thornsinyou.datagen;

import destiny.thornsinyou.ThornsInYou;
import destiny.thornsinyou.registry.ModBlockRegistry;
import destiny.thornsinyou.registry.ModItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ThornsInYou.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItemRegistry.SCARLET_ROSE_BULB);
        simpleItem(ModItemRegistry.SCARLET_ROSE_PETAL);
        simpleItem(ModItemRegistry.SCARLET_ROSE_DRIED_PETAL);

        simpleItem(ModItemRegistry.SCARLET_ROSE_SEEDS);
        simpleItem(ModItemRegistry.SCARLET_ROSE_BUSH_SEEDS);

        simpleItem(ModItemRegistry.EMPTY_TEA_CUP);

        simpleItem(ModItemRegistry.SCARLET_FRAGRANCE);

        simpleBlockItemBlockTexture(ModBlockRegistry.SCARLET_ROSE);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(ThornsInYou.MODID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleBlockItemBlockTexture(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(ThornsInYou.MODID,"block/" + item.getId().getPath()));
    }
}
