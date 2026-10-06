package dev.ultreon.mods.materialistic.fabric;

import dev.ultreon.mods.materialistic.Materialistic;
import net.fabricmc.api.ModInitializer;

public class MaterialisticFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Materialistic.init();
        Materialistic.postInit();
    }
}