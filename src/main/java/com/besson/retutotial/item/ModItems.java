package com.besson.retutotial.item;

import com.besson.retutotial.ReTutorial;
import com.besson.retutotial.item.custom.Prospector;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    // 注册物品
    public static final Item ICE_ETHER = registerItems("ice_ether", new Item(new Item.Settings()));
    public static final Item RAW_ICE_ETHER = registerItems("raw_ice_ether", new Item(new Item.Settings()));
    public static final Item STRAWBERRY = registerItems("strawberry", new Item(new Item.Settings().food(ModFoodComponents.STRAWBERRY)));
    public static final Item CHEESE = registerItems("cheese", new Item(new Item.Settings().food(ModFoodComponents.CHEESE)));
    public static final Item ANTHRACITE = registerItems("anthracite", new Item(new Item.Settings()));
    public static final Item PROSPECTOR = registerItems("prospector", new Prospector(new Item.Settings().maxDamage(127)));
    public static final Item PLATE = registerItems("plate", new Item(new Item.Settings()));
    // 注册方法，由原版改编（一堆方法整合）
    private static Item registerItems(String name, Item item) {
        // 由原版整合的方法
//        return Registry.register(Registries.ITEM, RegistryKey.of(Registries.ITEM.getKey(), Identifier.of(ReTutorial.MOD_ID, name)), item);
        // 采用register的另一个方法
        return Registry.register(Registries.ITEM, Identifier.of(ReTutorial.MOD_ID, name), item);
    }
    // 初始化方法
    public static void registerModItems() {
        ReTutorial.LOGGER.info("Registering Items");
    }
}
