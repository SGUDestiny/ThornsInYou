package destiny.thornsinyou.client.recipebook;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import destiny.thornsinyou.ThornsInYou;
import destiny.thornsinyou.crafting.CopperKettleRecipe;
import destiny.thornsinyou.registry.ModItemRegistry;
import destiny.thornsinyou.registry.ModRecipeTypes;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.client.event.RegisterRecipeBookCategoriesEvent;
import vectorwing.farmersdelight.common.item.DrinkableItem;

import java.util.function.Supplier;

public class RecipeCategories {
    public static final Supplier<RecipeBookCategories> BREWING_SEARCH = Suppliers.memoize(() -> RecipeBookCategories.create("BREWING_SEARCH", new ItemStack(Items.COMPASS)));
    public static final Supplier<RecipeBookCategories> BREWING_TEA = Suppliers.memoize(() -> RecipeBookCategories.create("BREWING_TEA", new ItemStack(ModItemRegistry.SCARLET_FRAGRANCE.get())));
    public static final Supplier<RecipeBookCategories> BREWING_MISC = Suppliers.memoize(() -> RecipeBookCategories.create("BREWING_MISC", new ItemStack(ModItemRegistry.SCARLET_ROSE_DRIED_PETAL.get())));

    public static void init(RegisterRecipeBookCategoriesEvent event) {
        event.registerBookCategories(ThornsInYou.RECIPE_TYPE_BREWING, ImmutableList.of(BREWING_SEARCH.get(), BREWING_TEA.get(), BREWING_MISC.get()));
        event.registerAggregateCategory(BREWING_SEARCH.get(), ImmutableList.of(BREWING_TEA.get(), BREWING_MISC.get()));
        event.registerRecipeCategoryFinder(ModRecipeTypes.BREWING.get(), recipe ->
        {
            if (recipe instanceof CopperKettleRecipe brewingRecipe) {
                CopperKettleRecipeBookTab tab = brewingRecipe.getRecipeBookTab();
                if (tab != null) {
                    return switch (tab) {
                        case TEA -> BREWING_TEA.get();
                        case MISC -> BREWING_MISC.get();
                        default -> BREWING_TEA.get();
                    };
                }
            }
            return BREWING_MISC.get();
        });
    }
}