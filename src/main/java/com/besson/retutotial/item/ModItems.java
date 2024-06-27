package com.besson.retutotial.item;

import com.besson.retutotial.ReTutorial;
import com.besson.retutotial.item.custom.Prospector;
import net.minecraft.item.*;
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
    public static final Item FIRE_ETHER = registerItems("fire_ether", new Item(new Item.Settings()));
    public static final Item FIRE_ETHER_SWORD = registerItems("fire_ether_sword", new SwordItem(ModToolMaterials.FIRE_ETHER,
                    new Item.Settings().fireproof().attributeModifiers(
                            SwordItem.createAttributeModifiers(ModToolMaterials.FIRE_ETHER, 3, -2.4f))));
    public static final Item FIRE_ETHER_SHOVEL = registerItems("fire_ether_shovel", new ShovelItem(ModToolMaterials.FIRE_ETHER,
            new Item.Settings().fireproof().attributeModifiers(
                    ShovelItem.createAttributeModifiers(ModToolMaterials.FIRE_ETHER, 1.5f, -3.0f))));
    public static final Item FIRE_ETHER_PICKAXE = registerItems("fire_ether_pickaxe", new PickaxeItem(ModToolMaterials.FIRE_ETHER,
            new Item.Settings().fireproof().attributeModifiers(
                    PickaxeItem.createAttributeModifiers(ModToolMaterials.FIRE_ETHER, 1.0f, -2.8f))));
    public static final Item FIRE_ETHER_AXE = registerItems("fire_ether_axe", new AxeItem(ModToolMaterials.FIRE_ETHER,
            new Item.Settings().fireproof().attributeModifiers(
                    AxeItem.createAttributeModifiers(ModToolMaterials.FIRE_ETHER, 5.0f, -3.0f))));
    public static final Item FIRE_ETHER_HOE = registerItems("fire_ether_hoe", new HoeItem(ModToolMaterials.FIRE_ETHER,
            new Item.Settings().fireproof().attributeModifiers(
                    HoeItem.createAttributeModifiers(ModToolMaterials.FIRE_ETHER, -4.0f, 0.0f))));
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
