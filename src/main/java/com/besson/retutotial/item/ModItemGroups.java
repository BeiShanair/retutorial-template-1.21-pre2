package com.besson.retutotial.item;

import com.besson.retutotial.ReTutorial;
import com.besson.retutotial.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
//    // 基于原版的方法
//    // 先写一个key
//    public static final RegistryKey<ItemGroup> RETUTORIAL_GROUP = register("retutorial_group");
//    // 再写一个注册方法
//    private static RegistryKey<ItemGroup> register(String name) {
//        return RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(ReTutorial.MOD_ID, name));
//    }
//    // 初始化方法
//    public static void registerModItemGroups() {
//        Registry.register(Registries.ITEM_GROUP, RETUTORIAL_GROUP,
//                ItemGroup.create(ItemGroup.Row.TOP, 7).displayName(Text.translatable("itemGroup.retutorial_group"))
//                        .icon(() -> new ItemStack(ModItems.ICE_ETHER)).entries((displayContext, entries) -> {
//                            entries.add(ModItems.ICE_ETHER);
//                        }).build());
//        ReTutorial.LOGGER.info("Registering Item Groups");
//    }
    // 简化的方法，利用返回值是ItemGroup这个特性
    public static final ItemGroup RETUTORIAL_GROUP = Registry.register(Registries.ITEM_GROUP, Identifier.of(ReTutorial.MOD_ID, "retutorial_group"),
            ItemGroup.create(null, -1).displayName(Text.translatable("itemGroup.retutorial_group"))
                    .icon(() -> new ItemStack(ModItems.ICE_ETHER)).entries((displayContext, entries) -> {
                entries.add(ModItems.ICE_ETHER);
                entries.add(ModItems.RAW_ICE_ETHER);
                entries.add(ModItems.STRAWBERRY);
                entries.add(ModItems.CHEESE);
                entries.add(ModItems.ANTHRACITE);
                entries.add(ModItems.PLATE);
                entries.add(ModItems.FIRE_ETHER);

                entries.add(ModItems.PROSPECTOR);

                entries.add(ModItems.FIRE_ETHER_AXE);
                entries.add(ModItems.FIRE_ETHER_PICKAXE);
                entries.add(ModItems.FIRE_ETHER_SHOVEL);
                entries.add(ModItems.FIRE_ETHER_SWORD);
                entries.add(ModItems.FIRE_ETHER_HOE);

                entries.add(ModBlocks.ICE_ETHER_ORE);
                entries.add(ModBlocks.ICE_ETHER_BLOCK);
                entries.add(ModBlocks.RAW_ICE_ETHER_BLOCK);

                entries.add(ModBlocks.ICE_ETHER_STAIRS);
                entries.add(ModBlocks.ICE_ETHER_SLAB);
                entries.add(ModBlocks.ICE_ETHER_BUTTON);
                entries.add(ModBlocks.ICE_ETHER_PRESSURE_PLATE);
                entries.add(ModBlocks.ICE_ETHER_FENCE);
                entries.add(ModBlocks.ICE_ETHER_FENCE_GATE);
                entries.add(ModBlocks.ICE_ETHER_WALL);
                entries.add(ModBlocks.ICE_ETHER_DOOR);
                entries.add(ModBlocks.ICE_ETHER_TRAPDOOR);

            }).build());


    public static void registerModItemGroups() {
        ReTutorial.LOGGER.info("Registering Item Groups");
    }
}
