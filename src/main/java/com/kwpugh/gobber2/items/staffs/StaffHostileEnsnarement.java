package com.kwpugh.gobber2.items.staffs;

import java.util.List;
import java.util.Optional;

import com.kwpugh.gobber2.Gobber2;

import com.kwpugh.gobber2.util.EnsnarementUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.DonkeyEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.passive.MuleEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/*
 * This item also relies on mixins
 * into VillagerEntity, AbstractDonkeyEntity, and
 * WanderingTraderEntity to change interactMob
 * methods and bypass usually GUIs
 *
 */
public class StaffHostileEnsnarement extends Item
{
    public StaffHostileEnsnarement(Settings settings)
    {
        super(settings);
    }

    // Right-click on entity, if right type, save entity info to tag and delete entity
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand)
    {
        if(!player.world.isClient)
        {
            if((stack.getOrCreateNbt().isEmpty()) &&
                    (entity instanceof AnimalEntity ||
                            entity instanceof GolemEntity ||
                            entity instanceof VillagerEntity) ||
                    entity instanceof WanderingTraderEntity ||
                    ((entity instanceof HostileEntity) && !(entity instanceof WitherEntity))    )
            {
                if(EnsnarementUtil.saveEntityToStack(entity, stack))
                {
                    player.setStackInHand(hand, stack);
                }

                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.SUCCESS;
    }

    // Right-click on block, if staff has stored entity set it's position, spawn it in, and remove tags on staff
    @SuppressWarnings("resource")
    public ActionResult useOnBlock(ItemUsageContext context)
    {
        ItemStack stack = context.getStack();
        if(!(context.getWorld() instanceof ServerWorld)) return ActionResult.SUCCESS;;
        if(!context.getWorld().isClient && stack.hasNbt() && stack.getNbt().contains("captured_entity"))
        {
            EnsnarementUtil.respawnEntity(context, stack);

            return ActionResult.SUCCESS;
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public boolean hasGlint(ItemStack stack)
    {
        return stack.hasNbt() && !stack.getOrCreateSubNbt("captured_entity").isEmpty();
    }

    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext)
    {
        tooltip.add(new TranslatableText("item.gobber2.gobber2_staff_hostile_ensnarement.tip1").formatted(Formatting.GREEN));

        if (stack.hasNbt() && !stack.getOrCreateSubNbt("captured_entity").isEmpty())
        {
            tooltip.add((new TranslatableText("item.gobber2.gobber2_staff_ensnarement.tip3", stack.getNbt().getString("name")).formatted(Formatting.GREEN)));
        }
    }
}