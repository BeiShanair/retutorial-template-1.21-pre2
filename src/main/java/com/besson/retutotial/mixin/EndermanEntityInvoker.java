package com.besson.retutotial.mixin;

import net.minecraft.entity.mob.EndermanEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(EndermanEntity.class)
public interface EndermanEntityInvoker {
    // 调用方法
    @Invoker("teleportTo")
    boolean invokeTeleportTo(double x, double y, double z);
}
