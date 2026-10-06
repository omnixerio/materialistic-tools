package dev.ultreon.mods.materialistic.item.tool.trait;

import net.minecraft.network.chat.TextColor;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import dev.ultreon.mods.materialistic.item.ItemCategory;

public class WitherTrait extends AbstractMobEffectTrait {
    public WitherTrait() {
        super(ItemCategory.WEAPON, ItemCategory.DIGGER, ItemCategory.BLOCK_UTIL);
    }

    @Override
    public TextColor getColor() {
        return TextColor.parseColor("#3F3F3F").getOrThrow();
    }

    @Override
    public MobEffectInstance getEffectInstance() {
        return new MobEffectInstance(MobEffects.WITHER, 50, 1);
    }
}
