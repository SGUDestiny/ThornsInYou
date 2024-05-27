package destiny.thornsinyou.registry;

import destiny.thornsinyou.ThornsInYou;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ThornsInYou.MODID);

    public static final RegistryObject<Item> SCARLET_ROSE_BULB = ITEMS.register("scarlet_rose_bulb",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SCARLET_ROSE_PETAL = ITEMS.register("scarlet_rose_petal",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SCARLET_ROSE_DRIED_PETAL = ITEMS.register("scarlet_rose_dried_petal",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCARLET_ROSE_SEEDS = ITEMS.register("scarlet_rose_seeds",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SCARLET_ROSE_BUSH_SEEDS = ITEMS.register("scarlet_rose_bush_seeds",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
