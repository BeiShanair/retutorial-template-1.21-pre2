package com.besson.retutotial.mixin;

import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ItemRenderer.class)
public interface ItemRenderAccessor {
    // 使用@Accessor注解获取ItemRenderer的models字段
    @Accessor("models")
    ItemModels getModels();
}
