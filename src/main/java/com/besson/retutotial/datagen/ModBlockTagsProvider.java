package com.besson.retutotial.datagen;

import com.besson.retutotial.block.ModBlocks;
import com.besson.retutotial.tags.ModBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagsProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.ICE_ETHER_BLOCK)
                .add(ModBlocks.ICE_ETHER_ORE)
                .add(ModBlocks.RAW_ICE_ETHER_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.ICE_ETHER_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.RAW_ICE_ETHER_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.ICE_ETHER_ORE);

        // 我们也可以使用数据生成器来添加我们模组的标签
        // 注意，add方法传参为Block
        // forceAddTag方法传参为Tag<Block>（下面的各类矿石包括普通矿石和深层矿石）
        getOrCreateTagBuilder(ModBlockTags.PROSPECTOR_ORE)
                .add(ModBlocks.ICE_ETHER_ORE)
                .forceAddTag(BlockTags.COAL_ORES)
                .forceAddTag(BlockTags.IRON_ORES)
                .forceAddTag(BlockTags.GOLD_ORES)
                .forceAddTag(BlockTags.LAPIS_ORES)
                .forceAddTag(BlockTags.REDSTONE_ORES)
                .forceAddTag(BlockTags.DIAMOND_ORES)
                .forceAddTag(BlockTags.EMERALD_ORES)
                .forceAddTag(BlockTags.COPPER_ORES);

        // 为fence、fence_gate、wall添加标签
        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(ModBlocks.ICE_ETHER_FENCE);
        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.ICE_ETHER_FENCE_GATE);
        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.ICE_ETHER_WALL);

        // 如果想让Button被箭或者三叉戟射中激活，则需要确保Block.Type是木头，且在这个标签里
        getOrCreateTagBuilder(BlockTags.BUTTONS)
                .add(ModBlocks.ICE_ETHER_BUTTON);

        // fence默认和下界砖栅栏相连，如果要和其他木制栅栏相连，需要在这个标签里（二者只能选其一）
        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.ICE_ETHER_FENCE);
    }
}
