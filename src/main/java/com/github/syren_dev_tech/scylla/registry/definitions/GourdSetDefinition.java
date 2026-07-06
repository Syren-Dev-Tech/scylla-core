package com.github.syren_dev_tech.scylla.registry.definitions;

import com.github.syren_dev_tech.scylla.blocks.plants.gourds.types.CarvedGourd;
import com.github.syren_dev_tech.scylla.blocks.plants.gourds.types.EquipableCarvedGourd;

public class GourdSetDefinition {
    public final String name;
    public final GourdDefinition gourd;
    public final StemDefinition stems;
    public final BlockDefinition<EquipableCarvedGourd> weableCarved;
    public final BlockDefinition<CarvedGourd> lantern;

    public GourdSetDefinition(String name, GourdDefinition gourd, StemDefinition stems, BlockDefinition<EquipableCarvedGourd> weableCarved, BlockDefinition<CarvedGourd> lantern) {
        this.name = name;
        this.gourd = gourd;
        this.stems = stems;
        this.weableCarved = weableCarved;
        this.lantern = lantern;
    }
}
