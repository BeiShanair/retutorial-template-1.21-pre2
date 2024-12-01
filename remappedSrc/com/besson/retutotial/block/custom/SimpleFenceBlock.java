package com.besson.retutotial.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class SimpleFenceBlock extends Block {
    // 这里我们来实现栅栏类的方块（当然，这里只有左右两侧的栅栏，如果你想要四面都有栅栏，那么你需要查阅原版栅栏相关的代码）
    // 然后此处的碰撞箱也省略了
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    // 设置枚举的方块状态
    public static final EnumProperty<Type> TYPE = EnumProperty.of("type", Type.class);
    public SimpleFenceBlock(Settings settings) {
        super(settings);
        // 设置默认方块属性
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(TYPE, Type.SINGLE));
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING,ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING,rotation.rotate(state.get(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(TYPE);
    }

    // 重写getStateForNeighborUpdate方法，用于更新方块状态
    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return getRelatedBlockState(state, world, pos, state.get(FACING));
    }

    // 编写一个方法来获取相关方块的状态
    private BlockState getRelatedBlockState(BlockState state, WorldAccess world, BlockPos pos, Direction direction) {
        // 判断左右两侧是否有相关方块
        boolean left = isRelatedBlock(world, pos, direction.rotateYCounterclockwise(), direction) ||
                isRelatedBlock(world, pos, direction.rotateYCounterclockwise(), direction.rotateYCounterclockwise());
        boolean right = isRelatedBlock(world, pos, direction.rotateYClockwise(), direction) ||
                isRelatedBlock(world, pos, direction.rotateYClockwise(), direction.rotateYClockwise());

        if (left && right) {
            return state.with(TYPE, Type.MIDDLE);
        } else if (right) {
            return state.with(TYPE, Type.RIGHT);
        } else if (left) {
            return state.with(TYPE, Type.LEFT);
        } else {
            return state.with(TYPE, Type.SINGLE);
        }
    }

    // 编写一个方法来判断是否有相关方块
    private boolean isRelatedBlock(WorldAccess world, BlockPos pos, Direction direction, Direction direction1) {
        BlockState state = world.getBlockState(pos.offset(direction));
        if (state.getBlock() == this) {
            Direction blockDirection = state.get(FACING);
            return blockDirection.equals(direction1);
        }
        return false;
    }

    // 编写一个嵌套枚举类来设置方块的属性
    public enum Type implements StringIdentifiable {
        SINGLE("single"),
        LEFT("left"),
        RIGHT("right"),
        MIDDLE("middle");

        private final String id;
        Type(String id) {
            this.id = id;
        }
        @Override
        public String asString() {
            return id;
        }
    }
}
