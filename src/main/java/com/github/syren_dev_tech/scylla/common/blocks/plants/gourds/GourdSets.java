package com.github.syren_dev_tech.scylla.common.blocks.plants.gourds;

import java.util.function.Supplier;

import com.github.syren_dev_tech.scylla.common.crops.Stems;
import com.github.syren_dev_tech.scylla.common.registry.ModRegister;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class GourdSets {

    public static final GourdSetDefinition create(ModRegister register, String name, ResourceKey<Item> seeds) {
        // CarvedGourd carved = CarvedGourds.create(name);
        var weableCarved = EquipableGourds.create(register, name);
        var lantern = CarvedGourdLanterns.create(register, name);

        var gourdRegistry = Gourds.create(register, name, Properties.ofFullCopy(Blocks.PUMPKIN));
        // gourd.setCarvedBlock(weableCarved.get()).setSeeds(seeds);

        var stems = Stems.create(register, name, gourdRegistry.resourceKey, seeds);

        // gourdRegistry.gourd.get().setStemBlock(stems.x.get()).setAttachedStemBlock(stems.y.get());

        return new GourdSetDefinition(name, gourdRegistry, stems, weableCarved, lantern);
    }

    private GourdSets() {
        // Prevent instantiation
    }
}
