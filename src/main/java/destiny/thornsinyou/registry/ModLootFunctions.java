package destiny.thornsinyou.registry;

import destiny.thornsinyou.ThornsInYou;
import destiny.thornsinyou.datagen.loot.CopyBeverageFunction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModLootFunctions {
    public static final DeferredRegister<LootItemFunctionType> LOOT_FUNCTIONS = DeferredRegister.create(BuiltInRegistries.LOOT_FUNCTION_TYPE.key(), ThornsInYou.MODID);

    public static final RegistryObject<LootItemFunctionType> COPY_BEVERAGE = LOOT_FUNCTIONS.register("copy_beverage", () -> new LootItemFunctionType(new CopyBeverageFunction.Serializer()));
}
