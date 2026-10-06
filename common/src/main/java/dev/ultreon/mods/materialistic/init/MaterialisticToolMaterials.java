package dev.ultreon.mods.materialistic.init;

import dev.ultreon.mods.materialistic.init.tags.MaterialisticBlockTags;
import dev.ultreon.mods.materialistic.init.tags.MaterialisticItemTags;
import dev.ultreon.mods.materialistic.util.item.ToolMaterialBuilder;
import dev.ultreon.mods.materialistic.util.item.ToolMaterialInfo;
import net.minecraft.world.item.ToolMaterial;

import static java.lang.Float.POSITIVE_INFINITY;
import static java.lang.Integer.MAX_VALUE;

public class MaterialisticToolMaterials {
    public static final ToolMaterialInfo COBALT = new ToolMaterialBuilder()
            .incorrectBlocksForDrops(MaterialisticBlockTags.INCORRECT_FOR_COBALT_TOOL)
            .higherThan(ToolMaterial.NETHERITE)
            .durability(8140)
            .efficiency(59.5F)
            .attackDamage(35f)
            .enchantability(96)
            .repairMaterial(MaterialisticItemTags.COBALT_TOOL_MATERIALS).build();
    public static final ToolMaterialInfo ULTRINIUM = new ToolMaterialBuilder()
            .incorrectBlocksForDrops(MaterialisticBlockTags.INCORRECT_FOR_ULTRINIUM_TOOL)
            .higherThan(COBALT.material())
            .durability(65842)
            .efficiency(509F)
            .attackDamage(130f)
            .enchantability(220)
            .repairMaterial(MaterialisticItemTags.ULTRINIUM_TOOL_MATERIALS).build();
    public static final ToolMaterialInfo CHUNK = new ToolMaterialBuilder()
            .incorrectBlocksForDrops(MaterialisticBlockTags.INCORRECT_FOR_CHUNK_TOOL)
            .higherThan(ULTRINIUM.material())
            .durability(8280426)
            .efficiency(2930F)
            .attackDamage(510f)
            .enchantability(900)
            .repairMaterial(MaterialisticItemTags.CHUNK_TOOL_MATERIALS).build();
    public static final ToolMaterialInfo INFINITY = new ToolMaterialBuilder()
            .incorrectBlocksForDrops(MaterialisticBlockTags.INCORRECT_FOR_ANY_TOOL)
            .higherThan(CHUNK.material())
            .durability(MAX_VALUE)
            .efficiency(POSITIVE_INFINITY)
            .attackDamage(POSITIVE_INFINITY)
            .enchantability(MAX_VALUE)
            .repairMaterial(MaterialisticItemTags.INFINITY_TOOL_MATERIALS).build();

    public static void postInit() {

    }
}
