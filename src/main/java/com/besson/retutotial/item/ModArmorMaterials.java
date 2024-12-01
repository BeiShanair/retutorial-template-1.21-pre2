package com.besson.retutotial.item;

import com.besson.retutotial.ReTutorial;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;

public interface ModArmorMaterials {
    ArmorMaterial ICE_ETHER = new ArmorMaterial(37, Util.make(new EnumMap(EquipmentType.class), map -> {
        map.put(EquipmentType.BOOTS, 3);
        map.put(EquipmentType.LEGGINGS, 6);
        map.put(EquipmentType.CHESTPLATE, 8);
        map.put(EquipmentType.HELMET, 3);
        map.put(EquipmentType.BODY, 11);
    }), 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.0f, 0.1f, ItemTags.REPAIRS_NETHERITE_ARMOR, Identifier.of(ReTutorial.MOD_ID, "ice_ether"));
}
