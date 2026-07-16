package com.github.syren_dev_tech.scylla.dimensions;

import java.util.Objects;
import com.github.syren_dev_tech.scylla.dimensions.portals.HorizontalPortal;
import com.github.syren_dev_tech.scylla.dimensions.portals.PortalTransit;
import com.github.syren_dev_tech.scylla.dimensions.portals.VerticalPortal;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public final class Portals {

    private Portals() {
        // Prevent instantiation
    }

    public static BlockDefinition<VerticalPortal> createVertical(ModRegister register, String name, ResourceKey<Level> fromDimension, ResourceKey<Level> toDimension) {
        return createVertical(register, name, fromDimension, toDimension, Properties.ofFullCopy(Blocks.NETHER_PORTAL));
    }

    public static BlockDefinition<VerticalPortal> createVertical(ModRegister register, String name, ResourceKey<Level> fromDimension, ResourceKey<Level> toDimension, Properties properties) {
        return createVertical(register, name, PortalTransit.between(fromDimension, toDimension), properties);
    }

    public static BlockDefinition<VerticalPortal> createVertical(ModRegister register, String name, PortalTransit transit, Properties properties) {
        validate(register, name, transit, properties);
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new VerticalPortal(transit, properties)));
    }

    public static BlockDefinition<VerticalPortal> createVertical(ModRegister register, String name, PortalTransit transit, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        validate(register, name, transit, properties);
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new VerticalPortal(transit, properties)), creativeTab);
    }

    public static BlockDefinition<HorizontalPortal> createHorizontal(ModRegister register, String name, ResourceKey<Level> destinationDimension) {
        return createHorizontal(register, name, PortalTransit.fixed(destinationDimension), Properties.ofFullCopy(Blocks.END_PORTAL));
    }

    public static BlockDefinition<HorizontalPortal> createHorizontal(ModRegister register, String name, ResourceKey<Level> destinationDimension, Properties properties) {
        return createHorizontal(register, name, PortalTransit.fixed(destinationDimension), properties);
    }

    public static BlockDefinition<HorizontalPortal> createHorizontal(ModRegister register, String name, PortalTransit transit, Properties properties) {
        validate(register, name, transit, properties);
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new HorizontalPortal(properties, transit)));
    }

    public static BlockDefinition<HorizontalPortal> createHorizontal(ModRegister register, String name, PortalTransit transit, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        validate(register, name, transit, properties);
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new HorizontalPortal(properties, transit)), creativeTab);
    }

    private static void validate(ModRegister register, String name, PortalTransit transit, Properties properties) {
        Objects.requireNonNull(register, "Register cannot be null");
        Objects.requireNonNull(transit, "Transit cannot be null");
        Objects.requireNonNull(properties, "Properties cannot be null");
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Portal block name cannot be null or blank");
        }
    }
}
