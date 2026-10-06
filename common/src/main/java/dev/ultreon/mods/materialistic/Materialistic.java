package dev.ultreon.mods.materialistic;

import dev.ultreon.mods.materialistic.init.*;
import dev.ultreon.mods.materialistic.item.tool.ModTraits;
import dev.ultreon.mods.materialistic.init.MaterialisticStats;
import dev.ultreon.mods.materialistic.init.MaterialisticOres;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Materialistic
{
	public static final String MOD_ID = "materialistic";
	public static final Logger LOGGER = LoggerFactory.getLogger("MaterialisticTools");

	public static void init() {
		MaterialisticBlocks.register();
		MaterialisticItems.register();
		MaterialisticEffects.register();
		MaterialisticOres.register();
		ModTraits.register();
		MaterialisticCreativeTabs.register();
		MaterialisticStats.register();
	}

	public static void postInit() {
		MaterialisticToolMaterials.postInit();
	}

	public static Identifier id(String name) {
		return Identifier.fromNamespaceAndPath(MOD_ID, name);
	}
}
