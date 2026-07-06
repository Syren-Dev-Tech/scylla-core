package com.github.syren_dev_tech.scylla.husbandry;

import com.github.syren_dev_tech.scylla.husbandry.types.WaterEgg;
import com.github.syren_dev_tech.scylla.husbandry.types.WaterEggData;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

// Needs custom implementation...

public class WaterEggs {

    public static final <T extends Mob> BlockDefinition<WaterEgg<T>> create(ModRegister register, String name, WaterEggData<T> eggData) {
        return create(register, name, Properties.ofFullCopy(Blocks.FROGSPAWN), eggData);
    }

    public static final <T extends Mob> BlockDefinition<WaterEgg<T>> create(ModRegister register, String name, WaterEggData<T> eggData, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.FROGSPAWN), eggData, creativeTab);
    }

    public static final <T extends Mob> BlockDefinition<WaterEgg<T>> create(ModRegister register, String name, Properties properties, WaterEggData<T> eggData) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new WaterEgg<T>(properties, eggData)));
    }

    public static final <T extends Mob> BlockDefinition<WaterEgg<T>> create(ModRegister register, String name, Properties properties, WaterEggData<T> eggData, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new WaterEgg<T>(properties, eggData)), creativeTab);
    }

    private WaterEggs() {
        // Prevent instantiation
    }
}
