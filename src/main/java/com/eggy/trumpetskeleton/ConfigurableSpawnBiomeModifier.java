package com.eggy.trumpetskeleton;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;

public record ConfigurableSpawnBiomeModifier(HolderSet<Biome> biomes) implements BiomeModifier {

    public static final MapCodec<ConfigurableSpawnBiomeModifier> CODEC =
            Biome.LIST_CODEC.fieldOf("biomes").xmap(ConfigurableSpawnBiomeModifier::new, ConfigurableSpawnBiomeModifier::biomes);

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        int weight = ModConfig.SKELETON_SPAWN_WEIGHT.get();
        int min = ModConfig.SPAWN_MIN_COUNT.get();

        int max = Math.max(min, ModConfig.SPAWN_MAX_COUNT.get());

        if (phase == Phase.ADD && this.biomes.contains(biome)) {
            builder.getMobSpawnSettings().addSpawn(
                    MobCategory.MONSTER,
                    new MobSpawnSettings.SpawnerData(
                            TrumpetSkeleton.TRUMPET_SKELETON.get(),
                            weight,
                            min,
                            max
                    )
            );
        }
    }

    @Override
    public MapCodec<? extends BiomeModifier> codec() {
        return CODEC;
    }
}