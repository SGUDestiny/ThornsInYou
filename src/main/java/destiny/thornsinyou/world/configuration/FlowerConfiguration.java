package destiny.thornsinyou.world.configuration;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import javax.annotation.Nullable;
import java.util.Optional;

public record FlowerConfiguration(int tries, int xzSpread, int ySpread, Holder<PlacedFeature> primaryFeature, Holder<PlacedFeature> secondaryFeature, @Nullable Holder<PlacedFeature> floorFeature) implements FeatureConfiguration {
    public static final Codec<destiny.thornsinyou.world.configuration.FlowerConfiguration> CODEC = RecordCodecBuilder.create((config) -> {
        return config.group(ExtraCodecs.POSITIVE_INT.fieldOf("tries").orElse(64).forGetter(destiny.thornsinyou.world.configuration.FlowerConfiguration::tries), ExtraCodecs.NON_NEGATIVE_INT.fieldOf("xz_spread").orElse(4).forGetter(destiny.thornsinyou.world.configuration.FlowerConfiguration::xzSpread), ExtraCodecs.NON_NEGATIVE_INT.fieldOf("y_spread").orElse(3).forGetter(destiny.thornsinyou.world.configuration.FlowerConfiguration::ySpread), PlacedFeature.CODEC.fieldOf("primary_feature").forGetter(destiny.thornsinyou.world.configuration.FlowerConfiguration::primaryFeature), PlacedFeature.CODEC.fieldOf("secondary_feature").forGetter(destiny.thornsinyou.world.configuration.FlowerConfiguration::secondaryFeature), PlacedFeature.CODEC.optionalFieldOf("floor_feature").forGetter((floorConfig) -> {
            return Optional.ofNullable(floorConfig.floorFeature);
        })).apply(config, (tries, xzSpread, yspread, primary, secondary, floor) -> {
            return (destiny.thornsinyou.world.configuration.FlowerConfiguration)floor.map((placedFeatureHolder) -> {
                return new destiny.thornsinyou.world.configuration.FlowerConfiguration(tries, xzSpread, yspread, primary, secondary, placedFeatureHolder);
            }).orElseGet(() -> {
                return new destiny.thornsinyou.world.configuration.FlowerConfiguration(tries, xzSpread, yspread, primary, secondary, (Holder)null);
            });
        });
    });

    public FlowerConfiguration(int tries, int xzSpread, int ySpread, Holder<PlacedFeature> primaryFeature, Holder<PlacedFeature> secondaryFeature, @Nullable Holder<PlacedFeature> floorFeature) {
        this.tries = tries;
        this.xzSpread = xzSpread;
        this.ySpread = ySpread;
        this.primaryFeature = primaryFeature;
        this.secondaryFeature = secondaryFeature;
        this.floorFeature = floorFeature;
    }

    public int tries() {
        return this.tries;
    }

    public int xzSpread() {
        return this.xzSpread;
    }

    public int ySpread() {
        return this.ySpread;
    }

    public Holder<PlacedFeature> primaryFeature() {
        return this.primaryFeature;
    }

    public Holder<PlacedFeature> secondaryFeature() {
        return this.secondaryFeature;
    }

    public Holder<PlacedFeature> floorFeature() {
        return this.floorFeature;
    }
}