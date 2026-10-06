package dev.ultreon.mods.materialistictools.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.ultreon.mods.materialistictools.MaterialisticTools;
import dev.ultreon.mods.materialistictools.item.IngredientItems;
import dev.ultreon.mods.materialistictools.item.material.ItemMaterial;
import dev.ultreon.mods.materialistictools.item.tool.Toolset;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Function;

import static dev.ultreon.mods.materialistictools.MaterialisticTools.MOD_ID;

@SuppressWarnings("unused")
public class ModItems {
    @ApiStatus.Internal
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(MOD_ID, Registries.ITEM);

    private static <T extends Item> RegistrySupplier<T> register(String name, Item.Properties properties, Function<Item.Properties, T> itemMap) {
        return REGISTER.register(name, () -> itemMap.apply(properties.setId(ResourceKey.create(Registries.ITEM, MaterialisticTools.id(name)))));
    }

    static {
        ItemMaterial.registerItems();
        Toolset.register();
        IngredientItems.register();
    }

    public static void register() {
        REGISTER.register();
    }
}
