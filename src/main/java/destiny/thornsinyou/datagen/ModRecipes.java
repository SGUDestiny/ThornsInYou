package destiny.thornsinyou.datagen;

import destiny.thornsinyou.registry.ModItemRegistry;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.TradeTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import java.util.function.Consumer;

public class ModRecipes {
    public static void register(Consumer<FinishedRecipe> consumer) {
        cuttingBoard(consumer);
        smoking(consumer);
    }

    private static void cuttingBoard(Consumer<FinishedRecipe> consumer) {
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItemRegistry.SCARLET_ROSE_BULB.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItemRegistry.SCARLET_ROSE_PETAL.get(), 2)
                .build(consumer);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItemRegistry.SCARLET_ROSE_BUSH_SEEDS.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItemRegistry.SCARLET_ROSE_SEEDS.get(), 3)
                .build(consumer);
    }

    private static void smoking(Consumer<FinishedRecipe> recipeConsumer) {
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ModItemRegistry.SCARLET_ROSE_PETAL.get()), RecipeCategory.FOOD, ModItemRegistry.SCARLET_ROSE_DRIED_PETAL.get(), 5, 200)
                .unlockedBy("criteria", InventoryChangeTrigger.TriggerInstance.hasItems(ModItemRegistry.SCARLET_ROSE_PETAL.get()))
                .save(recipeConsumer);
    }
}
