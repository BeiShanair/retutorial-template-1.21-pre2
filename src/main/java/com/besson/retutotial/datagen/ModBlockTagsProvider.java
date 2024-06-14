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
    }
}
