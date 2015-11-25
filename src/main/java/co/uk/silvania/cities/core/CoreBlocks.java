package co.uk.silvania.cities.core;

import co.uk.silvania.cities.core.blocks.*;
import co.uk.silvania.cities.core.npc.spawner.NPCSpawnerBlock;
import co.uk.silvania.cities.econ.atm.TileEntityATMBlock;
import co.uk.silvania.cities.econ.store.entity.AdminShopBlock;
import co.uk.silvania.cities.econ.store.entity.FloatingShelvesBlock;
import co.uk.silvania.cities.econ.store.entity.StockChestBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class CoreBlocks {
	private static CityConfig config;
	
	public static Block atmBlock;
	public static Block floatingShelvesBlock;
	public static Block npcSpawnerBlock;
	public static Block adminShopBlock;
	public static Block stockChest;
	
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
	public static Block lightingRotateBlocks;
	public static Block stainedGlassPane;
	public static Block styledGlassPane;
	public static Block styledGlassWhitePane;
	
	public static Block ironPanel;
	public static Block blockSilk;
	public static Block blockCloth;
	public static Block polishedStone;
	public static Block polishedBrick;
	public static Block polishedSmallBrick;
	public static Block polishedSlab;
	public static Block dulledStone;
	public static Block dulledBrick;
	public static Block dulledSmallBrick;
	public static Block dulledSlab;
	public static Block brickMulti;
	public static Block quartzDecorBlocks;
	
	public static Block rebarBlock;
	
	public static Block oakWalkway;
	public static Block spruceWalkway;
	public static Block birchWalkway;
	public static Block jungleWalkway;
	public static Block acaciaWalkway;
	public static Block darkOakWalkway;
	
	public static Block whiteWalkway;
	public static Block lightGreyWalkway;
	public static Block darkGreyWalkway;
	public static Block blackWalkway;
	
	public static Block oakLaminateWalkway;
	public static Block spruceLaminateWalkway;
	public static Block birchLaminateWalkway;
	public static Block jungleLaminateWalkway;
	public static Block acaciaLaminateWalkway;
	public static Block darkOakLaminateWalkway;
	
	public static Block oakWalkwayStairs;
	
	public static void init() {
		initBlocks();
		initEconBlocks();
		initSpecialBlocks();
	}

	
	public static void initEconBlocks() {
		atmBlock = new TileEntityATMBlock().setBlockName("atmBlock");
		floatingShelvesBlock = new FloatingShelvesBlock().setBlockName("floatingShelvesBlock");	
		stockChest = new StockChestBlock().setBlockName("stockChest");
	}
	
	public static void initBlocks() {
    	skyscraperBlocks = new SkyscraperBlocks().setBlockName("skyscraperBlocks");
    	stainedGlass = new StainedGlass().setBlockName("stainedGlass");
    	stainedGlassLit = new StainedGlassLit().setBlockName("stainedGlassLit");
    	styledGlass = new StyledGlass().setBlockName("styledGlass");
    	styledGlassWhite = new StyledGlassLit().setBlockName("styledGlassWhite");
    	woolCeilingTile = new WoolCeilingTile().setBlockName("woolCeilingTile");
    	woolStone = new WoolStone().setBlockName("woolStone");
    	woolWood = new WoolWood().setBlockName("woolWood");
    	plasticBlock = new PlasticBlocks().setBlockName("plasticBlock");
    	floorBlocks = new FloorBlocks().setBlockName("floorBlocks");
    	lightingBlocks = new LightingBlocks().setBlockName("lightingBlocks");
    	lightingRotateBlocks = new LightingRotateBlocks().setBlockName("lightingRotateBlocks");
    	styledGlassPane = new StainedGlassPane(FlenixCities_Core.modid + ":styledGlass0", FlenixCities_Core.modid + ":styledGlass0", false).setBlockName("styledGlassPane");
    	styledGlassWhitePane = new StainedGlassPane(FlenixCities_Core.modid + ":styledGlassWhite0", FlenixCities_Core.modid + ":styledGlassWhite0", false).setBlockName("styledGlassWhitePane");
    	quartzDecorBlocks = new BlockMulti(Material.rock).setBlockName("quartzDecorBlocks");
    	
    	
    	rebarBlock = new RebarBlock().setBlockName("rebarBlock");
    	
    	oakWalkway = new BlockWalkway(Material.wood, Block.soundTypeWood, "floorBlocks11", "floorBlocks11").setBlockName("oakWalkway");
    	spruceWalkway = new BlockWalkway(Material.wood, Block.soundTypeWood, "floorBlocks12", "floorBlocks12").setBlockName("spruceWalkway");
    	birchWalkway = new BlockWalkway(Material.wood, Block.soundTypeWood, "floorBlocks13", "floorBlocks13").setBlockName("birchWalkway");
    	jungleWalkway = new BlockWalkway(Material.wood, Block.soundTypeWood, "floorBlocks14", "floorBlocks14").setBlockName("jungleWalkway");
    	acaciaWalkway = new BlockWalkway(Material.wood, Block.soundTypeWood, "floorBlocks15", "floorBlocks15").setBlockName("acaciaWalkway");
    	darkOakWalkway = new BlockWalkway(Material.wood, Block.soundTypeWood, "floorBlocks16", "floorBlocks16").setBlockName("darkOakWalkway");
    	
    	whiteWalkway = new BlockWalkway(Material.rock, Block.soundTypeStone, "skyscraperBlocks0", "skyscraperBlocks0").setBlockName("whiteWalkway");
    	lightGreyWalkway = new BlockWalkway(Material.rock, Block.soundTypeStone, "skyscraperBlocks1", "skyscraperBlocks1").setBlockName("lightGreyWalkway");
    	darkGreyWalkway = new BlockWalkway(Material.rock, Block.soundTypeStone, "skyscraperBlocks2", "skyscraperBlocks2").setBlockName("darkGreyWalkway");
    	blackWalkway = new BlockWalkway(Material.rock, Block.soundTypeStone, "skyscraperBlocks3", "skyscraperBlocks3").setBlockName("blackWalkway");
    	
    	oakLaminateWalkway = new BlockWalkway(Material.wood, Block.soundTypeWood, "floorBlocks4", "oakLaminateWalkway").setBlockName("oakLaminateWalkway");
    	spruceLaminateWalkway = new BlockWalkway(Material.wood, Block.soundTypeWood, "floorBlocks5", "spruceLaminateWalkway").setBlockName("spruceLaminateWalkway");
    	birchLaminateWalkway = new BlockWalkway(Material.wood, Block.soundTypeWood, "floorBlocks6", "birchLaminateWalkway").setBlockName("birchLaminateWalkway");
    	jungleLaminateWalkway = new BlockWalkway(Material.wood, Block.soundTypeWood, "floorBlocks7", "jungleLaminateWalkway").setBlockName("jungleLaminateWalkway");
    	acaciaLaminateWalkway = new BlockWalkway(Material.wood, Block.soundTypeWood, "floorBlocks9", "acaciaLaminateWalkway").setBlockName("acaciaLaminateWalkway");
    	darkOakLaminateWalkway = new BlockWalkway(Material.wood, Block.soundTypeWood, "floorBlocks10", "darkOakLaminateWalkway").setBlockName("darkOakLaminateWalkway");
    	
    	oakWalkwayStairs = new BlockWalkwayStairs(Material.wood, Block.soundTypeWood, "floorBlocks11", "floorBlocks11").setBlockName("oakWalkwayStairs");
    	/*blockSilk = new BlockMulti(config.blockSilkID, Material.cloth).setBlockName("blockSilk");
    	blockCloth = new BlockMulti(config.blockClothID, Material.cloth).setBlockName("blockCloth");
    	polishedStone = new BlockMulti(config.polishedStoneID, Material.rock).setBlockName("polishedStone");
    	polishedBrick = new BlockMulti(config.polishedBrickID, Material.rock).setBlockName("polishedBrick");
    	polishedSmallBrick = new BlockMulti(config.polishedSmallBrickID, Material.rock).setBlockName("polishedSmallBrick");
    	polishedSlab = new BlockMulti(config.polishedSlabID, Material.rock).setBlockName("polishedSlab");
    	dulledStone = new BlockMulti(config.dulledStoneID, Material.rock).setBlockName("dulledStone");
    	dulledBrick = new BlockMulti(config.dulledBrickID, Material.rock).setBlockName("dulledBrick");
    	dulledSmallBrick = new BlockMulti(config.dulledSmallBrickID, Material.rock).setBlockName("dulledSmallBrick");
    	dulledSlab = new BlockMulti(config.dulledSlabID, Material.rock).setBlockName("dulledSlab");
    	brickMulti = new BlockMulti(config.brickMultiID, Material.rock).setBlockName("brickMulti");*/
	}
	
	public static void initSpecialBlocks() {
		npcSpawnerBlock = new NPCSpawnerBlock().setBlockName("npcSpawnerBlock");
		adminShopBlock = new AdminShopBlock().setBlockName("adminShopBlock");
	}

}
