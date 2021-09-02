package com.kwpugh.gobber2.items.armor;


import java.util.List;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.api.ArmorRemoveHandler;
import com.kwpugh.gobber2.api.ArmorTickable;
import com.kwpugh.gobber2.util.PlayerEquipUtil;

import com.kwpugh.gobber2.util.PlayerSpecialAbilities;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
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

public class DragonArmorNoFlight extends ArmorItem implements ArmorRemoveHandler, ArmorTickable
{
    static boolean enableDragonAllPerks = Gobber2.CONFIG.GENERAL.enableDragonAllPerks;
    static boolean enableDragonHealthPerks = Gobber2.CONFIG.GENERAL.enableDragonHealthPerks;
    static boolean enableDragonProtectiveEffects = Gobber2.CONFIG.GENERAL.enableDragonProtectiveEffects;
    static boolean enableDragonNoFallDamage = Gobber2.CONFIG.GENERAL.enableDragonNoFallDamage;
    static boolean unbreakableDragonArmor = Gobber2.CONFIG.GENERAL.unbreakableDragonArmor;
    static int gobberDragonArmorHealingPoints = Gobber2.CONFIG.GENERAL.gobberDragonArmorHealingPoints;

    public DragonArmorNoFlight(ArmorMaterial material, EquipmentSlot slot, Settings settings)
    {
        super(material, slot, settings);
    }

    // Depends on PlayerEntityMixinArmor and ArmorTickable interface
    @Override
    public void tickArmor(ItemStack stack, PlayerEntity player)
    {
        World world = player.world;

        if (PlayerEquipUtil.isPlayerWearingDragonArmor(player))
        {
            if(enableDragonAllPerks)
            {
                if(enableDragonHealthPerks)
                {
                    if (player.age % 180 == 0)
                    {
                        PlayerSpecialAbilities.giveGreaterAbsorption(player);
                        PlayerSpecialAbilities.giveSaturationEffect(player);
                        PlayerSpecialAbilities.giveHealing(player, gobberDragonArmorHealingPoints);
                    }
                }

                if(enableDragonProtectiveEffects)
                {
                    PlayerSpecialAbilities.giveWaterBreathing(player);
                    PlayerSpecialAbilities.givePhoenixEffect(world, player);
                    PlayerSpecialAbilities.giveCuringEffect(world, player);
                }

                if(enableDragonNoFallDamage)
                {
                    player.fallDistance = 0.0F;
                }
            }
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
            // TBD
        }
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player)
    {
        if(unbreakableDragonArmor)
        {
            stack.getOrCreateNbt().putBoolean("Unbreakable", true);
        }
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
    {
        if(enableDragonAllPerks)
        {
            tooltip.add(new TranslatableText("item.gobber2.gobber2_armor_dragon.tip1").formatted(Formatting.GREEN));

            if(enableDragonProtectiveEffects)
            {
                tooltip.add(new TranslatableText("item.gobber2.gobber2_armor_dragon.tip2").formatted(Formatting.GREEN));
                tooltip.add(new TranslatableText("item.gobber2.gobber2_armor_dragon.tip3").formatted(Formatting.GREEN));
            }

            if(enableDragonHealthPerks)
            {
                tooltip.add(new TranslatableText("item.gobber2.gobber2_armor_dragon.tip4").formatted(Formatting.GREEN));
            }

            if(enableDragonNoFallDamage)
            {
                tooltip.add(new TranslatableText("item.gobber2.gobber2_armor_dragon.tip5").formatted(Formatting.GREEN));
            }
        }
    }
}