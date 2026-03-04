package com.github.syren_dev_tech.scylla.registry.definitions;

import com.github.syren_dev_tech.scylla.common.blocks.plants.sponges.types.Sponge;
import net.minecraft.world.level.block.WetSpongeBlock;

public class SpongeDefinition {

    public final String name;
    public final BlockDefinition<Sponge> drySponge;
    public final BlockDefinition<WetSpongeBlock> wetSponge;

    public SpongeDefinition(String name, BlockDefinition<Sponge> drySponge, BlockDefinition<WetSpongeBlock> wetSponge) {
        this.name = name;
        this.drySponge = drySponge;
        this.wetSponge = wetSponge;
    }
}
