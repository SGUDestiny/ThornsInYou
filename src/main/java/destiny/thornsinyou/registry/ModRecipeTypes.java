package destiny.thornsinyou.registry;

import destiny.thornsinyou.ThornsInYou;
import destiny.thornsinyou.crafting.CopperKettleRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, ThornsInYou.MODID);

    public static final RegistryObject<RecipeType<CopperKettleRecipe>> BREWING = RECIPE_TYPES.register("brewing", () -> registerRecipeType("brewing"));

    public static <T extends Recipe<?>> RecipeType<T> registerRecipeType(final String identifier) {
        return new RecipeType<>()
        {
            public String toString() {
                return ThornsInYou.MODID + ":" + identifier;
            }
        };
    }
}
