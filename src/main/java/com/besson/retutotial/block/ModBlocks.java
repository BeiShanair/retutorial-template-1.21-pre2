package com.besson.retutotial.block;

import com.besson.retutotial.ReTutorial;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ModBlocks {
    public static final Block ICE_ETHER_ORE = registerBlocks("ice_ether_ore",
            Block::new,
            AbstractBlock.Settings.create().requiresTool().strength(3.0F, 3.0F),
            true);
    public static final Block ICE_ETHER_BLOCK = registerBlocks("ice_ether_block",
            Block::new,
            AbstractBlock.Settings.create().requiresTool().strength(4.5F, 6.0F),
            true);
    public static final Block RAW_ICE_ETHER_BLOCK = registerBlocks("raw_ice_ether_block",
            Block::new,
            AbstractBlock.Settings.create().requiresTool().strength(3.0F, 3.0F),
            true);

    // 基于原版的方块物品注册方法
    public static void registerBlockItems(String name, Block block) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(ReTutorial.MOD_ID, name));
        BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey).useBlockPrefixedTranslationKey());
        Registry.register(Registries.ITEM, itemKey, blockItem);

        if (blockItem instanceof BlockItem) {
            ((BlockItem) blockItem).appendBlocks(Item.BLOCK_ITEMS, blockItem);
        }
    }
    // 基于原版的方块注册方法
    public static Block registerBlocks(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings,
                                       boolean registerItem) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(ReTutorial.MOD_ID, name));
        Block block = (Block)factory.apply(settings.registryKey(key));

        if (registerItem){
            registerBlockItems(name, block);
         }

        return Registry.register(Registries.BLOCK, key, block);
    }

    // 初始化方法
    public static void registerModBlocks(){
        ReTutorial.LOGGER.info("Registering Blocks");
    }
}
