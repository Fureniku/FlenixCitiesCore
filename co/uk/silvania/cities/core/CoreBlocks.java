package co.uk.silvania.cities.core;

import co.uk.silvania.cities.core.blocks.*;
import co.uk.silvania.cities.core.blocks.entity.FloatingShelvesBlock;
import co.uk.silvania.cities.core.liquid.*;
import co.uk.silvania.cities.core.npc.spawner.NPCSpawnerBlock;
import co.uk.silvania.cities.econ.VillageHandlerBlacksmith;
import co.uk.silvania.cities.econ.atm.TileEntityATMBlock;
import co.uk.silvania.cities.econ.store.AdminShopBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

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
	public static Block npcSpawnerBlock;
	public static Block adminShopBlock;
	
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
	public static Block lightingBlocks;
	public static Block drywallWhite;
	public static Block drywallRed;
	public static Block drywallBlue;
	public static Block drywallGreen;
	public static Block drywallGrey;
	public static Block lightingRotateBlocks;
	public static Block stainedGlassPane;
	public static Block styledGlassPane;
	public static Block styledGlassWhitePane;
	
	public static Block rebarBlock;
	
	public static Block blockOil;
	public static Block blockPetrol;
	public static Block blockDiesel;
	public static Block blockRedDiesel;
	
	public static Fluid fluidOil;
	public static Fluid fluidPetrol;
	public static Fluid fluidDiesel;
	public static Fluid fluidRedDiesel;
	
	public static void init() {
		initFluids();
		initOres();
		initBlocks();
		initEconBlocks();
		initSpecialBlocks();
	}
	
	public static void initFluids() {
		fluidOil = new Fluid("oil").setBlockID(config.blockOilID).setViscosity(12000).setUnlocalizedName("fluidOil");
		fluidPetrol = new Fluid("petrol").setBlockID(config.blockPetrolID).setViscosity(1000).setUnlocalizedName("fluidOil");
		fluidDiesel = new Fluid("diesel").setBlockID(config.blockDieselID).setViscosity(800).setUnlocalizedName("fluidOil");
		fluidRedDiesel = new Fluid("reddiesel").setBlockID(config.blockRedDieselID).setViscosity(800).setUnlocalizedName("fluidOil");
		
		FluidRegistry.registerFluid(fluidOil);
		FluidRegistry.registerFluid(fluidPetrol);
		FluidRegistry.registerFluid(fluidDiesel);
		FluidRegistry.registerFluid(fluidRedDiesel);
		
		blockOil = new BlockOil(config.blockOilID, fluidOil);
		blockPetrol = new BlockPetrol(config.blockPetrolID, fluidPetrol);
		blockDiesel = new BlockDiesel(config.blockDieselID, fluidDiesel);
		blockRedDiesel = new BlockRedDiesel(config.blockRedDieselID, fluidRedDiesel);
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
    	lightingBlocks = new LightingBlocks(config.lightingBlockID).setUnlocalizedName("lightingBlocks");
    	drywallWhite = new BlockDrywall(config.drywallWhiteID, FlenixCities_Core.modid + ":drywallWhite", FlenixCities_Core.modid + ":drywallWhite", true);
    	drywallRed = new BlockDrywall(config.drywallRedID, FlenixCities_Core.modid + ":drywallRed", FlenixCities_Core.modid + ":drywallRed", true);
    	drywallBlue = new BlockDrywall(config.drywallBlueID, FlenixCities_Core.modid + ":drywallBlue", FlenixCities_Core.modid + ":drywallBlue", true);
    	drywallGreen = new BlockDrywall(config.drywallGreenID, FlenixCities_Core.modid + ":drywallGreen", FlenixCities_Core.modid + ":drywallGreen", true);
    	drywallGrey = new BlockDrywall(config.drywallGreyID, FlenixCities_Core.modid + ":drywallGrey", FlenixCities_Core.modid + ":drywallGrey", true);
    	lightingRotateBlocks = new LightingRotateBlocks(config.lightingRotateBlocksID).setUnlocalizedName("lightingRotateBlocks");
    	styledGlassPane = new StainedGlassPane(config.styledGlassPaneID, FlenixCities_Core.modid + ":styledGlass0", FlenixCities_Core.modid + ":styledGlass0", false).setUnlocalizedName("styledGlassPane");
    	styledGlassWhitePane = new StainedGlassPane(config.styledGlassWhitePaneID, FlenixCities_Core.modid + ":styledGlassWhite0", FlenixCities_Core.modid + ":styledGlassWhite0", false).setUnlocalizedName("styledGlassWhitePane");
    	
    	
    	rebarBlock = new RebarBlock(config.rebarBlockID).setUnlocalizedName("rebarBlock");
	}
	
	public static void initSpecialBlocks() {
		npcSpawnerBlock = new NPCSpawnerBlock(config.npcSpawnerBlockID).setUnlocalizedName("npcSpawnerBlock");
		adminShopBlock = new AdminShopBlock(500).setUnlocalizedName("adminShopBlock");
	}

}
