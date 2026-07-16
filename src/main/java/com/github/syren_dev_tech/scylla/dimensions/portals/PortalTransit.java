package com.github.syren_dev_tech.scylla.dimensions.portals;

import java.util.Map;
import java.util.Objects;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

@FunctionalInterface
public interface PortalTransit {

    ResourceKey<Level> destination(ResourceKey<Level> currentDimension);

    static PortalTransit fixed(ResourceKey<Level> destination) {
        Objects.requireNonNull(destination, "Destination dimension cannot be null");
        return (currentDimension) -> destination;
    }

    static PortalTransit between(ResourceKey<Level> first, ResourceKey<Level> second) {
        Objects.requireNonNull(first, "First dimension cannot be null");
        Objects.requireNonNull(second, "Second dimension cannot be null");

        return (currentDimension) -> {
            if (currentDimension == null) {
                return null;
            }

            if (currentDimension.equals(first)) {
                return second;
            }

            if (currentDimension.equals(second)) {
                return first;
            }

            return null;
        };
    }

    static PortalTransit map(Map<ResourceKey<Level>, ResourceKey<Level>> transitions) {
        Objects.requireNonNull(transitions, "Transitions cannot be null");
        return (currentDimension) -> transitions.get(currentDimension);
    }
}
