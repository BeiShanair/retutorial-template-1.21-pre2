package com.besson.retutotial.world;

import com.besson.retutotial.ReTutorial;
import com.besson.retutotial.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

public class ModConfiguredFeatures {
    // 这个类用于注册配置特征
    // 树的配置特征可见TreeConfiguredFeatures

    //注册树的key
    public static final RegistryKey<ConfiguredFeature<?, ?>> ICE_ETHER_TREE_KEY = of("ice_ether_tree");
    // 注册花的key
    public static final RegistryKey<ConfiguredFeature<?, ?>> SIMPLE_FLOWER_KEY = of("simple_flower");
    // bootstrap方法，用于数据生成
    public static void bootstrap(Registerable<ConfiguredFeature<?,?>> featureRegisterable) {
        // 注册树的配置特征（见TreeConfiguredFeatures）
        // StraightTrunkPlacer是树干的生成器，参数分别是树干最小高度、树干第一个随机增加高度、树干第二个随机增加高度
        // BlobFoliagePlacer是树叶的生成器，参数分别是树叶半径、树叶偏移、树叶高度
        // TwoLayersFeatureSize是树木整体的生成特征，参数分别是高度限制、较低层半径、较高层半径
        register(featureRegisterable, ICE_ETHER_TREE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.ICE_ETHER_LOG),
                new StraightTrunkPlacer(4, 3, 2),
                BlockStateProvider.of(ModBlocks.ICE_ETHER_LEAVES),
                new BlobFoliagePlacer(ConstantIntProvider.create(4), ConstantIntProvider.create(2), 2),
                new TwoLayersFeatureSize(1, 0, 2)
                ).build());
        // 注册花的配置特征
        // RandomPatchFeatureConfig是随机生成特征的生成器，参数分别是每个区块生成次数、每次生成的最大数量、每次生成的最小数量、生成的特征
        register(featureRegisterable, SIMPLE_FLOWER_KEY, Feature.FLOWER, new RandomPatchFeatureConfig(32, 6, 2,
                PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.SIMPLE_FLOWER)))));
    }

    // 注册方法，记得改id
    public static RegistryKey<ConfiguredFeature<?, ?>> of(String id) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(ReTutorial.MOD_ID, id));
    }
    // 注册方法
    public static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> registerable, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        registerable.register(key, new ConfiguredFeature<FC, F>(feature, config));
    }
}
