package com.besson.retutotial.painting;

import com.besson.retutotial.ReTutorial;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModPaintingVariants {
    public static final RegistryKey<PaintingVariant> TEST = of("test");

    public static void bootstrap(Registerable<PaintingVariant> registry) {
        register(registry, TEST, 1, 1, true);
    }

    private static void register(Registerable<PaintingVariant> registry, RegistryKey<PaintingVariant> key, int width, int height, boolean hasAuthor) {
        registry.register(key, new PaintingVariant(
                width,
                height,
                key.getValue(),
                Optional.of(Text.translatable(key.getValue().toTranslationKey("painting", "title")).formatted(Formatting.YELLOW)),
                hasAuthor ? Optional.of(Text.translatable(key.getValue().toTranslationKey("painting", "author")).formatted(Formatting.GRAY)) : Optional.empty()));
    }

    private static RegistryKey<PaintingVariant> of(String id) {
        return RegistryKey.of(RegistryKeys.PAINTING_VARIANT, Identifier.of(ReTutorial.MOD_ID, id));
    }
    public static void init() {
    }
}
