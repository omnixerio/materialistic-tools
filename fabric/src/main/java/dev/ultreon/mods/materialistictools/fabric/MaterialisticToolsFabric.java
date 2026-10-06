package dev.ultreon.mods.materialistictools.fabric;

import dev.ultreon.mods.materialistictools.MaterialisticTools;
import net.fabricmc.api.ModInitializer;

public class MaterialisticToolsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        MaterialisticTools.init();
        MaterialisticTools.postInit();
    }
}