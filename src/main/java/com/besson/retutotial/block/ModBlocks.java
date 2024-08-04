package com.besson.retutotial.block;

import com.besson.retutotial.ReTutorial;
import com.besson.retutotial.block.custom.*;
import com.besson.retutotial.entity.ModBlockEntities;
import com.besson.retutotial.sounds.ModSoundEvents;
import com.terraformersmc.terraform.sign.api.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallSignBlock;
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
            new IceEtherBlock(AbstractBlock.Settings.create().requiresTool().strength(4.5F, 6.0F).sounds(ModSoundEvents.BLOCK_SOUND_GROUP).nonOpaque()));
    public static final Block RAW_ICE_ETHER_BLOCK = registerBlocks("raw_ice_ether_block",
            new Block(AbstractBlock.Settings.create().requiresTool().strength(3.0F, 3.0F)));

    public static final Block ICE_ETHER_STAIRS = registerBlocks("ice_ether_stairs",
            new StairsBlock(ICE_ETHER_BLOCK.getDefaultState(), AbstractBlock.Settings.copy(ICE_ETHER_BLOCK)));
    public static final Block ICE_ETHER_SLAB = registerBlocks("ice_ether_slab",
            new SlabBlock(AbstractBlock.Settings.copy(ICE_ETHER_BLOCK).nonOpaque()));
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
    // 作物方块
    // 由于我们不需要返回方块物品，所以不需要调用registerBlockItems方法
    public static final Block STRAWBERRY_CROP = Registry.register(Registries.BLOCK, Identifier.of(ReTutorial.MOD_ID, "strawberry_crop"),
            new StrawberryCropBlock(AbstractBlock.Settings.copy(Blocks.WHEAT)));
    public static final Block CORN_CROP = Registry.register(Registries.BLOCK, Identifier.of(ReTutorial.MOD_ID, "corn_crop"),
            new CornCropBlock(AbstractBlock.Settings.copy(Blocks.WHEAT)));
    public static final Block OIL = Registry.register(Registries.BLOCK, Identifier.of(ReTutorial.MOD_ID, "oil"),
            new FluidBlock(ModFluids.OIL, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block BOX = registerBlocks("box",
            new BoxBlock(AbstractBlock.Settings.copy(Blocks.CHEST), () -> ModBlockEntities.BOX));
    public static final Block POLISHING_MACHINE = registerBlocks("polishing_machine",
            new PolishingMachine(AbstractBlock.Settings.copy(Blocks.STONE)));

    public static final Block SIMPLE_BLOCK = registerBlocks("simple_block",
            new SimpleBlock(AbstractBlock.Settings.copy(Blocks.STONE)));

    public static final Block SIMPLE_FENCE = registerBlocks("simple_fence",
            new SimpleFenceBlock(AbstractBlock.Settings.copy(Blocks.STONE).nonOpaque()));
    public static final Block ICE_ETHER_LOG = registerBlocks("ice_ether_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG).strength(4.0F)));
    public static final Block ICE_ETHER_WOOD = registerBlocks("ice_ether_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_WOOD).strength(4.0F)));
    public static final Block STRIPPED_ICE_ETHER_LOG = registerBlocks("stripped_ice_ether_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_LOG).strength(4.0F)));
    public static final Block STRIPPED_ICE_ETHER_WOOD = registerBlocks("stripped_ice_ether_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD).strength(4.0F)));
    public static final Block ICE_ETHER_PLANKS = registerBlocks("ice_ether_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).strength(4.0F)));
    public static final Block ICE_ETHER_LEAVES = registerBlocks("ice_ether_leaves",
            new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).nonOpaque()));

    // 我们借助Terraform API来实现告示牌的注册
    // 首先需要一些Identifier，这些是材质文件的路径
    public static final Identifier ICE_ETHER_SIGN_TEXTURE = Identifier.of(ReTutorial.MOD_ID, "entity/signs/ice_ether");
    public static final Identifier ICE_ETHER_HANGING_SIGN_TEXTURE = Identifier.of(ReTutorial.MOD_ID, "entity/signs/hanging/ice_ether");
    public static final Identifier ICE_ETHER_HANGING_SING_GUI = Identifier.of(ReTutorial.MOD_ID, "textures/gui/hanging_signs/ice_ether");

    // 告示牌的物品是两个为一组，所以说这里要分开来注册
    public static final Block ICE_ETHER_SIGN = Registry.register(Registries.BLOCK, Identifier.of(ReTutorial.MOD_ID, "ice_ether_sign"),
            new TerraformSignBlock(ICE_ETHER_SIGN_TEXTURE, AbstractBlock.Settings.copy(Blocks.OAK_SIGN)));
    public static final Block ICE_ETHER_WALL_SIGN = Registry.register(Registries.BLOCK, Identifier.of(ReTutorial.MOD_ID, "ice_ether_wall_sign"),
            new TerraformWallSignBlock(ICE_ETHER_SIGN_TEXTURE, AbstractBlock.Settings.copy(Blocks.OAK_WALL_SIGN)));
    public static final Block ICE_ETHER_HANGING_SIGN = Registry.register(Registries.BLOCK, Identifier.of(ReTutorial.MOD_ID, "ice_ether_hanging_sign"),
            new TerraformHangingSignBlock(ICE_ETHER_HANGING_SIGN_TEXTURE, ICE_ETHER_HANGING_SING_GUI, AbstractBlock.Settings.copy(Blocks.OAK_HANGING_SIGN)));
    public static final Block ICE_ETHER_WALL_HANGING_SIGN = Registry.register(Registries.BLOCK, Identifier.of(ReTutorial.MOD_ID, "ice_ether_wall_hanging_sign"),
            new TerraformWallHangingSignBlock(ICE_ETHER_HANGING_SIGN_TEXTURE, ICE_ETHER_HANGING_SING_GUI, AbstractBlock.Settings.copy(Blocks.OAK_WALL_HANGING_SIGN)));


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
