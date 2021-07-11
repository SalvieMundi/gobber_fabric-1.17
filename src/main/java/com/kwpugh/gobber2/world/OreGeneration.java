package com.kwpugh.gobber2.world;

import com.kwpugh.gobber2.Gobber2;

public class OreGeneration
{
    static boolean enableCAC = Gobber2.CONFIG.ORES.cacpEnable;

    public static void init()
    {
        if(enableCAC)
        {
            CACOreGen.registerConfiguredFeature();
            CACOreGen.registerBiomeModifications();
        }
        else
        {
            ModConfiguredFeatures.Features();
        }
    }
}
