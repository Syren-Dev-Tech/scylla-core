package com.github.syren_dev_tech.scylla.items.combat.types;

import java.util.Objects;
import net.minecraft.resources.ResourceLocation;

/**
 * Metadata that can be used by client render registration to decide whether a worn armour piece should use a GeckoLib model and which model resources to bind.
 */
public record ArmourModelConfig(boolean enabled, ResourceLocation modelResource, ResourceLocation textureResource, ResourceLocation animationResource) {

    private static final ArmourModelConfig NONE = new ArmourModelConfig(false, null, null, null);

    public static ArmourModelConfig none() {
        return NONE;
    }

    public static ArmourModelConfig gecko(ResourceLocation modelResource, ResourceLocation textureResource, ResourceLocation animationResource) {
        return new ArmourModelConfig(true, Objects.requireNonNull(modelResource, "Model resource cannot be null"), Objects.requireNonNull(textureResource, "Texture resource cannot be null"), Objects.requireNonNull(animationResource, "Animation resource cannot be null"));
    }

    public ArmourModelConfig {
        if (!enabled) {
            modelResource = null;
            textureResource = null;
            animationResource = null;
        } else {
            Objects.requireNonNull(modelResource, "Model resource cannot be null");
            Objects.requireNonNull(textureResource, "Texture resource cannot be null");
            Objects.requireNonNull(animationResource, "Animation resource cannot be null");
        }
    }
}
