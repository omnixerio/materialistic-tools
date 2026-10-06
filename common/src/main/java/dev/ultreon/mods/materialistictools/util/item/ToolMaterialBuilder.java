package dev.ultreon.mods.materialistictools.util.item;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.block.Block;

public class ToolMaterialBuilder {
    private TagKey<Block> incorrectBlocksForDrops;
    private int durability;
    private float efficiency;
    private float attackDamage;
    private int enchantability;
    private TagKey<Item> repairMaterial;
    private RelativeMaterial relative;

    public ToolMaterialBuilder incorrectBlocksForDrops(TagKey<Block> incorrectBlocksForDrops) {
        this.incorrectBlocksForDrops = incorrectBlocksForDrops;
        return this;
    }

    public ToolMaterialBuilder durability(int durability) {
        this.durability = durability;
        return this;
    }

    @Deprecated
    public ToolMaterialBuilder maxUses(int maxUses) {
        return durability(maxUses);
    }

    public ToolMaterialBuilder efficiency(float efficiency) {
        this.efficiency = efficiency;
        return this;
    }

    public ToolMaterialBuilder attackDamage(float attackDamage) {
        this.attackDamage = attackDamage;
        return this;
    }

    public ToolMaterialBuilder enchantability(int enchantability) {
        this.enchantability = enchantability;
        return this;
    }

    public ToolMaterialBuilder repairMaterial(TagKey<Item> repairMaterial) {
        this.repairMaterial = repairMaterial;
        return this;
    }

    public ToolMaterialBuilder sameAs(ToolMaterial toolMaterial) {
        this.relative = RelativeMaterial.sameAs(toolMaterial);
        return this;
    }

    public ToolMaterialBuilder higherThan(ToolMaterial toolMaterial) {
        this.relative = RelativeMaterial.higherThan(toolMaterial);
        return this;
    }

    public ToolMaterialInfo build() {
        if (incorrectBlocksForDrops == null) throw new IllegalStateException("Incorrect blocks for drops must be set!");
        if (relative == null) throw new IllegalStateException("Relative material must be set!");

        return new ToolMaterialInfo(
                relative,
                new ToolMaterial(
                        this.incorrectBlocksForDrops,
                        this.durability,
                        this.efficiency,
                        this.attackDamage,
                        this.enchantability,
                        this.repairMaterial
                )
        );
    }
}