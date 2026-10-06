package dev.ultreon.mods.materialistictools.item.tool.trait;

import net.minecraft.network.chat.TextColor;
import dev.ultreon.mods.materialistictools.item.ItemCategory;
import dev.ultreon.mods.materialistictools.item.ItemType;

import java.util.Set;

public class KnockbackTrait extends AbstractTrait {
    public KnockbackTrait() {
        super(ItemCategory.WEAPON, ItemCategory.DIGGER);
    }

    @Override
    public TextColor getColor() {
        return TextColor.parseColor("#B0D4ED").getOrThrow();
    }

    @Override
    public float getKnockback(Set<ItemType> itemTypes) {
        return 2f;
    }
}
