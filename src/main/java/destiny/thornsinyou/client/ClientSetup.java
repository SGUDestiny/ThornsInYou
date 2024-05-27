package destiny.thornsinyou.client;

import destiny.thornsinyou.block.entity.CopperKettleBlockEntity;
import destiny.thornsinyou.client.gui.CopperKettleScreen;
import destiny.thornsinyou.registry.ModMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import vectorwing.farmersdelight.client.gui.ComfortHealthOverlay;
import vectorwing.farmersdelight.client.gui.NourishmentHungerOverlay;

public class ClientSetup
{
    public static void init(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> MenuScreens.register(ModMenuTypes.COPPER_KETTLE.get(), CopperKettleScreen::new));
    }
}