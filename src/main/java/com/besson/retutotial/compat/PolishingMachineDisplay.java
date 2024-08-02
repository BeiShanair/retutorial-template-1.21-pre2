package com.besson.retutotial.compat;

import com.besson.retutotial.recipe.PolishingMachineRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.recipe.RecipeEntry;

import java.util.Collections;
import java.util.List;

public class PolishingMachineDisplay extends BasicDisplay {
    // 编写自己的显示类，继承BasicDisplay

    // 构造方法，用于传入输入和输出
    public PolishingMachineDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
    }

    // 这个构造方法是用于注册的
    public PolishingMachineDisplay(RecipeEntry<PolishingMachineRecipe> recipe) {
        super(getInputList(recipe.value()), List.of(EntryIngredient.of(EntryStacks.of(recipe.value().getResult(null)))));
    }

    // 自定义方法，用于获取输入（读取json文件的输入）
    private static List<EntryIngredient> getInputList(PolishingMachineRecipe recipe){
        if (recipe.getIngredients().isEmpty()) {
            return Collections.emptyList();
        }
        return EntryIngredients.ofIngredients(recipe.getIngredients());
    }

    // 获取分类标识
    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return PolishingMachineCategory.POLISHING_MACHINE;
    }
}
