package com.besson.retutotial.mixin;

import com.besson.retutotial.ReTutorial;
import com.besson.retutotial.item.ModItems;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public class ItemRenderMixin {
    // @ModifyVariable修改方法参数，这里修改了ItemRenderer的renderItem方法中的model参数
    // 注意，方法的返回值类型应当与我们方法的第一个参数类型一致，其余参数往后
    // @ModifyVariable的at属性指定修改的位置，argsOnly属性指定是否只修改方法参数
    @ModifyVariable(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V", at = @At("HEAD"), argsOnly = true)
    public BakedModel usePlateModel(BakedModel model, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        // 这里我们希望plate的2D模型只在GUI中显示（也就是物品栏这种），其他情况下使用3D模型
        // 所以我们根据renderMode来判断是否是GUI，如果是GUI则返回plate的2D模型，否则返回plate的3D模型
        boolean bl = renderMode != ModelTransformationMode.GUI;
        if (bl) {
            // 如果物品堆栈是plate，则返回plate的3D模型
            // 类似的写法请查阅目标方法中的望远镜和三叉戟
            if (stack.isOf(ModItems.PLATE)) {
                return ((ItemRenderAccessor) this).getModels().getModelManager().getModel(
                        ModelIdentifier.ofInventoryVariant(Identifier.of(ReTutorial.MOD_ID, "plate_3d")));
            }
        }
        return model;
    }
}
