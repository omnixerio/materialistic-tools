package dev.ultreon.mods.materialistictools.init.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static dev.ultreon.mods.materialistictools.MaterialisticTools.MOD_ID;

public class ModItemTags {
    public static final TagKey<Item> RAW_MEAT = tag("food/raw_meat");
    public static final TagKey<Item> COOKED_MEAT = tag("food/cooked_meat");
    public static final TagKey<Item> MEAT = tag("food/meat");
    public static final TagKey<Item> ROD_URANIUM = tag("rod/uranium");
    public static final TagKey<Item> OBSIDIAN = tag("obsidian");
    public static final TagKey<Item> REPAIRS_SILVER_ARMOR = tag("repairs_silver_armor");
    public static final TagKey<Item> SILVER_TOOL_MATERIALS = tag("silver_tool_materials");
    public static final TagKey<Item> REPAIRS_LEAD_ARMOR = tag("repairs_lead_armor");
    public static final TagKey<Item> LEAD_TOOL_MATERIALS = tag("lead_tool_materials");
    public static final TagKey<Item> REPAIRS_PLATINUM_ARMOR = tag("repairs_platinum_armor");
    public static final TagKey<Item> PLATINUM_TOOL_MATERIALS = tag("platinum_tool_materials");
    public static final TagKey<Item> REPAIRS_ALUMINUM_ARMOR = tag("repairs_aluminum_armor");
    public static final TagKey<Item> ALUMINUM_TOOL_MATERIALS = tag("aluminum_tool_materials");
    public static final TagKey<Item> REPAIRS_URANIUM_ARMOR = tag("repairs_uranium_armor");
    public static final TagKey<Item> URANIUM_TOOL_MATERIALS = tag("uranium_tool_materials");
    public static final TagKey<Item> REPAIRS_ELECTRUM_ARMOR = tag("repairs_electrum_armor");
    public static final TagKey<Item> ELECTRUM_TOOL_MATERIALS = tag("electrum_tool_materials");
    public static final TagKey<Item> REPAIRS_LUMIUM_ARMOR = tag("repairs_lumium_armor");
    public static final TagKey<Item> LUMIUM_TOOL_MATERIALS = tag("lumium_tool_materials");
    public static final TagKey<Item> REPAIRS_ENDERIUM_ARMOR = tag("repairs_enderium_armor");
    public static final TagKey<Item> ENDERIUM_TOOL_MATERIALS = tag("enderium_tool_materials");
//    public static final TagKey<Item> REPAIRS_AURORA_STEEL_ARMOR = tag("repairs_aurora_steel_armor");
//    public static final TagKey<Item> AURORA_STEEL_TOOL_MATERIALS = tag("aurora_steel_tool_materials");
    public static final TagKey<Item> REPAIRS_OBSIDIAN_ARMOR = tag("repairs_obsidian_armor");
    public static final TagKey<Item> OBSIDIAN_TOOL_MATERIALS = tag("obsidian_tool_materials");
    public static final TagKey<Item> REPAIRS_COBALT_ARMOR = tag("repairs_cobalt_armor");
    public static final TagKey<Item> COBALT_TOOL_MATERIALS = tag("cobalt_tool_materials");
    public static final TagKey<Item> REPAIRS_ULTRINIUM_ARMOR = tag("repairs_ultrinium_armor");
    public static final TagKey<Item> ULTRINIUM_TOOL_MATERIALS = tag("ultrinium_tool_materials");
    public static final TagKey<Item> REPAIRS_CHUNK_ARMOR = tag("repairs_chunk_armor");
    public static final TagKey<Item> CHUNK_TOOL_MATERIALS = tag("chunk_tool_materials");
    public static final TagKey<Item> REPAIRS_INFINITY_ARMOR = tag("repairs_infinity_armor");
    public static final TagKey<Item> INFINITY_TOOL_MATERIALS = tag("infinity_tool_materials");
    public static final TagKey<Item> REPAIRS_AMETHYST_ARMOR = tag("repairs_amethyst_armor");
    public static final TagKey<Item> AMETHYST_TOOL_MATERIALS = tag("amethyst_tool_materials");
    public static final TagKey<Item> REPAIRS_REDSTONE_ARMOR = tag("repairs_redstone_armor");
    public static final TagKey<Item> REDSTONE_TOOL_MATERIALS = tag("redstone_tool_materials");
    public static final TagKey<Item> REPAIRS_MITHRIL_ARMOR = tag("repairs_mithril_armor");
    public static final TagKey<Item> MITHRIL_TOOL_MATERIALS = tag("mithril_tool_materials");

    private static TagKey<Item> neoForgeTag(String name) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("neoforge", name));
    }

    private static TagKey<Item> mcTag(String name) {
        return TagKey.create(Registries.ITEM, Identifier.withDefaultNamespace(name));
    }

    private static TagKey<Item> tag(String name) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MOD_ID, name));
    }

    public static void init() {
        // Just initialize tags.
    }
}
