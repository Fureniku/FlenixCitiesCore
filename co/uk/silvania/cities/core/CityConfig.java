package co.uk.silvania.cities.core;

import java.io.File;
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CityConfig {
	
	public static File fcCoreConfigFile;
	
	public static void init(String configpath) {
		fcCoreConfigFile = new File(configpath + "FlenixCities_Core.cfg");
		
		CityConfig.initConfig(fcCoreConfigFile);
	}
	
    private String value;
    public static Configuration config;
	
	public static int rubyOreID;
	public static int silverOreID;
	public static int tecmoniumOreID;
	public static int titaniumOreID;
	public static int copperOreID;
	public static int tinOreID;
	public static int crystalOreID;
	public static int sapphireOreID;
	public static int oreStorageID;
	
	public static int skyscraperBlocksID;
	public static int stainedGlassID;
	public static int stainedGlassLitID;
	public static int styledGlassID;
	public static int styledGlassLitID;
	public static int woolCeilingTileID;
	public static int woolStoneID;
	public static int woolWoodID;
	public static int floorBlocksID;
	public static int plasticBlockID;
	public static int lightingBlockID;
	public static int lightingRotateBlocksID;
	
	public static int rebarBlockID;
	public static int drywallWhiteID;
	public static int drywallRedID;
	public static int drywallBlueID;
	public static int drywallGreenID;
	public static int drywallGreyID;
	public static int styledGlassPaneID;
	public static int styledGlassWhitePaneID;

	public static int blockOilID;
	public static int blockPetrolID;
	public static int blockDieselID;
	public static int blockRedDieselID;
	public static int adminShopBlockID;
	
	public static int atmID;
	public static int floatingShelvesID;
	public static int npcSpawnerBlockID;
	
	public static int verticalPoster1ID;
	public static int verticalPoster2ID;
	public static int verticalPoster3ID;
	public static int verticalPoster4ID;
	public static int horizontalPoster1ID;
	public static int horizontalPoster2ID;
	public static int horizontalPoster3ID;
	public static int horizontalPoster4ID;
	
	public static int plasticItemID;
	public static int smallPCBID;
	public static int largePCBID;
	
	public static int rubyItemID;
	public static int titaniumIngotID;
	public static int tecmoniumIngotID;
	public static int silverIngotID;
	public static int copperIngotID;
	public static int tinIngotID;
	public static int crystalItemID;
	public static int sapphireItemID;
	
	public static int coin1ID;
	public static int coin2ID;
	public static int coin5ID;
	public static int coin10ID;
	public static int coin25ID;
	public static int coin50ID;
	public static int coin100ID;
	public static int note100ID;
	public static int note200ID;
	public static int note500ID;
	public static int note1000ID;
	public static int note2000ID;
	public static int note5000ID;
	public static int note10000ID;
	public static int prePaidCardID;
	public static int debitCardID;
	public static int debitCardNewID;
	public static int atmItemID;
	public static int bankerSpawnerID;
	
	public static int idCardID;
	public static int accessCardID;
	
	public static int oilBucketID;
	public static int petrolBucketID;
	public static int dieselBucketID;
	public static int redDieselBucketID;

	public static int ringItemID;
	public static int diamondRingID;
	public static int necklaceItemID;
	public static int rubyNecklaceID;
	
	public static String currencyLarge;
	public static String currencySmall;
	public static String currencyLargePlural;
	public static String currencySmallPlural;
	
	public static boolean generateCopper;
	public static boolean generateTin;
	public static boolean generateSilver;
	public static boolean generateTitanium;
	public static boolean generateRuby;
	
	public static boolean disableOreDict;
	public static boolean debugMode;
	
	
	//public static void loadConfig(FMLPreInitializationEvent event) {
	//Configuration config = new Configuration(event.getSuggestedConfigurationFile());
	public static void initConfig (File configFile) {
		config = new Configuration(configFile);
		
		
		try {
			config.load();
			atmID = config.getBlock("ATM", 1402).getInt();
			floatingShelvesID = config.getBlock("Floating Shelves", 1418).getInt();
			
			rubyOreID = config.getBlock("Ruby Ore", 1403).getInt();
			silverOreID = config.getBlock("Silver Ore", 1404).getInt();
			tecmoniumOreID = config.getBlock("Tecmonium Ore", 1405).getInt();
			titaniumOreID = config.getBlock("Titanium Ore", 1406).getInt();
			copperOreID = config.getBlock("Copper Ore", 1407).getInt();
			tinOreID = config.getBlock("Tin Ore", 1408).getInt();
			crystalOreID = config.getBlock("Crystal Ore", 1409).getInt();
			sapphireOreID = config.getBlock("Sapphire Ore", 1410).getInt();
			oreStorageID = config.getBlock("Ore Storage", 1441).getInt();
			
			skyscraperBlocksID = config.getBlock("Skyscraper Blocks", 1421).getInt();
			stainedGlassID = config.getBlock("Stained Glass", 1416).getInt();
			stainedGlassLitID = config.getBlock("Stained Glass Lit", 1417).getInt();
			styledGlassID = config.getBlock("Styled Glass", 1411).getInt();
			styledGlassLitID = config.getBlock("Styled Glass Lit", 1412).getInt();
			woolCeilingTileID = config.getBlock("Wool Ceiling Tile", 1413).getInt();
			woolStoneID = config.getBlock("Wool Stone", 1414).getInt();
			woolWoodID = config.getBlock("Wool Wood", 1415).getInt();
			plasticBlockID = config.getBlock("Plastic Block", 1424).getInt();
			floorBlocksID = config.getBlock("Floor Blocks", 1440).getInt();
			
			rebarBlockID = config.getBlock("Rebar Block", 1449).getInt();
			lightingBlockID = config.getBlock("Lighting Blocks", 1450).getInt();
			drywallWhiteID = config.getBlock("Drywall White", 1451).getInt();
			drywallRedID = config.getBlock("Drywall Red", 1452).getInt();
			drywallBlueID = config.getBlock("Drywall Blue", 1453).getInt();
			drywallGreenID = config.getBlock("Drywall Green", 1454).getInt();
			drywallGreyID = config.getBlock("Drywall Grey", 1455).getInt();
			lightingRotateBlocksID = config.getBlock("Lighting Blocks (Rotatable)", 1456).getInt();
			npcSpawnerBlockID = config.getBlock("NPC Spawner", 1457).getInt();
			blockOilID = config.getBlock("Oil", 1460).getInt();
			blockPetrolID = config.getBlock("Petrol", 1461).getInt();
			blockDieselID = config.getBlock("Diesel", 1462).getInt();
			blockRedDieselID = config.getBlock("Red Diesel", 1463).getInt();
			styledGlassPaneID = config.getBlock("Styled Glass Pane", 1464).getInt();
			styledGlassWhitePaneID = config.getBlock("Styled Glass Pane (White)", 1465).getInt();
			adminShopBlockID = config.getBlock("Admin Shop Block", 1466).getInt();
			
			verticalPoster1ID = config.getBlock("Vertical Poster 1", 1441).getInt();
			verticalPoster2ID = config.getBlock("Vertical Poster 2", 1442).getInt();
			verticalPoster3ID = config.getBlock("Vertical Poster 3", 1443).getInt();
			verticalPoster4ID = config.getBlock("Vertical Poster 4", 1444).getInt();
			horizontalPoster1ID = config.getBlock("Horizontal Poster 1", 1445).getInt();
			horizontalPoster2ID = config.getBlock("Horizontal Poster 2", 1446).getInt();
			horizontalPoster3ID = config.getBlock("Horizontal Poster 3", 1447).getInt();
			horizontalPoster4ID = config.getBlock("Horizontal Poster 4", 1448).getInt();
					
			plasticItemID = config.getItem("Plastic", 18058).getInt();
			smallPCBID = config.getItem("Small PCB", 18059).getInt();
			largePCBID = config.getItem("Large PCB", 18060).getInt();
			
			rubyItemID = config.getItem("Ruby Item", 18074).getInt();
			titaniumIngotID = config.getItem("Titanium Ingot", 18075).getInt();
			tecmoniumIngotID = config.getItem("Tecmonium Ingot", 18076).getInt();
			silverIngotID = config.getItem("Silver Ingot", 18077).getInt();
			copperIngotID = config.getItem("Copper Ingot", 18078).getInt();
			tinIngotID = config.getItem("Tin Ingot", 18079).getInt();
			sapphireItemID = config.getItem("Sapphire Item", 18080).getInt();
			crystalItemID = config.getItem("Crystal Item", 18081).getInt();
	
			coin1ID = config.getItem("Coin (1)", 18000).getInt();
			coin2ID = config.getItem("Coin (2)", 18001).getInt();
			coin5ID = config.getItem("Coin (5)", 18002).getInt();
			coin10ID = config.getItem("Coin (10)", 18003).getInt();
			coin25ID = config.getItem("Coin (25)", 18004).getInt();
			coin50ID = config.getItem("Coin (50)", 18005).getInt();
			coin100ID = config.getItem("Coin (100)", 18006).getInt();
			note100ID = config.getItem("Note (100)", 18007).getInt();
			note200ID = config.getItem("Note (200)", 18008).getInt();
			note500ID = config.getItem("Note (500)", 18009).getInt();
			note1000ID = config.getItem("Note (1,000)", 18010).getInt();
			note2000ID = config.getItem("Note (2,000)", 18011).getInt();
			note5000ID = config.getItem("Note (5,000)", 18012).getInt();
			note10000ID = config.getItem("Note (10,000)", 18013).getInt();
			prePaidCardID = config.getItem("Pre-Paid Card", 18014).getInt();
			debitCardID = config.getItem("Debit Card", 18015).getInt();
			debitCardNewID = config.getItem("Debit Card (New)", 18017).getInt();
			atmItemID = config.getItem("ATM Item", 18016).getInt();
			bankerSpawnerID = config.getItem("Banker Spawner", 18018).getInt();
			idCardID = config.getItem("ID Card", 18019).getInt();
			accessCardID = config.getItem("Access Card", 18020).getInt();
			oilBucketID = config.getItem("Oil Bucket", 18021).getInt();
			petrolBucketID = config.getItem("Petrol Bucket", 18022).getInt();
			dieselBucketID = config.getItem("Diesel Bucket", 18023).getInt();
			redDieselBucketID = config.getItem("Red Diesel Bucket", 18024).getInt();
	
			ringItemID = config.getItem("Ring Item", 18064).getInt();
			diamondRingID = config.getItem("Diamond Ring", 18065).getInt();
			necklaceItemID = config.getItem("Necklace", 18066).getInt();
			rubyNecklaceID = config.getItem("Ruby Necklace", 18067).getInt();
	
			currencySmall = config.get("Currency Small", Configuration.CATEGORY_GENERAL, "Cent").getString();
			currencyLarge = config.get("Currency Large", Configuration.CATEGORY_GENERAL, "Dollar").getString();
			currencySmallPlural = config.get("Currency Small (Plural)", Configuration.CATEGORY_GENERAL, "Cents").getString();
			currencyLargePlural = config.get("Currency Large (Plural)", Configuration.CATEGORY_GENERAL, "Dollars").getString();
	
			generateCopper = config.get(Configuration.CATEGORY_GENERAL, "generateCopper", true).getBoolean(true);
			generateTin = config.get(Configuration.CATEGORY_GENERAL, "generateTin", true).getBoolean(true);
			generateTitanium = config.get(Configuration.CATEGORY_GENERAL, "generateTitanium", true).getBoolean(true);
			generateSilver = config.get(Configuration.CATEGORY_GENERAL, "generateSilver", true).getBoolean(true);
			generateRuby = config.get(Configuration.CATEGORY_GENERAL, "generateRuby", true).getBoolean(true);
			
			disableOreDict = config.get(Configuration.CATEGORY_GENERAL, "Disable OreDictionary (Do not use unless you know how!)", false).getBoolean(false);
			debugMode = config.get(Configuration.CATEGORY_GENERAL, "debug mode", false).getBoolean(false);
		}
		catch (Exception e) {
			FMLLog.log(Level.SEVERE, e, "### Warning! FlenixCities Core could not load it's config file! ###");
		}
		
		finally {
			if (config.hasChanged()) {
				config.save();
			}
		}
	}

}
