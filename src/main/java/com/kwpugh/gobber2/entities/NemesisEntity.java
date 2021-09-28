package com.kwpugh.gobber2.entities;

import com.kwpugh.gobber2.Gobber2;
import net.minecraft.entity.EntityType;

import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class NemesisEntity extends HostileEntity
{
    static double health = Gobber2.CONFIG.GENERAL.healthNemesis;
    static double damage = Gobber2.CONFIG.GENERAL.attackDamageNemesis;
    static double speed = Gobber2.CONFIG.GENERAL.movementSpeedNemesis;
    static double knockback = Gobber2.CONFIG.GENERAL.attackKnockbackNemesis;
    static double resistance = Gobber2.CONFIG.GENERAL.knockbackResistanceNemesis;

    public NemesisEntity(EntityType<? extends HostileEntity> entityType, World world)
    {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createNemesisAttributes()
    {
        return NemesisEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, health)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, resistance)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, speed)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, damage)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, knockback);
    }

    @Override
    public void initGoals()
    {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(4, new MeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.add(1, (new RevengeGoal(this, new Class[0])).setGroupRevenge(new Class[0]));
        this.targetSelector.add(2, new ActiveTargetGoal(this, PlayerEntity.class, true));
    }
}