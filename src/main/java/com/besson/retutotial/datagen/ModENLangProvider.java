package com.besson.retutotial.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class ModENLangProvider extends FabricLanguageProvider {
    public ModENLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add("item.retutorial.ice_ether", "Ice Ether");
        translationBuilder.add("item.retutorial.raw_ice_ether", "Raw Ice Ether");

        translationBuilder.add("block.retutorial.ice_ether_block", "Ice Ether Block");
        translationBuilder.add("block.retutorial.ice_ether_ore", "Ice Ether Ore");
        translationBuilder.add("block.retutorial.raw_ice_ether_block", "Raw Ice Ether Block");

        translationBuilder.add("itemGroup.retutorial", "Retutorial");
    }
}
