package com.github.syren_dev_tech.scylla.items.combat.types;

import com.github.syren_dev_tech.hydra.animatable.HydraItem;
import com.github.syren_dev_tech.hydra.animatable.instance.AnimatableInstanceCache;
import com.github.syren_dev_tech.hydra.animation.AnimatableManager.ControllerRegistrar;
import com.github.syren_dev_tech.hydra.util.HydraUtil;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;

public class ArmourPiece extends ArmorItem implements HydraItem {
    private final AnimatableInstanceCache cache = HydraUtil.createInstanceCache(this);
    private final ArmourModelConfig modelConfig;

    public ArmourPiece(Holder<ArmorMaterial> material, Type type, Properties properties) {
        this(material, type, properties, ArmourModelConfig.none());
    }

    public ArmourPiece(Holder<ArmorMaterial> material, Type type, Properties properties, ArmourModelConfig modelConfig) {
        super(material, type, properties);
        this.modelConfig = modelConfig;
    }

    @Override
    public void registerControllers(ControllerRegistrar controllers) {
        // Unimplemented
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public ArmourModelConfig modelConfig() {
        return this.modelConfig;
    }

    public boolean shouldRenderCustomModelWhenWorn() {
        return this.modelConfig.enabled();
    }
}
