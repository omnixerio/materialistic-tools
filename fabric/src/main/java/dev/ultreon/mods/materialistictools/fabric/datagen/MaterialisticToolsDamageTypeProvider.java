package dev.ultreon.mods.materialistictools.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class MaterialisticToolsDamageTypeProvider extends FabricDynamicRegistryProvider {
    public MaterialisticToolsDamageTypeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.DAMAGE_TYPE));
    }

    @Override
    public @NonNull String getName() {
        return "Materialistic Tools Damage Types";
    }
}