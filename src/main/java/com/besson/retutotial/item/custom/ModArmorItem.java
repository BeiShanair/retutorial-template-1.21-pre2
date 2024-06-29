package com.besson.retutotial.item.custom;

import com.besson.retutotial.item.ModArmorMaterials;
import com.google.common.collect.ImmutableMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;

import java.util.Map;

public class ModArmorItem extends ArmorItem {
    // Map存放ArmorMaterial和StatusEffectInstance的对应关系
    // 使用ImmutableMap.Builder创建一个不可变的Map
    // put方法添加ArmorMaterial和StatusEffectInstance的对应关系
    // StatusEffectInstance的构造方法参数分别为：效果类型，持续时间，效果等级，？，是否显示粒子，是否显示图标
    private static final Map<ArmorMaterial, StatusEffectInstance> MAP =
            (new ImmutableMap.Builder<ArmorMaterial, StatusEffectInstance>())
                    .put(ModArmorMaterials.ICE_ETHER.value(), new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 1000, 1
                            , false, false, true)).build();

    public ModArmorItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        // 如果不是客户端，且实体是玩家，且玩家穿戴了全套盔甲
        if (!world.isClient() && entity instanceof PlayerEntity player && hasFullSuitOfArmor(player)) {
            // 给玩家添加盔甲效果
            evaluateArmorEffects(player);
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    // 为玩家添加盔甲效果
    private void evaluateArmorEffects(PlayerEntity player) {
        for (Map.Entry<ArmorMaterial, StatusEffectInstance> entry : MAP.entrySet()) {
            ArmorMaterial material = entry.getKey();
            StatusEffectInstance effect = entry.getValue();

            // 如果玩家穿戴了指定材质的盔甲
            if (hasCorrectArmorOn(material, player)) {
                addStatusEffectForMaterial(player, material, effect);
            }
        }
    }

    private void addStatusEffectForMaterial(PlayerEntity player, ArmorMaterial material, StatusEffectInstance effect) {
        boolean hasEffect = player.hasStatusEffect(effect.getEffectType());

        // 如果玩家穿戴了指定材质的盔甲，且玩家没有指定效果
        if (!hasEffect) {
            player.addStatusEffect(new StatusEffectInstance(effect));
        }
    }

    // 玩家是否穿戴了指定材质的盔甲
    private boolean hasCorrectArmorOn(ArmorMaterial material, PlayerEntity player) {
        for (ItemStack armorStack : player.getInventory().armor) {
            if (!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }

        ArmorItem helmet = (ArmorItem) player.getInventory().getArmorStack(3).getItem();
        ArmorItem chestplate = (ArmorItem) player.getInventory().getArmorStack(2).getItem();
        ArmorItem leggings = (ArmorItem) player.getInventory().getArmorStack(1).getItem();
        ArmorItem boots = (ArmorItem) player.getInventory().getArmorStack(0).getItem();

        return helmet.getMaterial().value() == material
                && chestplate.getMaterial().value() == material
                && leggings.getMaterial().value() == material
                && boots.getMaterial().value() == material;
    }

    // 玩家是否穿戴了全套盔甲
    private boolean hasFullSuitOfArmor(PlayerEntity player) {
        ItemStack helmet = player.getInventory().getArmorStack(3);
        ItemStack chestplate = player.getInventory().getArmorStack(2);
        ItemStack leggings = player.getInventory().getArmorStack(1);
        ItemStack boots = player.getInventory().getArmorStack(0);

        return !helmet.isEmpty() && !chestplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }
}
