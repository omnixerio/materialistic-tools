package dev.ultreon.mods.materialistictools.advancements;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import dev.ultreon.mods.materialistictools.init.ModCriteriaTriggers;
import net.minecraft.advancements.predicates.ContextAwarePredicate;
import net.minecraft.advancements.predicates.MobEffectsPredicate;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.triggers.SimpleCriterionTrigger;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class EffectAppliedTrigger extends SimpleCriterionTrigger<EffectAppliedTrigger.TriggerInstance> {
	@Override
	public Codec<EffectAppliedTrigger.TriggerInstance> codec() {
		return EffectAppliedTrigger.TriggerInstance.CODEC;
	}

	public void trigger(final ServerPlayer player, final LivingEntity target, final Holder<MobEffect> appliedEffect) {
		this.trigger(player, triggerInstance -> triggerInstance.matches(target, appliedEffect));
	}

	public record TriggerInstance(Optional<ContextAwarePredicate> player, List<MobEffectsPredicate> effects)
		implements SimpleCriterionTrigger.SimpleInstance {
		public static final Codec<EffectAppliedTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
			i -> i.group(
					EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player),
					MobEffectsPredicate.CODEC.listOf().optionalFieldOf("effects", List.of()).forGetter(TriggerInstance::effects)
				)
				.apply(i, TriggerInstance::new)
		);

		public static Criterion<TriggerInstance> appliedEffect(final MobEffectsPredicate.Builder... effects) {
			return appliedEffect(Stream.of(effects).map(MobEffectsPredicate.Builder::build).toArray(MobEffectsPredicate[]::new));
		}

		public static Criterion<TriggerInstance> appliedEffect(final MobEffectsPredicate... effects) {
			return ModCriteriaTriggers.EFFECT_APPLIED.get()
				.createCriterion(new TriggerInstance(Optional.empty(), List.of(effects)));
		}

		public static Criterion<TriggerInstance> appliedEffect(final Holder<MobEffect>... effects) {
			Map<Holder<MobEffect>, MobEffectsPredicate.MobEffectInstancePredicate> predicates = new LinkedHashMap<>();

			for (Holder<MobEffect> effect : effects) {
				predicates.put(effect, new MobEffectsPredicate.MobEffectInstancePredicate());
			}

			return appliedEffect(new MobEffectsPredicate(predicates));
		}

		public boolean matches(final LivingEntity target, final Holder<MobEffect> appliedEffect) {
			if (this.effects.isEmpty()) {
				return true;
			}

			Map<Holder<MobEffect>, MobEffectInstance> activeEffects = target.getActiveEffectsMap();
			if (!activeEffects.containsKey(appliedEffect)) {
				return false;
			}

			for (MobEffectsPredicate predicate : this.effects) {
				if (predicate.effectMap().containsKey(appliedEffect) && predicate.matches(activeEffects)) {
					return true;
				}
			}

			return false;
		}
	}
}