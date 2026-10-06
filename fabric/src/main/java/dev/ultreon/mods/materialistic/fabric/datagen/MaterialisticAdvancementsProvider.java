package dev.ultreon.mods.materialistic.fabric.datagen;

import dev.ultreon.mods.materialistic.Materialistic;
import dev.ultreon.mods.materialistic.item.tool.Toolset;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class MaterialisticAdvancementsProvider extends FabricAdvancementProvider {
    protected MaterialisticAdvancementsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(HolderLookup.@NonNull Provider registryLookup, @NonNull Consumer<AdvancementHolder> consumer) {

        AdvancementHolder root = Advancement.Builder.advancement()
                .display(
                        Items.STONE,
                        Component.translatable("advancements.materialistic.root.title"), // The title
                        Component.translatable("advancements.materialistic.root.description"), // The description
                        Materialistic.id("gui/advancement_background"), // Background image for the tab in the advancements page, if this is a root advancement (has no parent)
                        AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
                        false, // Show the toast when completing it
                        false, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .addCriterion("has_crafting_table", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CRAFTING_TABLE))
                // Give the advancement an id
                .save(consumer, Materialistic.id("root"));

        AdvancementHolder wood = Advancement.Builder.advancement()
                .display(
                        Items.WOODEN_PICKAXE,
                        Component.translatable("advancements.materialistic.got_wooden_pickaxe.title"),
                        Component.translatable("advancements.materialistic.got_wooden_pickaxe.description"),
                        Materialistic.id("gui/advancement_background"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("got_wooden_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WOODEN_PICKAXE))
                .parent(root)
                .save(consumer, Materialistic.id("got_wooden_pickaxe"));

        AdvancementHolder electrum = Advancement.Builder.advancement()
                .display(
                        Toolset.ELECTRUM.getPickaxe().get(),
                        Component.translatable("advancements.materialistic.got_electrum_pickaxe.title"),
                        Component.translatable("advancements.materialistic.got_electrum_pickaxe.description"),
                        Materialistic.id("gui/advancement_background"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("got_electrum_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(Toolset.ELECTRUM.getPickaxe().get()))
                .parent(root)
                .save(consumer, Materialistic.id("got_electrum_pickaxe"));

        AdvancementHolder stone = Advancement.Builder.advancement()
                .display(
                        Items.STONE_PICKAXE,
                        Component.translatable("advancements.materialistic.got_stone_pickaxe.title"),
                        Component.translatable("advancements.materialistic.got_stone_pickaxe.description"),
                        Materialistic.id("gui/advancement_background"),
                        AdvancementType.TASK,
                        false,
                        true,
                        false
                )
                .addCriterion("got_stone_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE_PICKAXE))
                .parent(wood)
                .save(consumer, Materialistic.id("got_stone_pickaxe"));

        AdvancementHolder lumium = Advancement.Builder.advancement()
                .display(
                        Toolset.LUMIUM.getPickaxe().get(),
                        Component.translatable("advancements.materialistic.got_lumium_pickaxe.title"),
                        Component.translatable("advancements.materialistic.got_lumium_pickaxe.description"),
                        Materialistic.id("gui/advancement_background"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("got_lumium_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(Toolset.LUMIUM.getPickaxe().get()))
                .parent(wood)
                .save(consumer, Materialistic.id("got_lumium_pickaxe"));

        AdvancementHolder iron = Advancement.Builder.advancement()
                .display(
                        Items.IRON_PICKAXE,
                        Component.translatable("advancements.materialistic.got_iron_pickaxe.title"),
                        Component.translatable("advancements.materialistic.got_iron_pickaxe.description"),
                        Materialistic.id("gui/advancement_background"),
                        AdvancementType.TASK,
                        false,
                        true,
                        false
                )
                .addCriterion("got_iron_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_PICKAXE))
                .parent(stone)
                .save(consumer, Materialistic.id("got_iron_pickaxe"));

        AdvancementHolder silver = Advancement.Builder.advancement()
                .display(
                        Toolset.SILVER.getPickaxe().get(),
                        Component.translatable("advancements.materialistic.got_silver_pickaxe.title"),
                        Component.translatable("advancements.materialistic.got_silver_pickaxe.description"),
                        Materialistic.id("gui/advancement_background"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("got_silver_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(Toolset.SILVER.getPickaxe().get()))
                .parent(stone)
                .save(consumer, Materialistic.id("got_silver_pickaxe"));

        AdvancementHolder lead = Advancement.Builder.advancement()
                .display(
                        Toolset.LEAD.getPickaxe().get(),
                        Component.translatable("advancements.materialistic.got_lead_pickaxe.title"),
                        Component.translatable("advancements.materialistic.got_lead_pickaxe.description"),
                        Materialistic.id("gui/advancement_background"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("got_lead_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(Toolset.LEAD.getPickaxe().get()))
                .parent(stone)
                .save(consumer, Materialistic.id("got_lead_pickaxe"));

        AdvancementHolder aluminum = Advancement.Builder.advancement()
                .display(
                        Toolset.ALUMINUM.getPickaxe().get(),
                        Component.translatable("advancements.materialistic.got_aluminum_pickaxe.title"),
                        Component.translatable("advancements.materialistic.got_aluminum_pickaxe.description"),
                        Materialistic.id("gui/advancement_background"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("got_aluminum_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(Toolset.ALUMINUM.getPickaxe().get()))
                .parent(stone)
                .save(consumer, Materialistic.id("got_aluminum_pickaxe"));

        AdvancementHolder uranium = Advancement.Builder.advancement()
                .display(
                        Toolset.URANIUM.getPickaxe().get(),
                        Component.translatable("advancements.materialistic.got_uranium_pickaxe.title"),
                        Component.translatable("advancements.materialistic.got_uranium_pickaxe.description"),
                        Materialistic.id("gui/advancement_background"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("got_uranium_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(Toolset.URANIUM.getPickaxe().get()))
                .parent(stone)
                .save(consumer, Materialistic.id("got_uranium_pickaxe"));

        AdvancementHolder redstone = Advancement.Builder.advancement()
                .display(
                        Toolset.REDSTONE.getPickaxe().get(),
                        Component.translatable("advancements.materialistic.got_redstone_pickaxe.title"),
                        Component.translatable("advancements.materialistic.got_redstone_pickaxe.description"),
                        Materialistic.id("gui/advancement_background"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("got_redstone_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(Toolset.REDSTONE.getPickaxe().get()))
                .parent(stone)
                .save(consumer, Materialistic.id("got_redstone_pickaxe"));

        AdvancementHolder gold = Advancement.Builder.advancement()
                .display(
                        Items.GOLDEN_PICKAXE,
                        Component.translatable("advancements.materialistic.got_golden_pickaxe.title"),
                        Component.translatable("advancements.materialistic.got_golden_pickaxe.description"),
                        Materialistic.id("gui/advancement_background"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("got_golden_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLDEN_PICKAXE))
                .parent(iron)
                .save(consumer, Materialistic.id("got_golden_pickaxe"));

        AdvancementHolder platinum = Advancement.Builder.advancement()
                .display(
                        Toolset.PLATINUM.getPickaxe().get(),
                        Component.translatable("advancements.materialistic.got_platinum_pickaxe.title"),
                        Component.translatable("advancements.materialistic.got_platinum_pickaxe.description"),
                        Materialistic.id("gui/advancement_background"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("got_platinum_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(Toolset.PLATINUM.getPickaxe().get()))
                .parent(iron)
                .save(consumer, Materialistic.id("got_platinum_pickaxe"));

        AdvancementHolder enderium = Advancement.Builder.advancement()
                .display(
                        Toolset.ENDERIUM.getPickaxe().get(),
                        Component.translatable("advancements.materialistic.got_enderium_pickaxe.title"),
                        Component.translatable("advancements.materialistic.got_enderium_pickaxe.description"),
                        Materialistic.id("gui/advancement_background"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("got_enderium_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(Toolset.ENDERIUM.getPickaxe().get()))
                .parent(iron)
                .save(consumer, Materialistic.id("got_enderium_pickaxe"));

        AdvancementHolder diamond = Advancement.Builder.advancement()
                .display(
                        Items.DIAMOND_PICKAXE,
                        Component.translatable("advancements.materialistic.got_diamond_pickaxe.title"),
                        Component.translatable("advancements.materialistic.got_diamond_pickaxe.description"),
                        Materialistic.id("gui/advancement_background"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("got_diamond_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND_PICKAXE))
                .parent(iron)
                .save(consumer, Materialistic.id("got_diamond_pickaxe"));

        AdvancementHolder netherite = Advancement.Builder.advancement()
                .display(
                        Items.NETHERITE_PICKAXE,
                        Component.translatable("advancements.materialistic.got_netherite_pickaxe.title"),
                        Component.translatable("advancements.materialistic.got_netherite_pickaxe.description"),
                        Materialistic.id("gui/advancement_background"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("got_netherite_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_PICKAXE))
                .parent(diamond)
                .save(consumer, Materialistic.id("got_netherite_pickaxe"));

        AdvancementHolder mithril = Advancement.Builder.advancement()
                .display(
                        Toolset.MITHRIL.getPickaxe().get(),
                        Component.translatable("advancements.materialistic.got_mithril_pickaxe.title"),
                        Component.translatable("advancements.materialistic.got_mithril_pickaxe.description"),
                        Materialistic.id("gui/advancement_background"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("got_mithril_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(Toolset.MITHRIL.getPickaxe().get()))
                .parent(diamond)
                .save(consumer, Materialistic.id("got_mithril_pickaxe"));

        AdvancementHolder obsidian = Advancement.Builder.advancement()
                .display(
                        Toolset.OBSIDIAN.getPickaxe().get(),
                        Component.translatable("advancements.materialistic.got_obsidian_pickaxe.title"),
                        Component.translatable("advancements.materialistic.got_obsidian_pickaxe.description"),
                        Materialistic.id("gui/advancement_background"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("got_obsidian_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(Toolset.OBSIDIAN.getPickaxe().get()))
                .parent(diamond)
                .save(consumer, Materialistic.id("got_obsidian_pickaxe"));

        AdvancementHolder cobalt = Advancement.Builder.advancement()
                .display(
                        Toolset.COBALT.getPickaxe().get(),
                        Component.translatable("advancements.materialistic.got_cobalt_pickaxe.title"),
                        Component.translatable("advancements.materialistic.got_cobalt_pickaxe.description"),
                        Materialistic.id("gui/advancement_background"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("got_cobalt_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(Toolset.COBALT.getPickaxe().get()))
                .parent(netherite)
                .save(consumer, Materialistic.id("got_cobalt_pickaxe"));

        AdvancementHolder ultrinium = Advancement.Builder.advancement()
                .display(
                        Toolset.ULTRINIUM.getPickaxe().get(),
                        Component.translatable("advancements.materialistic.got_ultrinium_pickaxe.title"),
                        Component.translatable("advancements.materialistic.got_ultrinium_pickaxe.description"),
                        Materialistic.id("gui/advancement_background"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("got_ultrinium_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(Toolset.ULTRINIUM.getPickaxe().get()))
                .parent(cobalt)
                .save(consumer, Materialistic.id("got_ultrinium_pickaxe"));

        AdvancementHolder chunk = Advancement.Builder.advancement()
                .display(
                        Toolset.CHUNK.getPickaxe().get(),
                        Component.translatable("advancements.materialistic.got_chunk_pickaxe.title"),
                        Component.translatable("advancements.materialistic.got_chunk_pickaxe.description"),
                        Materialistic.id("gui/advancement_background"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("got_chunk_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(Toolset.CHUNK.getPickaxe().get()))
                .parent(ultrinium)
                .save(consumer, Materialistic.id("got_chunk_pickaxe"));

        AdvancementHolder infinity = Advancement.Builder.advancement()
                .display(
                        Toolset.INFINITY.getPickaxe().get(),
                        Component.translatable("advancements.materialistic.got_infinity_pickaxe.title"),
                        Component.translatable("advancements.materialistic.got_infinity_pickaxe.description"),
                        Materialistic.id("gui/advancement_background"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("got_infinity_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(Toolset.INFINITY.getPickaxe().get()))
                .parent(chunk)
                .save(consumer, Materialistic.id("got_infinity_pickaxe"));

    }
}
