package destiny.thornsinyou.registry;

import destiny.thornsinyou.ThornsInYou;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ThornsInYou.MODID);
    public static final RegistryObject<CreativeModeTab> TIY_MAIN_TAB = CREATIVE_MODE_TABS.register("thornsinyou",
            () -> CreativeModeTab.builder()

            .icon(() -> new ItemStack(ModItemRegistry.SCARLET_ROSE_BULB.get()))
            .title(Component.translatable("itemGroup.thornsinyou.main_tab"))
            .displayItems((parameters, output) -> {
                output.accept(ModBlockRegistry.SCARLET_ROSE_BUSH.get());
                output.accept(ModBlockRegistry.SCARLET_ROSE.get());
                output.accept(ModItemRegistry.SCARLET_ROSE_BULB.get());
                output.accept(ModItemRegistry.SCARLET_ROSE_PETAL.get());
                output.accept(ModItemRegistry.SCARLET_ROSE_DRIED_PETAL.get());
                output.accept(ModItemRegistry.SCARLET_ROSE_SEEDS.get());
                output.accept(ModItemRegistry.SCARLET_ROSE_BUSH_SEEDS.get());
                output.accept(ModBlockRegistry.COPPER_KETTLE.get());
                output.accept(ModItemRegistry.SCARLET_FRAGRANCE.get());
                output.accept(ModItemRegistry.EMPTY_TEA_CUP.get());
            })
            .build()
    );
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
