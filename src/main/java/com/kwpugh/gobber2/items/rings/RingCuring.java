package com.kwpugh.gobber2.items.rings;

import java.util.List;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class RingCuring extends Item
{
	public RingCuring(Settings settings)
	{
		super(settings);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
	{
		if (!world.isClient)
		{
			if(entity instanceof PlayerEntity)
			{	
				//TBD
			}
		}
	}

	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getStackInHand(hand);

		if (!world.isClient)
		{
			if(player.hasStatusEffect(StatusEffects.BLINDNESS) && player.isSneaking());
			{
				player.removeStatusEffect(StatusEffects.BLINDNESS);
			}
			
			if(player.hasStatusEffect(StatusEffects.HUNGER) && player.isSneaking());
			{
				player.removeStatusEffect(StatusEffects.HUNGER);
			}
			
			if(player.hasStatusEffect(StatusEffects.MINING_FATIGUE) && player.isSneaking());
			{
				player.removeStatusEffect(StatusEffects.MINING_FATIGUE);
			}
			
			if(player.hasStatusEffect(StatusEffects.NAUSEA) && player.isSneaking());
			{
				player.removeStatusEffect(StatusEffects.NAUSEA);
			}
			
			if(player.hasStatusEffect(StatusEffects.POISON) && player.isSneaking());
			{
				player.removeStatusEffect(StatusEffects.POISON);
			}
			
			if(player.hasStatusEffect(StatusEffects.SLOWNESS) && player.isSneaking());
			{
				player.removeStatusEffect(StatusEffects.SLOWNESS);
			}
			
			if(player.hasStatusEffect(StatusEffects.UNLUCK) && player.isSneaking());
			{
				player.removeStatusEffect(StatusEffects.UNLUCK);
			}
			
			if(player.hasStatusEffect(StatusEffects.WEAKNESS) && player.isSneaking());
			{
				player.removeStatusEffect(StatusEffects.WEAKNESS);
			}
			
			if(player.hasStatusEffect(StatusEffects.WITHER) && player.isSneaking());
			{
				player.removeStatusEffect(StatusEffects.WITHER);
			}
		}

		return TypedActionResult.success(stack);
	}

	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_curing.tip1").formatted(Formatting.GREEN));
		tooltip.add(new TranslatableText("item.gobber2.right_click").formatted(Formatting.YELLOW));
	}
}