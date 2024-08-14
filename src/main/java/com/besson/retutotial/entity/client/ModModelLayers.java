package com.besson.retutotial.entity.client;

import com.besson.retutotial.ReTutorial;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    // 为生物实体注册模型层，可参考EntityModelLayers
    public static final EntityModelLayer TIGER =
            new EntityModelLayer(Identifier.of(ReTutorial.MOD_ID,"tiger"),"main");
}
