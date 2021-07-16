package com.kwpugh.gobber2.items.armor;

import java.util.List;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.api.ArmorRemoveHandler;
import com.kwpugh.gobber2.api.ArmorTickable;
import com.kwpugh.gobber2.util.PlayerEquipUtil;

import com.kwpugh.gobber2.util.PlayerSpecialAbilities;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class DragonArmor extends ArmorItem implements ArmorRemoveHandler, ArmorTickable
{
    public DragonArmor(ArmorMaterial material, EquipmentSlot slot, Settings settings)
    {
        super(material, slot, settings);
    }

    static boolean enableHealing = Gobber2.CONFIG.GENERAL.enableHealingPerks;
    static boolean enableProtection = Gobber2.CONFIG.GENERAL.enableProtectiveEffects;
    static boolean enableNoFall = Gobber2.CONFIG.GENERAL.enableNoFallDamage;
    static boolean unbreakable = Gobber2.CONFIG.GENERAL.unbreakableDragonArmor;
    static int healing = Gobber2.CONFIG.GENERAL.gobberDragonArmorHealingPoints;
    static boolean enableFlight = Gobber2.CONFIG.GENERAL.enableFlight;

    @Override
    // Depends on PlayerEntityMixinArmor and ArmorTickable interface
    public void tickArmor(ItemStack stack, PlayerEntity player)
    {
        World world = player.world;

        if (PlayerEquipUtil.isPlayerWearingDragonArmor(player))
        {
            if(enableHealing)
            {
                if (player.age % 180 == 0)
                {
                    PlayerSpecialAbilities.giveGreaterAbsorption(world, player, stack);
                    PlayerSpecialAbilities.giveSaturationEffect(world, player, stack);
                    PlayerSpecialAbilities.giveHealing(world, player, stack, healing);
                }
            }

            if(enableProtection)
            {
                PlayerSpecialAbilities.giveWaterBreathing(world, player);
                PlayerSpecialAbilities.givePhoenixEffect(world, player);
                PlayerSpecialAbilities.giveCuringEffect(world, player);
            }

            if(enableNoFall)
            {
                player.fallDistance = 0.0F;
            }
        }

        if (PlayerEquipUtil.isPlayerWearingDragonArmor(player) && enableFlight)
        {
            player.getAbilities().allowFlying = true;
        }
    }


    @Override
    // Depends on PlayerEntityMixinArmor and ArmorRemoveHandler interface
    public void onRemoved(PlayerEntity player)
    {
        if (this.slot == EquipmentSlot.HEAD ||
                this.slot == EquipmentSlot.CHEST ||
                this.slot == EquipmentSlot.LEGS ||
                this.slot == EquipmentSlot.FEET
            )
        {
            if (!player.isCreative() && !player.isSpectator())
            {
                player.getAbilities().allowFlying = false;
                player.getAbilities().flying = false;
            }
        }
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player)
    {
        if(unbreakable)
        {
            stack.getOrCreateNbt().putBoolean("Unbreakable", true);
        }
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
    {
        tooltip.add(new TranslatableText("item.gobber2.gobber2_armor_dragon.tip1").formatted(Formatting.GREEN));

        if(enableFlight)
        {
            tooltip.add(new TranslatableText("item.gobber2.gobber2_armor_dragon.tip2").formatted(Formatting.GREEN));
        }
    }
}