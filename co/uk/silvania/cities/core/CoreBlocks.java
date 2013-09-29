package co.uk.silvania.cities.core;

import co.uk.silvania.cities.core.blocks.*;
import co.uk.silvania.cities.core.blocks.atm.TileEntityATMBlock;
import net.minecraft.block.Block;

public class CoreBlocks {
	private static CityConfig config;
	
	public static Block rubyOre;
	public static Block silverOre;
	public static Block tecmoniumOre;
	public static Block titaniumOre;
	public static Block copperOre;
	public static Block tinOre;
	public static Block crystalOre;
	public static Block sapphireOre;
	public static Block oreStorageBlock;
	
	public static Block atmBlock;
	public static Block floatingShelvesBlock;
	
	public static Block skyscraperBlocks;
	public static Block stainedGlass;
	public static Block stainedGlassLit;
	public static Block styledGlass;
	public static Block styledGlassWhite;
	public static Block woolCeilingTile;
	public static Block woolStone;
	public static Block woolWood;
	public static Block plasticBlock;
	public static Block floorBlocks;
	
	public static void init() {
		initOres();
		initBlocks();
		initEconBlocks();
	}
	
	public static void initOres() {
    	rubyOre = new RubyOre(config.rubyOreID).setUnlocalizedName("rubyOre");
    	silverOre = new SilverOre(config.silverOreID).setUnlocalizedName("silverOre");
    	tecmoniumOre = new TecmoniumOre(config.tecmoniumOreID).setUnlocalizedName("tecmoniumOre");
    	titaniumOre = new TitaniumOre(config.titaniumOreID).setUnlocalizedName("titaniumOre");
    	copperOre = new CopperOre(config.copperOreID).setUnlocalizedName("copperOre");
    	tinOre = new TinOre(config.tinOreID).setUnlocalizedName("tinOre");
    	crystalOre = new CrystalOre(config.crystalOreID).setUnlocalizedName("crystalOre");
    	sapphireOre = new SapphireOre(config.sapphireOreID).setUnlocalizedName("sapphireOre");
    	oreStorageBlock = new OreStorageBlock(config.oreStorageID).setUnlocalizedName("oreStorageBlock");
	}
	
	public static void initEconBlocks() {
		atmBlock = new TileEntityATMBlock(config.atmID).setUnlocalizedName("atmBlock");
		floatingShelvesBlock = new FloatingShelvesBlock(config.floatingShelvesID).setUnlocalizedName("floatingShelvesBlock");	
	}
	
	public static void initBlocks() {
    	skyscraperBlocks = new SkyscraperBlocks(config.skyscraperBlocksID).setUnlocalizedName("skyscraperBlocks");
    	stainedGlass = new StainedGlass(config.stainedGlassID).setUnlocalizedName("stainedGlass");
    	stainedGlassLit = new StainedGlassLit(config.stainedGlassLitID).setUnlocalizedName("stainedGlassLit");
    	styledGlass = new StyledGlass(config.styledGlassID).setUnlocalizedName("styledGlass");
    	styledGlassWhite = new StyledGlassLit(config.styledGlassLitID).setUnlocalizedName("styledGlassWhite");
    	woolCeilingTile = new WoolCeilingTile(config.woolCeilingTileID).setUnlocalizedName("woolCeilingTile");
    	woolStone = new WoolStone(config.woolStoneID).setUnlocalizedName("woolStone");
    	woolWood = new WoolWood(config.woolWoodID).setUnlocalizedName("woolWood");
    	plasticBlock = new PlasticBlocks(config.plasticBlockID).setUnlocalizedName("plasticBlock");
    	floorBlocks = new FloorBlocks(config.floorBlocksID).setUnlocalizedName("floorBlocks");
	}

}
