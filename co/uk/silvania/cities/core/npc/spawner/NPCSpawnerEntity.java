package co.uk.silvania.cities.core.npc.spawner;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class NPCSpawnerEntity extends TileEntity {
	
	public String npcName;
	public String playerName;
	public double offsetX;
	public double offsetY;
	public double offsetZ;
	public int spawnTime;
	public int despawnTime;
	public boolean spawnerLocked;
	public boolean spawnerInvisible;
	
	//Can be disabled per-entity
	public int heldID;
	public int helmetID;
	public int chestID;
	public int legsID;
	public int bootsID;
	public boolean wander;
	public boolean entityLocked;
	
	//Available to ops only.
	public boolean invincible;

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setDouble("offsetX", offsetX);
		nbt.setDouble("offsetY", offsetY);
		nbt.setDouble("offsetZ", offsetZ);
		nbt.setBoolean("canWander", true);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.offsetX = nbt.getDouble("offsetX");
		this.offsetY = nbt.getDouble("offsetY");
		this.offsetZ = nbt.getDouble("offsetZ");
	}
}
