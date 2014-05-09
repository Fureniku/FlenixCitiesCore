//VERY TEMPORARILY BORROWED THIS FROM AKJOSCH, WHO PORTED IT FROM ANGLEWYRM.
//It'll be replaced in the next release; this was simply so I could have some working NPCs for my server.
//In fact, this won't be in a public release at all; you'll only see this message if you're looking at my GitHub.

//The ONLY change I've made is adding more itemstacks for my money.

package co.uk.silvania.cities.api.npc;

import java.util.Random;

import co.uk.silvania.cities.core.CoreItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

public abstract class VillageHandlerBase
{
	
	public static CommonSidedProxy sidedProxy;
	
	// Common trade goods
	protected ItemStack oneCent = new ItemStack(CoreItems.coin1, 1);
	protected ItemStack twoCent = new ItemStack(CoreItems.coin2, 1);
	protected ItemStack fiveCent = new ItemStack(CoreItems.coin5, 1);
	protected ItemStack tenCent = new ItemStack(CoreItems.coin10, 1);
	protected ItemStack twentyfiveCent = new ItemStack(CoreItems.coin25, 1);
	protected ItemStack fiftyCent = new ItemStack(CoreItems.coin50, 1);
	protected ItemStack hundredCent = new ItemStack(CoreItems.coin100, 1);
	protected ItemStack oneDollar = new ItemStack(CoreItems.note100, 1);
	protected ItemStack twoDolalr = new ItemStack(CoreItems.note200, 1);
	protected ItemStack fiveDollar = new ItemStack(CoreItems.note500, 1);
	protected ItemStack tenDollar = new ItemStack(CoreItems.note1000, 1);
	protected ItemStack twentyDollar = new ItemStack(CoreItems.note2000, 1);
	protected ItemStack fiftyDollar = new ItemStack(CoreItems.note5000, 1);
	protected ItemStack hundredDollar = new ItemStack(CoreItems.note10000, 1);
	
	// Bunch of helper methods
	protected void addRandomTrade(MerchantRecipeList list, ItemStack src, ItemStack dst, double chance, Random rnd)
	{
		addRandomTrade(list, src, (ItemStack)null, dst, chance, rnd);
	}

	protected void addRandomTrade(MerchantRecipeList list, ItemStack src1, ItemStack src2, ItemStack dst, double chance, Random rnd)
	{
		if( chance >= rnd.nextDouble() )
		{
			list.add(new MerchantRecipe(src1, src2, dst));
		}
	}
	
	protected ItemStack randomItem(Item item, int min, int max, Random rnd)
	{
		return randomItem(item, min, max, 0, rnd);
	}
	
	protected ItemStack randomItem(Item item, int min, int max, int metadata, Random rnd)
	{
		return new ItemStack(item, min + rnd.nextInt(max - min + 1), metadata);
	}

	protected ItemStack randomItem(Block block, int min, int max, Random rnd)
	{
		return randomItem(block, min, max, 0, rnd);
	}
	
	protected ItemStack randomItem(Block block, int min, int max, int metadata, Random rnd)
	{
		return new ItemStack(block, min + rnd.nextInt(max - min + 1), metadata);
	}
}
