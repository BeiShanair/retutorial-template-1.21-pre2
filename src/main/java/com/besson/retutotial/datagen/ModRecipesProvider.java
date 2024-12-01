package com.besson.retutotial.datagen;

import com.besson.retutotial.ReTutorial;
import com.besson.retutotial.block.ModBlocks;
import com.besson.retutotial.item.ModItems;
import com.besson.retutotial.tags.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeGenerator;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipesProvider extends FabricRecipeProvider {
    public ModRecipesProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    private static final List<ItemConvertible> ICE_ETHER_LIST = List.of(ModItems.RAW_ICE_ETHER);

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                offerReversibleCompactingRecipes(RecipeCategory.MISC, ModItems.ICE_ETHER,
                        RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICE_ETHER_BLOCK);

                offerSmelting(ICE_ETHER_LIST, RecipeCategory.MISC, ModItems.ICE_ETHER,
                        0.7f, 200, "ice_ether");

                offerBlasting(ICE_ETHER_LIST, RecipeCategory.MISC, ModItems.ICE_ETHER,
                        0.7f, 100, "ice_ether");

//                offerFoodCookingRecipe(exporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING, CampfireCookingRecipe::new,
//                    600, ModItems.RAW_ICE_ETHER, ModItems.ICE_ETHER, 0.35f);

                createShaped(RecipeCategory.MISC, Items.SUGAR, 3)
                        .pattern("###")
                        .input('#', ModItemTags.SUGAR_TAG)
                        .criterion("has_item", conditionsFromItem(Items.BEETROOT))
                        .offerTo(exporter,ReTutorial.MOD_ID + "sugar");

                createShapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICE_ETHER_ORE)
                        .input(ModItems.ICE_ETHER)
                        .input(Blocks.STONE)
                        .criterion("has_item", conditionsFromItem(ModItems.ICE_ETHER))
                        .criterion("has_item", conditionsFromItem(Blocks.STONE))
                        .offerTo(exporter, ReTutorial.MOD_ID + "ice_ether_ore");

                createShapeless(RecipeCategory.MISC, ModItems.ANTHRACITE, 1)
                        .input(Items.COAL)
                        .input(ModItems.FIRE_ETHER)
                        .criterion("has_item", conditionsFromItem(Items.COAL))
                        .offerTo(exporter, ReTutorial.MOD_ID + "anthracite");
            }
        };
    }

    @Override
    public String getName() {
        return "recipes";
    }
}
