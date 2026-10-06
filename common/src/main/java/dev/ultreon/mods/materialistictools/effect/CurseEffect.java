package dev.ultreon.mods.materialistictools.effect;

import dev.ultreon.mods.materialistictools.MaterialisticTools;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import dev.ultreon.mods.materialistictools.init.ModDamageTypes;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

/**
 * @author XyperCode
 */
public class CurseEffect extends MobEffect {
    public CurseEffect() {
        super(MobEffectCategory.HARMFUL, 0xff00ff);
        this.addAttributeModifier(Attributes.LUCK, MaterialisticTools.id("cursed"), -4.0D, AttributeModifier.Operation.ADD_VALUE);
    }

    /**
     * This effect is not an instant effect.
     *
     * @return always {@code false}.
     */
    @Override
    public boolean isInstantaneous() {
        return false;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {
        int j = 20 >> amplification;
        if (j > 0) {
            return tickCount % j == 0;
        } else {
            return true;
        }
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel serverLevel, LivingEntity mob, int amplification) {
        RandomSource rng = mob.getRandom();
        switch (rng.nextInt(13)) {
            case 0 -> mob.hurt(new DamageSource(serverLevel.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(ModDamageTypes.CURSE)), 1f);
            case 1 -> mob.setDeltaMovement(((rng.nextDouble() - 0.5d) * 2d) * 10d, ((rng.nextDouble() - 0.5d) * 2d) * 10d, ((rng.nextDouble() - 0.5d) * 2d) * 10d);
            case 2 -> mob.setJumping(rng.nextBoolean());
            case 3 -> mob.setShiftKeyDown(rng.nextBoolean());
            case 4 -> mob.lerpMotion(new Vec3(((rng.nextDouble() - 0.5d) * 2d) * 10d, ((rng.nextDouble() - 0.5d) * 2d) * 10d, ((rng.nextDouble() - 0.5d) * 2d) * 10d));
            case 5 -> mob.teleportTo(mob.getX(), mob.getY() + rng.nextInt(20), mob.getZ());
            case 6 -> mob.addEffect(new MobEffectInstance(MobEffects.POISON, 12000, 5));
            case 7 -> mob.addEffect(new MobEffectInstance(MobEffects.WITHER, 12000, 5));
            case 8 -> mob.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 12000, 5));
            case 9 -> mob.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 12000, 5));
            case 10 -> mob.addEffect(new MobEffectInstance(MobEffects.HUNGER, 12000, 5));
            case 11 -> mob.addEffect(new MobEffectInstance(MobEffects.MINING_FATIGUE, 12000, 5));
            case 12 -> mob.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 12000, 3));
            case 13 -> mob.setRemainingFireTicks(40);
            default -> {
            }
        }
        return true;
    }
}
