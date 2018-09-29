package com.silvaniastudios.cities.core.npc.spawner;

import com.silvaniastudios.cities.core.FlenixCities;
import com.silvaniastudios.cities.core.npc.EntityBanker;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BankerSpawnerItem extends NPCPeripheral {

	public BankerSpawnerItem() {
		super();
		this.setCreativeTab(FlenixCities.tabEcon);
	}
	
	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
		EntityBanker entity = new EntityBanker(world);
		if (!world.isRemote) {
			entity.setLocationAndAngles((double)pos.getX() + 0.5, (double)pos.getY() + 1, (double)pos.getZ() + 0.5, 0.0F, 0.0F);
			//entity.onSpawnWithEgg(null);
			world.spawnEntity(entity);
			if (!player.capabilities.isCreativeMode) {
				player.getHeldItem(hand).setCount(player.getHeldItem(hand).getCount()-1);
			}
			return EnumActionResult.PASS;
		} else {
			return EnumActionResult.FAIL;
		}
	}
	
	@Override
	public boolean canWear() {
		return false;
	}
	
	@Override
	public String npcName() {
		return "Banker";
	}
}
