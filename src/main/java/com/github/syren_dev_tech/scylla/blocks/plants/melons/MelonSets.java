package com.github.syren_dev_tech.scylla.blocks.plants.melons;

import com.github.syren_dev_tech.scylla.crops.Stems;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.MelonSetDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class MelonSets {

    public static final MelonSetDefinition create(ModRegister register, String name, ResourceKey<Item> seeds) {
        var melonDefinition = Melons.create(register, name);

        var stems = Stems.create(register, name, melonDefinition.resourceKey, seeds);
        melonDefinition.melon.registry.get().setStem(stems.stem.registry.get());

        return new MelonSetDefinition(melonDefinition, stems);
    }

    private MelonSets() {
        // Prevent instantiation
    }
}
