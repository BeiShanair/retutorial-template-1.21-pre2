package com.besson.retutotial.world.biome;

import com.besson.retutotial.ReTutorial;
import com.besson.retutotial.world.biome.surface.ModMaterialRules;
import net.minecraft.util.Identifier;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class ModTerraBlenderAPI implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new ModOverworldRegion(Identifier.of(ReTutorial.MOD_ID,"overworld"),4));
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, ReTutorial.MOD_ID, ModMaterialRules.makeRules());
    }
}
