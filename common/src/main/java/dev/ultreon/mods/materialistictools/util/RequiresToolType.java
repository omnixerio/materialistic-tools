package dev.ultreon.mods.materialistictools.util;

import org.jetbrains.annotations.Nullable;
import dev.ultreon.mods.materialistictools.item.tool.ToolType;

public interface RequiresToolType {
    @Nullable
    ToolType getToolType();
}
