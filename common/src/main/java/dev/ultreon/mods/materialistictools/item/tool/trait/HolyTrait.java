package dev.ultreon.mods.materialistictools.item.tool.trait;

import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.Entity;
import dev.ultreon.mods.materialistictools.item.ItemType;
import org.jspecify.annotations.Nullable;

import java.util.Set;

public class HolyTrait extends AbstractTrait {
    public HolyTrait() {
        super(ItemType.SWORD, ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
    }

    @Override
    public TextColor getColor() {
        return TextColor.parseColor("#B0D4ED").getOrThrow();
    }

    @Override
    public float getSmiteValue(Set<ItemType> smpToolTypes, @Nullable Entity attacker) {
        return 8f;
    }
}
