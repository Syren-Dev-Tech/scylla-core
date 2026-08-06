package com.github.syren_dev_tech.scylla.dimensions;

import java.util.Objects;
import com.github.syren_dev_tech.scylla.files.ResourcePath;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;

public final class Dimensions {

    private Dimensions() {
        // Prevent instantiation
    }

    public static Builder create(ModRegister register, String name) {
        return new Builder(register, name);
    }

    public static DimensionDefinition of(ModRegister register, String name) {
        return create(register, name).build();
    }

    public record DimensionDefinition(String name, ResourceKey<Level> levelKey, ResourceKey<DimensionType> typeKey, ResourceKey<LevelStem> levelStemKey) {
    }

    public static final class Builder {

        private final ModRegister register;
        private final String name;
        private String levelName;
        private String dimensionTypeName;
        private String levelStemName;

        private Builder(ModRegister register, String name) {
            this.register = Objects.requireNonNull(register, "Register cannot be null");
            this.name = requireName(name, "Dimension name");
            this.levelName = this.name;
            this.dimensionTypeName = this.name + "_type";
            this.levelStemName = this.name;
        }

        public Builder withLevelName(String levelName) {
            this.levelName = requireName(levelName, "Level name");
            return this;
        }

        public Builder withDimensionTypeName(String dimensionTypeName) {
            this.dimensionTypeName = requireName(dimensionTypeName, "Dimension type name");
            return this;
        }

        public Builder withLevelStemName(String levelStemName) {
            this.levelStemName = requireName(levelStemName, "Level stem name");
            return this;
        }

        public DimensionDefinition build() {
            var levelKey = ResourceKey.create(Registries.DIMENSION, new ResourcePath(register.modId, this.levelName).get());
            var typeKey = ResourceKey.create(Registries.DIMENSION_TYPE, new ResourcePath(register.modId, this.dimensionTypeName).get());
            var levelStemKey = ResourceKey.create(Registries.LEVEL_STEM, new ResourcePath(register.modId, this.levelStemName).get());

            return new DimensionDefinition(this.name, levelKey, typeKey, levelStemKey);
        }
    }

    private static String requireName(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or blank");
        }

        return value;
    }
}
