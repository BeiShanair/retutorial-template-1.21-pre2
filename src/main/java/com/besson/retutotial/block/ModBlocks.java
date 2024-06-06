package com.besson.retutotial.block;

import com.besson.retutotial.ReTutorial;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block ICE_ETHER_ORE = registerBlocks("ice_ether_ore",
            new Block(AbstractBlock.Settings.create().requiresTool().strength(3.0F, 3.0F)));
    public static final Block ICE_ETHER_BLOCK = registerBlocks("ice_ether_block",
            new Block(AbstractBlock.Settings.create().requiresTool().strength(4.5F, 6.0F)));
    public static final Block RAW_ICE_ETHER_BLOCK = registerBlocks("raw_ice_ether_block",
            new Block(AbstractBlock.Settings.create().requiresTool().strength(3.0F, 3.0F)));
    // 基于原版的方块物品注册方法
    public static void registerBlockItems(String name, Block block) {
        Item item = Registry.register(Registries.ITEM, Identifier.of(ReTutorial.MOD_ID, name), new BlockItem(block, new Item.Settings()));
        if (item instanceof BlockItem) {
            ((BlockItem) item).appendBlocks(Item.BLOCK_ITEMS, item);
        }
    }
    // 基于原版的方块注册方法
    public static Block registerBlocks(String name, Block block) {
        registerBlockItems(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(ReTutorial.MOD_ID, name), block);
    }
    // 初始化方法
    public static void registerModBlocks(){
        ReTutorial.LOGGER.info("Registering Blocks");
    }
}
