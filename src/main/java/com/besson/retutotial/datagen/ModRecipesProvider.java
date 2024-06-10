package com.besson.retutotial.datagen;

import com.besson.retutotial.ReTutorial;
import com.besson.retutotial.block.ModBlocks;
import com.besson.retutotial.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.BlastingRecipe;
import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipesProvider extends FabricRecipeProvider {
    public ModRecipesProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    private static final List<ItemConvertible> ICE_ETHER_LIST = List.of(ModItems.RAW_ICE_ETHER);

    @Override
    public void generate(RecipeExporter exporter) {
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.ICE_ETHER,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICE_ETHER_BLOCK);

        offerSmelting(exporter, ICE_ETHER_LIST, RecipeCategory.MISC, ModItems.ICE_ETHER,
                0.7f, 200, "ice_ether");

        offerBlasting(exporter, ICE_ETHER_LIST, RecipeCategory.MISC, ModItems.ICE_ETHER,
                0.7f, 100, "ice_ether");

        offerCampfireCooking(exporter, ICE_ETHER_LIST, RecipeCategory.MISC, ModItems.ICE_ETHER,
                0.35f, 600, "ice_ether");

//        // 营火配方的另外一种生成方法，更简单一点
//        RecipeProvider.offerFoodCookingRecipe(exporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING, CampfireCookingRecipe::new,
//                600, ModItems.RAW_ICE_ETHER, ModItems.ICE_ETHER, 0.35f);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.SUGAR, 3)
                .pattern("###")
                .input('#', Items.BEETROOT)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Items.BEETROOT))
                .offerTo(exporter, Identifier.of(ReTutorial.MOD_ID, "beetroot_to_sugar"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICE_ETHER_ORE)
                .input(ModItems.ICE_ETHER)
                .input(Blocks.STONE)
                .criterion("has_item", RecipeProvider.conditionsFromItem(ModItems.ICE_ETHER))
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.STONE))
                .offerTo(exporter, Identifier.of(ReTutorial.MOD_ID, "ice_ether_ore"));
    }
    public static void offerCampfireCooking(RecipeExporter exporter, List<ItemConvertible> inputs, RecipeCategory category, ItemConvertible output, float experience, int cookingTime, String group) {
        RecipeProvider.offerMultipleOptions(exporter, RecipeSerializer.CAMPFIRE_COOKING, CampfireCookingRecipe::new,
                inputs, category, output, experience, cookingTime, group, "_from_campfire_cooking");
    }
}
