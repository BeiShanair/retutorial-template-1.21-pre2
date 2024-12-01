package com.besson.retutotial.screen;

import com.besson.retutotial.entity.custom.TigerEntity;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.text.Text;

public class TestScreen extends Screen {
    private TigerEntity tiger;
    public TestScreen(Text title, TigerEntity tiger) {
        super(title);
        this.tiger = tiger;
    }

    @Override
    protected void init() {
        ButtonWidget widget1 = ButtonWidget.builder(Text.of("Max Health + 10"),
                button -> {
                    increaseTigerMaxHealth();
                    this.client.getToastManager().add(
                            SystemToast.create(this.client, SystemToast.Type.NARRATOR_TOGGLE,
                                    Text.of("Max health increased by 10!"),
                                    Text.of("The tiger's max health is now " + tiger.getMaxHealth())
                    ));
                }).dimensions(40, 40, 120, 20).build();

        ButtonWidget widget2 = ButtonWidget.builder(Text.of("Max Health - 10"),
                button -> {
                    decreaseTigerMaxHealth();
                    this.client.getToastManager().add(
                            SystemToast.create(this.client, SystemToast.Type.NARRATOR_TOGGLE,
                                    Text.of("Max health decreased by 10!"),
                                    Text.of("The tiger's max health is now " + tiger.getMaxHealth())
                            ));
                }).dimensions(40, 70, 120, 20).build();

        this.addDrawableChild(widget1);
        this.addDrawableChild(widget2);
    }

    private void increaseTigerMaxHealth() {
        EntityAttributeInstance maxHealthAttribute = tiger.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        if (maxHealthAttribute != null) {
            maxHealthAttribute.setBaseValue(maxHealthAttribute.getBaseValue() + 10.0);
            tiger.setHealth((float) maxHealthAttribute.getBaseValue());
        }
    }

    private void decreaseTigerMaxHealth() {
        EntityAttributeInstance maxHealthAttribute = tiger.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        if (maxHealthAttribute != null) {
            maxHealthAttribute.setBaseValue(maxHealthAttribute.getBaseValue() - 10.0);
            tiger.setHealth((float) maxHealthAttribute.getBaseValue());
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.drawText(this.textRenderer, "This is a modifier screen for the Tiger entity.",
                40, 40 - this.textRenderer.fontHeight - 10, 0xFFFFFF, false);
    }
}
