package dev.ultreon.mods.materialistic.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import dev.ultreon.mods.materialistic.item.tool.types.CustomSword;

import java.util.Collections;

public class HookItem extends CustomSword {
    public HookItem(ToolMaterial material, float attackDamage, float speed, Item.Properties properties) {
        super(material, attackDamage, speed, properties, Collections::emptyList);
    }
}