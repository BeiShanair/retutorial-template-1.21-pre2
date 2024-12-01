package com.besson.retutotial.world.dimension;

import com.besson.retutotial.ReTutorial;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;

import java.util.OptionalLong;

public class ModDimensions {
    public static final RegistryKey<DimensionOptions> TEST_KEY = RegistryKey.of(RegistryKeys.DIMENSION,
            Identifier.of(ReTutorial.MOD_ID, "test"));
    public static final RegistryKey<World> TEST_LEVEL_KEY = RegistryKey.of(RegistryKeys.WORLD,
            Identifier.of(ReTutorial.MOD_ID, "test"));
    public static final RegistryKey<DimensionType> TEST_DIMENSION_TYPE_KEY = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            Identifier.of(ReTutorial.MOD_ID, "test_type"));

    public static void bootstrap(Registerable<DimensionType> context) {
        context.register(TEST_DIMENSION_TYPE_KEY, new DimensionType(
                OptionalLong.of(12000),
                false,
                false,
                false,
                true,
                1.0,
                true,
                false,
                0,
                256,
                256,
                BlockTags.INFINIBURN_OVERWORLD,
                DimensionTypes.OVERWORLD_ID,
                1.0F,
                new DimensionType.MonsterSettings(false,false, UniformIntProvider.create(0, 0), 0)));
    }
}
