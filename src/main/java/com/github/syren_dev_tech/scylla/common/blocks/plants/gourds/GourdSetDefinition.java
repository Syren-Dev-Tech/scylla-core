package com.github.syren_dev_tech.scylla.common.blocks.plants.gourds;

import java.util.function.Supplier;
import com.github.syren_dev_tech.scylla.common.blocks.plants.gourds.types.CarvedGourd;
import com.github.syren_dev_tech.scylla.common.blocks.plants.gourds.types.EquipableCarvedGourd;
import com.github.syren_dev_tech.scylla.common.crops.StemDefinition;

public class GourdSetDefinition {
    public final String name;
    public final GourdDefinition gourd;
    public final StemDefinition stems;
    public final Supplier<EquipableCarvedGourd> weableCarved;
    public final Supplier<CarvedGourd> lantern;

    public GourdSetDefinition(String name, GourdDefinition gourd, StemDefinition stems, Supplier<EquipableCarvedGourd> weableCarved, Supplier<CarvedGourd> lantern) {
        this.name = name;
        this.gourd = gourd;
        this.stems = stems;
        this.weableCarved = weableCarved;
        this.lantern = lantern;
    }
}
