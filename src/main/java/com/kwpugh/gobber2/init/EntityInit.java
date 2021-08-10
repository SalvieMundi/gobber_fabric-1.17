package com.kwpugh.gobber2.init;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.entities.NemesisEntity;
import com.kwpugh.gobber2.entities.NemesisEntityEnd;
import com.kwpugh.gobber2.entities.NemesisEntityNether;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityInit
{
    static boolean enable = Gobber2.CONFIG.GENERAL.enableAllNemesis;

    public static final EntityType<NemesisEntity> NEMESIS;
    public static final EntityType<NemesisEntityNether> NEMESIS_NETHER;
    public static final EntityType<NemesisEntityEnd> NEMESIS_END;

    static
    {
        NEMESIS = Registry.register(Registry.ENTITY_TYPE, new Identifier(Gobber2.MOD_ID, "nemesis"), FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, NemesisEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build());
        NEMESIS_NETHER = Registry.register(Registry.ENTITY_TYPE, new Identifier(Gobber2.MOD_ID, "nemesis_nether"), FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, NemesisEntityNether::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build());
        NEMESIS_END = Registry.register(Registry.ENTITY_TYPE, new Identifier(Gobber2.MOD_ID, "nemesis_end"), FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, NemesisEntityEnd::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build());
    }

    public static void init()
    {
        if(enable)
        {
            FabricDefaultAttributeRegistry.register(EntityInit.NEMESIS, NemesisEntity.createNemesisAttributes());
            FabricDefaultAttributeRegistry.register(EntityInit.NEMESIS_NETHER, NemesisEntityNether.createNemesisAttributes());
            FabricDefaultAttributeRegistry.register(EntityInit.NEMESIS_END, NemesisEntityEnd.createNemesisAttributes());
        }
    }
}
