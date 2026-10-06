package dev.ultreon.mods.materialistictools.fabric.datagen;

import dev.architectury.registry.registries.RegistrySupplier;
import dev.ultreon.mods.materialistictools.item.material.ItemMaterial;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;
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
            RegistrySupplier<Item> rawMaterial = value.getRawMaterial();
            RegistrySupplier<Item> gem = value.getGem();
            RegistrySupplier<Item> drop = rawMaterial != null ? rawMaterial : gem;

            if (drop == null) continue;

            if (deepslateOre != null) add(deepslateOre.get(), createOreDrop(deepslateOre.get(), drop.get()));
            if (netherOre != null) add(netherOre.get(), createOreDrop(netherOre.get(), drop.get()));
            if (stoneOre != null) add(stoneOre.get(), createOreDrop(stoneOre.get(), drop.get()));
            if (storageBlock != null) add(storageBlock.get(), createOreDrop(storageBlock.get(), drop.get()));
        }
    }
}
