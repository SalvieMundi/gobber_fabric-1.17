package com.kwpugh.gobber2.init;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.entities.NemesisEntity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityInit
{
    static boolean enableNemesis = Gobber2.CONFIG.GENERAL.enableNemesis;

    public static final EntityType<NemesisEntity> NEMESIS;

    static
    {
        NEMESIS = Registry.register(Registry.ENTITY_TYPE, new Identifier(Gobber2.MOD_ID, "nemesis"), FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, NemesisEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build());
    }

    public static void init()
    {
        if(enableNemesis)
        {
            FabricDefaultAttributeRegistry.register(EntityInit.NEMESIS, NemesisEntity.createNemesisAttributes());
        }
    }
}
