package dev.ultreon.mods.materialistictools.mixin;

import dev.ultreon.mods.materialistictools.MaterialisticToolsHooks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(BlockBehaviour.class)
public class BlockBehaviorMixin {
    @Inject(method = "getDestroyProgress", at = @At(value = "HEAD"), cancellable = true)
    public void getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos, CallbackInfoReturnable<Float> cir) {
        MaterialisticToolsHooks.destroyProgressHook(state, player, cir);
    }
    @Inject(method = "getDrops", at = @At(value = "HEAD"), cancellable = true)
    public void getDrops(BlockState state, LootParams.Builder params, CallbackInfoReturnable<List<ItemStack>> cir) {
        MaterialisticToolsHooks.dropsHook(state, params, cir);
    }

}
