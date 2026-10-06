package dev.ultreon.mods.materialistic.item.tool.trait;

import dev.ultreon.mods.materialistic.init.MaterialisticEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import dev.ultreon.mods.materialistic.item.ItemCategory;
import dev.ultreon.mods.materialistic.item.ItemType;

import java.util.function.Predicate;

public abstract class AbstractMobEffectTrait extends AbstractTrait {
    public AbstractMobEffectTrait() {
        super();
    }

    public AbstractMobEffectTrait(ItemType... types) {
        super(types);
    }

    public AbstractMobEffectTrait(ItemCategory... categories) {
        super(categories);
    }

    public AbstractMobEffectTrait(Predicate<ItemType> predicate) {
        super(predicate);
    }

    @Override
    public boolean onHitEntity(@NotNull ItemStack stack, @NotNull LivingEntity victim, LivingEntity attacker) {
        MobEffectInstance effectInstance = this.getEffectInstance();
        if (effectInstance.getEffect().is(MaterialisticEffects.RADIATION.getId())) {

        }
        victim.addEffect(effectInstance);
        return super.onHitEntity(stack, victim, attacker);
    }

    @Override
    public void onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if (entity instanceof LivingEntity) {
            MobEffectInstance effectInstance = this.getEffectInstance();
            ((LivingEntity) entity).addEffect(effectInstance);
        }
    }

    public abstract MobEffectInstance getEffectInstance();
}
