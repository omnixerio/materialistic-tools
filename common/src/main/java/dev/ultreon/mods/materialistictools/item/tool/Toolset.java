package dev.ultreon.mods.materialistictools.item.tool;

import com.mojang.datafixers.util.Either;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.ultreon.mods.materialistictools.MaterialisticTools;
import dev.ultreon.mods.materialistictools.debug.Debugger;
import dev.ultreon.mods.materialistictools.init.ModItems;
import dev.ultreon.mods.materialistictools.init.ModToolMaterials;
import dev.ultreon.mods.materialistictools.init.tags.ModBlockTags;
import dev.ultreon.mods.materialistictools.init.tags.ModItemTags;
import dev.ultreon.mods.materialistictools.item.IngredientItems;
import dev.ultreon.mods.materialistictools.item.material.ItemMaterial;
import dev.ultreon.mods.materialistictools.item.tool.types.*;
import dev.ultreon.mods.materialistictools.util.FeatureStatus;
import dev.ultreon.mods.materialistictools.util.item.ArmorMaterialBuilder;
import dev.ultreon.mods.materialistictools.util.item.RelativeMaterial;
import dev.ultreon.mods.materialistictools.util.item.ToolMaterialBuilder;
import dev.ultreon.mods.materialistictools.util.item.ToolMaterialInfo;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

@SuppressWarnings({"OptionalGetWithoutIsPresent", "Convert2MethodRef"})
public enum Toolset implements Predicate<Holder<Item>> {
    SILVER(builder("silver")
            .material(() -> Either.right(ItemMaterial.SILVER.getIngotTag().orElseThrow()), () -> Items.STICK)
            .armor(() -> TraitPack.create().all(ModTraits.HOLY.get()).build(), () -> new ArmorMaterialBuilder()
                    .name(MaterialisticTools.id("silver"))
                    .maxDamageFactor(15)
                    .damageReduction(new int[]{2, 5, 6, 2})
                    .enchantability(48)
                    .knockbackResistance(1F)
                    .sound(SoundEvents.ARMOR_EQUIP_IRON)
                    .toughness(0F)
                    .repairMaterial(ModItemTags.REPAIRS_SILVER_ARMOR)
                    .build())
            .tools(() -> TraitPack.create().all(ModTraits.HOLY.get()).build(), () -> new ToolMaterialBuilder()
                    .incorrectBlocksForDrops(ModBlockTags.INCORRECT_FOR_SILVER_TOOL)
                    .sameAs(ToolMaterial.IRON)
                    .durability(580)
                    .efficiency(5.5F)
                    .attackDamage(1.7F)
                    .enchantability(48)
                    .repairMaterial(ModItemTags.SILVER_TOOL_MATERIALS).build())),
    LEAD(builder("lead")
            .material(() -> Either.right(ItemMaterial.LEAD.getIngotTag().orElseThrow()), () -> Items.STICK)
            .armor(() -> TraitPack.create().all(ModTraits.POISON.get(), ModTraits.SHARP.get()).build(), () -> new ArmorMaterialBuilder()
                    .name(MaterialisticTools.id("lead"))
                    .maxDamageFactor(14)
                    .damageReduction(new int[]{4, 9, 12, 7})
                    .enchantability(4)
                    .knockbackResistance(1F)
                    .sound(SoundEvents.ARMOR_EQUIP_IRON)
                    .toughness(4F)
                    .repairMaterial(ModItemTags.REPAIRS_LEAD_ARMOR)
                    .build())
            .tools(() -> TraitPack.create()
                    .all(ModTraits.POISON.get(), ModTraits.SHARP.get()).build(), () -> new ToolMaterialBuilder()
                    .incorrectBlocksForDrops(ModBlockTags.INCORRECT_FOR_LEAD_TOOL)
                    .sameAs(ToolMaterial.IRON)
                    .durability(450)
                    .efficiency(8F)
                    .attackDamage(3F)
                    .enchantability(7)
                    .repairMaterial(ModItemTags.LEAD_TOOL_MATERIALS).build())),
    PLATINUM(builder("platinum")
            .material(() -> Either.right(ItemMaterial.PLATINUM.getIngotTag().orElseThrow()), () -> Items.STICK)
            .armor(() -> new ArmorMaterialBuilder()
                    .name(MaterialisticTools.id("platinum"))
                    .maxDamageFactor(36)
                    .damageReduction(new int[]{3, 6, 8, 3})
                    .enchantability(14)
                    .knockbackResistance(0F)
                    .sound(SoundEvents.ARMOR_EQUIP_IRON)
                    .toughness(0F)
                    .repairMaterial(ModItemTags.REPAIRS_PLATINUM_ARMOR)
                    .build())
            .tools(() -> new ToolMaterialBuilder()
                    .incorrectBlocksForDrops(ModBlockTags.INCORRECT_FOR_PLATINUM_TOOL)
                    .sameAs(ToolMaterial.DIAMOND)
                    .durability(1240)
                    .efficiency(7F)
                    .attackDamage(5F)
                    .enchantability(14)
                    .repairMaterial(ModItemTags.PLATINUM_TOOL_MATERIALS).build())),
    ALUMINUM(builder("aluminum")
            .material(() -> Either.right(ItemMaterial.ALUMINUM.getIngotTag().orElseThrow()), () -> Items.STICK)
            .armor(() -> new ArmorMaterialBuilder()
                    .name(MaterialisticTools.id("aluminum"))
                    .maxDamageFactor(13)
                    .damageReduction(new int[]{2, 5, 6, 2})
                    .enchantability(10)
                    .knockbackResistance(0F)
                    .sound(SoundEvents.ARMOR_EQUIP_IRON)
                    .toughness(0F)
                    .repairMaterial(ModItemTags.REPAIRS_ALUMINUM_ARMOR)
                    .build())
            .tools(() -> TraitPack.create().all(ModTraits.SHARP.get()).build(), () -> new ToolMaterialBuilder()
                    .incorrectBlocksForDrops(ModBlockTags.INCORRECT_FOR_ALUMINUM_TOOL)
                    .sameAs(ToolMaterial.IRON)
                    .durability(180)
                    .efficiency(9F)
                    .attackDamage(3F)
                    .enchantability(9)
                    .repairMaterial(ModItemTags.ALUMINUM_TOOL_MATERIALS).build())),
    URANIUM(builder("uranium")
            .material(() -> Either.right(ItemMaterial.URANIUM.getIngotTag().orElseThrow()), () -> Items.STICK)
            .armor(() -> TraitPack.create().all(ModTraits.RADIOACTIVE.get()).build(), () -> new ArmorMaterialBuilder()
                    .name(MaterialisticTools.id("uranium"))
                    .maxDamageFactor(14)
                    .damageReduction(new int[]{2, 5, 6, 2})
                    .enchantability(10)
                    .knockbackResistance(0F)
                    .sound(SoundEvents.ARMOR_EQUIP_IRON)
                    .toughness(0F)
                    .repairMaterial(ModItemTags.REPAIRS_URANIUM_ARMOR)
                    .build())
            .tools(() -> TraitPack.create().all(ModTraits.RADIOACTIVE.get()).build(), () -> new ToolMaterialBuilder()
                    .incorrectBlocksForDrops(ModBlockTags.INCORRECT_FOR_URANIUM_TOOL)
                    .sameAs(ToolMaterial.IRON)
                    .durability(220)
                    .efficiency(5.3F)
                    .attackDamage(1.4F)
                    .enchantability(11)
                    .repairMaterial(ModItemTags.URANIUM_TOOL_MATERIALS).build())),
    ELECTRUM(builder("electrum", FeatureStatus.WIP)
            .material(() -> Either.right(ItemMaterial.ELECTRUM.getIngotTag().orElseThrow()), () -> Items.STICK)
            .armor(() -> new ArmorMaterialBuilder()
                    .name(MaterialisticTools.id("electrum"))
                    .maxDamageFactor(14)
                    .damageReduction(new int[]{2, 5, 6, 2})
                    .enchantability(11)
                    .knockbackResistance(0F)
                    .sound(SoundEvents.ARMOR_EQUIP_IRON)
                    .toughness(0F)
                    .repairMaterial(ModItemTags.REPAIRS_ELECTRUM_ARMOR)
                    .build())
            .tools(() -> new ToolMaterialBuilder()
                    .incorrectBlocksForDrops(ModBlockTags.INCORRECT_FOR_ELECTRUM_TOOL)
                    .sameAs(ToolMaterial.WOOD)
                    .durability(283)
                    .efficiency(4.5F)
                    .attackDamage(2F)
                    .enchantability(11)
                    .repairMaterial(ModItemTags.ELECTRUM_TOOL_MATERIALS).build())),
    LUMIUM(builder("lumium", FeatureStatus.WIP)
            .material(() -> Either.right(ItemMaterial.LUMIUM.getIngotTag().orElseThrow()), () -> Items.STICK)
            .armor(() -> new ArmorMaterialBuilder()
                    .name(MaterialisticTools.id("lumium"))
                    .maxDamageFactor(9)
                    .damageReduction(new int[]{2, 5, 6, 2})
                    .enchantability(36)
                    .knockbackResistance(0F)
                    .sound(SoundEvents.ARMOR_EQUIP_IRON)
                    .toughness(0F)
                    .repairMaterial(ModItemTags.REPAIRS_LUMIUM_ARMOR)
                    .build())
            .tools(() -> new ToolMaterialBuilder()
                    .incorrectBlocksForDrops(ModBlockTags.INCORRECT_FOR_LUMIUM_TOOL)
                    .sameAs(ToolMaterial.STONE)
                    .durability(200)
                    .efficiency(5F)
                    .attackDamage(2.7F)
                    .enchantability(36)
                    .repairMaterial(ModItemTags.LUMIUM_TOOL_MATERIALS).build())),
    ENDERIUM(builder("enderium")
            .material(() -> Either.right(ItemMaterial.ENDERIUM.getIngotTag().orElseThrow()), () -> Items.STICK)
            .armor(() -> TraitPack.create().armor(ModTraits.ENDER.get()).build(), () -> new ArmorMaterialBuilder()
                    .name(MaterialisticTools.id("enderium"))
                    .maxDamageFactor(42)
                    .damageReduction(new int[]{4, 10, 12, 3})
                    .enchantability(56)
                    .knockbackResistance(.2F)
                    .sound(SoundEvents.ARMOR_EQUIP_IRON)
                    .toughness(4F)
                    .repairMaterial(ModItemTags.REPAIRS_ENDERIUM_ARMOR)
                    .build())
            .tools(() -> TraitPack.create().tools(ModTraits.ENDER.get()).build(), () -> new ToolMaterialBuilder()
                    .incorrectBlocksForDrops(ModBlockTags.INCORRECT_FOR_ENDERIUM_TOOL)
                    .sameAs(ToolMaterial.DIAMOND)
                    .durability(2340)
                    .efficiency(16F)
                    .attackDamage(7F)
                    .enchantability(56)
                    .repairMaterial(ModItemTags.ENDERIUM_TOOL_MATERIALS).build())),
    MITHRIL(builder("mithril")
            .material(() -> Either.right(ItemMaterial.MITHRIL.getIngotTag().orElseThrow()), () -> Items.STICK)
            .armor(() -> TraitPack.create().build(), () -> new ArmorMaterialBuilder()
                    .name(MaterialisticTools.id("mithril"))
                    .maxDamageFactor(45)
                    .damageReduction(new int[]{4, 10, 12, 3})
                    .enchantability(56)
                    .knockbackResistance(.5F)
                    .sound(SoundEvents.ARMOR_EQUIP_NETHERITE)
                    .toughness(5F)
                    .repairMaterial(ModItemTags.REPAIRS_MITHRIL_ARMOR)
                    .build())
            .tools(() -> TraitPack.create().build(), () -> new ToolMaterialBuilder()
                    .incorrectBlocksForDrops(ModBlockTags.INCORRECT_FOR_MITHRIL_TOOL)
                    .sameAs(ToolMaterial.NETHERITE)
                    .durability(3240)
                    .efficiency(18F)
                    .attackDamage(8.0F)
                    .enchantability(56)
                    .repairMaterial(ModItemTags.MITHRIL_TOOL_MATERIALS).build())),
    REDSTONE(builder("redstone")
            .material(() -> Either.right(ItemMaterial.REDSTONE.getIngotTag().orElseThrow()), () -> Items.STICK)
            .armor(() -> TraitPack.create().build(), () -> new ArmorMaterialBuilder()
                    .name(MaterialisticTools.id("redstone"))
                    .maxDamageFactor(42)
                    .damageReduction(new int[]{4, 10, 12, 3})
                    .enchantability(56)
                    .knockbackResistance(.2F)
                    .sound(SoundEvents.ARMOR_EQUIP_DIAMOND)
                    .toughness(4F)
                    .repairMaterial(ModItemTags.REPAIRS_REDSTONE_ARMOR)
                    .build())
            .tools(() -> TraitPack.create().build(), () -> new ToolMaterialBuilder()
                    .incorrectBlocksForDrops(ModBlockTags.INCORRECT_FOR_ENDERIUM_TOOL)
                    .sameAs(ToolMaterial.IRON)
                    .durability(350)
                    .efficiency(5.6F)
                    .attackDamage(3.0F)
                    .enchantability(9)
                    .repairMaterial(ModItemTags.REDSTONE_TOOL_MATERIALS).build())),
//    AURORA_STEEL(builder("aurora_steel")
//            .material(() -> Either.right(ItemMaterial.AURORA_STEEL.getIngotTag().orElseThrow()), () -> Items.STICK)
//            .armor(() -> TraitPack.create().armor(ModTraits.REFLECTIVE_AURA.get()).build(), () -> new ArmorMaterialBuilder()
//                    .name(MaterialisticTools.id("aurora_steel"))
//                    .maxDamageFactor(42)
//                    .damageReduction(new int[]{4, 10, 12, 3})
//                    .enchantability(56)
//                    .knockbackResistance(.2F)
//                    .sound(SoundEvents.ARMOR_EQUIP_IRON)
//                    .toughness(4F)
//                    .repairMaterial(ModItemTags.REPAIRS_AURORA_STEEL_ARMOR)
//                    .build())
//            .tools(() -> TraitPack.create().tools(ModTraits.AURORA_SHIELD.get()).armor(ModTraits.REFLECTIVE_AURA.get()).build(), () -> new ToolMaterialBuilder()
//                    .incorrectBlocksForDrops(ModBlockTags.INCORRECT_FOR_AURORA_STEEL_TOOL)
//                    .sameAs(ToolMaterial.IRON)
//                    .durability(2184)
//                    .efficiency(17F)
//                    .attackDamage(8F).enchantability(65)
//                    .repairMaterial(ModItemTags.AURORA_STEEL_TOOL_MATERIALS).build())),
    OBSIDIAN(builder("obsidian")
            .material(() -> Either.right(ModItemTags.OBSIDIAN), () -> IngredientItems.FIRE_ROD.asItem(), () -> Either.left(IngredientItems.FIRE_GEM.asItem()))
            .armor(() -> TraitPack.create().armor(ModTraits.BLAST_RESISTANT.get()).build(), () -> new ArmorMaterialBuilder()
                    .name(MaterialisticTools.id("obsidian"))
                    .maxDamageFactor(119)
                    .damageReduction(new int[]{4, 8, 10, 6})
                    .enchantability(17)
                    .knockbackResistance(.2F)
                    .sound(SoundEvents.ARMOR_EQUIP_NETHERITE)
                    .toughness(4F)
                    .repairMaterial(ModItemTags.REPAIRS_OBSIDIAN_ARMOR)
                    .build())
            .tools(() -> TraitPack.create().all(ModTraits.BLAZE.get()).build(), () -> new ToolMaterialBuilder()
                    .incorrectBlocksForDrops(ModBlockTags.INCORRECT_FOR_OBSIDIAN_TOOL)
                    .sameAs(ToolMaterial.NETHERITE)
                    .durability(3658).efficiency(18F).attackDamage(3F).enchantability(17)
                    .repairMaterial(ModItemTags.OBSIDIAN_TOOL_MATERIALS)
                    .build())),
    COBALT(builder("cobalt", FeatureStatus.WIP)
            .material(() -> Either.right(ItemMaterial.COBALT.getIngotTag().orElseThrow()), () -> Items.STICK)
            .armor(() -> TraitPack.create().armor(ModTraits.MAGIC_RESISTANT.get()).build(), () -> new ArmorMaterialBuilder()
                    .name(MaterialisticTools.id("cobalt"))
                    .maxDamageFactor(64)
                    .damageReduction(new int[]{28, 48, 56, 35})
                    .enchantability(24)
                    .knockbackResistance(.2F)
                    .sound(SoundEvents.ARMOR_EQUIP_NETHERITE)
                    .toughness(6F)
                    .repairMaterial(ModItemTags.REPAIRS_COBALT_ARMOR)
                    .build())
            .tools(() -> ModToolMaterials.COBALT)),
    ULTRINIUM(builder("ultrinium")
            .material(() -> Either.right(ItemMaterial.ULTRINIUM.getIngotTag().orElseThrow()), () -> Items.STICK)
            .armor(() -> TraitPack.create().armor(ModTraits.BLAST_RESISTANT.get(), ModTraits.MAGIC_RESISTANT.get(), ModTraits.FIRE_RESISTANT.get(), ModTraits.PROJECTILE_RESISTANT.get(), ModTraits.HOLY.get()).build(), () -> new ArmorMaterialBuilder()
                    .name(MaterialisticTools.id("ultrinium"))
                    .maxDamageFactor(1014)
                    .damageReduction(new int[]{125, 200, 275, 165})
                    .enchantability(224)
                    .knockbackResistance(1F)
                    .sound(SoundEvents.ARMOR_EQUIP_NETHERITE)
                    .toughness(64F)
                    .repairMaterial(ModItemTags.REPAIRS_ULTRINIUM_ARMOR)
                    .build())
            .tools(() -> TraitPack.create().tools(ModTraits.SHARP.get(), ModTraits.WITHER.get(), ModTraits.POISON.get()).build(), () -> ModToolMaterials.ULTRINIUM)),
    CHUNK(builder("chunk")
            .material(() -> Either.right(ItemMaterial.CHUNK.getIngotTag().orElseThrow()), () -> Items.STICK)
            .armor(() -> TraitPack.create().armor(ModTraits.BLAST_RESISTANT.get(), ModTraits.MAGIC_RESISTANT.get(), ModTraits.FIRE_RESISTANT.get(), ModTraits.PROJECTILE_RESISTANT.get(), ModTraits.CHUNKY.get()).build(), () -> new ArmorMaterialBuilder()
                    .name(MaterialisticTools.id("chunk"))
                    .maxDamageFactor(14985)
                    .damageReduction(new int[]{500, 800, 700, 400})
                    .enchantability(907)
                    .knockbackResistance(2F)
                    .sound(SoundEvents.ARMOR_EQUIP_NETHERITE)
                    .toughness(256F)
                    .repairMaterial(ModItemTags.REPAIRS_CHUNK_ARMOR)
                    .build())
            .tools(() -> TraitPack.create().tools(ModTraits.EXPLOSIVE.get(), ModTraits.EMPOWERED.get()).build(), () -> ModToolMaterials.CHUNK)),
    INFINITY(builder("infinity")
            .material(() -> Either.right(ItemMaterial.INFINITY.getIngotTag().orElseThrow()), () -> Items.STICK)
            .armor(() -> TraitPack.create().armor(
                    ModTraits.BLAST_RESISTANT.get(), ModTraits.MAGIC_RESISTANT.get(),
                    ModTraits.FIRE_RESISTANT.get(), ModTraits.PROJECTILE_RESISTANT.get(),
                    ModTraits.HOLY.get(), ModTraits.INFINITY.get(), ModTraits.POISON.get(),
                    ModTraits.WITHER.get(), ModTraits.SHARP.get(), ModTraits.ENDER.get(),
                    ModTraits.RADIOACTIVE.get()).build(),
                    () -> new ArmorMaterialBuilder()
                            .name(MaterialisticTools.id("infinity"))
                            .maxDamageFactor((int) Float.POSITIVE_INFINITY)
                            .damageReduction(new int[]{(int) Float.POSITIVE_INFINITY, (int) Float.POSITIVE_INFINITY, (int) Float.POSITIVE_INFINITY, (int) Float.POSITIVE_INFINITY})
                            .enchantability((int) Float.POSITIVE_INFINITY)
                            .knockbackResistance(Float.POSITIVE_INFINITY)
                            .sound(SoundEvents.ARMOR_EQUIP_NETHERITE)
                            .toughness(Float.POSITIVE_INFINITY)
                            .repairMaterial(ModItemTags.REPAIRS_INFINITY_ARMOR)
                            .build())
            .tools(() -> TraitPack.create().all(
                    ModTraits.INFINITY.get(), ModTraits.POISON.get(),
                    ModTraits.WITHER.get(), ModTraits.SHARP.get(),
                    ModTraits.ENDER.get(), ModTraits.BLAZE.get(),
                    ModTraits.RADIOACTIVE.get()).build(), () -> ModToolMaterials.INFINITY)),
    AMETHYST(builder("amethyst")
            .material(() -> Either.right(ItemMaterial.AMETHYST.getGemTag().orElseThrow()), () -> Items.STICK)
            .armor(() -> TraitPack.create().armor(ModTraits.MAGIC_RESISTANT.get()).build(), () -> new ArmorMaterialBuilder()
                    .name(MaterialisticTools.id("amethyst"))
                    .maxDamageFactor(21)
                    .damageReduction(new int[]{4, 9, 11, 5})
                    .enchantability(38)
                    .knockbackResistance(0)
                    .sound(SoundEvents.ARMOR_EQUIP_DIAMOND)
                    .toughness(0)
                    .repairMaterial(ModItemTags.REPAIRS_AMETHYST_ARMOR)
                    .build())
            .tools(() -> new ToolMaterialBuilder()
                    .incorrectBlocksForDrops(ModBlockTags.INCORRECT_FOR_AMETHYST_TOOL)
                    .sameAs(ToolMaterial.IRON)
                    .durability(226)
                    .efficiency(6.5F).attackDamage(3.8F).enchantability(30)
                    .repairMaterial(ModItemTags.AMETHYST_TOOL_MATERIALS)
                    .build())),
    ;
    
    private final String toolName;

    private final Supplier<Either<Item, TagKey<Item>>> baseMaterial;
    private final Supplier<Item> handleMaterial;
    @Nullable
    private final Supplier<Either<Item, TagKey<Item>>> element;

    private final ArmorMaterial armorMaterial;
    private final ToolMaterial toolMaterial;

    private final Supplier<Item> helmetSupplier;
    private final Supplier<Item> chestplateSupplier;
    private final Supplier<Item> leggingsSupplier;
    private final Supplier<Item> bootsSupplier;
    private final Supplier<Item> swordSupplier;
    private final Supplier<AxeItem> axeSupplier;
    private final Supplier<Item> pickaxeSupplier;
    private final Supplier<ShovelItem> shovelSupplier;
    private final Supplier<HoeItem> hoeSupplier;

    private final FeatureStatus status;
    private final RelativeMaterial relative;

    private RegistrySupplier<Item> helmet;
    private RegistrySupplier<Item> chestplate;
    private RegistrySupplier<Item> leggings;
    private RegistrySupplier<Item> boots;
    private RegistrySupplier<Item> sword;
    private RegistrySupplier<AxeItem> axe;
    private RegistrySupplier<Item> pickaxe;
    private RegistrySupplier<ShovelItem> shovel;
    private RegistrySupplier<HoeItem> hoe;

    Toolset(Builder builder) {
        this(builder, builder.name);
    }

    Toolset(Builder builder, String toolName) {
        if (!builder.name.equals(this.getName())) {
            throw new IllegalArgumentException("Builder name is incorrect, should be " + this.getName());
        }
        this.status = builder.status;
        this.toolName = toolName;
        Debugger.log(toolName + "{<class>:<init>[1].0}: " + builder.baseMaterial);
        this.baseMaterial = builder.baseMaterial;
        this.handleMaterial = builder.handleMaterial;
        this.element = builder.element;

        this.armorMaterial = builder.armorMaterial;
        this.toolMaterial = builder.toolMaterial.material();
        this.relative = builder.toolMaterial.relative();

        this.helmetSupplier = builder.helmet;
        this.chestplateSupplier = builder.chestplate;
        this.leggingsSupplier = builder.leggings;
        this.bootsSupplier = builder.boots;
        this.swordSupplier = builder.sword;
        this.axeSupplier = builder.axe;
        this.pickaxeSupplier = builder.pickaxe;
        this.shovelSupplier = builder.shovel;
        this.hoeSupplier = builder.hoe;
        Debugger.log(toolName + "{<class>:<init>[1].1}: " + this.baseMaterial);
    }

    public static void register() {
        for (Toolset metal : values()) {
            if (metal.helmetSupplier != null) {
                metal.helmet = ModItems.REGISTER.register(
                        metal.toolName + "_helmet", metal.helmetSupplier);
            }
            if (metal.chestplateSupplier != null) {
                metal.chestplate = ModItems.REGISTER.register(
                        metal.toolName + "_chestplate", metal.chestplateSupplier);
            }
            if (metal.leggingsSupplier != null) {
                metal.leggings = ModItems.REGISTER.register(
                        metal.toolName + "_leggings", metal.leggingsSupplier);
            }
            if (metal.bootsSupplier != null) {
                metal.boots = ModItems.REGISTER.register(
                        metal.toolName + "_boots", metal.bootsSupplier);
            }
            if (metal.swordSupplier != null) {
                metal.sword = ModItems.REGISTER.register(
                        metal.toolName + "_sword", metal.swordSupplier);
            }
            if (metal.axeSupplier != null) {
                metal.axe = ModItems.REGISTER.register(
                        metal.toolName + "_axe", metal.axeSupplier);
            }
            if (metal.pickaxeSupplier != null) {
                metal.pickaxe = ModItems.REGISTER.register(
                        metal.toolName + "_pickaxe", metal.pickaxeSupplier);
            }
            if (metal.shovelSupplier != null) {
                metal.shovel = ModItems.REGISTER.register(
                        metal.toolName + "_shovel", metal.shovelSupplier);
            }
            if (metal.hoeSupplier != null) {
                metal.hoe = ModItems.REGISTER.register(
                        metal.toolName + "_hoe", metal.hoeSupplier);
            }
        }
    }

    public Supplier<Either<Item, TagKey<Item>>> getBaseMaterial() {
        return this.baseMaterial;
    }

    public Supplier<Item> getHandleMaterial() {
        return this.handleMaterial;
    }

    public @Nullable Supplier<Either<Item, TagKey<Item>>> getElement() {
        return this.element;
    }

    public ArmorMaterial getArmorMaterial() {
        return this.armorMaterial;
    }

    public ToolMaterial getToolMaterial() {
        return this.toolMaterial;
    }

    public String getToolName() {
        return this.toolName;
    }

    public RegistrySupplier<Item> getHelmet() {
        return this.helmet;
    }

    public RegistrySupplier<Item> getChestplate() {
        return this.chestplate;
    }

    public RegistrySupplier<Item> getLeggings() {
        return this.leggings;
    }

    public RegistrySupplier<Item> getBoots() {
        return this.boots;
    }

    public RegistrySupplier<Item> getSword() {
        return this.sword;
    }

    public RegistrySupplier<AxeItem> getAxe() {
        return this.axe;
    }

    public RegistrySupplier<Item> getPickaxe() {
        return this.pickaxe;
    }

    public RegistrySupplier<ShovelItem> getShovel() {
        return this.shovel;
    }

    public RegistrySupplier<HoeItem> getHoe() {
        return this.hoe;
    }

    public FeatureStatus getStatus() {
        return this.status;
    }

    private static Builder builder(String name) {
        return new Builder(name);
    }

    private static Builder builder(String name, FeatureStatus status) {
        return new Builder(name, status);
    }

    public String getName() {
        return this.name().toLowerCase(Locale.ROOT);
    }

    @Nullable
    public RelativeMaterial getRelative() {
        return relative;
    }

    @SuppressWarnings({"SameParameterValue"})
    private static final class Builder {
        final String name;
        private Supplier<Either<Item, TagKey<Item>>> baseMaterial;
        private Supplier<Item> handleMaterial;
        private Supplier<Either<Item, TagKey<Item>>> element;
        private ArmorMaterial armorMaterial;
        private ToolMaterialInfo toolMaterial;
        private Supplier<Item> helmet;
        private Supplier<Item> chestplate;
        private Supplier<Item> leggings;
        private Supplier<Item> boots;
        private Supplier<Item> sword;
        private Supplier<AxeItem> axe;
        private Supplier<Item> pickaxe;
        private Supplier<ShovelItem> shovel;
        private Supplier<HoeItem> hoe;
        private FeatureStatus status = FeatureStatus.NORMAL;

        Builder(String name) {
            this.name = name;
        }

        Builder(String name, FeatureStatus status) {
            this.name = name;
            this.status = status;
        }

        Item.Properties properties(String itemName) {
            return new Item.Properties().setId(ResourceKey.create(Registries.ITEM, MaterialisticTools.id(this.name + "_" + itemName)));
        }

        Builder material(Supplier<Either<Item, TagKey<Item>>> material, Supplier<Item> handleMaterial) {
            MaterialisticTools.LOGGER.debug(this.name + "{BUILDER:MATERIAL[0]}: " + material);
            return this.material(material, handleMaterial, null);
        }

        Builder material(Supplier<Either<Item, TagKey<Item>>> material, Supplier<Item> handleMaterial, @Nullable Supplier<Either<Item, TagKey<Item>>> element) {
            MaterialisticTools.LOGGER.debug(this.name + "{BUILDER:MATERIAL[1]}: " + material);
            this.baseMaterial = material;
            this.handleMaterial = handleMaterial;
            this.element = element;
            return this;
        }

        Builder armor(Supplier<ArmorMaterial> armorMaterial) {
            return this.armor(() -> TraitPack.EMPTY, armorMaterial);
        }

        Builder armor(Supplier<TraitPack> pack, Supplier<ArmorMaterial> armorMaterial) {
            this.armorMaterial = armorMaterial.get();

            if (this.status == FeatureStatus.WIP) {
                this.helmet = () -> new CustomArmor(armorMaterial.get(), ArmorType.HELMET, properties("helmet"), () -> pack.get().helmet) {
                    @Override
                    public void appendHoverText(@NonNull ItemStack itemStack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, @NonNull Consumer<Component> builder, @NonNull TooltipFlag tooltipFlag) {
                        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
                        builder.accept(Component.translatable("misc.materialistic_tools.wip"));
                    }
                };
                this.chestplate = () -> new CustomArmor(armorMaterial.get(), ArmorType.CHESTPLATE, properties("chestplate"), () -> pack.get().chestplate) {
                    @Override
                    public void appendHoverText(@NonNull ItemStack itemStack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, @NonNull Consumer<Component> builder, @NonNull TooltipFlag tooltipFlag) {
                        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
                        builder.accept(Component.translatable("misc.materialistic_tools.wip"));
                    }
                };
                this.leggings = () -> new CustomArmor(armorMaterial.get(), ArmorType.LEGGINGS, properties("leggings"), () -> pack.get().leggings) {
                    @Override
                    public void appendHoverText(@NonNull ItemStack itemStack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, @NonNull Consumer<Component> builder, @NonNull TooltipFlag tooltipFlag) {
                        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
                        builder.accept(Component.translatable("misc.materialistic_tools.wip"));
                    }
                };
                this.boots = () -> new CustomArmor(armorMaterial.get(), ArmorType.BOOTS, properties("boots"), () -> pack.get().boots) {
                    @Override
                    public void appendHoverText(@NonNull ItemStack itemStack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, @NonNull Consumer<Component> builder, @NonNull TooltipFlag tooltipFlag) {
                        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
                        builder.accept(Component.translatable("misc.materialistic_tools.wip"));
                    }
                };
            } else if (this.status == FeatureStatus.NORMAL) {
                this.helmet = () -> new CustomArmor(armorMaterial.get(), ArmorType.HELMET, properties("helmet"), () -> pack.get().helmet) { };
                this.chestplate = () -> new CustomArmor(armorMaterial.get(), ArmorType.CHESTPLATE, properties("chestplate"), () -> pack.get().chestplate) { };
                this.leggings = () -> new CustomArmor(armorMaterial.get(), ArmorType.LEGGINGS, properties("leggings"), () -> pack.get().leggings) { };
                this.boots = () -> new CustomArmor(armorMaterial.get(), ArmorType.BOOTS, properties("boots"), () -> pack.get().boots) {
                };
            } else if (this.status == FeatureStatus.DEPRECATED) {
                this.helmet = () -> new CustomArmor(armorMaterial.get(), ArmorType.HELMET, properties("helmet"), () -> pack.get().helmet) {
                    @Override
                    public void appendHoverText(@NonNull ItemStack itemStack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, @NonNull Consumer<Component> builder, @NonNull TooltipFlag tooltipFlag) {
                        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
                        builder.accept(Component.translatable("misc.materialistic_tools.deprecated"));
                    }
                };
                this.chestplate = () -> new CustomArmor(armorMaterial.get(), ArmorType.CHESTPLATE, properties("chestplate"), () -> pack.get().chestplate) {
                    @Override
                    public void appendHoverText(@NonNull ItemStack itemStack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, @NonNull Consumer<Component> builder, @NonNull TooltipFlag tooltipFlag) {
                        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
                        builder.accept(Component.translatable("misc.materialistic_tools.deprecated"));
                    }
                };
                this.leggings = () -> new CustomArmor(armorMaterial.get(), ArmorType.LEGGINGS, properties("leggings"), () -> pack.get().leggings) {
                    @Override
                    public void appendHoverText(@NonNull ItemStack itemStack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, @NonNull Consumer<Component> builder, @NonNull TooltipFlag tooltipFlag) {
                        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
                        builder.accept(Component.translatable("misc.materialistic_tools.deprecated"));
                    }
                };
                this.boots = () -> new CustomArmor(armorMaterial.get(), ArmorType.BOOTS, properties("boots"), () -> pack.get().boots) {
                    @Override
                    public void appendHoverText(@NonNull ItemStack itemStack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, @NonNull Consumer<Component> builder, @NonNull TooltipFlag tooltipFlag) {
                        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
                        builder.accept(Component.translatable("misc.materialistic_tools.deprecated"));
                    }
                };
            }
            return this;
        }

        Builder tools(Supplier<ToolMaterialInfo> itemTier) {
            return this.tools(() -> TraitPack.EMPTY, itemTier);
        }

        Builder tools(Supplier<TraitPack> pack, Supplier<ToolMaterialInfo> itemTier) {
            this.toolMaterial = itemTier.get();

            if (this.status == FeatureStatus.WIP) {
                this.sword = () -> new CustomSword(itemTier.get().material(), 3, -2.4F, properties("sword"), () -> pack.get().sword) {
                    @Override
                    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
                        super.appendHoverText(stack, context, display, builder, tooltipFlag);
                        builder.accept(Component.translatable("misc.materialistic_tools.wip"));
                    }
                };
                this.axe = () -> new CustomAxe(itemTier.get().material(), 5F, -3F, properties("axe"), () -> pack.get().axe) {
                    @Override
                    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
                        super.appendHoverText(stack, context, display, builder, tooltipFlag);
                        builder.accept(Component.translatable("misc.materialistic_tools.wip"));
                    }
                };
                this.pickaxe = () -> new CustomPickaxe(itemTier.get().material(), 1, -2.8F, properties("pickaxe"), () -> pack.get().pickaxe) {
                    @Override
                    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
                        super.appendHoverText(stack, context, display, builder, tooltipFlag);
                        builder.accept(Component.translatable("misc.materialistic_tools.wip"));
                    }
                };
                this.shovel = () -> new CustomShovel(itemTier.get().material(), 1.5F, -3F, properties("shovel"), () -> pack.get().shovel) {
                    @Override
                    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
                        super.appendHoverText(stack, context, display, builder, tooltipFlag);
                        builder.accept(Component.translatable("misc.materialistic_tools.wip"));
                    }
                };
                this.hoe = () -> new CustomHoe(itemTier.get().material(), (int) -(itemTier.get().material().attackDamageBonus() - 1), -1F, properties("hoe"), () -> pack.get().hoe) {
                    @Override
                    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
                        super.appendHoverText(stack, context, display, builder, tooltipFlag);
                        builder.accept(Component.translatable("misc.materialistic_tools.wip"));
                    }
                };
            } else if (this.status == FeatureStatus.NORMAL) {
                this.sword = () -> new CustomSword(itemTier.get().material(), 3, -2.4F, properties("sword"), () -> pack.get().sword);
                this.axe = () -> new CustomAxe(itemTier.get().material(), 5F, -3F, properties("axe"), () -> pack.get().axe);
                this.pickaxe = () -> new CustomPickaxe(itemTier.get().material(), 1, -2.8F, properties("pickaxe"), () -> pack.get().pickaxe);
                this.shovel = () -> new CustomShovel(itemTier.get().material(), 1.5F, -3F, properties("shovel"), () -> pack.get().shovel);
                this.hoe = () -> new CustomHoe(itemTier.get().material(), (int) -(itemTier.get().material().attackDamageBonus() - 1), -1F, properties("hoe"), () -> pack.get().hoe);
            } else if (this.status == FeatureStatus.DEPRECATED) {
                this.sword = () -> new CustomSword(itemTier.get().material(), 3, -2.4F, properties("sword"), () -> pack.get().sword) {
                    @Override
                    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
                        super.appendHoverText(stack, context, display, builder, tooltipFlag);
                        builder.accept(Component.translatable("misc.materialistic_tools.deprecated"));
                    }
                };
                this.axe = () -> new CustomAxe(itemTier.get().material(), 5F, -3F, properties("axe"), () -> pack.get().axe) {
                    @Override
                    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
                        super.appendHoverText(stack, context, display, builder, tooltipFlag);
                        builder.accept(Component.translatable("misc.materialistic_tools.deprecated"));
                    }
                };
                this.pickaxe = () -> new CustomPickaxe(itemTier.get().material(), 1, -2.8F, properties("pickaxe"), () -> pack.get().pickaxe) {
                    @Override
                    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
                        super.appendHoverText(stack, context, display, builder, tooltipFlag);
                        builder.accept(Component.translatable("misc.materialistic_tools.deprecated"));
                    }
                };
                this.shovel = () -> new CustomShovel(itemTier.get().material(), 1.5F, -3F, properties("shovel"), () -> pack.get().shovel) {
                    @Override
                    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
                        super.appendHoverText(stack, context, display, builder, tooltipFlag);
                        builder.accept(Component.translatable("misc.materialistic_tools.deprecated"));
                    }
                };
                this.hoe = () -> new CustomHoe(itemTier.get().material(), (int) -(itemTier.get().material().attackDamageBonus() - 1), -1F, properties("hoe"), () -> pack.get().hoe) {
                    @Override
                    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
                        super.appendHoverText(stack, context, display, builder, tooltipFlag);
                        builder.accept(Component.translatable("misc.materialistic_tools.deprecated"));
                    }
                };
            }
            return this;
        }
    }

    @Override
    public String toString() {
        return "Toolset{" +
                "toolName='" + this.toolName + '\'' +
                ", baseMaterial=" + this.baseMaterial +
                ", handleMaterial=" + this.handleMaterial +
                ", element=" + this.element +
                ", status=" + this.status +
                ", helmet=" + this.helmet +
                ", chestplate=" + this.chestplate +
                ", leggings=" + this.leggings +
                ", boots=" + this.boots +
                ", sword=" + this.sword +
                ", axe=" + this.axe +
                ", pickaxe=" + this.pickaxe +
                ", shovel=" + this.shovel +
                ", hoe=" + this.hoe +
                '}';
    }

    @Override
    public boolean test(Holder<Item> itemHolder) {
        return itemHolder.value() == this.sword.orElse(null)
                || itemHolder.value() == this.axe.orElse(null)
                || itemHolder.value() == this.pickaxe.orElse(null)
                || itemHolder.value() == this.shovel.orElse(null)
                || itemHolder.value() == this.hoe.orElse(null)
                || itemHolder.value() == this.helmet.orElse(null)
                || itemHolder.value() == this.chestplate.orElse(null)
                || itemHolder.value() == this.leggings.orElse(null)
                || itemHolder.value() == this.boots.orElse(null)
                ;
    }
}
