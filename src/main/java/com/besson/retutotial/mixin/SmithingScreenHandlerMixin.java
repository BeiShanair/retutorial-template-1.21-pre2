package com.besson.retutotial.mixin;

import com.besson.retutotial.util.SmithingRemainderHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.SmithingScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(SmithingScreenHandler.class)
public class SmithingScreenHandlerMixin {
    @Inject(method = "decrementStack", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isEmpty()Z"),locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void captureStack(int slot, CallbackInfo ci, ItemStack itemStack) {
        SmithingRemainderHandler.REMAINDER_STACK.set(itemStack.getRecipeRemainder());
    }

    @ModifyArg(method = "decrementStack", at = @At(value = "INVOKE", target = "Lnet/minecraft/inventory/Inventory;setStack(ILnet/minecraft/item/ItemStack;)V"), index = 1)
    private ItemStack replaceRemainder(ItemStack stack) {
        ItemStack remainder = SmithingRemainderHandler.REMAINDER_STACK.get();
        SmithingRemainderHandler.REMAINDER_STACK.remove();
        return stack.isOf(Items.AIR) ? remainder : stack;
    }
}
