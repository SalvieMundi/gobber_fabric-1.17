package com.kwpugh.gobber2.items.tools.basetools;

import java.util.function.Predicate;

import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.UseAction;

public class ModBow extends BowItem
{
	public ModBow(Settings settings)
	{
		super(settings);
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