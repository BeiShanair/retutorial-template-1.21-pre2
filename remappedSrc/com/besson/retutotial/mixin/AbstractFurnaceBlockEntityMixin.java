package com.besson.retutotial.mixin;

import com.besson.retutotial.item.ModItems;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.Item;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

// 使用Mixin，注册新的燃烧物
@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class AbstractFurnaceBlockEntityMixin {
    // @Shadow注解，用于获取字段
    // 这里获取的是fuelTimes字段
    @Shadow private static volatile @Nullable Map<Item, Integer> fuelTimes;

    // 添加新的燃烧物
    // 注入到createFuelTimeMap方法的尾部
    @Inject(at = @At("TAIL"), method = "createFuelTimeMap")
    private static void getFuelTimeMixin(CallbackInfoReturnable<Map<Item, Integer>> cir) {
        fuelTimes.put(ModItems.ANTHRACITE, 1600);
    }
}
