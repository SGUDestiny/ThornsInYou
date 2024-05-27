package destiny.thornsinyou.registry;

import destiny.thornsinyou.ThornsInYou;
import destiny.thornsinyou.block.entity.container.CopperKettleMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, ThornsInYou.MODID);

    public static final RegistryObject<MenuType<CopperKettleMenu>> COPPER_KETTLE = MENU_TYPES
            .register("cooking_pot", () -> IForgeMenuType.create(CopperKettleMenu::new));
}
