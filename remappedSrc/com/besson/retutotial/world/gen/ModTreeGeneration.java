package com.besson.retutotial.world.gen;

import com.besson.retutotial.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class ModTreeGeneration {
    public static void registerTrees(){
        // 树的世界生成，借助fabric的api来写
        // 通过BiomeModifications.addFeature方法来添加树的世界生成
        // BiomeSelectors.includeByKey方法来选择要添加世界生成的生物群系（可以选择多个）
        // GenerationStep.Feature.VEGETAL_DECORATION表示生成的方式（按植被生成）
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.PLAINS, BiomeKeys.FOREST),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.ICE_ETHER_TREE_PLACED_KEY);
    }
}
