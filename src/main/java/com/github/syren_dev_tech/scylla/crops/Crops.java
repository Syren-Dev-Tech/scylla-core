package com.github.syren_dev_tech.scylla.crops;

import com.github.syren_dev_tech.scylla.crops.types.Crop;
import com.github.syren_dev_tech.scylla.crops.types.CropProperties;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import com.github.syren_dev_tech.scylla.registry.definitions.ItemDefinition;
import net.minecraft.world.item.Item;

public class Crops {

    private Crops() {}

    public static BlockDefinition<Crop> create(ModRegister register, String name, CropProperties properties, ItemDefinition<? extends Item> seed) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new Crop(properties, seed)));
    }
}
