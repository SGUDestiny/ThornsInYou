package destiny.thornsinyou.datagen.loot;

import destiny.thornsinyou.registry.ModBlockRegistry;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlockRegistry.SCARLET_ROSE.get());

        this.add(ModBlockRegistry.POTTED_SCARLET_ROSE.get(), createPotFlowerItemTable(ModBlockRegistry.SCARLET_ROSE.get()));

        this.dropSelf(ModBlockRegistry.SCARLET_ROSE_BUSH.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
