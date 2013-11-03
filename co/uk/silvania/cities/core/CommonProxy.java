package co.uk.silvania.cities.core;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import co.uk.silvania.cities.core.blocks.*;
import co.uk.silvania.cities.core.items.*;
import co.uk.silvania.cities.core.npc.EntityBanker;
import co.uk.silvania.cities.econ.VillagerTradeHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;

public class CommonProxy {
	
	@Instance
	public static FlenixCities_Core instance;
	
    public void registerRenderThings() {}
    public void registerRenderers() {}
    public boolean banCheck() {
		return false;
	}
    
    public void entityStuff() {
    	EntityRegistry.registerGlobalEntityID(EntityBanker.class,  "Banker", EntityRegistry.findGlobalUniqueEntityId());
    	EntityRegistry.registerModEntity(EntityBanker.class, "Banker", 1, instance, 128, 1, true);
    }
    
    public void registerBlocks() {
        GameRegistry.registerBlock(CoreBlocks.atmBlock, ItemATMBlock.class, "FlenixCities" + (CoreBlocks.atmBlock.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(CoreBlocks.floatingShelvesBlock, "floatingShelvesBlock");
        
        GameRegistry.registerBlock(CoreBlocks.skyscraperBlocks, ItemSkyscraperBlocks.class, "FlenixCities" + (CoreBlocks.skyscraperBlocks.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(CoreBlocks.stainedGlass, ItemStainedGlass.class, "FlenixCities" + (CoreBlocks.stainedGlass.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(CoreBlocks.stainedGlassLit, ItemStainedGlassLit.class, "FlenixCities" + (CoreBlocks.stainedGlassLit.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(CoreBlocks.styledGlass, ItemStyledGlass.class, "FlenixCities" + (CoreBlocks.styledGlass.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(CoreBlocks.styledGlassWhite, ItemStyledGlassLit.class, "FlenixCities" + (CoreBlocks.styledGlassWhite.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(CoreBlocks.woolCeilingTile, ItemWoolCeilingTile.class, "FlenixCities" + (CoreBlocks.woolCeilingTile.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(CoreBlocks.woolStone, ItemWoolStone.class, "FlenixCities" + (CoreBlocks.woolStone.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(CoreBlocks.woolWood, ItemWoolStone.class, "FlenixCities" + (CoreBlocks.woolWood.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(CoreBlocks.plasticBlock, ItemPlasticBlocks.class, "FlenixCities" + (CoreBlocks.plasticBlock.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(CoreBlocks.floorBlocks, ItemFloorBlocks.class, "FlenixCities" + (CoreBlocks.floorBlocks.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(CoreBlocks.oreStorageBlock, ItemOreStorageBlocks.class, "FlenixCities" + (CoreBlocks.oreStorageBlock.getUnlocalizedName().substring(5)));
        
        GameRegistry.registerBlock(CoreBlocks.copperOre, "copperOre");
       	GameRegistry.registerBlock(CoreBlocks.tinOre, "tinOre");
       	GameRegistry.registerBlock(CoreBlocks.crystalOre, "crystalOre");
       	GameRegistry.registerBlock(CoreBlocks.rubyOre, "rubyOre");
       	GameRegistry.registerBlock(CoreBlocks.silverOre, "silverOre");
       	GameRegistry.registerBlock(CoreBlocks.tecmoniumOre, "tecmoniumOre");
       	GameRegistry.registerBlock(CoreBlocks.titaniumOre, "titaniumOre");
       	
       	GameRegistry.registerBlock(CoreBlocks.rebarBlock, "rebarBlock");
       	GameRegistry.registerBlock(CoreBlocks.lightingBlocks, ItemLightingBlock.class, "FlenixCities" + (CoreBlocks.lightingBlocks.getUnlocalizedName().substring(5)));
       	GameRegistry.registerBlock(CoreBlocks.lightingRotateBlocks, ItemLightingBlock.class, "FlenixCities" + (CoreBlocks.lightingRotateBlocks.getUnlocalizedName().substring(5)));
       	
       	GameRegistry.registerBlock(CoreBlocks.drywallWhite, "drywallWhite");
       	GameRegistry.registerBlock(CoreBlocks.drywallRed, "drywallRed");
       	GameRegistry.registerBlock(CoreBlocks.drywallBlue, "drywallBlue");
       	GameRegistry.registerBlock(CoreBlocks.drywallGreen, "drywallGreen");
       	GameRegistry.registerBlock(CoreBlocks.drywallGrey, "drywallGrey");
       	
       	
       	//GameRegistry.registerBlock(FlenixCities.verticalPoster1, ItemBlockPosterVertical.class, "FlenixCities" + (FlenixCities.verticalPoster1.getUnlocalizedName().substring(5)));
       	//GameRegistry.registerBlock(FlenixCities.verticalPoster2, ItemBlockPosterVertical.class, "FlenixCities" + (FlenixCities.verticalPoster2.getUnlocalizedName().substring(5)));
       	//GameRegistry.registerBlock(FlenixCities.verticalPoster3, ItemBlockPosterVertical.class, "FlenixCities" + (FlenixCities.verticalPoster3.getUnlocalizedName().substring(5)));
       	//GameRegistry.registerBlock(FlenixCities.verticalPoster4, ItemBlockPosterVertical.class, "FlenixCities" + (FlenixCities.verticalPoster4.getUnlocalizedName().substring(5)));
        
    	//GameRegistry.registerItem(CoreItems.plasticItem, "plasticItem");
    	//GameRegistry.registerItem(CoreItems.smallPCB, "smallPCB");
    	//GameRegistry.registerItem(CoreItems.largePCB, "largePCB");
    	GameRegistry.registerItem(CoreItems.rubyItem, "rubyItem");
    	GameRegistry.registerItem(CoreItems.titaniumIngot, "titaniumIngot");
    	GameRegistry.registerItem(CoreItems.tecmoniumIngot, "tecmoniumIngot");
    	GameRegistry.registerItem(CoreItems.silverIngot, "silverIngot");
    	GameRegistry.registerItem(CoreItems.copperIngot, "copperIngot");
    	GameRegistry.registerItem(CoreItems.tinIngot, "tinIngot");
    	GameRegistry.registerItem(CoreItems.crystalItem, "crystalItem");
    	GameRegistry.registerItem(CoreItems.sapphireItem, "sapphireItem");
    	
        GameRegistry.registerItem(CoreItems.coin1, "coin1");
        GameRegistry.registerItem(CoreItems.coin2, "coin2");
        GameRegistry.registerItem(CoreItems.coin5, "coin5");
        GameRegistry.registerItem(CoreItems.coin10, "coin10");
        GameRegistry.registerItem(CoreItems.coin25, "coin25");
        GameRegistry.registerItem(CoreItems.coin50, "coin50");
        GameRegistry.registerItem(CoreItems.coin100, "coin100");
        GameRegistry.registerItem(CoreItems.note100, "note100");
        GameRegistry.registerItem(CoreItems.note500, "note500");
        GameRegistry.registerItem(CoreItems.note1000, "note1000");
        GameRegistry.registerItem(CoreItems.note2000, "note2000");
        GameRegistry.registerItem(CoreItems.note5000, "note5000");
        GameRegistry.registerItem(CoreItems.note10000, "note10000");
        GameRegistry.registerItem(CoreItems.debitCard, "debitCard");

    	/*GameRegistry.registerItem(CoreItems.ringItem, "ringItem");
    	GameRegistry.registerItem(CoreItems.diamondRing, "diamondRing");
    	GameRegistry.registerItem(CoreItems.necklaceItem, "necklaceItem");
    	GameRegistry.registerItem(CoreItems.rubyNecklace, "rubyNecklace");*/
    }
    
    public void addNames() {
        LanguageRegistry.addName(CoreBlocks.copperOre, "Copper Ore");
        LanguageRegistry.addName(CoreBlocks.tinOre, "Tin Ore");
        LanguageRegistry.addName(CoreBlocks.rubyOre, "Ruby Ore");
        LanguageRegistry.addName(CoreBlocks.tecmoniumOre, "Tecmonium Ore");
        LanguageRegistry.addName(CoreBlocks.crystalOre, "Crystal Ore");
        LanguageRegistry.addName(CoreBlocks.titaniumOre, "Titanium Ore");
        LanguageRegistry.addName(CoreBlocks.silverOre, "Silver Ore");
        
    	LanguageRegistry.addName(CoreBlocks.rebarBlock, "Rebar");
    	
    	LanguageRegistry.addName(CoreBlocks.drywallWhite, "White Drywall");
    	LanguageRegistry.addName(CoreBlocks.drywallRed, "Red Drywall");
    	LanguageRegistry.addName(CoreBlocks.drywallBlue, "Blue Drywall");
    	LanguageRegistry.addName(CoreBlocks.drywallGreen, "Green Drywall");
    	LanguageRegistry.addName(CoreBlocks.drywallGrey, "Grey Drywall");
        
        LanguageRegistry.addName(CoreBlocks.floatingShelvesBlock, "Floating Shelves");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.atmBlock, 1, 0), "ATM Stone");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.atmBlock, 1, 4), "ATM Stone Brick");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.atmBlock, 1, 8), "ATM City White");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.atmBlock, 1, 12), "ATM City Grey");
        
    	LanguageRegistry.addName(CoreItems.rubyItem, "Ruby");
    	LanguageRegistry.addName(CoreItems.titaniumIngot, "Titanium Ingot");
    	LanguageRegistry.addName(CoreItems.tecmoniumIngot, "Tecmonium Ore");
    	LanguageRegistry.addName(CoreItems.silverIngot, "Silver Ingot");
    	LanguageRegistry.addName(CoreItems.copperIngot, "Copper Ingot");
    	LanguageRegistry.addName(CoreItems.tinIngot, "Tin Ingot");
    	LanguageRegistry.addName(CoreItems.crystalItem, "Crystal");
    	LanguageRegistry.addName(CoreItems.sapphireItem, "Sapphire");
    	
    	/*LanguageRegistry.addName(CoreItems.plasticItem, "Plastic");
    	LanguageRegistry.addName(CoreItems.smallPCB, "Small PCB");
    	LanguageRegistry.addName(CoreItems.largePCB, "Large PCB");
    	
    	LanguageRegistry.addName(CoreItems.ringItem, "Ring");
    	LanguageRegistry.addName(CoreItems.diamondRing, "Diamond Ring");
    	LanguageRegistry.addName(CoreItems.necklaceItem, "Necklace");
    	LanguageRegistry.addName(CoreItems.rubyNecklace, "Ruby Necklace");*/

        LanguageRegistry.addName(CoreItems.coin1, "1 " + CityConfig.currencySmall);
        LanguageRegistry.addName(CoreItems.coin2, "2 " + CityConfig.currencySmallPlural);
        LanguageRegistry.addName(CoreItems.coin5, "5 " + CityConfig.currencySmallPlural);
        LanguageRegistry.addName(CoreItems.coin10, "10 " + CityConfig.currencySmallPlural);
        LanguageRegistry.addName(CoreItems.coin25, "25 " + CityConfig.currencySmallPlural);
        LanguageRegistry.addName(CoreItems.coin50, "50 " + CityConfig.currencySmallPlural);
        LanguageRegistry.addName(CoreItems.coin100, "1 " + CityConfig.currencyLarge + " Coin");
        LanguageRegistry.addName(CoreItems.note100, "1 " + CityConfig.currencyLarge);
        LanguageRegistry.addName(CoreItems.note200, "2 " + CityConfig.currencyLargePlural);
        LanguageRegistry.addName(CoreItems.note500, "5 " + CityConfig.currencyLargePlural);
        LanguageRegistry.addName(CoreItems.note1000, "10 " + CityConfig.currencyLargePlural);
        LanguageRegistry.addName(CoreItems.note2000, "20 " + CityConfig.currencyLargePlural);
        LanguageRegistry.addName(CoreItems.note5000, "50 " + CityConfig.currencyLargePlural);
        LanguageRegistry.addName(CoreItems.note10000, "100 " + CityConfig.currencyLargePlural);
        LanguageRegistry.addName(CoreItems.debitCard, "Debit Card");
        
        LanguageRegistry.addName(new ItemStack(CoreBlocks.skyscraperBlocks, 1, 0), "White Skyscraper Block");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.skyscraperBlocks, 1, 1), "Light Grey Skyscraper Block");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.skyscraperBlocks, 1, 2), "Dark Grey Skyscraper Block");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.skyscraperBlocks, 1, 3), "Black Skyscraper Block");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.skyscraperBlocks, 1, 4), "White Skyscraper Block (Framed)");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.skyscraperBlocks, 1, 5), "Light Grey Skyscraper Block (Framed)");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.skyscraperBlocks, 1, 6), "Dark Grey Skyscraper Block (Framed)");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.skyscraperBlocks, 1, 7), "Black Skyscraper Block (Framed)");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.skyscraperBlocks, 1, 8), "White Skyscraper Block (Framed)");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.skyscraperBlocks, 1, 9), "Light Grey Skyscraper Block (Framed)");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.skyscraperBlocks, 1, 10), "Dark Grey Skyscraper Block (Framed)");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.skyscraperBlocks, 1, 11), "Black Skyscraper Block (Framed)");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.skyscraperBlocks, 1, 12), "White Skyscraper Block (Framed)");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.skyscraperBlocks, 1, 13), "Light Grey Skyscraper Block (Framed)");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.skyscraperBlocks, 1, 14), "Dark Grey Skyscraper Block (Framed)");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.skyscraperBlocks, 1, 15), "Black Skyscraper Block (Framed)");
        
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlass, 1, 0), "White Stained Glass");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlass, 1, 1), "Orange Stained Glass");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlass, 1, 2), "Magenta Stained Glass");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlass, 1, 3), "Light Blue Stained Glass");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlass, 1, 4), "Yellow Stained Glass");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlass, 1, 5), "Lime Stained Glass");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlass, 1, 6), "Pink Stained Glass");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlass, 1, 7), "Grey Stained Glass");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlass, 1, 8), "Light Grey Stained Glass");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlass, 1, 9), "Cyan Stained Glass");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlass, 1, 10), "Purple Stained Glass");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlass, 1, 11), "Blue Stained Glass");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlass, 1, 12), "Brown Stained Glass");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlass, 1, 13), "Green Stained Glass");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlass, 1, 14), "Red Stained Glass");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlass, 1, 15), "Black Stained Glass");
        
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlassLit, 1, 0), "White Stained Glass (Lit)");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlassLit, 1, 1), "Orange Stained Glass (Lit)");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlassLit, 1, 2), "Magenta Stained Glass (Lit)");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlassLit, 1, 3), "Light Blue Stained Glass (Lit)");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlassLit, 1, 4), "Yellow Stained Glass (Lit)");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlassLit, 1, 5), "Lime Stained Glass (Lit)");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlassLit, 1, 6), "Pink Stained Glass (Lit)");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlassLit, 1, 7), "Grey Stained Glass (Lit)");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlassLit, 1, 8), "Light Grey Stained Glass (Lit)");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlassLit, 1, 9), "Cyan Stained Glass (Lit)");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlassLit, 1, 10), "Purple Stained Glass (Lit)");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlassLit, 1, 11), "Blue Stained Glass (Lit)");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlassLit, 1, 12), "Brown Stained Glass (Lit)");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlassLit, 1, 13), "Green Stained Glass (Lit)");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlassLit, 1, 14), "Red Stained Glass (Lit)");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.stainedGlassLit, 1, 15), "Black Stained Glass (Lit)");
        
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlass, 1, 0), "Blue White-framed Glass");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlass, 1, 1), "Blue Horizontal Forked Glass");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlass, 1, 2), "Blue Vertical Forked Glass");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlass, 1, 3), "Blue Framed Aligned Cross");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlass, 1, 4), "Blue Aligned Cross");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlass, 1, 5), "Blue Framed Horizontal Line");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlass, 1, 6), "Blue Horizontal Line");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlass, 1, 7), "Blue Framed Vertical Line");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlass, 1, 8), "blue Vertical Line");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlass, 1, 9), "Blue Small Squares");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlass, 1, 10), "Blue Cross");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlass, 1, 11), "Blue Supercross");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlass, 1, 12), "Suggest!");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlass, 1, 13), "Suggest!");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlass, 1, 14), "Suggest!");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlass, 1, 15), "Suggest!");
        
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlassWhite, 1, 0), "Clear White-framed Glass");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlassWhite, 1, 1), "Clear Horizontal Forked Glass");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlassWhite, 1, 2), "Clear Vertical Forked Glass");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlassWhite, 1, 3), "Clear Framed Aligned Cross");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlassWhite, 1, 4), "Clear Aligned Cross");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlassWhite, 1, 5), "Clear Framed Horizontal Line");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlassWhite, 1, 6), "Clear Horizontal Line");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlassWhite, 1, 7), "Clear Framed Vertical Line");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlassWhite, 1, 8), "Clear Vertical Line");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlassWhite, 1, 9), "Clear Small Squares");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlassWhite, 1, 10), "Clear Cross");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlassWhite, 1, 11), "Clear Supercross");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlassWhite, 1, 12), "Suggest!");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlassWhite, 1, 13), "Suggest!");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlassWhite, 1, 14), "Suggest!");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.styledGlassWhite, 1, 15), "Suggest!");
        
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolCeilingTile, 1, 0), "White Wool-Topped Ceiling Tile");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolCeilingTile, 1, 1), "Orange Wool-Topped Ceiling Tile");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolCeilingTile, 1, 2), "Magenta Wool-Topped Ceiling Tile");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolCeilingTile, 1, 3), "Light Blue Wool-Topped Ceiling Tile");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolCeilingTile, 1, 4), "Yellow Wool-Topped Ceiling Tile");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolCeilingTile, 1, 5), "Lime Wool-Topped Ceiling Tile");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolCeilingTile, 1, 6), "Pink Wool-Topped Ceiling Tile");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolCeilingTile, 1, 7), "Grey Wool-Topped Ceiling Tile");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolCeilingTile, 1, 8), "Light Grey Wool-Topped Ceiling Tile");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolCeilingTile, 1, 9), "Cyan Wool-Topped Ceiling Tile");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolCeilingTile, 1, 10), "Purple Wool-Topped Ceiling Tile");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolCeilingTile, 1, 11), "Blue Wool-Topped Ceiling Tile");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolCeilingTile, 1, 12), "Brown Wool-Topped Ceiling Tile");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolCeilingTile, 1, 13), "Green Wool-Topped Ceiling Tile");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolCeilingTile, 1, 14), "Red Wool-Topped Ceiling Tile");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolCeilingTile, 1, 15), "Black Wool-Topped Ceiling Tile");
        
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolStone, 1, 0), "White Wool-Topped Stone");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolStone, 1, 1), "Orange Wool-Topped Stone");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolStone, 1, 2), "Magenta Wool-Topped Stone");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolStone, 1, 3), "Light Blue Wool-Topped Stone");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolStone, 1, 4), "Yellow Wool-Topped Stone");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolStone, 1, 5), "Lime Wool-Topped Stone");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolStone, 1, 6), "Pink Wool-Topped Stone");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolStone, 1, 7), "Grey Wool-Topped Stone");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolStone, 1, 8), "Light Grey Wool-Topped Stone");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolStone, 1, 9), "Cyan Wool-Topped Stone");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolStone, 1, 10), "Purple Wool-Topped Stone");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolStone, 1, 11), "Blue Wool-Topped Stone");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolStone, 1, 12), "Brown Wool-Topped Stone");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolStone, 1, 13), "Green Wool-Topped Stone");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolStone, 1, 14), "Red Wool-Topped Stone");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolStone, 1, 15), "Black Wool-Topped Stone");
        
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolWood, 1, 0), "White Wool-Topped Wood");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolWood, 1, 1), "Orange Wool-Topped Wood");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolWood, 1, 2), "Magenta Wool-Topped Wood");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolWood, 1, 3), "Light Blue Wool-Topped Wood");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolWood, 1, 4), "Yellow Wool-Topped Wood");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolWood, 1, 5), "Lime Wool-Topped Wood");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolWood, 1, 6), "Pink Wool-Topped Wood");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolWood, 1, 7), "Grey Wool-Topped Wood");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolWood, 1, 8), "Light Grey Wool-Topped Wood");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolWood, 1, 9), "Cyan Wool-Topped Wood");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolWood, 1, 10), "Purple Wool-Topped Wood");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolWood, 1, 11), "Blue Wool-Topped Wood");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolWood, 1, 12), "Brown Wool-Topped Wood");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolWood, 1, 13), "Green Wool-Topped Wood");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolWood, 1, 14), "Red Wool-Topped Wood");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.woolWood, 1, 15), "Black Wool-Topped Wood");
        
        LanguageRegistry.addName(new ItemStack(CoreBlocks.plasticBlock, 1, 0), "White Plastic Block");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.plasticBlock, 1, 1), "Orange Plastic Block");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.plasticBlock, 1, 2), "Magenta Plastic Block");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.plasticBlock, 1, 3), "Light Blue Plastic Block");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.plasticBlock, 1, 4), "Yellow Plastic Block");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.plasticBlock, 1, 5), "Lime Plastic Block");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.plasticBlock, 1, 6), "Pink Plastic Block");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.plasticBlock, 1, 7), "Grey Plastic Block");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.plasticBlock, 1, 8), "Light Grey Plastic Block");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.plasticBlock, 1, 9), "Cyan Plastic Block");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.plasticBlock, 1, 10), "Purple Plastic Block");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.plasticBlock, 1, 11), "Blue Plastic Block");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.plasticBlock, 1, 12), "Brown Plastic Block");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.plasticBlock, 1, 13), "Green Plastic Block");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.plasticBlock, 1, 14), "Red Plastic Block");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.plasticBlock, 1, 15), "Black Plastic Block");
        
        LanguageRegistry.addName(new ItemStack(CoreBlocks.floorBlocks, 1, 0), "Bathroom Tiles");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.floorBlocks, 1, 1), "Kitchen Tiles");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.floorBlocks, 1, 2), "Generic Tiles 1");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.floorBlocks, 1, 3), "Generic Tiles 2");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.floorBlocks, 1, 4), "Oak Wood Laminate");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.floorBlocks, 1, 5), "Spruce Wood Laminate");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.floorBlocks, 1, 6), "Birch Wood Laminate");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.floorBlocks, 1, 7), "Jungle Wood Laminate");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.floorBlocks, 1, 8), "");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.floorBlocks, 1, 9), "");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.floorBlocks, 1, 10), "");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.floorBlocks, 1, 11), "");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.floorBlocks, 1, 12), "");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.floorBlocks, 1, 13), "");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.floorBlocks, 1, 14), "");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.floorBlocks, 1, 15), "");
        
        LanguageRegistry.addName(new ItemStack(CoreBlocks.lightingBlocks, 1, 1), "Light Panel");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.lightingBlocks, 1, 2), "Ceiling Light");
        
        LanguageRegistry.addName(new ItemStack(CoreBlocks.lightingRotateBlocks, 1, 0), "Vertical Light Strip");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.lightingRotateBlocks, 1, 4), "Vertical Light Strip (Short)");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.lightingRotateBlocks, 1, 8), "Vertical Light Panel");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.lightingRotateBlocks, 1, 12), "Vertical Light Panel (Small)");
        
        LanguageRegistry.addName(new ItemStack(CoreBlocks.oreStorageBlock, 1, 0), "Copper Block");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.oreStorageBlock, 1, 1), "Tin Block");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.oreStorageBlock, 1, 2), "Ruby Block");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.oreStorageBlock, 1, 3), "Sapphire Block");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.oreStorageBlock, 1, 4), "Tecmonium Block");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.oreStorageBlock, 1, 5), "Crystal Block");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.oreStorageBlock, 1, 6), "Silver Block");
        LanguageRegistry.addName(new ItemStack(CoreBlocks.oreStorageBlock, 1, 7), "Titanium Block");
        
        /*LanguageRegistry.addName(new ItemStack(FlenixCities.verticalPoster1, 1, 0), "Silvania VA");
        LanguageRegistry.addName(new ItemStack(FlenixCities.verticalPoster1, 1, 4), "Remula");
        LanguageRegistry.addName(new ItemStack(FlenixCities.verticalPoster1, 1, 8), "9");
        LanguageRegistry.addName(new ItemStack(FlenixCities.verticalPoster1, 1, 12), "13");
        
        LanguageRegistry.addName(new ItemStack(FlenixCities.verticalPoster2, 1, 0), "17");
        LanguageRegistry.addName(new ItemStack(FlenixCities.verticalPoster2, 1, 4), "21");
        LanguageRegistry.addName(new ItemStack(FlenixCities.verticalPoster2, 1, 8), "25");
        LanguageRegistry.addName(new ItemStack(FlenixCities.verticalPoster2, 1, 12), "29");
        
        LanguageRegistry.addName(new ItemStack(FlenixCities.verticalPoster3, 1, 0), "DoanVPS Poster");
        LanguageRegistry.addName(new ItemStack(FlenixCities.verticalPoster3, 1, 4), "Ad Slot 2");
        LanguageRegistry.addName(new ItemStack(FlenixCities.verticalPoster3, 1, 8), "Ad Slot 3");
        LanguageRegistry.addName(new ItemStack(FlenixCities.verticalPoster3, 1, 12), "Ad Slot 4");
        
        LanguageRegistry.addName(new ItemStack(FlenixCities.verticalPoster4, 1, 0), "Ad Slot 5");
        LanguageRegistry.addName(new ItemStack(FlenixCities.verticalPoster4, 1, 4), "Ad Slot 6");
        LanguageRegistry.addName(new ItemStack(FlenixCities.verticalPoster4, 1, 8), "Ad Slot 7");
        LanguageRegistry.addName(new ItemStack(FlenixCities.verticalPoster4, 1, 12), "Ad Slot 8");*/
    }
    
    public void setVillagerTrades() {
    	VillagerRegistry.instance().registerVillageTradeHandler(3, new VillagerTradeHandler());
    }
    
    public void addRecipes() {
    	ItemStack copperIngot = new ItemStack(CoreItems.copperIngot);
    	ItemStack tinIngot = new ItemStack(CoreItems.tinIngot);
    	ItemStack silverIngot = new ItemStack(CoreItems.silverIngot);
    	ItemStack titaniumIngot = new ItemStack(CoreItems.titaniumIngot);
    	ItemStack tecmoniumIngot = new ItemStack(CoreItems.tecmoniumIngot);
    	ItemStack crystalItem = new ItemStack(CoreItems.crystalItem);
    	ItemStack rubyItem = new ItemStack(CoreItems.rubyItem);
    	ItemStack sapphireItem = new ItemStack(CoreItems.sapphireItem);
    	
    	ItemStack copperOre = new ItemStack(CoreBlocks.copperOre);
    	ItemStack tinOre = new ItemStack(CoreBlocks.tinOre);
    	ItemStack silverOre = new ItemStack(CoreBlocks.silverOre);
    	ItemStack titaniumOre = new ItemStack(CoreBlocks.titaniumOre);
    	ItemStack tecmoniumOre = new ItemStack(CoreBlocks.tecmoniumOre);
    	ItemStack crystalOre = new ItemStack(CoreBlocks.crystalOre);
    	ItemStack rubyOre = new ItemStack(CoreBlocks.rubyOre);
    	ItemStack sapphireOre = new ItemStack(CoreBlocks.sapphireOre);
    	
    	ItemStack copperBlock = new ItemStack(CoreBlocks.oreStorageBlock, 1, 0);
    	ItemStack tinBlock = new ItemStack(CoreBlocks.oreStorageBlock, 1, 1);
    	ItemStack rubyBlock = new ItemStack(CoreBlocks.oreStorageBlock, 1, 2);
    	ItemStack sapphireBlock = new ItemStack(CoreBlocks.oreStorageBlock, 1, 3);
    	ItemStack tecmoniumBlock = new ItemStack(CoreBlocks.oreStorageBlock, 1, 4);
    	ItemStack crystalBlock = new ItemStack(CoreBlocks.oreStorageBlock, 1, 5);
    	ItemStack silverBlock = new ItemStack(CoreBlocks.oreStorageBlock, 1, 6);
    	ItemStack titaniumBlock = new ItemStack(CoreBlocks.oreStorageBlock, 1, 7);
    	
    	
    	ItemStack stoneBlock = new ItemStack(Block.stone);
    	ItemStack quartzItem = new ItemStack(Item.netherQuartz);
    	ItemStack skyscraperAny = new ItemStack(CoreBlocks.skyscraperBlocks);
    	ItemStack skyscraperWhite = new ItemStack(CoreBlocks.skyscraperBlocks, 1, 0);
    	ItemStack skyscraperLightGrey = new ItemStack(CoreBlocks.skyscraperBlocks, 1, 1);
    	ItemStack skyscraperDarkGrey = new ItemStack(CoreBlocks.skyscraperBlocks, 1, 2);
    	ItemStack skyscraperBlack = new ItemStack(CoreBlocks.skyscraperBlocks, 1, 3);
    	ItemStack rebarBlock = new ItemStack(CoreBlocks.rebarBlock);
    	
    	ItemStack blackDye = new ItemStack(Item.dyePowder, 1, 0);
    	ItemStack redDye = new ItemStack(Item.dyePowder, 1, 1);
    	ItemStack greenDye = new ItemStack(Item.dyePowder, 1, 2);
    	ItemStack brownDye = new ItemStack(Item.dyePowder, 1, 3);
    	ItemStack blueDye = new ItemStack(Item.dyePowder, 1, 4);
    	ItemStack purpleDye = new ItemStack(Item.dyePowder, 1, 5);
    	ItemStack tealDye = new ItemStack(Item.dyePowder, 1, 6);
    	ItemStack lightGreyDye = new ItemStack(Item.dyePowder, 1, 7);
    	ItemStack darkGreyDye = new ItemStack(Item.dyePowder, 1, 8);
    	ItemStack pinkDye = new ItemStack(Item.dyePowder, 1, 9);
    	ItemStack limeGreenDye = new ItemStack(Item.dyePowder, 1, 10);
    	ItemStack yellowDye = new ItemStack(Item.dyePowder, 1, 11);
    	ItemStack lightBlueDye = new ItemStack(Item.dyePowder, 1, 12);
    	ItemStack magentaDye = new ItemStack(Item.dyePowder, 1, 13);
    	ItemStack orangeDye = new ItemStack(Item.dyePowder, 1, 14);
    	ItemStack whiteDye = new ItemStack(Item.dyePowder, 1, 15);    	
    	
    	ItemStack blackWool = new ItemStack(Block.cloth, 1, 15);
    	ItemStack redWool = new ItemStack(Block.cloth, 1, 14);
    	ItemStack greenWool = new ItemStack(Block.cloth, 1, 13);
    	ItemStack brownWool = new ItemStack(Block.cloth, 1, 12);
    	ItemStack blueWool = new ItemStack(Block.cloth, 1, 11);
    	ItemStack purpleWool = new ItemStack(Block.cloth, 1, 10);
    	ItemStack tealWool = new ItemStack(Block.cloth, 1, 9);
    	ItemStack lightGreyWool = new ItemStack(Block.cloth, 1, 8);
    	ItemStack darkGreyWool = new ItemStack(Block.cloth, 1, 7);
    	ItemStack pinkWool = new ItemStack(Block.cloth, 1, 6);
    	ItemStack limeGreenWool = new ItemStack(Block.cloth, 1, 5);
    	ItemStack yellowWool = new ItemStack(Block.cloth, 1, 4);
    	ItemStack lightBlueWool = new ItemStack(Block.cloth, 1, 3);
    	ItemStack magentaWool = new ItemStack(Block.cloth, 1, 2);
    	ItemStack orangeWool = new ItemStack(Block.cloth, 1, 1);
    	ItemStack whiteWool = new ItemStack(Block.cloth, 1, 0); 
    	
    	ItemStack ceilingTile = new ItemStack(CoreBlocks.floorBlocks, 1, 9);
    	ItemStack woodBlock = new ItemStack(Block.planks);
    	
    	GameRegistry.addRecipe(copperBlock, "iii", "iii", "iii", 'i', copperIngot);
    	GameRegistry.addRecipe(tinBlock, "iii", "iii", "iii", 'i', tinIngot);
    	GameRegistry.addRecipe(rubyBlock, "iii", "iii", "iii", 'i', rubyItem);
    	GameRegistry.addRecipe(sapphireBlock, "iii", "iii", "iii", 'i', sapphireItem);
    	GameRegistry.addRecipe(tecmoniumBlock, "iii", "iii", "iii", 'i', tecmoniumIngot);
    	GameRegistry.addRecipe(crystalBlock, "iii", "iii", "iii", 'i', crystalItem);
    	GameRegistry.addRecipe(silverBlock, "iii", "iii", "iii", 'i', silverIngot);
    	GameRegistry.addRecipe(titaniumBlock, "iii", "iii", "iii", 'i', titaniumIngot);
    	
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.rebarBlock, 9, 0), " t ", " t ", " t ", 't', titaniumIngot);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.floorBlocks, 1, 9), stoneBlock, quartzItem);
    	
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 3, 4), " s ", "dsd", " s ", 's', skyscraperWhite, 'd', blackDye);
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 3, 5), " s ", "dsd", " s ", 's', skyscraperLightGrey, 'd', blackDye);
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 3, 6), " s ", "dsd", " s ", 's', skyscraperDarkGrey, 'd', blackDye);
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 3, 7), " s ", "dsd", " s ", 's', skyscraperBlack, 'd', blackDye);
    	
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 3, 8), " d ", "sss", " d ", 's', skyscraperWhite, 'd', blackDye);
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 3, 9), " d ", "sss", " d ", 's', skyscraperLightGrey, 'd', blackDye);
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 3, 10), " d ", "sss", " d ", 's', skyscraperDarkGrey, 'd', blackDye);
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 3, 11), " d ", "sss", " d ", 's', skyscraperBlack, 'd', blackDye);
    	
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 1, 12), " d ", "dsd", " d ", 's', skyscraperWhite, 'd', blackDye);
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 1, 13), " d ", "dsd", " d ", 's', skyscraperLightGrey, 'd', blackDye);
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 1, 14), " d ", "dsd", " d ", 's', skyscraperDarkGrey, 'd', blackDye);
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 1, 15), " d ", "dsd", " d ", 's', skyscraperBlack, 'd', blackDye);
    	
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreItems.copperIngot, 9, 0), copperBlock);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreItems.tinIngot, 9, 0), tinBlock);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreItems.rubyItem, 9, 0), rubyBlock);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreItems.sapphireItem, 9, 0), sapphireBlock);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreItems.tecmoniumIngot, 9, 0), tecmoniumBlock);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreItems.crystalItem, 9, 0), crystalBlock);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreItems.silverIngot, 9, 0), silverBlock);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreItems.titaniumIngot, 9, 0), titaniumBlock);
    	
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.copperOre), copperOre);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.tinOre), tinOre);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.silverOre), silverOre);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.titaniumOre), titaniumOre);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.tecmoniumOre), tecmoniumOre);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.crystalOre), crystalOre);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.rubyOre), rubyOre);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.sapphireOre), sapphireOre);
    	
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 2, 0), stoneBlock, rebarBlock);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 1, 0), skyscraperAny, whiteDye);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 1, 1), skyscraperAny, lightGreyDye);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 1, 2), skyscraperAny, darkGreyDye);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 1, 3), skyscraperAny, blackDye);
    	
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 0), ceilingTile, whiteWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 1), ceilingTile, orangeWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 2), ceilingTile, magentaWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 3), ceilingTile, lightBlueWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 4), ceilingTile, yellowWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 5), ceilingTile, limeGreenWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 6), ceilingTile, pinkWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 7), ceilingTile, darkGreyWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 8), ceilingTile, lightGreyWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 9), ceilingTile, tealWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 10), ceilingTile, purpleWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 11), ceilingTile, blueWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 12), ceilingTile, brownWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 13), ceilingTile, greenWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 14), ceilingTile, redWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 15), ceilingTile, blackWool);
    	
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolStone, 1, 0), stoneBlock, whiteWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolStone, 1, 1), stoneBlock, orangeWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolStone, 1, 2), stoneBlock, magentaWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolStone, 1, 3), stoneBlock, lightBlueWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolStone, 1, 4), stoneBlock, yellowWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolStone, 1, 5), stoneBlock, limeGreenWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolStone, 1, 6), stoneBlock, pinkWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolStone, 1, 7), stoneBlock, darkGreyWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolStone, 1, 8), stoneBlock, lightGreyWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolStone, 1, 9), stoneBlock, tealWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolStone, 1, 10), stoneBlock, purpleWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolStone, 1, 11), stoneBlock, blueWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolStone, 1, 12), stoneBlock, brownWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolStone, 1, 13), stoneBlock, greenWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolStone, 1, 14), stoneBlock, redWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolStone, 1, 15), stoneBlock, blackWool);
    	
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolWood, 1, 0), woodBlock, whiteWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolWood, 1, 1), woodBlock, orangeWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolWood, 1, 2), woodBlock, magentaWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolWood, 1, 3), woodBlock, lightBlueWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolWood, 1, 4), woodBlock, yellowWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolWood, 1, 5), woodBlock, limeGreenWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolWood, 1, 6), woodBlock, pinkWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolWood, 1, 7), woodBlock, darkGreyWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolWood, 1, 8), woodBlock, lightGreyWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolWood, 1, 9), woodBlock, tealWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolWood, 1, 10), woodBlock, purpleWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolWood, 1, 11), woodBlock, blueWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolWood, 1, 12), woodBlock, brownWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolWood, 1, 13), woodBlock, greenWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolWood, 1, 14), woodBlock, redWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolWood, 1, 15), woodBlock, blackWool);
    	
    	GameRegistry.addSmelting(CoreBlocks.copperOre.blockID, copperIngot, 0.5F);
    	GameRegistry.addSmelting(CoreBlocks.tinOre.blockID, tinIngot, 0.5F);
    	GameRegistry.addSmelting(CoreBlocks.silverOre.blockID, silverIngot, 0.8F);
    	GameRegistry.addSmelting(CoreBlocks.titaniumOre.blockID, titaniumIngot, 1.0F);
    	GameRegistry.addSmelting(CoreBlocks.tecmoniumOre.blockID, tecmoniumIngot, 1.2F);
    }

}
