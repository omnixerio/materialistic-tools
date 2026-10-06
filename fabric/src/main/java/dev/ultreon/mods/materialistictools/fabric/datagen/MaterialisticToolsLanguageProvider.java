package dev.ultreon.mods.materialistictools.fabric.datagen;

import dev.ultreon.mods.materialistictools.MaterialisticTools;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class MaterialisticToolsLanguageProvider extends FabricLanguageProvider {
    public MaterialisticToolsLanguageProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generateTranslations(HolderLookup.@NonNull Provider registryLookup, @NonNull TranslationBuilder translationBuilder) {
        for (Block block : BuiltInRegistries.BLOCK) {
            Identifier key = BuiltInRegistries.BLOCK.getKey(block);
            if (!key.getNamespace().equals(MaterialisticTools.MOD_ID))
                continue;
            translationBuilder.add(block, caseConvert(key.getPath()));
        }

        for (Item item : BuiltInRegistries.ITEM) {
            Identifier key = BuiltInRegistries.ITEM.getKey(item);
            if (!key.getNamespace().equals(MaterialisticTools.MOD_ID))
                continue;
            translationBuilder.add(item, caseConvert(key.getPath()));
        }
    }

    private String caseConvert(String string) {
        StringBuilder sb = new StringBuilder();
        boolean nextUppercase = true;
        for (char c : string.toCharArray()) {
            if (nextUppercase) {
                sb.append(Character.toUpperCase(c));
                nextUppercase = false;
                continue;
            }
            if (c == '_') {
                nextUppercase = true;
                sb.append(' ');
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    @Override
    public @NonNull String getName() {
        return "Materialistic English Translations";
    }
}