package destiny.thornsinyou.datagen.builder;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import destiny.thornsinyou.ThornsInYou;
import destiny.thornsinyou.client.recipebook.CopperKettleRecipeBookTab;
import destiny.thornsinyou.registry.ModRecipeSerializers;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import vectorwing.farmersdelight.FarmersDelight;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

public class CopperKettleRecipeBuilder {
    private CopperKettleRecipeBookTab tab;
    private final List<Ingredient> ingredients = Lists.newArrayList();
    private final Item result;
    private final int count;
    private final int brewingTime;
    private final float experience;
    private final Item container;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();

    private CopperKettleRecipeBuilder(ItemLike resultIn, int count, int brewingTime, float experience, @Nullable ItemLike container) {
        this.result = resultIn.asItem();
        this.count = count;
        this.brewingTime = brewingTime;
        this.experience = experience;
        this.container = container != null ? container.asItem() : null;
        this.tab = null;
    }

    public static CopperKettleRecipeBuilder cookingPotRecipe(ItemLike mainResult, int count, int brewingTime, float experience) {
        return new CopperKettleRecipeBuilder(mainResult, count, brewingTime, experience, null);
    }

    public static CopperKettleRecipeBuilder cookingPotRecipe(ItemLike mainResult, int count, int brewingTime, float experience, ItemLike container) {
        return new CopperKettleRecipeBuilder(mainResult, count, brewingTime, experience, container);
    }

    public CopperKettleRecipeBuilder addIngredient(TagKey<Item> tagIn) {
        return addIngredient(Ingredient.of(tagIn));
    }

    public CopperKettleRecipeBuilder addIngredient(ItemLike itemIn) {
        return addIngredient(itemIn, 1);
    }

    public CopperKettleRecipeBuilder addIngredient(ItemLike itemIn, int quantity) {
        for (int i = 0; i < quantity; ++i) {
            addIngredient(Ingredient.of(itemIn));
        }
        return this;
    }

    public CopperKettleRecipeBuilder addIngredient(Ingredient ingredientIn) {
        return addIngredient(ingredientIn, 1);
    }

    public CopperKettleRecipeBuilder addIngredient(Ingredient ingredientIn, int quantity) {
        for (int i = 0; i < quantity; ++i) {
            ingredients.add(ingredientIn);
        }
        return this;
    }

    public CopperKettleRecipeBuilder unlockedBy(String criterionName, CriterionTriggerInstance criterionTrigger) {
        advancement.addCriterion(criterionName, criterionTrigger);
        return this;
    }

    public CopperKettleRecipeBuilder unlockedByItems(String criterionName, ItemLike... items) {
        return unlockedBy(criterionName, InventoryChangeTrigger.TriggerInstance.hasItems(items));
    }

    public CopperKettleRecipeBuilder unlockedByAnyIngredient(ItemLike... items) {
        advancement.addCriterion("has_any_ingredient", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items).build()));
        return this;
    }

    public CopperKettleRecipeBuilder setRecipeBookTab(CopperKettleRecipeBookTab tab) {
        this.tab = tab;
        return this;
    }

    public void build(Consumer<FinishedRecipe> consumerIn) {
        ResourceLocation location = ForgeRegistries.ITEMS.getKey(result);
        build(consumerIn, ThornsInYou.MODID + ":brewing/" + location.getPath());
    }

    public void build(Consumer<FinishedRecipe> consumerIn, String save) {
        ResourceLocation resourcelocation = ForgeRegistries.ITEMS.getKey(result);
        if ((new ResourceLocation(save)).equals(resourcelocation)) {
            throw new IllegalStateException("Brewing Recipe " + save + " should remove its 'save' argument");
        } else {
            build(consumerIn, new ResourceLocation(save));
        }
    }

    public void build(Consumer<FinishedRecipe> consumerIn, ResourceLocation id) {
        if (!advancement.getCriteria().isEmpty()) {
            advancement.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                    .rewards(AdvancementRewards.Builder.recipe(id))
                    .requirements(RequirementsStrategy.OR);
            ResourceLocation advancementId = new ResourceLocation(id.getNamespace(), "recipes/" + id.getPath());
            consumerIn.accept(new CopperKettleRecipeBuilder.Result(id, result, count, ingredients, brewingTime, experience, container, tab, advancement, advancementId));
        } else {
            consumerIn.accept(new CopperKettleRecipeBuilder.Result(id, result, count, ingredients, brewingTime, experience, container, tab));
        }
    }

    public static class Result implements FinishedRecipe
    {
        private final ResourceLocation id;
        private final CopperKettleRecipeBookTab tab;
        private final List<Ingredient> ingredients;
        private final Item result;
        private final int count;
        private final int brewingTime;
        private final float experience;
        private final Item container;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation idIn, Item resultIn, int countIn, List<Ingredient> ingredientsIn, int brewingTimeIn, float experienceIn, @Nullable Item containerIn, @Nullable CopperKettleRecipeBookTab tabIn, @Nullable Advancement.Builder advancement, @Nullable ResourceLocation advancementId) {
            this.id = idIn;
            this.tab = tabIn;
            this.ingredients = ingredientsIn;
            this.result = resultIn;
            this.count = countIn;
            this.brewingTime = brewingTimeIn;
            this.experience = experienceIn;
            this.container = containerIn;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        public Result(ResourceLocation idIn, Item resultIn, int countIn, List<Ingredient> ingredientsIn, int brewingTimeIn, float experienceIn, @Nullable Item containerIn, @Nullable CopperKettleRecipeBookTab tabIn) {
            this(idIn, resultIn, countIn, ingredientsIn, brewingTimeIn, experienceIn, containerIn, tabIn, null, null);
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            if (tab != null) {
                json.addProperty("recipe_book_tab", tab.toString());
            }

            JsonArray arrayIngredients = new JsonArray();

            for (Ingredient ingredient : ingredients) {
                arrayIngredients.add(ingredient.toJson());
            }
            json.add("ingredients", arrayIngredients);

            JsonObject objectResult = new JsonObject();
            objectResult.addProperty("items", ForgeRegistries.ITEMS.getKey(result).toString());
            if (count > 1) {
                objectResult.addProperty("count", count);
            }
            json.add("result", objectResult);

            if (container != null) {
                JsonObject objectContainer = new JsonObject();
                objectContainer.addProperty("items", ForgeRegistries.ITEMS.getKey(container).toString());
                json.add("container", objectContainer);
            }
            if (experience > 0) {
                json.addProperty("experience", experience);
            }
            json.addProperty("brewingTime", brewingTime);
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ModRecipeSerializers.BREWING.get();
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return advancement != null ? advancement.serializeToJson() : null;
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return advancementId;
        }
    }
}
