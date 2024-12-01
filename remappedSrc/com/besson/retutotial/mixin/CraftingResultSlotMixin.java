package com.besson.retutotial.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CraftingResultSlot.class)
public class CraftingResultSlotMixin {
    @Inject(method = "onTakeItem", at = @At(value = "INVOKE_ASSIGN", target =
            "Lnet/minecraft/recipe/RecipeManager;" +
            "getRemainingStacks(Lnet/minecraft/recipe/RecipeType;Lnet/minecraft/recipe/input/RecipeInput;" +
            "Lnet/minecraft/world/World;)Lnet/minecraft/util/collection/DefaultedList;"))
    private void removeNonDurabilityTools(PlayerEntity player, ItemStack stack, CallbackInfo ci,
                                          @Local LocalRef<DefaultedList<ItemStack>> defaultedListRef) {
        Item check = null;
        DefaultedList<ItemStack> defaultedList = defaultedListRef.get();
        int length = defaultedList.size();
        boolean isRepair = false;
        for (ItemStack itemStack : defaultedList) {
            if (itemStack.equals(ItemStack.EMPTY)) continue;
            if (check == null) {
                check = itemStack.getItem();
                continue;
            }
            if (itemStack.isOf(check)) {
                if (isRepair) {
                    isRepair = false;
                    break;
                }
                isRepair = true;
            }
        }
        if (isRepair) {
            defaultedListRef.set(DefaultedList.ofSize(length, ItemStack.EMPTY));
        }

    }
}
