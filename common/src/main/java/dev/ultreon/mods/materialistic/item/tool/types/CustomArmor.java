package dev.ultreon.mods.materialistic.item.tool.types;

import dev.architectury.event.CompoundEventResult;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import dev.ultreon.mods.materialistic.item.ItemType;
import dev.ultreon.mods.materialistic.item.tool.TraitsItem;
import dev.ultreon.mods.materialistic.item.tool.trait.AbstractTrait;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import org.jspecify.annotations.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CustomArmor extends Item implements TraitsItem {
    private final ArmorType slot;
    private final Supplier<List<AbstractTrait>> traits;

    public CustomArmor(ArmorMaterial material, ArmorType slot, Properties builderIn, Supplier<List<AbstractTrait>> traits) {
        super(addProperties(builderIn.humanoidArmor(material, slot), traits.get()));
        this.slot = slot;
        this.traits = traits;
    }

    private static Properties addProperties(Properties properties, List<AbstractTrait> abstractTraits) {
        for (AbstractTrait abstractTrait : abstractTraits) {
            abstractTrait.addProperties(properties);
        }
        return properties;
    }

    @Override
    public void inventoryTick(@NonNull ItemStack itemStack, @NonNull ServerLevel level, @NonNull Entity owner, @org.jspecify.annotations.Nullable EquipmentSlot slot) {
        super.inventoryTick(itemStack, level, owner, slot);
        
        if (!(owner instanceof Player player)) return;
        
        for (AbstractTrait trait : this.getTraits()) {
            trait.onArmorTick(itemStack, level, player);
        }
    }

    @Override
    public final List<AbstractTrait> getTraits() {
        return this.traits.get().stream().filter(abstractTrait -> abstractTrait.isApplicable(this.getItemTypes())).toList();
    }

    @Override
    public Set<ItemType> getItemTypes() {
        return switch (this.slot) {
            case HELMET -> Set.of(ItemType.HELMET);
            case CHESTPLATE -> Set.of(ItemType.CHESTPLATE);
            case LEGGINGS -> Set.of(ItemType.LEGGINGS);
            case BOOTS -> Set.of(ItemType.BOOTS);
            case BODY -> Set.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        };
    }

    @Override
    public void appendHoverText(@NonNull ItemStack itemStack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, @NonNull Consumer<Component> builder, @NonNull TooltipFlag tooltipFlag) {
        for (AbstractTrait trait : this.getTraits()) {
            builder.accept(trait.getTranslation());
        }
    }

    static {
        EntityEvent.LIVING_HURT.register(CustomArmor::onLivingDamageEvent);
    }

    private static EventResult onLivingDamageEvent(LivingEntity livingEntity, DamageSource damageSource, float damage) {
        for (ItemStack stack : Arrays.stream(EquipmentSlot.values()).filter(EquipmentSlot::isArmor).map(livingEntity::getItemBySlot).filter((itemStack) -> itemStack.getItem() instanceof CustomArmor).toList()) {
            CustomArmor item = (CustomArmor) stack.getItem();
            CompoundEventResult<Float> eventResult = item.livingDamage(stack, livingEntity, damageSource, damage);
            if (eventResult.interruptsFurtherEvaluation()) {
                Float object = eventResult.object();
                if (eventResult.object() != null) {
                    livingEntity.hurt(damageSource, object);
                }
                return EventResult.interruptFalse();
            }
        }
        return EventResult.interruptTrue();
    }

    private CompoundEventResult<Float> livingDamage(ItemStack stack, LivingEntity victim, DamageSource damageSource, final float damage) {
        float smite = 0f;
        float finalDamage = damage;
        for (AbstractTrait trait : this.getTraits()) {
            Float modified = trait.onLivingDamage(stack, victim, damageSource, finalDamage);
            if (modified == null)
                return CompoundEventResult.interruptFalse(null);

            modified = trait.livingHurt(stack, victim, damageSource, modified);
            if (modified == null)
                return CompoundEventResult.interruptFalse(null);

            finalDamage = modified;

            float smiteValue = trait.getSmiteValue(this.getItemTypes(), victim);
            smite += smiteValue;
        }

        Entity directAttacker = damageSource.getDirectEntity();
        if (directAttacker instanceof LivingEntity mob && mob.is(EntityTypeTags.UNDEAD) && smite > 0.0F) {
            if (mob.level() instanceof ServerLevel serverLevel)
                directAttacker.hurtServer(serverLevel, damageSource, smite);
        }
        return CompoundEventResult.interruptFalse(finalDamage);
    }
}
