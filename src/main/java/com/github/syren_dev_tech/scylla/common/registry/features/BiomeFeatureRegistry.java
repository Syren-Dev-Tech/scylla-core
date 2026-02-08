package com.github.syren_dev_tech.scylla.common.registry.features;

import java.util.HashMap;
import java.util.Map;

import com.github.syren_dev_tech.scylla.common.collections.Tuple;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class BiomeFeatureRegistry {

    private BiomeFeatureRegistry() {}

    public class BiomeOreFeatureRegistry {

        private BiomeOreFeatureRegistry() {}

        protected static final Map<String, Tuple<TagKey<Biome>, ResourceKey<PlacedFeature>>> modifiers = new HashMap<>();

        public static final void register(String name, TagKey<Biome> biome, ResourceKey<PlacedFeature> placedFeatureKey) {
            Tuple<TagKey<Biome>, ResourceKey<PlacedFeature>> value = new Tuple<>(biome, placedFeatureKey);
            modifiers.put(name, value);
        }
    }
}
