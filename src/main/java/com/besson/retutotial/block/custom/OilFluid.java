package com.besson.retutotial.block.custom;

import com.besson.retutotial.block.CustomFluid;
import com.besson.retutotial.block.ModBlocks;
import com.besson.retutotial.block.ModFluids;
import com.besson.retutotial.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;

public abstract class OilFluid extends CustomFluid {
    // 获取静止流体
    @Override
    public Fluid getStill() {
        return ModFluids.OIL;
    }

    // 获取流动流体
    @Override
    public Fluid getFlowing() {
        return ModFluids.FLOWING_OIL;
    }

    // 获取流体的桶
    @Override
    public Item getBucketItem() {
        return ModItems.OIL_BUCKET;
    }

    // 获取流体对应的方块状态
    @Override
    protected BlockState toBlockState(FluidState state) {
        return ModBlocks.OIL.getDefaultState().with(FluidBlock.LEVEL, WaterFluid.getBlockStateLevel(state));
    }

    // 嵌套类，静止流体
    public static class Flowing extends OilFluid {
        // 添加流体属性
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        // 是否是静止流体
        @Override
        public boolean isStill(FluidState state) {
            return false;
        }

        // 获取流体高度
        @Override
        public int getLevel(FluidState state) {
            return state.get(LEVEL);
        }
    }
    // 嵌套类，静止流体
    public static class Still extends OilFluid {

        // 是否是静止流体
        @Override
        public boolean isStill(FluidState state) {
            return true;
        }

        // 获取流体高度（MC中采用1-8的比例指定流体高度属性）
        @Override
        public int getLevel(FluidState state) {
            return 8;
        }
    }
}
