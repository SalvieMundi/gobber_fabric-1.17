package com.kwpugh.gobber2.client;

import com.kwpugh.gobber2.entities.NemesisEntityModel;
import com.kwpugh.gobber2.entities.NemesisEntityRenderer;
import com.kwpugh.gobber2.init.EntityInit;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class EntityRenderRegistry
{
    public static final EntityModelLayer MODEL_NEMESIS_LAYER = new EntityModelLayer(new Identifier("gobber2", "nemesis"), "nemesis");

    public static void init()
    {
        EntityRendererRegistry.INSTANCE.register(EntityInit.NEMESIS, (context) -> {
            return new NemesisEntityRenderer(context);
        });

        EntityModelLayerRegistry.registerModelLayer(MODEL_NEMESIS_LAYER, NemesisEntityModel::getTexturedModelData);
    }
}
