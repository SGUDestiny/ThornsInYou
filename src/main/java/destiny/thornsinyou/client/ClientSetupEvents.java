package destiny.thornsinyou.client;

import destiny.thornsinyou.ThornsInYou;
import destiny.thornsinyou.client.gui.CopperKettleTooltip;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ThornsInYou.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetupEvents
{

    @SubscribeEvent
    public static void registerCustomTooltipRenderers(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(CopperKettleTooltip.CookingPotTooltipComponent.class, CopperKettleTooltip::new);
    }
}