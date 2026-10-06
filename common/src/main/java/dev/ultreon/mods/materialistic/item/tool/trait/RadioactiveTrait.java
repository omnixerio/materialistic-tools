package dev.ultreon.mods.materialistic.item.tool.trait;

import dev.ultreon.mods.materialistic.effect.RadiationEffect;
import dev.ultreon.mods.materialistic.init.MaterialisticEffects;
import dev.ultreon.mods.materialistic.item.ItemType;
import dev.ultreon.mods.materialistic.item.tool.Toolset;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.TextColor;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

/**
 * Radioactive tool trait.
 *
 * @see RadiationEffect
 * @see Toolset#URANIUM
 */
public class RadioactiveTrait extends AbstractMobEffectTrait {
    public RadioactiveTrait() {
        super(ItemType.SWORD, ItemType.AXE, ItemType.PICKAXE, ItemType.SHOVEL);
    }

    @Override
    public TextColor getColor() {
        return TextColor.parseColor("#00FF00").getOrThrow();
    }

    @Override
    public List<ItemStack> getDrops(List<ItemStack> stacks, ItemStack tool, Entity entity, Level level, BlockState state) {
        List<ItemStack> drops = super.getDrops(stacks, tool, entity, level, state);

        if (level.getRandom().nextInt(5) == 0 && (state.is(BlockTags.FLOWERS) || state.is(BlockTags.LEAVES) || state.is(BlockTags.SMALL_FLOWERS))) {
            drops.clear();
            drops.add(new ItemStack(Items.CHARCOAL));
        } else if (level.getRandom().nextInt(10) == 0 && (state.is(BlockTags.WOOL) || state.is(BlockTags.WOOL_CARPETS))) {
            drops.clear();
            drops.add(new ItemStack(Items.CHARCOAL));
        } else if (level.getRandom().nextInt(15) == 0 && state.is(BlockTags.OVERWORLD_NATURAL_LOGS) || state.is(BlockTags.OVERWORLD_NATURAL_LOGS)) {
            drops.clear();
            drops.add(new ItemStack(Items.CHARCOAL));
        } else if (level.getRandom().nextInt(20) == 0 && state.is(BlockTags.PLANKS)) {
            drops.clear();
            drops.add(new ItemStack(Items.CHARCOAL));
        }

        return drops;
    }

    @Override
    public MobEffectInstance getEffectInstance() {
        return new MobEffectInstance((Holder) MaterialisticEffects.RADIATION.asHolder(), 1200, 5);
    }
}
