package com.github.syren_dev_tech.scylla.crops;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import com.github.syren_dev_tech.scylla.files.ResourcePath;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public final class TreeFeatures {

    private static final Map<String, TreeFeatureRegistration> TREE_FEATURES = new HashMap<>();

    private TreeFeatures() {}

    public static TreeFeatureRegistration register(ModRegister register, String name, TreeFeatureBuilder builder) {
        Objects.requireNonNull(register, "Mod register cannot be null");
        Objects.requireNonNull(name, "Feature name cannot be null");
        Objects.requireNonNull(builder, "Tree feature builder cannot be null");

        ResourceKey<ConfiguredFeature<?, ?>> configuredKey = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourcePath(register.modId, name).get());

        ResourceKey<PlacedFeature> placedKey = ResourceKey.create(Registries.PLACED_FEATURE, new ResourcePath(register.modId, name + "_placed").get());

        var definition = builder.build();
        var configuredFeature = new ConfiguredFeature<>(Feature.TREE, definition.configuration());
        var registration = new TreeFeatureRegistration(name, configuredKey, placedKey, configuredFeature, definition.placementModifiers(), definition.biomeTag());

        TREE_FEATURES.put(name, registration);
        return registration;
    }

    public static TreeFeatureRegistration register(ModRegister register, String name, BlockDefinition<? extends Block> wood, BlockDefinition<? extends Block> leaves) {
        return register(register, name, TreeFeatureBuilder.create().withWood(wood).withLeaves(leaves));
    }

    public static Map<String, TreeFeatureRegistration> getFeatures() {
        return Collections.unmodifiableMap(TREE_FEATURES);
    }

    public record TreeFeatureRegistration(String name, ResourceKey<ConfiguredFeature<?, ?>> configuredKey, ResourceKey<PlacedFeature> placedKey, ConfiguredFeature<?, ?> configuredFeature, java.util.List<net.minecraft.world.level.levelgen.placement.PlacementModifier> placementModifiers, net.minecraft.tags.TagKey<net.minecraft.world.level.biome.Biome> biomeTag) {
    }
}
