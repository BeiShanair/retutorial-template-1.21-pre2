package com.besson.retutotial.datagen;

import com.besson.retutotial.item.ModItems;
import com.besson.retutotial.tags.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagsProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        // 同样我们可以使用getOrCreateTagBuilder方法来创建标签
        getOrCreateTagBuilder(ModItemTags.SUGAR_TAG)
                .add(Items.BEETROOT)
                .add(ModItems.STRAWBERRY)
                .add(ModItems.CHEESE);
        // 如果要让盔甲可以锻造，我们需要将盔甲的材料加入到标签中
        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.ICE_ETHER_HELMET, ModItems.ICE_ETHER_CHESTPLATE, ModItems.ICE_ETHER_LEGGINGS, ModItems.ICE_ETHER_BOOTS);
    }
}
