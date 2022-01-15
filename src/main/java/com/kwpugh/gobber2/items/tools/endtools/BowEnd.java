package com.kwpugh.gobber2.items.tools.endtools;

import java.util.function.Predicate;

import com.kwpugh.gobber2.Gobber2;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class BowEnd extends BowItem 
{
	public BowEnd(Settings settings)
	{
		super(settings);
	}

	static boolean unbreakable = Gobber2.CONFIG.GENERAL.unbreakableEndTools;
	
	@Override
	public void onCraft(ItemStack stack, World world, PlayerEntity player) 
	{
		if(world.isClient) return;

		if(unbreakable)
		{
			stack.getOrCreateNbt().putBoolean("Unbreakable", true);
		}
	}
	
    public static float getPullProgress(int useTicks) 
    {
	      float f = (float)useTicks / 20.0F;
	      f = (f * f + f * 2.0F) / 3.0F;
	      
	      if (f > 1.0F) 
	      {
	         f = 1.0F;
	      }

	      return f;
	}

	public int getMaxUseTime(ItemStack stack) 
	{
	      return 72000;
	}

	public UseAction getUseAction(ItemStack stack) 
	{
	      return UseAction.BOW;
	}

	public Predicate<ItemStack> getProjectiles() 
	{
	   return BOW_PROJECTILES;
	}

	public int getRange() 
	{
	   return 15;
	}
}