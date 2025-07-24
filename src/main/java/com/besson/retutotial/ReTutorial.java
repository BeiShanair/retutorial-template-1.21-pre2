package com.besson.retutotial;

import com.besson.retutotial.block.ModBlockFamilies;
import com.besson.retutotial.block.ModBlocks;
import com.besson.retutotial.block.ModFluids;
import com.besson.retutotial.enchanment.ModEnchantmentEffects;
import com.besson.retutotial.enchanment.ModEnchantments;
import com.besson.retutotial.entity.ModBlockEntities;
import com.besson.retutotial.entity.ModBoats;
import com.besson.retutotial.entity.ModEntities;
import com.besson.retutotial.entity.custom.TigerEntity;
import com.besson.retutotial.item.ModItemGroups;
import com.besson.retutotial.item.ModItems;
import com.besson.retutotial.painting.ModPaintingVariants;
import com.besson.retutotial.particle.ModParticles;
import com.besson.retutotial.recipe.ModRecipes;
import com.besson.retutotial.screen.ModScreenHandlers;
import com.besson.retutotial.sounds.ModSoundEvents;
import com.besson.retutotial.tags.ModBlockTags;
import com.besson.retutotial.tags.ModItemTags;
import com.besson.retutotial.util.ModCustomTrades;
import com.besson.retutotial.util.ModLootTableModifiers;
import com.besson.retutotial.villager.ModVillagers;
import com.besson.retutotial.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.util.Identifier;
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
		ModBoats.registerBoats();

		ModWorldGeneration.generateModWorldGen();
		ModParticles.registerModParticles();
		ModEnchantmentEffects.registerModEnchantments();

		ModPaintingVariants.init();
		// 注册生物实体及其属性
		FabricDefaultAttributeRegistry.register(ModEntities.TIGER, TigerEntity.createTigerAttributes());

		// 原木和去皮原木、木头和去皮木头的注册（原版是硬编码，在AxeItem的STRIPPED_BLOCKS中）
		StrippableBlockRegistry.register(ModBlocks.ICE_ETHER_LOG, ModBlocks.STRIPPED_ICE_ETHER_LOG);
		StrippableBlockRegistry.register(ModBlocks.ICE_ETHER_WOOD, ModBlocks.STRIPPED_ICE_ETHER_WOOD);

		// 注册可燃原木和去皮原木、木头和去皮木头、木板、树叶
		// 原版的注册见FireBlock
		// 当然这里只是被燃烧，而要将其作为燃料要加入到tag中
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.ICE_ETHER_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.ICE_ETHER_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_ICE_ETHER_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_ICE_ETHER_WOOD, 5, 5);

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.ICE_ETHER_PLANKS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.ICE_ETHER_LEAVES, 30, 60);

		CustomPortalBuilder.beginPortal()
				.frameBlock(ModBlocks.ICE_ETHER_BLOCK)
				.lightWithItem(ModItems.ICE_ETHER)
				.destDimID(Identifier.of(ReTutorial.MOD_ID, "test"))
				.tintColor(0xc76a4f)
				.registerPortal();
		
	}
}