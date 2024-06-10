package com.besson.retutotial.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    // 注册食物组件
    public static final FoodComponent STRAWBERRY = new FoodComponent.Builder().nutrition(4).saturationModifier(0.6F)
            .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 600), 0.25f).build();
    public static final FoodComponent CHEESE = new FoodComponent.Builder().nutrition(8).saturationModifier(0.8F).build();
}
