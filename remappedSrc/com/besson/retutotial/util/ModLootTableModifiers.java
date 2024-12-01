package com.besson.retutotial.util;

import com.besson.retutotial.item.ModItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class ModLootTableModifiers {
    // 修改战利品列表，使用Fabric的API
    // 首先获取到对应的战利品表的ID
    private static final Identifier JUNGLE_TEMPLE_ID =
            Identifier.ofVanilla("chests/jungle_temple");
    private static final Identifier CREEPER_ID =
            Identifier.ofVanilla("entities/creeper");

    // 初始化方法
    // 直接在这个方法中注册事件
    public static void modifyLootTables(){
        // 一般使用LootTableEvents.MODIFY.register()方法
        // REPLACE并不建议使用，而且在1.21版本中，并不太好写，需要配合mixin
        LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {
            if (JUNGLE_TEMPLE_ID.equals(key.getValue())){
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(1.0f))
                        .with(ItemEntry.builder(ModItems.PROSPECTOR))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

                tableBuilder.pool(poolBuilder.build());
            }
            if (CREEPER_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(1.0f))
                        .with(ItemEntry.builder(ModItems.ANTHRACITE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 5.0f)));

                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
}
