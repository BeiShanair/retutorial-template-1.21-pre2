package com.besson.retutotial.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class ReTutorialMixin {
	// 注入代码
	// at: 注入的位置，HEAD表示在方法头部插入代码，RETURN表示在方法返回前插入代码，TAIL表示在方法尾部插入代码，INVOKE表示在方法调用前插入代码
	// method: 要注入的方法的签名
	// cancellable: 是否可以取消这个方法
	@Inject(at = @At("HEAD"), method = "loadWorld")
	private void init(CallbackInfo info) {
		System.out.println("Hello Minecraft!");
		// This code is injected into the start of MinecraftServer.loadWorld()V
	}
}