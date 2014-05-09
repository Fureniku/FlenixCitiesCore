package co.uk.silvania.cities.econ.store;

import java.io.File;
import java.util.logging.Level;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;


public class VanillaItemValueConfig {
	
	public static File valueConfigFile;
    public static Configuration config;
	
	public static void init(String configpath) {
		valueConfigFile = new File(configpath + "VanillaItems.cfg");
		
		VanillaItemValueConfig.initConfig(valueConfigFile);
	}
	
	public static final String CATEGORY_GENERIC = "General Settings";
	public static final String CATEGORY_VANILLA_BLOCKS = "Vanilla Blocks";
	public static final String CATEGORY_VANILLA_ITEMS = "Vanilla Items";
	
	public static double multiplier;
	
	public static double stone;
	
	public static double iron_shovel;
	public static double iron_pickaxe;
	public static double iron_axe;
	public static double flint_and_steel;
	public static double apple;
	public static double bow;
	public static double arrow;
	public static double coal;
	public static double diamond;
	public static double iron_ingot;
	public static double gold_ingot;
	public static double iron_sword;
	public static double wooden_sword;
	public static double wooden_shovel;
	public static double wooden_pickaxe;
	public static double wooden_axe;
	public static double stone_sword;
	public static double stone_shovel;
	public static double stone_pickaxe;
	public static double stone_axe;
	public static double diamond_sword;
	public static double diamond_shovel;
	public static double diamond_pickaxe;
	public static double diamond_axe;
	public static double stick;
	public static double bowl;
	public static double mushroom_stew;
	public static double golden_sword;
	public static double golden_shovel;
	public static double golden_pickaxe;
	public static double golden_axe;
	public static double string;
	public static double feather;
	public static double gunpowder;
	
	public static double wooden_hoe;
	public static double stone_hoe;
	public static double iron_hoe;
	public static double diamond_hoe;
	public static double golden_hoe;
	public static double wheat_seeds;
	public static double wheat;
	public static double bread;
	public static double leather_helmet;
	public static double leather_chestplate;
	public static double leather_leggings;
	public static double leather_boots;
	public static double chainmail_helmet;
	public static double chainmail_chestplate;
	public static double chainmail_leggings;
	public static double chainmail_boots;
	public static double iron_helmet;
	public static double iron_chestplate;
	public static double iron_leggings;
	public static double iron_boots;
	public static double diamond_helmet;
	public static double diamond_chestplate;
	public static double diamond_leggings;
	public static double diamond_boots;
	public static double golden_helmet;
	public static double golden_chestplate;
	public static double golden_leggings;
	public static double golden_boots;
	public static double flint;
	public static double porkchop;
	public static double cooked_porkchop;
	public static double painting;
	public static double golden_apple;
	public static double sign;
	
	public static double wooden_door;
	public static double bucket;
	public static double water_bucket;
	public static double lava_bucket;
	public static double minecart;
	public static double saddle;
	public static double iron_door;
	public static double redstone;
	public static double snowball;
	public static double boat;
	public static double leather;
	public static double milk_bucket;
	public static double brick;
	public static double clay_ball;
	public static double reeds;
	public static double paper;
	public static double book;
	public static double slime_ball;
	public static double chest_minecart;
	public static double furnace_minecart;
	public static double egg;
	public static double compass;
	public static double fishing_rod;
	public static double clock;
	public static double glowstone_dust;
	public static double fish;
	public static double cooked_fish;
	public static double dye;
	public static double bone;
	public static double sugar;
	public static double cake;
	public static double bed;
	public static double repeater;
	public static double cookie;
	
	public static double filled_map;
	public static double shears;
	public static double melon;
	public static double pumpkin_seeds;
	public static double melon_seeds;
	public static double beef;
	public static double cooked_beef;
	public static double chicken;
	public static double cooked_chicken;
	public static double rotten_flesh;
	public static double ender_pearl;
	public static double blaze_rod;
	public static double ghast_tear;
	public static double gold_nugget;
	public static double nether_wart;
	public static double potion;
	public static double glass_bottle;
	public static double spider_eye;
	public static double fermented_spider_eye;
	public static double blaze_powder;
	public static double magma_cream;
	public static double brewing_stand;
	public static double cauldron;
	public static double ender_eye;
	public static double speckled_melon;
	public static double spawn_egg;
	public static double experience_bottle;
	public static double fire_charge;
	public static double writable_book;
	public static double written_book;
	public static double emerald;
	public static double item_frame;
	public static double flower_pot;
	public static double carrot;
	
	public static double potato;
	public static double baked_potato;
	public static double poisonous_potato;
	public static double map;
	public static double golden_carrot;
	public static double skull;
	public static double carrot_on_a_stick;
	public static double nether_star;
	public static double pumpkin_pie;
	public static double fireworks;
	public static double firework_charge;
	public static double enchanted_book;
	public static double comparator;
	public static double netherbrick;
	public static double quartz;
	public static double tnt_minecart;
	public static double hopper_minecart;
	public static double iron_horse_armor;
	public static double golden_horse_armor;
	public static double diamond_horse_armor;
	public static double lead;
	public static double name_tag;
	public static double command_block_minecart;
	public static double record_13;
	public static double record_cat;
	public static double record_blocks;
	public static double record_chirp;
	public static double record_far;
	public static double record_mall;
	public static double record_mellohi;
	public static double record_stal;
	public static double record_strad;
	public static double record_ward;
	public static double record_11;
	public static double record_wait;

	public static void initConfig (File configFile) {
		config = new Configuration(configFile);
		
		
		try {
			config.load();
			config.addCustomCategoryComment(CATEGORY_GENERIC, "The cost multiplier automatically multiplies ALL the values here." + "\n" + "For example, if Stone was set to 7.2, and the multiplier set to 1.5," + "\n" + "the in-game default value of stone would be 7.2 x 1.5 = 10.8");
			multiplier = config.get(CATEGORY_GENERIC, "Cost Multiplier", 1.0).getDouble(multiplier);
			
			config.addCustomCategoryComment(CATEGORY_VANILLA_BLOCKS, "All of the blocks from official Minecraft");
			stone = config.get(CATEGORY_VANILLA_BLOCKS, "Stone", 0.7).getDouble(stone);
			
			config.addCustomCategoryComment(CATEGORY_VANILLA_ITEMS, "All of the items from official Minecraft");
			iron_shovel = config.get(CATEGORY_VANILLA_ITEMS, "Iron Shovel", 1.7).getDouble(iron_shovel);

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
