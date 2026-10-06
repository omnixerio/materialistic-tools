package dev.ultreon.mods.materialistictools.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.ultreon.mods.materialistictools.advancements.EffectAppliedTrigger;
import net.minecraft.advancements.triggers.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Supplier;

import static dev.ultreon.mods.materialistictools.MaterialisticTools.MOD_ID;

public class ModCriteriaTriggers {
    @ApiStatus.Internal
    public static final DeferredRegister<CriterionTrigger<?>> REGISTER = DeferredRegister.create(MOD_ID, Registries.TRIGGER_TYPE);

    public static final RegistrySupplier<EffectAppliedTrigger> EFFECT_APPLIED = register("effect_applied", EffectAppliedTrigger::new);

    private static <T extends CriterionTrigger<?>> RegistrySupplier<T> register(String id, Supplier<T> supplier) {
        return REGISTER.register(id, supplier);
    }
}
