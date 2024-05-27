package destiny.thornsinyou.client.gui;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import org.joml.Matrix4f;
import destiny.thornsinyou.utility.TextUtils;

public class CopperKettleTooltip implements ClientTooltipComponent
{
    private static final int ITEM_SIZE = 16;
    private static final int MARGIN = 4;

    private final int textSpacing = Minecraft.getInstance().font.lineHeight + 1;
    private final ItemStack brewStack;

    public CopperKettleTooltip(CookingPotTooltipComponent tooltip) {
        this.brewStack = tooltip.mealStack;
    }

    @Override
    public int getHeight() {
        return brewStack.isEmpty() ? textSpacing : textSpacing + ITEM_SIZE;
    }

    @Override
    public int getWidth(Font font) {
        if (!brewStack.isEmpty()) {
            MutableComponent textServingsOf = brewStack.getCount() == 1
                    ? TextUtils.getTranslation("tooltip.copper_kettle.single_serving")
                    : TextUtils.getTranslation("tooltip.copper_kettle.many_servings", brewStack.getCount());
            return Math.max(font.width(textServingsOf), font.width(brewStack.getHoverName()) + 20);
        } else {
            return font.width(TextUtils.getTranslation("tooltip.copper_kettle.empty"));
        }
    }

    @Override
    public void renderImage(Font font, int mouseX, int mouseY, GuiGraphics gui) {
        if (brewStack.isEmpty()) return;
        gui.renderItem(brewStack, mouseX, mouseY + textSpacing, 0);
    }

    @Override
    public void renderText(Font font, int x, int y, Matrix4f matrix4f, MultiBufferSource.BufferSource bufferSource) {
        Integer color = ChatFormatting.GRAY.getColor();
        int gray = color == null ? -1 : color;

        if (!brewStack.isEmpty()) {
            MutableComponent textServingsOf = brewStack.getCount() == 1
                    ? TextUtils.getTranslation("tooltip.copper_kettle.single_serving")
                    : TextUtils.getTranslation("tooltip.copper_kettle.many_servings", brewStack.getCount());

            font.drawInBatch(textServingsOf, (float) x, (float) y, gray, true, matrix4f, bufferSource, Font.DisplayMode.NORMAL, 0, 15728880);
            font.drawInBatch(brewStack.getHoverName(), x + ITEM_SIZE + MARGIN, y + textSpacing + MARGIN, -1, true, matrix4f, bufferSource, Font.DisplayMode.NORMAL, 0, 15728880);
        } else {
            MutableComponent textEmpty = TextUtils.getTranslation("tooltip.copper_kettle.empty");
            font.drawInBatch(textEmpty, x, y, gray, true, matrix4f, bufferSource, Font.DisplayMode.NORMAL, 0, 15728880);
        }
    }

    public static record CookingPotTooltipComponent(ItemStack mealStack) implements TooltipComponent
    {
    }
}