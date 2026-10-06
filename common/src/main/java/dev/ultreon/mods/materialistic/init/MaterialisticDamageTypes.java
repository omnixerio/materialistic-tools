package dev.ultreon.mods.materialistic.init;

import dev.ultreon.mods.materialistic.Materialistic;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public final class MaterialisticDamageTypes {
    public static final ResourceKey<DamageType> CURSE = create("curse");
    public static final ResourceKey<DamageType> RADIATION = create("radiation");
    public static final ResourceKey<DamageType> BLEEDING = create("bleeding");

    private static ResourceKey<DamageType> create(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, Materialistic.id(name));
    }

    public static void nopInit() {

    }

    public static void configure(BootstrapContext<DamageType> context) {
        context.register(CURSE, new DamageType("materialistic.curse", 0.0F));
        context.register(RADIATION, new DamageType("materialistic.radiation", 0.0F));
        context.register(BLEEDING, new DamageType("materialistic.bleeding", 0.0F));
    }
}
