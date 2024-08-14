package com.besson.retutotial.entity;

import com.besson.retutotial.ReTutorial;
import com.besson.retutotial.entity.custom.TigerEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    // 注册实体，因为fabric的注册方法弃用了（虽然并不影响使用），我们采用原版的方法注册
    // 注意在create中，和fabric的参数是对调的
    // 以及在dimensions中，直接填写数字即可
    public static final EntityType<TigerEntity> TIGER = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(ReTutorial.MOD_ID,"tiger"),
            EntityType.Builder.create(TigerEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1f, 1f).build());
}
