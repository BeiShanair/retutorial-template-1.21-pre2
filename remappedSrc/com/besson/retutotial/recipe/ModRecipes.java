package com.besson.retutotial.recipe;

import com.besson.retutotial.ReTutorial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(ReTutorial.MOD_ID, PolishingMachineRecipe.Serializer.ID),
                PolishingMachineRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, Identifier.of(ReTutorial.MOD_ID, PolishingMachineRecipe.Type.ID),
                PolishingMachineRecipe.Type.INSTANCE);
    }

}
