package com.github.syren_dev_tech.scylla.registry;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.github.syren_dev_tech.scylla.files.TomlBuilder;
import com.github.syren_dev_tech.scylla.files.TomlConfigManager;

public class ModRegister {

    public final String modId;
    public final ModRegistrars registrars;

    public final Map<String, Object> events = new HashMap<>();
    public final BlockRegistry blockRegistry;
    public final ItemRegistry itemRegistry;
    public final CreativeTabRegistry creativeTabRegistry;
    public final MobRegistry mobRegistry;
    public final Map<String, TomlConfigManager> configs = new HashMap<>();

    public ModRegister(String modId, ModRegistrars registrars) {
        this.modId = modId;
        this.registrars = registrars;

        this.blockRegistry = new BlockRegistry(this, registrars.blockRegistrar);
        this.itemRegistry = new ItemRegistry(this, registrars.itemRegistrar);
        this.creativeTabRegistry = new CreativeTabRegistry(this, registrars.creativeTabRegistrar);
        this.mobRegistry = new MobRegistry(this, registrars.entityRegistrar);
    }

    public void loadConfig(String filePath, TomlBuilder tomlBuilder) throws IOException {
        configs.put(filePath, TomlConfigManager.load(filePath, tomlBuilder));
    }

    public TomlBuilder getConfig(String filePath) {
        var config = configs.get(filePath);
        if (config == null) {
            return null;
        }

        return config.getConfig();
    }
}
