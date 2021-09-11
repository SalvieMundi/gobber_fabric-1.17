package com.kwpugh.gobber2.lists;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.ItemInit;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class EndArmorMaterial implements ArmorMaterial
{
	private static int endDurabilityMultiplier = Gobber2.CONFIG.GENERAL.gobberEndDurabilityMultiplier;
	private static int endEnchantability = Gobber2.CONFIG.GENERAL.gobberEndArmorEnchantability;
	private static float endToughness = Gobber2.CONFIG.GENERAL.gobberEndToughness;
	private static float endKnochback = Gobber2.CONFIG.GENERAL.gobberEndKnockbackResistance;
	private static int endHead = Gobber2.CONFIG.GENERAL.endHeadProtection;
	private static int endChest = Gobber2.CONFIG.GENERAL.endChestProtecction;
	private static int endLeggings = Gobber2.CONFIG.GENERAL.endLeggingsProtection;
	private static int endBoots= Gobber2.CONFIG.GENERAL.endBootsProtection;
	
	private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
	private static final int[] PROTECTION_AMOUNT = new int[]{endHead, endLeggings, endChest, endBoots};

	@Override
	public int getDurability(EquipmentSlot slot) 
	{
		return BASE_DURABILITY[slot.getEntitySlotId()] * endDurabilityMultiplier;
	}

	@Override
	public int getProtectionAmount(EquipmentSlot slot) 
	{
		return PROTECTION_AMOUNT[slot.getEntitySlotId()];
	}

	@Override
	public int getEnchantability()
	{
		return endEnchantability;
	}

	@Override
	public SoundEvent getEquipSound() 
	{
		return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
	}

	@Override
	public Ingredient getRepairIngredient() 
	{
		return Ingredient.ofItems(ItemInit.GOBBER2_INGOT_END);
	}

	@Override
	public String getName() 
	{
		return "gobber2_end";
	}

	@Override
	public float getToughness()
	{
		return endToughness;
	}

	@Override
	public float getKnockbackResistance()
	{
		return endKnochback;
	}
}