package com.besson.retutotial.tags;

import com.besson.retutotial.ReTutorial;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModItemTags {
    // 注册物品标签
    public static final TagKey<Item> SUGAR_TAG = of("sugar_tag");
    private static TagKey<Item> of(String id) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(ReTutorial.MOD_ID, id));
    }
    // 初始化方法
    public static void registerModItemTags(){
        ReTutorial.LOGGER.info("Registering Item Tags");
    }
}
