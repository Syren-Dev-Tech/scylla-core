package com.github.syren_dev_tech.scylla.common.blocks.plants.melons;

import com.github.syren_dev_tech.scylla.common.crops.Stems;
import com.github.syren_dev_tech.scylla.common.registry.ModRegister;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class MelonSets {

    public static final MelonSetDefinition create(ModRegister register, String name, ResourceKey<Item> seeds) {
        var melonDefinition = Melons.create(register, name);

        var stems = Stems.create(register, name, melonDefinition.resourceKey, seeds);
        melonDefinition.melon.get().setStem(stems.stem.get());

        return new MelonSetDefinition(melonDefinition, stems);
    }

    private MelonSets() {
        // Prevent instantiation
    }
}
