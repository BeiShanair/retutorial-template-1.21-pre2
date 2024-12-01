package com.besson.retutotial.compat;

import com.besson.retutotial.block.ModBlocks;
import com.besson.retutotial.recipe.PolishingMachineRecipe;
import com.besson.retutotial.screen.PolishingMachineScreen;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;

public class ModREIClientPlugins implements REIClientPlugin {
    /*
     * REI插件的相关写法在它的Wiki上都有
     * 具体可以参考其Wiki：https://chicken-fetch-ve7.craft.me/qj8mHyTVd7qkOZ
     * 不过值得注意的是，build.gradle中的依赖的写法和官方给出的有所不同，以我目前的教程为主
     * 即 modImplementation "me.shedaniel:RoughlyEnoughItems-fabric:16.0.744" 这条
     * 如果按照官方的写法，将导致无法找到相应的类（我也不知道这里有什么bug）
     */

    // 注册分类，注册工作站方块
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new PolishingMachineCategory());
        registry.addWorkstations(PolishingMachineCategory.POLISHING_MACHINE, EntryStacks.of(ModBlocks.POLISHING_MACHINE));
    }

    // 注册显示
    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(PolishingMachineRecipe.class, PolishingMachineRecipe.Type.INSTANCE,
                PolishingMachineDisplay::new);
    }

    // 注册屏幕
    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen -> new Rectangle(75, 30, 20, 30), PolishingMachineScreen.class,
                PolishingMachineCategory.POLISHING_MACHINE);
    }
}
