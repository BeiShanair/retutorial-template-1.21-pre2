package com.besson.retutotial;

import com.besson.retutotial.block.ModBlockFamilies;
import com.besson.retutotial.block.ModBlocks;
import com.besson.retutotial.block.ModFluids;
import com.besson.retutotial.entity.ModBlockEntities;
import com.besson.retutotial.item.ModItemGroups;
import com.besson.retutotial.item.ModItems;
import com.besson.retutotial.recipe.ModRecipes;
import com.besson.retutotial.screen.ModScreenHandlers;
import com.besson.retutotial.sounds.ModSoundEvents;
import com.besson.retutotial.tags.ModBlockTags;
import com.besson.retutotial.tags.ModItemTags;
import com.besson.retutotial.util.ModCustomTrades;
import com.besson.retutotial.util.ModLootTableModifiers;
import com.besson.retutotial.villager.ModVillagers;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReTutorial implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "retutorial";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModItems.registerModItems();
		ModItemGroups.registerModItemGroups();
		ModBlocks.registerModBlocks();
		ModBlockTags.registerModBlockTags();
		ModItemTags.registerModItemTags();
		ModBlockFamilies.registerModBlockFamilies();

		ModLootTableModifiers.modifyLootTables();
		ModCustomTrades.registerModTrades();
		ModVillagers.registerVillagers();
		ModSoundEvents.registerSounds();
		ModFluids.registerModFluids();

		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();
		ModRecipes.registerRecipes();
	}
}