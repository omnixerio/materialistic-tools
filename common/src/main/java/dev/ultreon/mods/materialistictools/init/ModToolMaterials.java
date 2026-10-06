package dev.ultreon.mods.materialistictools.init;

import dev.ultreon.mods.materialistictools.init.tags.ModBlockTags;
import dev.ultreon.mods.materialistictools.init.tags.ModItemTags;
import dev.ultreon.mods.materialistictools.util.item.ToolMaterialBuilder;
import dev.ultreon.mods.materialistictools.util.item.ToolMaterialInfo;
import net.minecraft.world.item.ToolMaterial;

import static java.lang.Float.POSITIVE_INFINITY;
import static java.lang.Integer.MAX_VALUE;

public class ModToolMaterials {
    public static final ToolMaterialInfo COBALT = new ToolMaterialBuilder()
            .incorrectBlocksForDrops(ModBlockTags.INCORRECT_FOR_COBALT_TOOL)
            .higherThan(ToolMaterial.NETHERITE)
            .durability(8140)
            .efficiency(59.5F)
            .attackDamage(35f)
            .enchantability(96)
            .repairMaterial(ModItemTags.COBALT_TOOL_MATERIALS).build();
    public static final ToolMaterialInfo ULTRINIUM = new ToolMaterialBuilder()
            .incorrectBlocksForDrops(ModBlockTags.INCORRECT_FOR_ULTRINIUM_TOOL)
            .higherThan(COBALT.material())
            .durability(65842)
            .efficiency(509F)
            .attackDamage(130f)
            .enchantability(220)
            .repairMaterial(ModItemTags.ULTRINIUM_TOOL_MATERIALS).build();
    public static final ToolMaterialInfo CHUNK = new ToolMaterialBuilder()
            .incorrectBlocksForDrops(ModBlockTags.INCORRECT_FOR_CHUNK_TOOL)
            .higherThan(ULTRINIUM.material())
            .durability(8280426)
            .efficiency(2930F)
            .attackDamage(510f)
            .enchantability(900)
            .repairMaterial(ModItemTags.CHUNK_TOOL_MATERIALS).build();
    public static final ToolMaterialInfo INFINITY = new ToolMaterialBuilder()
            .incorrectBlocksForDrops(ModBlockTags.INCORRECT_FOR_ANY_TOOL)
            .higherThan(CHUNK.material())
            .durability(MAX_VALUE)
            .efficiency(POSITIVE_INFINITY)
            .attackDamage(POSITIVE_INFINITY)
            .enchantability(MAX_VALUE)
            .repairMaterial(ModItemTags.INFINITY_TOOL_MATERIALS).build();

    public static void postInit() {

    }
}
