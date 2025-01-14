package com.kwpugh.gobber2.util;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.ItemInit;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public final class PlayerEquipUtil
{
    static boolean phantomProtection = Gobber2.CONFIG.GENERAL.enablePhantomProtection;
    
    public static boolean isPlayerWearingGobberArmor(PlayerEntity player)
    { 
    	if(phantomProtection)
    	{
        	ItemStack head = player.getEquippedStack(EquipmentSlot.HEAD);
    		ItemStack chest = player.getEquippedStack(EquipmentSlot.CHEST);
    		ItemStack legs = player.getEquippedStack(EquipmentSlot.LEGS);
    	    ItemStack feet = player.getEquippedStack(EquipmentSlot.FEET);
    			
    	    //Full Set
        	if(		(head.getItem() == ItemInit.GOBBER2_HELMET && 
        			chest.getItem() == ItemInit.GOBBER2_CHESTPLATE && 
        			legs.getItem() == ItemInit.GOBBER2_LEGGINGS && 
        			feet.getItem() == ItemInit.GOBBER2_BOOTS)  ||
        			
        			(head.getItem() == ItemInit.GOBBER2_HELMET_NETHER && 
        			chest.getItem() == ItemInit.GOBBER2_CHESTPLATE_NETHER && 
        			legs.getItem() == ItemInit.GOBBER2_LEGGINGS_NETHER && 
        			feet.getItem() == ItemInit.GOBBER2_BOOTS_NETHER)    ||
        			
        			(head.getItem() == ItemInit.GOBBER2_HELMET_END && 
        			chest.getItem() == ItemInit.GOBBER2_CHESTPLATE_END && 
        			legs.getItem() == ItemInit.GOBBER2_LEGGINGS_END && 
        			feet.getItem() == ItemInit.GOBBER2_BOOTS_END)    ||

			        (head.getItem() == ItemInit.GOBBER2_HELMET_DRAGON &&
					chest.getItem() == ItemInit.GOBBER2_CHESTPLATE_DRAGON &&
					legs.getItem() == ItemInit.GOBBER2_LEGGINGS_DRAGON &&
					feet.getItem() == ItemInit.GOBBER2_BOOTS_DRAGON)     ||

					(head.getItem() == ItemInit.GOBBER2_HELMET_DRAGON_NO_FLIGHT &&
					chest.getItem() == ItemInit.GOBBER2_CHESTPLATE_DRAGON_NO_FLIGHT &&
					legs.getItem() == ItemInit.GOBBER2_LEGGINGS_DRAGON_NO_FLIGHT &&
					feet.getItem() == ItemInit.GOBBER2_BOOTS_DRAGON_NO_FLIGHT) )
    	      	{
    	      		return true;  		
    	      	}
    	}
    	
    	return false;
    }

	// Wearing full Dragon Armor
	public static boolean isPlayerWearingDragonArmor(PlayerEntity player)
	{
		ItemStack head = player.getEquippedStack(EquipmentSlot.HEAD);
		ItemStack chest = player.getEquippedStack(EquipmentSlot.CHEST);
		ItemStack legs = player.getEquippedStack(EquipmentSlot.LEGS);
		ItemStack feet = player.getEquippedStack(EquipmentSlot.FEET);

		//Full Set
		if(		(head.getItem() == ItemInit.GOBBER2_HELMET_DRAGON &&
				chest.getItem() == ItemInit.GOBBER2_CHESTPLATE_DRAGON &&
				legs.getItem() == ItemInit.GOBBER2_LEGGINGS_DRAGON &&
				feet.getItem() == ItemInit.GOBBER2_BOOTS_DRAGON)  ||

				(head.getItem() == ItemInit.GOBBER2_HELMET_DRAGON_NO_FLIGHT &&
				chest.getItem() == ItemInit.GOBBER2_CHESTPLATE_DRAGON_NO_FLIGHT &&
				legs.getItem() == ItemInit.GOBBER2_LEGGINGS_DRAGON_NO_FLIGHT &&
				feet.getItem() == ItemInit.GOBBER2_BOOTS_DRAGON_NO_FLIGHT)  )
		{
			return true;
		}

		return false;
	}

	//Holding the Ring of Stealth
    public static boolean isPlayerGotStealth(PlayerEntity player)
    {
	 	ItemStack offHand = player.getOffHandStack();
	 	
	 	if(offHand.getItem() == ItemInit.GOBBER2_RING_STEALTH)
	 	{
	   		return true;
	   	}
	   		
	     return false;
    }
    
    // Ring of Stealth in inventory
    public static boolean isPlayerPhantomFree(PlayerEntity player)
    { 	    
		PlayerInventory inv = player.getInventory();
		int size = inv.size();
		
		//Is the ring in the player inventory?
		for (int slot = 0; slot < size; slot++)
		{
			ItemStack stack = inv.getStack(slot);
			if (stack.getItem() == ItemInit.GOBBER2_RING_STEALTH)
			{
				return true;
			}
		}				
	     
        return false;
    }

	// Hero Medallion in inventory
	public static boolean isVillageHero(PlayerEntity player)
	{
		PlayerInventory inv = player.getInventory();
		int size = inv.size();

		//Is the ring in the player inventory?
		for (int slot = 0; slot < size; slot++)
		{
			ItemStack stack = inv.getStack(slot);
			if (stack.getItem() == ItemInit.GOBBER2_MEDALLION_HERO)
			{
				return true;
			}
		}

		return false;
	}

    // Ring of Phoenix in inventory - Old code, but save
    public static boolean isPlayerFireImmune(PlayerEntity player)
    { 	    
		PlayerInventory inv = player.getInventory();
		int size = inv.size();
		
		//Is the ring in the player inventory?
		for (int slot = 0; slot < size; slot++)
		{
			ItemStack stack = inv.getStack(slot);
			if (stack.getItem() == ItemInit.GOBBER2_RING_PHOENIX)
			{	
				return true;
			}
		}				
	     
        return false;
    }
} 