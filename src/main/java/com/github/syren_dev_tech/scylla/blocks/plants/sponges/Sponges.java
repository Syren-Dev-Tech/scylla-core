package com.github.syren_dev_tech.scylla.blocks.plants.sponges;

import com.github.syren_dev_tech.scylla.blocks.plants.sponges.types.Sponge;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import com.github.syren_dev_tech.scylla.registry.definitions.SpongeDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WetSpongeBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Sponges {

    public static class WetSponges {

        public static BlockDefinition<WetSpongeBlock> create(ModRegister register, String name) {
            return create(register, name, Properties.ofFullCopy(Blocks.WET_SPONGE));
        }

        public static BlockDefinition<WetSpongeBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.WET_SPONGE), creativeTab);
        }

        public static BlockDefinition<WetSpongeBlock> create(ModRegister register, String name, Properties properties) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new WetSpongeBlock(properties)));
        }

        public static BlockDefinition<WetSpongeBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new WetSpongeBlock(properties)), creativeTab);
        }

        private WetSponges() {
            // Prevent instantiation
        }
    }

    // Add sea foliage and liquid to dry sponge after this.
    public static final SpongeDefinition create(ModRegister register, String name, Properties dryProperties, Properties wetProperties) {
        var wet = WetSponges.create(register, "wet_" + name, wetProperties);
        var dry = register.blockRegistry.register(name, BlockDefinition.of(() -> new Sponge(dryProperties)));
        dry.registry.get().setWetSponge(wet.registry.get());

        return new SpongeDefinition(name, dry, wet);
    }

    // Add sea foliage and liquid to dry sponge after this.
    public static final SpongeDefinition create(ModRegister register, String name, Properties dryProperties, Properties wetProperties, ResourceKey<CreativeModeTab> creativeTab) {
        var wet = WetSponges.create(register, "wet_" + name, wetProperties, creativeTab);
        var dry = register.blockRegistry.register(name, BlockDefinition.of(() -> new Sponge(dryProperties)), creativeTab);
        dry.registry.get().setWetSponge(wet.registry.get());

        return new SpongeDefinition(name, dry, wet);
    }

    private Sponges() {
        // Prevent instantiation
    }
}
