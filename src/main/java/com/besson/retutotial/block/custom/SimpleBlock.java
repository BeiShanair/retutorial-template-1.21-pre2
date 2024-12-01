package com.besson.retutotial.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class SimpleBlock extends Block {
    // 设置方块的面朝向（其实是可以直接继承HorizontalFacingBlock的，不过你得写一个编解码器）
    // 最简单的编解码器：public static final MapCodec<SimpleBlock> CODEC = SimpleBlock.createCodec(SimpleBlock::new);
    // 不想写编解码器的话，就直接继承Block，然后引入FACING属性，然后在构造方法中设置一下

    // 引入FACING属性
//    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final EnumProperty<Direction> FACING = Properties.FACING;

    // 简单设置一下碰撞箱
    // 记得在Blockbench中安装Mod Utils插件，然后将所有的体块放在一个名为“VoxelShapes”的文件夹中，然后才能导出
    // 既然设置了方块的朝向，那么碰撞箱也要根据朝向来设置
    public static final VoxelShape SHAPE_N = Stream.of(
            Block.createCuboidShape(0, 0, 0, 16, 2, 16),
            Block.createCuboidShape(0, 14, 0, 16, 16, 16),
            Block.createCuboidShape(0, 2, 2, 16, 14, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public static final VoxelShape SHAPE_W = Stream.of(
            Block.createCuboidShape(0, 0, 0, 16, 2, 16),
            Block.createCuboidShape(0, 14, 0, 16, 16, 16),
            Block.createCuboidShape(2, 2, 0, 16, 14, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public static final VoxelShape SHAPE_S = Stream.of(
            Block.createCuboidShape(0, 0, 0, 16, 2, 16),
            Block.createCuboidShape(0, 14, 0, 16, 16, 16),
            Block.createCuboidShape(0, 2, 0, 16, 14, 14)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public static final VoxelShape SHAPE_E = Stream.of(
            Block.createCuboidShape(0, 0, 0, 16, 2, 16),
            Block.createCuboidShape(0, 14, 0, 16, 16, 16),
            Block.createCuboidShape(0, 2, 0, 14, 14, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public SimpleBlock(Settings settings) {
        super(settings);
        // 设置方块的默认朝向（一般是北，不过也要看你Blockbench怎么做的）
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH));
    }

    // 重新设置一下碰撞箱
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case EAST -> SHAPE_E;
            case SOUTH -> SHAPE_S;
            case WEST -> SHAPE_W;
            default -> SHAPE_N;
        };
    }

    // 重写旋转方法
    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    // 重写镜像方法
    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }
}
