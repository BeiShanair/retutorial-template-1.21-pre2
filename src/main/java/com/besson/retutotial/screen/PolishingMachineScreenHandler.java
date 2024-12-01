package com.besson.retutotial.screen;
//
//import com.besson.retutotial.data.PolishingMachineData;
//import com.besson.retutotial.entity.custom.PolishingMachineBlockEntity;
//import net.minecraft.block.entity.BlockEntity;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.entity.player.PlayerInventory;
//import net.minecraft.inventory.Inventory;
//import net.minecraft.item.ItemStack;
//import net.minecraft.screen.ArrayPropertyDelegate;
//import net.minecraft.screen.PropertyDelegate;
//import net.minecraft.screen.ScreenHandler;
//import net.minecraft.screen.slot.Slot;
//
//public class PolishingMachineScreenHandler extends ScreenHandler {
//    // 屏幕处理器，继承ScreenHandler，这个类是用来处理屏幕的
//    // 除了要渲染GUI，还得设置各个物品栏的位置
//
//    // 一个Inventory，用来存放物品栏
//    private final Inventory inventory;
//
//    // 一个PropertyDelegate，用来存放进度和最大进度
//    private final PropertyDelegate propertyDelegate;
//
//    // 方块实体
//    public final PolishingMachineBlockEntity blockEntity;
//
//    public PolishingMachineScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate propertyDelegate) {
//        // 构造方法中同样更改了类型
//        super(ModScreenHandlers.POLISHING_MACHINE_SCREEN_HANDLER, syncId);
//
//        // 检查物品栏的大小
//        checkSize((Inventory) blockEntity, 2);
//
//        // 设置物品栏，其实就是获取方块实体的物品栏
//        this.inventory = (Inventory) blockEntity;
//        inventory.onOpen(playerInventory.player);
//
//        // 设置PropertyDelegate
//        this.propertyDelegate = propertyDelegate;
//        this.blockEntity = (PolishingMachineBlockEntity) blockEntity;
//
//        // 添加两个槽，即我们方块实体的物品栏
//        this.addSlot(new Slot(inventory, 0, 80, 11));
//        this.addSlot(new Slot(inventory, 1, 80, 59));
//
//        // 添加玩家的物品栏和快捷栏
//        addPlayerInventory(playerInventory);
//        addPlayerHotbar(playerInventory);
//
//        // 添加属性
//        addProperties(propertyDelegate);
//    }
//
//    // 下面的构造方法是用于注册的
//    public PolishingMachineScreenHandler(int syncId, PlayerInventory playerInventory, PolishingMachineData data) {
//        this(syncId, playerInventory, playerInventory.player.getWorld().getBlockEntity(data.pos()),
//                new ArrayPropertyDelegate(2));
//    }
//
//
//    // 重写快速移动方法（按shift可以快速移动物品）
//    @Override
//    public ItemStack quickMove(PlayerEntity player, int slot) {
//        ItemStack newStack = ItemStack.EMPTY;
//        Slot invslot = this.slots.get(slot);
//        if (invslot != null && invslot.hasStack()) {
//            ItemStack originalStack = invslot.getStack();
//            newStack = originalStack.copy();
//            if (slot < this.inventory.size()) {
//                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
//                    return ItemStack.EMPTY;
//                }
//            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
//                return ItemStack.EMPTY;
//            }
//
//            if (originalStack.isEmpty()) {
//                invslot.setStack(ItemStack.EMPTY);
//            } else {
//                invslot.markDirty();
//            }
//        }
//        return newStack;
//    }
//
//    // 重写canUse方法，判断玩家是否可以使用这个屏幕
//    @Override
//    public boolean canUse(PlayerEntity player) {
//        return this.inventory.canPlayerUse(player);
//    }
//
//    // 添加玩家的物品栏（其实你自己去搬原版的那些代码就可以）
//    private void addPlayerInventory(PlayerInventory playerInventory) {
//        for (int i = 0; i < 3; ++i) {
//            for (int l = 0; l < 9; ++l) {
//                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
//            }
//        }
//    }
//
//    // 添加玩家的快捷栏
//    private void addPlayerHotbar(PlayerInventory playerInventory) {
//        for (int i = 0; i < 9; ++i) {
//            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
//        }
//    }
//
//    // 判断是否正在合成
//    public boolean isCrafting() {
//        return propertyDelegate.get(0) > 0;
//    }
//
//    // 判断是否正在下雨（这个只是个例子）
//    public boolean isRaining() {
//        return blockEntity.getWorld().isRaining();
//    }
//
//    // 获取进度（根据进度去渲染箭头）
//    public int getScaledProgress() {
//        int progress = propertyDelegate.get(0);
//        int maxProgress = propertyDelegate.get(1);
//        // 这个是那个箭头的长度
//        int progressArrowSize = 26;
//
//        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
//    }
//}
