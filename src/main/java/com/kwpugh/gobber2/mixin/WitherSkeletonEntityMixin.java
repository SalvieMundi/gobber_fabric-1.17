package com.kwpugh.gobber2.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kwpugh.gobber2.init.EffectsInit;
import com.kwpugh.gobber2.init.ItemInit;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

@Mixin(WitherSkeletonEntity.class)
public abstract class WitherSkeletonEntityMixin extends AbstractSkeletonEntity
{
	public WitherSkeletonEntityMixin(EntityType<? extends WitherSkeletonEntity> entityType, World world)
	{
		super(entityType, world);
	}
	
	@Inject(at = @At("TAIL"), method = "dropEquipment")
	protected void gobberDropEquipment(DamageSource source, int lootingMultiplier, boolean allowDrops, CallbackInfo ci)
	{
		if(!world.isClient && (source.getAttacker() instanceof PlayerEntity))
		{
			PlayerEntity player = (PlayerEntity) source.getAttacker();
			ItemStack mainHand = player.getEquippedStack(EquipmentSlot.MAINHAND);
			ItemStack offHand = player.getEquippedStack(EquipmentSlot.OFFHAND);
			
			if(player.hasStatusEffect(StatusEffects.WITHER));
			{
				player.removeStatusEffect(StatusEffects.WITHER);
			}
			
			if(mainHand.getItem() == ItemInit.GOBBER2_SWORD_NETHER)
			{
				this.dropItem(Items.WITHER_SKELETON_SKULL);  
			}
			
			if(offHand.getItem() == ItemInit.GOBBER2_MEDALLION_EXP)
			{
				StatusEffectInstance effect = new StatusEffectInstance(EffectsInit.KNOWLEDGE, 120, 0, true, true);
				player.addStatusEffect(effect);
			}
		}
	}
}