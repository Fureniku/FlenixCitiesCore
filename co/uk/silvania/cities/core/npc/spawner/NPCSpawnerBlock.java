package co.uk.silvania.cities.core.npc.spawner;

import co.uk.silvania.cities.core.FlenixCities_Core;
import co.uk.silvania.cities.core.blocks.entity.TileEntityFloatingShelves;
import co.uk.silvania.cities.core.npc.EntityBanker;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
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
    	NPCSpawnerEntity tile = (NPCSpawnerEntity) world.getBlockTileEntity(x, y, z);
    	
		if (!world.isRemote) {
	    	if (tile != null) {
				EntityBanker banker = new EntityBanker(world);
				NBTTagCompound nbt = new NBTTagCompound();
				banker.writeEntityToNBT(nbt);
				//For now, just hard-coding some test values to get the NBT block -> mob working.
				nbt.setBoolean("wander", false);// tile.wander);
				nbt.setString("playerName", "Blah" );//tile.playerName);
				nbt.setInteger("despawnTime", 12345);//tile.despawnTime);
				nbt.setInteger("heldID", 6);//tile.heldID);
				nbt.setInteger("helmetID", 7);//tile.helmetID);
				nbt.setInteger("chestID", 8);//tile.chestID);
				nbt.setInteger("legsID", 9);//tile.legsID);
				nbt.setInteger("bootsID", 10);//tile.bootsID);
				nbt.setBoolean("entityLocked", true);//tile.entityLocked);
				nbt.setBoolean("entityInvincible", true);//tile.invincible);
				banker.readEntityFromNBT(nbt);
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
		}
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
