package com.eggy.trumpetskeleton;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ModConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    // Item Settings
    public static final ModConfigSpec.IntValue TRUMPET_COOLDOWN;
    public static final ModConfigSpec.DoubleValue KNOCKBACK_STRENGTH;

    // Sound Settings
    public static final ModConfigSpec.DoubleValue SOUND_VOLUME;

    // Mob Spawn Settings
    public static final ModConfigSpec.IntValue SKELETON_SPAWN_WEIGHT;
    public static final ModConfigSpec.IntValue SPAWN_MIN_COUNT;
    public static final ModConfigSpec.IntValue SPAWN_MAX_COUNT;

    static {
        BUILDER.push("Trumpet Item Settings");
        TRUMPET_COOLDOWN = BUILDER
                .comment("Cooldown in ticks (20 ticks = 1 second) after using the trumpet.")
                .defineInRange("trumpetCooldown", 20, 0, 72000);

        KNOCKBACK_STRENGTH = BUILDER
                .comment("Knockback/blast force applied to targets.")
                .defineInRange("knockbackStrength", 1.5D, 0.0D, 10.0D);
        BUILDER.pop();

        BUILDER.push("Sound Settings");
        SOUND_VOLUME = BUILDER
                .comment("Master volume for the trumpet doot sound.")
                .defineInRange("soundVolume", 1.0D, 0.0D, 5.0D);
        BUILDER.pop();

        BUILDER.push("Spawn Settings");
        SKELETON_SPAWN_WEIGHT = BUILDER
                .comment("Spawn weight lottery tickets. Higher means more frequent.")
                .defineInRange("spawnWeight", 2, 1, 100);

        SPAWN_MIN_COUNT = BUILDER
                .comment("Minimum number of skeletons in a group.")
                .defineInRange("minGroupSize", 1, 1, 10);

        SPAWN_MAX_COUNT = BUILDER
                .comment("Maximum number of skeletons in a group.")
                .defineInRange("maxGroupSize", 3, 1, 10);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}