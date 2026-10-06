package dev.ultreon.mods.materialistic.item.tool.types;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import dev.ultreon.mods.materialistic.item.ItemType;
import dev.ultreon.mods.materialistic.item.tool.TraitsItem;
import dev.ultreon.mods.materialistic.item.tool.trait.AbstractTrait;
import dev.ultreon.mods.materialistic.util.ItemUtils;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CustomAxe extends AxeItem implements TraitsItem {
    private final Supplier<List<AbstractTrait>> traits;

    protected static final UUID ATTACK_KNOCKBACK_MODIFIER = UUID.nameUUIDFromBytes("Attack Knockback".getBytes());

    public CustomAxe(ToolMaterial material, float attackDamageIn, float attackSpeedIn, Properties properties, Supplier<List<AbstractTrait>> traits) {
        super(material, attackDamageIn, attackSpeedIn, addProperties(properties, traits.get()));
        this.traits = traits;
    }

    private static Properties addProperties(Properties properties, List<AbstractTrait> abstractTraits) {
        for (AbstractTrait abstractTrait : abstractTraits) {
            abstractTrait.addProperties(properties);
        }
        return properties;
    }

    @Override
    public float getAttackDamageBonus(@NonNull Entity victim, float damage, @NonNull DamageSource damageSource) {
        float smite = 0f;
        for (AbstractTrait trait : this.getTraits()) {
            Entity entity = damageSource.getEntity();
            smite += trait.getSmiteValue(this.getItemTypes(), entity == null ? damageSource.getDirectEntity() : entity);
        }

        float attackDamageBonus = super.getAttackDamageBonus(victim, damage, damageSource);
        if (victim instanceof LivingEntity livingEntity && livingEntity.is(EntityTypeTags.UNDEAD))
            attackDamageBonus += smite;

        return attackDamageBonus;
    }

    @Override
    public Set<ItemType> getItemTypes() {
        return Set.of(ItemType.AXE);
    }

    @Override
    public List<AbstractTrait> getTraits() {
        return this.traits.get().stream().filter(abstractTrait -> abstractTrait.isApplicable(this.getItemTypes())).toList();
    }

    @Override
    public @NonNull InteractionResult useOn(@NonNull UseOnContext context) {
        InteractionResult result = super.useOn(context);
        for (AbstractTrait trait : this.getTraits()) {
            InteractionResult actionResultType = trait.onUseItem(context);
            result = ItemUtils.maxActionResult(result, actionResultType);
        }
        return result;
    }

    @Override
    public boolean useOnRelease(@NonNull ItemStack itemStack) {
        boolean val = false;
        for (AbstractTrait trait : this.getTraits()) {
            val |= trait.onRightClick(itemStack);
        }
        return val;
    }

    @Override
    public void hurtEnemy(@NonNull ItemStack stack, @NonNull LivingEntity victim, @NonNull LivingEntity attacker) {
        super.hurtEnemy(stack, victim, attacker);
    }

    @Override
    public void inventoryTick(@NonNull ItemStack stack, @NonNull ServerLevel level, @NonNull Entity owner, @Nullable EquipmentSlot slot) {
        for (AbstractTrait trait : this.getTraits()) {
            trait.onInventoryTick(stack, level, owner, slot);
        }
    }

    @Override
    public boolean mineBlock(@NonNull ItemStack stack, @NonNull Level dimension, @NonNull BlockState state, @NonNull BlockPos pos, @NonNull LivingEntity living) {
        boolean op = super.mineBlock(stack, dimension, state, pos, living);
        for (AbstractTrait trait : this.getTraits()) {
            op |= trait.onBlockBroken(stack, dimension, state, pos, living);
        }
        return op;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        for (AbstractTrait trait : this.getTraits()) {
            builder.accept(trait.getTranslation());
        }
    }

    @Override
    public float getDestroySpeed(@NonNull ItemStack stack, @NonNull BlockState state) {
        float val = super.getDestroySpeed(stack, state);
        for (AbstractTrait trait : this.getTraits()) {
            val *= trait.getDestroyMultiplier(this.getItemTypes(), stack, state);
        }
        for (AbstractTrait trait : this.getTraits()) {
            val += trait.getDestroyModifier(this.getItemTypes(), stack, state);
        }
        for (AbstractTrait trait : this.getTraits()) {
            val *= trait.getDestroyTotalMultiplier(this.getItemTypes(), stack, state);
        }
        return val;
    }
}