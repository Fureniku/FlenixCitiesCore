package co.uk.silvania.cities.core.npc.spawner;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class NPCSpawnerEntity extends TileEntity {
	
	public String npcName;
	public String playerName;
	public double offsetX = 5;
	public double offsetY = 5;
	public double offsetZ = 5;
	public int spawnTime;
	public int deSpawnTime;
	public boolean invincible;
	public boolean wander;
	public boolean locked;
	public boolean invisible;
	public int heldID;
	public int helmetID;
	public int chestID;
	public int legsID;
	public int bootsID;

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setDouble("offsetX", offsetX);
		nbt.setDouble("offsetY", offsetY);
		nbt.setDouble("offsetZ", offsetZ);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.offsetX = nbt.getDouble("offsetX");
		this.offsetY = nbt.getDouble("offsetY");
		this.offsetZ = nbt.getDouble("offsetZ");
	}
}
