package com.besson.retutotial.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public abstract class CustomFluid extends FlowableFluid {
    // 如果你的模组中有较多相似的流体，可以将流体的属性抽象到这里，然后让你的流体继承这个类
    // 这样可以减少重复代码

    // 给定流体是否是相同的流体
    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == getFlowing() || fluid == getStill();
    }

    // 是否是无限流体
    // 参考水是无限流体，岩浆不是无限流体
    @Override
    protected boolean isInfinite(World world) {
        return false;
    }

    // 当方块被破坏时，掉落物品
    // 即当流体替换方块时，掉落方块的物品
    @Override
    protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
        BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
        Block.dropStacks(state, world, pos, blockEntity);
    }

    // 流体是否可以替换方块
    // 在原版中，岩浆高于一定的高度时，会将水替换掉？看lava的canBeReplacedWith，我不太能理解
    @Override
    protected boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {
        return false;
    }

    // 流体流动速度
    @Override
    protected int getFlowSpeed(WorldView world) {
        return 4;
    }

    // 流体每个区块可以降低多少？（抽象
    @Override
    protected int getLevelDecreasePerBlock(WorldView world) {
        return 1;
    }

    // 获取tick速率
    @Override
    public int getTickRate(WorldView world) {
        return 5;
    }

    // 获取爆炸抗性
    @Override
    protected float getBlastResistance() {
        return 100.0F;
    }
}
