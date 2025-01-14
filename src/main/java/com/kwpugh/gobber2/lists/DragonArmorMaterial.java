package com.kwpugh.gobber2.lists;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.ItemInit;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class DragonArmorMaterial implements ArmorMaterial
{
    private static int dragonDurabilityMultiplier = Gobber2.CONFIG.GENERAL.gobberDragonDurabilityMultiplier;
    private static int dragonEnchantability = Gobber2.CONFIG.GENERAL.gobberDragonArmorEnchantability;
    private static float dragonToughness = Gobber2.CONFIG.GENERAL.gobberDragonToughness;
    private static float dragonKnochback = Gobber2.CONFIG.GENERAL.gobberDragonKnockbackResistance;
    private static int dragonHead = Gobber2.CONFIG.GENERAL.dragonHeadProtection;
    private static int dragonChest = Gobber2.CONFIG.GENERAL.dragonChestProtecction;
    private static int dragonLeggings = Gobber2.CONFIG.GENERAL.dragonLeggingsProtection;
    private static int dragonBoots= Gobber2.CONFIG.GENERAL.dragonBootsProtection;

    private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
    private static final int[] PROTECTION_AMOUNT = new int[]{dragonHead, dragonLeggings, dragonChest, dragonBoots};

    @Override
    public int getDurability(EquipmentSlot slot)
    {
        return BASE_DURABILITY[slot.getEntitySlotId()] * dragonDurabilityMultiplier;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot)
    {
        return PROTECTION_AMOUNT[slot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability()
    {
        return dragonEnchantability;
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
        return "gobber2_dragon";
    }

    @Override
    public float getToughness()
    {
        return dragonToughness;
    }

    @Override
    public float getKnockbackResistance()
    {
        return dragonKnochback;
    }
}
