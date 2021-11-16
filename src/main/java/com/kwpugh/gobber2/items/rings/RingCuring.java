package com.kwpugh.gobber2.items.rings;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.EnchantmentInit;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
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

import java.util.List;

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
			if(player.hasStatusEffect(StatusEffects.BLINDNESS))
			{
				player.removeStatusEffect(StatusEffects.BLINDNESS);
			}
			
			if(player.hasStatusEffect(StatusEffects.HUNGER))
			{
				player.removeStatusEffect(StatusEffects.HUNGER);
			}
			
			if(player.hasStatusEffect(StatusEffects.MINING_FATIGUE))
			{
				player.removeStatusEffect(StatusEffects.MINING_FATIGUE);
			}
			
			if(player.hasStatusEffect(StatusEffects.NAUSEA))
			{
				player.removeStatusEffect(StatusEffects.NAUSEA);
			}
			
			if(player.hasStatusEffect(StatusEffects.POISON))
			{
				player.removeStatusEffect(StatusEffects.POISON);
			}
			
			if(player.hasStatusEffect(StatusEffects.SLOWNESS))
			{
				player.removeStatusEffect(StatusEffects.SLOWNESS);
			}
			
			if(player.hasStatusEffect(StatusEffects.UNLUCK))
			{
				player.removeStatusEffect(StatusEffects.UNLUCK);
			}
			
			if(player.hasStatusEffect(StatusEffects.WEAKNESS))
			{
				player.removeStatusEffect(StatusEffects.WEAKNESS);
			}
			
			if(player.hasStatusEffect(StatusEffects.WITHER))
			{
				player.removeStatusEffect(StatusEffects.WITHER);
			}
		}

		if( !(EnchantmentHelper.getLevel(EnchantmentInit.QUICKUSE, player.getEquippedStack(EquipmentSlot.MAINHAND)) > 0) )
		{
			player.getItemCooldownManager().set(this, Gobber2.CONFIG.GENERAL.ringCuringCooldown);
		}

		return TypedActionResult.success(stack);
	}

	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_curing.tip1").formatted(Formatting.GREEN));
		tooltip.add(new TranslatableText("item.gobber2.right_click_activate").formatted(Formatting.YELLOW));
	}
}