package com.kwpugh.gobber2.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kwpugh.gobber2.util.PlayerEquipUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

@Mixin(MobEntity.class)

public abstract class MobEntityMixinSetTarget extends Entity
{
    public MobEntityMixinSetTarget(EntityType<?> type, World world)
    {
        super(type, world);
    }

    @Inject(at = @At("HEAD"), method = "setTarget", cancellable = true)
    public void gobberSetTarget(LivingEntity target, CallbackInfo ci)
    {
        MobEntity self = (MobEntity) (Object) this;

        if(target instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) target;

            if(!this.world.isClient && !player.isCreative())
            {
                if(self instanceof HostileEntity)
                {
                    if(PlayerEquipUtil.isPlayerGotStealth(player))
                    {
                        ci.cancel();
                    }
                }

                if(self instanceof PhantomEntity)
                {
                    if(PlayerEquipUtil.isPlayerPhantomFree(player))
                    {
                        ci.cancel();
                    }
                }

                if(self instanceof PhantomEntity)
                {
                    if(PlayerEquipUtil.isPlayerWearingGobberArmor(player))
                    {
                        ci.cancel();
                    }
                }
            }
        }
    }
}