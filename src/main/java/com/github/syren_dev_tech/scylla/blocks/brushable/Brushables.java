package com.github.syren_dev_tech.scylla.blocks.brushable;

import java.util.Objects;
import com.github.syren_dev_tech.scylla.blocks.brushable.types.CustomBrushableBlock;
import com.github.syren_dev_tech.scylla.blocks.brushable.types.CustomFallingBrushableBlock;
import com.github.syren_dev_tech.scylla.files.ResourcePath;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public final class Brushables {

    private static final String DATA_ROOT = "src/main/resources/data/";
    private static final String JSON_EXT = ".json";

    private Brushables() {
        // Prevent instantiation
    }

    public static Builder create(ModRegister register, String name) {
        return new Builder(register, name);
    }

    public static Builder sand(ModRegister register, String name) {
        return create(register, name).asFalling().withProperties(Properties.ofFullCopy(Blocks.SUSPICIOUS_SAND));
    }

    public static Builder stone(ModRegister register, String name) {
        return create(register, name).asStatic();
    }

    public record Definition(String name, boolean falling, BlockDefinition<? extends Block> block, BlockDefinition<Block> turnsInto, SoundEvent brushSound, SoundEvent brushCompletedSound, DataRequirements dataRequirements) {
    }

    public record DataRequirements(String archaeologyLootTablePath, String blockLootTablePath, String configuredFeaturePath, String placedFeaturePath, String biomeModifierPath, ResourceLocation archaeologyLootTableId, ResourceLocation placedFeatureId, ResourceLocation configuredFeatureId) {

        public String archaeologyLootTableTemplate() {
            return """
                    {
                      "type": "minecraft:archaeology",
                      "pools": [
                        {
                          "rolls": 1,
                          "entries": [
                            {
                              "type": "minecraft:item",
                              "name": "minecraft:emerald"
                            }
                          ]
                        }
                      ],
                      "random_sequence": "%s"
                    }
                    """.formatted(this.archaeologyLootTableId);
        }

        public String blockLootTableTemplate(String blockId) {
            return """
                    {
                      "type": "minecraft:block",
                      "pools": [
                        {
                          "rolls": 1,
                          "entries": [
                            {
                              "type": "minecraft:item",
                              "name": "%s"
                            }
                          ],
                          "conditions": [
                            {
                              "condition": "minecraft:survives_explosion"
                            }
                          ]
                        }
                      ]
                    }
                    """.formatted(blockId);
        }

        public String configuredFeatureTemplate(String blockId) {
            return """
                    {
                      "type": "minecraft:ore",
                      "config": {
                        "size": 6,
                        "discard_chance_on_air_exposure": 0.0,
                        "targets": [
                          {
                            "target": {
                              "predicate_type": "minecraft:tag_match",
                              "tag": "minecraft:stone_ore_replaceables"
                            },
                            "state": {
                              "Name": "%s"
                            }
                          }
                        ]
                      }
                    }
                    """.formatted(blockId);
        }

        public String placedFeatureTemplate() {
            return """
                    {
                      "feature": "%s",
                      "placement": [
                        { "type": "minecraft:count", "count": 4 },
                        { "type": "minecraft:in_square" },
                        {
                          "type": "minecraft:height_range",
                          "height": {
                            "type": "minecraft:uniform",
                            "min_inclusive": { "absolute": -16 },
                            "max_inclusive": { "absolute": 48 }
                          }
                        },
                        { "type": "minecraft:biome" }
                      ]
                    }
                    """.formatted(this.configuredFeatureId);
        }

        public String biomeModifierTemplate() {
            return """
                    {
                      "type": "neoforge:add_features",
                      "biomes": "#minecraft:is_overworld",
                      "features": "%s",
                      "step": "underground_ores"
                    }
                    """.formatted(this.placedFeatureId);
        }
    }

    public static final class Builder {

        private final ModRegister register;
        private final String name;
        private BlockDefinition<Block> turnsInto;
        private SoundEvent brushSound = SoundEvents.BRUSH_SAND;
        private SoundEvent brushCompletedSound = SoundEvents.BRUSH_SAND_COMPLETED;
        private Properties properties;
        private ResourceKey<CreativeModeTab> creativeTab;
        private boolean falling;
        private String archaeologyLootTableName;
        private String configuredFeatureName;
        private String placedFeatureName;
        private String biomeModifierName;

        private Builder(ModRegister register, String name) {
            this.register = Objects.requireNonNull(register, "Register cannot be null");
            this.name = requireName(name, "Brushable block name");
            this.archaeologyLootTableName = "archaeology/" + this.name;
            this.configuredFeatureName = this.name + "_patch";
            this.placedFeatureName = this.configuredFeatureName;
            this.biomeModifierName = this.configuredFeatureName;
        }

        public Builder turnsInto(BlockDefinition<Block> turnsInto) {
            this.turnsInto = Objects.requireNonNull(turnsInto, "Turns-into block cannot be null");
            return this;
        }

        public Builder withBrushSound(SoundEvent brushSound) {
            this.brushSound = Objects.requireNonNull(brushSound, "Brush sound cannot be null");
            return this;
        }

        public Builder withBrushCompletedSound(SoundEvent brushCompletedSound) {
            this.brushCompletedSound = Objects.requireNonNull(brushCompletedSound, "Brush completed sound cannot be null");
            return this;
        }

        public Builder withSounds(SoundEvent brushSound, SoundEvent brushCompletedSound) {
            return withBrushSound(brushSound).withBrushCompletedSound(brushCompletedSound);
        }

        public Builder withProperties(Properties properties) {
            this.properties = Objects.requireNonNull(properties, "Brushable properties cannot be null");
            return this;
        }

        public Builder asFalling() {
            this.falling = true;
            return this;
        }

        public Builder asStatic() {
            this.falling = false;
            return this;
        }

        public Builder withCreativeTab(ResourceKey<CreativeModeTab> creativeTab) {
            this.creativeTab = Objects.requireNonNull(creativeTab, "Creative tab cannot be null");
            return this;
        }

        public Builder withArchaeologyLootTableName(String archaeologyLootTableName) {
            this.archaeologyLootTableName = requireName(archaeologyLootTableName, "Archaeology loot table path");
            return this;
        }

        public Builder withWorldgenNames(String configuredFeatureName, String placedFeatureName, String biomeModifierName) {
            this.configuredFeatureName = requireName(configuredFeatureName, "Configured feature name");
            this.placedFeatureName = requireName(placedFeatureName, "Placed feature name");
            this.biomeModifierName = requireName(biomeModifierName, "Biome modifier name");
            return this;
        }

        public Definition register() {
            if (this.turnsInto == null) {
                throw new IllegalStateException("Turns-into block must be provided via turnsInto(...)");
            }

            final Properties resolvedProperties;
            if (this.properties != null) {
                resolvedProperties = this.properties;
            } else if (this.falling) {
                resolvedProperties = Properties.ofFullCopy(Blocks.SUSPICIOUS_SAND);
            } else {
                resolvedProperties = this.turnsInto.properties();
            }
            final BlockDefinition<Block> resolvedTurnsInto = this.turnsInto;
            final SoundEvent resolvedBrushSound = this.brushSound;
            final SoundEvent resolvedBrushCompletedSound = this.brushCompletedSound;

            BlockDefinition<? extends Block> blockDefinition;
            if (this.falling) {
                BlockDefinition<CustomFallingBrushableBlock> definition = BlockDefinition.of(() -> new CustomFallingBrushableBlock(resolvedTurnsInto.registry.get(), resolvedProperties, resolvedBrushSound, resolvedBrushCompletedSound));
                blockDefinition = this.creativeTab == null ? this.register.blockRegistry.register(this.name, definition) : this.register.blockRegistry.register(this.name, definition, this.creativeTab);
            } else {
                BlockDefinition<CustomBrushableBlock> definition = BlockDefinition.of(() -> new CustomBrushableBlock(resolvedTurnsInto.registry.get(), resolvedBrushSound, resolvedBrushCompletedSound, resolvedProperties));
                blockDefinition = this.creativeTab == null ? this.register.blockRegistry.register(this.name, definition) : this.register.blockRegistry.register(this.name, definition, this.creativeTab);
            }

            ResourceLocation archaeologyLootId = new ResourcePath(this.register.modId, this.archaeologyLootTableName).get();
            ResourceLocation configuredFeatureId = new ResourcePath(this.register.modId, this.configuredFeatureName).get();
            ResourceLocation placedFeatureId = new ResourcePath(this.register.modId, this.placedFeatureName).get();

            DataRequirements requirements = new DataRequirements(DATA_ROOT + this.register.modId + "/loot_tables/" + this.archaeologyLootTableName + JSON_EXT, DATA_ROOT + this.register.modId + "/loot_tables/blocks/" + this.name + JSON_EXT, DATA_ROOT + this.register.modId + "/worldgen/configured_feature/" + this.configuredFeatureName + JSON_EXT, DATA_ROOT + this.register.modId + "/worldgen/placed_feature/" + this.placedFeatureName + JSON_EXT, DATA_ROOT + this.register.modId + "/neoforge/biome_modifier/" + this.biomeModifierName + JSON_EXT, archaeologyLootId, placedFeatureId, configuredFeatureId);

            return new Definition(this.name, this.falling, blockDefinition, this.turnsInto, this.brushSound, this.brushCompletedSound, requirements);
        }

        private static String requireName(String value, String fieldName) {
            if (value == null || value.isBlank()) {
                throw new IllegalArgumentException(fieldName + " cannot be null or blank");
            }

            return value;
        }
    }
}
