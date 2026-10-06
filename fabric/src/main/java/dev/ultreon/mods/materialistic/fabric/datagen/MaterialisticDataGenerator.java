package dev.ultreon.mods.materialistic.fabric.datagen;

import dev.ultreon.mods.materialistic.init.MaterialisticDamageTypes;
import dev.ultreon.mods.materialistic.init.MaterialisticOres;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import org.jspecify.annotations.NonNull;

public class MaterialisticDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(MaterialisticModelProvider::new);
        pack.addProvider(MaterialisticRecipeProvider::new);
        pack.addProvider(MaterialisticItemTagProvider::new);
        pack.addProvider(MaterialisticBlockTagProvider::new);
        pack.addProvider(MaterialisticBlockLootTableGenerator::new);
        pack.addProvider(MaterialisticAdvancementsProvider::new);
        pack.addProvider(MaterialisticWorldgenProvider::new);
        pack.addProvider(MaterialisticDamageTypeProvider::new);
    }

    @Override
    public void buildRegistry(@NonNull RegistrySetBuilder registryBuilder) {
        DataGeneratorEntrypoint.super.buildRegistry(registryBuilder);
        registryBuilder.add(Registries.CONFIGURED_FEATURE, MaterialisticOres::configureFeatures);
        registryBuilder.add(Registries.PLACED_FEATURE, MaterialisticOres::configurePlacements);
        registryBuilder.add(Registries.DAMAGE_TYPE, MaterialisticDamageTypes::configure);
    }
}
