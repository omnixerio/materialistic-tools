package dev.ultreon.mods.materialistictools.item.tool;

import net.minecraft.world.level.ItemLike;
import dev.ultreon.mods.materialistictools.item.ItemType;
import dev.ultreon.mods.materialistictools.item.tool.trait.AbstractTrait;

import java.util.List;
import java.util.Set;

public interface TraitsItem extends ItemLike {
    List<AbstractTrait> getTraits();

    Set<ItemType> getItemTypes();
}
