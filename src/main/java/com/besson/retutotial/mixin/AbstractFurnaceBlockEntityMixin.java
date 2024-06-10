package com.besson.retutotial.mixin;

import com.besson.retutotial.item.ModItems;
import com.google.common.collect.Maps;
import net.minecraft.SharedConstants;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Util;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.LinkedHashMap;
import java.util.Map;

// 使用Mixin，注册新的燃烧物
@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class AbstractFurnaceBlockEntityMixin {
    @Unique
    private static boolean isNonFlammableWood(Item item) {
        return item.getRegistryEntry().isIn(ItemTags.NON_FLAMMABLE_WOOD);
    }

    @Shadow
    private static void addFuel(Map<Item, Integer> fuelTimes, ItemConvertible item, int fuelTime) {
        Item item2 = item.asItem();
        if (isNonFlammableWood(item2)) {
            if (SharedConstants.isDevelopment) {
                throw Util.throwOrPause(new IllegalStateException("A developer tried to explicitly make fire resistant item "
                        + item2.getName(null).getString() + " a furnace fuel. That will not work!"));
            }
            return;
        }
        fuelTimes.put(item2, fuelTime);
    }

    @Shadow private static volatile @Nullable Map<Item, Integer> fuelTimes;

    // 添加新的燃烧物
    // 注入到createFuelTimeMap方法的头部
    @Inject(at = @At("HEAD"), method = "createFuelTimeMap", cancellable = true)
    private static void getFuelTimeMixin(CallbackInfoReturnable<Map<Item, Integer>> cir) {
        LinkedHashMap<Item, Integer> map2 = Maps.newLinkedHashMap();
        addFuel(map2, ModItems.ANTHRACITE, 1600);
        fuelTimes = map2;
    }
}
