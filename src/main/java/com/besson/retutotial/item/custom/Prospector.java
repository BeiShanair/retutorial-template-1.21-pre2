package com.besson.retutotial.item.custom;

import com.besson.retutotial.sounds.ModSoundEvents;
import com.besson.retutotial.tags.ModBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class Prospector extends Item {
    public ItemStack stack = this.getDefaultStack();
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
                                player.sendMessage(Text.of("Found " + blockName + "!"), false);
                                world.playSound(null, blockPos, ModSoundEvents.PROSPECTOR_FOUND_ORE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                                foundBlock = true;
                                break;
                            }
                        }
                    }
                }
                // 当然，如果没有找到矿石，就输出“没有找到矿石”
                if (!foundBlock) {
                    player.sendMessage(Text.of("No Ore Found!"), false);
                }
            } else {
                // 这里的方法和上面的方法类似，只是没有扩大搜索范围
                for (int i = 0; i <= blockPos.getY() + 64; i++) {
                    BlockPos blockPos1 = blockPos.down(i);
                    BlockState blockState = context.getWorld().getBlockState(blockPos1);
                    String blockName = blockState.getBlock().getName().getString();

                    if (isRightBlock(blockState)) {
                        player.sendMessage(Text.of("Found " + blockName + "!"), false);
                        world.playSound(null, blockPos, ModSoundEvents.PROSPECTOR_FOUND_ORE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        foundBlock = true;
                        break;
                    }
                }
                if (!foundBlock) {
                    player.sendMessage(Text.of("No Ore Found!"), false);
                }
            }
        }
        // 每次使用后，耐久度减1
        context.getStack().damage(1, player, LivingEntity.getSlotForHand(context.getHand()));

        player.sendMessage(Text.of("Durability: " + context.getStack().getDamage() + "/127"), false);

        // 当耐久度大于50时，提示玩家，更换物品
        // 并且将耐久度传递给新的物品
        if (context.getStack().getDamage() > 50) {
            player.sendMessage(Text.of("Your Prospector is getting old!"), false);
            ItemStack newStack = new ItemStack(Items.STONE_AXE);
            newStack.setDamage(context.getStack().getDamage());

            context.getStack().getEnchantments().getEnchantmentEntries().forEach(enchantment -> {
                newStack.addEnchantment(enchantment.getKey(), enchantment.getIntValue());
            });
            context.getPlayer().setStackInHand(context.getHand(), newStack);
        }
        return ActionResult.SUCCESS;
    }


    // 用Tag来改写方法
    private boolean isRightBlock(BlockState blockState) {
        if (blockState.isIn(ModBlockTags.PROSPECTOR_ORE)) {
            return true;
        } else {
            return false;
        }
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
