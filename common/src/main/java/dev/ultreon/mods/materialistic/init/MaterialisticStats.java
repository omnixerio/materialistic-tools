package dev.ultreon.mods.materialistic.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.ultreon.mods.materialistic.Materialistic;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.stats.StatFormatter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MaterialisticStats {
    private static final DeferredRegister<Identifier> REGISTER = DeferredRegister.create(Materialistic.MOD_ID, Registries.CUSTOM_STAT);
    private static final Map<String, StatFormatter> FORMATTERS = new HashMap<>();

//    public static final RegistrySupplier<Identifier> INFINITY_KILL = register("infinity_kill", Integer::toString, () -> MaterialisticTools.res("stats/infinity_kill"));

    private static RegistrySupplier<Identifier> register(String name, StatFormatter formatter, Supplier<Identifier> toRegister) {
        MaterialisticStats.FORMATTERS.put(name, formatter);
        return REGISTER.register(name, toRegister);
    }

    public static void register() {
        REGISTER.register();
    }
}
