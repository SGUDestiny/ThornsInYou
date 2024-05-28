package destiny.thornsinyou.registry;

import destiny.thornsinyou.FoodValues;
import destiny.thornsinyou.ThornsInYou;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.item.DrinkableItem;

public class ModItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ThornsInYou.MODID);

    public static Item.Properties teaItem() {
        return new Item.Properties().stacksTo(8);
    }

    public static final RegistryObject<Item> SCARLET_ROSE_BULB = ITEMS.register("scarlet_rose_bulb",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SCARLET_ROSE_PETAL = ITEMS.register("scarlet_rose_petal",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SCARLET_ROSE_DRIED_PETAL = ITEMS.register("scarlet_rose_dried_petal",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCARLET_ROSE_SEEDS = ITEMS.register("scarlet_rose_seeds",
            () -> new ItemNameBlockItem(ModBlockRegistry.SCARLET_ROSE_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> SCARLET_ROSE_BUSH_SEEDS = ITEMS.register("scarlet_rose_bush_seeds",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EMPTY_TEA_CUP = ITEMS.register("empty_tea_cup",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
            ));

    public static final RegistryObject<Item> SCARLET_FRAGRANCE = ITEMS.register("scarlet_fragrance",
            () -> new DrinkableItem(teaItem()
                    .food(FoodValues.SCARLET_FRAGRANCE), true, false));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
