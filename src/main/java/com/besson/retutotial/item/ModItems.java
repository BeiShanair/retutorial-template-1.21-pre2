package com.besson.retutotial.item;

import com.besson.retutotial.ReTutorial;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {
    // 注册物品
    public static final Item ICE_ETHER = registerItems("ice_ether", Item::new, new Item.Settings());
    public static final Item RAW_ICE_ETHER = registerItems("raw_ice_ether",Item::new, new Item.Settings());
    // 注册方法，由原版改编（一堆方法整合）
    public static Item registerItems(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(ReTutorial.MOD_ID, name));

        Item item = (Item)factory.apply(settings.registryKey(key));

        if (item instanceof BlockItem blockItem) {
            blockItem.appendBlocks(Item.BLOCK_ITEMS, item);
        }

        return Registry.register(Registries.ITEM, key, item);
    }

    // 初始化方法
    public static void registerModItems() {
        ReTutorial.LOGGER.info("Registering Items");
    }
}
