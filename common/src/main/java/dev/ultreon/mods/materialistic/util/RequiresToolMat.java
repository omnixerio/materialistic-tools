package dev.ultreon.mods.materialistic.util;

import dev.ultreon.mods.materialistic.item.tool.ToolRequirement;
import org.jetbrains.annotations.Nullable;

public interface RequiresToolMat {
    @Nullable
    ToolRequirement getRequirement();
}
