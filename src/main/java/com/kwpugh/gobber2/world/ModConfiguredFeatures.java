package com.kwpugh.gobber2.world;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.config.Gobber2Config;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStep;

public class ModConfiguredFeatures
{	
	public static final Gobber2Config.Ores CONFIG2 = Gobber2.CONFIG.ORES;
	
	public static void Features()
	{
		if(CONFIG2.luckyEnable && !CONFIG2.cacpEnable)
		{
			Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Gobber2.MOD_ID, "ore_lucky_block_overworld"), OreGen.ORE_LUCKY_BLOCK_OVERWORLD );
		}
		
		if(CONFIG2.gobberEnable && !CONFIG2.cacpEnable)
		{
			Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Gobber2.MOD_ID, "ore_gobber_overworld"), OreGen.ORE_GOBBER_OVERWORLD );
		}
		
		if(CONFIG2.netherGobberEnable)
		{
			Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Gobber2.MOD_ID, "ore_gobber_nether"), OreGen.ORE_GOBBER_NETHER );
		}
		
		if(CONFIG2.endGobberEnable)
		{
			Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Gobber2.MOD_ID, "ore_gobber_theend"), OreGen.ORE_GOBBER_THEEND );
		}
	}	
}