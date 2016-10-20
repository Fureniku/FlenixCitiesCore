package com.silvaniastudios.cities.core;

import com.silvaniastudios.cities.core.blocks.*;
import com.silvaniastudios.cities.core.npc.spawner.NPCSpawnerBlock;
import com.silvaniastudios.cities.econ.atm.TileEntityATMBlock;
import com.silvaniastudios.cities.econ.store.entity.AdminShopBlock;
import com.silvaniastudios.cities.econ.store.entity.FloatingShelvesBlock;
import com.silvaniastudios.cities.econ.store.entity.StockChestBlock;

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
	public static Block skyscraperBlocks2;
	public static Block skyscraperBlocks3;
	public static Block skyscraperBlocks4;
	public static Block skyscraperBlocks5;
	public static Block skyscraperBlocks6;
	public static Block skyscraperBlocks7;
		
	public static Block stainedGlass;
	public static Block stainedGlassLit;
	public static Block styledGlass;
	public static Block styledGlassWhite;
	public static Block woolCeilingTile;
	public static Block woolStone;
	public static Block woolWood;
	public static Block plasticBlock;
	public static Block floorBlocks;
	public static Block floorBlocks2;
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
	
	public static Block lightGreenWalkway;
	public static Block cyanWalkway;
	public static Block purpleWalkway;
	public static Block pinkWalkway;
	
	public static Block blueWalkway;
	public static Block brownWalkway;
	public static Block greenWalkway;
	public static Block redWalkway;
	
	public static Block orangeWalkway;
	public static Block magentaWalkway;
	public static Block lightBlueWalkway;
	public static Block yellowWalkway;
	
	public static Block pastelDarkBlueWalkway;
	public static Block pastelBrownWalkway;
	public static Block pastelGreenWalkway;
	public static Block pastelPinkWalkway;
	
	public static Block pastelOrangeWalkway;
	public static Block pastelMagentaWalkway;
	public static Block pastelRedWalkway;
	public static Block pastelYellowWalkway;
	
	public static Block pastelLightBlueWalkway;
	public static Block pastelLightGreenWalkway;
	public static Block pastelCyanWalkway;
	public static Block pastelPurpleWalkway;
	
	public static Block oakLaminateWalkway;
	public static Block spruceLaminateWalkway;
	public static Block birchLaminateWalkway;
	public static Block jungleLaminateWalkway;
	public static Block acaciaLaminateWalkway;
	public static Block darkOakLaminateWalkway;
	
	public static Block oakWalkwayStairs;
	public static Block spruceWalkwayStairs;
	public static Block birchWalkwayStairs;
	public static Block jungleWalkwayStairs;
	public static Block acaciaWalkwayStairs;
	public static Block darkOakWalkwayStairs;
	
	public static Block whiteWalkwayStairs;
	public static Block lightGreyWalkwayStairs;
	public static Block darkGreyWalkwayStairs;
	public static Block blackWalkwayStairs;
	
	public static Block lightGreenWalkwayStairs;
	public static Block cyanWalkwayStairs;
	public static Block purpleWalkwayStairs;
	public static Block pinkWalkwayStairs;
	
	public static Block blueWalkwayStairs;
	public static Block brownWalkwayStairs;
	public static Block greenWalkwayStairs;
	public static Block redWalkwayStairs;
	
	public static Block orangeWalkwayStairs;
	public static Block magentaWalkwayStairs;
	public static Block lightBlueWalkwayStairs;
	public static Block yellowWalkwayStairs;
	
	public static Block pastelDarkBlueWalkwayStairs;
	public static Block pastelBrownWalkwayStairs;
	public static Block pastelGreenWalkwayStairs;
	public static Block pastelPinkWalkwayStairs;
	
	public static Block pastelOrangeWalkwayStairs;
	public static Block pastelMagentaWalkwayStairs;
	public static Block pastelRedWalkwayStairs;
	public static Block pastelYellowWalkwayStairs;
	
	public static Block pastelLightBlueWalkwayStairs;
	public static Block pastelLightGreenWalkwayStairs;
	public static Block pastelCyanWalkwayStairs;
	public static Block pastelPurpleWalkwayStairs;
	
	public static Block oakLaminateWalkwayStairs;
	public static Block spruceLaminateWalkwayStairs;
	public static Block birchLaminateWalkwayStairs;
	public static Block jungleLaminateWalkwayStairs;
	public static Block acaciaLaminateWalkwayStairs;
	public static Block darkOakLaminateWalkwayStairs;
	
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
		skyscraperBlocks2 = new SkyscraperBlocks().setBlockName("skyscraperBlocks2");
		skyscraperBlocks3 = new SkyscraperBlocks().setBlockName("skyscraperBlocks3");
		skyscraperBlocks4 = new SkyscraperBlocks().setBlockName("skyscraperBlocks4");
		skyscraperBlocks5 = new SkyscraperBlocks().setBlockName("skyscraperBlocks5");
		skyscraperBlocks6 = new SkyscraperBlocks().setBlockName("skyscraperBlocks6");
		skyscraperBlocks7 = new SkyscraperBlocks().setBlockName("skyscraperBlocks7");
		
    	stainedGlass = new StainedGlass().setBlockName("stainedGlass");
    	stainedGlassLit = new StainedGlassLit().setBlockName("stainedGlassLit");
    	styledGlass = new StyledGlass().setBlockName("styledGlass");
    	styledGlassWhite = new StyledGlassLit().setBlockName("styledGlassWhite");
    	woolCeilingTile = new WoolCeilingTile().setBlockName("woolCeilingTile");
    	woolStone = new WoolStone().setBlockName("woolStone");
    	woolWood = new WoolWood().setBlockName("woolWood");
    	plasticBlock = new PlasticBlocks().setBlockName("plasticBlock");
    	floorBlocks = new FloorBlocks().setBlockName("floorBlocks");
    	floorBlocks2 = new FloorBlocks().setBlockName("floorBlocks2");
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
    	
    	lightGreenWalkway = new BlockWalkway(Material.rock, Block.soundTypeStone, "skyscraperBlocks20", "skyscraperBlocks20").setBlockName("lightGreenWalkway");
    	cyanWalkway = new BlockWalkway(Material.rock, Block.soundTypeStone, "skyscraperBlocks21", "skyscraperBlocks21").setBlockName("cyanWalkway");
    	purpleWalkway = new BlockWalkway(Material.rock, Block.soundTypeStone, "skyscraperBlocks22", "skyscraperBlocks22").setBlockName("purpleWalkway");
    	pinkWalkway = new BlockWalkway(Material.rock, Block.soundTypeStone, "skyscraperBlocks23", "skyscraperBlocks23").setBlockName("pinkWalkway");
    	
    	blueWalkway = new BlockWalkway(Material.rock, Block.soundTypeStone, "skyscraperBlocks30", "skyscraperBlocks30").setBlockName("blueWalkway");
    	brownWalkway = new BlockWalkway(Material.rock, Block.soundTypeStone, "skyscraperBlocks31", "skyscraperBlocks31").setBlockName("brownWalkway");
    	greenWalkway = new BlockWalkway(Material.rock, Block.soundTypeStone, "skyscraperBlocks32", "skyscraperBlocks32").setBlockName("greenWalkway");
    	redWalkway = new BlockWalkway(Material.rock, Block.soundTypeStone, "skyscraperBlocks33", "skyscraperBlocks33").setBlockName("redWalkway");
    	
    	orangeWalkway = new BlockWalkway(Material.rock, Block.soundTypeStone, "skyscraperBlocks40", "skyscraperBlocks40").setBlockName("orangeWalkway");
    	magentaWalkway = new BlockWalkway(Material.rock, Block.soundTypeStone, "skyscraperBlocks41", "skyscraperBlocks41").setBlockName("magentaWalkway");
    	lightBlueWalkway = new BlockWalkway(Material.rock, Block.soundTypeStone, "skyscraperBlocks42", "skyscraperBlocks42").setBlockName("lightBlueWalkway");
    	yellowWalkway = new BlockWalkway(Material.rock, Block.soundTypeStone, "skyscraperBlocks43", "skyscraperBlocks43").setBlockName("yellowWalkway");
    	
    	pastelDarkBlueWalkway = new BlockWalkway(Material.rock, Block.soundTypeStone, "skyscraperBlocks50", "skyscraperBlocks50").setBlockName("pastelDarkBlueWalkway");
    	pastelBrownWalkway = new BlockWalkway(Material.rock, Block.soundTypeStone, "skyscraperBlocks51", "skyscraperBlocks51").setBlockName("pastelBrownWalkway");
    	pastelGreenWalkway = new BlockWalkway(Material.rock, Block.soundTypeStone, "skyscraperBlocks52", "skyscraperBlocks52").setBlockName("pastelGreenWalkway");
    	pastelPinkWalkway = new BlockWalkway(Material.rock, Block.soundTypeStone, "skyscraperBlocks53", "skyscraperBlocks53").setBlockName("pastelPinkWalkway");
    	
    	pastelOrangeWalkway = new BlockWalkway(Material.rock, Block.soundTypeStone, "skyscraperBlocks60", "skyscraperBlocks60").setBlockName("pastelOrangeWalkway");
    	pastelMagentaWalkway = new BlockWalkway(Material.rock, Block.soundTypeStone, "skyscraperBlocks61", "skyscraperBlocks61").setBlockName("pastelMagentaWalkway");
    	pastelRedWalkway = new BlockWalkway(Material.rock, Block.soundTypeStone, "skyscraperBlocks62", "skyscraperBlocks62").setBlockName("pastelRedWalkway");
    	pastelYellowWalkway = new BlockWalkway(Material.rock, Block.soundTypeStone, "skyscraperBlocks63", "skyscraperBlocks63").setBlockName("pastelYellowWalkway");
    	
    	pastelLightBlueWalkway = new BlockWalkway(Material.rock, Block.soundTypeStone, "skyscraperBlocks70", "skyscraperBlocks70").setBlockName("pastelLightBlueWalkway");
    	pastelLightGreenWalkway = new BlockWalkway(Material.rock, Block.soundTypeStone, "skyscraperBlocks71", "skyscraperBlocks71").setBlockName("pastelLightGreenWalkway");
    	pastelCyanWalkway = new BlockWalkway(Material.rock, Block.soundTypeStone, "skyscraperBlocks72", "skyscraperBlocks72").setBlockName("pastelCyanWalkway");
    	pastelPurpleWalkway = new BlockWalkway(Material.rock, Block.soundTypeStone, "skyscraperBlocks73", "skyscraperBlocks73").setBlockName("pastelPurpleWalkway");
    	
    	oakLaminateWalkway = new BlockWalkway(Material.wood, Block.soundTypeWood, "floorBlocks4", "oakLaminateWalkway").setBlockName("oakLaminateWalkway");
    	spruceLaminateWalkway = new BlockWalkway(Material.wood, Block.soundTypeWood, "floorBlocks5", "spruceLaminateWalkway").setBlockName("spruceLaminateWalkway");
    	birchLaminateWalkway = new BlockWalkway(Material.wood, Block.soundTypeWood, "floorBlocks6", "birchLaminateWalkway").setBlockName("birchLaminateWalkway");
    	jungleLaminateWalkway = new BlockWalkway(Material.wood, Block.soundTypeWood, "floorBlocks7", "jungleLaminateWalkway").setBlockName("jungleLaminateWalkway");
    	acaciaLaminateWalkway = new BlockWalkway(Material.wood, Block.soundTypeWood, "floorBlocks9", "acaciaLaminateWalkway").setBlockName("acaciaLaminateWalkway");
    	darkOakLaminateWalkway = new BlockWalkway(Material.wood, Block.soundTypeWood, "floorBlocks10", "darkOakLaminateWalkway").setBlockName("darkOakLaminateWalkway");
    	
    	oakWalkwayStairs = new BlockWalkwayStairs(Material.wood, Block.soundTypeWood, "floorBlocks11", "floorBlocks11").setBlockName("oakWalkwayStairs");
    	spruceWalkwayStairs = new BlockWalkwayStairs(Material.wood, Block.soundTypeWood, "floorBlocks12", "floorBlocks12").setBlockName("spruceWalkwayStairs");
    	birchWalkwayStairs = new BlockWalkwayStairs(Material.wood, Block.soundTypeWood, "floorBlocks13", "floorBlocks13").setBlockName("birchWalkwayStairs");
    	jungleWalkwayStairs = new BlockWalkwayStairs(Material.wood, Block.soundTypeWood, "floorBlocks14", "floorBlocks14").setBlockName("jungleWalkwayStairs");
    	acaciaWalkwayStairs = new BlockWalkwayStairs(Material.wood, Block.soundTypeWood, "floorBlocks15", "floorBlocks15").setBlockName("acaciaWalkwayStairs");
    	darkOakWalkwayStairs = new BlockWalkwayStairs(Material.wood, Block.soundTypeWood, "floorBlocks16", "floorBlocks16").setBlockName("darkOakWalkwayStairs");
    	
    	whiteWalkwayStairs = new BlockWalkwayStairs(Material.rock, Block.soundTypeStone, "skyscraperBlocks0", "skyscraperBlocks0").setBlockName("whiteWalkwayStairs");
    	lightGreyWalkwayStairs = new BlockWalkwayStairs(Material.rock, Block.soundTypeStone, "skyscraperBlocks1", "skyscraperBlocks1").setBlockName("lightGreyWalkwayStairs");
    	darkGreyWalkwayStairs = new BlockWalkwayStairs(Material.rock, Block.soundTypeStone, "skyscraperBlocks2", "skyscraperBlocks2").setBlockName("darkGreyWalkwayStairs");
    	blackWalkwayStairs = new BlockWalkwayStairs(Material.rock, Block.soundTypeStone, "skyscraperBlocks3", "skyscraperBlocks3").setBlockName("blackWalkwayStairs");
    	
    	lightGreenWalkwayStairs = new BlockWalkwayStairs(Material.rock, Block.soundTypeStone, "skyscraperBlocks20", "skyscraperBlocks20").setBlockName("lightGreenWalkwayStairs");
    	cyanWalkwayStairs = new BlockWalkwayStairs(Material.rock, Block.soundTypeStone, "skyscraperBlocks21", "skyscraperBlocks21").setBlockName("cyanWalkwayStairs");
    	purpleWalkwayStairs = new BlockWalkwayStairs(Material.rock, Block.soundTypeStone, "skyscraperBlocks22", "skyscraperBlocks22").setBlockName("purpleWalkwayStairs");
    	pinkWalkwayStairs = new BlockWalkwayStairs(Material.rock, Block.soundTypeStone, "skyscraperBlocks23", "skyscraperBlocks23").setBlockName("pinkWalkwayStairs");
    	
    	blueWalkwayStairs = new BlockWalkwayStairs(Material.rock, Block.soundTypeStone, "skyscraperBlocks30", "skyscraperBlocks30").setBlockName("blueWalkwayStairs");
    	brownWalkwayStairs = new BlockWalkwayStairs(Material.rock, Block.soundTypeStone, "skyscraperBlocks31", "skyscraperBlocks31").setBlockName("brownWalkwayStairs");
    	greenWalkwayStairs = new BlockWalkwayStairs(Material.rock, Block.soundTypeStone, "skyscraperBlocks32", "skyscraperBlocks32").setBlockName("greenWalkwayStairs");
    	redWalkwayStairs = new BlockWalkwayStairs(Material.rock, Block.soundTypeStone, "skyscraperBlocks33", "skyscraperBlocks33").setBlockName("redWalkwayStairs");
    	
    	orangeWalkwayStairs = new BlockWalkwayStairs(Material.rock, Block.soundTypeStone, "skyscraperBlocks40", "skyscraperBlocks40").setBlockName("orangeWalkwayStairs");
    	magentaWalkwayStairs = new BlockWalkwayStairs(Material.rock, Block.soundTypeStone, "skyscraperBlocks41", "skyscraperBlocks41").setBlockName("magentaWalkwayStairs");
    	lightBlueWalkwayStairs = new BlockWalkwayStairs(Material.rock, Block.soundTypeStone, "skyscraperBlocks42", "skyscraperBlocks42").setBlockName("lightBlueWalkwayStairs");
    	yellowWalkwayStairs = new BlockWalkwayStairs(Material.rock, Block.soundTypeStone, "skyscraperBlocks43", "skyscraperBlocks43").setBlockName("yellowWalkwayStairs");
    	
    	pastelDarkBlueWalkwayStairs = new BlockWalkwayStairs(Material.rock, Block.soundTypeStone, "skyscraperBlocks50", "skyscraperBlocks50").setBlockName("pastelDarkBlueWalkwayStairs");
    	pastelBrownWalkwayStairs = new BlockWalkwayStairs(Material.rock, Block.soundTypeStone, "skyscraperBlocks51", "skyscraperBlocks51").setBlockName("pastelBrownWalkwayStairs");
    	pastelGreenWalkwayStairs = new BlockWalkwayStairs(Material.rock, Block.soundTypeStone, "skyscraperBlocks52", "skyscraperBlocks52").setBlockName("pastelGreenWalkwayStairs");
    	pastelPinkWalkwayStairs = new BlockWalkwayStairs(Material.rock, Block.soundTypeStone, "skyscraperBlocks53", "skyscraperBlocks53").setBlockName("pastelPinkWalkwayStairs");
    	
    	pastelOrangeWalkwayStairs = new BlockWalkwayStairs(Material.rock, Block.soundTypeStone, "skyscraperBlocks60", "skyscraperBlocks60").setBlockName("pastelOrangeWalkwayStairs");
    	pastelMagentaWalkwayStairs = new BlockWalkwayStairs(Material.rock, Block.soundTypeStone, "skyscraperBlocks61", "skyscraperBlocks61").setBlockName("pastelMagentaWalkwayStairs");
    	pastelRedWalkwayStairs = new BlockWalkwayStairs(Material.rock, Block.soundTypeStone, "skyscraperBlocks62", "skyscraperBlocks62").setBlockName("pastelRedWalkwayStairs");
    	pastelYellowWalkwayStairs = new BlockWalkwayStairs(Material.rock, Block.soundTypeStone, "skyscraperBlocks63", "skyscraperBlocks63").setBlockName("pastelYellowWalkwayStairs");
    	
    	pastelLightBlueWalkwayStairs = new BlockWalkwayStairs(Material.rock, Block.soundTypeStone, "skyscraperBlocks70", "skyscraperBlocks70").setBlockName("pastelLightBlueWalkwayStairs");
    	pastelLightGreenWalkwayStairs = new BlockWalkwayStairs(Material.rock, Block.soundTypeStone, "skyscraperBlocks71", "skyscraperBlocks71").setBlockName("pastelLightGreenWalkwayStairs");
    	pastelCyanWalkwayStairs = new BlockWalkwayStairs(Material.rock, Block.soundTypeStone, "skyscraperBlocks72", "skyscraperBlocks72").setBlockName("pastelCyanWalkwayStairs");
    	pastelPurpleWalkwayStairs = new BlockWalkwayStairs(Material.rock, Block.soundTypeStone, "skyscraperBlocks73", "skyscraperBlocks73").setBlockName("pastelPurpleWalkwayStairs");
    	
    	oakLaminateWalkwayStairs = new BlockWalkwayStairs(Material.wood, Block.soundTypeWood, "floorBlocks4", "oakLaminateWalkway").setBlockName("oakLaminateWalkwayStairs");
    	spruceLaminateWalkwayStairs = new BlockWalkwayStairs(Material.wood, Block.soundTypeWood, "floorBlocks5", "spruceLaminateWalkway").setBlockName("spruceLaminateWalkwayStairs");
    	birchLaminateWalkwayStairs = new BlockWalkwayStairs(Material.wood, Block.soundTypeWood, "floorBlocks6", "birchLaminateWalkway").setBlockName("birchLaminateWalkwayStairs");
    	jungleLaminateWalkwayStairs = new BlockWalkwayStairs(Material.wood, Block.soundTypeWood, "floorBlocks7", "jungleLaminateWalkway").setBlockName("jungleLaminateWalkwayStairs");
    	acaciaLaminateWalkwayStairs = new BlockWalkwayStairs(Material.wood, Block.soundTypeWood, "floorBlocks9", "acaciaLaminateWalkway").setBlockName("acaciaLaminateWalkwayStairs");
    	darkOakLaminateWalkwayStairs = new BlockWalkwayStairs(Material.wood, Block.soundTypeWood, "floorBlocks10", "darkOakLaminateWalkway").setBlockName("darkOakLaminateWalkwayStairs");
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
