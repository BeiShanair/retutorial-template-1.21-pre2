package com.besson.retutotial.entity;

import com.besson.retutotial.ReTutorial;
import com.besson.retutotial.block.ModBlocks;
import com.besson.retutotial.entity.custom.BoxBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    // 注册箱子方块实体，和原版的BlockEntityType一样
    public static final BlockEntityType<BoxBlockEntity> BOX = create("box", BoxBlockEntity::new, ModBlocks.BOX);

//    public static final BlockEntityType<PolishingMachineBlockEntity> POLISHING_MACHINE_BLOCK_ENTITY = create("polishing_machine_block_entity",
//            PolishingMachineBlockEntity::new, ModBlocks.POLISHING_MACHINE);

    private static <T extends BlockEntity> BlockEntityType<T> create(String id, FabricBlockEntityTypeBuilder.Factory<T> factory, Block... blocks) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE,
                Identifier.of(ReTutorial.MOD_ID, id),
                FabricBlockEntityTypeBuilder.create(factory, blocks).build());
    }
    // 初始化方法
    public static void registerBlockEntities() {
    }
}
