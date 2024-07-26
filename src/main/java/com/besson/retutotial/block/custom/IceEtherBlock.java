package com.besson.retutotial.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.Direction;

public class IceEtherBlock extends Block implements ModTranslucentBlock {
    public IceEtherBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        if (hasCompleteSurface(stateFrom, direction.getOpposite())) {
            return true;
        }
        return super.isSideInvisible(state, stateFrom, direction);
    }
}
