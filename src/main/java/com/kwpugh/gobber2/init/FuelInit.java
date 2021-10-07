package com.kwpugh.gobber2.init;

import net.fabricmc.fabric.api.registry.FuelRegistry;

public class FuelInit 
{
	public static void registerFuels()
	{
		FuelRegistry registry = FuelRegistry.INSTANCE;

		registry.add(ItemInit.GOBBER2_FOO, 12000);
		registry.add(ItemInit.GOBBER2_FOO_NETHER, 24000);
		registry.add(ItemInit.GOBBER2_FOO_END, 32000);
	}
}