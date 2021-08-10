package com.kwpugh.gobber2;

import com.kwpugh.gobber2.config.Gobber2Config;
import com.kwpugh.gobber2.init.*;
import com.kwpugh.gobber2.world.OreGen;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class Gobber2 implements ModInitializer
{
	public static final String MOD_ID = "gobber2";
	public static final ItemGroup GOBBER2_GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "gobber2_group"), () -> new ItemStack(ItemInit.GOBBER2_SWORD_NETHER));
	public static final Gobber2Config CONFIG = AutoConfig.register(Gobber2Config.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new)).getConfig();

    @Override
    public void onInitialize()
    { 
    	TagInit.registerTags();
    	BlockInit.registerBlocks();
    	BlockInit.registerBlockItems();
    	BlockInit.registerBlockEntities();
    	ItemInit.registerItems();
		OreGen.Features();
    	FuelInit.registerFuels();;
    	EnchantmentInit.registerEnchantments();
    	EffectsInit.registerEffects();
    	LootTableInit.registerLoot();
		EntityInit.init();
    }
}