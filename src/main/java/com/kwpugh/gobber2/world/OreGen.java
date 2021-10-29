package com.kwpugh.gobber2.world;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.config.Gobber2Config;
import com.kwpugh.gobber2.init.BlockInit;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;

public class OreGen
{	
	public static final Gobber2Config.Ores CONFIG = Gobber2.CONFIG.ORES;

	public static ConfiguredFeature<?, ?> ORE_GOBBER_OVERWORLD = Feature.ORE
			.configure(new OreFeatureConfig(
					OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES,
					BlockInit.GOBBER2_ORE.getDefaultState(),
					CONFIG.gobberVeinSize)) // Vein size
			.range(new RangeDecoratorConfig(
					UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.aboveBottom(CONFIG.gobberMaxLevel)))) // Inclusive min and max height
			.spreadHorizontally()
			.repeat(CONFIG.gobberPerChunk); // Number of veins per chunk

	public static ConfiguredFeature<?, ?> ORE_LUCKY_BLOCK_OVERWORLD = Feature.ORE
			.configure(new OreFeatureConfig(
					OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES,
					BlockInit.GOBBER2_LUCKY_BLOCK.getDefaultState(),
					CONFIG.luckyVeinSize)) // vein size
			.range(new RangeDecoratorConfig(
					UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.aboveBottom(CONFIG.luckyMaxLevel))))
			.spreadHorizontally()
			.repeat(CONFIG.luckyPerChunk); // number of veins per chunk

	public static ConfiguredFeature<?, ?> ORE_GOBBER_NETHER = Feature.ORE
			.configure(new OreFeatureConfig(
					OreFeatureConfig.Rules.BASE_STONE_NETHER,
					BlockInit.GOBBER2_ORE_NETHER.getDefaultState(),
					CONFIG.netherGobberVeinSize)) // vein size
			.uniformRange(YOffset.aboveBottom(0), YOffset.aboveBottom(CONFIG.netherGobberMaxLevel))
			.spreadHorizontally()
			.repeat(CONFIG.netherGobberPerChunk); // number of veins per chunk

	public static ConfiguredFeature<?, ?> ORE_GOBBER_THEEND = Feature.ORE
			.configure(new OreFeatureConfig(
					new BlockMatchRuleTest(Blocks.END_STONE), // base block is endstone in the end biomes
					BlockInit.GOBBER2_ORE_END.getDefaultState(),
					CONFIG.endGobberVeinSize))
			.uniformRange(YOffset.aboveBottom(0), YOffset.aboveBottom(CONFIG.endGobberMaxLevel))
			.spreadHorizontally()
			.repeat(CONFIG.endGobberPerChunk);

	public static void Features()
	{
		if(CONFIG.luckyEnable)
		{
			RegistryKey<ConfiguredFeature<?, ?>> luckyBlockOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
					new Identifier(Gobber2.MOD_ID, "ore_lucky_block_overworld"));
			Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, luckyBlockOverworld.getValue(), ORE_LUCKY_BLOCK_OVERWORLD);
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, luckyBlockOverworld);
		}

		if(CONFIG.gobberEnable)
		{
			RegistryKey<ConfiguredFeature<?, ?>> gobberOreOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
					new Identifier(Gobber2.MOD_ID, "ore_gobber_overworld"));
			Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, gobberOreOverworld.getValue(), ORE_GOBBER_OVERWORLD);
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, gobberOreOverworld);
		}

		if(CONFIG.netherGobberEnable)
		{
			RegistryKey<ConfiguredFeature<?, ?>> gobberOreNether = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
					new Identifier(Gobber2.MOD_ID, "ore_gobber_nether"));
			Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, gobberOreNether.getValue(), ORE_GOBBER_NETHER);
			BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, gobberOreNether);
		}

		if(CONFIG.endGobberEnable)
		{
			RegistryKey<ConfiguredFeature<?, ?>> oreGobberEnd = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
					new Identifier(Gobber2.MOD_ID, "ore_gobber_theend"));
			Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreGobberEnd.getValue(), ORE_GOBBER_THEEND);
			BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES, oreGobberEnd);
		}
	}
}
