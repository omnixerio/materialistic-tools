package dev.ultreon.mods.materialistictools.neoforge;

import dev.ultreon.mods.materialistictools.MaterialisticTools;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;

public class MaterialisticToolsNeoForge {
    public MaterialisticToolsNeoForge(IEventBus modEventBus, ModContainer modContainer) {
        // Submit our event bus to let architectury register our content on the right time
        MaterialisticTools.init();

        modEventBus.register(this);
    }

    @SubscribeEvent
    public void onLoadComplete(FMLLoadCompleteEvent evt) {
        MaterialisticTools.postInit();
    }
}