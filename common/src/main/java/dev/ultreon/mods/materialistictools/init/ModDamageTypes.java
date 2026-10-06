package dev.ultreon.mods.materialistictools.init;

import dev.ultreon.mods.materialistictools.MaterialisticTools;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public final class ModDamageTypes {
    public static final ResourceKey<DamageType> CURSE = create("curse");
    public static final ResourceKey<DamageType> RADIATION = create("radiation");
    public static final ResourceKey<DamageType> BLEEDING = create("bleeding");

    private static ResourceKey<DamageType> create(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, MaterialisticTools.id(name));
    }

    public static void nopInit() {

    }

    public static void configure(BootstrapContext<DamageType> context) {
        context.register(CURSE, new DamageType("materialistic_tools.curse", 0.0F));
        context.register(RADIATION, new DamageType("materialistic_tools.radiation", 0.0F));
        context.register(BLEEDING, new DamageType("materialistic_tools.bleeding", 0.0F));
    }
}
