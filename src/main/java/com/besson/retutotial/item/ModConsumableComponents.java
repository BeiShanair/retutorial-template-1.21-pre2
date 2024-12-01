package com.besson.retutotial.item;

import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.item.consume.UseAction;
import net.minecraft.sound.SoundEvents;

import java.util.List;

public class ModConsumableComponents {
    public static final ConsumableComponent STRAWBERRY = food()
            .consumeEffect(
                    new ApplyEffectsConsumeEffect(
                            List.of(
                                    new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 600, 2)
                            )
                    )
            )
            .build();


    public static ConsumableComponent.Builder food() {
        return ConsumableComponent.builder().consumeSeconds(1.6F).useAction(UseAction.EAT).sound(SoundEvents.ENTITY_GENERIC_EAT).consumeParticles(true);
    }
}
