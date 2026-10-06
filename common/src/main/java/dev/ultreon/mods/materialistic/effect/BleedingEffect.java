package dev.ultreon.mods.materialistic.effect;

import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import dev.ultreon.mods.materialistic.init.MaterialisticDamageTypes;
import dev.ultreon.mods.materialistic.item.tool.Toolset;
import org.jspecify.annotations.NonNull;

/**
 * Radiation potion effect, does one heart of damage very slowly. Can kill all living entities.
 *
 * @see Toolset#ALUMINUM
 * @see Toolset#OBSIDIAN
 */
public class BleedingEffect extends MobEffect {
    public BleedingEffect() {
        super(MobEffectCategory.HARMFUL, 0xbfbfbf);
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel serverLevel, LivingEntity mob, int amplification) {
        mob.hurtServer(serverLevel, new DamageSource(serverLevel.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(MaterialisticDamageTypes.BLEEDING)), 1f);
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {
        int j = 35 >> amplification;
        if (j > 0)
            return tickCount % j == 0;
        else
            return true;
    }
}
