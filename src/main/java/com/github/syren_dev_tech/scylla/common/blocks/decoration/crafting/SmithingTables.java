package com.github.syren_dev_tech.scylla.common.blocks.decoration.crafting;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SmithingTableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class SmithingTables {

    public static final BlockDefinition<SmithingTableBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.SMITHING_TABLE));
    }

    public static final BlockDefinition<SmithingTableBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.SMITHING_TABLE), creativeTab);
    }

    public static final BlockDefinition<SmithingTableBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new SmithingTableBlock(properties)));
    }

    public static final BlockDefinition<SmithingTableBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new SmithingTableBlock(properties)), creativeTab);
    }

    private SmithingTables() {
        // Prevent instantiation
    }
}
