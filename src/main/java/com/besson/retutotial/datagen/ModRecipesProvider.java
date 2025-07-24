package com.besson.retutotial.datagen;

import com.besson.retutotial.block.ModBlocks;
import com.besson.retutotial.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeGenerator;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipesProvider extends FabricRecipeProvider {
    public ModRecipesProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }
    private static final List<ItemConvertible> ICE_ETHER_LIST = List.of(ModItems.RAW_ICE_ETHER);

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                RegistryWrapper.Impl<Item> itemLookup = registries.getOrThrow(RegistryKeys.ITEM);

                offerReversibleCompactingRecipes(RecipeCategory.MISC, ModItems.ICE_ETHER,
                        RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICE_ETHER_BLOCK);

                offerSmelting(ICE_ETHER_LIST, RecipeCategory.MISC, ModItems.ICE_ETHER,
                        0.7f, 200, "ice_ether");

                offerBlasting(ICE_ETHER_LIST, RecipeCategory.MISC, ModItems.ICE_ETHER,
                        0.7f, 100, "ice_ether");

                // 营火配方的另外一种生成方法，更简单一点
                offerFoodCookingRecipe("campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING, CampfireCookingRecipe::new,
                        600, ModItems.RAW_ICE_ETHER, ModItems.ICE_ETHER, 0.35f);

                ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.MISC, Items.SUGAR, 3)
                        .pattern("###")
                        .input('#', Items.BEETROOT)
                        .criterion("has_item", conditionsFromItem(Items.BEETROOT))
                        .offerTo(recipeExporter);

                ShapelessRecipeJsonBuilder.create(itemLookup, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICE_ETHER_ORE)
                        .input(ModItems.ICE_ETHER)
                        .input(Blocks.STONE)
                        .criterion("has_item", conditionsFromItem(ModItems.ICE_ETHER))
                        .criterion("has_item", conditionsFromItem(Blocks.STONE))
                        .offerTo(recipeExporter);
            }
        };
    }

    @Override
    public String getName() {
        return "Recipe Gen";
    }
}
