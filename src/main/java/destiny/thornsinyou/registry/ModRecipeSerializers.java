package destiny.thornsinyou.registry;

import destiny.thornsinyou.ThornsInYou;
import destiny.thornsinyou.crafting.CopperKettleRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.crafting.FoodServingRecipe;

public class ModRecipeSerializers {

        public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ThornsInYou.MODID);

        public static final RegistryObject<RecipeSerializer<?>> BREWING = RECIPE_SERIALIZERS.register("brewing", CopperKettleRecipe.Serializer::new);

}