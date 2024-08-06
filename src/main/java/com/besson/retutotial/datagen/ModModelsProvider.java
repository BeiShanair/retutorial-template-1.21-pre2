package com.besson.retutotial.datagen;

import com.besson.retutotial.block.ModBlockFamilies;
import com.besson.retutotial.block.ModBlocks;
import com.besson.retutotial.block.custom.CornCropBlock;
import com.besson.retutotial.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.ArmorItem;
import net.minecraft.state.property.Properties;

public class ModModelsProvider extends FabricModelProvider {
    public ModModelsProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // 参考原版的写法，使用BlockFamily
        ModBlockFamilies.getFamilies().filter(BlockFamily::shouldGenerateModels).forEach(
                family -> blockStateModelGenerator.registerCubeAllModelTexturePool(family.getBaseBlock())
                        .family((BlockFamily)family));

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ICE_ETHER_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_ICE_ETHER_BLOCK);
        // 作物的模型生成，罗列出所有的生长阶段
        blockStateModelGenerator.registerCrop(ModBlocks.STRAWBERRY_CROP, Properties.AGE_5, 0, 1, 2, 3, 4, 5);
        // 按照十字交叉的方式生成作物的模型，参考浆果作物
        blockStateModelGenerator.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(ModBlocks.CORN_CROP)
                        .coordinate(BlockStateVariantMap.create(CornCropBlock.AGE)
                                .register(stage -> BlockStateVariant.create()
                                        .put(VariantSettings.MODEL, blockStateModelGenerator.createSubModel(ModBlocks.CORN_CROP, "_stage" + stage,
                                                Models.CROSS, TextureMap::cross)))));

        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.BOX);
        blockStateModelGenerator.registerSimpleState(ModBlocks.POLISHING_MACHINE);
        // 生成简单的方块状态文件，不生成模型文件
//        blockStateModelGenerator.registerSimpleState(ModBlocks.SIMPLE_BLOCK);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.SIMPLE_BLOCK);
        // 因为SimpleFenceBlock有我们自定义的属性，所以这里不便使用数据生成器生成方块状态文件

        blockStateModelGenerator.registerLog(ModBlocks.ICE_ETHER_LOG).log(ModBlocks.ICE_ETHER_LOG).wood(ModBlocks.ICE_ETHER_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_ICE_ETHER_LOG).log(ModBlocks.STRIPPED_ICE_ETHER_LOG).wood(ModBlocks.STRIPPED_ICE_ETHER_WOOD);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ICE_ETHER_LEAVES);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.ICE_ETHER, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_ICE_ETHER, Models.GENERATED);
        itemModelGenerator.register(ModItems.STRAWBERRY, Models.GENERATED);
        itemModelGenerator.register(ModItems.CORN, Models.GENERATED);
        itemModelGenerator.register(ModItems.CORN_SEEDS, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHEESE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ANTHRACITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PROSPECTOR, Models.GENERATED);
        itemModelGenerator.register(ModItems.FIRE_ETHER, Models.GENERATED);

        itemModelGenerator.register(ModItems.FIRE_ETHER_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FIRE_ETHER_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FIRE_ETHER_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FIRE_ETHER_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FIRE_ETHER_HOE, Models.HANDHELD);

        itemModelGenerator.registerArmor((ArmorItem) ModItems.ICE_ETHER_HELMET);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.ICE_ETHER_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.ICE_ETHER_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.ICE_ETHER_BOOTS);

        itemModelGenerator.register(ModItems.TEST_MUSIC_DISC, Models.TEMPLATE_MUSIC_DISC);

        itemModelGenerator.register(ModItems.OIL_BUCKET, Models.GENERATED);

        itemModelGenerator.register(ModItems.ICE_ETHER_HORSE_ARMOR, Models.GENERATED);
        itemModelGenerator.registerWolfArmor(ModItems.ICE_ETHER_WOLF_ARMOR);

        // 方块组不会生成悬挂标牌的模型，所以我们需要手动编写
        itemModelGenerator.register(ModItems.ICE_ETHER_HANGING_SIGN, Models.GENERATED);

        itemModelGenerator.register(ModItems.ICE_ETHER_BOAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.ICE_ETHER_CHEST_BOAT, Models.GENERATED);
    }
}
