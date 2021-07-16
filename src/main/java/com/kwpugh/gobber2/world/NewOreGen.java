package com.kwpugh.gobber2.world;

import com.google.common.collect.ImmutableList;
import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.config.Gobber2Config;
import com.kwpugh.gobber2.init.BlockInit;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;

/*
************
OreGen approach that works with CavesAndCliffsPreview.zip, but not with 1.17 Part I
************
 */

public class NewOreGen
{
    public static final Gobber2Config.Ores CONFIG2 = Gobber2.CONFIG.ORES;

    public static final ImmutableList<OreFeatureConfig.Target> GOBBER_ORE_TARGETS;
    public static final ImmutableList<OreFeatureConfig.Target> LUCKY_BLOCK_TARGETS;

    public static final ConfiguredFeature<?, ?> GOBBER_ORE;
    public static final ConfiguredFeature<?, ?> LUCKY_BLOCK;

    static
    {
        GOBBER_ORE_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES, BlockInit.GOBBER2_ORE.getDefaultState()), OreFeatureConfig.createTarget(OreFeatureConfig.Rules.DEEPSLATE_ORE_REPLACEABLES, BlockInit.GOBBER2_ORE.getDefaultState()));
        LUCKY_BLOCK_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES, BlockInit.GOBBER2_LUCKY_BLOCK.getDefaultState()), OreFeatureConfig.createTarget(OreFeatureConfig.Rules.DEEPSLATE_ORE_REPLACEABLES, BlockInit.GOBBER2_LUCKY_BLOCK.getDefaultState()));

        GOBBER_ORE = Feature.ORE.configure(new OreFeatureConfig(GOBBER_ORE_TARGETS, 4)).uniformRange(YOffset.getBottom(), YOffset.fixed(-50)).spreadHorizontally().repeat(2);
        LUCKY_BLOCK = Feature.ORE.configure(new OreFeatureConfig(LUCKY_BLOCK_TARGETS, 4)).uniformRange(YOffset.getBottom(), YOffset.fixed(128)).spreadHorizontally().repeat(2);
    }

    public static void registerConfiguredFeature()
    {
        if(CONFIG2.cacpEnable)
        {
            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Gobber2.MOD_ID, "gobber2_ore"), GOBBER_ORE);
            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Gobber2.MOD_ID, "gobber2_lucky_block"), LUCKY_BLOCK);
        }
    }

    public static void registerBiomeModifications()
    {
        if(CONFIG2.cacpEnable)
        {
            BuiltinRegistries.CONFIGURED_FEATURE.getKey(GOBBER_ORE)
                    .ifPresent(key -> BiomeModifications.addFeature(ctx -> true, GenerationStep.Feature.UNDERGROUND_ORES, key));

            BuiltinRegistries.CONFIGURED_FEATURE.getKey(LUCKY_BLOCK)
                    .ifPresent(key -> BiomeModifications.addFeature(ctx -> true, GenerationStep.Feature.UNDERGROUND_ORES, key));
        }
    }
}