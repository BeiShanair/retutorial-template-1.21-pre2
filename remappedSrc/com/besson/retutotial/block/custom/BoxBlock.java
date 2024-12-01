package com.besson.retutotial.block.custom;

import com.besson.retutotial.entity.ModBlockEntities;
import com.besson.retutotial.entity.custom.BoxBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class BoxBlock extends AbstractChestBlock<BoxBlockEntity> {
    // 仿写原版的箱子方块，实现箱子的功能
    // 写一个Facing属性（具体的使用方法在后续的教程中再讲，这里其实没什么用）
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    // CODEC是一个MapCodec，用于序列化和反序列化方块
    public static final MapCodec<BoxBlock> CODEC = BoxBlock.createCodec(settings -> new BoxBlock((AbstractBlock.Settings) settings, () -> ModBlockEntities.BOX));
    public BoxBlock(Settings settings, Supplier<BlockEntityType<? extends BoxBlockEntity>> blockEntityTypeSupplier) {
        super(settings, blockEntityTypeSupplier);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    // 需要重写的第一个方法
    @Override
    protected MapCodec<? extends AbstractChestBlock<BoxBlockEntity>> getCodec() {
        return CODEC;
    }

    // 需要重写的第二个方法，而箱子它有double的属性，但我们这个只是一个方块，所以返回null
    @Override
    public DoubleBlockProperties.PropertySource<? extends ChestBlockEntity> getBlockEntitySource(BlockState state, World world, BlockPos pos, boolean ignoreBlocked) {
        return null;
    }

    // 需要重写的第三个方法，返回一个箱子方块实体
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BoxBlockEntity(pos, state);
    }

    // 重写getRenderType方法，返回MODEL，这个也是方块实体的渲染方法
    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    // 重写onUse方法，用于打开箱子，弹出箱子界面
    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient()){
            NamedScreenHandlerFactory namedScreenHandlerFactory = this.createScreenHandlerFactory(state, world, pos);
            if (namedScreenHandlerFactory != null){
                player.openHandledScreen(namedScreenHandlerFactory);
                return ActionResult.CONSUME;
            }
        }
        return ActionResult.SUCCESS;
    }
}
