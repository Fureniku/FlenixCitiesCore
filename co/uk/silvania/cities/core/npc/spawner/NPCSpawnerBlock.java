package co.uk.silvania.cities.core.npc.spawner;

import co.uk.silvania.cities.core.FlenixCities_Core;
import co.uk.silvania.cities.core.blocks.entity.TileEntityFloatingShelves;
import co.uk.silvania.cities.core.npc.EntityBanker;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class NPCSpawnerBlock extends BlockContainer {

	public NPCSpawnerBlock(int id) {
		super(id, Material.iron);
		this.setCreativeTab(FlenixCities_Core.tabCity);
		this.setHardness(2.0F);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
	}
	
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        return null;
    }
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new NPCSpawnerEntity();
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}
	
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float j, float k, float l) {
    	if (!world.isRemote) {
    		EntityVillager villager = new EntityVillager(world, 41);
    		villager.setLocationAndAngles(x, y, z, 0.0f, 0.0f);
    		world.spawnEntityInWorld(villager);
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
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
}
