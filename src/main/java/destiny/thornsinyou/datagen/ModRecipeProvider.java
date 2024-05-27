package destiny.thornsinyou.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItemRegistry.SCARLET_ROSE_PETAL.get(), 3)
//                .requires(ModItemRegistry.SCARLET_ROSE_BULB.get())
//                .unlockedBy(getHasName(ModItemRegistry.SCARLET_ROSE_BULB.get()), has(ModItemRegistry.SCARLET_ROSE_BULB.get()))
//                .save(consumer);

        ModRecipes.register(consumer);
    }
}
