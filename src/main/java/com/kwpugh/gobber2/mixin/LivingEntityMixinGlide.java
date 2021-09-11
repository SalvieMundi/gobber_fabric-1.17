package com.kwpugh.gobber2.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.kwpugh.gobber2.init.ItemInit;

import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixinGlide extends Entity 
{
  @Shadow
  protected int roll;

  public LivingEntityMixinGlide(EntityType<?> type, World world)
  {
    super(type, world);
  }

  @ModifyArg(method = "tickFallFlying()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setFlag(IZ)V"), index = 1)

  private boolean gobberInitAiMixin(boolean value)
  {
    boolean bl = this.getFlag(7);
    LivingEntity livingEntity = (LivingEntity) (Object) this;
    ItemStack itemStack = livingEntity.getEquippedStack(EquipmentSlot.CHEST);
    if (bl && !this.onGround && !this.hasVehicle() &&
            ( (itemStack.getItem() == ItemInit.GOBBER2_CHESTPLATE_END) ||
                    (itemStack.getItem() == ItemInit.GOBBER2_CHESTPLATE_DRAGON_NO_FLIGHT)) )
    {
      return true;
    }
    return value;
  }
}