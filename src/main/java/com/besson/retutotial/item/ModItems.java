package com.besson.retutotial.item;

import com.besson.retutotial.ReTutorial;
import com.besson.retutotial.block.ModBlocks;
import com.besson.retutotial.block.ModFluids;
import com.besson.retutotial.item.custom.FireEther;
import com.besson.retutotial.item.custom.Hat;
import com.besson.retutotial.item.custom.ModArmorItem;
import com.besson.retutotial.item.custom.Prospector;
import com.besson.retutotial.sounds.ModJukeboxSongs;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    // 注册物品
    public static final Item ICE_ETHER = registerItems("ice_ether", new Item(new Item.Settings()));
    public static final Item RAW_ICE_ETHER = registerItems("raw_ice_ether", new Item(new Item.Settings()));
    public static final Item STRAWBERRY = registerItems("strawberry", new Item(new Item.Settings().food(ModFoodComponents.STRAWBERRY)));
    public static final Item CHEESE = registerItems("cheese", new Item(new Item.Settings().food(ModFoodComponents.CHEESE)));
    public static final Item ANTHRACITE = registerItems("anthracite", new Item(new Item.Settings()));
    public static final Item PROSPECTOR = registerItems("prospector", new Prospector(new Item.Settings().maxDamage(127)));
    public static final Item PLATE = registerItems("plate", new Item(new Item.Settings()));
    public static final Item FIRE_ETHER = registerItems("fire_ether", new FireEther(new Item.Settings()));
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

    public static final Item ICE_ETHER_HELMET = registerItems("ice_ether_helmet", new ModArmorItem(ModArmorMaterials.ICE_ETHER,
                    ArmorItem.Type.HELMET, new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(37))));
    public static final Item ICE_ETHER_CHESTPLATE = registerItems("ice_ether_chestplate", new ArmorItem(ModArmorMaterials.ICE_ETHER,
                    ArmorItem.Type.CHESTPLATE, new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(37))));
    public static final Item ICE_ETHER_LEGGINGS = registerItems("ice_ether_leggings", new ArmorItem(ModArmorMaterials.ICE_ETHER,
                    ArmorItem.Type.LEGGINGS, new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(37))));
    public static final Item ICE_ETHER_BOOTS = registerItems("ice_ether_boots", new ArmorItem(ModArmorMaterials.ICE_ETHER,
                    ArmorItem.Type.BOOTS, new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(37))));
    public static final Item CORN = registerItems("corn", new Item(new Item.Settings().food(ModFoodComponents.CORN)));

    public static final Item HAT = registerItems("hat", new Hat(Hat.Type.HAT, new Item.Settings()));

    public static final Item STRAWBERRY_SEEDS = registerItems("strawberry_seeds", new AliasedBlockItem(ModBlocks.STRAWBERRY_CROP,
            new Item.Settings()));
    public static final Item CORN_SEEDS = registerItems("corn_seeds", new AliasedBlockItem(ModBlocks.CORN_CROP,
            new Item.Settings()));
    public static final Item TEST = registerItems("test", new Item(new Item.Settings()));
    public static final Item TEST_MUSIC_DISC = registerItems("test_music_disc",
            new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).jukeboxPlayable(ModJukeboxSongs.TEST)));
    public static final Item OIL_BUCKET = registerItems("oil_bucket", new BucketItem(
            ModFluids.OIL, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item ICE_ETHER_HORSE_ARMOR = registerItems("ice_ether_horse_armor",
            new AnimalArmorItem(ModArmorMaterials.ICE_ETHER, AnimalArmorItem.Type.EQUESTRIAN,false,
                    new Item.Settings().maxCount(1)));
    public static final Item ICE_ETHER_WOLF_ARMOR = registerItems("ice_ether_wolf_armor",
            new AnimalArmorItem(ModArmorMaterials.ICE_ETHER, AnimalArmorItem.Type.CANINE, true,
                    new Item.Settings().maxDamage(ArmorItem.Type.BODY.getMaxDamage(4))));

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
