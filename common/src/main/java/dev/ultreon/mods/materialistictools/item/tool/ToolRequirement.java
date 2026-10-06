package dev.ultreon.mods.materialistictools.item.tool;

import dev.ultreon.mods.materialistictools.init.ModToolMaterials;
import dev.ultreon.mods.materialistictools.init.tags.ModBlockTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.block.Block;

public enum ToolRequirement {
    WOOD(BlockTags.INCORRECT_FOR_WOODEN_TOOL, ToolMaterial.WOOD),
    GOLD(BlockTags.INCORRECT_FOR_GOLD_TOOL, ToolMaterial.GOLD),
    STONE(BlockTags.INCORRECT_FOR_STONE_TOOL, ToolMaterial.STONE),
    IRON(BlockTags.INCORRECT_FOR_IRON_TOOL, ToolMaterial.IRON),
    DIAMOND(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, ToolMaterial.DIAMOND),
    NETHERITE(ModBlockTags.NEEDS_NETHERITE_TOOL, ToolMaterial.NETHERITE),
    COBALT(ModBlockTags.INCORRECT_FOR_COBALT_TOOL, ModToolMaterials.COBALT.material()),
    ULTRINIUM(ModBlockTags.INCORRECT_FOR_ULTRINIUM_TOOL, ModToolMaterials.ULTRINIUM.material()),
    CHUNK(ModBlockTags.INCORRECT_FOR_CHUNK_TOOL, ModToolMaterials.CHUNK.material()),
    INFINITY(ModBlockTags.INCORRECT_FOR_ANY_TOOL, ModToolMaterials.INFINITY.material());

    private final TagKey<Block> tag;
    private final ToolMaterial material;

    ToolRequirement(TagKey<Block> tag, ToolMaterial material) {
        this.tag = tag;
        this.material = material;
    }

    public TagKey<Block> getTag() {
        return this.tag;
    }

    public ToolMaterial getMaterial() {
        return this.material;
    }

    public static void registerAll() {
        // Nope
    }
}
