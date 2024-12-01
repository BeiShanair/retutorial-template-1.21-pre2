package com.besson.retutotial;

import com.besson.retutotial.block.ModBlocks;
import com.besson.retutotial.block.ModFluids;
import com.besson.retutotial.particle.ModParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

public class ReTutorialClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ICE_ETHER_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ICE_ETHER_TRAPDOOR,RenderLayer.getCutout());
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.OIL, ModFluids.FLOWING_OIL,
                new SimpleFluidRenderHandler(
                        Identifier.of("minecraft:block/water_still"),
                        Identifier.of("minecraft:block/water_flow"),
                        0x42413b
                ));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.OIL, ModFluids.FLOWING_OIL);

//        // 注册屏幕
//        HandledScreens.register(ModScreenHandlers.POLISHING_MACHINE_SCREEN_HANDLER, PolishingMachineScreen::new);

//        TerraformBoatClientHelper.registerModelLayers(ModBoats.ICE_ETHER_BOAT, false);

//        // 注册生物实体的模型层和渲染器
//        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.TIGER, TigerModel::getTexturedModelData);
//        EntityRendererRegistry.register(ModEntities.TIGER, TigerRenderer::new);

        ParticleFactoryRegistry.getInstance().register(ModParticles.GREEN_FLAME, FlameParticle.Factory::new);
    }
}
