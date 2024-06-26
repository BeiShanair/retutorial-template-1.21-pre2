package com.besson.retutotial.block;

import com.besson.retutotial.ReTutorial;
import net.minecraft.block.*;
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

    public static final Block ICE_ETHER_STAIRS = registerBlocks("ice_ether_stairs",
            new StairsBlock(ICE_ETHER_BLOCK.getDefaultState(), AbstractBlock.Settings.copy(ICE_ETHER_BLOCK)));
    public static final Block ICE_ETHER_SLAB = registerBlocks("ice_ether_slab",
            new SlabBlock(AbstractBlock.Settings.copy(ICE_ETHER_BLOCK)));
    // 按钮的参数顺序变了，且少了一个wooden的属性
    public static final Block ICE_ETHER_BUTTON = registerBlocks("ice_ether_button",
            new ButtonBlock(BlockSetType.OAK, 10, AbstractBlock.Settings.copy(ICE_ETHER_BLOCK)));
    // 压力板的参数顺序变了，且没有了敏感度的参数
    public static final Block ICE_ETHER_PRESSURE_PLATE = registerBlocks("ice_ether_pressure_plate",
            new PressurePlateBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(ICE_ETHER_BLOCK)));
    public static final Block ICE_ETHER_FENCE = registerBlocks("ice_ether_fence",
            new FenceBlock(AbstractBlock.Settings.copy(ICE_ETHER_BLOCK)));
    public static final Block ICE_ETHER_FENCE_GATE = registerBlocks("ice_ether_fence_gate",
            new FenceGateBlock(WoodType.OAK, AbstractBlock.Settings.copy(ICE_ETHER_BLOCK)));
    public static final Block ICE_ETHER_WALL = registerBlocks("ice_ether_wall",
            new WallBlock(AbstractBlock.Settings.copy(ICE_ETHER_BLOCK)));
    public static final Block ICE_ETHER_DOOR = registerBlocks("ice_ether_door",
            new DoorBlock(BlockSetType.STONE, AbstractBlock.Settings.copy(ICE_ETHER_BLOCK).nonOpaque()));
    public static final Block ICE_ETHER_TRAPDOOR = registerBlocks("ice_ether_trapdoor",
            new TrapdoorBlock(BlockSetType.IRON, AbstractBlock.Settings.copy(ICE_ETHER_BLOCK).nonOpaque()));

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
