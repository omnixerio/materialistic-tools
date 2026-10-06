package dev.ultreon.mods.materialistictools.item.tool.trait;

import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import dev.ultreon.mods.materialistictools.MaterialisticTools;
import dev.ultreon.mods.materialistictools.item.ItemCategory;

public class EmpoweredTrait extends AbstractTrait {
    public EmpoweredTrait() {
        super(ItemCategory.WEAPON);
    }

    @Override
    public boolean onHitEntity(@NotNull ItemStack stack, @NotNull LivingEntity victim, LivingEntity attacker) {
        if (attacker.level().isClientSide()) {
            // Don't do anything on client.
            return true;
        }

        Level level = victim.level();
        if (attacker.getRandom().nextInt(15) == 0) {
            LightningBolt lightningBolt = EntityTypes.LIGHTNING_BOLT.create((ServerLevel) level, null, victim.blockPosition(), EntitySpawnReason.MOB_SUMMONED, false, false);
            if (lightningBolt != null) {
                level.addFreshEntity(lightningBolt);
            } else {
                MaterialisticTools.LOGGER.warn(MARKER, "Couldn't summon lightning bolt");
            }
        }
        return true;
    }

    @Override
    public TextColor getColor() {
        return TextColor.parseColor("#CCAA00").getOrThrow();
    }
}
