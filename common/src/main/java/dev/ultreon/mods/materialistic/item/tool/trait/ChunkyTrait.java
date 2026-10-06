package dev.ultreon.mods.materialistic.item.tool.trait;

import net.minecraft.network.chat.TextColor;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import dev.ultreon.mods.materialistic.item.ItemCategory;

public class ChunkyTrait extends AbstractTrait {
    public ChunkyTrait() {
        super(ItemCategory.ARMOR);
    }

    @Override
    public Float onLivingDamage(ItemStack stack, LivingEntity entity, DamageSource source, float amount) {
        return amount / 500;
    }

    @Override
    public TextColor getColor() {
        return TextColor.parseColor("#00CC87").getOrThrow();
    }
}
