package com.github.syren_dev_tech.scylla.mobs.client;

import com.github.syren_dev_tech.hydra.cache.object.BakedHydraModel;
import com.github.syren_dev_tech.hydra.renderer.HydraEntityRenderer;
import com.github.syren_dev_tech.hydra.renderer.HydraRenderer;
import com.github.syren_dev_tech.hydra.renderer.layer.HydraRenderLayer;
import com.github.syren_dev_tech.scylla.mobs.CreatureBuilder;
import com.github.syren_dev_tech.scylla.mobs.creatures.CreatureState;
import com.github.syren_dev_tech.scylla.mobs.creatures.CustomCreature;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class CustomCreatureRenderer<T extends CustomCreature> extends HydraEntityRenderer<T> {

    private final CreatureBuilder<T> builder;

    public CustomCreatureRenderer(CreatureBuilder<T> builder, EntityRendererProvider.Context context) {
        super(context, new CustomCreatureModel<>(builder));
        this.shadowRadius = builder.getShadowSize(); // Set shadow size
        this.builder = builder;

        this.addRenderLayer(new MyColorLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        CreatureState<T> state = getTypedState(pEntity);
        TextureDefinition definition = this.builder.getTextureForState(state);
        return definition.getTexture().get();
    }

    @SuppressWarnings("unchecked")
    private CreatureState<T> getTypedState(T animatable) {
        return (CreatureState<T>) animatable.getState();
    }

    public class MyColorLayer extends HydraRenderLayer<T> {

        public MyColorLayer(HydraRenderer<T> entityRendererIn) {
            super(entityRendererIn);
        }

        @Override
        public void render(PoseStack poseStack, T animatable, BakedHydraModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
            CreatureState<T> state = getTypedState(animatable);
            TextureDefinition definition = builder.getTextureForState(state);
            if (definition.getMask() != null) {
                ResourceLocation maskResource = definition.getMask().get();
                var layerRenderType = RenderType.entityCutoutNoCull(maskResource);
                var layerBuffer = bufferSource.getBuffer(layerRenderType);
                int white = rgbaToArgb(CreatureBuilder.rgba(255, 255, 255, 255));

                getRenderer().reRender(model, poseStack, bufferSource, animatable, layerRenderType, layerBuffer, partialTick, packedLight, packedOverlay, white);
            }

            for (var maskLayer : builder.getMaskLayers()) {
                var mask = maskLayer.getMask().apply(state);
                ResourceLocation maskResource = mask == null ? null : mask.get();

                if (maskResource != null) {
                    var layerRenderType = RenderType.entityCutoutNoCull(maskResource);
                    var layerBuffer = bufferSource.getBuffer(layerRenderType);
                    int rgba = rgbaToArgb(maskLayer.getColor().apply(state));

                    getRenderer().reRender(model, poseStack, bufferSource, animatable, layerRenderType, layerBuffer, partialTick, packedLight, packedOverlay, rgba);
                }
            }

            for (var tintLayer : builder.getNbtMaskTintLayers()) {
                ResourceLocation maskResource = tintLayer.getMask().get();
                if (maskResource == null) {
                    continue;
                }

                int rgba = this.resolveTintColor(animatable, tintLayer);
                var layerRenderType = RenderType.entityCutoutNoCull(maskResource);
                var layerBuffer = bufferSource.getBuffer(layerRenderType);

                getRenderer().reRender(model, poseStack, bufferSource, animatable, layerRenderType, layerBuffer, partialTick, packedLight, packedOverlay, rgbaToArgb(rgba));
            }
        }

        private int rgbaToArgb(int rgba) {
            int red = (rgba >> 24) & 0xFF;
            int green = (rgba >> 16) & 0xFF;
            int blue = (rgba >> 8) & 0xFF;
            int alpha = rgba & 0xFF;

            return (alpha << 24) | (red << 16) | (green << 8) | blue;
        }

        private int resolveTintColor(T animatable, CreatureBuilder.NbtMaskTintLayer tintLayer) {
            return switch (tintLayer.getMode()) {
                case RANGE -> CustomNbtTintUtil.tintFromRange(animatable.getCustomNbtInt(tintLayer.getFieldPath(), tintLayer.getMinValue()), tintLayer.getMinValue(), tintLayer.getMaxValue(), tintLayer.getMinColor(), tintLayer.getMaxColor());
                case BOOLEAN -> CustomNbtTintUtil.tintFromBoolean(animatable.getCustomNbtBoolean(tintLayer.getFieldPath(), false), tintLayer.getMinColor(), tintLayer.getMaxColor());
            };
        }
    }
}
