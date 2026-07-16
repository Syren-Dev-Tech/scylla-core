package com.github.syren_dev_tech.scylla.dimensions;

import java.util.Objects;
import com.github.syren_dev_tech.scylla.dimensions.portals.PortalFrameBlock;
import com.github.syren_dev_tech.scylla.dimensions.portals.PortalTransit;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public final class PortalSet {

    private PortalSet() {
        // Prevent instantiation
    }

    public static Builder create(ModRegister register, String name, PortalTransit transit) {
        return new Builder(register, name, transit);
    }

    public record Definition(Dimensions.DimensionDefinition dimension, PortalShapeMode shapeMode, BlockDefinition<PortalFrameBlock> frame, BlockDefinition<? extends Block> verticalPortal, BlockDefinition<? extends Block> horizontalPortal, Item keyItem) {
    }

    public static final class Builder {

        private final ModRegister register;
        private final String name;
        private final PortalTransit transit;
        private PortalShapeMode shapeMode = PortalShapeMode.BOTH;
        private Properties frameProperties = Properties.ofFullCopy(Blocks.OBSIDIAN);
        private Properties verticalPortalProperties = Properties.ofFullCopy(Blocks.NETHER_PORTAL);
        private Properties horizontalPortalProperties = Properties.ofFullCopy(Blocks.END_PORTAL);
        private Item keyItem;
        private String frameName;
        private String verticalPortalName;
        private String horizontalPortalName;

        private Builder(ModRegister register, String name, PortalTransit transit) {
            this.register = Objects.requireNonNull(register, "Register cannot be null");
            this.name = requireName(name, "Portal name");
            this.transit = Objects.requireNonNull(transit, "Portal transit cannot be null");
            this.frameName = this.name + "_frame";
            this.verticalPortalName = this.name + "_vertical_portal";
            this.horizontalPortalName = this.name + "_horizontal_portal";
        }

        public Builder withShapeMode(PortalShapeMode shapeMode) {
            this.shapeMode = Objects.requireNonNull(shapeMode, "Portal shape mode cannot be null");
            return this;
        }

        public Builder withFrameName(String frameName) {
            this.frameName = requireName(frameName, "Frame block name");
            return this;
        }

        public Builder withVerticalPortalName(String verticalPortalName) {
            this.verticalPortalName = requireName(verticalPortalName, "Vertical portal block name");
            return this;
        }

        public Builder withHorizontalPortalName(String horizontalPortalName) {
            this.horizontalPortalName = requireName(horizontalPortalName, "Horizontal portal block name");
            return this;
        }

        public Builder withFrameProperties(Properties frameProperties) {
            this.frameProperties = Objects.requireNonNull(frameProperties, "Frame properties cannot be null");
            return this;
        }

        public Builder withFrameSourceBlock(Block sourceBlock) {
            this.frameProperties = Properties.ofFullCopy(Objects.requireNonNull(sourceBlock, "Source block cannot be null"));
            return this;
        }

        public Builder withVerticalPortalProperties(Properties verticalPortalProperties) {
            this.verticalPortalProperties = Objects.requireNonNull(verticalPortalProperties, "Vertical portal properties cannot be null");
            return this;
        }

        public Builder withHorizontalPortalProperties(Properties horizontalPortalProperties) {
            this.horizontalPortalProperties = Objects.requireNonNull(horizontalPortalProperties, "Horizontal portal properties cannot be null");
            return this;
        }

        public Builder withKeyItem(Item keyItem) {
            this.keyItem = keyItem;
            return this;
        }

        public Definition register() {
            var dimension = Dimensions.create(this.register, this.name).build();
            var frame = this.register.blockRegistry.register(this.frameName, BlockDefinition.of(() -> new PortalFrameBlock(this.frameProperties, this.keyItem)));
            BlockDefinition<? extends Block> verticalPortal = null;
            BlockDefinition<? extends Block> horizontalPortal = null;

            if (this.shapeMode == PortalShapeMode.VERTICAL || this.shapeMode == PortalShapeMode.BOTH) {
                verticalPortal = Portals.createVertical(this.register, this.verticalPortalName, this.transit, this.verticalPortalProperties);
            }

            if (this.shapeMode == PortalShapeMode.HORIZONTAL || this.shapeMode == PortalShapeMode.BOTH) {
                horizontalPortal = Portals.createHorizontal(this.register, this.horizontalPortalName, this.transit, this.horizontalPortalProperties);
            }

            return new Definition(dimension, this.shapeMode, frame, verticalPortal, horizontalPortal, this.keyItem);
        }
    }

    private static String requireName(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or blank");
        }

        return value;
    }
}
