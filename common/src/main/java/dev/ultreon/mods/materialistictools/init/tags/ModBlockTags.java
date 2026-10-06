package dev.ultreon.mods.materialistictools.init.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import static dev.ultreon.mods.materialistictools.MaterialisticTools.MOD_ID;

public class ModBlockTags {
    public static final TagKey<Block> NEEDS_NETHERITE_TOOL = tag("needs_netherite_tool");
    public static final TagKey<Block> NEEDS_COBALT_TOOL = tag("needs_cobalt_tool");
    public static final TagKey<Block> NEEDS_ULTRINIUM_TOOL = tag("needs_ultrinium_tool");
    public static final TagKey<Block> NEEDS_CHUNK_TOOL = tag("needs_chunk_tool");
    public static final TagKey<Block> NEEDS_INFINITY_TOOL = tag("needs_infinity_tool");
    public static final TagKey<Block> MINEABLE_WITH_SWORD = tag("mineable_with_sword");
    public static final TagKey<Block> INCORRECT_FOR_COBALT_TOOL = tag("incorrect_for_cobalt_tool");
    public static final TagKey<Block> INCORRECT_FOR_CHUNK_TOOL = tag("incorrect_for_chunk_tool");
    public static final TagKey<Block> INCORRECT_FOR_ULTRINIUM_TOOL = tag("incorrect_for_ultrinium_tool");
    public static final TagKey<Block> INCORRECT_FOR_ANY_TOOL = tag("incorrect_for_any_tool");
    public static final TagKey<Block> INCORRECT_FOR_SILVER_TOOL = tag("incorrect_for_silver_tool");
    public static final TagKey<Block> INCORRECT_FOR_LEAD_TOOL = tag("incorrect_for_lead_tool");
    public static final TagKey<Block> INCORRECT_FOR_PLATINUM_TOOL = tag("incorrect_for_platinum_tool");
    public static final TagKey<Block> INCORRECT_FOR_ALUMINUM_TOOL = tag("incorrect_for_aluminum_tool");
    public static final TagKey<Block> INCORRECT_FOR_URANIUM_TOOL = tag("incorrect_for_uranium_tool");
    public static final TagKey<Block> INCORRECT_FOR_ELECTRUM_TOOL = tag("incorrect_for_electrum_tool");
    public static final TagKey<Block> INCORRECT_FOR_LUMIUM_TOOL = tag("incorrect_for_lumium_tool");
    public static final TagKey<Block> INCORRECT_FOR_ENDERIUM_TOOL = tag("incorrect_for_enderium_tool");
    public static final TagKey<Block> INCORRECT_FOR_AURORA_STEEL_TOOL = tag("incorrect_for_aurora_steel_tool");
    public static final TagKey<Block> INCORRECT_FOR_OBSIDIAN_TOOL = tag("incorrect_for_obsidian_tool");
    public static final TagKey<Block> INCORRECT_FOR_AMETHYST_TOOL = tag("incorrect_for_amethyst_tool");
    public static final TagKey<Block> INCORRECT_FOR_MITHRIL_TOOL = tag("incorrect_for_mithril_tool");

    private static TagKey<Block> neoForgeTag(String name) {
        return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("neoforge", name));
    }

    private static TagKey<Block> mcTag(String name) {
        return TagKey.create(Registries.BLOCK, Identifier.withDefaultNamespace(name));
    }

    private static TagKey<Block> tag(String name) {
        return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, name));
    }

    public static void init() {
        // Just initialize tags.
    }
}
