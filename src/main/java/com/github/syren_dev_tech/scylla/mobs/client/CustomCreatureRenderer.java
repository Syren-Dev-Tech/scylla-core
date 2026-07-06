package com.github.syren_dev_tech.scylla.mobs.client;

import com.github.syren_dev_tech.scylla.mobs.CreatureBuilder;
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

        if (builder.getTextureRenderer() != null)
            this.addRenderLayer(builder.getTextureRenderer());
        if (builder.getMasks() != null)
            this.addRenderLayer(new MyColorLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        return builder.getTextures().apply(pEntity.getState()).get();
    }

    public class MyColorLayer extends GeoRenderLayer<T> {

        public MyColorLayer(GeoRenderer<T> entityRendererIn) {
            super(entityRendererIn);
        }

        @Override
        public void render(PoseStack poseStack, T animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
            int red = 0;
            int green = 255;
            int blue = 0;
            int alpha = 255;

            var mask = builder.getMasks().apply(animatable.getState());

            var renderType2 = RenderType.entityCutoutNoCull(mask.get());
            var buffer2 = bufferSource.getBuffer(RenderType.entityCutoutNoCull(mask.get()));
            var rgb = (red << 24) + (green << 16) + (blue << 8) + (alpha);

            getRenderer().reRender(model, poseStack, bufferSource, animatable, renderType2, buffer2, partialTick, packedLight, packedOverlay, rgb);
        }
    }
}
