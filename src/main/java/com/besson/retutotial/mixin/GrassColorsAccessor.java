package com.besson.retutotial.mixin;

import net.minecraft.world.biome.GrassColors;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GrassColors.class)
public interface GrassColorsAccessor {
    // @Accessor 也可以访问静态字段
    // 通过 @Accessor 注解的方法必须是静态的
    // 以下是 GrassColors 类中的字段
    @Accessor("colorMap")
    static int[] getColorMap() {
        throw new AssertionError();
    }

    // 设置字段的值
    @Accessor("colorMap")
    static void setColorMap(int[] colorMap) {
        throw new AssertionError();
    }

}
