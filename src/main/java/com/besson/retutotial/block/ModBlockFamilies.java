package com.besson.retutotial.block;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.registry.Registries;

import java.util.Map;
import java.util.stream.Stream;

public class ModBlockFamilies {
    // 参考原版的写法，使用BlockFamily，用于数据生成
    private static final Map<Block, BlockFamily> BASE_BLOCKS_TO_FAMILIES = Maps.newHashMap();

    // 在这里我们能够添加所有和ICE_ETHER相关的方块（同一个族的）
    public static final BlockFamily ICE_ETHER = register(ModBlocks.ICE_ETHER_BLOCK)
            .stairs(ModBlocks.ICE_ETHER_STAIRS)
            .slab(ModBlocks.ICE_ETHER_SLAB)
            .button(ModBlocks.ICE_ETHER_BUTTON)
            .pressurePlate(ModBlocks.ICE_ETHER_PRESSURE_PLATE)
            .fence(ModBlocks.ICE_ETHER_FENCE)
            .fenceGate(ModBlocks.ICE_ETHER_FENCE_GATE)
            .wall(ModBlocks.ICE_ETHER_WALL)
            .door(ModBlocks.ICE_ETHER_DOOR)
            .trapdoor(ModBlocks.ICE_ETHER_TRAPDOOR)
            .build();
    public static BlockFamily.Builder register(Block baseBlock) {
        BlockFamily.Builder builder = new BlockFamily.Builder(baseBlock);
        BlockFamily blockFamily = BASE_BLOCKS_TO_FAMILIES.put(baseBlock, builder.build());
        if (blockFamily != null) {
            throw new IllegalStateException("Duplicate family definition for " + Registries.BLOCK.getId(baseBlock));
        }
        return builder;
    }

    public static Stream<BlockFamily> getFamilies() {
        return BASE_BLOCKS_TO_FAMILIES.values().stream();
    }
    public static void registerModBlockFamilies() {

    }
}
