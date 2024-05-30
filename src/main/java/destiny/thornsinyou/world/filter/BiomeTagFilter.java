package destiny.thornsinyou.world.filter;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementFilter;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraftforge.registries.ForgeRegistries;
import destiny.thornsinyou.registry.ModPlacementModifiers;

public class BiomeTagFilter extends PlacementFilter {
    public static final Codec<destiny.thornsinyou.world.filter.BiomeTagFilter> CODEC = RecordCodecBuilder.create((builder) -> {
        return builder.group(TagKey.codec(ForgeRegistries.BIOMES.getRegistryKey()).fieldOf("tag").forGetter((instance) -> {
            return instance.biomeTag;
        })).apply(builder, destiny.thornsinyou.world.filter.BiomeTagFilter::new);
    });
    private final TagKey<Biome> biomeTag;

    private BiomeTagFilter(TagKey<Biome> biomeTag) {
        this.biomeTag = biomeTag;
    }

    public static destiny.thornsinyou.world.filter.BiomeTagFilter biomeIsInTag(TagKey<Biome> biomeTag) {
        return new destiny.thornsinyou.world.filter.BiomeTagFilter(biomeTag);
    }

    protected boolean shouldPlace(PlacementContext context, RandomSource random, BlockPos pos) {
        Holder<Biome> biome = context.getLevel().getBiome(pos);
        return biome.is(this.biomeTag);
    }

    public PlacementModifierType<?> type() {
        return (PlacementModifierType)ModPlacementModifiers.BIOME_TAG.get();
    }
}