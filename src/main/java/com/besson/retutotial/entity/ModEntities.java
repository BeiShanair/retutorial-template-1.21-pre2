package com.besson.retutotial.entity;

import com.besson.retutotial.ReTutorial;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final RegistryKey<EntityType<?>> TIGER_KEY = registryKey("tiger");

//    public static final EntityType<TigerEntity> TIGER = Registry.register(Registries.ENTITY_TYPE,
//            Identifier.of(ReTutorial.MOD_ID,"tiger"),
//            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, TigerEntity::new)
//                    .dimensions(EntityDimensions.fixed(1f,1f)).build(TIGER_KEY));

    private static RegistryKey<EntityType<?>> registryKey(String id) {
        return RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(ReTutorial.MOD_ID, id));
    }
}
