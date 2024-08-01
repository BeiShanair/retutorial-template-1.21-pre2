package com.besson.retutotial.recipe;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.List;

public class PolishingMachineRecipe implements Recipe<SingleStackRecipeInput> {
    private final ItemStack output;
    private final List<Ingredient> recipeItems;

    public PolishingMachineRecipe(List<Ingredient> recipeItems, ItemStack output) {
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(SingleStackRecipeInput input, World world) {
        if (world.isClient()) {
            return false;
        }
        return recipeItems.get(0).test(input.item());
    }

    @Override
    public ItemStack craft(SingleStackRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<PolishingMachineRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "polishing_machine";
    }

    public static class Serializer implements RecipeSerializer<PolishingMachineRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "polishing_machine";

//        public static final MapCodec<PolishingMachineRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
//                (Ingredient.DISALLOW_EMPTY_CODEC.listOf().fieldOf("ingredient")).forGetter(r -> r.getIngredients()),
//                (ItemStack.VALIDATED_CODEC.fieldOf("output")).forGetter(r -> r.output))
//                .apply(instance, PolishingMachineRecipe::new));

        public static final MapCodec<PolishingMachineRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                (Ingredient.DISALLOW_EMPTY_CODEC.listOf().fieldOf("ingredients")).flatXmap(ingredients -> {
                    Ingredient[] ingredients1 = (Ingredient[]) ingredients.stream().filter(ingredient -> !ingredient.isEmpty()).toArray(Ingredient[]::new);
                    if (ingredients1.length == 0) {
                        return DataResult.error(() -> "No ingredients");
                    }
                    if (ingredients1.length > 9) {
                        return DataResult.error(() -> "Too many ingredients");
                    }
                    return DataResult.success(DefaultedList.copyOf(Ingredient.EMPTY, ingredients1));
                }, DataResult::success).forGetter(r -> r.getIngredients()),
                (ItemStack.VALIDATED_CODEC.fieldOf("output")).forGetter(r -> r.output)).apply(instance, PolishingMachineRecipe::new));

        public static final PacketCodec<RegistryByteBuf, PolishingMachineRecipe> PACKET_CODEC = PacketCodec.ofStatic(
                Serializer::write, Serializer::read);

        private static PolishingMachineRecipe read(RegistryByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.PACKET_CODEC.decode(buf));
            }
            ItemStack output = ItemStack.PACKET_CODEC.decode(buf);
            return new PolishingMachineRecipe(inputs, output);
        }

        private static void write(RegistryByteBuf buf, PolishingMachineRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ingredient : recipe.getIngredients()) {
                Ingredient.PACKET_CODEC.encode(buf, ingredient);
            }
            ItemStack.PACKET_CODEC.encode(buf, recipe.getResult(null));

        }

        @Override
        public MapCodec<PolishingMachineRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, PolishingMachineRecipe> packetCodec() {
            return PACKET_CODEC;
        }

    }
}
