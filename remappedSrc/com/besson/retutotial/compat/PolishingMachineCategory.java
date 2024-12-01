package com.besson.retutotial.compat;

import com.besson.retutotial.ReTutorial;
import com.besson.retutotial.block.ModBlocks;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

public class PolishingMachineCategory implements DisplayCategory<BasicDisplay> {
    // 编写自己的分类，实现DisplayCategory接口

    // 获取GUI的纹理
    public static final Identifier TEXTURE =
            Identifier.of(ReTutorial.MOD_ID, "textures/gui/polishing_machine_gui.png");

    // 注册分类标识
    public static final CategoryIdentifier<PolishingMachineDisplay> POLISHING_MACHINE =
            CategoryIdentifier.of(ReTutorial.MOD_ID, "polishing_machine");

    // 获取分类标识
    @Override
    public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier() {
        return POLISHING_MACHINE;
    }

    // 获取分类的标题（这个直接用我们写过的翻译即可）
    @Override
    public Text getTitle() {
        return Text.translatable("container.retutorial.polishing_machine");
    }

    // 获取分类的图标（获取方块的图标）
    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.POLISHING_MACHINE.asItem().getDefaultStack());
    }

    // 设置GUI的显示（也就是在这里，如果按照官方的引入方法，将导致Rectangle及其同级的类无法找到）
    @Override
    public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds) {
        // 新建一个起始点，用于设置GUI的位置
        // 这里的getCenterX()和getCenterY()是REI的GUI的中心点（肯定得设置偏移，不然就从中心开始渲染了）
        final Point startPoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 45);
        List<Widget> widgets = new LinkedList<>();

        // 创建纹理，截取的是（从左上角开始的）175*82的区域（也就是下面的玩家物品栏和快捷栏不要）
        widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x, startPoint.y, 175, 82)));

        // 创建输入和输出的槽
        // 而这里添加的物品栏是和我们创建的屏幕处理器中的物品栏一样的
        if (!display.getInputEntries().isEmpty()) {
            widgets.add(Widgets.createSlot(new Point(startPoint.x + 80, startPoint.y + 11))
                    .entries(display.getInputEntries().get(0)));
        }

        if (!display.getOutputEntries().isEmpty()) {
            widgets.add(Widgets.createSlot(new Point(startPoint.x + 80, startPoint.y + 59))
                    .markOutput().entries(display.getOutputEntries().get(0)));
        }

        return widgets;
    }

    // 获取GUI的宽度（也就是REI的GUI背景要显示的宽度）
    // 因为我们的GUI是叠在REI的GUI上的，所以这里的宽度要设置得合适
    @Override
    public int getDisplayHeight() {
        return 90;
    }
}
