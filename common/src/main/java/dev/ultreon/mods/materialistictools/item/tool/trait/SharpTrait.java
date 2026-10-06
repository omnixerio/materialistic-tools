package dev.ultreon.mods.materialistictools.item.tool.trait;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.effect.MobEffectInstance;
import dev.ultreon.mods.materialistictools.init.ModEffects;
import dev.ultreon.mods.materialistictools.item.ItemType;

public class SharpTrait extends AbstractMobEffectTrait {
    public SharpTrait() {
        super(ItemType.SWORD, ItemType.AXE, ItemType.PICKAXE, ItemType.SHOVEL, ItemType.HOE);
    }

    @Override
    public TextColor getColor() {
        return TextColor.parseColor("#df1300").getOrThrow();
    }

    @Override
    public MobEffectInstance getEffectInstance() {
        return new MobEffectInstance((Holder) ModEffects.BLEEDING.asHolder(), 240, 2);
    }
}
