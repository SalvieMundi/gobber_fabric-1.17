package com.kwpugh.gobber2.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.attribute.ClampedEntityAttribute;

@Mixin(ClampedEntityAttribute.class)
abstract class ClampedEntityAttributeMixin
{
    @Final @Shadow private double minValue;

    @Final @Shadow private double maxValue;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(String translationKey, double fallback, double min, double max, CallbackInfo info)
    {
        if(translationKey == "attribute.name.generic.armor")
        {
            System.out.println("Gobber Mod Info Message: Max value of " + translationKey + " is currently set to " + this.maxValue);
        }
    }
}