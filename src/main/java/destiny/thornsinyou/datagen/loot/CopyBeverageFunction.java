package destiny.thornsinyou.datagen.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import destiny.thornsinyou.block.entity.CopperKettleBlockEntity;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import destiny.thornsinyou.registry.ModLootFunctions;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class CopyBeverageFunction extends LootItemConditionalFunction {
    public static final ResourceLocation ID = new ResourceLocation("thornsinyou", "copy_beverage");

    private CopyBeverageFunction(LootItemCondition[] conditions) {
        super(conditions);
    }

    public static LootItemConditionalFunction.Builder<?> builder() {
        return simpleBuilder(destiny.thornsinyou.datagen.loot.CopyBeverageFunction::new);
    }

    protected ItemStack run(ItemStack stack, LootContext context) {
        BlockEntity tile = (BlockEntity)context.getParamOrNull(LootContextParams.BLOCK_ENTITY);
        if (tile instanceof CopperKettleBlockEntity) {
            CompoundTag tag = ((CopperKettleBlockEntity)tile).writeBrew(new CompoundTag());
            if (!tag.isEmpty()) {
                stack.addTagElement("BlockEntityTag", tag);
            }
        }

        return stack;
    }

    public LootItemFunctionType getType() {
        return (LootItemFunctionType) ModLootFunctions.COPY_BEVERAGE.get();
    }

    public static class Serializer extends LootItemConditionalFunction.Serializer<destiny.thornsinyou.datagen.loot.CopyBeverageFunction> {
        public Serializer() {
        }

        public destiny.thornsinyou.datagen.loot.CopyBeverageFunction deserialize(JsonObject json, JsonDeserializationContext context, LootItemCondition[] conditions) {
            return new destiny.thornsinyou.datagen.loot.CopyBeverageFunction(conditions);
        }
    }
}

