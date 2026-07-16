package com.github.syren_dev_tech.scylla.items.combat.types;

import java.util.function.Consumer;
import com.github.syren_dev_tech.scylla.items.combat.client.WearableArmourRenderers;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ArmourPiece extends ArmorItem implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final ArmourModelConfig modelConfig;

    public ArmourPiece(Holder<ArmorMaterial> material, Type type, Properties properties) {
        this(material, type, properties, ArmourModelConfig.none());
    }

    public ArmourPiece(Holder<ArmorMaterial> material, Type type, Properties properties, ArmourModelConfig modelConfig) {
        super(material, type, properties);
        this.modelConfig = modelConfig;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        WearableArmourRenderers.createProvider(this, consumer);
    }

    public ArmourModelConfig modelConfig() {
        return this.modelConfig;
    }

    public boolean shouldRenderCustomModelWhenWorn() {
        return this.modelConfig.enabled();
    }
}
