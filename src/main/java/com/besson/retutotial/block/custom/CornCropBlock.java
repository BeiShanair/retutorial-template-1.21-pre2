package com.besson.retutotial.block.custom;

import com.besson.retutotial.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class CornCropBlock extends CropBlock {
    // 设置第一阶段和第二阶段的成熟阶段
    public static final int FIRST_STAGE_AGE = 7;
    public static final int SECOND_STAGE_AGE = 1;
    // 设置作物的成熟阶段属性，由于原版最大成熟阶段为7，所以我们需要自行设置
    public static final IntProperty AGE = IntProperty.of("age",0,8);
    // 作物外轮廓线同理，我们需要自行设置
    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 12.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 14.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0)};
    public CornCropBlock(Settings settings) {
        super(settings);
    }

    // 重写获取作物外轮廓线的方法
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AGE_TO_SHAPE[this.getAge(state)];
    }

    // 重写添加属性的方法
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    // 重写获取最大成熟阶段的方法
    @Override
    public int getMaxAge() {
        return FIRST_STAGE_AGE + SECOND_STAGE_AGE;
    }

    // 重写获取生长阶段属性的方法
    @Override
    protected IntProperty getAgeProperty() {
        return AGE;
    }

    // 重写获取种子物品的方法
    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.CORN_SEEDS;
    }

    // 重写判断是否可以放置的方法，可以参考仙人掌、甘蔗等作物的放置方法
    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState block = world.getBlockState(pos.down());
        return super.canPlaceAt(state, world, pos) ||
                block.isOf(this) && block.get(AGE) == 7;
    }

    // 重写生长方法
    @Override
    public void applyGrowth(World world, BlockPos pos, BlockState state) {
        int nextAge = this.getAge(state) + this.getGrowthAmount(world);
        int maxAge = this.getMaxAge();
        if (nextAge > maxAge){
            nextAge = maxAge;
        }
        BlockState upState = world.getBlockState(pos.up());
        if (this.getAge(state) == FIRST_STAGE_AGE && upState.isOf(Blocks.AIR)) {
            world.setBlockState(pos.up(), this.withAge(nextAge), Block.NOTIFY_LISTENERS);
        } else {
            world.setBlockState(pos, this.withAge(nextAge - 1), Block.NOTIFY_LISTENERS);
        }
    }

    // 重写随机生长方法
    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int age = this.getAge(state);
        float f = getAvailableMoisture(this, world, pos);
        if (world.getBaseLightLevel(pos, 0) >= 9 && random.nextInt((int) (25.0F / f) + 1) == 0) {
            BlockState blockState = world.getBlockState(pos.up());
            if (age == FIRST_STAGE_AGE && blockState.isOf(Blocks.AIR)) {
                world.setBlockState(pos.up(), this.withAge(age + 1), Block.NOTIFY_LISTENERS);
            } else {
                world.setBlockState(pos, this.withAge(age + 1), Block.NOTIFY_LISTENERS);
            }
        }
    }
}
