package com.kwpugh.gobber2.util;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.EffectsInit;
import com.kwpugh.gobber2.init.ItemInit;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PlayerSpecialAbilities
{
	static boolean waterCheck = Gobber2.CONFIG.GENERAL.medallionBreathingWaterCheck;
	static boolean enableNetherSwordPerks = Gobber2.CONFIG.GENERAL.enableNetherSwordPerks;

	//Increases the player's food level to max on tick update, based on inputs
	public static void giveHealing(PlayerEntity player, int amount)
	{
		if(player.age % 180 == 0)
		{
			if(player.getHealth() < player.getMaxHealth())
			{
				player.heal(amount);
			}		
		}
	}

	// Checks if player has negative effects and removes them, used on Dragon Armor
	public static void giveCuringEffect(World world, PlayerEntity player)
	{
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
	}
	//Set player saturation level to max on tick update
	public static void giveSaturationEffect(PlayerEntity player)
	{
		if(player.age % 180 == 0)
		{
			if(player.getHungerManager().getFoodLevel() < 20)
			player.getHungerManager().setFoodLevel(player.getHungerManager().getFoodLevel() + 1);
		}
	}
	
	//Lesser yellow hearts on tick update
	public static void giveLesserAbsorption(PlayerEntity player)
	{
		float current = player.getAbsorptionAmount();
		
		if(player.getHealth() < 20 || current >= 10)
		{
			return;
		}
		
		if(current >= 9)
		{
			if (player.age % 180 == 0)
			{
				player.setAbsorptionAmount(10);
			} 
			
			
			return;
		}

		if(current < 9)
		{
			if (player.age % 180 == 0)
			{
				player.setAbsorptionAmount(current + 0.33F);
			}
		}
	}
	
	//Greater yellow hearts on tick update
	public static void giveGreaterAbsorption(PlayerEntity player)
	{
		float current = player.getAbsorptionAmount();
		
		if(player.getHealth() < 20 || current >= 20)
		{
			return;
		}
		
		if(current >= 19)
		{
			if (player.age % 180 == 0)
			{
				player.setAbsorptionAmount(20);
			} 
			
			return;
		}
		if(current < 19)
		{
			if (player.age % 180 == 0)
			{
				player.setAbsorptionAmount(current + 1.0F);
			}
		}
	}
	
	public static void givePhoenixEffect(World world, PlayerEntity player)
	{
		BlockPos pos = player.getBlockPos();
		
		if(world.getBlockState(pos).getBlock() == Blocks.MAGMA_BLOCK           )
		{
			player.setNoGravity(true);			
		}
		
		if(player.isOnFire() ||
				player.isInLava())
		{
			StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, Gobber2.CONFIG.GENERAL.effectDuration, 0, false, false);
			StatusEffectInstance effect2 = new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, Gobber2.CONFIG.GENERAL.effectDuration, 0, false, false);
			StatusEffectInstance effect3 = new StatusEffectInstance(StatusEffects.SATURATION, Gobber2.CONFIG.GENERAL.effectDuration, 0, false, false);
			
			{
				player.addStatusEffect(effect);
				player.addStatusEffect(effect2);
				player.addStatusEffect(effect3);
			}
		}
	}
	
	public static void giveWaterBreathing(PlayerEntity player)
	{
		if(player.isSubmergedInWater() || !waterCheck)
		{
			StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.WATER_BREATHING, Gobber2.CONFIG.GENERAL.effectDuration, 0, false, false);
			
			{
				player.addStatusEffect(effect);
			}
		}
	}

	public static void giveNetherSwordPerk(DamageSource source, WitherSkeletonEntity self)
	{
		PlayerEntity player = (PlayerEntity) source.getAttacker();
		ItemStack mainHand = player.getEquippedStack(EquipmentSlot.MAINHAND);
		ItemStack offHand = player.getEquippedStack(EquipmentSlot.OFFHAND);

		if(player.hasStatusEffect(StatusEffects.WITHER));
		{
			player.removeStatusEffect(StatusEffects.WITHER);
		}

		if(mainHand.getItem() == ItemInit.GOBBER2_SWORD_NETHER & enableNetherSwordPerks)
		{
			self.dropItem(Items.WITHER_SKELETON_SKULL);
		}

		if(offHand.getItem() == ItemInit.GOBBER2_MEDALLION_EXP)
		{
			StatusEffectInstance effect = new StatusEffectInstance(EffectsInit.KNOWLEDGE, Gobber2.CONFIG.GENERAL.effectDurationKnowledge, 0, true, true);
			player.addStatusEffect(effect);
		}
	}
}