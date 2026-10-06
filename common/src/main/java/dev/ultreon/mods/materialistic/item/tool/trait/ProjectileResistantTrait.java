package dev.ultreon.mods.materialistic.item.tool.trait;

import net.minecraft.network.chat.TextColor;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import dev.ultreon.mods.materialistic.item.ItemCategory;

public class ProjectileResistantTrait extends AbstractTrait {
    public ProjectileResistantTrait() {
        super(ItemCategory.ARMOR);
    }

    @Override
    public TextColor getColor() {
        return TextColor.parseColor("#6B5232").getOrThrow();
    }

    @Override
    public Float onLivingDamage(ItemStack stack, LivingEntity entity, DamageSource source, float amount) {
        super.onLivingDamage(stack, entity, source, amount);

        if (source.is(DamageTypeTags.IS_PROJECTILE) && !source.is(DamageTypeTags.BYPASSES_ENCHANTMENTS)) {
            return null;
        }
        return amount;
    }
}
