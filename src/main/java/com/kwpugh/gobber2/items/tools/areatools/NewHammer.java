package com.kwpugh.gobber2.items.tools.areatools;

import com.kwpugh.pugh_tools.Tools.Hammer;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class NewHammer extends Hammer
{
    public NewHammer(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings)
    {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
    {
        String range = "3x3";

        tooltip.add(new TranslatableText("item.gobber2.gobber2_hammer.tip1").formatted(Formatting.GREEN));
        tooltip.add(new TranslatableText("item.gobber2.gobber2_hammer.tip2", range).formatted(Formatting.RED));
    }
}
