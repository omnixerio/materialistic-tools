package dev.ultreon.mods.materialistic.neoforge;

import dev.ultreon.mods.materialistic.Materialistic;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;

@Mod(value = Materialistic.MOD_ID)
public class MaterialisticNeoForge {
    public MaterialisticNeoForge(IEventBus modEventBus, ModContainer modContainer) {
        // Submit our event bus to let architectury register our content on the right time
        Materialistic.init();

        modEventBus.register(this);
    }

    @SubscribeEvent
    public void onLoadComplete(FMLLoadCompleteEvent evt) {
        Materialistic.postInit();
    }
}