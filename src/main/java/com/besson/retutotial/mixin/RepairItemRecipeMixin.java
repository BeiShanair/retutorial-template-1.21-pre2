package com.besson.retutotial.mixin;

import com.besson.retutotial.item.ModItems;
import com.mojang.datafixers.util.Pair;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RepairItemRecipe;
import net.minecraft.recipe.input.CraftingRecipeInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RepairItemRecipe.class)
public class RepairItemRecipeMixin {
    @Inject(method = "findPair", at = @At("RETURN"), cancellable = true)
    private void prevent(CraftingRecipeInput input, CallbackInfoReturnable<Pair<ItemStack, ItemStack>> cir) {
        Pair<ItemStack, ItemStack> pair = cir.getReturnValue();
        if (pair != null) {
            ItemStack itemStack = pair.getFirst();
            ItemStack itemStack2 = pair.getSecond();
            if (itemStack.getItem() == ModItems.FIRE_ETHER && itemStack2.getItem() == ModItems.FIRE_ETHER) {
                cir.setReturnValue(null);
            }
        }

    }

}
