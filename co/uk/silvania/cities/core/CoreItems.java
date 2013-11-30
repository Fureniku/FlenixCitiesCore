package co.uk.silvania.cities.core;

import net.minecraft.item.Item;
import co.uk.silvania.cities.core.items.CraftingIngredientItems;
import co.uk.silvania.cities.core.items.ItemIDCard;
import co.uk.silvania.cities.core.npc.spawner.BankerSpawnerItem;
import co.uk.silvania.cities.econ.DebitCardItem;
import co.uk.silvania.cities.econ.money.*;

public class CoreItems {
	private static CityConfig config;
	
	public static Item rubyItem;
	public static Item titaniumIngot;
	public static Item tecmoniumIngot;
	public static Item silverIngot;
	public static Item copperIngot;
	public static Item tinIngot;
	public static Item crystalItem;
	public static Item sapphireItem;
	
	public static Item plasticItem;
	public static Item smallPCB;
	public static Item largePCB;
	
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

	public static Item debitCard;
	public static Item debitCardNew;
	public static Item atmItem;
	public static Item ringItem;
	public static Item diamondRing;
	public static Item necklaceItem;
	public static Item rubyNecklace;
	
	public static Item bankerSpawner;
	
	public static Item idCard;
	
	public static void init() {
		initMinerals();
		initCrafting();
		initEconItems();
		initGenericItems();
	}
	
	public static void initMinerals() {
		rubyItem = new CraftingIngredientItems(config.rubyItemID, 64, FlenixCities_Core.tabCity).setUnlocalizedName("rubyItem");
		titaniumIngot = new CraftingIngredientItems(config.titaniumIngotID, 64, FlenixCities_Core.tabCity).setUnlocalizedName("titaniumIngot");
		tecmoniumIngot = new CraftingIngredientItems(config.tecmoniumIngotID, 64, FlenixCities_Core.tabCity).setUnlocalizedName("tecmoniumIngot");
		silverIngot = new CraftingIngredientItems(config.silverIngotID, 64, FlenixCities_Core.tabCity).setUnlocalizedName("silverIngot");
		copperIngot = new CraftingIngredientItems(config.copperIngotID, 64, FlenixCities_Core.tabCity).setUnlocalizedName("copperIngot");
		tinIngot = new CraftingIngredientItems(config.tinIngotID, 64, FlenixCities_Core.tabCity).setUnlocalizedName("tinIngot");
		crystalItem = new CraftingIngredientItems(config.crystalItemID, 64, FlenixCities_Core.tabCity).setUnlocalizedName("crystalItem");
		sapphireItem = new CraftingIngredientItems(config.sapphireItemID, 64, FlenixCities_Core.tabCity).setUnlocalizedName("sapphireItem");
	}
	
	public static void initCrafting() {
		//plasticItem = new CraftingIngredientItems(config.plasticItemID, 64).setUnlocalizedName("plasticItem");
		//smallPCB = new CraftingIngredientItems(config.smallPCBID, 64).setUnlocalizedName("smallPCB");
		//largePCB = new CraftingIngredientItems(config.largePCBID, 64).setUnlocalizedName("largePCB");
	}
	
	public static void initEconItems() {
		coin1 = new ItemCoin1(config.coin1ID).setUnlocalizedName("coin1");
    	coin2 = new ItemCoin2(config.coin2ID).setUnlocalizedName("coin2");
    	coin5 = new ItemCoin5(config.coin5ID).setUnlocalizedName("coin5");
    	coin10 = new ItemCoin10(config.coin10ID).setUnlocalizedName("coin10");
    	coin25 = new ItemCoin25(config.coin25ID).setUnlocalizedName("coin25");
    	coin50 = new ItemCoin50(config.coin50ID).setUnlocalizedName("coin50");
    	coin100 = new ItemCoin100(config.coin100ID).setUnlocalizedName("coin100");
    	note100 = new ItemNote1(config.note100ID).setUnlocalizedName("note100");
    	note200 = new ItemNote2(config.note200ID).setUnlocalizedName("note200");
    	note500 = new ItemNote5(config.note500ID).setUnlocalizedName("note500");
    	note1000 = new ItemNote10(config.note1000ID).setUnlocalizedName("note1000");
    	note2000 = new ItemNote20(config.note2000ID).setUnlocalizedName("note2000");
    	note5000 = new ItemNote50(config.note5000ID).setUnlocalizedName("note5000");
    	note10000 = new ItemNote100(config.note10000ID).setUnlocalizedName("note10000");
    	
    	debitCardNew = new DebitCardItem(config.debitCardNewID).setUnlocalizedName("debitCardNew");
    	debitCard = new CraftingIngredientItems(config.debitCardID, 1, FlenixCities_Core.tabEcon).setUnlocalizedName("debitCard");
    	
    	bankerSpawner = new BankerSpawnerItem(config.bankerSpawnerID).setUnlocalizedName("bankerSpawnerItem");
		//ringItem = new CraftingIngredientItems(config.ringItemID, 64).setUnlocalizedName("ringItem");
		//diamondRing = new CraftingIngredientItems(config.diamondRingID, 64).setUnlocalizedName("diamondRing");
		//necklaceItem = new CraftingIngredientItems(config.necklaceItemID, 64).setUnlocalizedName("necklaceItem");
		//rubyNecklace = new CraftingIngredientItems(config.rubyNecklaceID, 64).setUnlocalizedName("rubyNecklace");
	}
	
	public static void initGenericItems() {
		idCard = new ItemIDCard(config.idCardID).setUnlocalizedName("idCard");
	}
}
