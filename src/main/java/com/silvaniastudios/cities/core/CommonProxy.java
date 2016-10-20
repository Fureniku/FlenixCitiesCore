package com.silvaniastudios.cities.core;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

import com.silvaniastudios.cities.core.items.*;
import com.silvaniastudios.cities.core.npc.EntityBanker;

import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class CommonProxy {
	
	@Instance
	public static FlenixCities_Core instance;
	
    public void entityStuff() {
    	EntityRegistry.registerModEntity(EntityBanker.class, "Banker", 1, FlenixCities_Core.instance, 32, 5, true);
    }
    
    public void registerBlocks() {
        GameRegistry.registerBlock(CoreBlocks.atmBlock, ItemATMBlock.class, "FlenixCities" + (CoreBlocks.atmBlock.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(CoreBlocks.floatingShelvesBlock, "floatingShelvesBlock");
        GameRegistry.registerBlock(CoreBlocks.npcSpawnerBlock, "npcSpawnerBlock");
        GameRegistry.registerBlock(CoreBlocks.stockChest, "stockChest");
        
        GameRegistry.registerBlock(CoreBlocks.skyscraperBlocks, ItemSkyscraperBlocks.class, "FlenixCities" + (CoreBlocks.skyscraperBlocks.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(CoreBlocks.skyscraperBlocks2, ItemSkyscraperBlocks.class, "FlenixCities" + (CoreBlocks.skyscraperBlocks2.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(CoreBlocks.skyscraperBlocks3, ItemSkyscraperBlocks.class, "FlenixCities" + (CoreBlocks.skyscraperBlocks3.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(CoreBlocks.skyscraperBlocks4, ItemSkyscraperBlocks.class, "FlenixCities" + (CoreBlocks.skyscraperBlocks4.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(CoreBlocks.skyscraperBlocks5, ItemSkyscraperBlocks.class, "FlenixCities" + (CoreBlocks.skyscraperBlocks5.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(CoreBlocks.skyscraperBlocks6, ItemSkyscraperBlocks.class, "FlenixCities" + (CoreBlocks.skyscraperBlocks6.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(CoreBlocks.skyscraperBlocks7, ItemSkyscraperBlocks.class, "FlenixCities" + (CoreBlocks.skyscraperBlocks7.getUnlocalizedName().substring(5)));
        
        GameRegistry.registerBlock(CoreBlocks.stainedGlass, ItemStainedGlass.class, "FlenixCities" + (CoreBlocks.stainedGlass.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(CoreBlocks.stainedGlassLit, ItemStainedGlassLit.class, "FlenixCities" + (CoreBlocks.stainedGlassLit.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(CoreBlocks.styledGlass, ItemStyledGlass.class, "FlenixCities" + (CoreBlocks.styledGlass.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(CoreBlocks.styledGlassWhite, ItemStyledGlassLit.class, "FlenixCities" + (CoreBlocks.styledGlassWhite.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(CoreBlocks.woolCeilingTile, ItemWoolCeilingTile.class, "FlenixCities" + (CoreBlocks.woolCeilingTile.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(CoreBlocks.woolStone, ItemWoolStone.class, "FlenixCities" + (CoreBlocks.woolStone.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(CoreBlocks.woolWood, ItemWoolStone.class, "FlenixCities" + (CoreBlocks.woolWood.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(CoreBlocks.plasticBlock, ItemPlasticBlocks.class, "FlenixCities" + (CoreBlocks.plasticBlock.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(CoreBlocks.floorBlocks, ItemFloorBlocks.class, "FlenixCities" + (CoreBlocks.floorBlocks.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(CoreBlocks.floorBlocks2, ItemFloorBlocks.class, "FlenixCities" + (CoreBlocks.floorBlocks2.getUnlocalizedName().substring(5)));
       	
        GameRegistry.registerBlock(CoreBlocks.oakWalkway, "oakWalkway");
        GameRegistry.registerBlock(CoreBlocks.spruceWalkway, "spruceWalkway");
        GameRegistry.registerBlock(CoreBlocks.birchWalkway, "birchWalkway");
        GameRegistry.registerBlock(CoreBlocks.jungleWalkway, "jungleWalkway");
        GameRegistry.registerBlock(CoreBlocks.acaciaWalkway, "acaciaWalkway");
        GameRegistry.registerBlock(CoreBlocks.darkOakWalkway, "darkOakWalkway");
        
        GameRegistry.registerBlock(CoreBlocks.whiteWalkway, "whiteWalkway");
        GameRegistry.registerBlock(CoreBlocks.lightGreyWalkway, "lightGreyWalkway");
        GameRegistry.registerBlock(CoreBlocks.darkGreyWalkway, "darkGreyWalkway");
        GameRegistry.registerBlock(CoreBlocks.blackWalkway, "blackWalkway");
        
        GameRegistry.registerBlock(CoreBlocks.lightGreenWalkway, "lightGreenWalkway");
        GameRegistry.registerBlock(CoreBlocks.cyanWalkway, "cyanWalkway");
        GameRegistry.registerBlock(CoreBlocks.purpleWalkway, "purpleWalkway");
        GameRegistry.registerBlock(CoreBlocks.pinkWalkway, "pinkWalkway");
        
        GameRegistry.registerBlock(CoreBlocks.blueWalkway, "blueWalkway");
        GameRegistry.registerBlock(CoreBlocks.brownWalkway, "brownWalkway");
        GameRegistry.registerBlock(CoreBlocks.greenWalkway, "greenWalkway");
        GameRegistry.registerBlock(CoreBlocks.redWalkway, "redWalkway");
        
        GameRegistry.registerBlock(CoreBlocks.orangeWalkway, "orangeWalkway");
        GameRegistry.registerBlock(CoreBlocks.magentaWalkway, "magentaWalkway");
        GameRegistry.registerBlock(CoreBlocks.lightBlueWalkway, "lightBlueWalkway");
        GameRegistry.registerBlock(CoreBlocks.yellowWalkway, "yellowWalkway");
        
        GameRegistry.registerBlock(CoreBlocks.pastelDarkBlueWalkway, "pastelDarkBlueWalkway");
    	GameRegistry.registerBlock(CoreBlocks.pastelBrownWalkway, "pastelBrownWalkway");
    	GameRegistry.registerBlock(CoreBlocks.pastelGreenWalkway, "pastelGreenWalkway");
    	GameRegistry.registerBlock(CoreBlocks.pastelPinkWalkway, "pastelPinkWalkway");
    	
    	GameRegistry.registerBlock(CoreBlocks.pastelOrangeWalkway, "pastelOrangeWalkway");
    	GameRegistry.registerBlock(CoreBlocks.pastelMagentaWalkway, "pastelMagentaWalkway");
    	GameRegistry.registerBlock(CoreBlocks.pastelRedWalkway, "pastelRedWalkway");
    	GameRegistry.registerBlock(CoreBlocks.pastelYellowWalkway, "pastelYellowWalkway");
    	
    	GameRegistry.registerBlock(CoreBlocks.pastelLightBlueWalkway, "pastelLightBlueWalkway");
    	GameRegistry.registerBlock(CoreBlocks.pastelLightGreenWalkway, "pastelLightGreenWalkway");
    	GameRegistry.registerBlock(CoreBlocks.pastelCyanWalkway, "pastelCyanWalkway");
    	GameRegistry.registerBlock(CoreBlocks.pastelPurpleWalkway, "pastelPurpleWalkway");
        
        GameRegistry.registerBlock(CoreBlocks.oakLaminateWalkway, "oakLaminateWalkway");
        GameRegistry.registerBlock(CoreBlocks.spruceLaminateWalkway, "spruceLaminateWalkway");
        GameRegistry.registerBlock(CoreBlocks.birchLaminateWalkway, "birchLaminateWalkway");
        GameRegistry.registerBlock(CoreBlocks.jungleLaminateWalkway, "jungleLaminateWalkway");
        GameRegistry.registerBlock(CoreBlocks.acaciaLaminateWalkway, "acaciaLaminateWalkway");
        GameRegistry.registerBlock(CoreBlocks.darkOakLaminateWalkway, "darkOakLaminateWalkway");
        
        GameRegistry.registerBlock(CoreBlocks.oakWalkwayStairs, "oakWalkwayStairs");
        GameRegistry.registerBlock(CoreBlocks.spruceWalkwayStairs, "spruceWalkwayStairs");
        GameRegistry.registerBlock(CoreBlocks.birchWalkwayStairs, "birchWalkwayStairs");
        GameRegistry.registerBlock(CoreBlocks.jungleWalkwayStairs, "jungleWalkwayStairs");
        GameRegistry.registerBlock(CoreBlocks.acaciaWalkwayStairs, "acaciaWalkwayStairs");
        GameRegistry.registerBlock(CoreBlocks.darkOakWalkwayStairs, "darkOakWalkwayStairs");
        
        GameRegistry.registerBlock(CoreBlocks.whiteWalkwayStairs, "whiteWalkwayStairs");
        GameRegistry.registerBlock(CoreBlocks.lightGreyWalkwayStairs, "lightGreyWalkwayStairs");
        GameRegistry.registerBlock(CoreBlocks.darkGreyWalkwayStairs, "darkGreyWalkwayStairs");
        GameRegistry.registerBlock(CoreBlocks.blackWalkwayStairs, "blackWalkwayStairs");
        
        GameRegistry.registerBlock(CoreBlocks.lightGreenWalkwayStairs, "lightGreenWalkwayStairs");
        GameRegistry.registerBlock(CoreBlocks.cyanWalkwayStairs, "cyanWalkwayStairs");
        GameRegistry.registerBlock(CoreBlocks.purpleWalkwayStairs, "purpleWalkwayStairs");
        GameRegistry.registerBlock(CoreBlocks.pinkWalkwayStairs, "pinkWalkwayStairs");
        
        GameRegistry.registerBlock(CoreBlocks.blueWalkwayStairs, "blueWalkwayStairs");
        GameRegistry.registerBlock(CoreBlocks.brownWalkwayStairs, "brownWalkwayStairs");
        GameRegistry.registerBlock(CoreBlocks.greenWalkwayStairs, "greenWalkwayStairs");
        GameRegistry.registerBlock(CoreBlocks.redWalkwayStairs, "redWalkwayStairs");
        
        GameRegistry.registerBlock(CoreBlocks.orangeWalkwayStairs, "orangeWalkwayStairs");
        GameRegistry.registerBlock(CoreBlocks.magentaWalkwayStairs, "magentaWalkwayStairs");
        GameRegistry.registerBlock(CoreBlocks.lightBlueWalkwayStairs, "lightBlueWalkwayStairs");
        GameRegistry.registerBlock(CoreBlocks.yellowWalkwayStairs, "yellowWalkwayStairs");
        
        GameRegistry.registerBlock(CoreBlocks.pastelDarkBlueWalkwayStairs, "pastelDarkBlueWalkwayStairs");
    	GameRegistry.registerBlock(CoreBlocks.pastelBrownWalkwayStairs, "pastelBrownWalkwayStairs");
    	GameRegistry.registerBlock(CoreBlocks.pastelGreenWalkwayStairs, "pastelGreenWalkwayStairs");
    	GameRegistry.registerBlock(CoreBlocks.pastelPinkWalkwayStairs, "pastelPinkWalkwayStairs");
    	
    	GameRegistry.registerBlock(CoreBlocks.pastelOrangeWalkwayStairs, "pastelOrangeWalkwayStairs");
    	GameRegistry.registerBlock(CoreBlocks.pastelMagentaWalkwayStairs, "pastelMagentaWalkwayStairs");
    	GameRegistry.registerBlock(CoreBlocks.pastelRedWalkwayStairs, "pastelRedWalkwayStairs");
    	GameRegistry.registerBlock(CoreBlocks.pastelYellowWalkwayStairs, "pastelYellowWalkwayStairs");
    	
    	GameRegistry.registerBlock(CoreBlocks.pastelLightBlueWalkwayStairs, "pastelLightBlueWalkwayStairs");
    	GameRegistry.registerBlock(CoreBlocks.pastelLightGreenWalkwayStairs, "pastelLightGreenWalkwayStairs");
    	GameRegistry.registerBlock(CoreBlocks.pastelCyanWalkwayStairs, "pastelCyanWalkwayStairs");
    	GameRegistry.registerBlock(CoreBlocks.pastelPurpleWalkwayStairs, "pastelPurpleWalkwayStairs");
        
        GameRegistry.registerBlock(CoreBlocks.oakLaminateWalkwayStairs, "oakLaminateWalkwayStairs");
        GameRegistry.registerBlock(CoreBlocks.spruceLaminateWalkwayStairs, "spruceLaminateWalkwayStairs");
        GameRegistry.registerBlock(CoreBlocks.birchLaminateWalkwayStairs, "birchLaminateWalkwayStairs");
        GameRegistry.registerBlock(CoreBlocks.jungleLaminateWalkwayStairs, "jungleLaminateWalkwayStairs");
        GameRegistry.registerBlock(CoreBlocks.acaciaLaminateWalkwayStairs, "acaciaLaminateWalkwayStairs");
        GameRegistry.registerBlock(CoreBlocks.darkOakLaminateWalkwayStairs, "darkOakLaminateWalkwayStairs");
        
       	GameRegistry.registerBlock(CoreBlocks.rebarBlock, "rebarBlock");
       	GameRegistry.registerBlock(CoreBlocks.lightingBlocks, ItemLightingBlock.class, "FlenixCities" + (CoreBlocks.lightingBlocks.getUnlocalizedName().substring(5)));
       	GameRegistry.registerBlock(CoreBlocks.lightingRotateBlocks, ItemLightingBlock.class, "FlenixCities" + (CoreBlocks.lightingRotateBlocks.getUnlocalizedName().substring(5)));

       	GameRegistry.registerBlock(CoreBlocks.styledGlassPane, "styledGlassPane"); //, ItemFloorBlocks.class, "FlenixCities" + (CoreBlocks.styledGlassPane.getUnlocalizedName().substring(5)));
       	GameRegistry.registerBlock(CoreBlocks.styledGlassWhitePane, "styledGlassWhitePane");
       	
       	GameRegistry.registerBlock(CoreBlocks.quartzDecorBlocks, ItemFloorBlocks.class, "quartzDecorBlocks");
        GameRegistry.registerBlock(CoreBlocks.adminShopBlock, "adminShopBlock");
       	
       	
       	//GameRegistry.registerBlock(FlenixCities.verticalPoster1, ItemBlockPosterVertical.class, "FlenixCities" + (FlenixCities.verticalPoster1.getUnlocalizedName().substring(5)));
       	//GameRegistry.registerBlock(FlenixCities.verticalPoster2, ItemBlockPosterVertical.class, "FlenixCities" + (FlenixCities.verticalPoster2.getUnlocalizedName().substring(5)));
       	//GameRegistry.registerBlock(FlenixCities.verticalPoster3, ItemBlockPosterVertical.class, "FlenixCities" + (FlenixCities.verticalPoster3.getUnlocalizedName().substring(5)));
       	//GameRegistry.registerBlock(FlenixCities.verticalPoster4, ItemBlockPosterVertical.class, "FlenixCities" + (FlenixCities.verticalPoster4.getUnlocalizedName().substring(5)));

        GameRegistry.registerItem(CoreItems.coin1, "coin1");
        GameRegistry.registerItem(CoreItems.coin2, "coin2");
        GameRegistry.registerItem(CoreItems.coin5, "coin5");
        GameRegistry.registerItem(CoreItems.coin10, "coin10");
        GameRegistry.registerItem(CoreItems.coin25, "coin25");
        GameRegistry.registerItem(CoreItems.coin50, "coin50");
        GameRegistry.registerItem(CoreItems.coin100, "coin100");
        GameRegistry.registerItem(CoreItems.note100, "note100");
        GameRegistry.registerItem(CoreItems.note200, "note200");
        GameRegistry.registerItem(CoreItems.note500, "note500");
        GameRegistry.registerItem(CoreItems.note1000, "note1000");
        GameRegistry.registerItem(CoreItems.note2000, "note2000");
        GameRegistry.registerItem(CoreItems.note5000, "note5000");
        GameRegistry.registerItem(CoreItems.note10000, "note10000");
        GameRegistry.registerItem(CoreItems.debitCardNew, "debitCardNew");
        GameRegistry.registerItem(CoreItems.bankerSpawner, "bankerSpawner");
        GameRegistry.registerItem(CoreItems.storeStockPairer, "storeStockPairer");
        
        GameRegistry.registerItem(CoreItems.plasticItem, "plasticItem");
        GameRegistry.registerItem(CoreItems.rawPlasticItem, "rawPlasticItem");
        GameRegistry.registerItem(CoreItems.smallPCB, "smallPCB");
        GameRegistry.registerItem(CoreItems.largePCB, "largePCB");
        GameRegistry.registerItem(CoreItems.atmInternals, "atmInternals");
        GameRegistry.registerItem(CoreItems.atmScreen, "atmScreen");
        GameRegistry.registerItem(CoreItems.atmButtons, "atmButtons");
        GameRegistry.registerItem(CoreItems.cpu, "cpu");
        
        GameRegistry.registerItem(CoreItems.pliers, "pliers");

    	/*GameRegistry.registerItem(CoreItems.ringItem, "ringItem");
    	GameRegistry.registerItem(CoreItems.diamondRing, "diamondRing");
    	GameRegistry.registerItem(CoreItems.necklaceItem, "necklaceItem");
    	GameRegistry.registerItem(CoreItems.rubyNecklace, "rubyNecklace");*/
    }
    
    public void addRecipes() {
    	OreDictionary.registerOre("ingotPlastic", CoreItems.plasticItem);
    	OreDictionary.registerOre("sheetPlastic", CoreItems.plasticItem);
    	OreDictionary.registerOre("rawPlastic", CoreItems.rawPlasticItem);
    	OreDictionary.registerOre("dustPlastic", CoreItems.rawPlasticItem);

    	ItemStack ironIngot = new ItemStack(Items.iron_ingot);
    	ItemStack glowstone = new ItemStack(Items.glowstone_dust);
    	ItemStack redstone = new ItemStack(Items.redstone);
    	ItemStack redstoneTorch = new ItemStack(Blocks.redstone_torch);
    	ItemStack diamond = new ItemStack(Items.diamond);
    	ItemStack goldIngot = new ItemStack(Items.gold_ingot);
    	ItemStack ironBlock = new ItemStack(Blocks.iron_block);
    	ItemStack glass = new ItemStack(Blocks.glass);
    	
    	ItemStack stoneBlock = new ItemStack(Blocks.stone);
    	ItemStack quartzItem = new ItemStack(Items.quartz);
    	ItemStack plasticBlockAny = new ItemStack(CoreBlocks.plasticBlock);
    	ItemStack skyscraperAny = new ItemStack(CoreBlocks.skyscraperBlocks);
    	ItemStack skyscraperWhite = new ItemStack(CoreBlocks.skyscraperBlocks, 1, 0);
    	ItemStack skyscraperLightGrey = new ItemStack(CoreBlocks.skyscraperBlocks, 1, 1);
    	ItemStack skyscraperDarkGrey = new ItemStack(CoreBlocks.skyscraperBlocks, 1, 2);
    	ItemStack skyscraperBlack = new ItemStack(CoreBlocks.skyscraperBlocks, 1, 3);
    	ItemStack rebarBlock = new ItemStack(CoreBlocks.rebarBlock);
    	ItemStack plastic = new ItemStack(CoreItems.plasticItem);
    	
    	ItemStack smallPCB = new ItemStack(CoreItems.smallPCB);
    	ItemStack largePCB = new ItemStack(CoreItems.largePCB);
    	ItemStack atmInternals = new ItemStack(CoreItems.atmInternals);
    	ItemStack atmScreen = new ItemStack(CoreItems.atmScreen);
    	ItemStack atmButtons = new ItemStack(CoreItems.atmButtons);
    	ItemStack cpu = new ItemStack(CoreItems.cpu);
    	
    	ItemStack blackDye = new ItemStack(Items.dye, 1, 0);
    	ItemStack redDye = new ItemStack(Items.dye, 1, 1);
    	ItemStack greenDye = new ItemStack(Items.dye, 1, 2);
    	ItemStack brownDye = new ItemStack(Items.dye, 1, 3);
    	ItemStack blueDye = new ItemStack(Items.dye, 1, 4);
    	ItemStack purpleDye = new ItemStack(Items.dye, 1, 5);
    	ItemStack tealDye = new ItemStack(Items.dye, 1, 6);
    	ItemStack lightGreyDye = new ItemStack(Items.dye, 1, 7);
    	ItemStack darkGreyDye = new ItemStack(Items.dye, 1, 8);
    	ItemStack pinkDye = new ItemStack(Items.dye, 1, 9);
    	ItemStack limeGreenDye = new ItemStack(Items.dye, 1, 10);
    	ItemStack yellowDye = new ItemStack(Items.dye, 1, 11);
    	ItemStack lightBlueDye = new ItemStack(Items.dye, 1, 12);
    	ItemStack magentaDye = new ItemStack(Items.dye, 1, 13);
    	ItemStack orangeDye = new ItemStack(Items.dye, 1, 14);
    	ItemStack whiteDye = new ItemStack(Items.dye, 1, 15);    	
    	
    	ItemStack blackWool = new ItemStack(Blocks.wool, 1, 15);
    	ItemStack redWool = new ItemStack(Blocks.wool, 1, 14);
    	ItemStack greenWool = new ItemStack(Blocks.wool, 1, 13);
    	ItemStack brownWool = new ItemStack(Blocks.wool, 1, 12);
    	ItemStack blueWool = new ItemStack(Blocks.wool, 1, 11);
    	ItemStack purpleWool = new ItemStack(Blocks.wool, 1, 10);
    	ItemStack tealWool = new ItemStack(Blocks.wool, 1, 9);
    	ItemStack lightGreyWool = new ItemStack(Blocks.wool, 1, 8);
    	ItemStack darkGreyWool = new ItemStack(Blocks.wool, 1, 7);
    	ItemStack pinkWool = new ItemStack(Blocks.wool, 1, 6);
    	ItemStack limeGreenWool = new ItemStack(Blocks.wool, 1, 5);
    	ItemStack yellowWool = new ItemStack(Blocks.wool, 1, 4);
    	ItemStack lightBlueWool = new ItemStack(Blocks.wool, 1, 3);
    	ItemStack magentaWool = new ItemStack(Blocks.wool, 1, 2);
    	ItemStack orangeWool = new ItemStack(Blocks.wool, 1, 1);
    	ItemStack whiteWool = new ItemStack(Blocks.wool, 1, 0); 
    	
    	ItemStack woodBlock = new ItemStack(Blocks.planks);
    	
    	//TODO  GameRegistry.addRecipe(new ItemStack(CoreBlocks.rebarBlock, 9, 0), "   ", " t ", "   ", 't', titaniumIngot);
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.rebarBlock, 9, 0), " i ", " i ", " i ", 'i', ironIngot);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.floorBlocks, 1, 9), stoneBlock, quartzItem);
    	
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 3, 4), " s ", "dsd", " s ", 's', skyscraperWhite, 'd', blackDye);
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 3, 5), " s ", "dsd", " s ", 's', skyscraperLightGrey, 'd', blackDye);
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 3, 6), " s ", "dsd", " s ", 's', skyscraperDarkGrey, 'd', blackDye);
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 3, 7), " s ", "dsd", " s ", 's', skyscraperBlack, 'd', blackDye);
    	
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 3, 8), " d ", "sss", " d ", 's', skyscraperWhite, 'd', blackDye);
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 3, 9), " d ", "sss", " d ", 's', skyscraperLightGrey, 'd', blackDye);
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 3, 10), " d ", "sss", " d ", 's', skyscraperDarkGrey, 'd', blackDye);
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 3, 11), " d ", "sss", " d ", 's', skyscraperBlack, 'd', blackDye);
    	
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 5, 12), "dsd", "sss", "dsd", 's', skyscraperWhite, 'd', blackDye);
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 5, 13), "dsd", "sss", "dsd", 's', skyscraperLightGrey, 'd', blackDye);
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 5, 14), "dsd", "sss", "dsd", 's', skyscraperDarkGrey, 'd', blackDye);
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 5, 15), "dsd", "sss", "dsd", 's', skyscraperBlack, 'd', blackDye);
    	
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 3, 0), new ItemStack(Blocks.stained_glass, 1, 0), new ItemStack(Blocks.stained_glass, 1, 0), new ItemStack(Blocks.stained_glass, 1, 0));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 3, 1), new ItemStack(Blocks.stained_glass, 1, 1), new ItemStack(Blocks.stained_glass, 1, 1), new ItemStack(Blocks.stained_glass, 1, 1));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 3, 2), new ItemStack(Blocks.stained_glass, 1, 2), new ItemStack(Blocks.stained_glass, 1, 2), new ItemStack(Blocks.stained_glass, 1, 2));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 3, 3), new ItemStack(Blocks.stained_glass, 1, 3), new ItemStack(Blocks.stained_glass, 1, 3), new ItemStack(Blocks.stained_glass, 1, 3));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 3, 4), new ItemStack(Blocks.stained_glass, 1, 4), new ItemStack(Blocks.stained_glass, 1, 4), new ItemStack(Blocks.stained_glass, 1, 4));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 3, 5), new ItemStack(Blocks.stained_glass, 1, 5), new ItemStack(Blocks.stained_glass, 1, 5), new ItemStack(Blocks.stained_glass, 1, 5));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 3, 6), new ItemStack(Blocks.stained_glass, 1, 6), new ItemStack(Blocks.stained_glass, 1, 6), new ItemStack(Blocks.stained_glass, 1, 6));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 3, 7), new ItemStack(Blocks.stained_glass, 1, 7), new ItemStack(Blocks.stained_glass, 1, 7), new ItemStack(Blocks.stained_glass, 1, 7));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 3, 8), new ItemStack(Blocks.stained_glass, 1, 8), new ItemStack(Blocks.stained_glass, 1, 8), new ItemStack(Blocks.stained_glass, 1, 8));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 3, 9), new ItemStack(Blocks.stained_glass, 1, 9), new ItemStack(Blocks.stained_glass, 1, 9), new ItemStack(Blocks.stained_glass, 1, 9));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 3, 10), new ItemStack(Blocks.stained_glass, 1, 10), new ItemStack(Blocks.stained_glass, 1, 10), new ItemStack(Blocks.stained_glass, 1, 10));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 3, 11), new ItemStack(Blocks.stained_glass, 1, 11), new ItemStack(Blocks.stained_glass, 1, 11), new ItemStack(Blocks.stained_glass, 1, 11));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 3, 12), new ItemStack(Blocks.stained_glass, 1, 12), new ItemStack(Blocks.stained_glass, 1, 12), new ItemStack(Blocks.stained_glass, 1, 12));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 3, 13), new ItemStack(Blocks.stained_glass, 1, 13), new ItemStack(Blocks.stained_glass, 1, 13), new ItemStack(Blocks.stained_glass, 1, 13));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 3, 14), new ItemStack(Blocks.stained_glass, 1, 14), new ItemStack(Blocks.stained_glass, 1, 14), new ItemStack(Blocks.stained_glass, 1, 14));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 3, 15), new ItemStack(Blocks.stained_glass, 1, 15), new ItemStack(Blocks.stained_glass, 1, 15), new ItemStack(Blocks.stained_glass, 1, 15));
    	
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 1, 0), new ItemStack(CoreBlocks.stainedGlass, 1, 0), glowstone);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 1, 1), new ItemStack(CoreBlocks.stainedGlass, 1, 1), glowstone);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 1, 2), new ItemStack(CoreBlocks.stainedGlass, 1, 2), glowstone);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 1, 3), new ItemStack(CoreBlocks.stainedGlass, 1, 3), glowstone);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 1, 4), new ItemStack(CoreBlocks.stainedGlass, 1, 4), glowstone);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 1, 5), new ItemStack(CoreBlocks.stainedGlass, 1, 5), glowstone);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 1, 6), new ItemStack(CoreBlocks.stainedGlass, 1, 6), glowstone);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 1, 7), new ItemStack(CoreBlocks.stainedGlass, 1, 7), glowstone);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 1, 8), new ItemStack(CoreBlocks.stainedGlass, 1, 8), glowstone);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 1, 9), new ItemStack(CoreBlocks.stainedGlass, 1, 9), glowstone);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 1, 10), new ItemStack(CoreBlocks.stainedGlass, 1, 10), glowstone);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 1, 11), new ItemStack(CoreBlocks.stainedGlass, 1, 11), glowstone);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 1, 12), new ItemStack(CoreBlocks.stainedGlass, 1, 12), glowstone);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 1, 13), new ItemStack(CoreBlocks.stainedGlass, 1, 13), glowstone);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 1, 14), new ItemStack(CoreBlocks.stainedGlass, 1, 14), glowstone);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.stainedGlass, 1, 15), new ItemStack(CoreBlocks.stainedGlass, 1, 15), glowstone);
    	
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.plasticBlock, 1, 0), plastic, plastic, plastic, plastic, plastic, plastic, plastic, plastic, plastic);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.plasticBlock, 1, 0), plasticBlockAny, whiteDye);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.plasticBlock, 1, 1), plasticBlockAny, orangeDye);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.plasticBlock, 1, 2), plasticBlockAny, magentaDye);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.plasticBlock, 1, 3), plasticBlockAny, lightBlueDye);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.plasticBlock, 1, 4), plasticBlockAny, yellowDye);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.plasticBlock, 1, 5), plasticBlockAny, limeGreenDye);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.plasticBlock, 1, 6), plasticBlockAny, pinkDye);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.plasticBlock, 1, 7), plasticBlockAny, lightGreyDye);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.plasticBlock, 1, 8), plasticBlockAny, darkGreyDye);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.plasticBlock, 1, 9), plasticBlockAny, tealDye);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.plasticBlock, 1, 10), plasticBlockAny, purpleDye);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.plasticBlock, 1, 11), plasticBlockAny, blueDye);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.plasticBlock, 1, 12), plasticBlockAny, brownDye);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.plasticBlock, 1, 13), plasticBlockAny, greenDye);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.plasticBlock, 1, 14), plasticBlockAny, redDye);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.plasticBlock, 1, 15), plasticBlockAny, blackDye);
    	
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreItems.plasticItem, 9), plasticBlockAny);
    	
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.styledGlass, 5, 0), "dgd", "ggg", "dgd", 'd', whiteDye, 'g', new ItemStack(Blocks.stained_glass, 1, 9));
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.styledGlassWhite, 5, 0), "dgd", "ggg", "dgd", 'd', whiteDye, 'g', new ItemStack(Blocks.stained_glass, 1, 0));
    	
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.styledGlass, 1, 0), new ItemStack(CoreBlocks.styledGlass, 1, 12));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.styledGlass, 1, 1), new ItemStack(CoreBlocks.styledGlass, 1, 0));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.styledGlass, 1, 2), new ItemStack(CoreBlocks.styledGlass, 1, 1));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.styledGlass, 1, 3), new ItemStack(CoreBlocks.styledGlass, 1, 2));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.styledGlass, 1, 4), new ItemStack(CoreBlocks.styledGlass, 1, 3));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.styledGlass, 1, 5), new ItemStack(CoreBlocks.styledGlass, 1, 4));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.styledGlass, 1, 6), new ItemStack(CoreBlocks.styledGlass, 1, 5));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.styledGlass, 1, 7), new ItemStack(CoreBlocks.styledGlass, 1, 6));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.styledGlass, 1, 8), new ItemStack(CoreBlocks.styledGlass, 1, 7));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.styledGlass, 1, 9), new ItemStack(CoreBlocks.styledGlass, 1, 8));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.styledGlass, 1, 10), new ItemStack(CoreBlocks.styledGlass, 1, 9));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.styledGlass, 1, 11), new ItemStack(CoreBlocks.styledGlass, 1, 10));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.styledGlass, 1, 12), new ItemStack(CoreBlocks.styledGlass, 1, 11));
    	
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.styledGlassWhite, 1, 0), new ItemStack(CoreBlocks.styledGlassWhite, 1, 12));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.styledGlassWhite, 1, 1), new ItemStack(CoreBlocks.styledGlassWhite, 1, 0));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.styledGlassWhite, 1, 2), new ItemStack(CoreBlocks.styledGlassWhite, 1, 1));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.styledGlassWhite, 1, 3), new ItemStack(CoreBlocks.styledGlassWhite, 1, 2));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.styledGlassWhite, 1, 4), new ItemStack(CoreBlocks.styledGlassWhite, 1, 3));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.styledGlassWhite, 1, 5), new ItemStack(CoreBlocks.styledGlassWhite, 1, 4));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.styledGlassWhite, 1, 6), new ItemStack(CoreBlocks.styledGlassWhite, 1, 5));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.styledGlassWhite, 1, 7), new ItemStack(CoreBlocks.styledGlassWhite, 1, 6));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.styledGlassWhite, 1, 8), new ItemStack(CoreBlocks.styledGlassWhite, 1, 7));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.styledGlassWhite, 1, 9), new ItemStack(CoreBlocks.styledGlassWhite, 1, 8));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.styledGlassWhite, 1, 10), new ItemStack(CoreBlocks.styledGlassWhite, 1, 9));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.styledGlassWhite, 1, 11), new ItemStack(CoreBlocks.styledGlassWhite, 1, 10));
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.styledGlassWhite, 1, 12), new ItemStack(CoreBlocks.styledGlassWhite, 1, 11));
    	
    	GameRegistry.addRecipe(new ItemStack(CoreItems.rawPlasticItem, 5), " c ", "ccc", " c ", 'c', new ItemStack(Items.coal, 1));
    	GameRegistry.addSmelting(CoreItems.rawPlasticItem, new ItemStack(CoreItems.plasticItem, 4), 0.1F);
    	
    	GameRegistry.addRecipe(smallPCB, "rtr", "rir", "pgp", 'r', redstone, 't', redstoneTorch, 'p', plastic, 'g', greenDye, 'i', goldIngot);
    	GameRegistry.addRecipe(largePCB, "rtr", "ppp", 'r', redstone, 't', redstoneTorch, 'p', smallPCB);
    	GameRegistry.addRecipe(cpu, "pdp", "dgd", "pdp", 'p', smallPCB, 'g', goldIngot, 'd', diamond);
    	GameRegistry.addRecipe(atmInternals, "iii", "lcl", "iii", 'i', ironIngot, 'l', largePCB, 'c', cpu);
    	GameRegistry.addRecipe(atmScreen, "jij", "gag", "l l", 'i', ironBlock, 'j', glowstone, 'g', glass, 'l', largePCB, 'a', atmInternals);
    	GameRegistry.addRecipe(atmButtons, "bbb", "bsb", "bbb", 'b', new ItemStack(Blocks.stone_button), 's', smallPCB);
    	
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.atmBlock, 1, 0), "bbb", "bsb", "bkb", 'b', new ItemStack(Blocks.stone), 's', atmScreen, 'k', atmButtons);
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.atmBlock, 1, 4), "bbb", "bsb", "bkb", 'b', new ItemStack(Blocks.stonebrick), 's', atmScreen, 'k', atmButtons);
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.atmBlock, 1, 8), "bbb", "bsb", "bkb", 'b', skyscraperWhite, 's', atmScreen, 'k', atmButtons);
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.atmBlock, 1, 12), "bbb", "bsb", "bkb", 'b', skyscraperDarkGrey, 's', atmScreen, 'k', atmButtons);
    	
    	if (CityConfig.playerOwnedShops) {
	    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.floatingShelvesBlock, 1, 0), "  w", "pww", "cww", 'w', skyscraperWhite, 'p', smallPCB, 'c', new ItemStack(Blocks.chest));
	    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.stockChest, 1, 0), "scs", "cpc", "iii", 's', skyscraperLightGrey, 'c', new ItemStack(Blocks.chest), 'p', largePCB, 'i', ironIngot);
	    	GameRegistry.addRecipe(new ItemStack(CoreItems.storeStockPairer), "pgp", "pkp", "pkp", 'p', plastic, 'g', glowstone, 'k', largePCB);
    	}
    	
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.floorBlocks, 8, 5), "www", "wpw", "www", 'p', plastic, 'w', new ItemStack(Blocks.planks, 1, 0));
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.floorBlocks, 8, 6), "www", "wpw", "www", 'p', plastic, 'w', new ItemStack(Blocks.planks, 1, 1));
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.floorBlocks, 8, 7), "www", "wpw", "www", 'p', plastic, 'w', new ItemStack(Blocks.planks, 1, 2));
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.floorBlocks, 8, 8), "www", "wpw", "www", 'p', plastic, 'w', new ItemStack(Blocks.planks, 1, 3));
    	
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.lightingBlocks, 1, 0), "pgp", "ppp", 'p', plastic, 'g', glowstone);
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.lightingBlocks, 1, 0), "pgp", " p ", 'p', plastic, 'g', glowstone);
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.lightingBlocks, 1, 0), "pgp", "pgp", "pgp", 'p', plastic, 'g', glowstone);
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.lightingBlocks, 1, 0), "pgp", 'p', plastic, 'g', glowstone);
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.lightingBlocks, 1, 0), "pgp", "pgp", 'p', plastic, 'g', glowstone);
    	
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.lightingRotateBlocks, 1, 0), "pg", "pg", 'p', plastic, 'g', glowstone);
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.lightingRotateBlocks, 1, 4), "pg", 'p', plastic, 'g', glowstone);
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.lightingRotateBlocks, 1, 8), "ppg", "ppg", 'p', plastic, 'g', glowstone);
    	GameRegistry.addRecipe(new ItemStack(CoreBlocks.lightingRotateBlocks, 1, 12), "ppg", 'p', plastic, 'g', glowstone);
    	
    	
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 8, 0), stoneBlock, stoneBlock, stoneBlock, stoneBlock, stoneBlock, stoneBlock, stoneBlock, stoneBlock, rebarBlock);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 1, 0), skyscraperAny, whiteDye);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 1, 1), skyscraperAny, lightGreyDye);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 1, 2), skyscraperAny, darkGreyDye);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.skyscraperBlocks, 1, 3), skyscraperAny, blackDye);
    	
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 0), skyscraperAny, whiteWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 1), skyscraperAny, orangeWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 2), skyscraperAny, magentaWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 3), skyscraperAny, lightBlueWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 4), skyscraperAny, yellowWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 5), skyscraperAny, limeGreenWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 6), skyscraperAny, pinkWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 7), skyscraperAny, darkGreyWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 8), skyscraperAny, lightGreyWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 9), skyscraperAny, tealWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 10), skyscraperAny, purpleWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 11), skyscraperAny, blueWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 12), skyscraperAny, brownWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 13), skyscraperAny, greenWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 14), skyscraperAny, redWool);
    	GameRegistry.addShapelessRecipe(new ItemStack(CoreBlocks.woolCeilingTile, 1, 15), skyscraperAny, blackWool);
    	
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
    }

    public void registerRenderThings() {}
    public void registerRenderers() {}
    
}
