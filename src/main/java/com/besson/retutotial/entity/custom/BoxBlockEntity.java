package com.besson.retutotial.entity.custom;

import com.besson.retutotial.entity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class BoxBlockEntity extends LootableContainerBlockEntity {
    // 仿写原版的箱子方块实体
    // inventory是箱子的物品栏, 27个格子
    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(27, ItemStack.EMPTY);
    // 这个是super的构造方法
    protected BoxBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }
    // 这个是我们自己的构造方法，用于实例化这个方块实体，调用的是super的构造方法
    public BoxBlockEntity(BlockPos blockPos, BlockState blockState) {
        this(ModBlockEntities.BOX, blockPos, blockState);
    }
    // 返回箱子的名字
    @Override
    protected Text getContainerName() {
        return Text.translatable("container.box");
    }

    // 返回箱子的物品栏
    @Override
    protected DefaultedList<ItemStack> getHeldStacks() {
        return this.inventory;
    }

    // 设置箱子的物品栏
    @Override
    protected void setHeldStacks(DefaultedList<ItemStack> inventory) {
        this.inventory = inventory;
    }

    // 创建一个箱子的屏幕处理器
    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return GenericContainerScreenHandler.createGeneric9x3(syncId, playerInventory, this);
    }

    // 返回箱子的物品栏大小
    @Override
    public int size() {
        return 27;
    }
}
