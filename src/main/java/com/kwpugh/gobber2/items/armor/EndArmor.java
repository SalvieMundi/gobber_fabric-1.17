package com.kwpugh.gobber2.items.armor;

import java.util.List;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.ItemInit;
import com.kwpugh.gobber2.util.PlayerSpecialAbilities;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class EndArmor extends ArmorItem
{
	public EndArmor(ArmorMaterial material, EquipmentSlot slot, Settings settings)
	{
		super(material, slot, settings);
	}
	
	static boolean enableEndAllPerks = Gobber2.CONFIG.GENERAL.enableEndAllPerks;
	static boolean unbreakableEndArmor = Gobber2.CONFIG.GENERAL.unbreakableEndArmor;
	static boolean enableEndHealthPerks = Gobber2.CONFIG.GENERAL.enableEndHealthPerks;

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
	{
		if(!world.isClient && entity instanceof PlayerEntity && enableEndAllPerks)
		{
			PlayerEntity player = (PlayerEntity) entity;
	
	    	ItemStack head = player.getEquippedStack(EquipmentSlot.HEAD);
			ItemStack chest = player.getEquippedStack(EquipmentSlot.CHEST);
			ItemStack legs = player.getEquippedStack(EquipmentSlot.LEGS);
		    ItemStack feet = player.getEquippedStack(EquipmentSlot.FEET);
					
	    	if((head.getItem() == ItemInit.GOBBER2_HELMET_END && 
	    			chest.getItem() == ItemInit.GOBBER2_CHESTPLATE_END && 
	    			legs.getItem() == ItemInit.GOBBER2_LEGGINGS_END && 
	    			feet.getItem() == ItemInit.GOBBER2_BOOTS_END))
	      	{
	    		if(player.age % 180 == 0)
				{
					System.out.println("Health: " + player.getHealth());
					System.out.println("Food: " + player.getHungerManager().getFoodLevel());
					System.out.println("Sat: " + player.getHungerManager().getSaturationLevel());

					if(enableEndHealthPerks)
					{
						PlayerSpecialAbilities.giveGreaterAbsorption(player);
						PlayerSpecialAbilities.giveSaturationEffect(player);
						PlayerSpecialAbilities.giveHealing(player, 4);
					}
				}
	    		
	    		PlayerSpecialAbilities.giveWaterBreathing(player);
	    		PlayerSpecialAbilities.givePhoenixEffect(world, player);
	    		
	    		player.fallDistance = 0.0F;
	      	}
		}
	}
	
	@Override
	public void onCraft(ItemStack stack, World world, PlayerEntity player) 
	{
		if(unbreakableEndArmor)
		{
			stack.getOrCreateNbt().putBoolean("Unbreakable", true);
		}
	}
	
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		if(enableEndAllPerks)
		{
			tooltip.add(new TranslatableText("item.gobber2.gobber2_armor_end.tip1").formatted(Formatting.GREEN));
			tooltip.add(new TranslatableText("item.gobber2.gobber2_armor_end.tip2").formatted(Formatting.GREEN));
			tooltip.add(new TranslatableText("item.gobber2.gobber2_armor_end.tip3").formatted(Formatting.GREEN));
			if(enableEndHealthPerks)
			{
				tooltip.add(new TranslatableText("item.gobber2.gobber2_armor_end.tip4").formatted(Formatting.GREEN));
			}
		}
	}	
}