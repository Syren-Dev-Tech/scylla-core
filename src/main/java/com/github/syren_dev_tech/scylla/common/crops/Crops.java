package com.github.syren_dev_tech.scylla.common.crops;

import com.github.syren_dev_tech.scylla.common.crops.types.Crop;
import com.github.syren_dev_tech.scylla.common.crops.types.CropProperties;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import com.github.syren_dev_tech.scylla.registry.definitions.ItemDefinition;
import net.minecraft.world.item.Item;

public class Crops {

    private Crops() {}

    public static final BlockDefinition<Crop> create(ModRegister register, String name, CropProperties properties, ItemDefinition<? extends Item> seed) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new Crop(properties, seed)));
    }
}
