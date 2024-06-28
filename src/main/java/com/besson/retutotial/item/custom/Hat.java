package com.besson.retutotial.item.custom;

import com.mojang.serialization.Codec;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Equipment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Hat extends Item implements Equipment {
    // 一个奇怪的实现，可以做头饰
    protected final Type type;
    public Hat(Type type, Settings settings) {
        super(settings);
        this.type = type;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return this.equipAndSwap(this, world, user, hand);
    }

    @Override
    public EquipmentSlot getSlotType() {
        return this.type.getEquipmentSlot();
    }
    public static enum Type implements StringIdentifiable
    {
        HAT(EquipmentSlot.HEAD, 11, "helmet");

        public static final Codec<ArmorItem.Type> CODEC;
        private final EquipmentSlot equipmentSlot;
        private final String name;
        private final int baseMaxDamage;

        private Type(EquipmentSlot equipmentSlot, int baseMaxDamage, String name) {
            this.equipmentSlot = equipmentSlot;
            this.name = name;
            this.baseMaxDamage = baseMaxDamage;
        }

        public int getMaxDamage(int multiplier) {
            return this.baseMaxDamage * multiplier;
        }

        public EquipmentSlot getEquipmentSlot() {
            return this.equipmentSlot;
        }

        public String getName() {
            return this.name;
        }

        public boolean isTrimmable() {
            return false;
        }

        @Override
        public String asString() {
            return this.name;
        }

        static {
            CODEC = StringIdentifiable.createBasicCodec(ArmorItem.Type::values);
        }
    }
}
