package com.github.syren_dev_tech.scylla.common.husbandry;

import com.github.syren_dev_tech.scylla.common.husbandry.types.LargeEgg;
import com.github.syren_dev_tech.scylla.common.husbandry.types.LargeEggData;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DragonEggBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class LargeEggs {

    public static final <T extends Animal> BlockDefinition<DragonEggBlock> create(ModRegister register, String name, LargeEggData<T> eggData) {
        return create(register, name, Properties.ofFullCopy(Blocks.DRAGON_EGG), eggData);
    }

    public static final <T extends Animal> BlockDefinition<DragonEggBlock> create(ModRegister register, String name, LargeEggData<T> eggData, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.DRAGON_EGG), eggData, creativeTab);
    }

    public static final <T extends Animal> BlockDefinition<DragonEggBlock> create(ModRegister register, String name, Properties properties, LargeEggData<T> eggData) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new LargeEgg<T>(properties, eggData)));
    }

    public static final <T extends Animal> BlockDefinition<DragonEggBlock> create(ModRegister register, String name, Properties properties, LargeEggData<T> eggData, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new LargeEgg<T>(properties, eggData)), creativeTab);
    }

    private LargeEggs() {
        // Prevent instantiation
    }
}
