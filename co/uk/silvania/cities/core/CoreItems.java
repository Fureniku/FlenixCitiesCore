package co.uk.silvania.cities.core;

import net.minecraft.item.Item;
import co.uk.silvania.cities.core.items.CraftingIngredientItems;
import co.uk.silvania.cities.core.items.econ.DebitCard;
import co.uk.silvania.cities.core.items.econ.ItemCoin1;
import co.uk.silvania.cities.core.items.econ.ItemCoin10;
import co.uk.silvania.cities.core.items.econ.ItemCoin100;
import co.uk.silvania.cities.core.items.econ.ItemCoin2;
import co.uk.silvania.cities.core.items.econ.ItemCoin25;
import co.uk.silvania.cities.core.items.econ.ItemCoin5;
import co.uk.silvania.cities.core.items.econ.ItemCoin50;
import co.uk.silvania.cities.core.items.econ.ItemNote1;
import co.uk.silvania.cities.core.items.econ.ItemNote10;
import co.uk.silvania.cities.core.items.econ.ItemNote100;
import co.uk.silvania.cities.core.items.econ.ItemNote2;
import co.uk.silvania.cities.core.items.econ.ItemNote20;
import co.uk.silvania.cities.core.items.econ.ItemNote5;
import co.uk.silvania.cities.core.items.econ.ItemNote50;

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
	public static Item atmItem;
	public static Item ringItem;
	public static Item diamondRing;
	public static Item necklaceItem;
	public static Item rubyNecklace;
	
	public static void init() {
		initMinerals();
		initCrafting();
		initEconItems();
	}
	
	public static void initMinerals() {
		rubyItem = new CraftingIngredientItems(config.rubyItemID, 64).setUnlocalizedName("rubyItem");
		titaniumIngot = new CraftingIngredientItems(config.titaniumIngotID, 64).setUnlocalizedName("titaniumIngot");
		tecmoniumIngot = new CraftingIngredientItems(config.tecmoniumIngotID, 64).setUnlocalizedName("tecmoniumIngot");
		silverIngot = new CraftingIngredientItems(config.silverIngotID, 64).setUnlocalizedName("silverIngot");
		copperIngot = new CraftingIngredientItems(config.copperIngotID, 64).setUnlocalizedName("copperIngot");
		tinIngot = new CraftingIngredientItems(config.tinIngotID, 64).setUnlocalizedName("tinIngot");
		crystalItem = new CraftingIngredientItems(config.crystalItemID, 64).setUnlocalizedName("crystalItem");
		sapphireItem = new CraftingIngredientItems(config.sapphireItemID, 64).setUnlocalizedName("sapphireItem");
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
    	
    	debitCard = new DebitCard(config.debitCardID).setUnlocalizedName("debitCard");
		//ringItem = new CraftingIngredientItems(config.ringItemID, 64).setUnlocalizedName("ringItem");
		//diamondRing = new CraftingIngredientItems(config.diamondRingID, 64).setUnlocalizedName("diamondRing");
		//necklaceItem = new CraftingIngredientItems(config.necklaceItemID, 64).setUnlocalizedName("necklaceItem");
		//rubyNecklace = new CraftingIngredientItems(config.rubyNecklaceID, 64).setUnlocalizedName("rubyNecklace");
	}

}
