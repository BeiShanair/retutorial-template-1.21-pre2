package com.besson.retutotial.entity.custom;

import com.besson.retutotial.data.PolishingMachineData;
import com.besson.retutotial.entity.ModBlockEntities;
import com.besson.retutotial.recipe.PolishingMachineRecipe;
import com.besson.retutotial.screen.PolishingMachineScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class PolishingMachineBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<PolishingMachineData>, ImplementedInventory {
    // 方块实体，继承BlockEntity
    // 实现ImplementedInventory接口（这个接口来自于Fabric的Wiki）
    // 实现ExtendedScreenHandlerFactory接口（这个接口是包括服务端和客户端的数据传输的，但现在的版本需要我们自己写传输的数据文件）

    // 一个DefaultedList，用来存放物品栏，默认为空
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);

    // 输入槽和输出槽的索引
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    // 一个PropertyDelegate，用来存放进度和最大进度
    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

    public PolishingMachineBlockEntity(BlockPos pos, BlockState state) {
        // 构造方法中修改了它的类型
        super(ModBlockEntities.POLISHING_MACHINE_BLOCK_ENTITY, pos, state);

        // 编写PropertyDelegate
        this.propertyDelegate = new PropertyDelegate() {
            // 重写get方法，根据index返回对应的值
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> PolishingMachineBlockEntity.this.progress;
                    case 1 -> PolishingMachineBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            // 重写set方法，根据index设置对应的值
            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> PolishingMachineBlockEntity.this.progress = value;
                    case 1 -> PolishingMachineBlockEntity.this.maxProgress = value;
                }
            }

            // 返回size
            @Override
            public int size() {
                return 2;
            }
        };
    }

    // 重写getItems方法，返回物品栏
    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    // 方块实体显示的名字
    @Override
    public Text getDisplayName() {
        return Text.translatable("container.retutorial.polishing_machine");
    }

    // 打开GUI需要的屏幕处理程序
    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new PolishingMachineScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    // 写入nbt数据
    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory,false, registryLookup);
        nbt.putInt("polishing_machine", progress);
    }

    // 读取nbt数据
    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory, registryLookup);
        progress = nbt.getInt("polishing_machine");
    }

    // 设置每个槽位的最大物品数量
    @Override
    public int getMaxCountPerStack() {
        return 64;
    }

    // 自定义的tick方法，用于处理实时计算
    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) {
            return;
        }
        if (isOutputSlotAvailable()) {
            if (this.hasRecipe()) {
                this.increaseCraftProgress();
                markDirty(world, pos, state);

                if (hasCraftingFinished()) {
                    this.craftItem();
                    this.resetProgress();
                }
            } else {
                this.resetProgress();
            }
        } else {
            this.resetProgress();
            markDirty(world, pos, state);
        }
    }

    // 重置进度
    private void resetProgress() {
        this.progress = 0;
    }

    // 制作物品
    private void craftItem() {
//        ItemStack result = new ItemStack(ModItems.ICE_ETHER);
//        this.setStack(OUTPUT_SLOT, new ItemStack(result.getItem(), getStack(OUTPUT_SLOT).getCount() + result.getCount()));
        Optional<RecipeEntry<PolishingMachineRecipe>> recipe = getCurrentRecipe();
        if (recipe.isPresent()) {
            PolishingMachineRecipe polishingMachineRecipe = recipe.get().value();
            int num = polishingMachineRecipe.getNum();
            if (getStack(INPUT_SLOT).getCount() < num) {
                return;
            }
            this.setStack(OUTPUT_SLOT, new ItemStack(polishingMachineRecipe.getResult(null).getItem(),
                    getStack(OUTPUT_SLOT).getCount() + polishingMachineRecipe.getResult(null).getCount()));

            this.removeStack(INPUT_SLOT, num);
        }
    }

    private Optional<RecipeEntry<PolishingMachineRecipe>> getCurrentRecipe() {
        SimpleInventory inv = new SimpleInventory(this.size());
        for (int i = 0; i < this.size(); i++) {
            inv.setStack(i, this.getStack(i));
        }
        return getWorld().getRecipeManager().getFirstMatch(PolishingMachineRecipe.Type.INSTANCE,
                new SingleStackRecipeInput(inv.getStack(0)), getWorld());
    }

    // 判断是否制作完成
    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    // 增加进度
    private void increaseCraftProgress() {
        progress++;
    }

    // 判断是否有配方
    private boolean hasRecipe() {
//        ItemStack result = new ItemStack(ModItems.ICE_ETHER);
//        boolean hasInput = getStack(INPUT_SLOT).getItem() == Items.ICE;
//        return hasInput && canInsertAmountIntoOutputSlot(result) &&
//                canInsertItemIntoOutputSlot(result.getItem());
        Optional<RecipeEntry<PolishingMachineRecipe>> recipe = getCurrentRecipe();

        return recipe.isPresent() && getStack(INPUT_SLOT).getCount() >= recipe.get().value().getNum() &&
                canInsertAmountIntoOutputSlot(recipe.get().value().getResult(null)) &&
                canInsertItemIntoOutputSlot(recipe.get().value().getResult(null).getItem());
    }

    // 判断是否可以插入物品到输出槽
    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= 64;
    }

    // 判断是否可以插入物品到输出槽
    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.getStack(OUTPUT_SLOT).getItem() == item ||
                this.getStack(OUTPUT_SLOT).isEmpty();
    }

    // 判断输出槽是否有空位
    private boolean isOutputSlotAvailable() {
        return this.getStack(OUTPUT_SLOT).isEmpty() ||
                this.getStack(OUTPUT_SLOT).getCount() < 64;
    }

    // 获取屏幕打开数据，也就是我们编写的数据文件
    @Override
    public PolishingMachineData getScreenOpeningData(ServerPlayerEntity player) {
        return new PolishingMachineData(pos);
    }
}
