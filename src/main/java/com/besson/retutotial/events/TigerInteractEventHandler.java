package com.besson.retutotial.events;

import com.besson.retutotial.entity.custom.TigerEntity;
import com.besson.retutotial.screen.TestScreen;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class TigerInteractEventHandler {
    public static void register(){
        UseEntityCallback.EVENT.register(
                (PlayerEntity player, World world, Hand hand, Entity entity, EntityHitResult hitResult) -> {
                    if (entity instanceof TigerEntity) {
                        MinecraftClient.getInstance().execute(() ->
                                MinecraftClient.getInstance().setScreen(new TestScreen(Text.empty(), (TigerEntity) entity)));
                        return ActionResult.SUCCESS;
                    }
                    return ActionResult.PASS;
                }
        );
    }
}
