package com.kwpugh.gobber2.blocks;

import java.util.List;
import java.util.Random;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.entities.NemesisEntity;
import com.kwpugh.gobber2.init.EntityInit;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameRules;

public class OreEnd  extends OreBlock
{
	public OreEnd(FabricBlockSettings settings) 
	{
		super(settings);
		this.settings.requiresTool();
	}

	Random random = new Random();
	static boolean enableNemesis = Gobber2.CONFIG.GENERAL.enableNemesis;
	static double dropChanceNemesis = Gobber2.CONFIG.GENERAL.dropChanceNemesis;

	public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack)
	{
		super.onStacksDropped(state, world, pos, stack);

		if(enableNemesis)
		{
			double r = random.nextDouble();
			if (r <= dropChanceNemesis)
			{
				if (world.getGameRules().getBoolean(GameRules.DO_TILE_DROPS) && EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0)
				{
					this.spawnNemesis(world, pos);
				}
			}
		}

	}

	private void spawnNemesis(ServerWorld world, BlockPos pos)
	{
		NemesisEntity nemesisEntity = EntityInit.NEMESIS.create(world);
		nemesisEntity.refreshPositionAndAngles((double)pos.getX() + 0.6D, pos.getY(), (double)pos.getZ() + 0.5D, 0.0F, 0.0F);
		world.spawnEntity(nemesisEntity);
		nemesisEntity.playSpawnEffects();
	}

	@Environment(EnvType.CLIENT)
	public void appendTooltip(ItemStack stack, BlockView world, List<Text> tooltip, TooltipContext options)
	{
		tooltip.add(new TranslatableText("item.gobber2.gobber2_ore_end.tip1").formatted(Formatting.GREEN));
		tooltip.add(new TranslatableText("item.gobber2.gobber2_ore_end.tip2").formatted(Formatting.YELLOW));
	}	  
}