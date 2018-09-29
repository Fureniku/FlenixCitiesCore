package com.silvaniastudios.cities.core.npc.spawner;

import com.silvaniastudios.cities.core.FlenixCities;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NPCSpawnerBlock extends BlockContainer {

	public NPCSpawnerBlock() {
		super(Material.IRON);
		this.setCreativeTab(FlenixCities.tabCity);
		this.setHardness(2.0F);
	}
	
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    	if (!world.isRemote) {
    		EntityVillager villager = new EntityVillager(world, 41);
    		villager.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0.0f, 0.0f);
    		world.spawnEntity(villager);
    		return true;
    	} else {
    		return false;
    	}
    	
    	/*NPCSpawnerEntity tile = (NPCSpawnerEntity) world.getBlockTileEntity(x, y, z);
    	
		if (!world.isRemote) {
	    	if (tile != null) {
				EntityBanker banker = new EntityBanker(world);
				NBTTagCompound nbt = banker.getEntityData();
				//For now, just hard-coding some test values to get the NBT block -> mob working.
				nbt.setBoolean("wander", tile.wander);
				nbt.setString("playerName", tile.playerName);
				nbt.setString("npcName", tile.npcName);
				nbt.setInteger("despawnTime", tile.despawnTime);
				nbt.setInteger("heldID", tile.heldID);
				nbt.setInteger("helmetID", tile.helmetID);
				nbt.setInteger("chestID", tile.chestID);
				nbt.setInteger("legsID", tile.legsID);
				nbt.setInteger("bootsID", tile.bootsID);
				nbt.setBoolean("entityLocked", tile.entityLocked);
				nbt.setBoolean("Invulnerable", tile.invincible);
				System.out.println("Seeting invincibility to " + tile.invincible);
				banker.writeEntityToNBT(nbt);
				banker.readEntityFromNBT(nbt); //This checks if the value is correct via a println in the read method
				
				//Ignore this bit - it doesn't need to be passed to the mob.
				banker.posX = x;
				banker.posY = y;
				banker.posZ = z;
				System.out.println("The offset values are: X:" + tile.offsetX + " Y:" + tile.offsetY + " Z:" + tile.offsetZ);
				banker.setLocationAndAngles((double)x + tile.offsetX, (double)y + tile.offsetY, (double)z + tile.offsetZ, 0.0F, 0.0F);
				banker.onSpawnWithEgg(null);
				world.spawnEntityInWorld(banker);
				
	    	}
	    	return true;
		} else {
			return false;
		}*/
    }
	
	@Override
	public TileEntity createNewTileEntity(World world, int id) {
		return new NPCSpawnerEntity();
	}
}
