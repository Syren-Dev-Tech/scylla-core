# Scylla Core Package Guide

This index links to package-level README files under `src/main/java/com/github/syren_dev_tech/scylla`.

## Package READMEs

- [blocks](src/main/java/com/github/syren_dev_tech/scylla/blocks/README.md) - Block registration helpers, block-set generators, and category-based block APIs.
- [creative_tabs](src/main/java/com/github/syren_dev_tech/scylla/creative_tabs/README.md) - Creative mode tab creation helper.
- [crops](src/main/java/com/github/syren_dev_tech/scylla/crops/README.md) - Crop, farmland, stems, and tree feature registration helpers.
- [dimensions](src/main/java/com/github/syren_dev_tech/scylla/dimensions/README.md) - Custom portal block implementations.
- [effects](src/main/java/com/github/syren_dev_tech/scylla/effects/README.md) - Particle-oriented effect types.
- [husbandry](src/main/java/com/github/syren_dev_tech/scylla/husbandry/README.md) - Egg block families and egg data configuration helpers.
- [items](src/main/java/com/github/syren_dev_tech/scylla/items/README.md) - Generic item, combat item, tool, and food registration helpers.
- [mobs](src/main/java/com/github/syren_dev_tech/scylla/mobs/README.md) - Creature builder workflow, AI goals, animation, and rendering helpers.
- [ores](src/main/java/com/github/syren_dev_tech/scylla/ores/README.md) - Fluent ore material builders for gem, dust, and metal pipelines.
- [registry](src/main/java/com/github/syren_dev_tech/scylla/registry/README.md) - Core registration context, typed registries, definitions, and feature metadata.
- [utilities](src/main/java/com/github/syren_dev_tech/scylla/utilities/README.md) - Shared utility classes for collections, config, files, and general helpers.

## JSON Examples Table Of Contents

Reference guides live in [json-examples/README.md](json-examples/README.md).

- [blockstates.cube_all.json.md](json-examples/blockstates.cube_all.json.md) - Single-variant blockstate mapping.
- [blockstates.horizontal_facing.json.md](json-examples/blockstates.horizontal_facing.json.md) - Blockstate variants for directional blocks.
- [models.block.cube_all.json.md](json-examples/models.block.cube_all.json.md) - Standard block model using one texture on all faces.
- [models.block.orientable.json.md](json-examples/models.block.orientable.json.md) - Front-facing orientable block model.
- [models.item.generated.json.md](json-examples/models.item.generated.json.md) - Flat item model for generic items.
- [models.item.handheld.json.md](json-examples/models.item.handheld.json.md) - Handheld item model for tools and weapons.
- [loot_tables.blocks.self_drop.json.md](json-examples/loot_tables.blocks.self_drop.json.md) - Block loot table that drops itself.
- [loot_tables.blocks.silk_touch.json.md](json-examples/loot_tables.blocks.silk_touch.json.md) - Silk Touch conditional ore loot table.
- [loot_tables.chests.simple.json.md](json-examples/loot_tables.chests.simple.json.md) - Weighted chest loot table.
- [recipes.crafting_shaped.json.md](json-examples/recipes.crafting_shaped.json.md) - Shaped crafting recipe.
- [recipes.crafting_shapeless.json.md](json-examples/recipes.crafting_shapeless.json.md) - Shapeless crafting recipe.
- [recipes.smelting.json.md](json-examples/recipes.smelting.json.md) - Smelting recipe.
- [recipes.stonecutting.json.md](json-examples/recipes.stonecutting.json.md) - Stonecutting conversion recipe.
- [tags.blocks.mineable_pickaxe.json.md](json-examples/tags.blocks.mineable_pickaxe.json.md) - Extend vanilla mineable-by-pickaxe tag.
- [tags.items.planks.json.md](json-examples/tags.items.planks.json.md) - Extend vanilla planks item tag.
- [worldgen.configured_feature.ore.json.md](json-examples/worldgen.configured_feature.ore.json.md) - Ore configured feature definition.
- [worldgen.placed_feature.ore.json.md](json-examples/worldgen.placed_feature.ore.json.md) - Ore placed feature frequency and height rules.
- [neoforge.biome_modifier.add_features.json.md](json-examples/neoforge.biome_modifier.add_features.json.md) - NeoForge biome modifier for feature injection.
- [advancements.husbandry.root.json.md](json-examples/advancements.husbandry.root.json.md) - Advancement definition example.
- [predicates.match_tool.json.md](json-examples/predicates.match_tool.json.md) - Reusable tool-match predicate.
- [sounds.json.md](json-examples/sounds.json.md) - Sound event registry mapping.
- [lang.en_us.json.md](json-examples/lang.en_us.json.md) - Language key/value localization file.
- [atlases.blocks.json.md](json-examples/atlases.blocks.json.md) - Texture atlas source definition.
- [damage_type.radiation.json.md](json-examples/damage_type.radiation.json.md) - Custom damage type definition.
- [pack.mcmeta.json.md](json-examples/pack.mcmeta.json.md) - Resource/data pack metadata.
