package com.github.syren_dev_tech.scylla.blocks.plants.gourds;

import com.github.syren_dev_tech.scylla.blocks.plants.gourds.types.EquipableCarvedGourd;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class EquipableGourds {

    public static BlockDefinition<EquipableCarvedGourd> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.CARVED_PUMPKIN));
    }

    public static BlockDefinition<EquipableCarvedGourd> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.CARVED_PUMPKIN), creativeTab);
    }

    public static BlockDefinition<EquipableCarvedGourd> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new EquipableCarvedGourd(properties)));
    }

    public static BlockDefinition<EquipableCarvedGourd> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new EquipableCarvedGourd(properties)), creativeTab);
    }

    private EquipableGourds() {
        // Prevent instantiation
    }
}
