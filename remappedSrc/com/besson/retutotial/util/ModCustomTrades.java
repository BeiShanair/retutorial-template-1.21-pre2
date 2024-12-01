package com.besson.retutotial.util;

import com.besson.retutotial.block.ModBlocks;
import com.besson.retutotial.item.ModItems;
import com.besson.retutotial.villager.ModVillagers;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

public class ModCustomTrades {
    // 原版的交易是在TradeOffers中，而直接修改原版的交易是比较复杂的
    // 所以我们使用Fabric API提供的TradeOfferHelper来注册我们的交易
    public static void registerModTrades() {
        // 注册农民的交易
        // 第一个参数是职业，第二个参数是等级
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1, factories -> {
            // BuyItemFactory是购买物品的工厂，即玩家出售物品，村民给绿宝石
            factories.add(new TradeOffers.BuyItemFactory(ModItems.CORN, 5, 12, 5, 2));
            // SellItemFactory是出售物品的工厂，即村民出售物品，玩家给绿宝石
            factories.add(new TradeOffers.SellItemFactory(ModItems.CORN_SEEDS, 1, 12, 5, 2, 0.5f));
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 2, factories -> {
            factories.add(new TradeOffers.BuyItemFactory(ModItems.STRAWBERRY, 5, 12, 5, 2));
            // ProcessItemFactory是加工物品的工厂，即玩家给出原材料和绿宝石，村民给出加工后的物品
            factories.add(new TradeOffers.ProcessItemFactory(Items.MILK_BUCKET, 1, 2, ModItems.CHEESE, 3, 16, 1, 0.5f));
        });

        // 由于1.21版本的附魔更改，导致我们无法指定到确切附魔类型和附魔等级的附魔书或者工具
        // 原版提供的SellEnchantedToolFactory和EnchantBookFactory无法指定附魔类型和等级
        // 所以关于附魔物品的交易省略

        TradeOfferHelper.registerVillagerOffers(ModVillagers.ICE_ETHER_MASTER, 1, factories -> {
            factories.add(new TradeOffers.SellItemFactory(ModItems.ICE_ETHER, 2, 9, 12, 2, 0.5f));
            factories.add(new TradeOffers.BuyItemFactory(ModItems.RAW_ICE_ETHER, 2, 9, 12, 2));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.ICE_ETHER_MASTER, 2, factories -> {
            factories.add(new TradeOffers.SellItemFactory(ModBlocks.ICE_ETHER_BLOCK.asItem(), 4, 16, 12, 4, 0.5f));
            factories.add(new TradeOffers.BuyItemFactory(ModBlocks.RAW_ICE_ETHER_BLOCK.asItem(), 4, 16, 12, 4));
        });
    }
}
