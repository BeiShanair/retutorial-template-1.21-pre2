package com.besson.retutotial.item.custom;

import com.besson.retutotial.tags.ModBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class Prospector extends Item {
    public Prospector(Settings settings) {
        super(settings);

    }

    // 重写右键点击方块的方法
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {

        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        PlayerEntity player = context.getPlayer();

        if (!world.isClient()) {
            boolean foundBlock = false;

            if (!Screen.hasShiftDown()) {
                for (int i = 0; i <= blockPos.getY() + 64; i++) {
                    for (int j = 0; j <= 5; j++) {
                        for (int k = 0; k <= 5; k++) {
                            BlockPos blockPos1 = blockPos.down(i).north(j).east(k);
                            BlockState blockState = context.getWorld().getBlockState(blockPos1);
                            String blockName = blockState.getBlock().getName().getString();

                            if (isRightBlock(blockState)) {
                                // 输出找到的矿石名称，当然为了游戏的平衡性，就不输出坐标了
                                player.sendMessage(Text.of("Found " + blockName + "!"));
                                foundBlock = true;
                                break;
                            }
                        }
                    }
                }
                // 当然，如果没有找到矿石，就输出“没有找到矿石”
                if (!foundBlock) {
                    player.sendMessage(Text.of("No Ore Found!"));
                }
            } else {
                // 这里的方法和上面的方法类似，只是没有扩大搜索范围
                for (int i = 0; i <= blockPos.getY() + 64; i++) {
                    BlockPos blockPos1 = blockPos.down(i);
                    BlockState blockState = context.getWorld().getBlockState(blockPos1);
                    String blockName = blockState.getBlock().getName().getString();

                    if (isRightBlock(blockState)) {
                        player.sendMessage(Text.of("Found " + blockName + "!"));
                        foundBlock = true;
                        break;
                    }
                }
                if (!foundBlock) {
                    player.sendMessage(Text.of("No Ore Found!"));
                }
            }
        }
        // 每次使用后，耐久度减1
        context.getStack().damage(1, player, LivingEntity.getSlotForHand(context.getHand()));
        return ActionResult.SUCCESS;
    }

//    // 我们自定义的方法，用于判断方块是否是矿石
//    private boolean isRightBlock(BlockState blockState) {
//        // 这玩意讲了Tags之后就会简单很多
//        if (blockState.isOf(Blocks.DIAMOND_ORE) || blockState.isOf(Blocks.IRON_ORE) || blockState.isOf(Blocks.COAL_ORE)){
//            return true;
//        } else {
//            return false;
//        }
//    }

    // 用Tag来改写方法
    private boolean isRightBlock(BlockState blockState) {
        if (blockState.isIn(ModBlockTags.PROSPECTOR_ORE)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    // 暂定附魔方法
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        DynamicRegistryManager registryManager = world.getRegistryManager();
        RegistryEntry<Enchantment> enchantment =
                registryManager.get(RegistryKeys.ENCHANTMENT).getEntry(Identifier.ofVanilla("sharpness")).get();
        stack.addEnchantment(enchantment, 5);
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        if (Screen.hasShiftDown()){
            tooltip.add(Text.translatable("item.retutorial.prospector.shift_tooltip"));
        }else {
            tooltip.add(Text.translatable("item.retutorial.prospector.tooltip"));
        }
    }
}
