package dev.ultreon.mods.materialistictools.item.tool.trait;

import net.minecraft.network.chat.TextColor;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import dev.ultreon.mods.materialistictools.item.ItemCategory;

public class PoisonTrait extends AbstractMobEffectTrait {
    public PoisonTrait() {
        super(ItemCategory.WEAPON);
    }

    @Override
    public TextColor getColor() {
        return TextColor.parseColor("#007F3F").getOrThrow();
    }

    @Override
    public MobEffectInstance getEffectInstance() {
        return new MobEffectInstance(MobEffects.POISON, 50, 1);
    }
}
