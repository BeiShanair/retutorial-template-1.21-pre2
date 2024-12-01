package com.besson.retutotial.item.custom;

import com.besson.retutotial.ReTutorial;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class Hat extends Item {
    // 一个奇怪的实现，可以做头饰

    public Hat(EquipmentType type, Settings settings) {
        super(settings.component(
                DataComponentTypes.EQUIPPABLE,
                EquippableComponent.builder(type.getEquipmentSlot())
                        .equipSound(SoundEvents.ITEM_ARMOR_EQUIP_CHAIN)
                        .model(Identifier.of(ReTutorial.MOD_ID, "hat")).build()
        ));
    }
}
