package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.util.PlayerEquipUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.util.Nameable;
import net.minecraft.world.World;
import net.minecraft.world.entity.EntityLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixinFreeze implements Nameable, EntityLike, CommandOutput
{
    public EntityMixinFreeze(EntityType<?> type, World world)
    {
        super();
    }

    @Inject(at = @At("HEAD"), method = "canFreeze", cancellable = true)
    public void gobberCanFreeze(CallbackInfoReturnable cir)
    {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (PlayerEquipUtil.isPlayerWearingGobberArmor(player))
        {
            cir.setReturnValue(false);
        }
    }
}