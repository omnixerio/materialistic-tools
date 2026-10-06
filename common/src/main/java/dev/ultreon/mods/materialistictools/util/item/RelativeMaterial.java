package dev.ultreon.mods.materialistictools.util.item;

import net.minecraft.world.item.ToolMaterial;

public final class RelativeMaterial {
    private final Relative relative;
    private final ToolMaterial toolMaterial;

    private RelativeMaterial(Relative relative, ToolMaterial toolMaterial) {
        this.relative = relative;
        this.toolMaterial = toolMaterial;
    }

    public Relative getRelative() {
        return relative;
    }

    public ToolMaterial getToolMaterial() {
        return toolMaterial;
    }

    public static RelativeMaterial sameAs(ToolMaterial toolMaterial) {
        return new RelativeMaterial(Relative.SAME_AS, toolMaterial);
    }

    public static RelativeMaterial higherThan(ToolMaterial toolMaterial) {
        return new RelativeMaterial(Relative.HIGHER_THAN, toolMaterial);
    }

    public enum Relative {
        SAME_AS,
        HIGHER_THAN
    }
}
