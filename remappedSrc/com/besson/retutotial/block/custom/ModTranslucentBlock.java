package com.besson.retutotial.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.Direction;

public interface ModTranslucentBlock {
    default boolean hasCompleteSurface(BlockState state, Direction direction) {
        return state.isOf((Block) this);
    }
}
