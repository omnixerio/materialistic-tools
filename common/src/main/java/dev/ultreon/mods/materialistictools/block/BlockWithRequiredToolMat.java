package dev.ultreon.mods.materialistictools.block;

import net.minecraft.world.level.block.Block;
import dev.ultreon.mods.materialistictools.item.tool.ToolRequirement;
import dev.ultreon.mods.materialistictools.util.RequiresToolMat;

public class BlockWithRequiredToolMat extends Block implements RequiresToolMat {
    private final ToolRequirement toolRequirement;

    public BlockWithRequiredToolMat(ToolRequirement toolRequirement, Properties properties) {
        super(properties);
        this.toolRequirement = toolRequirement;
    }

    @Override
    public ToolRequirement getRequirement() {
        return this.toolRequirement;
    }
}
