package dev.ultreon.mods.materialistic.item.tool.trait;

import net.minecraft.network.chat.TextColor;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import dev.ultreon.mods.materialistic.item.ItemType;

public class ParalyzeTrait extends AbstractMobEffectTrait {
    public ParalyzeTrait() {
        super(ItemType.AXE, ItemType.BATTLE_AXE, ItemType.HAMMER);
    }

    @Override
    public TextColor getColor() {
        return TextColor.parseColor("#fff000").getOrThrow();
    }

    @Override
    public MobEffectInstance getEffectInstance() {
        return new MobEffectInstance(MobEffects.SLOWNESS, 30, 29);
    }
}
