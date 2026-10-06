package dev.ultreon.mods.materialistictools.util;

import dev.ultreon.mods.materialistictools.item.tool.ToolRequirement;
import org.jetbrains.annotations.Nullable;

public interface RequiresToolMat {
    @Nullable
    ToolRequirement getRequirement();
}
