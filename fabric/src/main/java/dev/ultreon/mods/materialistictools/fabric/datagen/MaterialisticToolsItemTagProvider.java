package dev.ultreon.mods.materialistictools.fabric.datagen;

import dev.architectury.registry.registries.RegistrySupplier;
import dev.ultreon.mods.materialistictools.item.material.ItemMaterial;
import dev.ultreon.mods.materialistictools.item.tool.Toolset;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.util.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class MaterialisticToolsItemTagProvider extends FabricTagsProvider<Item> {
    public MaterialisticToolsItemTagProvider(FabricPackOutput dataGenerator) {
        super(dataGenerator, Registries.ITEM, CompletableFuture.supplyAsync(VanillaRegistries::createLookup, Util.backgroundExecutor()));
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider provider) {
        for (ItemMaterial value : ItemMaterial.getValues()) {
            value.getIngotTag().ifPresent(t -> {
                RegistrySupplier<Item> ingot = value.getIngot();
                if (ingot != null) {
                    builder(t).add(ingot.getKey());
                }
            });
            value.getGemTag().ifPresent(t -> {
                RegistrySupplier<Item> gem = value.getGem();
                if (gem != null) {
                    builder(t).add(gem.getKey());
                }
            });
            value.getRawMaterialTag().ifPresent(t -> {
                RegistrySupplier<Item> rawMaterial = value.getRawMaterial();
                if (rawMaterial != null) {
                    builder(t).add(rawMaterial.getKey());
                }
            });
            value.getStorageBlockItemTag().ifPresent(t -> {
                RegistrySupplier<Block> storageBlock = value.getStorageBlock();
                if (storageBlock != null) {
                    builder(t).add(BuiltInRegistries.ITEM.getResourceKey(storageBlock.get().asItem()).orElse(null));
                }
            });
            value.getOreItemTag().ifPresent(t -> {
                TagAppender<Item> builder = builder(t);
                if (value.getStoneOre() != null) {
                    builder = builder.add(BuiltInRegistries.ITEM.getResourceKey(value.getStoneOre().get().asItem()).orElse(null));
                }
                if (value.getDeepslateOre() != null) {
                    builder = builder.add(BuiltInRegistries.ITEM.getResourceKey(value.getDeepslateOre().get().asItem()).orElse(null));
                }
                if (value.getNetherOre() != null) {
                    builder = builder.add(BuiltInRegistries.ITEM.getResourceKey(value.getNetherOre().get().asItem()).orElse(null));
                }
            });
        }

        builder(Toolset.ALUMINUM.getToolMaterial().repairItems())
                .addTag(ItemMaterial.ALUMINUM.getIngotTag().orElseThrow());
        builder(Toolset.CHUNK.getToolMaterial().repairItems())
                .addTag(ItemMaterial.CHUNK.getIngotTag().orElseThrow());
        builder(Toolset.COBALT.getToolMaterial().repairItems())
                .addTag(ItemMaterial.COBALT.getIngotTag().orElseThrow());
        builder(Toolset.ELECTRUM.getToolMaterial().repairItems())
                .addTag(ItemMaterial.ELECTRUM.getIngotTag().orElseThrow());
        builder(Toolset.ENDERIUM.getToolMaterial().repairItems())
                .addTag(ItemMaterial.ENDERIUM.getIngotTag().orElseThrow());
        builder(Toolset.INFINITY.getToolMaterial().repairItems())
                .addTag(ItemMaterial.INFINITY.getIngotTag().orElseThrow());
        builder(Toolset.LEAD.getToolMaterial().repairItems())
                .addTag(ItemMaterial.LEAD.getIngotTag().orElseThrow());
        builder(Toolset.LUMIUM.getToolMaterial().repairItems())
                .addTag(ItemMaterial.LUMIUM.getIngotTag().orElseThrow());
        builder(Toolset.PLATINUM.getToolMaterial().repairItems())
                .addTag(ItemMaterial.PLATINUM.getIngotTag().orElseThrow());
        builder(Toolset.REDSTONE.getToolMaterial().repairItems())
                .addTag(ItemMaterial.REDSTONE.getIngotTag().orElseThrow());
        builder(Toolset.SILVER.getToolMaterial().repairItems())
                .addTag(ItemMaterial.SILVER.getIngotTag().orElseThrow());
        builder(Toolset.ULTRINIUM.getToolMaterial().repairItems())
                .addTag(ItemMaterial.ULTRINIUM.getIngotTag().orElseThrow());
        builder(Toolset.URANIUM.getToolMaterial().repairItems())
                .addTag(ItemMaterial.URANIUM.getIngotTag().orElseThrow());
        builder(Toolset.REDSTONE.getToolMaterial().repairItems())
                .addTag(ItemMaterial.REDSTONE.getIngotTag().orElseThrow());
        builder(Toolset.MITHRIL.getToolMaterial().repairItems())
                .addTag(ItemMaterial.MITHRIL.getIngotTag().orElseThrow());
        builder(Toolset.OBSIDIAN.getToolMaterial().repairItems())
                .add(BuiltInRegistries.ITEM.getResourceKey(Items.OBSIDIAN).orElseThrow());
        builder(Toolset.AMETHYST.getToolMaterial().repairItems())
                .add(BuiltInRegistries.ITEM.getResourceKey(Items.AMETHYST_SHARD).orElseThrow());

        builder(Toolset.ALUMINUM.getArmorMaterial().repairIngredient())
                .addTag(ItemMaterial.ALUMINUM.getIngotTag().orElseThrow());
        builder(Toolset.CHUNK.getArmorMaterial().repairIngredient())
                .addTag(ItemMaterial.CHUNK.getIngotTag().orElseThrow());
        builder(Toolset.COBALT.getArmorMaterial().repairIngredient())
                .addTag(ItemMaterial.COBALT.getIngotTag().orElseThrow());
        builder(Toolset.ELECTRUM.getArmorMaterial().repairIngredient())
                .addTag(ItemMaterial.ELECTRUM.getIngotTag().orElseThrow());
        builder(Toolset.ENDERIUM.getArmorMaterial().repairIngredient())
                .addTag(ItemMaterial.ENDERIUM.getIngotTag().orElseThrow());
        builder(Toolset.INFINITY.getArmorMaterial().repairIngredient())
                .addTag(ItemMaterial.INFINITY.getIngotTag().orElseThrow());
        builder(Toolset.LEAD.getArmorMaterial().repairIngredient())
                .addTag(ItemMaterial.LEAD.getIngotTag().orElseThrow());
        builder(Toolset.LUMIUM.getArmorMaterial().repairIngredient())
                .addTag(ItemMaterial.LUMIUM.getIngotTag().orElseThrow());
        builder(Toolset.PLATINUM.getArmorMaterial().repairIngredient())
                .addTag(ItemMaterial.PLATINUM.getIngotTag().orElseThrow());
        builder(Toolset.REDSTONE.getArmorMaterial().repairIngredient())
                .addTag(ItemMaterial.REDSTONE.getIngotTag().orElseThrow());
        builder(Toolset.SILVER.getArmorMaterial().repairIngredient())
                .addTag(ItemMaterial.SILVER.getIngotTag().orElseThrow());
        builder(Toolset.ULTRINIUM.getArmorMaterial().repairIngredient())
                .addTag(ItemMaterial.ULTRINIUM.getIngotTag().orElseThrow());
        builder(Toolset.URANIUM.getArmorMaterial().repairIngredient())
                .addTag(ItemMaterial.URANIUM.getIngotTag().orElseThrow());
        builder(Toolset.OBSIDIAN.getArmorMaterial().repairIngredient())
                .add(BuiltInRegistries.ITEM.getResourceKey(Items.OBSIDIAN).orElseThrow());
        builder(Toolset.AMETHYST.getArmorMaterial().repairIngredient())
                .add(BuiltInRegistries.ITEM.getResourceKey(Items.AMETHYST_SHARD).orElseThrow());
    }

    @Override
    public @NonNull String getName() {
        return "Materialistic Tools Item Tags";
    }
}
