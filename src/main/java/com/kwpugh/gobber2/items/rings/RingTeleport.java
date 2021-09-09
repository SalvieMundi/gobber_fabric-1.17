package com.kwpugh.gobber2.items.rings;

import java.util.List;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;

public class RingTeleport extends Item
{
	public RingTeleport(Item.Settings settings)
	{
		super(settings);
	}

	//Use the teleport function or clear location
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
    	ItemStack stack = player.getStackInHand(hand);

		if(getPosition(stack) != null && !player.isSneaking())
		{
			teleport(player, world, stack);
		}

		if(getPosition(stack) != null && player.isSneaking())
		{
			setPosition(stack, world, null, player);
			player.sendMessage((new TranslatableText("item.gobber2.gobber2_ring_teleport.tip8")), true);   // loc cleared
		}

        return new TypedActionResult<>(ActionResult.SUCCESS, stack);
    }

	//Set the location in the ring
	@Override
	public ActionResult useOnBlock(ItemUsageContext context)
	{
		World world = context.getWorld();
		BlockPos pos = context.getBlockPos();
		PlayerEntity player = context.getPlayer();
		Direction direction = context.getSide();
		ItemStack stack = context.getPlayer().getMainHandStack();

	 	if(getPosition(stack) == null && !player.isSneaking())
    	{
    		player.sendMessage((new TranslatableText("item.gobber2.gobber2_ring_teleport.tip2")), true);   // not set
    	}

		if(getPosition(stack) == null && player.isSneaking())
		{
			setPosition(stack, world, pos.offset(direction), player);
			player.sendMessage((new TranslatableText("item.gobber2.gobber2_ring_teleport.tip6", getLocationString(stack)).formatted(Formatting.GREEN)), true);   // loc set

			return ActionResult.SUCCESS;
		}

		if(getPosition(stack) != null)
		{
			player.sendMessage((new TranslatableText("item.gobber2.gobber2_ring_teleport.tip7", getLocationString(stack)).formatted(Formatting.YELLOW)), true);    // loc already set

			return ActionResult.SUCCESS;
		}

		return ActionResult.SUCCESS;
	}

	//Get stored BlockPos, if it exists
	public static BlockPos getPosition(ItemStack stack)
	{
		NbtCompound tags = stack.getNbt();

		if (stack.hasNbt() && tags.contains("pos"))
		{
			NbtCompound subTags = stack.getOrCreateSubNbt("pos");
			return NbtHelper.toBlockPos(subTags);
		}
		else
		{
			return null;
		}
	}

	//Get stored dimension, if exists
	public static String getDimension(ItemStack stack)
	{
		NbtCompound tags = stack.getNbt();

		if (stack.hasNbt() && tags.contains("dim"))
		{
			return stack.getNbt().getString("dim");
		}
		else
		{
			return null;
		}
	}

	//Set position and dimension in the NBT
	public static void setPosition(ItemStack stack, World world, BlockPos pos, PlayerEntity player)
	{
		if(world.isClient) return;

		NbtCompound tags;

		if (!stack.hasNbt())
		{
			tags = new NbtCompound();
		}
		else
		{
			tags = stack.getNbt();
		}

		if (pos == null) // not pointing at a block will clear the NBT
		{
			tags.remove("pos");
			tags.remove("dim");
		}
		else
		{
			RegistryKey<World> registryKey = world.getRegistryKey();
			tags.put("pos", NbtHelper.fromBlockPos(pos));
			if(registryKey == World.OVERWORLD) tags.putString("dim", "Overworld");
			if(registryKey == World.NETHER) tags.putString("dim", "The Nether");
			if(registryKey == World.END) tags.putString("dim", "The End");
		}

		stack.setNbt(tags);
	}

	public void teleport(PlayerEntity player, World world, ItemStack stack)
	{
		if(world.isClient) return;

		BlockPos pos = getPosition(stack);
		String dim = getDimension(stack);
		RegistryKey<World> registryKey = world.getRegistryKey();
		ServerWorld serverWorld = ((ServerWorld)world).getServer().getWorld(registryKey);  //current world the player is in
		ServerWorld overWorld = ((ServerWorld)world).getServer().getWorld(World.OVERWORLD);
		ServerWorld netherWorld = ((ServerWorld)world).getServer().getWorld(World.NETHER);
		ServerWorld endWorld = ((ServerWorld)world).getServer().getWorld(World.END);
		ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
		ServerWorld destWorld = null;
		boolean unknownWorld = false;

		// Check stored DIM with current dim the player is in
		if((stack.getNbt().getString("dim").contains("Overworld")))
		{
			destWorld = overWorld;
		}
		else if((stack.getNbt().getString("dim").contains("The Nether")))
		{
			destWorld = netherWorld;
		}
		else if((stack.getNbt().getString("dim").contains("The End")))
		{
			destWorld = endWorld;
		}
		else
		{
			unknownWorld = true;
		}

		// Determine if cross-dimension teleport is required and execute
		if(unknownWorld)
		{
			serverPlayer.sendMessage((new TranslatableText("item.gobber2.gobber2_ring_teleport.tip9")), true);  //  wrong dim
		}
		else if(serverWorld == destWorld)
		{
			serverPlayer.requestTeleport(pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F);
			world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
		}
		else if(serverWorld != destWorld)
		{
			serverPlayer.teleport(destWorld, pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F, serverPlayer.getYaw(), serverPlayer.getPitch());
			world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
		}
	}

	public String getLocationString(ItemStack stack)
	{
		int storedX = stack.getOrCreateSubNbt("pos").getInt("X");
		int storedY = stack.getOrCreateSubNbt("pos").getInt("Y");
		int storedZ = stack.getOrCreateSubNbt("pos").getInt("Z");
		String storedWorld = stack.getNbt().getString("dim");
		String displayInfo = storedWorld  + "  x: " + storedX + " y: " + storedY + " z: " + storedZ;

		return displayInfo;
	}

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_teleport.tip1").formatted(Formatting.GREEN));
		tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_teleport.tip2").formatted(Formatting.YELLOW));
		tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_teleport.tip3").formatted(Formatting.YELLOW));
		tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_teleport.tip4").formatted(Formatting.YELLOW));

		if(getPosition(stack) != null)
		{
			tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_teleport.tip5", getLocationString(stack)).formatted(Formatting.RED));
		}
	}
}