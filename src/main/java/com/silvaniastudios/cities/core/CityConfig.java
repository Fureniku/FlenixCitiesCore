package com.silvaniastudios.cities.core;

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
	public static boolean extraDecorativeBlocks;
	
	public static boolean mobsDropMoney;
	
	public static void initConfig (File configFile) {
		config = new Configuration(configFile);
		
		try {
			config.load();	
			debugMode = config.get(Configuration.CATEGORY_GENERAL, "debug mode", false).getBoolean(false);
			extraDecorativeBlocks = config.get(Configuration.CATEGORY_GENERAL, "Add loads of extra decorative blocks (Uses an additional 170 block IDs)", true).getBoolean(true);
		}
		catch (Exception e) {
			System.out.println("### Warning! Fureniku's Cities could not load it's config file! ###");
		}
		
		finally {
			if (config.hasChanged()) {
				config.save();
			}
		}
	}

}
