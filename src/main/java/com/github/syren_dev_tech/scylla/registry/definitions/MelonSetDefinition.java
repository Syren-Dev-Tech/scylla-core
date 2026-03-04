package com.github.syren_dev_tech.scylla.registry.definitions;

public class MelonSetDefinition {
    public final MelonDefinition melon;
    public final StemDefinition stem;

    public MelonSetDefinition(MelonDefinition melon, StemDefinition stem) {
        this.melon = melon;
        this.stem = stem;
    }
}
