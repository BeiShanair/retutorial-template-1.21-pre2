package com.besson.retutotial.datagen;

import com.besson.retutotial.block.ModBlocks;
import com.besson.retutotial.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModENLangProvider extends FabricLanguageProvider {
    public ModENLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        // languageCode默认是en_us，也可以是其他语言，比如zh_cn
        super(dataOutput, "en_us", registryLookup);

    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.ICE_ETHER, "Ice Ether");
        translationBuilder.add(ModItems.RAW_ICE_ETHER, "Raw Ice Ether");
        translationBuilder.add(ModItems.STRAWBERRY, "Strawberry");
        translationBuilder.add(ModItems.STRAWBERRY_SEEDS, "Strawberry Seeds");
        translationBuilder.add(ModItems.CHEESE, "Cheese");
        translationBuilder.add(ModItems.CORN, "Corn");
        translationBuilder.add(ModItems.CORN_SEEDS, "Corn Seeds");
        translationBuilder.add(ModItems.ANTHRACITE, "Anthracite");
        translationBuilder.add(ModItems.PROSPECTOR, "Prospector");
        translationBuilder.add(ModItems.PLATE, "Plate");
        translationBuilder.add(ModItems.FIRE_ETHER, "Fire Ether");
        translationBuilder.add(ModItems.ICE_ETHER_HELMET, "Ice Ether Helmet");
        translationBuilder.add(ModItems.ICE_ETHER_CHESTPLATE, "Ice Ether Chestplate");
        translationBuilder.add(ModItems.ICE_ETHER_LEGGINGS, "Ice Ether Leggings");
        translationBuilder.add(ModItems.ICE_ETHER_BOOTS, "Ice Ether Boots");
        translationBuilder.add(ModItems.HAT, "Hat");
        translationBuilder.add(ModItems.SIMPLE_ITEM, "Simple Item");

        translationBuilder.add(ModItems.FIRE_ETHER_AXE, "Fire Ether Axe");
        translationBuilder.add(ModItems.FIRE_ETHER_PICKAXE, "Fire Ether Pickaxe");
        translationBuilder.add(ModItems.FIRE_ETHER_SHOVEL, "Fire Ether Shovel");
        translationBuilder.add(ModItems.FIRE_ETHER_SWORD, "Fire Ether Sword");
        translationBuilder.add(ModItems.FIRE_ETHER_HOE, "Fire Ether Hoe");

        translationBuilder.add(ModItems.OIL_BUCKET, "Oil Bucket");
        translationBuilder.add(ModItems.ICE_ETHER_HORSE_ARMOR, "Ice Ether Horse Armor");

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
        translationBuilder.add(ModBlocks.BOX, "Box");
        translationBuilder.add(ModBlocks.SIMPLE_BLOCK, "Simple Block");
        translationBuilder.add(ModBlocks.SIMPLE_FENCE, "Simple Fence");
        translationBuilder.add(ModBlocks.ICE_ETHER_LOG, "Ice Ether Log");
        translationBuilder.add(ModBlocks.ICE_ETHER_WOOD, "Ice Ether Wood");
        translationBuilder.add(ModBlocks.STRIPPED_ICE_ETHER_LOG, "Stripped Ice Ether Log");
        translationBuilder.add(ModBlocks.STRIPPED_ICE_ETHER_WOOD, "Stripped Ice Ether Wood");
        translationBuilder.add(ModBlocks.ICE_ETHER_PLANKS, "Ice Ether Planks");
        translationBuilder.add(ModBlocks.ICE_ETHER_LEAVES, "Ice Ether Leaves");
        translationBuilder.add(ModBlocks.ICE_ETHER_TREE_SAPLING, "Ice Ether Tree Sapling");
        translationBuilder.add(ModBlocks.SIMPLE_FLOWER, "Simple Flower");

        translationBuilder.add("itemGroup.retutorial_group", "ReTutorial");

        translationBuilder.add("item.retutorial.prospector.tooltip", "Hold \u00A72SHIFT\u00A7r for more information");
        translationBuilder.add("item.retutorial.prospector.shift_tooltip", "A tool used to prospect for ores.");

        translationBuilder.add("sounds.retutorial.prospector_found_ore", "Prospector Found Ore");
        translationBuilder.add("sounds.retutorial.block_break", "Block Break");
        translationBuilder.add("sounds.retutorial.block_step", "Block Step");
        translationBuilder.add("sounds.retutorial.block_place", "Block Place");
        translationBuilder.add("sounds.retutorial.block_hit", "Block Hit");
        translationBuilder.add("sounds.retutorial.block_fall", "Block Fall");

        translationBuilder.add(ModItems.TEST_MUSIC_DISC, "Test Music Disc");
        translationBuilder.add("jukebox_song.retutorial.test", "高橋李依 - 小さな恋のうた");
        translationBuilder.add("container.retutorial.polishing_machine", "Polishing Machine");
    }

}
