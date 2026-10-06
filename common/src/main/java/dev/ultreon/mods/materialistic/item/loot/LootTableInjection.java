//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package dev.ultreon.mods.materialistic.item.loot;

import dev.architectury.event.events.common.LootEvent;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.HashMap;
import java.util.Map;

import static dev.ultreon.mods.materialistic.Materialistic.MOD_ID;

public class LootTableInjection {
    private static final Map<Identifier, Injector> injections = new HashMap<>();

    public static Identifier mcLoc(String path) {
        return Identifier.withDefaultNamespace(path);
    }

    public static Identifier forgeLoc(String path) {
        return Identifier.fromNamespaceAndPath("neoforge", path);
    }

    public static Identifier modLoc(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    public static void register() {
        LootEvent.MODIFY_LOOT_TABLE.register(LootTableInjection::inject);
    }

    private static void inject(ResourceKey<LootTable> key, LootEvent.LootTableModificationContext context, boolean builtin) {
        Injector injector = injections.get(key.identifier());
        if (injector != null) {
            context.addPool(injector.createPool());
        }
    }

    public static void registerInjection(Identifier target, Identifier injection) {
        injections.put(target, new Injector(target, injection));
    }

    public static void registerInjection(Identifier target, String modId) {
        registerInjection(target, Identifier.fromNamespaceAndPath(modId, target.getPath()));
    }

    private record Injector(Identifier target, Identifier injection) {
        private Injector(Identifier target, Identifier injection) {
            this.target = target;
            this.injection = injection;
        }

        private LootPool.Builder createPool() {
            return LootPool.lootPool()
                    .add(createInjectionEntry(this.injection))
                    .setBonusRolls(UniformGenerator.between(0.0F, 1.0F));
        }

        private static LootPoolEntryContainer.Builder<?> createInjectionEntry(Identifier name) {
            return NestedLootTable.lootTableReference(ResourceKey.create(Registries.LOOT_TABLE, Identifier.fromNamespaceAndPath(name.getNamespace(), "inject/" + name.getPath()))).setWeight(1);
        }

        public Identifier target() {
            return this.target;
        }

        public Identifier injection() {
            return this.injection;
        }
    }
}
