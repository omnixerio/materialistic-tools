package dev.ultreon.mods.materialistic.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.ultreon.mods.materialistic.item.material.ItemMaterial;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.ApiStatus;

import static dev.ultreon.mods.materialistic.Materialistic.MOD_ID;

@SuppressWarnings("unused")
public class MaterialisticBlocks {
    @ApiStatus.Internal
    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(MOD_ID, Registries.BLOCK);

    static {
        ItemMaterial.registerBlocks();
    }

    public static void register() {
        REGISTER.register();
    }
}
