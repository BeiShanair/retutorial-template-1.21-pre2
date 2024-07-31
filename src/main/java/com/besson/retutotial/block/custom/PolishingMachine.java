/*
 * 方块实体的一点介绍：
 * （不过我对于方块实体并不是很熟悉，本身不太写方块实体，所以这里的介绍可能不够详细）
 * 方块实体是一种特殊的方块（本质是实体），它可以存储数据，可以有自己的逻辑，可以有自己的GUI
 * 比如说箱子、熔炉、发射器等等都是方块实体
 *
 * 一般来说，方块实体里面写的便是你希望它实现的功能
 *
 * 对于有GUI的方块实体，一般会实现NamedScreenHandlerFactory接口，这个接口是用来打开GUI的
 * 并且你要编写Screen类和ScreenHandler类，用来显示GUI和处理GUI的逻辑
 *
 * 而无GUI的方块实体，比如说营火，他们不需要屏幕和屏幕处理程序
 * （具体还是先看原版的方块实体吧，更高级一点的看各大工业模组，他们拥有大量方块实体）
 */

package com.besson.retutotial.block.custom;

import com.besson.retutotial.entity.ModBlockEntities;
import com.besson.retutotial.entity.custom.PolishingMachineBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PolishingMachine extends BlockWithEntity implements BlockEntityProvider {
    // 1.20.4以后的版本中，基本上都用上了编解码器，这个编解码器是用来序列化和反序列化数据的
    // 最简单的写法就是直接调用PolishingMachine.createCodec(PolishingMachine::new)
    public static final MapCodec<PolishingMachine> CODEC = PolishingMachine.createCodec(PolishingMachine::new);

    // 方块的碰撞箱
    public static final VoxelShape SHAPE = Block.createCuboidShape(0,0,0,16,10,16);
    public PolishingMachine(Settings settings) {
        super(settings);
    }

    // 获取碰撞箱
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    // 获取编解码器
    @Override
    protected MapCodec<PolishingMachine> getCodec() {
        return CODEC;
    }

    // 创建方块实体
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PolishingMachineBlockEntity(pos, state);
    }

    // 获取渲染类型，这里是MODEL，也就是渲染模型
    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    // 方块被移除时的逻辑
    // 也就是说如果你的方块实体里面有东西，那么这个东西会被掉落出来（就像熔炉一样）
    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof PolishingMachineBlockEntity) {
                ItemScatterer.spawn(world, pos, (PolishingMachineBlockEntity)blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    // 方块被右键点击时的逻辑（也就是使用）
    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient()) {
            NamedScreenHandlerFactory screenHandlerFactory = ((PolishingMachineBlockEntity) world.getBlockEntity(pos));

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }

    // 具体介绍见BlockEntityProvider.getTicker
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.POLISHING_MACHINE_BLOCK_ENTITY,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }
}
