package dev.ultreon.mods.materialistictools.fabric.datagen;

import dev.architectury.registry.registries.RegistrySupplier;
import dev.ultreon.mods.materialistictools.init.tags.ModBlockTags;
import dev.ultreon.mods.materialistictools.item.material.ItemMaterial;
import dev.ultreon.mods.materialistictools.item.tool.Toolset;
import dev.ultreon.mods.materialistictools.util.item.RelativeMaterial;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jspecify.annotations.NonNull;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class MaterialisticToolsBlockTagProvider extends FabricTagsProvider<Block> {
    public MaterialisticToolsBlockTagProvider(FabricPackOutput dataGenerator) {
        super(dataGenerator, Registries.BLOCK, CompletableFuture.supplyAsync(VanillaRegistries::createLookup, net.minecraft.util.Util.backgroundExecutor()));
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.@NonNull Provider provider) {
        for (ItemMaterial value : ItemMaterial.getValues()) {
            RegistrySupplier<Block> deepslateOre = value.getDeepslateOre();
            RegistrySupplier<Block> netherOre = value.getNetherOre();
            RegistrySupplier<Block> stoneOre = value.getStoneOre();

            Optional<TagKey<Block>> oreTag = value.getOreTag();

            oreTag.ifPresentOrElse(blockTagKey -> {
                TagAppender<Block> builder = builder(blockTagKey);
                if (stoneOre != null) builder.add(stoneOre.getKey());
                if (deepslateOre != null) builder.add(deepslateOre.getKey());
                if (netherOre != null) builder.add(netherOre.getKey());

                builder(BlockTags.MINEABLE_WITH_PICKAXE)
                        .addTag(blockTagKey);
            }, () -> {
                TagAppender<Block> builder = builder(BlockTags.MINEABLE_WITH_PICKAXE);
                if (stoneOre != null) builder.add(stoneOre.getKey());
                if (netherOre != null) builder.add(netherOre.getKey());
                if (deepslateOre != null) builder.add(deepslateOre.getKey());
            });
        }

        for (Toolset value : Toolset.values()) {
            TagKey<Block> tag = value.getToolMaterial().incorrectBlocksForDrops();
            builder(tag);
            RelativeMaterial relativeMaterial = value.getRelative();
            if (relativeMaterial == null) continue;

            TagKey<Block> relativeTag = relativeMaterial.getToolMaterial().incorrectBlocksForDrops();

            switch (relativeMaterial.getRelative()) {
                case SAME_AS -> builder(tag).addTag(relativeTag);
                case HIGHER_THAN -> {
                    // A higher material harvests everything the referenced material harvests and more,
                    // so its restriction set is a strict subset that a tag reference cannot express.
                }
            }
        }

        builder(ModBlockTags.INCORRECT_FOR_COBALT_TOOL)
                .addTag(ModBlockTags.NEEDS_ULTRINIUM_TOOL)
                .addTag(ModBlockTags.NEEDS_CHUNK_TOOL)
                .addTag(ModBlockTags.NEEDS_INFINITY_TOOL);

        builder(ModBlockTags.INCORRECT_FOR_ULTRINIUM_TOOL)
                .addTag(ModBlockTags.NEEDS_CHUNK_TOOL)
                .addTag(ModBlockTags.NEEDS_INFINITY_TOOL);

        builder(ModBlockTags.INCORRECT_FOR_CHUNK_TOOL)
                .addTag(ModBlockTags.NEEDS_INFINITY_TOOL);

        builder(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)
                .addTag(ModBlockTags.NEEDS_COBALT_TOOL)
                .addTag(ModBlockTags.NEEDS_CHUNK_TOOL)
                .addTag(ModBlockTags.NEEDS_ULTRINIUM_TOOL)
                .addTag(ModBlockTags.NEEDS_INFINITY_TOOL);

        builder(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .addTag(ModBlockTags.NEEDS_COBALT_TOOL)
                .addTag(ModBlockTags.NEEDS_CHUNK_TOOL)
                .addTag(ModBlockTags.NEEDS_ULTRINIUM_TOOL)
                .addTag(ModBlockTags.NEEDS_INFINITY_TOOL);

        builder(BlockTags.INCORRECT_FOR_COPPER_TOOL)
                .addTag(ModBlockTags.NEEDS_COBALT_TOOL)
                .addTag(ModBlockTags.NEEDS_CHUNK_TOOL)
                .addTag(ModBlockTags.NEEDS_ULTRINIUM_TOOL)
                .addTag(ModBlockTags.NEEDS_INFINITY_TOOL);

        builder(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .addTag(ModBlockTags.NEEDS_COBALT_TOOL)
                .addTag(ModBlockTags.NEEDS_CHUNK_TOOL)
                .addTag(ModBlockTags.NEEDS_ULTRINIUM_TOOL)
                .addTag(ModBlockTags.NEEDS_INFINITY_TOOL);

        builder(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .addTag(ModBlockTags.NEEDS_COBALT_TOOL)
                .addTag(ModBlockTags.NEEDS_CHUNK_TOOL)
                .addTag(ModBlockTags.NEEDS_ULTRINIUM_TOOL)
                .addTag(ModBlockTags.NEEDS_INFINITY_TOOL);

        builder(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .addTag(ModBlockTags.NEEDS_COBALT_TOOL)
                .addTag(ModBlockTags.NEEDS_CHUNK_TOOL)
                .addTag(ModBlockTags.NEEDS_ULTRINIUM_TOOL)
                .addTag(ModBlockTags.NEEDS_INFINITY_TOOL);

        builder(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .addTag(ModBlockTags.NEEDS_COBALT_TOOL)
                .addTag(ModBlockTags.NEEDS_CHUNK_TOOL)
                .addTag(ModBlockTags.NEEDS_ULTRINIUM_TOOL)
                .addTag(ModBlockTags.NEEDS_INFINITY_TOOL);

        builder(ModBlockTags.NEEDS_NETHERITE_TOOL).add(
                Objects.requireNonNull(ItemMaterial.COBALT.getNetherOre()).getKey(),
                Objects.requireNonNull(ItemMaterial.COBALT.getStorageBlock()).getKey()
        );
        builder(ModBlockTags.NEEDS_COBALT_TOOL).add(
                Objects.requireNonNull(ItemMaterial.ULTRINIUM.getStoneOre()).getKey(),
                Objects.requireNonNull(ItemMaterial.ULTRINIUM.getDeepslateOre()).getKey(),
                Objects.requireNonNull(ItemMaterial.ULTRINIUM.getStorageBlock()).getKey()
        );
        builder(ModBlockTags.NEEDS_ULTRINIUM_TOOL).add(
                Objects.requireNonNull(ItemMaterial.CHUNK.getStoneOre()).getKey(),
                Objects.requireNonNull(ItemMaterial.CHUNK.getDeepslateOre()).getKey(),
                Objects.requireNonNull(ItemMaterial.CHUNK.getStorageBlock()).getKey()
        );
        builder(ModBlockTags.NEEDS_CHUNK_TOOL).add(
                Objects.requireNonNull(ItemMaterial.INFINITY.getStoneOre()).getKey(),
                Objects.requireNonNull(ItemMaterial.INFINITY.getDeepslateOre()).getKey(),
                Objects.requireNonNull(ItemMaterial.INFINITY.getStorageBlock()).getKey()
        );
        builder(ModBlockTags.NEEDS_INFINITY_TOOL).add(
                BuiltInRegistries.BLOCK.getResourceKey(Blocks.BEDROCK).orElseThrow()
        );
    }

    @Override
    public @NonNull String getName() {
        return "Materialistic Tools Block Tags";
    }
}
