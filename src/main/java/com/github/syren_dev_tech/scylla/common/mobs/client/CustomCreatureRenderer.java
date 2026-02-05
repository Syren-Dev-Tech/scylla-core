package com.github.syren_dev_tech.scylla.common.mobs.client;

import com.github.syren_dev_tech.scylla.common.mobs.CreatureBuilder;
import com.github.syren_dev_tech.scylla.common.mobs.creatures.CustomCreature;
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

        if (builder.getTextureRenderer() != null)
            this.addRenderLayer(builder.getTextureRenderer());
        if (builder.getMasks() != null)
            this.addRenderLayer(new MyColorLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        return builder.getTextures().apply(pEntity.getState());
    }

    public class MyColorLayer extends GeoRenderLayer<T> {

        public MyColorLayer(GeoRenderer<T> entityRendererIn) {
            super(entityRendererIn);
        }

        @Override
        public void render(PoseStack poseStack, T animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
            float red = 0.0f;
            float green = 1.0f;
            float blue = 0.0f;

            var mask = builder.getMasks().apply(animatable.getState());

            getRenderer().reRender(model, poseStack, bufferSource, animatable, RenderType.entityCutoutNoCull(mask), bufferSource.getBuffer(RenderType.entityCutoutNoCull(mask)), partialTick, packedLight, packedOverlay, red, green, blue, 1.0f);
        }
    }
}
