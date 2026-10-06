package dev.ultreon.mods.materialistic.item.material;

import dev.architectury.registry.registries.RegistrySupplier;
import dev.ultreon.mods.materialistic.Materialistic;
import dev.ultreon.mods.materialistic.block.BlockWithRequiredToolMat;
import dev.ultreon.mods.materialistic.block.OreBlock;
import dev.ultreon.mods.materialistic.init.MaterialisticBlocks;
import dev.ultreon.mods.materialistic.init.MaterialisticItems;
import dev.ultreon.mods.materialistic.item.tool.ToolRequirement;
import dev.ultreon.mods.materialistic.item.tool.Toolset;
import dev.ultreon.mods.materialistic.world.gen.ores.ItemMaterialOre;
import dev.ultreon.mods.materialistic.init.MaterialisticOres;
import dev.ultreon.mods.materialistic.world.gen.ores.Ore;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static dev.ultreon.mods.materialistic.Materialistic.MOD_ID;

@SuppressWarnings("unused")
public class ItemMaterial implements BaseItemMaterial, Predicate<Holder<Item>> {
    // Class preload.
    private static final List<ItemMaterial> all = new ArrayList<>();
    private static final Map<Identifier, ItemMaterial> map = new HashMap<>();

    // Vanilla stuff
    public static final ItemMaterial GOLD = new ItemMaterial(builder("gold").vanillaMetal(true, true));
    public static final ItemMaterial IRON = new ItemMaterial(builder("iron").vanillaMetal(true, true));
    public static final ItemMaterial COPPER = new ItemMaterial(builder("copper").vanillaMetal(true, false));
    public static final ItemMaterial NETHERITE = new ItemMaterial(builder("netherite").vanillaMetal(false, false));
    public static final ItemMaterial DIAMOND = new ItemMaterial(builder("diamond").vanillaGem(true, true));
    public static final ItemMaterial EMERALD = new ItemMaterial(builder("emerald").vanillaGem(true, true));
    public static final ItemMaterial LAPIS = new ItemMaterial(builder("lapis").vanillaGem(false, false));
    public static final ItemMaterial QUARTZ = new ItemMaterial(builder("quartz").vanillaGem(false, false));
    public static final ItemMaterial AMETHYST = new ItemMaterial(builder("amethyst").vanillaGem(false, false));
    public static final ItemMaterial PRISMARINE = new ItemMaterial(builder("prismarine").vanillaGem(false, false));

    // Metals
    public static final ItemMaterial COMPRESSED_IRON = new ItemMaterial(builder("compressed_iron").ingot());
    public static final ItemMaterial SILVER = new ItemMaterial(overworldOre("silver", MaterialisticOres.SILVER).experience(0.7F));
    public static final ItemMaterial LEAD = new ItemMaterial(overworldOre("lead", MaterialisticOres.LEAD).experience(0.5F));
    public static final ItemMaterial PLATINUM = new ItemMaterial(overworldOre("platinum", MaterialisticOres.PLATINUM).experience(0.9F));
    public static final ItemMaterial ALUMINUM = new ItemMaterial(overworldOre("aluminum", MaterialisticOres.BAUXITE).experience(0.6F), Materialistic.id("bauxite"));
    public static final ItemMaterial URANIUM = new ItemMaterial(overworldOre("uranium", MaterialisticOres.URANIUM).experience(0.1F));
    public static final ItemMaterial ELECTRUM = new ItemMaterial(builderAlloy("electrum", ToolRequirement.STONE).experience(0.1F));
    public static final ItemMaterial LUMIUM = new ItemMaterial(builderAlloy("lumium", ToolRequirement.STONE).experience(0.6F));
    public static final ItemMaterial ENDERIUM = new ItemMaterial(builderAlloy("enderium", ToolRequirement.DIAMOND).experience(0.8F));
    public static final ItemMaterial REDSTONE = new ItemMaterial(builderAlloy("redstone", ToolRequirement.IRON).experience(0.5F));
    public static final ItemMaterial MITHRIL = new ItemMaterial(overworldOre("mithril", MaterialisticOres.MITHRIL).experience(1.0F));
//    public static final ItemMaterial AURORA_STEEL = new ItemMaterial(builderAlloy("aurora_steel", ToolRequirement.DIAMOND).experience(1.0F));
    public static final ItemMaterial COBALT = new ItemMaterial(netherOre("cobalt", MaterialisticOres.COBALT).experience(1.2F));
    public static final ItemMaterial ULTRINIUM = new ItemMaterial(overworldOre("ultrinium", MaterialisticOres.ULTRINIUM).experience(3.0F).cookingTime(600));
    public static final ItemMaterial CHUNK = new ItemMaterial(overworldOre("chunk", MaterialisticOres.CHUNK).experience(5.0F).cookingTime(2000));
    public static final ItemMaterial INFINITY = new ItemMaterial(overworldOre("infinity", MaterialisticOres.INFINITY).experience(7.0F).cookingTime(5000));

    private final Identifier oreRegistryName;
    private final Identifier registryName;

    private final Supplier<Toolset> tools;

    private final Supplier<OreBlock> stoneOreSupplier;
    private final Supplier<OreBlock> deepslateOreSupplier;
    private final Supplier<OreBlock> netherOreSupplier;
    private final Supplier<Block> storageBlockSupplier;
    private final Supplier<Block> rawMaterialBlockSupplier;
    private final Supplier<Item> rawMaterialSupplier;
    private final Supplier<Item> ingotSupplier;
    private final Supplier<Item> nuggetSupplier;
    private final Supplier<Item> gemSupplier;
    private final TagKey<Block> storageBlockTag;
    private final TagKey<Block> oreTag;
    private final TagKey<Item> storageBlockItemTag;
    private final TagKey<Item> oreItemTag;
    private final TagKey<Item> rawMaterialTag;
    private final TagKey<Item> ingotTag;
    private final TagKey<Item> nuggetTag;
    @Nullable private final ToolRequirement harvestRequirement;
    private final TagKey<Item> gemTag;
    private RegistrySupplier<Block> stoneOre;
    private RegistrySupplier<Block> deepslateOre;
    private RegistrySupplier<Block> netherOre;
    private RegistrySupplier<Block> storageBlock;
    private RegistrySupplier<Block> rawMaterialBlock;
    private RegistrySupplier<Item> rawMaterial;
    private RegistrySupplier<Item> dust;
    private RegistrySupplier<Item> ingot;
    private RegistrySupplier<Item> gem;
    private RegistrySupplier<Item> nugget;

    private final Collection<TagKey<Block>> miningTags = new ArrayList<>();
    private final float experience;
    private final int cookingTime;

    public ItemMaterial(Builder builder) {
        this(builder, builder.registryName);
    }

    public ItemMaterial(Builder builder, Identifier registryName) {
        this.oreRegistryName = registryName;
        this.registryName = builder.registryName;

        this.storageBlockSupplier = builder.storageBlock;
        this.stoneOreSupplier = builder.stoneOre;
        this.deepslateOreSupplier = builder.deepslateOre;
        this.rawMaterialBlockSupplier = builder.rawMaterialBlock;
        this.netherOreSupplier = builder.netherOre;
        this.rawMaterialSupplier = builder.rawMaterial;
        this.ingotSupplier = builder.ingot;
        this.gemSupplier = builder.gem;
        this.nuggetSupplier = builder.nugget;
        this.oreTag = builder.oreTag;
        this.storageBlockTag = builder.storageBlockTag;
        this.oreItemTag = this.oreTag != null ? Builder.itemTag(this.oreTag.location()) : null;
        this.storageBlockItemTag = this.storageBlockTag != null ? Builder.itemTag(this.storageBlockTag.location()) : null;
        this.rawMaterialTag = builder.rawMaterialTag;
        this.ingotTag = builder.ingotTag;
        this.gemTag = builder.gemTag;
        this.nuggetTag = builder.nuggetTag;
        this.harvestRequirement = builder.toolRequirement;
        this.miningTags.addAll(builder.miningTags);

        this.experience = builder.experience;
        this.cookingTime = builder.cookingTime;

        this.tools = builder.tools;

        map.put(registryName, this);
        all.add(this);
    }

    public static void registerBlocks() {
        for (ItemMaterial metal : getValues()) {
            if (metal.stoneOreSupplier != null) {
                String name = metal.oreRegistryName.getPath() + "_ore";
                metal.stoneOre = MaterialisticBlocks.REGISTER.register(name, metal.stoneOreSupplier);
                MaterialisticItems.REGISTER.register(name, () ->
                        new BlockItem(metal.stoneOre.get(), properties(name)));
            }
            if (metal.deepslateOreSupplier != null) {
                String name = "deepslate_" + metal.oreRegistryName.getPath() + "_ore";
                metal.deepslateOre = MaterialisticBlocks.REGISTER.register(name, metal.deepslateOreSupplier);
                MaterialisticItems.REGISTER.register(name, () ->
                        new BlockItem(metal.deepslateOre.get(), properties(name)));
            }
            if (metal.netherOreSupplier != null) {
                String name = "nether_" + metal.oreRegistryName.getPath() + "_ore";
                metal.netherOre = MaterialisticBlocks.REGISTER.register(name, metal.netherOreSupplier);
                MaterialisticItems.REGISTER.register(name, () ->
                        new BlockItem(metal.netherOre.get(), properties(name)));
            }
            if (metal.storageBlockSupplier != null) {
                String name = metal.registryName.getPath() + "_block";
                metal.storageBlock = MaterialisticBlocks.REGISTER.register(name, metal.storageBlockSupplier);
                MaterialisticItems.REGISTER.register(name, () ->
                        new BlockItem(metal.storageBlock.get(), properties(name)));
            }
            if (metal.rawMaterialBlockSupplier != null) {
                String name = "raw_" + metal.oreRegistryName.getPath() + "_block";
                metal.rawMaterialBlock = MaterialisticBlocks.REGISTER.register(name, metal.rawMaterialBlockSupplier);
                MaterialisticItems.REGISTER.register(name, () ->
                        new BlockItem(metal.rawMaterialBlock.get(), properties(name)));
            }
        }
    }

    public static void registerItems() {
        for (ItemMaterial metal : getValues()) {
            if (metal.rawMaterialSupplier != null) {
                String[] split = metal.oreRegistryName.getPath().split("/");
                split[split.length - 1] = "raw_" + split[split.length - 1];
                metal.rawMaterial = MaterialisticItems.REGISTER.register(
                        String.join("", split), metal.rawMaterialSupplier);
            }
            if (metal.ingotSupplier != null) {
                metal.ingot = MaterialisticItems.REGISTER.register(
                        metal.registryName.getPath() + "_ingot", metal.ingotSupplier);
            }
            if (metal.gemSupplier != null) {
                metal.gem = MaterialisticItems.REGISTER.register(
                        metal.registryName.getPath(), metal.gemSupplier);
            }
            if (metal.nuggetSupplier != null) {
                metal.nugget = MaterialisticItems.REGISTER.register(
                        metal.registryName.getPath() + "_nugget", metal.nuggetSupplier);
            }
        }
    }

    private static Builder builder(String name) {
        return new Builder(name);
    }

    private static Item.Properties properties(String name) {
        return new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MOD_ID, name)));
    }

    /**
     * Creates a base builder with ore.
     *
     * @param name name of the material.
     * @param ore ore object.
     * @return the item material builder.
     */
    public static Builder overworldOre(String name, Ore ore) {
        return builder(name).toolRequirement(ore.getToolRequirement()).rawMaterial(ore).storageBlock(ore.getToolRequirement()).overworldOre(ore).ingot().nugget();
    }

    /**
     * Creates base builder with ore.
     *
     * @param name name of the material.
     * @param ore ore object.
     * @return the item material builder.
     */
    public static Builder netherOre(String name, Ore ore) {
        return builder(name).toolRequirement(ore.getToolRequirement()).storageBlock(ore.getToolRequirement()).netherOre(ore).rawMaterial(ore).ingot().nugget();
    }

    /**
     * Creates alloy material builder.
     *
     * @param name name of the material.
     * @param toolRequirement the material's harvest level.
     * @return the item material builder.
     */
    public static Builder builderAlloy(String name, ToolRequirement toolRequirement) {
        return builder(name).storageBlock(toolRequirement).ingot().nugget();
    }

    /**
     * Creates gem material builder.
     *
     * @deprecated replaced by {@link #builderGem(String, ItemMaterialOre)}.
     * @param name name of the material.
     * @param ore the ore object for the gem.
     * @param harvestLevel the material's harvest level.
     * @return the item material builder.
     */
    @SuppressWarnings("unused")
    @Deprecated
    public static Builder builderGem(String name, ItemMaterialOre ore, @Deprecated int harvestLevel) {
        return builderGem(name, ore);
    }

    /**
     * Creates gem material builder.
     *
     * @param name name of the material.
     * @param ore the ore object for the gem.
     * @return the item material builder.
     */
    public static Builder builderGem(String name, ItemMaterialOre ore) {
        return builder(name).storageBlock(ore.getToolRequirement()).overworldOre(ore).gem();
    }

    /**
     * Get the toolset bound to this material.
     *
     * @return toolset instance.
     */
    @Nullable
    public Toolset getToolset() {
        return this.tools.get();
    }

    /**
     * Get the material's name.
     *
     * @return resource path name for the item material.
     */
    @Deprecated(forRemoval = true)
    @Override
    public String getName() {
        return this.name().toLowerCase(Locale.ROOT);
    }

    /**
     * Get the stone ore block.
     *
     * @return optional value for the stone ore block.
     */
    @Override
    public @Nullable RegistrySupplier<Block> getStoneOre() {
        return this.stoneOre;
    }

    /**
     * Get the deepslate ore block.
     *
     * @return optional value for the deepslate ore block.
     */
    @Override
    public @Nullable RegistrySupplier<Block> getDeepslateOre() {
        return this.deepslateOre;
    }

    /**
     * Get the nether ore block.
     *
     * @return optional value for the nether ore block.
     */
    @Override
    public @Nullable RegistrySupplier<Block> getNetherOre() {
        return this.netherOre;
    }

    /**
     * Get the storage block.
     *
     * @return optional value for storage block.
     */
    @Override
    public @Nullable RegistrySupplier<Block> getStorageBlock() {
        return this.storageBlock;
    }

    /**
     * Get the ore chunks item.
     *
     * @return optional value for the ore chunks item.
     */
    @Override
    public @Nullable RegistrySupplier<Item> getRawMaterial() {
        return this.rawMaterial;
    }

    /**
     * Get the ingot item.
     *
     * @return optional value for the ingot item.
     */
    @Override
    public @Nullable RegistrySupplier<Item> getIngot() {
        return this.ingot;
    }

    /**
     * Get the gem item.
     *
     * @return optional value for the gem item.
     */
    @Override
    public @Nullable RegistrySupplier<Item> getGem() {
        return this.gem;
    }

    /**
     * Get the nugget item.
     *
     * @return optional value for the nugget item.
     */
    @Override
    public @Nullable RegistrySupplier<Item> getNugget() {
        return this.nugget;
    }

    /**
     * Get the ore tag.
     *
     * @return optional value for the ore block tag.
     */
    @Override
    public Optional<TagKey<Block>> getOreTag() {
        return Optional.ofNullable(this.oreTag);
    }

    /**
     * Get the storage block tag.
     *
     * @return optional value for the storage block tag.
     */
    @Override
    public Optional<TagKey<Block>> getStorageBlockTag() {
        return Optional.ofNullable(this.storageBlockTag);
    }

    /**
     * Get the ore item tag.
     *
     * @return optional value for the ore item tag.
     */
    @Override
    public Optional<TagKey<Item>> getOreItemTag() {
        return Optional.ofNullable(this.oreItemTag);
    }

    /**
     * Get the storage block item tag.
     *
     * @return optional value for the storage block item tag.
     */
    @Override
    public Optional<TagKey<Item>> getStorageBlockItemTag() {
        return Optional.ofNullable(this.storageBlockItemTag);
    }

    /**
     * Get the chunks tag.
     *
     * @return optional value for the ore chunks item tag.
     */
    @Override
    public Optional<TagKey<Item>> getRawMaterialTag() {
        return Optional.ofNullable(this.rawMaterialTag);
    }

    /**
     * Get the ingot tag.
     *
     * @return optional value for the ingot item tag.
     */
    @Override
    public Optional<TagKey<Item>> getIngotTag() {
        return Optional.ofNullable(this.ingotTag);
    }

    /**
     * Get the gem tag.
     *
     * @return optional value for the gem item tag.
     */
    @Override
    public Optional<TagKey<Item>> getGemTag() {
        return Optional.ofNullable(this.gemTag);
    }

    /**
     * Get the nugget tag.
     *
     * @return optional value for the nugget item tag.
     */
    @Override
    public Optional<TagKey<Item>> getNuggetTag() {
        return Optional.ofNullable(this.nuggetTag);
    }

    /**
     * @author XyperCode
     */
    public @Nullable RegistrySupplier<Block> getRawMaterialBlock() {
        return rawMaterialBlock;
    }

    public @Nullable ToolRequirement getHarvestRequirement() {
        return this.harvestRequirement;
    }

    @Deprecated(forRemoval = true)
    public String name() {
        return this.registryName.toString();
    }

    public Identifier getRegistryName() {
        return this.registryName;
    }

    @Deprecated(forRemoval = true)
    public static ItemMaterial[] values() {
        return all.toArray(new ItemMaterial[0]);
    }

    public static Collection<ItemMaterial> getValues() {
        return map.values();
    }

    public static ItemMaterial fromName(Identifier name) {
        return map.get(name);
    }

    public Collection<TagKey<Block>> getMiningTags() {
        return Collections.unmodifiableCollection(this.miningTags);
    }

    @Override
    public boolean test(Holder<Item> itemHolder) {
        return (this.ingot != null && itemHolder.value() == this.ingot.orElse(null))
                || (this.nugget != null && itemHolder.value() == this.nugget.orElse(null))
                || (this.rawMaterial != null && itemHolder.value() == this.rawMaterial.orElse(null))
                || (this.gem != null && itemHolder.value() == this.gem.orElse(null))
                || (this.storageBlock != null && itemHolder.value() == this.storageBlock.toOptional().map(Block::asItem).orElse(null))
                || (this.stoneOre != null && itemHolder.value() == this.stoneOre.toOptional().map(Block::asItem).orElse(null))
                || (this.netherOre != null && itemHolder.value() == this.netherOre.toOptional().map(Block::asItem).orElse(null))
                || (this.deepslateOre != null && itemHolder.value() == this.deepslateOre.toOptional().map(Block::asItem).orElse(null))
                ;
    }

    public List<ItemLike> getOres() {
        List<ItemLike> ores = new ArrayList<>();
        if (stoneOre != null) ores.add(this.stoneOre.get());
        if (deepslateOre != null) ores.add(this.deepslateOre.get());
        if (netherOre != null) ores.add(this.netherOre.get());
        return ores;
    }

    public List<ItemLike> getSmeltables() {
        List<ItemLike> ores = new ArrayList<>();
        if (stoneOre != null) ores.add(this.stoneOre.get());
        if (deepslateOre != null) ores.add(this.deepslateOre.get());
        if (netherOre != null) ores.add(this.netherOre.get());
        if (rawMaterial != null) ores.add(this.rawMaterial.get());
        return ores;
    }

    public float getExperience() {
        return experience;
    }

    public int getCookingTime() {
        return cookingTime;
    }
    public static class Builder {

        private final Identifier registryName;
        private float experience = 0.1F;
        private int cookingTime = 200;
        private Supplier<OreBlock> stoneOre;
        private Supplier<OreBlock> deepslateOre;
        private Supplier<OreBlock> netherOre;
        private Supplier<Block> storageBlock;
        private Supplier<Block> rawMaterialBlock;
        private Supplier<Item> rawMaterial;
        private Supplier<Item> ingot;
        private Supplier<Item> gem;
        private Supplier<Item> nugget;
        private TagKey<Block> oreTag;
        private TagKey<Block> storageBlockTag;
        private TagKey<Item> rawMaterialTag;
        private TagKey<Item> ingotTag;
        private TagKey<Item> gemTag;
        private TagKey<Item> nuggetTag;
        private ToolRequirement toolRequirement;

        private Supplier<Toolset> tools;
        private final List<TagKey<Block>> miningTags = new ArrayList<>();

        /**
         * @param name name of the item material.
         */
        private Builder(String name) {
            this.registryName = Identifier.fromNamespaceAndPath(MOD_ID, name);
        }

        public static TagKey<Block> blockTag(String path) {
            return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("neoforge", path));
        }

        public static TagKey<Item> itemTag(String path) {
            return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("neoforge", path));
        }

        public static TagKey<Item> itemTag(Identifier tag) {
            return TagKey.create(Registries.ITEM, tag);
        }

        public Builder experience(float experience) {
            this.experience = experience;
            return this;
        }

        public Builder cookingTime(int cookingTime) {
            this.cookingTime = cookingTime;
            return this;
        }

        public Builder overworldOre(Ore ore) {
            this.stoneOre = () -> new OreBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, registryName.withPath(ore.getName() + "_ore"))).mapColor(MapColor.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(ore.getHardness(), ore.getResistance())
                    .sound(SoundType.STONE), ore);
            this.deepslateOre = () -> new OreBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, registryName.withPath(ore.getName() + "_deepslate_ore")))
                    .mapColor(MapColor.DEEPSLATE)
                    .requiresCorrectToolForDrops()
                    .strength(ore.getHardness(), ore.getResistance())
                    .sound(SoundType.DEEPSLATE), ore);
            this.miningTags.add(BlockTags.MINEABLE_WITH_PICKAXE);
            this.miningTags.add(ore.getToolRequirement().getTag());
            this.toolRequirement = ore.getToolRequirement();
            if (this.oreTag == null) {
                this.oreTag = blockTag("ores/" + ore.getName());
            }
            return this;
        }

        public Builder netherOre(Ore ore) {
            this.netherOre = () -> new OreBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, registryName.withPath(ore.getName() + "_nether_ore")))
                    .mapColor(MapColor.NETHER)
                    .requiresCorrectToolForDrops()
                    .strength(ore.getHardness(), ore.getResistance())
                    .sound(SoundType.NETHERRACK), ore);
            this.miningTags.add(BlockTags.MINEABLE_WITH_PICKAXE);
            this.miningTags.add(ore.getToolRequirement().getTag());
            this.toolRequirement = ore.getToolRequirement();
            if (this.oreTag == null) {
                this.oreTag = blockTag("ores/" + ore.getName());
            }
            return this;
        }

        public Builder tools(Supplier<Toolset> tools) {
            this.tools = tools;
            return this;
        }

        public Builder storageBlock(ToolRequirement tool) {
            this.storageBlock = () -> new BlockWithRequiredToolMat(tool, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, registryName.withPath(s -> s + "_block")))
                    .mapColor(MapColor.METAL)
                    .strength(4, 20)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL));
            this.storageBlockTag = blockTag("storage_blocks/" + this.registryName.getPath());
            this.toolRequirement = tool;
            return this;
        }

        public Builder vanillaGem(boolean storageBlock, boolean ore) {
            if (ore) this.oreTag = blockTag("ores/" + this.registryName.getPath());
            if (storageBlock) this.storageBlockTag = blockTag("storage_block/" + this.registryName.getPath());
            this.gemTag = itemTag("gems/" + this.registryName.getPath());
            return this;
        }

        public Builder vanillaGemWithoutBlocks() {
            this.gemTag = itemTag("gems/" + this.registryName.getPath());
            return this;
        }

        public Builder vanillaMetal(boolean rawMaterial, boolean nugget) {
            if (rawMaterial) this.oreTag = blockTag("ores/" + this.registryName.getPath());
            this.storageBlockTag = blockTag("storage_block/" + this.registryName.getPath());
            this.ingotTag = itemTag("ingots/" + this.registryName.getPath());
            if (nugget) this.nuggetTag = itemTag("nuggets/" + this.registryName.getPath());
            if (rawMaterial) this.rawMaterialTag = itemTag("raw_material/" + this.registryName.getPath());
            return this;
        }

        public Builder vanillaMetalWithoutNugvalue() {
            this.oreTag = blockTag("ores/" + this.registryName.getPath());
            this.storageBlockTag = blockTag("storage_block/" + this.registryName.getPath());
            this.ingotTag = itemTag("ingots/" + this.registryName.getPath());
            return this;
        }

        public Builder rawMaterial(Ore ore) {
            this.rawMaterial = () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, registryName.withPath(s -> "raw_" + ore.getName()))));
            this.rawMaterialTag = itemTag("raw_material/" + ore.getName());
            this.rawMaterialBlock = () -> new Block(Block.Properties.of().setId(ResourceKey.create(Registries.BLOCK, registryName.withPath(s -> "raw_" + ore.getName() + "_block"))));
            return this;
        }

        public Builder ingot() {
            this.ingot = () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, registryName.withPath(s -> s + "_ingot"))));
            this.ingotTag = itemTag("ingots/" + this.registryName.getPath());
            return this;
        }

        public Builder nugget() {
            this.nugget = () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, registryName.withPath(s -> s + "_nugget"))));
            this.nuggetTag = itemTag("nuggets/" + this.registryName.getPath());
            return this;
        }

        public Builder gem() {
            this.gem = () -> new Item(properties(registryName.getPath()));
            this.gemTag = itemTag("gems/" + this.registryName.getPath());
            return this;
        }

        public Builder toolRequirement(ToolRequirement toolRequirement) {
            this.toolRequirement = toolRequirement;
            return this;
        }
    }
}
