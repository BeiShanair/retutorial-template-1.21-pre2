package com.besson.retutotial.world;

import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class ModOrePlacements {
    // ModOrePlacements类定义了一些静态方法，用于生成Minecraft中的矿石放置修饰符列表
    // （其实就是OrePlacedFeatures类里的方法）
    // 每个方法都返回一个List<PlacementModifier>对象
    public static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        // SquarePlacementModifier.of()和BiomePlacementModifier.of()，它们分别用于定义方形放置和生物群系放置修饰符
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }
    // 这个方法用于根据给定的计数生成放置修饰符列表
    public static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }
    // 这个方法用于根据给定的稀有度生成放置修饰符列表
    public static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
    }
}
