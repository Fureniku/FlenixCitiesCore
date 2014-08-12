package co.uk.silvania.cities.core;

import net.minecraft.item.Item;
import co.uk.silvania.cities.core.npc.spawner.BankerSpawnerItem;
import co.uk.silvania.cities.econ.DebitCardItem;
import co.uk.silvania.cities.econ.money.*;

public class CoreItems {
	private static CityConfig config;
		
	public static Item coin1;
	public static Item coin2;
	public static Item coin5;
	public static Item coin10;
	public static Item coin25;
	public static Item coin50;
	public static Item coin100;
	public static Item note100;
	public static Item note200;
	public static Item note500;
	public static Item note1000;
	public static Item note2000;
	public static Item note5000;
	public static Item note10000;

	public static Item debitCardNew;
	
	public static Item bankerSpawner;

	
	public static void init() {
		initCrafting();
		initEconItems();
	}
	
	
	public static void initCrafting() {
		//plasticItem = new CraftingIngredientItems(config.plasticItemID, 64).setUnlocalizedName("plasticItem");
		//smallPCB = new CraftingIngredientItems(config.smallPCBID, 64).setUnlocalizedName("smallPCB");
		//largePCB = new CraftingIngredientItems(config.largePCBID, 64).setUnlocalizedName("largePCB");
	}
	
	public static void initEconItems() {
		coin1 = new ItemCoin(0.01).setUnlocalizedName("coin1");
    	coin2 = new ItemCoin(0.02).setUnlocalizedName("coin2");
    	coin5 = new ItemCoin(0.05).setUnlocalizedName("coin5");
    	coin10 = new ItemCoin(0.1).setUnlocalizedName("coin10");
    	coin25 = new ItemCoin(0.25).setUnlocalizedName("coin25");
    	coin50 = new ItemCoin(0.5).setUnlocalizedName("coin50");
    	coin100 = new ItemCoin(1).setUnlocalizedName("coin100");
    	note100 = new ItemNote(1).setUnlocalizedName("note100");
    	note200 = new ItemNote(2).setUnlocalizedName("note200");
    	note500 = new ItemNote(5).setUnlocalizedName("note500");
    	note1000 = new ItemNote(10).setUnlocalizedName("note1000");
    	note2000 = new ItemNote(20).setUnlocalizedName("note2000");
    	note5000 = new ItemNote(50).setUnlocalizedName("note5000");
    	note10000 = new ItemNote(100).setUnlocalizedName("note10000");
    	
    	debitCardNew = new DebitCardItem().setUnlocalizedName("debitCardNew");
    	
    	bankerSpawner = new BankerSpawnerItem().setUnlocalizedName("bankerSpawnerItem");
		//ringItem = new CraftingIngredientItems(config.ringItemID, 64).setUnlocalizedName("ringItem");
		//diamondRing = new CraftingIngredientItems(config.diamondRingID, 64).setUnlocalizedName("diamondRing");
		//necklaceItem = new CraftingIngredientItems(config.necklaceItemID, 64).setUnlocalizedName("necklaceItem");
		//rubyNecklace = new CraftingIngredientItems(config.rubyNecklaceID, 64).setUnlocalizedName("rubyNecklace");
	}
	

}
