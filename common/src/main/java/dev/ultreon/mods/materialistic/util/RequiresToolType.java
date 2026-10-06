package dev.ultreon.mods.materialistic.util;

import org.jetbrains.annotations.Nullable;
import dev.ultreon.mods.materialistic.item.tool.ToolType;

public interface RequiresToolType {
    @Nullable
    ToolType getToolType();
}
