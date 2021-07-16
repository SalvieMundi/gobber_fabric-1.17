package com.kwpugh.gobber2.items.tools.endtools;

import com.kwpugh.gobber2.Gobber2;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.world.World;

public class HoeEnd extends HoeItem
{
	public HoeEnd(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings)
	{
		super(material, attackDamage, attackSpeed, settings);
	}
	
	static boolean unbreakable = Gobber2.CONFIG.GENERAL.unbreakableEndTools;
	
	@Override
	public void onCraft(ItemStack stack, World world, PlayerEntity player) 
	{
		if(unbreakable)
		{
			stack.getOrCreateNbt().putBoolean("Unbreakable", true);
		}
	}
}