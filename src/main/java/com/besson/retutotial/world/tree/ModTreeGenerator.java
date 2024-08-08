package com.besson.retutotial.world.tree;

import com.besson.retutotial.ReTutorial;
import com.besson.retutotial.world.ModConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class ModTreeGenerator {
    public static final SaplingGenerator ICE_ETHER_TREE = new SaplingGenerator(
            ReTutorial.MOD_ID + ":ice_ether_tree",
            Optional.empty(),
            Optional.of(ModConfiguredFeatures.ICE_ETHER_TREE_KEY),
            Optional.empty());
}
