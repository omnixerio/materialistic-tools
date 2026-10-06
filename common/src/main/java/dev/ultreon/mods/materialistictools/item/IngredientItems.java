package dev.ultreon.mods.materialistictools.item;

import dev.architectury.registry.registries.RegistrySupplier;
import dev.ultreon.mods.materialistictools.MaterialisticTools;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;
import dev.ultreon.mods.materialistictools.init.ModItems;

public enum IngredientItems implements ItemLike {
    URANIUM_ROD,
    FIRE_GEM,
    FIRE_POWDER,
    FIRE_ROD,
    ;

    private final FoodProperties food;
    private RegistrySupplier<Item> item;

    IngredientItems() {
        this(null);
    }

    IngredientItems(FoodProperties food) {
        this.food = food;
    }

    public static void register() {
        for (IngredientItems item : values()) {
            item.item = ModItems.REGISTER.register(item.getName(), () -> new Item(createProperties(item.food).setId(ResourceKey.create(Registries.ITEM, MaterialisticTools.id(item.getName())))));
        }
    }

    private static Item.Properties createProperties(FoodProperties food) {
        Item.Properties properties = new Item.Properties();
        if (food != null) properties = properties.food(food);
        return properties;
    }

    @Override
    public @NotNull Item asItem() {
        return this.item.get();
    }

    public String getName() {
        return this.name().toLowerCase();
    }
}
