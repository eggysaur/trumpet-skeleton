package com.eggy.trumpetskeleton;

import com.mojang.serialization.MapCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.common.world.BiomeModifier;
import org.slf4j.Logger;
import net.neoforged.fml.config.ModConfig.Type;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

@Mod(TrumpetSkeleton.MODID)
public class TrumpetSkeleton {

    public static final String MODID = "trumpetskeleton";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, MODID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, MODID);

    public static final DeferredRegister<MapCodec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MODID);

    public static final DeferredHolder<MapCodec<? extends BiomeModifier>, MapCodec<ConfigurableSpawnBiomeModifier>> CONFIG_SPAWNS =
            BIOME_MODIFIER_SERIALIZERS.register("configurable_spawns", () -> ConfigurableSpawnBiomeModifier.CODEC);

    public static final DeferredHolder<SoundEvent, SoundEvent> TRUMPET_DOOT = SOUND_EVENTS.register(
            "item.trumpet.doot",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MODID, "item.trumpet.doot"))
    );
    public static final Supplier<EntityType<TrumpetSkeletonEntity>> TRUMPET_SKELETON = ENTITY_TYPES.register("trumpet_skeleton",
            () -> EntityType.Builder.of(TrumpetSkeletonEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.99f) // Standard skeleton hitbox size
                    .clientTrackingRange(8)
                    .build("trumpet_skeleton")
    );

    public static final DeferredItem<Item> TRUMPET = ITEMS.register("trumpet",
            () -> new TrumpetItem(new Item.Properties().stacksTo(1).durability(64))
    );

    public static final DeferredItem<DeferredSpawnEggItem> TRUMPET_SKELETON_SPAWN_EGG = ITEMS.register("trumpet_skeleton_spawn_egg",
            () -> new DeferredSpawnEggItem(
                    TRUMPET_SKELETON,
                    0xFFFFFF,
                    0xFFFFFF,
                    new Item.Properties()
            )
    );

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("trumpet_skeleton", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.trumpetskeleton"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> TRUMPET.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(TRUMPET.get());
                output.accept(TRUMPET_SKELETON_SPAWN_EGG.get());
            }).build()
    );

    public TrumpetSkeleton(IEventBus modEventBus, ModContainer modContainer) {
        // Register the config (using your com.eggy.trumpetskeleton.ModConfig)
        modContainer.registerConfig(Type.COMMON, ModConfig.SPEC);

        // Register entity attributes event listener
        modEventBus.addListener(this::registerEntityAttributes);

        // Register all registries
        SOUND_EVENTS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
        BIOME_MODIFIER_SERIALIZERS.register(modEventBus);
    }

    private void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(TRUMPET_SKELETON.get(), TrumpetSkeletonEntity.createAttributes().build());
    }
}