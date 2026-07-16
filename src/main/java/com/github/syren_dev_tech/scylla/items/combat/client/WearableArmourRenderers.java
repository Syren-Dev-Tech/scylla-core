package com.github.syren_dev_tech.scylla.items.combat.client;

import java.util.function.Consumer;
import com.github.syren_dev_tech.scylla.items.combat.types.ArmourPiece;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public final class WearableArmourRenderers {

    private WearableArmourRenderers() {}

    public static void createProvider(ArmourPiece armourPiece, Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private GeoArmorRenderer<ArmourPiece> renderer;

            @Override
            public <T extends LivingEntity> HumanoidModel<?> getGeoArmorRenderer(T livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<T> original) {
                if (!armourPiece.shouldRenderCustomModelWhenWorn()) {
                    return original;
                }

                if (this.renderer == null) {
                    this.renderer = new GeoArmorRenderer<>(new ConfiguredArmourModel());
                }

                var minecraft = Minecraft.getInstance();
                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original, minecraft.renderBuffers().bufferSource(), 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
                return this.renderer;
            }
        });
    }

    private static final class ConfiguredArmourModel extends GeoModel<ArmourPiece> {

        @Override
        public ResourceLocation getModelResource(ArmourPiece animatable) {
            return animatable.modelConfig().modelResource();
        }

        @Override
        public ResourceLocation getTextureResource(ArmourPiece animatable) {
            return animatable.modelConfig().textureResource();
        }

        @Override
        public ResourceLocation getAnimationResource(ArmourPiece animatable) {
            return animatable.modelConfig().animationResource();
        }
    }
}
