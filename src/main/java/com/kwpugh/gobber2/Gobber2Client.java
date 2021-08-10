package com.kwpugh.gobber2;

import com.kwpugh.gobber2.client.BlockRenderLayMap;
import com.kwpugh.gobber2.client.EntityRenderRegistry;
import com.kwpugh.gobber2.events.ToolTips;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class Gobber2Client implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
		BlockRenderLayMap.init();
    	ToolTips.init();
		EntityRenderRegistry.init();
    }
}