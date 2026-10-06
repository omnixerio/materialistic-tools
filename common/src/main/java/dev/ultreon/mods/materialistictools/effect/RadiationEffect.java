package dev.ultreon.mods.materialistictools.effect;

import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import dev.ultreon.mods.materialistictools.init.ModDamageTypes;
import dev.ultreon.mods.materialistictools.item.tool.Toolset;
import org.jspecify.annotations.NonNull;

/**
 * Radiation potion effect, does one heart of damage very slowly. Can kill all living entities.
 *
 * @see Toolset#URANIUM
 */
public class RadiationEffect extends MobEffect {
    public RadiationEffect() {
        super(MobEffectCategory.HARMFUL, 0x408040);
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel serverLevel, LivingEntity mob, int amplification) {
        mob.hurtServer(serverLevel, new DamageSource(serverLevel.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(ModDamageTypes.RADIATION)), 2.0F);
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {
        int i;

        if (tickCount == 0) i = 0;
        else i = (1800 * amplification) / tickCount;

        if (i > 0) return tickCount % i == 0;
        else return true;
    }
}
