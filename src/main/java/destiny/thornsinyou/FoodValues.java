package destiny.thornsinyou;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class FoodValues {
    public static final int BRIEF_DURATION = 600;    // 30 seconds
    public static final int SHORT_DURATION = 1200;    // 1 minute
    public static final int MEDIUM_DURATION = 3600;    // 3 minutes
    public static final int LONG_DURATION = 6000;    // 5 minutes
    public static final int VERY_LONG_DURATION = 12000;    // 10 minutes
    public static final int VERY_VERY_LONG_DURATION = 36000;    // 30 minutes

    public static final FoodProperties SCARLET_FRAGRANCE = (new FoodProperties.Builder())
            .alwaysEat()
            .nutrition(8)
            .saturationMod(0.7F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, SHORT_DURATION, 3), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, VERY_LONG_DURATION + LONG_DURATION, 4), 1.0F)
            .build();
}
