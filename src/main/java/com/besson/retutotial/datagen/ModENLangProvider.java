package com.besson.retutotial.datagen;

import com.besson.retutotial.block.ModBlocks;
import com.besson.retutotial.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModENLangProvider extends FabricLanguageProvider {
    public ModENLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);

    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.ICE_ETHER, "Ice Ether");
        translationBuilder.add(ModItems.RAW_ICE_ETHER, "Raw Ice Ether");
        translationBuilder.add(ModItems.STRAWBERRY, "Strawberry");
        translationBuilder.add(ModItems.CHEESE, "Cheese");
        translationBuilder.add(ModItems.ANTHRACITE, "Anthracite");
        translationBuilder.add(ModItems.PROSPECTOR, "Prospector");
        translationBuilder.add(ModItems.PLATE, "Plate");
        translationBuilder.add(ModItems.FIRE_ETHER, "Fire Ether");

        translationBuilder.add(ModItems.FIRE_ETHER_AXE, "Fire Ether Axe");
        translationBuilder.add(ModItems.FIRE_ETHER_PICKAXE, "Fire Ether Pickaxe");
        translationBuilder.add(ModItems.FIRE_ETHER_SHOVEL, "Fire Ether Shovel");
        translationBuilder.add(ModItems.FIRE_ETHER_SWORD, "Fire Ether Sword");
        translationBuilder.add(ModItems.FIRE_ETHER_HOE, "Fire Ether Hoe");

        translationBuilder.add(ModBlocks.ICE_ETHER_BLOCK, "Ice Ether Block");
        translationBuilder.add(ModBlocks.ICE_ETHER_ORE, "Ice Ether Ore");
        translationBuilder.add(ModBlocks.RAW_ICE_ETHER_BLOCK, "Raw Ice Ether Block");
        translationBuilder.add(ModBlocks.ICE_ETHER_STAIRS, "Ice Ether Stairs");
        translationBuilder.add(ModBlocks.ICE_ETHER_SLAB, "Ice Ether Slab");
        translationBuilder.add(ModBlocks.ICE_ETHER_BUTTON, "Ice Ether Button");
        translationBuilder.add(ModBlocks.ICE_ETHER_PRESSURE_PLATE, "Ice Ether Pressure Plate");
        translationBuilder.add(ModBlocks.ICE_ETHER_FENCE, "Ice Ether Fence");
        translationBuilder.add(ModBlocks.ICE_ETHER_FENCE_GATE, "Ice Ether Fence Gate");
        translationBuilder.add(ModBlocks.ICE_ETHER_WALL, "Ice Ether Wall");
        translationBuilder.add(ModBlocks.ICE_ETHER_DOOR, "Ice Ether Door");
        translationBuilder.add(ModBlocks.ICE_ETHER_TRAPDOOR, "Ice Ether Trapdoor");

        translationBuilder.add("itemGroup.retutorial_group", "ReTutorial");

        translationBuilder.add("item.retutorial.prospector.tooltip", "Hold \u00A72SHIFT\u00A7r for more information");
        translationBuilder.add("item.retutorial.prospector.shift_tooltip", "A tool used to prospect for ores.");
    }

}
