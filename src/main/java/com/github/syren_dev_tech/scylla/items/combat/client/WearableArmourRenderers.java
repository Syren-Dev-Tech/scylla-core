package com.github.syren_dev_tech.scylla.items.combat.client;

import java.util.function.Consumer;
import com.github.syren_dev_tech.hydra.animatable.client.HydraRenderProvider;
import com.github.syren_dev_tech.hydra.model.HydraModel;
import com.github.syren_dev_tech.hydra.renderer.HydraArmorRenderer;
import com.github.syren_dev_tech.scylla.items.combat.types.ArmourPiece;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public final class WearableArmourRenderers {

    private WearableArmourRenderers() {}

    public static void createProvider(ArmourPiece armourPiece, Consumer<HydraRenderProvider> consumer) {
        consumer.accept(new HydraRenderProvider() {
            private HydraArmorRenderer<ArmourPiece> renderer;

            @Override
            public <T extends LivingEntity> HumanoidModel<?> getHydraArmorRenderer(T livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<T> original) {
                if (!armourPiece.shouldRenderCustomModelWhenWorn()) {
                    return original;
                }

                if (this.renderer == null) {
                    this.renderer = new HydraArmorRenderer<>(new ConfiguredArmourModel());
                }

                var minecraft = Minecraft.getInstance();
                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original, minecraft.renderBuffers().bufferSource(), 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
                return this.renderer;
            }
        });
    }

    private static final class ConfiguredArmourModel extends HydraModel<ArmourPiece> {

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
