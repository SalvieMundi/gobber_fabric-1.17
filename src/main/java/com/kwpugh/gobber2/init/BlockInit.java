package com.kwpugh.gobber2.init;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.blocks.*;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockInit
{
	public static final Block GOBBER2_LUCKY_BLOCK = new LuckyBlock(FabricBlockSettings.of(Material.STONE).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().strength(2.0F, 2.0F));
	
	public static final Block GOBBER2_ORE = new OreGobber(FabricBlockSettings.of(Material.STONE).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool().strength(2.0F, 2.0F));	
	public static final Block GOBBER2_ORE_NETHER = new OreNether(FabricBlockSettings.of(Material.STONE).breakByTool(FabricToolTags.PICKAXES, 4).requiresTool().strength(2.0F, 2.0F));
	public static final Block GOBBER2_ORE_END = new OreEnd(FabricBlockSettings.of(Material.STONE).breakByTool(FabricToolTags.PICKAXES, 5).requiresTool().strength(2.0F, 2.0F));
	
	public static final Block GOBBER2_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().strength(2.0F, 2.0F));
	public static final Block GOBBER2_BLOCK_NETHER = new Block(FabricBlockSettings.of(Material.STONE).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().strength(2.0F, 2.0F));
	public static final Block GOBBER2_BLOCK_END = new Block(FabricBlockSettings.of(Material.STONE).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().strength(2.0F, 2.0F));

	public static final Block GOBBER2_GLASS = new ModGlass(FabricBlockSettings.of(Material.STONE).luminance(15).nonOpaque().breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().strength(2.0F, 2.0F));
	public static final Block GOBBER2_GLASS_NETHER = new ModGlass(FabricBlockSettings.of(Material.STONE).luminance(15).nonOpaque().breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().strength(2.0F, 2.0F));
	public static final Block GOBBER2_GLASS_END = new ModGlass(FabricBlockSettings.of(Material.STONE).luminance(15).nonOpaque().breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().strength(2.0F, 2.0F));
	public static final Block CLEAR_GLASS = new ModGlass(FabricBlockSettings.of(Material.STONE).luminance(15).nonOpaque().breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().strength(2.0F, 2.0F));

	public static void registerBlocks()
	{
		{
			Registry.register(Registry.BLOCK, new Identifier(Gobber2.MOD_ID, "gobber2_lucky_block"), GOBBER2_LUCKY_BLOCK);
			
			Registry.register(Registry.BLOCK, new Identifier(Gobber2.MOD_ID, "gobber2_ore"), GOBBER2_ORE);
			Registry.register(Registry.BLOCK, new Identifier(Gobber2.MOD_ID, "gobber2_ore_nether"), GOBBER2_ORE_NETHER);
			Registry.register(Registry.BLOCK, new Identifier(Gobber2.MOD_ID, "gobber2_ore_end"), GOBBER2_ORE_END);
			
			Registry.register(Registry.BLOCK, new Identifier(Gobber2.MOD_ID, "gobber2_block"), GOBBER2_BLOCK);
			Registry.register(Registry.BLOCK, new Identifier(Gobber2.MOD_ID, "gobber2_block_nether"), GOBBER2_BLOCK_NETHER);
			Registry.register(Registry.BLOCK, new Identifier(Gobber2.MOD_ID, "gobber2_block_end"), GOBBER2_BLOCK_END);
			
			Registry.register(Registry.BLOCK, new Identifier(Gobber2.MOD_ID, "gobber2_glass"), GOBBER2_GLASS);
			Registry.register(Registry.BLOCK, new Identifier(Gobber2.MOD_ID, "gobber2_glass_nether"), GOBBER2_GLASS_NETHER);
			Registry.register(Registry.BLOCK, new Identifier(Gobber2.MOD_ID, "gobber2_glass_end"), GOBBER2_GLASS_END);
			Registry.register(Registry.BLOCK, new Identifier(Gobber2.MOD_ID, "clear_glass"), CLEAR_GLASS);
		}
	}
	
	private static Material FabricBlockSettings(Material stone)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public static void registerBlockItems()
	{
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_lucky_block"), new BlockItem(GOBBER2_LUCKY_BLOCK, new Item.Settings().group(Gobber2.GOBBER2_GROUP)));
			
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ore"), new BlockItem(GOBBER2_ORE, new Item.Settings().group(Gobber2.GOBBER2_GROUP)));
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ore_nether"), new BlockItem(GOBBER2_ORE_NETHER, new Item.Settings().group(Gobber2.GOBBER2_GROUP)));
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ore_end"), new BlockItem(GOBBER2_ORE_END, new Item.Settings().group(Gobber2.GOBBER2_GROUP)));
			
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_block"), new BlockItem(GOBBER2_BLOCK, new Item.Settings().group(Gobber2.GOBBER2_GROUP)));
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_block_nether"), new BlockItem(GOBBER2_BLOCK_NETHER, new Item.Settings().group(Gobber2.GOBBER2_GROUP)));
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_block_end"), new BlockItem(GOBBER2_BLOCK_END, new Item.Settings().group(Gobber2.GOBBER2_GROUP)));
			
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_glass"), new BlockItem(GOBBER2_GLASS, new Item.Settings().group(Gobber2.GOBBER2_GROUP)));
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_glass_nether"), new BlockItem(GOBBER2_GLASS_NETHER, new Item.Settings().group(Gobber2.GOBBER2_GROUP)));
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_glass_end"), new BlockItem(GOBBER2_GLASS_END, new Item.Settings().group(Gobber2.GOBBER2_GROUP)));
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "clear_glass"), new BlockItem(CLEAR_GLASS, new Item.Settings().group(Gobber2.GOBBER2_GROUP)));
		}
	}
	
	public static void registerBlockEntities()
	{
		// Register block entities here
	}
}
