package dev.ultreon.mods.materialistictools.fabric.datagen;

import dev.architectury.registry.registries.RegistrySupplier;
import dev.ultreon.mods.materialistictools.item.material.ItemMaterial;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class MaterialisticToolsBlockLootTableGenerator extends FabricBlockLootSubProvider {
    public MaterialisticToolsBlockLootTableGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generate() {
        for (ItemMaterial value : ItemMaterial.getValues()) {
            RegistrySupplier<Block> deepslateOre = value.getDeepslateOre();
            RegistrySupplier<Block> netherOre = value.getNetherOre();
            RegistrySupplier<Block> stoneOre = value.getStoneOre();
            RegistrySupplier<Block> storageBlock = value.getStorageBlock();

            if (deepslateOre != null) dropSelf(deepslateOre.get());
            if (netherOre != null) dropSelf(netherOre.get());
            if (stoneOre != null) dropSelf(stoneOre.get());
            if (storageBlock != null) dropSelf(storageBlock.get());
        }
    }
}
