package com.besson.retutotial.item;

import com.besson.retutotial.ReTutorial;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    // 注册物品
    public static final Item ICE_ETHER = registerItems("ice_ether", new Item(new Item.Settings()));
    // 注册方法，由原版改编
    private static Item registerItems(String name, Item item) {
        ReTutorial.LOGGER.info("Registering Item: {}", name);
        return Registry.register(Registries.ITEM, Identifier.of(ReTutorial.MOD_ID, name), item);
    }
    // 初始化方法
    public static void registerModItems() {
        ReTutorial.LOGGER.info("Registering Items");
    }
}
