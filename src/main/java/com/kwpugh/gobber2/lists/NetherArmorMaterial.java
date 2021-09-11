package com.kwpugh.gobber2.lists;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.ItemInit;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class NetherArmorMaterial implements ArmorMaterial
{
	private static int netherDurabilityMultiplier = Gobber2.CONFIG.GENERAL.gobberNetherDurabilityMultiplier;
	private static int netherEnchantability = Gobber2.CONFIG.GENERAL.gobberNetherArmorEnchantability;
	private static float netherToughness = Gobber2.CONFIG.GENERAL.gobberNetherToughness;
	private static float netherKnochback = Gobber2.CONFIG.GENERAL.gobberNetherKnockbackResistance;
	private static int netherHead = Gobber2.CONFIG.GENERAL.netherHeadProtection;
	private static int netherChest = Gobber2.CONFIG.GENERAL.netherChestProtecction;
	private static int netherLeggings = Gobber2.CONFIG.GENERAL.netherLeggingsProtection;
	private static int netherBoots = Gobber2.CONFIG.GENERAL.netherBootsProtection;
	
	private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
	private static final int[] PROTECTION_AMOUNT = new int[]{netherHead, netherLeggings, netherChest, netherBoots};

	@Override
	public int getDurability(EquipmentSlot slot) 
	{
		return BASE_DURABILITY[slot.getEntitySlotId()] * netherDurabilityMultiplier;
	}

	@Override
	public int getProtectionAmount(EquipmentSlot slot) 
	{
		return PROTECTION_AMOUNT[slot.getEntitySlotId()];
	}

	@Override
	public int getEnchantability()
	{
		return netherEnchantability;
	}

	@Override
	public SoundEvent getEquipSound() 
	{
		return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
	}

	@Override
	public Ingredient getRepairIngredient() 
	{
		return Ingredient.ofItems(ItemInit.GOBBER2_INGOT_NETHER);
	}

	@Override
	public String getName() 
	{
		return "gobber2_nether";
	}

	@Override
	public float getToughness()
	{
		return netherToughness;
	}

	@Override
	public float getKnockbackResistance()
	{
		return netherKnochback;
	}
}