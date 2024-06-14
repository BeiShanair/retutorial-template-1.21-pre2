package com.besson.retutotial.item.custom;

import com.besson.retutotial.tags.ModBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Prospector extends Item {

    // super函数调用父类的构造函数，并设置最大耐久度为127
    public Prospector(Settings settings) {
        super(settings.maxDamage(127));
    }

    // useOnBlock方法将在玩家右键点击方块时触发
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        // 获取世界、方块坐标、玩家
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        PlayerEntity player = context.getPlayer();

        // 如果不是客户端
        if (!world.isClient()) {
            boolean foundBlock = false;
            // 如果按住shift键，就扩大搜索范围（往北和西各3个方块的范围）
            if (Screen.hasShiftDown()) {
                for (int i = 0; i <= blockPos.getY() + 128; i++) {
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
                for (int i = 0; i <= blockPos.getY() + 128; i++) {
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
        // 这玩意讲了Tags之后就会简单很多
        if (blockState.isIn(ModBlockTags.PROSPECTOR_ORE)){
            return true;
        } else {
            return false;
        }
    }
}
