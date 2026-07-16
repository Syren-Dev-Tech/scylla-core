package com.github.syren_dev_tech.scylla.items.combat;

import com.github.syren_dev_tech.scylla.items.combat.types.ArmourModelConfig;
import com.github.syren_dev_tech.scylla.items.combat.types.ArmourPiece;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.ItemDefinition;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.ArmorItem.Type;

public class Wearables {

    public record SetDefinition(String baseName, ItemDefinition<? extends ArmorItem> helmet, ItemDefinition<? extends ArmorItem> chestplate, ItemDefinition<? extends ArmorItem> leggings, ItemDefinition<? extends ArmorItem> boots) {
    }

    private static final Type[] SUPPORTED_ARMOUR_TYPES = new Type[] {Type.HELMET, Type.CHESTPLATE, Type.LEGGINGS, Type.BOOTS};

    private static final class PieceConfig {
        final String name;
        final Properties properties;
        final ArmourModelConfig modelConfig;

        private PieceConfig(String name, Properties properties, ArmourModelConfig modelConfig) {
            this.name = name;
            this.properties = properties;
            this.modelConfig = modelConfig;
        }
    }

    public static final class Builder {

        private final ModRegister register;
        private final String baseName;
        private final Map<Type, PieceConfig> pieceConfigs = new EnumMap<>(Type.class);

        private Holder<ArmorMaterial> armorMaterial = ArmorMaterials.CHAIN;
        private ResourceKey<CreativeModeTab> creativeTab;
        private Properties defaultProperties = new Properties();

        private Builder(ModRegister register, String baseName) {
            this.register = Objects.requireNonNull(register, "Register cannot be null");
            this.baseName = requireName(baseName, "Armour base name");

            for (var type : SUPPORTED_ARMOUR_TYPES) {
                this.pieceConfigs.put(type, new PieceConfig(defaultName(type), this.defaultProperties, ArmourModelConfig.none()));
            }
        }

        public Builder withArmorMaterial(Holder<ArmorMaterial> armorMaterial) {
            this.armorMaterial = Objects.requireNonNull(armorMaterial, "Armor material cannot be null");
            return this;
        }

        public Builder withProperties(Properties properties) {
            this.defaultProperties = Objects.requireNonNull(properties, "Armour properties cannot be null");

            for (var type : SUPPORTED_ARMOUR_TYPES) {
                var config = this.pieceConfigs.get(type);
                this.pieceConfigs.put(type, new PieceConfig(config.name, this.defaultProperties, config.modelConfig));
            }

            return this;
        }

        public Builder withCreativeTab(ResourceKey<CreativeModeTab> creativeTab) {
            this.creativeTab = creativeTab;
            return this;
        }

        public Builder withPieceName(Type armorType, String pieceName) {
            var type = requireSupportedType(armorType);
            var config = this.pieceConfigs.get(type);
            this.pieceConfigs.put(type, new PieceConfig(requireName(pieceName, "Armour piece name"), config.properties, config.modelConfig));
            return this;
        }

        public Builder withPieceProperties(Type armorType, Properties properties) {
            var type = requireSupportedType(armorType);
            var config = this.pieceConfigs.get(type);
            this.pieceConfigs.put(type, new PieceConfig(config.name, Objects.requireNonNull(properties, "Armour piece properties cannot be null"), config.modelConfig));
            return this;
        }

        public Builder withGeckoModel(Type armorType, ResourceLocation modelResource, ResourceLocation textureResource, ResourceLocation animationResource) {
            return withModelConfig(armorType, ArmourModelConfig.gecko(modelResource, textureResource, animationResource));
        }

        public Builder withModelConfig(Type armorType, ArmourModelConfig modelConfig) {
            var type = requireSupportedType(armorType);
            var config = this.pieceConfigs.get(type);
            this.pieceConfigs.put(type, new PieceConfig(config.name, config.properties, Objects.requireNonNull(modelConfig, "Armour model config cannot be null")));
            return this;
        }

        public Builder clearModelConfig(Type armorType) {
            return withModelConfig(armorType, ArmourModelConfig.none());
        }

        public SetDefinition register() {
            var helmetConfig = this.pieceConfigs.get(Type.HELMET);
            var chestplateConfig = this.pieceConfigs.get(Type.CHESTPLATE);
            var leggingsConfig = this.pieceConfigs.get(Type.LEGGINGS);
            var bootsConfig = this.pieceConfigs.get(Type.BOOTS);

            var helmet = registerPiece(this.register, helmetConfig.name, helmetConfig.properties, Type.HELMET, this.armorMaterial, helmetConfig.modelConfig, this.creativeTab);
            var chestplate = registerPiece(this.register, chestplateConfig.name, chestplateConfig.properties, Type.CHESTPLATE, this.armorMaterial, chestplateConfig.modelConfig, this.creativeTab);
            var leggings = registerPiece(this.register, leggingsConfig.name, leggingsConfig.properties, Type.LEGGINGS, this.armorMaterial, leggingsConfig.modelConfig, this.creativeTab);
            var boots = registerPiece(this.register, bootsConfig.name, bootsConfig.properties, Type.BOOTS, this.armorMaterial, bootsConfig.modelConfig, this.creativeTab);

            return new SetDefinition(this.baseName, helmet, chestplate, leggings, boots);
        }

        private Type requireSupportedType(Type armorType) {
            Objects.requireNonNull(armorType, "Armour type cannot be null");

            for (var supportedType : SUPPORTED_ARMOUR_TYPES) {
                if (supportedType == armorType) {
                    return armorType;
                }
            }

            throw new IllegalArgumentException("Unsupported armour type for wearable sets: " + armorType);
        }

        private String defaultName(Type armorType) {
            return this.baseName + switch (armorType) {
                case HELMET -> "_helmet";
                case CHESTPLATE -> "_chestplate";
                case LEGGINGS -> "_leggings";
                case BOOTS -> "_boots";
                default -> throw new IllegalArgumentException("Unsupported armour type for wearable sets: " + armorType);
            };
        }
    }

    private Wearables() {}

    public static Builder set(ModRegister register, String baseName) {
        return new Builder(register, baseName);
    }

    public static final ItemDefinition<ArmorItem> create(ModRegister register, String name, Type armorType) {
        return create(register, name, new Properties(), armorType, ArmorMaterials.CHAIN);
    }

    public static final ItemDefinition<ArmorItem> create(ModRegister register, String name, Type armorType, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, new Properties(), armorType, ArmorMaterials.CHAIN, creativeTab);
    }

    public static final ItemDefinition<ArmorItem> create(ModRegister register, String name, Type armorType, Holder<ArmorMaterial> armorMaterial) {
        return create(register, name, new Properties(), armorType, armorMaterial);
    }

    public static final ItemDefinition<ArmorItem> create(ModRegister register, String name, Type armorType, Holder<ArmorMaterial> armorMaterial, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, new Properties(), armorType, armorMaterial, creativeTab);
    }

    public static final ItemDefinition<ArmorItem> create(ModRegister register, String name, Properties properties, Type armorType) {
        return create(register, name, properties, armorType, ArmorMaterials.CHAIN);
    }

    public static final ItemDefinition<ArmorItem> create(ModRegister register, String name, Properties properties, Type armorType, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, properties, armorType, ArmorMaterials.CHAIN, creativeTab);
    }

    public static final ItemDefinition<ArmorItem> create(ModRegister register, String name, Properties properties, Type armorType, Holder<ArmorMaterial> armorMaterial) {
        var item = register.itemRegistry.register(name, () -> new ArmorItem(armorMaterial, armorType, properties));
        register.itemRegistry.wearable.put(name, item);

        return item;
    }

    public static final ItemDefinition<ArmorItem> create(ModRegister register, String name, Properties properties, Type armorType, Holder<ArmorMaterial> armorMaterial, ResourceKey<CreativeModeTab> creativeTab) {
        var item = register.itemRegistry.register(name, () -> new ArmorItem(armorMaterial, armorType, properties), creativeTab);
        register.itemRegistry.wearable.put(name, item);

        return item;
    }

    public static final ItemDefinition<? extends ArmorItem> create(ModRegister register, String name, Properties properties, Type armorType, Holder<ArmorMaterial> armorMaterial, ArmourModelConfig modelConfig) {
        return registerPiece(register, name, properties, armorType, armorMaterial, modelConfig, null);
    }

    public static final ItemDefinition<? extends ArmorItem> create(ModRegister register, String name, Properties properties, Type armorType, Holder<ArmorMaterial> armorMaterial, ArmourModelConfig modelConfig, ResourceKey<CreativeModeTab> creativeTab) {
        return registerPiece(register, name, properties, armorType, armorMaterial, modelConfig, creativeTab);
    }

    private static ItemDefinition<? extends ArmorItem> registerPiece(ModRegister register, String name, Properties properties, Type armorType, Holder<ArmorMaterial> armorMaterial, ArmourModelConfig modelConfig, ResourceKey<CreativeModeTab> creativeTab) {
        var resolvedModelConfig = modelConfig == null ? ArmourModelConfig.none() : modelConfig;
        ItemDefinition<? extends ArmorItem> item;

        if (resolvedModelConfig.enabled()) {
            if (creativeTab == null) {
                item = register.itemRegistry.register(name, () -> new ArmourPiece(armorMaterial, armorType, properties, resolvedModelConfig));
            } else {
                item = register.itemRegistry.register(name, () -> new ArmourPiece(armorMaterial, armorType, properties, resolvedModelConfig), creativeTab);
            }
        } else {
            if (creativeTab == null) {
                item = register.itemRegistry.register(name, () -> new ArmorItem(armorMaterial, armorType, properties));
            } else {
                item = register.itemRegistry.register(name, () -> new ArmorItem(armorMaterial, armorType, properties), creativeTab);
            }
        }

        register.itemRegistry.wearable.put(name, item);
        return item;
    }

    private static String requireName(String value, String fieldName) {
        var resolved = Objects.requireNonNull(value, fieldName + " cannot be null").trim();
        if (resolved.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be blank");
        }

        return resolved;
    }
}
