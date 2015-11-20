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
	public static Block birchWalkway;
	public static Block spruceWalkway;
	public static Block jungleWalkway;
	public static Block acaciaWalkway;
	public static Block darkOakWalkway;
	
	public static Block whiteWalkway;
	public static Block lightGreyWalkway;
	public static Block darkGreyWalkway;
	public static Block blackWalkway;
	
	public static Block oakLaminateWalkway;
	public static Block birchLaminateWalkway;
	public static Block spruceLaminateWalkway;
	public static Block jungleLaminateWalkway;
	public static Block acaciaLaminateWalkway;
	public static Block darkLaminateOakWalkway;
	
	public static Block oakWalkwayFence;
	public static Block birchWalkwayFence;
	public static Block spruceWalkwayFence;
	public static Block jungleWalkwayFence;
	public static Block acaciaWalkwayFence;
	public static Block darkOakWalkwayFence;
	
	public static Block whiteWalkwayFence;
	public static Block lightGreyWalkwayFence;
	public static Block darkGreyWalkwayFence;
	public static Block blackWalkwayFence;
	
	public static Block oakLaminateWalkwayFence;
	public static Block birchLaminateWalkwayFence;
	public static Block spruceLaminateWalkwayFence;
	public static Block jungleLaminateWalkwayFence;
	public static Block acaciaLaminateWalkwayFence;
	public static Block darkLaminateOakWalkwayFence;
	
	public static Block oakWalkwayOpenFence;
	public static Block birchWalkwayOpenFence;
	public static Block spruceWalkwayOpenFence;
	public static Block jungleWalkwayOpenFence;
	public static Block acaciaWalkwayOpenFence;
	public static Block darkOakWalkwayOpenFence;
	
	public static Block whiteWalkwayOpenFence;
	public static Block lightGreyWalkwayOpenFence;
	public static Block darkGreyWalkwayOpenFence;
	public static Block blackWalkwayOpenFence;
	
	public static Block oakLaminateWalkwayOpenFence;
	public static Block birchLaminateWalkwayOpenFence;
	public static Block spruceLaminateWalkwayOpenFence;
	public static Block jungleLaminateWalkwayOpenFence;
	public static Block acaciaLaminateWalkwayOpenFence;
	public static Block darkLaminateOakWalkwayOpenFence;
	
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
    	
    	oakWalkway = new BlockWalkway(Material.wood, Block.soundTypeWood).setBlockName("oakWalkway");
    	
    	/*ironPanel = new BlockDrywall(config.ironPanelID, Block.blockIron.getItemIconName(), Block.blockIron.getItemIconName(), false).setBlockName("ironPanel");
    	blockSilk = new BlockMulti(config.blockSilkID, Material.cloth).setBlockName("blockSilk");
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
