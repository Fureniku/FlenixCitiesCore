package com.silvaniastudios.cities.api;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

public class GeneralUtils {
	
	public static int getPlayerTemperature(EntityPlayer player) {
		int x = (int) Math.round(player.posX);
		int y = (int) Math.round(player.posY);
		int z = (int) Math.round(player.posZ);
		BlockPos pos = new BlockPos(x, y, z);
		Biome biome = player.world.getBiome(pos);
		float temp = biome.getTemperature(pos) * 10;
		int tempMult = (int) Math.round(temp) * 2;
		//Gets the biomes temperature as a float, converts to celcius (integer).
		//For example, desert is 2.0F, times 20 = 40c
		//Ocean, River, Ice Plains and Ice Mountain are overriden as their values made no sense.
		
		if (player.world.provider.isNether()) {
			return 40; //40 celcius in the nether.
		}
		if (player.isBurning()) {
			return 90; //HOT HOT HOT!
		}
		if (player.isInsideOfMaterial(Material.LAVA)) {
			return 3000;
		}
		if (biome.getBiomeName().equalsIgnoreCase("ocean")) { 
			return 8;
		} else if (biome.getBiomeName().equalsIgnoreCase("ice_flats")) {
			return -14;
		} else if (biome.getBiomeName().equalsIgnoreCase("ice_mountains")) {
			return -22;
		} else if (biome.getBiomeName().equalsIgnoreCase("river")) {
			return 9;
		}
		return tempMult;
	}
}
