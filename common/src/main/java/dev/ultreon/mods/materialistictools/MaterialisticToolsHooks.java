package dev.ultreon.mods.materialistictools;

import dev.ultreon.mods.materialistictools.item.tool.Toolset;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

public class MaterialisticToolsHooks {
    public static void destroyProgressHook(BlockState state, Player player, CallbackInfoReturnable<Float> cir) {
        if (state.is(Blocks.BEDROCK) && player.getItemInHand(InteractionHand.MAIN_HAND).is(Toolset.INFINITY.getPickaxe().get())) {
            cir.setReturnValue(0.01F);
        }
    }

    public static void dropsHook(BlockState state, LootParams.Builder params, CallbackInfoReturnable<List<ItemStack>> cir) {
        if (state.is(Blocks.BEDROCK)) {
            cir.setReturnValue(List.of(new ItemStack(Blocks.BEDROCK)));
        }
    }
}
