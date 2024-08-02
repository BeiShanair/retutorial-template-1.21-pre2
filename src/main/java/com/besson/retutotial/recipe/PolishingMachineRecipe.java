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
    // 编写自己的配方类，实现Recipe接口
    // 和1.20不一样，实现的接口也不一样，所用的泛型如果有需要，你可以自己写一个
    // 这里的SingleStackRecipeInput是单槽的配方输入

    // 一个ItemStack，用于存放输出
    private final ItemStack output;

    // 一个List，用于存放输入
    private final List<Ingredient> recipeItems;

    // 构造方法，用于传入输入和输出
    public PolishingMachineRecipe(List<Ingredient> recipeItems, ItemStack output) {
        this.output = output;
        this.recipeItems = recipeItems;
    }

    // 重写getIngredients方法，返回输入（这个也是为了方便REI的相关教程的）
    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.ofSize(this.recipeItems.size());
        list.addAll(recipeItems);
        return list;
    }

    // 重写matches方法，用于判断输入是否匹配
    @Override
    public boolean matches(SingleStackRecipeInput input, World world) {
        if (world.isClient()) {
            return false;
        }
        return recipeItems.get(0).test(input.item());
    }

    // 重写craft方法，用于合成，返回输出
    @Override
    public ItemStack craft(SingleStackRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    // 返回配方是否适合给定的网格大小
    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    // 返回配方的结果
    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return output;
    }

    // 返回配方的序列化器（编解码器）
    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    // 返回配方的类型
    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    // 配方的类型
    public static class Type implements RecipeType<PolishingMachineRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "polishing_machine";
    }

    // 配方的序列化器
    public static class Serializer implements RecipeSerializer<PolishingMachineRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "polishing_machine";

//        public static final MapCodec<PolishingMachineRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
//                (Ingredient.DISALLOW_EMPTY_CODEC.listOf().fieldOf("ingredient")).forGetter(r -> r.getIngredients()),
//                (ItemStack.VALIDATED_CODEC.fieldOf("output")).forGetter(r -> r.output))
//                .apply(instance, PolishingMachineRecipe::new));

        // 编解码器
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

        // 用于网络传输的编解码器
        public static final PacketCodec<RegistryByteBuf, PolishingMachineRecipe> PACKET_CODEC = PacketCodec.ofStatic(
                Serializer::write, Serializer::read);

        // 读取方法
        private static PolishingMachineRecipe read(RegistryByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.PACKET_CODEC.decode(buf));
            }
            ItemStack output = ItemStack.PACKET_CODEC.decode(buf);
            return new PolishingMachineRecipe(inputs, output);
        }

        // 写入方法
        private static void write(RegistryByteBuf buf, PolishingMachineRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ingredient : recipe.getIngredients()) {
                Ingredient.PACKET_CODEC.encode(buf, ingredient);
            }
            ItemStack.PACKET_CODEC.encode(buf, recipe.getResult(null));

        }

        // 返回编解码器
        @Override
        public MapCodec<PolishingMachineRecipe> codec() {
            return CODEC;
        }

        // 返回用于网络传输的编解码器
        @Override
        public PacketCodec<RegistryByteBuf, PolishingMachineRecipe> packetCodec() {
            return PACKET_CODEC;
        }

    }
}
