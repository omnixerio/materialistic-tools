package dev.ultreon.mods.materialistictools.inject.data;

import net.minecraft.nbt.CompoundTag;

public class ModEntityData {
    public int abilityTicker;

    public ModEntityData(CompoundTag tag) {
        this.abilityTicker = tag.getInt("abilityTicker").orElse(0);
    }

    public ModEntityData() {

    }

    public void tick() {
        if (this.abilityTicker-- == 0) {
            this.abilityTicker = -1;
        } else if (this.abilityTicker < -1) {
            this.abilityTicker = -1;
        }
    }

    public void save(CompoundTag tag) {
        tag.putInt("abilityTicker", abilityTicker);
    }
}
