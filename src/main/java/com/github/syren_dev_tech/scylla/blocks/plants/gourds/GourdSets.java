package com.github.syren_dev_tech.scylla.blocks.plants.gourds;

import com.github.syren_dev_tech.scylla.crops.Stems;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.GourdSetDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class GourdSets {

    public static final GourdSetDefinition create(ModRegister register, String name, ResourceKey<Item> seeds) {
        // CarvedGourd carved = CarvedGourds.create(name);
        var weableCarved = EquipableGourds.create(register, name);
        var lantern = CarvedGourdLanterns.create(register, name);

        var gourdDefinition = Gourds.create(register, name, Properties.ofFullCopy(Blocks.PUMPKIN));
        // gourd.setCarvedBlock(weableCarved.get()).setSeeds(seeds);

        var stems = Stems.create(register, name, gourdDefinition.resourceKey, seeds);

        // gourdDefinition.gourd.get().setStemBlock(stems.x.get()).setAttachedStemBlock(stems.y.get());

        return new GourdSetDefinition(name, gourdDefinition, stems, weableCarved, lantern);
    }

    private GourdSets() {
        // Prevent instantiation
    }
}
