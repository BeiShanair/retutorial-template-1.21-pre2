package com.besson.retutotial.item;

import com.besson.retutotial.ReTutorial;
import com.besson.retutotial.block.ModBlocks;
import com.besson.retutotial.block.ModFluids;
import com.besson.retutotial.item.custom.FireEther;
import com.besson.retutotial.item.custom.Hat;
import com.besson.retutotial.item.custom.Prospector;
import com.besson.retutotial.sounds.ModJukeboxSongs;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.function.Function;

public class ModItems {
    // 注册物品
    public static final Item ICE_ETHER = registerItems("ice_ether", new Item.Settings());
    public static final Item RAW_ICE_ETHER = registerItems("raw_ice_ether", new Item.Settings());
    public static final Item STRAWBERRY = registerItems("strawberry", new Item.Settings().food(ModFoodComponents.STRAWBERRY, ModConsumableComponents.STRAWBERRY));
    public static final Item CHEESE = registerItems("cheese", new Item.Settings().food(ModFoodComponents.CHEESE));
    public static final Item ANTHRACITE = registerItems("anthracite", new Item.Settings());
    public static final Item PROSPECTOR = register("prospector", settings -> new Prospector(new Item.Settings().maxDamage(127)));
    public static final Item PLATE = registerItems("plate", new Item.Settings());
    public static final Item FIRE_ETHER = register("fire_ether", settings -> new FireEther(new Item.Settings()));
    public static final Item FIRE_ETHER_SWORD = register("fire_ether_sword", settings -> new SwordItem(ModToolMaterials.FIRE_ETHER, 3.0f, -2.4f, settings.fireproof()));
    public static final Item FIRE_ETHER_SHOVEL = register("fire_ether_shovel", settings -> new ShovelItem(ModToolMaterials.FIRE_ETHER, 1.5f, -3.0f, settings.fireproof()));
    public static final Item FIRE_ETHER_PICKAXE = register("fire_ether_pickaxe", settings -> new PickaxeItem(ModToolMaterials.FIRE_ETHER, 1, -2.8f, settings.fireproof()));
    public static final Item FIRE_ETHER_AXE = register("fire_ether_axe", settings -> new AxeItem(ModToolMaterials.FIRE_ETHER, 6.0f, -3.0f, settings.fireproof()));
    public static final Item FIRE_ETHER_HOE = register("fire_ether_hoe", settings -> new HoeItem(ModToolMaterials.FIRE_ETHER, -3, 0.0f, settings.fireproof()));

    public static final Item ICE_ETHER_HELMET = register("ice_ether_helmet", settings -> new ArmorItem(ModArmorMaterials.ICE_ETHER,
                    EquipmentType.HELMET,settings));
    public static final Item ICE_ETHER_CHESTPLATE = register("ice_ether_chestplate", settings -> new ArmorItem(ModArmorMaterials.ICE_ETHER,
                    EquipmentType.CHESTPLATE, settings));
    public static final Item ICE_ETHER_LEGGINGS = register("ice_ether_leggings", settings -> new ArmorItem(ModArmorMaterials.ICE_ETHER,
                    EquipmentType.LEGGINGS, settings));
    public static final Item ICE_ETHER_BOOTS = register("ice_ether_boots", settings -> new ArmorItem(ModArmorMaterials.ICE_ETHER,
                    EquipmentType.BOOTS, settings));

    public static final Item CORN = registerItems("corn", new Item.Settings().food(ModFoodComponents.CORN));

    public static final Item HAT = register("hat", settings -> new Hat(EquipmentType.HELMET, new Item.Settings()));

    public static final Item STRAWBERRY_SEEDS = register("strawberry_seeds", createBlockItemWithUniqueName(ModBlocks.STRAWBERRY_CROP));
    public static final Item CORN_SEEDS = register("corn_seeds", createBlockItemWithUniqueName(ModBlocks.CORN_CROP));

    public static final Item TEST_MUSIC_DISC = registerItems("test_music_disc",
            new Item.Settings().maxCount(1).rarity(Rarity.RARE).jukeboxPlayable(ModJukeboxSongs.TEST));

    public static final Item OIL_BUCKET = register("oil_bucket", settings -> new BucketItem(
            ModFluids.OIL, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));

    public static final Item ICE_ETHER_HORSE_ARMOR = register("ice_ether_horse_armor",
            settings -> new AnimalArmorItem(ModArmorMaterials.ICE_ETHER, AnimalArmorItem.Type.EQUESTRIAN, SoundEvents.ENTITY_HORSE_ARMOR,false,
                    new Item.Settings().maxCount(1)));

//    public static final Item ICE_ETHER_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.ICE_ETHER_BOAT, ModBoats.ICE_ETHER_BOAT_KEY, false);
//    public static final Item ICE_ETHER_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.ICE_ETHER_CHEST_BOAT, ModBoats.ICE_ETHER_BOAT_KEY, true);

    public static final Item SIMPLE_ITEM = registerItems("simple_item", new Item.Settings());

    private static RegistryKey<Item> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(ReTutorial.MOD_ID, id));
    }

    private static Function<Item.Settings, Item> createBlockItemWithUniqueName(Block block) {
        return settings -> new BlockItem(block, settings.useItemPrefixedTranslationKey());
    }

    public static Item registerItems(String id, Item.Settings settings) {
        return register(keyOf(id), Item::new, settings);
    }

    public static Item register(String id, Function<Item.Settings, Item> factory) {
        return register(keyOf(id), factory, new Item.Settings());
    }

    public static Item register(RegistryKey<Item> key, Function<Item.Settings, Item> factory, Item.Settings settings) {
        Item item = (Item)factory.apply(settings.registryKey(key));

        return Registry.register(Registries.ITEM, key, item);
    }
    // 初始化方法
    public static void registerModItems() {
        ReTutorial.LOGGER.info("Registering Items");
    }
}
