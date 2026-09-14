package com.github.syren_dev_tech.scylla.blocks.spawners.trial_spawner;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Locale;

public record TrialSpawnerConfig(int totalWaves, TriggerType triggerType, ResourceLocation lootTable, List<SpawnEntry> spawns) {
    public static final Codec<TrialSpawnerConfig> CODEC = RecordCodecBuilder.create(instance ->
        instance.group(
            Codec.INT.fieldOf("total_waves").forGetter(TrialSpawnerConfig::totalWaves),
            TriggerType.CODEC.fieldOf("trigger_type").forGetter(TrialSpawnerConfig::triggerType),
            ResourceLocation.CODEC.fieldOf("loot_table").forGetter(TrialSpawnerConfig::lootTable),
            SpawnEntry.CODEC.listOf().fieldOf("spawns").forGetter(TrialSpawnerConfig::spawns)
        ).apply(instance, TrialSpawnerConfig::new)
    );

    public record SpawnEntry(ResourceLocation entityId, int count) {
        public static final Codec<SpawnEntry> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                ResourceLocation.CODEC.fieldOf("entity").forGetter(SpawnEntry::entityId),
                Codec.INT.fieldOf("count").forGetter(SpawnEntry::count)
            ).apply(instance, SpawnEntry::new)
        );
    }

    public enum TriggerType {
        PROXIMITY,
        REDSTONE,
        INTERACTION;

        public static final Codec<TriggerType> CODEC = Codec.STRING.xmap(
            value -> TriggerType.valueOf(value.toUpperCase(Locale.ROOT)),
            TriggerType::name
        );
    }
}
