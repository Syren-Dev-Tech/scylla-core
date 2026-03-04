package com.github.syren_dev_tech.scylla.common.husbandry;

import com.github.syren_dev_tech.scylla.common.husbandry.types.SmallEgg;
import com.github.syren_dev_tech.scylla.common.husbandry.types.SmallEggData;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class SmallEggs {

    public static final <T extends Animal> BlockDefinition<SmallEgg<T>> create(ModRegister register, String name, SmallEggData<T> eggData) {
        return create(register, name, Properties.ofFullCopy(Blocks.TURTLE_EGG), eggData);
    }

    public static final <T extends Animal> BlockDefinition<SmallEgg<T>> create(ModRegister register, String name, SmallEggData<T> eggData, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.TURTLE_EGG), eggData, creativeTab);
    }

    public static final <T extends Animal> BlockDefinition<SmallEgg<T>> create(ModRegister register, String name, Properties properties, SmallEggData<T> eggData) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new SmallEgg<T>(properties, eggData)));
    }

    public static final <T extends Animal> BlockDefinition<SmallEgg<T>> create(ModRegister register, String name, Properties properties, SmallEggData<T> eggData, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new SmallEgg<T>(properties, eggData)), creativeTab);
    }

    private SmallEggs() {
        // Prevent instantiation
    }
}
