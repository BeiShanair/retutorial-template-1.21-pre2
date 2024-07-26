package com.besson.retutotial.entity;

import com.besson.retutotial.ReTutorial;
import com.besson.retutotial.block.ModBlocks;
import com.besson.retutotial.entity.custom.BoxBlockEntity;
import com.mojang.datafixers.types.Type;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class ModBlockEntities {
    // 注册箱子方块实体，和原版的BlockEntityType一样
    public static final BlockEntityType<BoxBlockEntity> BOX = create("box", BlockEntityType.Builder.create(BoxBlockEntity::new, ModBlocks.BOX));
    // create方法，用于注册方块实体，原版的方法改编
    private static <T extends BlockEntity> BlockEntityType<T> create(String id, BlockEntityType.Builder<T> builder) {
        Type<?> type = Util.getChoiceType(TypeReferences.BLOCK_ENTITY, id);
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(ReTutorial.MOD_ID, id), builder.build(type));
    }
    // 初始化方法
    public static void registerBlockEntities() {
    }
}
