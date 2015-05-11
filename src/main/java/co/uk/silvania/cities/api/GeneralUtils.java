package co.uk.silvania.cities.api;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class GeneralUtils {
	
	public static int getPlayerTemperature(EntityPlayer player) {
		int x = (int) Math.round(player.posX);
		int y = (int) Math.round(player.posY);
		int z = (int) Math.round(player.posZ);
		BiomeGenBase biome = player.worldObj.getBiomeGenForCoords(x, z);
		float temp = biome.getFloatTemperature(x, y, z) * 10;
		int tempMult = (int) Math.round(temp) * 2;
		//Gets the biomes temperature as a float, converts to celcius (integer).
		//For example, desert is 2.0F, times 20 = 40c
		//Ocean, River, Ice Plains and Ice Mountain are overriden as their values made no sense.
		
		if (player.worldObj.provider.isHellWorld) {
			return 40; //40 celcius in the nether.
		}
		if (player.isBurning()) {
			return 90; //HOT HOT HOT!
		}
		if (player.isInsideOfMaterial(Material.lava)) {
			return 3000;
		}
		if (biome == BiomeGenBase.ocean) {
			return 8;
		} else if (biome == BiomeGenBase.icePlains) {
			return -14;
		} else if (biome == BiomeGenBase.iceMountains) {
			return -22;
		} else if (biome == BiomeGenBase.river) {
			return 9;
		}
		return tempMult;
	}
}
