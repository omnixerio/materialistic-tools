package dev.ultreon.mods.materialistic.item.tool.trait;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.effect.MobEffectInstance;
import dev.ultreon.mods.materialistic.init.MaterialisticEffects;
import dev.ultreon.mods.materialistic.item.ItemType;

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
        return new MobEffectInstance((Holder) MaterialisticEffects.BLEEDING.asHolder(), 240, 2);
    }
}
