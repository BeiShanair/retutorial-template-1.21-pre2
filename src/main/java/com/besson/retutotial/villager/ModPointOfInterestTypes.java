package com.besson.retutotial.villager;

import com.besson.retutotial.ReTutorial;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.poi.PointOfInterestType;

public class ModPointOfInterestTypes {
    // 注册冰之魔法大师的工作站
    public static final RegistryKey<PointOfInterestType> ICE_ETHER_KEY = of("ice_ether_poi");
    // 注册方法
    private static RegistryKey<PointOfInterestType> of(String id) {
        return RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, Identifier.of(ReTutorial.MOD_ID, id));
    }
    // 保险起见可以写个初始化的方法
    // 当然可以不写，因为ModVillagers类中的ICE_ETHER_MASTER注册时会调用这里的常量，此时它将完成初始化
}
