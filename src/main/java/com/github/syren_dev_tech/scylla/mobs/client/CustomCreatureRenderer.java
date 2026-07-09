package com.github.syren_dev_tech.scylla.mobs.client;

import com.github.syren_dev_tech.scylla.mobs.CreatureBuilder;
import com.github.syren_dev_tech.scylla.mobs.creatures.CreatureState;
import com.github.syren_dev_tech.scylla.mobs.creatures.CustomCreature;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class CustomCreatureRenderer<T extends CustomCreature> extends GeoEntityRenderer<T> {

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

    public class MyColorLayer extends GeoRenderLayer<T> {

        public MyColorLayer(GeoRenderer<T> entityRendererIn) {
            super(entityRendererIn);
        }

        @Override
        public void render(PoseStack poseStack, T animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
            CreatureState<T> state = getTypedState(animatable);
            TextureDefinition definition = builder.getTextureForState(state);
            if (definition.getMask() != null) {
                ResourceLocation maskResource = definition.getMask().get();
                var layerRenderType = RenderType.entityCutoutNoCull(maskResource);
                var layerBuffer = bufferSource.getBuffer(layerRenderType);
                int white = CreatureBuilder.rgba(255, 255, 255, 255);

                getRenderer().reRender(model, poseStack, bufferSource, animatable, layerRenderType, layerBuffer, partialTick, packedLight, packedOverlay, white);
            }

            for (var maskLayer : builder.getMaskLayers()) {
                var mask = maskLayer.getMask().apply(state);
                ResourceLocation maskResource = mask == null ? null : mask.get();

                if (maskResource != null) {
                    var layerRenderType = RenderType.entityCutoutNoCull(maskResource);
                    var layerBuffer = bufferSource.getBuffer(layerRenderType);
                    int rgba = maskLayer.getColor().apply(state);

                    getRenderer().reRender(model, poseStack, bufferSource, animatable, layerRenderType, layerBuffer, partialTick, packedLight, packedOverlay, rgba);
                }
            }
        }
    }
}
