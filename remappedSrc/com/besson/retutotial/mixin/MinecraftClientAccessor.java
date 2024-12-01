package com.besson.retutotial.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MinecraftClient.class)
public interface MinecraftClientAccessor {
    // Accessor注解，访问器，用于访问私有字段
    // Accessor注解中的模式有get和set，get表示获取字段的值，set表示设置字段的值
    // Accessor注解中的prefix表示字段的前缀，这个前缀会被去掉，然后首字母小写，作为字段的名字
    // 以下代码表示获取itemUseCooldown字段的值
    @Accessor
    int getItemUseCooldown();
    // 以下代码表示设置itemUseCooldown字段的值
    @Accessor
    void setItemUseCooldown(int itemUseCooldown);

//    // 当然，还可以采用更加灵活的方式，通过Accessor注解的value属性来指定字段的名字
//    // 以下代码表示获取attackCooldown字段的值
//    @Accessor("attackCooldown")
//    int getAttackCooldown2();
//    // 以下代码表示设置attackCooldown字段的值
//    @Accessor("attackCooldown")
//    void setAttackCooldown2(int attackCooldown);
}
