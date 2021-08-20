package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.api.ArmorRemoveHandler;
import com.kwpugh.gobber2.api.ArmorTickable;
import com.kwpugh.gobber2.util.ItemUtils;
import com.kwpugh.gobber2.util.PlayerSpecialAbilities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

/**
 * Adapted from TechReborn - credit to the authors
 */

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixinArmor extends LivingEntity
{
    @Shadow
    public abstract Iterable<ItemStack> getArmorItems();

    protected PlayerEntityMixinArmor(EntityType<? extends LivingEntity> type, World world)
    {
        super(type, world);
    }

    private final DefaultedList<ItemStack> gobber_armorcache = DefaultedList.ofSize(4, ItemStack.EMPTY);

    @Inject(method = "tick", at = @At("HEAD"))
    public void gobberTickArmor(CallbackInfo ci)
    {
        // Special perk for Kevin, if wanted
        PlayerEntity self = (PlayerEntity) (Object) this;
        ItemStack mainHand = self.getMainHandStack();
        Item offHand = self.getOffHandStack().getItem();
        UUID kwpugh = UUID.fromString("10d71fA6-B516-452f-9B1C-F2C447D69E60");

        if(self.getUuid().equals(kwpugh) && offHand == Items.DIAMOND)
        {
            PlayerSpecialAbilities.giveHealing(self, 5);
            PlayerSpecialAbilities.giveSaturationEffect(self);
            PlayerSpecialAbilities.giveGreaterAbsorption(self);
            PlayerSpecialAbilities.giveCuringEffect(this.world, self);
        }

        // Check if armor has been removed, used with flight function
        int i = 0;
        for (ItemStack stack : getArmorItems())
        {
            ItemStack cachedStack = gobber_armorcache.get(i);

            if (!ItemUtils.isItemEqual(cachedStack, stack, false, false))
            {
                if (cachedStack.getItem() instanceof ArmorRemoveHandler)
                {
                    ((ArmorRemoveHandler) cachedStack.getItem()).onRemoved((PlayerEntity) (Object) this);
                }
                gobber_armorcache.set(i, stack.copy());
            }
            i++;

            if (!stack.isEmpty() && stack.getItem() instanceof ArmorTickable)
            {
                ((ArmorTickable) stack.getItem()).tickArmor(stack, (PlayerEntity) (Object) this);
            }
        }
    }
}
