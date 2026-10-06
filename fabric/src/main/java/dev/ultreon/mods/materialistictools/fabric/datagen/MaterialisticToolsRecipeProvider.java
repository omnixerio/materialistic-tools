package dev.ultreon.mods.materialistictools.fabric.datagen;

import com.mojang.datafixers.util.Either;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.ultreon.mods.materialistictools.init.ModItems;
import dev.ultreon.mods.materialistictools.item.IngredientItems;
import dev.ultreon.mods.materialistictools.item.material.ItemMaterial;
import dev.ultreon.mods.materialistictools.item.tool.Toolset;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import static net.minecraft.advancements.triggers.InventoryChangeTrigger.TriggerInstance.hasItems;

public class MaterialisticToolsRecipeProvider extends FabricRecipeProvider {
    public MaterialisticToolsRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registryLookup, @NonNull RecipeOutput exporter) {
        return new RecipeProvider(registryLookup, exporter) {
            @Override
            public void buildRecipes() {
                for (Toolset value : Toolset.values()) {
                    RegistrySupplier<Item> sword = value.getSword();
                    RegistrySupplier<Item> pickaxe = value.getPickaxe();
                    RegistrySupplier<AxeItem> axe = value.getAxe();
                    RegistrySupplier<ShovelItem> shovel = value.getShovel();
                    RegistrySupplier<HoeItem> hoe = value.getHoe();

                    RegistrySupplier<Item> helmet = value.getHelmet();
                    RegistrySupplier<Item> chestplate = value.getChestplate();
                    RegistrySupplier<Item> leggings = value.getLeggings();
                    RegistrySupplier<Item> boots = value.getBoots();

                    Supplier<Either<Item, TagKey<Item>>> baseMaterial = value.getBaseMaterial();
                    baseMaterial.get().ifLeft(item -> {
                        shaped(RecipeCategory.COMBAT, sword.get())
                                .define('#', item)
                                .define('/', value.getHandleMaterial().get())
                                .pattern("#")
                                .pattern("#")
                                .pattern("/")
                                .unlockedBy(getHasName(item), this.has(item))
                                .save(this.output);
                        shaped(RecipeCategory.TOOLS, pickaxe.get())
                                .define('#', item)
                                .define('/', value.getHandleMaterial().get())
                                .pattern("###")
                                .pattern(" / ")
                                .pattern(" / ")
                                .unlockedBy(getHasName(item), this.has(item))
                                .save(this.output);
                        shaped(RecipeCategory.TOOLS, axe.get())
                                .define('#', item)
                                .define('/', value.getHandleMaterial().get())
                                .pattern("##")
                                .pattern("#/")
                                .pattern(" /")
                                .unlockedBy(getHasName(item), this.has(item))
                                .save(this.output);
                        shaped(RecipeCategory.TOOLS, shovel.get())
                                .define('#', item)
                                .define('/', value.getHandleMaterial().get())
                                .pattern("#")
                                .pattern("/")
                                .pattern("/")
                                .unlockedBy(getHasName(item), this.has(item))
                                .save(this.output);
                        shaped(RecipeCategory.TOOLS, hoe.get())
                                .define('#', item)
                                .define('/', value.getHandleMaterial().get())
                                .pattern("##")
                                .pattern(" /")
                                .pattern(" /")
                                .unlockedBy(getHasName(item), this.has(item))
                                .save(this.output);

                        Supplier<Either<Item, TagKey<Item>>> element = value.getElement();
                        Either<Item, TagKey<Item>> itemTagKeyEither;
                        if (element != null) {
                            itemTagKeyEither = element.get();
                            itemTagKeyEither.ifLeft(elemItem -> {
                                shaped(RecipeCategory.TOOLS, helmet.get())
                                        .define('#', item)
                                        .define('O', elemItem)
                                        .pattern("#O#")
                                        .pattern("# #")
                                        .unlockedBy(getHasName(item), this.has(item))
                                        .save(this.output);
                                shaped(RecipeCategory.TOOLS, chestplate.get())
                                        .define('#', item)
                                        .define('O', elemItem)
                                        .pattern("# #")
                                        .pattern("#O#")
                                        .pattern("###")
                                        .unlockedBy(getHasName(item), this.has(item))
                                        .save(this.output);
                                shaped(RecipeCategory.TOOLS, leggings.get())
                                        .define('#', item)
                                        .define('O', elemItem)
                                        .pattern("#O#")
                                        .pattern("# #")
                                        .pattern("# #")
                                        .unlockedBy(getHasName(item), this.has(item))
                                        .save(this.output);
                                shaped(RecipeCategory.TOOLS, boots.get())
                                        .define('#', item)
                                        .define('O', elemItem)
                                        .pattern("# #")
                                        .pattern("O O")
                                        .unlockedBy(getHasName(item), this.has(item))
                                        .save(this.output);
                            }).ifRight(elemTag -> {
                                shaped(RecipeCategory.TOOLS, helmet.get())
                                        .define('#', item)
                                        .define('O', elemTag)
                                        .pattern("#O#")
                                        .pattern("# #")
                                        .unlockedBy(getHasName(item), this.has(item))
                                        .save(this.output);
                                shaped(RecipeCategory.TOOLS, chestplate.get())
                                        .define('#', item)
                                        .define('O', elemTag)
                                        .pattern("# #")
                                        .pattern("#O#")
                                        .pattern("###")
                                        .unlockedBy(getHasName(item), this.has(item))
                                        .save(this.output);
                                shaped(RecipeCategory.TOOLS, leggings.get())
                                        .define('#', item)
                                        .define('O', elemTag)
                                        .pattern("#O#")
                                        .pattern("# #")
                                        .pattern("# #")
                                        .unlockedBy(getHasName(item), this.has(item))
                                        .save(this.output);
                                shaped(RecipeCategory.TOOLS, boots.get())
                                        .define('#', item)
                                        .define('O', elemTag)
                                        .pattern("# #")
                                        .pattern("O O")
                                        .unlockedBy(getHasName(item), this.has(item))
                                        .save(this.output);
                            });
                        } else {
                            shaped(RecipeCategory.TOOLS, helmet.get())
                                    .define('#', item)
                                    .pattern("###")
                                    .pattern("# #")
                                    .unlockedBy(getHasName(item), this.has(item))
                                    .save(this.output);
                            shaped(RecipeCategory.TOOLS, chestplate.get())
                                    .define('#', item)
                                    .pattern("# #")
                                    .pattern("###")
                                    .pattern("###")
                                    .unlockedBy(getHasName(item), this.has(item))
                                    .save(this.output);
                            shaped(RecipeCategory.TOOLS, leggings.get())
                                    .define('#', item)
                                    .pattern("###")
                                    .pattern("# #")
                                    .pattern("# #")
                                    .unlockedBy(getHasName(item), this.has(item))
                                    .save(this.output);
                            shaped(RecipeCategory.TOOLS, boots.get())
                                    .define('#', item)
                                    .pattern("# #")
                                    .pattern("# #")
                                    .unlockedBy(getHasName(item), this.has(item))
                                    .save(this.output);
                        }
                    }).ifRight(tag -> {
                        shaped(RecipeCategory.COMBAT, sword.get())
                                .define('#', tag)
                                .define('/', value.getHandleMaterial().get())
                                .pattern("#")
                                .pattern("#")
                                .pattern("/")
                                .unlockedBy("has_" + tag.location(), this.has(tag))
                                .save(this.output);
                        shaped(RecipeCategory.TOOLS, pickaxe.get())
                                .define('#', tag)
                                .define('/', value.getHandleMaterial().get())
                                .pattern("###")
                                .pattern(" / ")
                                .pattern(" / ")
                                .unlockedBy("has_" + tag.location(), this.has(tag))
                                .save(this.output);
                        shaped(RecipeCategory.TOOLS, axe.get())
                                .define('#', tag)
                                .define('/', value.getHandleMaterial().get())
                                .pattern("##")
                                .pattern("#/")
                                .pattern(" /")
                                .unlockedBy("has_" + tag.location(), this.has(tag))
                                .save(this.output);
                        shaped(RecipeCategory.TOOLS, shovel.get())
                                .define('#', tag)
                                .define('/', value.getHandleMaterial().get())
                                .pattern("#")
                                .pattern("/")
                                .pattern("/")
                                .unlockedBy("has_" + tag.location(), this.has(tag))
                                .save(this.output);
                        shaped(RecipeCategory.TOOLS, hoe.get())
                                .define('#', tag)
                                .define('/', value.getHandleMaterial().get())
                                .pattern("##")
                                .pattern(" /")
                                .pattern(" /")
                                .unlockedBy("has_" + tag.location(), this.has(tag))
                                .save(this.output);

                        Supplier<Either<Item, TagKey<Item>>> element = value.getElement();
                        Either<Item, TagKey<Item>> itemTagKeyEither;
                        if (element != null) {
                            itemTagKeyEither = element.get();
                            itemTagKeyEither.ifLeft(elemItem -> {
                                shaped(RecipeCategory.TOOLS, helmet.get())
                                        .define('#', tag)
                                        .define('O', elemItem)
                                        .pattern("#O#")
                                        .pattern("# #")
                                        .unlockedBy("has_" + tag.location(), this.has(tag))
                                        .save(this.output);
                                shaped(RecipeCategory.TOOLS, chestplate.get())
                                        .define('#', tag)
                                        .define('O', elemItem)
                                        .pattern("# #")
                                        .pattern("#O#")
                                        .pattern("###")
                                        .unlockedBy("has_" + tag.location(), this.has(tag))
                                        .save(this.output);
                                shaped(RecipeCategory.TOOLS, leggings.get())
                                        .define('#', tag)
                                        .define('O', elemItem)
                                        .pattern("#O#")
                                        .pattern("# #")
                                        .pattern("# #")
                                        .unlockedBy("has_" + tag.location(), this.has(tag))
                                        .save(this.output);
                                shaped(RecipeCategory.TOOLS, boots.get())
                                        .define('#', tag)
                                        .define('O', elemItem)
                                        .pattern("# #")
                                        .pattern("O O")
                                        .unlockedBy("has_" + tag.location(), this.has(tag))
                                        .save(this.output);
                            }).ifRight(elemTag -> {
                                shaped(RecipeCategory.TOOLS, helmet.get())
                                        .define('#', tag)
                                        .define('O', elemTag)
                                        .pattern("#O#")
                                        .pattern("# #")
                                        .unlockedBy("has_" + tag.location(), this.has(tag))
                                        .save(this.output);
                                shaped(RecipeCategory.TOOLS, chestplate.get())
                                        .define('#', tag)
                                        .define('O', elemTag)
                                        .pattern("# #")
                                        .pattern("#O#")
                                        .pattern("###")
                                        .unlockedBy("has_" + tag.location(), this.has(tag))
                                        .save(this.output);
                                shaped(RecipeCategory.TOOLS, leggings.get())
                                        .define('#', tag)
                                        .define('O', elemTag)
                                        .pattern("#O#")
                                        .pattern("# #")
                                        .pattern("# #")
                                        .unlockedBy("has_" + tag.location(), this.has(tag))
                                        .save(this.output);
                                shaped(RecipeCategory.TOOLS, boots.get())
                                        .define('#', tag)
                                        .define('O', elemTag)
                                        .pattern("# #")
                                        .pattern("O O")
                                        .unlockedBy("has_" + tag.location(), this.has(tag))
                                        .save(this.output);
                            });
                        } else {
                            shaped(RecipeCategory.TOOLS, helmet.get())
                                        .define('#', tag)
                                        .pattern("###")
                                        .pattern("# #")
                                        .unlockedBy("has_" + tag.location(), this.has(tag))
                                        .save(this.output);
                            shaped(RecipeCategory.TOOLS, chestplate.get())
                                        .define('#', tag)
                                        .pattern("# #")
                                        .pattern("###")
                                        .pattern("###")
                                        .unlockedBy("has_" + tag.location(), this.has(tag))
                                        .save(this.output);
                            shaped(RecipeCategory.TOOLS, leggings.get())
                                        .define('#', tag)
                                        .pattern("###")
                                        .pattern("# #")
                                        .pattern("# #")
                                        .unlockedBy("has_" + tag.location(), this.has(tag))
                                        .save(this.output);
                            shaped(RecipeCategory.TOOLS, boots.get())
                                        .define('#', tag)
                                        .pattern("# #")
                                        .pattern("# #")
                                        .unlockedBy("has_" + tag.location(), this.has(tag))
                                        .save(this.output);
                        }
                    });
                }

                for (ItemMaterial value : ItemMaterial.getValues()) {
                    @Nullable RegistrySupplier<Item> ingot = value.getIngot();
                    @Nullable RegistrySupplier<Item> gem = value.getIngot();
                    @Nullable RegistrySupplier<Item> rawMaterial = value.getRawMaterial();
                    @Nullable RegistrySupplier<Block> storageBlock = value.getStorageBlock();
                    @Nullable RegistrySupplier<Block> rawMaterialBlock = value.getRawMaterialBlock();
                    if (ingot != null) {
                        oreSmelting(value.getSmeltables(), RecipeCategory.MISC, CookingBookCategory.MISC, ingot.get(), value.getExperience(), value.getCookingTime(), value.getRegistryName().getPath() + "_ingot");
                        oreBlasting(value.getSmeltables(), RecipeCategory.MISC, CookingBookCategory.MISC, ingot.get(), value.getExperience(), value.getCookingTime() / 2, value.getRegistryName().getPath() + "_ingot");
                    } else if (gem != null) {
                        oreSmelting(value.getSmeltables(), RecipeCategory.MISC, CookingBookCategory.MISC, gem.get(), value.getExperience(), value.getCookingTime(), value.getRegistryName().getPath());
                        oreBlasting(value.getSmeltables(), RecipeCategory.MISC, CookingBookCategory.MISC, gem.get(), value.getExperience(), value.getCookingTime() / 2, value.getRegistryName().getPath());
                    }

                    if (storageBlock != null && ingot != null) {
                        nineBlockStorageRecipes(RecipeCategory.MISC, ingot.get(), RecipeCategory.BUILDING_BLOCKS, storageBlock.get());
                    } else if (storageBlock != null && gem != null) {
                        nineBlockStorageRecipes(RecipeCategory.MISC, gem.get(), RecipeCategory.BUILDING_BLOCKS, storageBlock.get());
                    }
                    if (rawMaterialBlock != null && rawMaterial != null) {
                        nineBlockStorageRecipes(RecipeCategory.MISC, rawMaterial.get(), RecipeCategory.BUILDING_BLOCKS, rawMaterialBlock.get());
                    }
                }

                shaped(RecipeCategory.MISC, IngredientItems.FIRE_ROD, 1)
                        .define('#', IngredientItems.FIRE_POWDER)
                        .define('/', Items.STICK)
                        .pattern(" # ")
                        .pattern("#/#")
                        .pattern(" # ")
                        .unlockedBy(getHasName(IngredientItems.FIRE_POWDER), this.has(IngredientItems.FIRE_POWDER))
                        .save(output);
                shapeless(RecipeCategory.MISC, IngredientItems.FIRE_POWDER, 4)
                        .requires(IngredientItems.FIRE_GEM)
                        .unlockedBy(getHasName(IngredientItems.FIRE_GEM), this.has(IngredientItems.FIRE_GEM))
                        .save(output);
                shapeless(RecipeCategory.MISC, IngredientItems.FIRE_GEM, 1)
                        .requires(Items.DIAMOND, 4)
                        .requires(Items.BLAZE_POWDER, 4)
                        .unlockedBy(getHasName(Items.BLAZE_POWDER), this.has(Items.BLAZE_POWDER))
                        .save(output);
                shaped(RecipeCategory.MISC, IngredientItems.URANIUM_ROD, 4)
                        .define('/', ItemMaterial.URANIUM.getIngotTag().orElseThrow())
                        .pattern("/")
                        .pattern("/")
                        .unlockedBy(getHasName(ItemMaterial.URANIUM.getIngot().get()), this.has(ItemMaterial.URANIUM.getIngotTag().get()))
                        .save(output);

                oreSmelting(List.of(Items.REDSTONE), RecipeCategory.MISC, CookingBookCategory.MISC, ItemMaterial.REDSTONE.getIngot().get(), 0.8F, 200, "redstone_ingot");
                oreBlasting(List.of(Items.REDSTONE), RecipeCategory.MISC, CookingBookCategory.MISC, ItemMaterial.REDSTONE.getIngot().get(), 0.8F, 100, "redstone_ingot");
            }

            private void twoByTwoTransform(RecipeCategory category, Block result, Block ingredient) {
                this.shaped(category, result, 4)
                        .define('#', ingredient)
                        .pattern("##")
                        .pattern("##")
                        .unlockedBy(getHasName(ingredient), this.has(ingredient))
                        .save(this.output);
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return "OddzRecipeProvider";
    }
}