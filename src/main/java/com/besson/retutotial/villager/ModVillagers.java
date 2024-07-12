package com.besson.retutotial.villager;

import com.besson.retutotial.ReTutorial;
import com.besson.retutotial.block.ModBlocks;
import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import org.jetbrains.annotations.Nullable;

public class ModVillagers {
    // 注册冰之魔法大师（村民）
    // 注册村民职业的时候，需要传入一个PointOfInterestType类型的参数，这个参数是村民的工作站
    // 这里指定了村民的工作站是ICE_ETHER_MASTER
    public static final VillagerProfession ICE_ETHER_MASTER = register("ice_ether_master",
            ModPointOfInterestTypes.ICE_ETHER_KEY, SoundEvents.ENTITY_VILLAGER_WORK_ARMORER);

    // 注册和工作站绑定的方块
    // 注意，注册名等于工作站名（可查看PointOfInterestTypes类）
    public static final PointOfInterestType ICE_ETHER_POI = registerPointOfInterestType("ice_ether_poi", ModBlocks.ICE_ETHER_BLOCK);

    // 村民职业注册，由原版的方法整合
    private static VillagerProfession register(String id, RegistryKey<PointOfInterestType> heldWorkstation, @Nullable SoundEvent workSound) {
        return Registry.register(Registries.VILLAGER_PROFESSION, Identifier.of(ReTutorial.MOD_ID, id),
                new VillagerProfession(id, entry -> entry.matchesKey(heldWorkstation), entry -> entry.matchesKey(heldWorkstation),
                        ImmutableSet.of(), ImmutableSet.of(), workSound));
    }
    // 注册和工作站绑定的方块
    // 在原版中的PointOfInterestTypes类，我们可以发现他们也写了相关的内容
    // 然而，它使用的register方法是私有方法，且调用了其他的方法，所以我们无法直接调用（当然，你也可以使用mixin）
    // 但我们可以借助PointOfInterestHelper类来注册（Fabric API）
    private static PointOfInterestType registerPointOfInterestType(String name, Block block){
        // 在这里，我们要设置一些参数
        // tickCount是这个工作站最大能容纳的村民数量，通常是1
        // searchDistance是村民寻找这个工作站的最大距离，通常是1（单位不是方块，暂时不知具体的值）
        return PointOfInterestHelper.register(Identifier.of(ReTutorial.MOD_ID,name), 1, 1, block);
    }
    // 初始化
    public static void registerVillagers() {
        ReTutorial.LOGGER.info("Registering Villagers");
    }
}
