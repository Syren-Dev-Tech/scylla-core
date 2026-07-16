package com.github.syren_dev_tech.scylla.items;

import java.util.Objects;
import com.github.syren_dev_tech.scylla.items.combat.Swords;
import com.github.syren_dev_tech.scylla.items.tools.Axes;
import com.github.syren_dev_tech.scylla.items.tools.Hoes;
import com.github.syren_dev_tech.scylla.items.tools.Pickaxes;
import com.github.syren_dev_tech.scylla.items.tools.Shovels;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.ItemDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;

public class Equipment {

    public record SetDefinition(String baseName, ItemDefinition<SwordItem> sword, ItemDefinition<PickaxeItem> pickaxe, ItemDefinition<AxeItem> axe, ItemDefinition<ShovelItem> shovel, ItemDefinition<HoeItem> hoe) {
    }

    public static final class Builder {

        private final ModRegister register;
        private final String baseName;

        private ResourceKey<CreativeModeTab> creativeTab;

        private Tier swordTier = Tiers.IRON;
        private Tier pickaxeTier = Tiers.IRON;
        private Tier axeTier = Tiers.IRON;
        private Tier shovelTier = Tiers.IRON;
        private Tier hoeTier = Tiers.IRON;

        private Item.Properties swordProperties = new Item.Properties();
        private Item.Properties pickaxeProperties = new Item.Properties();
        private Item.Properties axeProperties = new Item.Properties();
        private Item.Properties shovelProperties = new Item.Properties();
        private Item.Properties hoeProperties = new Item.Properties();

        private String swordName;
        private String pickaxeName;
        private String axeName;
        private String shovelName;
        private String hoeName;

        private Builder(ModRegister register, String baseName) {
            this.register = Objects.requireNonNull(register, "Register cannot be null");
            this.baseName = requireName(baseName, "Equipment base name");

            this.swordName = this.baseName + "_sword";
            this.pickaxeName = this.baseName + "_pickaxe";
            this.axeName = this.baseName + "_axe";
            this.shovelName = this.baseName + "_shovel";
            this.hoeName = this.baseName + "_hoe";
        }

        public Builder withCreativeTab(ResourceKey<CreativeModeTab> creativeTab) {
            this.creativeTab = creativeTab;
            return this;
        }

        public Builder withTier(Tier tier) {
            var resolvedTier = Objects.requireNonNull(tier, "Tier cannot be null");
            this.swordTier = resolvedTier;
            this.pickaxeTier = resolvedTier;
            this.axeTier = resolvedTier;
            this.shovelTier = resolvedTier;
            this.hoeTier = resolvedTier;
            return this;
        }

        public Builder withSwordTier(Tier tier) {
            this.swordTier = Objects.requireNonNull(tier, "Sword tier cannot be null");
            return this;
        }

        public Builder withPickaxeTier(Tier tier) {
            this.pickaxeTier = Objects.requireNonNull(tier, "Pickaxe tier cannot be null");
            return this;
        }

        public Builder withAxeTier(Tier tier) {
            this.axeTier = Objects.requireNonNull(tier, "Axe tier cannot be null");
            return this;
        }

        public Builder withShovelTier(Tier tier) {
            this.shovelTier = Objects.requireNonNull(tier, "Shovel tier cannot be null");
            return this;
        }

        public Builder withHoeTier(Tier tier) {
            this.hoeTier = Objects.requireNonNull(tier, "Hoe tier cannot be null");
            return this;
        }

        public Builder withProperties(Item.Properties properties) {
            var resolved = Objects.requireNonNull(properties, "Properties cannot be null");
            this.swordProperties = resolved;
            this.pickaxeProperties = resolved;
            this.axeProperties = resolved;
            this.shovelProperties = resolved;
            this.hoeProperties = resolved;
            return this;
        }

        public Builder withSwordProperties(Item.Properties properties) {
            this.swordProperties = Objects.requireNonNull(properties, "Sword properties cannot be null");
            return this;
        }

        public Builder withPickaxeProperties(Item.Properties properties) {
            this.pickaxeProperties = Objects.requireNonNull(properties, "Pickaxe properties cannot be null");
            return this;
        }

        public Builder withAxeProperties(Item.Properties properties) {
            this.axeProperties = Objects.requireNonNull(properties, "Axe properties cannot be null");
            return this;
        }

        public Builder withShovelProperties(Item.Properties properties) {
            this.shovelProperties = Objects.requireNonNull(properties, "Shovel properties cannot be null");
            return this;
        }

        public Builder withHoeProperties(Item.Properties properties) {
            this.hoeProperties = Objects.requireNonNull(properties, "Hoe properties cannot be null");
            return this;
        }

        public Builder withSwordName(String name) {
            this.swordName = requireName(name, "Sword name");
            return this;
        }

        public Builder withPickaxeName(String name) {
            this.pickaxeName = requireName(name, "Pickaxe name");
            return this;
        }

        public Builder withAxeName(String name) {
            this.axeName = requireName(name, "Axe name");
            return this;
        }

        public Builder withShovelName(String name) {
            this.shovelName = requireName(name, "Shovel name");
            return this;
        }

        public Builder withHoeName(String name) {
            this.hoeName = requireName(name, "Hoe name");
            return this;
        }

        public SetDefinition register() {
            ItemDefinition<SwordItem> sword;
            ItemDefinition<PickaxeItem> pickaxe;
            ItemDefinition<AxeItem> axe;
            ItemDefinition<ShovelItem> shovel;
            ItemDefinition<HoeItem> hoe;

            if (this.creativeTab == null) {
                sword = Swords.create(this.register, this.swordName, this.swordProperties, this.swordTier);
                pickaxe = Pickaxes.create(this.register, this.pickaxeName, this.pickaxeProperties, this.pickaxeTier);
                axe = Axes.create(this.register, this.axeName, this.axeProperties, this.axeTier);
                shovel = Shovels.create(this.register, this.shovelName, this.shovelProperties, this.shovelTier);
                hoe = Hoes.create(this.register, this.hoeName, this.hoeProperties, this.hoeTier);
            } else {
                sword = Swords.create(this.register, this.swordName, this.swordProperties, this.swordTier, this.creativeTab);
                pickaxe = Pickaxes.create(this.register, this.pickaxeName, this.pickaxeProperties, this.pickaxeTier, this.creativeTab);
                axe = Axes.create(this.register, this.axeName, this.axeProperties, this.axeTier, this.creativeTab);
                shovel = Shovels.create(this.register, this.shovelName, this.shovelProperties, this.shovelTier, this.creativeTab);
                hoe = Hoes.create(this.register, this.hoeName, this.hoeProperties, this.hoeTier, this.creativeTab);
            }

            return new SetDefinition(this.baseName, sword, pickaxe, axe, shovel, hoe);
        }
    }

    private Equipment() {}

    public static Builder set(ModRegister register, String baseName) {
        return new Builder(register, baseName);
    }

    private static String requireName(String value, String fieldName) {
        var resolved = Objects.requireNonNull(value, fieldName + " cannot be null").trim();
        if (resolved.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be blank");
        }

        return resolved;
    }
}
