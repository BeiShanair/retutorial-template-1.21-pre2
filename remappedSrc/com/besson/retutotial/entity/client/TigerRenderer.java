package com.besson.retutotial.entity.client;

import com.besson.retutotial.ReTutorial;
import com.besson.retutotial.entity.custom.TigerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class TigerRenderer extends MobEntityRenderer<TigerEntity, TigerModel<TigerEntity>> {
    // 设置实体的材质
    private static final Identifier TEXTURE =Identifier.of(ReTutorial.MOD_ID,"textures/entity/tiger.png");
    public TigerRenderer(EntityRendererFactory.Context context) {
        // 构造方法中只留一个参数
        // 另外两个参数我们直接设置
        // 第二个参数为模型，第三个参数为实体阴影大小
        // 模型我们直接传入TigerModel，并且通过context.getPart方法获取模型层
        super(context, new TigerModel<>(context.getPart(ModModelLayers.TIGER)), 0.5f);
    }

    @Override
    public Identifier getTexture(TigerEntity entity) {
        return TEXTURE;
    }

    // 重写render方法，判断实体是否为幼年，如果是则缩小0.5倍
    @Override
    public void render(TigerEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if (livingEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
