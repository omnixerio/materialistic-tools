package dev.ultreon.mods.materialistictools.fabric.datagen;

import dev.architectury.registry.registries.RegistrySupplier;
import dev.ultreon.mods.materialistictools.init.ModItems;
import dev.ultreon.mods.materialistictools.item.IngredientItems;
import dev.ultreon.mods.materialistictools.item.material.ItemMaterial;
import dev.ultreon.mods.materialistictools.item.tool.Toolset;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;

public class MaterialisticToolsModelProvider extends FabricModelProvider {
    public MaterialisticToolsModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(@NonNull BlockModelGenerators generator) {
        for (ItemMaterial value : ItemMaterial.getValues()) {
            RegistrySupplier<Block> deepslateOre = value.getDeepslateOre();
            RegistrySupplier<Block> netherOre = value.getNetherOre();
            RegistrySupplier<Block> stoneOre = value.getStoneOre();
            RegistrySupplier<Block> storageBlock = value.getStorageBlock();


            if (deepslateOre != null) {
                generator.createTrivialCube(deepslateOre.get());
                Identifier model = ModelLocationUtils.getModelLocation(deepslateOre.get());
                generator.registerSimpleItemModel(deepslateOre.get(), model);
            }
            if (netherOre != null) {
                generator.createTrivialCube(netherOre.get());
                Identifier model = ModelLocationUtils.getModelLocation(netherOre.get());
                generator.registerSimpleItemModel(netherOre.get(), model);
            }
            if (stoneOre != null) {
                generator.createTrivialCube(stoneOre.get());
                Identifier model = ModelLocationUtils.getModelLocation(stoneOre.get());
                generator.registerSimpleItemModel(stoneOre.get(), model);
            }
            if (storageBlock != null) {
                generator.createTrivialCube(storageBlock.get());
                Identifier model = ModelLocationUtils.getModelLocation(storageBlock.get());
                generator.registerSimpleItemModel(storageBlock.get(), model);
            }
        }
    }

    @Override
    public void generateItemModels(@NonNull ItemModelGenerators itemModelGenerator) {
        for (Toolset value : Toolset.values()) {
            itemModelGenerator.generateFlatItem(value.getSword().get(), ModelTemplates.FLAT_HANDHELD_ITEM);
            itemModelGenerator.generateFlatItem(value.getAxe().get(), ModelTemplates.FLAT_HANDHELD_ITEM);
            itemModelGenerator.generateFlatItem(value.getPickaxe().get(), ModelTemplates.FLAT_HANDHELD_ITEM);
            itemModelGenerator.generateFlatItem(value.getShovel().get(), ModelTemplates.FLAT_HANDHELD_ITEM);
            itemModelGenerator.generateFlatItem(value.getHoe().get(), ModelTemplates.FLAT_HANDHELD_ITEM);
            itemModelGenerator.generateFlatItem(value.getHelmet().get(), ModelTemplates.FLAT_ITEM);
            itemModelGenerator.generateFlatItem(value.getChestplate().get(), ModelTemplates.FLAT_ITEM);
            itemModelGenerator.generateFlatItem(value.getLeggings().get(), ModelTemplates.FLAT_ITEM);
            itemModelGenerator.generateFlatItem(value.getBoots().get(), ModelTemplates.FLAT_ITEM);
        }

        for (IngredientItems value : IngredientItems.values()) {
            itemModelGenerator.generateFlatItem(value.asItem(), ModelTemplates.FLAT_ITEM);
        }

        for (ItemMaterial value : ItemMaterial.getValues()) {
            if (value.getIngot() != null)
                itemModelGenerator.generateFlatItem(value.getIngot().get(), ModelTemplates.FLAT_ITEM);
            if (value.getNugget() != null)
                itemModelGenerator.generateFlatItem(value.getNugget().get(), ModelTemplates.FLAT_ITEM);
            if (value.getGem() != null)
                itemModelGenerator.generateFlatItem(value.getGem().get(), ModelTemplates.FLAT_ITEM);
            if (value.getRawMaterial() != null)
                itemModelGenerator.generateFlatItem(value.getRawMaterial().get(), ModelTemplates.FLAT_ITEM);
        }
    }

    @Override
    public @NonNull String getName() {
        return "Materialistic Tools Models";
    }
}