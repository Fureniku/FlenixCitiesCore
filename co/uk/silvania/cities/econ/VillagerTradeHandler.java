package co.uk.silvania.cities.econ;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import co.uk.silvania.cities.core.CoreItems;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

public class VillagerTradeHandler implements IVillageTradeHandler {
	
	@Override
	public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipes, Random random) {
		recipes.add(new MerchantRecipe(new ItemStack(CoreItems.note1000, 1), new ItemStack(Block.cobblestone, 1, 64)));
	}

}
