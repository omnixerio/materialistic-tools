package dev.ultreon.mods.materialistictools;

import dev.ultreon.mods.materialistictools.init.*;
import dev.ultreon.mods.materialistictools.init.*;
import dev.ultreon.mods.materialistictools.item.tool.ModTraits;
import dev.ultreon.mods.materialistictools.stats.ModStats;
import dev.ultreon.mods.materialistictools.world.gen.ores.ModOres;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaterialisticTools
{
	public static final String MOD_ID = "materialistic_tools";
	public static final Logger LOGGER = LoggerFactory.getLogger("MaterialisticTools");

	public static void init() {
		ModBlocks.register();
		ModItems.register();
		ModEffects.register();
		ModOres.register();
		ModTraits.register();
		ModCreativeTabs.register();
		ModStats.register();
	}

	public static void postInit() {
		ModToolMaterials.postInit();
	}

	public static Identifier id(String name) {
		return Identifier.fromNamespaceAndPath(MOD_ID, name);
	}
}
