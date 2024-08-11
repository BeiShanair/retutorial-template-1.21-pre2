package com.besson.retutotial.world;

import com.besson.retutotial.ReTutorial;
import com.besson.retutotial.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class ModPlacedFeatures {
    // 这个类用于注册放置特征
    // 树的放置特征可见TreePlacedFeatures
    // 但我们写的放置特征是属于植被的，所以更准确地说应该看VegetationPlacedFeatures

    // 注册树的放置的key
    public static final RegistryKey<PlacedFeature> ICE_ETHER_TREE_PLACED_KEY = of("ice_ether_tree_placed");
    // 注册花的放置的key
    public static final RegistryKey<PlacedFeature> SIMPLE_FLOWER_PLACED_KEY = of("simple_flower_placed");

    // 注册矿石的放置的key（三个维度）
    public static final RegistryKey<PlacedFeature> ICE_ETHER_ORE_PLACED_KEY = of("ice_ether_ore_placed");
    public static final RegistryKey<PlacedFeature> NETHER_ICE_ETHER_ORE_PLACED_KEY = of("nether_ice_ether_ore_placed");
    public static final RegistryKey<PlacedFeature> END_ICE_ETHER_ORE_PLACED_KEY = of("end_ice_ether_ore_placed");

    // boostrap方法，用于数据生成
    public static void boostrap(Registerable<PlacedFeature> featureRegisterable) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        // PlacedFeatures.createCountExtraModifier是一个放置特征的生成器，参数分别是最小生成数量、额外生成概率、额外生成数量
        register(featureRegisterable, ICE_ETHER_TREE_PLACED_KEY, registryEntryLookup.getOrThrow(ModConfiguredFeatures.ICE_ETHER_TREE_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        PlacedFeatures.createCountExtraModifier(2, 0.1f, 2),
                        ModBlocks.ICE_ETHER_TREE_SAPLING));
        // RarityFilterPlacementModifier是一个放置特征的生成器，参数是代表多少个区块生成一次
        // PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP按照地形生成
        register(featureRegisterable, SIMPLE_FLOWER_PLACED_KEY, registryEntryLookup.getOrThrow(ModConfiguredFeatures.SIMPLE_FLOWER_KEY),
                RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        // 为三个维度的矿石注册放置特征
        // modifiersWithCount的参数分别是每个区块生成的数量、高度范围
        // HeightRangePlacementModifier.uniform参数是高度范围，另有trapezoid方法（梯形生成法）
        register(featureRegisterable, ICE_ETHER_ORE_PLACED_KEY, registryEntryLookup.getOrThrow(ModConfiguredFeatures.ICE_ETHER_ORE_KEY),
                ModOrePlacements.modifiersWithCount(12,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(80))));
        register(featureRegisterable, NETHER_ICE_ETHER_ORE_PLACED_KEY, registryEntryLookup.getOrThrow(ModConfiguredFeatures.NETHER_ICE_ETHER_ORE_KEY),
                ModOrePlacements.modifiersWithCount(12,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(80))));
        register(featureRegisterable, END_ICE_ETHER_ORE_PLACED_KEY, registryEntryLookup.getOrThrow(ModConfiguredFeatures.END_ICE_ETHER_ORE_KEY),
                ModOrePlacements.modifiersWithCount(12,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(80))));
    }

    // 注册方法
    public static RegistryKey<PlacedFeature> of(String id) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(ReTutorial.MOD_ID, id));
    }
    public static void register(Registerable<PlacedFeature> featureRegisterable, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
        register(featureRegisterable, key, feature, List.of(modifiers));
    }
    public static void register(Registerable<PlacedFeature> featureRegisterable, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> modifiers) {
        featureRegisterable.register(key, new PlacedFeature(feature, List.copyOf(modifiers)));
    }
}
