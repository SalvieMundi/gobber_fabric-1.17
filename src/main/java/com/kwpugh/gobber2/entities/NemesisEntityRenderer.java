package com.kwpugh.gobber2.entities;

import com.kwpugh.gobber2.client.EntityRenderRegistry;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class NemesisEntityRenderer extends MobEntityRenderer<NemesisEntity, NemesisEntityModel>
{
    public NemesisEntityRenderer(EntityRendererFactory.Context context)
    {
        super(context, new NemesisEntityModel(context.getPart(EntityRenderRegistry.MODEL_NEMESIS_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(NemesisEntity entity)
    {
        return new Identifier("gobber2", "textures/entity/nemesis/nemesis.png");
    }
}