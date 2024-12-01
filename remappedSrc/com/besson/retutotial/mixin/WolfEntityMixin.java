package com.besson.retutotial.mixin;

import com.besson.retutotial.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WolfEntity.class)
public abstract class WolfEntityMixin extends TameableEntity {
    protected WolfEntityMixin(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    public void interactMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> info) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(ModItems.ICE_ETHER_WOLF_ARMOR) && this.isOwner(player) && this.getBodyArmor().isEmpty() && !this.isBaby()) {
            this.equipBodyArmor(itemStack.copyWithCount(1));
            itemStack.decrementUnlessCreative(1, player);
            info.setReturnValue(ActionResult.SUCCESS);
            info.cancel();
        }
    }
}
