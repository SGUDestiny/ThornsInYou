package destiny.thornsinyou.world.modifier;

import com.mojang.serialization.Codec;
import java.util.Optional;

import destiny.thornsinyou.registry.ModBiomeModifiers;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.common.world.BiomeModifier.Phase;

public record AddFeaturesByFilterBiomeModifier(HolderSet<Biome> allowedBiomes, Optional<HolderSet<Biome>> deniedBiomes, Optional<Float> minimumTemperature, Optional<Float> maximumTemperature, HolderSet<PlacedFeature> features, GenerationStep.Decoration step) implements BiomeModifier {
    public AddFeaturesByFilterBiomeModifier(HolderSet<Biome> allowedBiomes, Optional<HolderSet<Biome>> deniedBiomes, Optional<Float> minimumTemperature, Optional<Float> maximumTemperature, HolderSet<PlacedFeature> features, GenerationStep.Decoration step) {
        this.allowedBiomes = allowedBiomes;
        this.deniedBiomes = deniedBiomes;
        this.minimumTemperature = minimumTemperature;
        this.maximumTemperature = maximumTemperature;
        this.features = features;
        this.step = step;
    }

    public void modify(Holder<Biome> biome, BiomeModifier.Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD && this.allowedBiomes.contains(biome)) {
            if (this.deniedBiomes.isPresent() && ((HolderSet)this.deniedBiomes.get()).contains(biome)) {
                return;
            }

            if (this.minimumTemperature.isPresent() && ((Biome)biome.get()).getBaseTemperature() < (Float)this.minimumTemperature.get()) {
                return;
            }

            if (this.maximumTemperature.isPresent() && ((Biome)biome.get()).getBaseTemperature() > (Float)this.maximumTemperature.get()) {
                return;
            }

            BiomeGenerationSettingsBuilder generationSettings = builder.getGenerationSettings();
            this.features.forEach((holder) -> {
                generationSettings.addFeature(this.step, holder);
            });
        }

    }

    public Codec<? extends BiomeModifier> codec() {
        return (Codec) ModBiomeModifiers.ADD_FEATURES_BY_FILTER.get();
    }

    public HolderSet<Biome> allowedBiomes() {
        return this.allowedBiomes;
    }

    public Optional<HolderSet<Biome>> deniedBiomes() {
        return this.deniedBiomes;
    }

    public Optional<Float> minimumTemperature() {
        return this.minimumTemperature;
    }

    public Optional<Float> maximumTemperature() {
        return this.maximumTemperature;
    }

    public HolderSet<PlacedFeature> features() {
        return this.features;
    }

    public GenerationStep.Decoration step() {
        return this.step;
    }
}