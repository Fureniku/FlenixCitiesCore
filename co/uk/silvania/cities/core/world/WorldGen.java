package co.uk.silvania.cities.core.world;

import java.util.Random;

import co.uk.silvania.cities.core.CoreBlocks;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGen implements IWorldGenerator {
    
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        
		switch(world.provider.dimensionId) {
            case 1:
                generateNether(world, random, chunkX * 16, chunkZ * 16);
            case 0:
                generateSurface(world, random, chunkX * 16, chunkZ * 16);
            case -1:
                generateEnd(world, random, chunkX * 16, chunkZ * 16);
		}
		
	}
    
	private void generateEnd(World world, Random random, int chunkX, int chunkZ){
		
	}
    
	private void generateSurface(World world, Random random, int chunkX, int chunkZ) {
		for(int i = 0; i < 17; i++) {
			int xCoord = chunkX + random.nextInt(16);
			int yCoord = random.nextInt(80);
			int zCoord = chunkZ + random.nextInt(16);
			(new WorldGenMinable(CoreBlocks.copperOre.blockID, 7)).generate(world, random, xCoord, yCoord, zCoord);
		}
		
		for(int i = 0; i < 14; i++) {
			int xCoord = chunkX + random.nextInt(16);
			int yCoord = random.nextInt(50);
			int zCoord = chunkZ + random.nextInt(16);
			(new WorldGenMinable(CoreBlocks.tinOre.blockID, 5)).generate(world, random, xCoord, yCoord, zCoord);
		}
		
		for(int i = 0; i < 4; i++) {
			int xCoord = chunkX + random.nextInt(16);
			int yCoord = random.nextInt(40);
			int zCoord = chunkZ + random.nextInt(16);
			(new WorldGenMinable(CoreBlocks.titaniumOre.blockID, 8)).generate(world, random, xCoord, yCoord, zCoord);
		}
		
		for(int i = 0; i < 3; i++) {
			int xCoord = chunkX + random.nextInt(16);
			int yCoord = random.nextInt(28);
			int zCoord = chunkZ + random.nextInt(16);
			(new WorldGenMinable(CoreBlocks.rubyOre.blockID, 5)).generate(world, random, xCoord, yCoord, zCoord);
		}
		
		/*for(int i = 0; i < 3; i++) {
			int xCoord = chunkX + random.nextInt(16);
			int yCoord = random.nextInt(28);
			int zCoord = chunkZ + random.nextInt(16);
			(new WorldGenMinable(CoreBlocks.sapphireOre.blockID, 5)).generate(world, random, xCoord, yCoord, zCoord);
		}*/
		
		for(int i = 0; i < 6; i++) {
			int xCoord = chunkX + random.nextInt(16);
			int yCoord = random.nextInt(20);
			int zCoord = chunkZ + random.nextInt(16);
			(new WorldGenMinable(CoreBlocks.tecmoniumOre.blockID, 6)).generate(world, random, xCoord, yCoord, zCoord);
		}
		
		for(int i = 0; i < 7; i++) {
			int xCoord = chunkX + random.nextInt(16);
			int yCoord = random.nextInt(32);
			int zCoord = chunkZ + random.nextInt(16);
			(new WorldGenMinable(CoreBlocks.silverOre.blockID, 6)).generate(world, random, xCoord, yCoord, zCoord);
		}
		
		for(int i = 0; i < 4; i++) {
			int xCoord = chunkX + random.nextInt(16);
			int yCoord = random.nextInt(36);
			int zCoord = chunkZ + random.nextInt(16);
			(new WorldGenMinable(CoreBlocks.crystalOre.blockID, 12)).generate(world, random, xCoord, yCoord, zCoord);
		}
		for(int i = 0; i < 1; i++) {
			int xCoord = chunkX + random.nextInt(16);
			int yCoord = random.nextInt(32);
			int zCoord = chunkZ + random.nextInt(16);
			(new WorldGenLakes(CoreBlocks.blockOil.blockID)).generate(world, random, xCoord, yCoord, zCoord);
		}
	}
    
	private void generateNether(World world, Random random, int chunkX, int chunkZ){
		
	}
    
}