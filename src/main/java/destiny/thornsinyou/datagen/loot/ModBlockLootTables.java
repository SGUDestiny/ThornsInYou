package destiny.thornsinyou.datagen.loot;

import destiny.thornsinyou.block.ScarletRoseCropBlock;
import destiny.thornsinyou.registry.ModBlockRegistry;
import destiny.thornsinyou.registry.ModItemRegistry;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;
import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {

    private final Set<Block> generatedLootTables = new HashSet<>();
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
//        this.dropSelf(ModBlockRegistry.SCARLET_ROSE.get());

        this.add(ModBlockRegistry.POTTED_SCARLET_ROSE.get(), createPotFlowerItemTable(ModBlockRegistry.SCARLET_ROSE.get()));

        this.dropSelf(ModBlockRegistry.SCARLET_ROSE_BUSH.get());

        add(ModBlockRegistry.COPPER_KETTLE.get(), (block) -> LootTable.lootTable().withPool(this.applyExplosionCondition(block, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(block)
                .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY)).apply(CopyBeverageFunction.builder())))));

//        LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition
//                .hasBlockStateProperties(ModBlockRegistry.SCARLET_ROSE_CROP.get())
//                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(ScarletRoseCropBlock.AGE, 6));
//
//
//        this.add(ModBlockRegistry.SCARLET_ROSE_CROP.get(), createCropDrops(ModBlockRegistry.SCARLET_ROSE_CROP.get(), ModItemRegistry.SCARLET_ROSE_BULB.get(),
//                ModItemRegistry.SCARLET_ROSE_SEEDS.get(), lootitemcondition$builder));
    }

    @Override
    protected void add(Block block, LootTable.Builder builder) {
        this.generatedLootTables.add(block);
        this.map.put(block.getLootTable(), builder);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
