package com.kwpugh.gobber2.items.rings;

import java.util.List;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.util.EnableUtil;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
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

public class RingVision extends Item
{
	public RingVision(Settings settings)
	{
		super(settings);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
	{
		if(EnableUtil.isEnabled(stack))
		{
			StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.NIGHT_VISION, Gobber2.CONFIG.GENERAL.effectDuration, 0, false, false);
			LivingEntity player = (LivingEntity) entity;
			{
				player.addStatusEffect(effect);
			}
		}
	}

	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
	{
		ItemStack itemStack = user.getStackInHand(hand);

		if (!world.isClient)
		{
			EnableUtil.changeEnabled(user, hand);
			user.sendMessage((new TranslatableText("Status changed")), true);
		}

		return TypedActionResult.success(itemStack);
	}

	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_vision.tip1").formatted(Formatting.GREEN));
		tooltip.add(new TranslatableText("item.gobber2.right_click").formatted(Formatting.YELLOW));
	}
}
