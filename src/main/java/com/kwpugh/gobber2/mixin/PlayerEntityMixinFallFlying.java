package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.Gobber2;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.kwpugh.gobber2.init.ItemInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixinFallFlying extends LivingEntity 
{
   public PlayerEntityMixinFallFlying(EntityType<? extends LivingEntity> entityType, World world)
   {
      super(entityType, world);
   }

   @Inject(method = "checkFallFlying", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/entity/player/PlayerEntity;getEquippedStack(Lnet/minecraft/entity/EquipmentSlot;)Lnet/minecraft/item/ItemStack;"), cancellable = true)
   public void gobberCheckFallFlying(CallbackInfoReturnable<Boolean> info)
   {
       if (Gobber2.CONFIG.GENERAL.enableEndArmorGliding && !this.onGround && !this.isFallFlying() && !this.isTouchingWater() && !this.hasStatusEffect(StatusEffects.LEVITATION))
       {
           ItemStack itemStack = this.getEquippedStack(EquipmentSlot.CHEST);
           if (itemStack.isOf(ItemInit.GOBBER2_CHESTPLATE_END) ||  itemStack.isOf(ItemInit.GOBBER2_CHESTPLATE_DRAGON_NO_FLIGHT))
           {
               this.startFallFlying();
               info.setReturnValue(true);
           }
       }
   }

    @Shadow
    public void startFallFlying() {}
}