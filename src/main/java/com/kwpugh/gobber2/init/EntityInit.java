package com.kwpugh.gobber2.init;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.entities.NemesisEntity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityInit
{
    static boolean enableNemesis = Gobber2.CONFIG.GENERAL.enableNemesis;

    public static final EntityType<NemesisEntity> NEMESIS;

    static
    {
        NEMESIS = Registry.register(Registry.ENTITY_TYPE, new Identifier(Gobber2.MOD_ID, "nemesis"), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, NemesisEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build());
    }

    public static final SpawnEggItem NEMESIS_SPAWN_EGG = new SpawnEggItem(NEMESIS, 2243405, 7375001, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));

    public static void init()
    {
        if(enableNemesis)
        {
            FabricDefaultAttributeRegistry.register(EntityInit.NEMESIS, NemesisEntity.createNemesisAttributes());

            Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "nemesis_spawn_egg"), NEMESIS_SPAWN_EGG);
        }
    }



}
