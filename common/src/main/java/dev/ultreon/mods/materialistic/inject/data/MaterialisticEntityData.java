package dev.ultreon.mods.materialistic.inject.data;

import net.minecraft.nbt.CompoundTag;

public class MaterialisticEntityData {
    public int abilityTicker;

    public MaterialisticEntityData(CompoundTag tag) {
        this.abilityTicker = tag.getInt("abilityTicker").orElse(0);
    }

    public MaterialisticEntityData() {

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
