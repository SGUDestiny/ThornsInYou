package destiny.thornsinyou.datagen;

import destiny.thornsinyou.registry.ModBlockRegistry;
import destiny.thornsinyou.registry.ModItemRegistry;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.TradeTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeI18n;
import net.minecraftforge.common.data.ForgeItemTagsProvider;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import java.util.function.Consumer;

public class ModRecipes {
    public static void register(Consumer<FinishedRecipe> consumer) {
        cuttingBoard(consumer);
        smoking(consumer);
        recipesVanilla(consumer);
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

    private static void recipesVanilla(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BREWING, ModBlockRegistry.COPPER_KETTLE.get(), 1)
                .pattern(" I ")
                .pattern("CCC")
                .pattern("CBC")
                .define('C', Items.COPPER_INGOT)
                .define('B', Blocks.COPPER_BLOCK)
                .define('I', Items.IRON_BARS)
                .unlockedBy("has_copper_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer);
    }
}
