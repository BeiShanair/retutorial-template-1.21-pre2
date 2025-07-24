package com.besson.retutotial.enchanment;

import com.besson.retutotial.ReTutorial;
import com.besson.retutotial.enchanment.custom.TestEnchantment;
import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantmentEffects {
    public static final MapCodec<? extends EnchantmentEntityEffect> TEST =
            registerEntityEffect("test", TestEnchantment.CODEC);

    private static MapCodec<? extends EnchantmentEntityEffect> registerEntityEffect(String name,
                                                                                    MapCodec<? extends EnchantmentEntityEffect> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(ReTutorial.MOD_ID, name), codec);
    }
    public static void registerModEnchantments() {

    }
}
