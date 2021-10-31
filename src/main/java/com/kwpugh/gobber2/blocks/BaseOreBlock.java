package com.kwpugh.gobber2.blocks;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.entities.NemesisEntity;
import com.kwpugh.gobber2.init.EntityInit;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.GameRules;

import java.util.Random;

public class BaseOreBlock extends OreBlock
{
    public BaseOreBlock(FabricBlockSettings settings)
    {
        super(settings);
        this.settings.requiresTool();
    }

    Random random = new Random();
    boolean enableNemesis = Gobber2.CONFIG.GENERAL.enableNemesis;
    double dropChanceNemesis = Gobber2.CONFIG.GENERAL.dropChanceNemesis;
    UniformIntProvider experienceDropped = UniformIntProvider.create(3, 7);

    @Override
    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack)
    {
        super.onStacksDropped(state, world, pos, stack);

        if(EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0)
        {
            int i = this.experienceDropped.get(random);
            if (i > 0)
            {
                this.dropExperience(world, pos, i);
            }
        }

        if(enableNemesis)
        {
            double r = random.nextDouble();
            if (r <= dropChanceNemesis)
            {
                if (world.getGameRules().getBoolean(GameRules.DO_TILE_DROPS) && EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0)
                {
                    this.spawnNemesis(world, pos);
                }
            }
        }
    }

    private void spawnNemesis(ServerWorld world, BlockPos pos)
    {
        NemesisEntity nemesisEntity = EntityInit.NEMESIS.create(world);
        nemesisEntity.refreshPositionAndAngles((double)pos.getX() + 0.6D, pos.getY(), (double)pos.getZ() + 0.5D, 0.0F, 0.0F);
        world.spawnEntity(nemesisEntity);
        nemesisEntity.playSpawnEffects();
    }
}
