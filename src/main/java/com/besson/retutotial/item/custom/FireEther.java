package com.besson.retutotial.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FireEther extends Item {
    public FireEther(Settings settings) {
        super(settings.maxDamage(128));
    }

    @Override
    public boolean hasRecipeRemainder() {
        return true;
    }

    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        ItemStack itemStack = stack.copy();
        if (itemStack.getDamage() < itemStack.getMaxDamage() - 1) {
            itemStack.setDamage(itemStack.getDamage() + 1);
            return itemStack;
        }
        return ItemStack.EMPTY;
    }

//    @Override
//    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
//        super.finishUsing(stack, world, user);
//
//        if (stack.getDamage() < stack.getMaxDamage() - 1) {
//            ItemStack itemStack = stack.copy();
//            itemStack.setDamage(itemStack.getDamage() + 1);
//            return itemStack;
//        }
//        return ItemStack.EMPTY;
//    }
}
