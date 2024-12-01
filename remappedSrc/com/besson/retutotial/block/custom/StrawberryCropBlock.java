package com.besson.retutotial.block.custom;

import com.besson.retutotial.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class StrawberryCropBlock extends CropBlock {
    // 定义作物的最大生长阶段
    public static final int MAX_AGE = 5;
    // 定义作物的生长阶段属性，可以直接使用Properties.AGE_5
    public static final IntProperty AGE = Properties.AGE_5;
    public StrawberryCropBlock(Settings settings) {
        super(settings);
    }

    // 重写获取最大生长阶段的方法
    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    // 重写获取生长阶段属性的方法
    protected IntProperty getAgeProperty() {
        return AGE;
    }
    @Override
    public int getAge(BlockState state) {
        return state.get(this.getAgeProperty());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    // 重写获取作物种子的方法
    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.STRAWBERRY_SEEDS;
    }

    // 重写作物可以种植的条件（参考PlantBlock）
    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(BlockTags.DIRT);
    }
}
