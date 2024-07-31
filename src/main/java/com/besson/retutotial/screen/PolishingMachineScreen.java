package com.besson.retutotial.screen;

import com.besson.retutotial.ReTutorial;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class PolishingMachineScreen extends HandledScreen<PolishingMachineScreenHandler> {
    // 设置GUI，其实是渲染GUI

    // 设置GUI的材质文件
    private static final Identifier TEXTURE = Identifier.of(ReTutorial.MOD_ID,"textures/gui/polishing_machine_gui.png");
    public PolishingMachineScreen(PolishingMachineScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    // 可以通过init来重新设置标题的位置
    @Override
    protected void init() {
        super.init();
        // 比如说这样（根据你的GUI安排）
        // titleY = 10;
    }

    // 设置背景
    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f,1f,1f,1f);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderProgressArrow(context, x, y);
    }

    // 这个是渲染加工时的箭头
    private void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting() && handler.isRaining()) {
            // 获取这部分箭头的位置（一般这种东西是放在GUI主体的外面）
            context.drawTexture(TEXTURE, x + 85, y + 30, 176, 0, 8, handler.getScaledProgress());
        }
    }

    // 渲染方法
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
