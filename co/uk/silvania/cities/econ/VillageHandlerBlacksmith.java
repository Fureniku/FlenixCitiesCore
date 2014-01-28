package co.uk.silvania.cities.econ;

import java.util.Random;

import co.uk.silvania.cities.api.npc.VillageHandlerBase;
import co.uk.silvania.cities.core.CityConfig;
import co.uk.silvania.cities.core.CoreBlocks;
import co.uk.silvania.cities.core.CoreItems;
import co.uk.silvania.cities.core.FlenixCities_Core;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

public class VillageHandlerBlacksmith extends VillageHandlerBase implements IVillageTradeHandler
{
	public static VillageHandlerBlacksmith villageHandler = null;
	
	
	public static void init(CityConfig config)
	{
		if( null == villageHandler )
		{
			villageHandler = new VillageHandlerBlacksmith();
			VillagerRegistry.instance().registerVillagerId(41);
			VillagerRegistry.instance().registerVillageTradeHandler(41, villageHandler);
			//sidedProxy.registerVillagerSkin(41, FlenixCities_Core.modid, "textures/entities/npcbanker.png");
		}
	}

	@Override
	public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random) {
		
		// HE Sells
		recipeList.add(new MerchantRecipe(fiftyDollar, randomItem(Item.coal, 10, 12, random)));
		addRandomTrade(recipeList, hundredDollar, randomItem(Item.ingotIron, 4, 6, random), 0.9, random);
		addRandomTrade(recipeList, fiftyDollar, randomItem(Item.ingotGold, 1, 1, random), 0.4, random);
		addRandomTrade(recipeList, hundredDollar, randomItem(CoreItems.copperIngot, 5, 6, random), 0.5, random);
		addRandomTrade(recipeList, hundredDollar, randomItem(CoreItems.tinIngot, 5, 6, random), 0.5, random);
		
		// HE Buys
		addRandomTrade(recipeList, randomItem(Item.coal, 16, 20, random), fiftyDollar, 1.0, random);
		addRandomTrade(recipeList, randomItem(Block.oreIron, 14, 17, random), fiftyDollar, 1.0, random);
		addRandomTrade(recipeList, randomItem(Block.oreGold, 2, 3, random), fiftyDollar, 1.0, random);
		addRandomTrade(recipeList, randomItem(CoreBlocks.copperOre, 10, 12, random), hundredDollar, 1.0, random);
		addRandomTrade(recipeList, randomItem(CoreBlocks.tinOre, 10, 12, random), hundredDollar, 1.0, random);
	}

}
