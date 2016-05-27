package co.uk.silvania.cities.core;

import java.io.File;
import net.minecraftforge.common.config.Configuration;

public class CityConfig {
	
	public static File fcCoreConfigFile;
	
	public static void init(String configpath) {
		fcCoreConfigFile = new File(configpath + "FlenixCities_Core.cfg");
		
		CityConfig.initConfig(fcCoreConfigFile);
	}
	
    public static Configuration config;
	
	public static String currencyLarge;
	public static String currencySmall;
	public static String currencyLargePlural;
	public static String currencySmallPlural;

	public static boolean playerOwnedShops;
	public static boolean debugMode;
	public static boolean allowCardPurchases;
	
	public static boolean mobsDropMoney;
	
	
	//public static void loadConfig(FMLPreInitializationEvent event) {
	//Configuration config = new Configuration(event.getSuggestedConfigurationFile());
	public static void initConfig (File configFile) {
		config = new Configuration(configFile);
		
		try {
			config.load();	
			currencySmall = config.get("Currency Small", Configuration.CATEGORY_GENERAL, "Cent").getString();
			currencyLarge = config.get("Currency Large", Configuration.CATEGORY_GENERAL, "Dollar").getString();
			currencySmallPlural = config.get("Currency Small (Plural)", Configuration.CATEGORY_GENERAL, "Cents").getString();
			currencyLargePlural = config.get("Currency Large (Plural)", Configuration.CATEGORY_GENERAL, "Dollars").getString();

			playerOwnedShops = config.get(Configuration.CATEGORY_GENERAL, "Enable player-owned shops", true).getBoolean(true);
			debugMode = config.get(Configuration.CATEGORY_GENERAL, "debug mode", false).getBoolean(false);
			allowCardPurchases = config.get(Configuration.CATEGORY_GENERAL, "Enable player-owned shops", true).getBoolean(true);
			mobsDropMoney = config.get(Configuration.CATEGORY_GENERAL, "Allow mobs dropping money (Only when killed by players directly!)", true).getBoolean(true);
		}
		catch (Exception e) {
			System.out.println("### Warning! FlenixCities Core could not load it's config file! ###");
			//FMLLog.log(Level.SEVERE, e, "### Warning! FlenixCities Core could not load it's config file! ###");
		}
		
		finally {
			if (config.hasChanged()) {
				config.save();
			}
		}
	}

}
