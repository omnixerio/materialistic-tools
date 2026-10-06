package dev.ultreon.mods.materialistictools.fabric.datagen;

import dev.ultreon.mods.materialistictools.init.ModDamageTypes;
import dev.ultreon.mods.materialistictools.world.gen.ores.ModOres;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import org.jspecify.annotations.NonNull;

public class MaterialisticToolsDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(MaterialisticToolsModelProvider::new);
        pack.addProvider(MaterialisticToolsRecipeProvider::new);
        pack.addProvider(MaterialisticToolsItemTagProvider::new);
        pack.addProvider(MaterialisticToolsBlockTagProvider::new);
        pack.addProvider(MaterialisticToolsBlockLootTableGenerator::new);
        pack.addProvider(MaterialisticToolsAdvancementsProvider::new);
        pack.addProvider(MaterialisticToolsWorldgenProvider::new);
        pack.addProvider(MaterialisticToolsDamageTypeProvider::new);
    }

    @Override
    public void buildRegistry(@NonNull RegistrySetBuilder registryBuilder) {
        DataGeneratorEntrypoint.super.buildRegistry(registryBuilder);
        registryBuilder.add(Registries.CONFIGURED_FEATURE, ModOres::configureFeatures);
        registryBuilder.add(Registries.PLACED_FEATURE, ModOres::configurePlacements);
        registryBuilder.add(Registries.DAMAGE_TYPE, ModDamageTypes::configure);
    }
}
