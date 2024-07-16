package com.besson.retutotial.block.custom;

import com.besson.retutotial.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class Test2x2 extends Block {
    // 此类实现了一个2x1x2的方块，可以用于实现大型方块
    // 同时各个部分可以有不同的属性，如主方块和从方块

    // PART属性用于区分主方块和从方块
    public static final EnumProperty<TestPart> PART = EnumProperty.of("part", TestPart.class);
    private List<BlockPos> posList;
    private BlockPos masterPos;

    public Test2x2(Settings settings) {
        super(settings);
        // 设置默认状态为从方块
        this.stateManager.getDefaultState().with(PART, TestPart.SLAVE);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        // 添加PART属性
        builder.add(PART);
        super.appendProperties(builder);
    }
    public Vec3i getSize(){
        // 返回方块的大小
        return new Vec3i(2, 1, 2);
    };

    // 重写放置方块的方法
    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (placer == null) {
            return;
        }
        Vec3i size = getSize();
        // 获取方块的位置列表（按照blockbench中的模型形状）
        List<BlockPos> posList = Utils.checkPlayerFace(pos, size, placer);
        // 按照xyz坐标排序，找到主方块
        posList.sort((pos1, pos2) -> {
            int c1 = Integer.compare(pos1.getX(), pos2.getX());
            if (c1 != 0) {
                return c1;
            }
            int c2 = Integer.compare(pos1.getY(), pos2.getY());
            if (c2 != 0) {
                return c1;
            }
            return Integer.compare(pos1.getZ(), pos2.getZ());
        });
        // 设置方块的状态
        // 主方块为列表中的第一个方块，其他方块为从方块
        BlockPos masterPos = posList.getFirst();
        for (BlockPos p : posList) {
            if (p.equals(masterPos)) {
                world.setBlockState(p, this.getDefaultState().with(PART, TestPart.MASTER));
            } else {
                world.setBlockState(p, this.getDefaultState().with(PART, TestPart.SLAVE));
            }
        }
        // 保存主方块的位置和方块列表
        this.posList = posList;
        this.masterPos = masterPos;
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        // 从方块不渲染，主方块渲染
        if (state.get(PART) != TestPart.MASTER) {
            return BlockRenderType.INVISIBLE;
        } else {
            return BlockRenderType.MODEL;
        }
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(0, 0, 0, 16, 16, 16);
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        // 移除任意一部分方块时，移除所有方块
        BlockPos masterPos = this.masterPos;
        if (Objects.equals(masterPos, pos) || state.get(PART) == TestPart.SLAVE) {
            if (masterPos != null) {
                // 利用保存的方块列表移除所有方块
                for (BlockPos p : posList) {
                    world.removeBlock(p, false);
                }
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    // PART属性的枚举类
    public enum TestPart implements StringIdentifiable {
        MASTER("master"),
        SLAVE("slave");

        private final String name;
        TestPart(String name) {
            this.name = name;
        }

        @Override
        public String asString() {
            return this.name;
        }
    }
}
