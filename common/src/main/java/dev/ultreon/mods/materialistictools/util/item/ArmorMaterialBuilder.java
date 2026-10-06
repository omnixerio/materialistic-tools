package dev.ultreon.mods.materialistictools.util.item;

import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.Map;

public class ArmorMaterialBuilder {
    private int maxDamageFactor;
    private int[] damageReduction;
    private int enchantability;
    private Holder<SoundEvent> sound;
    private float toughness;
    private TagKey<Item> repairMaterial;
    private float knockbackResistance;
    private ResourceKey<EquipmentAsset> assetId;

    public ArmorMaterialBuilder name(Identifier name) {
        this.assetId = ResourceKey.create(EquipmentAssets.ROOT_ID, name);
        return this;
    }

    public ArmorMaterialBuilder maxDamageFactor(int maxDamageFactor) {
        this.maxDamageFactor = maxDamageFactor;
        return this;
    }

    public ArmorMaterialBuilder damageReduction(int[] damageReduction) {
        this.damageReduction = damageReduction;
        return this;
    }

    public ArmorMaterialBuilder enchantability(int enchantability) {
        this.enchantability = enchantability;
        return this;
    }

    public ArmorMaterialBuilder sound(Holder<SoundEvent> sound) {
        this.sound = sound;
        return this;
    }

    public ArmorMaterialBuilder toughness(float toughness) {
        this.toughness = toughness;
        return this;
    }

    public ArmorMaterialBuilder repairMaterial(TagKey<Item> repairMaterial) {
        this.repairMaterial = repairMaterial;
        return this;
    }

    public ArmorMaterialBuilder knockbackResistance(float knockbackResistance) {
        this.knockbackResistance = knockbackResistance;
        return this;
    }

    public net.minecraft.world.item.equipment.ArmorMaterial build() {
        return new net.minecraft.world.item.equipment.ArmorMaterial(
                ArmorMaterialBuilder.this.maxDamageFactor,
                Map.of(
                        ArmorType.HELMET, damageReduction[0],
                        ArmorType.CHESTPLATE, damageReduction[2],
                        ArmorType.LEGGINGS, damageReduction[1],
                        ArmorType.BOOTS, damageReduction[3]
                ), enchantability, sound, toughness, knockbackResistance, repairMaterial, assetId
        );
    }
}