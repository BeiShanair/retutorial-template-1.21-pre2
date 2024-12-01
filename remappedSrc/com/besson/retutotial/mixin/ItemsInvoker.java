package com.besson.retutotial.mixin;

import com.besson.retutotial.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Items.class)
public interface ItemsInvoker {
    // 静态方法调用器
    @Invoker("register")
    static Item register(String id, Item item) {
        throw new AssertionError();
    }
}
