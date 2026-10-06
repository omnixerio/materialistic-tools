package dev.ultreon.mods.materialistic.item.tool;

import net.minecraft.world.level.ItemLike;
import dev.ultreon.mods.materialistic.item.ItemType;
import dev.ultreon.mods.materialistic.item.tool.trait.AbstractTrait;

import java.util.List;
import java.util.Set;

public interface TraitsItem extends ItemLike {
    List<AbstractTrait> getTraits();

    Set<ItemType> getItemTypes();
}
