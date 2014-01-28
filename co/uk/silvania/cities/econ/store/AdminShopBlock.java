package co.uk.silvania.cities.econ.store;

import co.uk.silvania.cities.core.FlenixCities_Core;
import co.uk.silvania.cities.econ.EconUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class AdminShopBlock extends Block {

	public AdminShopBlock(int id) {
		super(id, Material.iron);
		this.setCreativeTab(FlenixCities_Core.tabEcon);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float j, float k, float l) {
		if (!world.isRemote) {
        	if (player.capabilities.isCreativeMode) {
        		System.out.println("You are in creative!");
        	} else {
				double cash = EconUtils.getInventoryCash(player);
				double value = VanillaItemValueConfig.stone;
				if (player.isSneaking()) {
					String materialName = Block.stone.getUnlocalizedName();
					System.out.println("Item costs: " + (VanillaItemValueConfig.stone));
					
				} else if (!player.isSneaking() && cash >= value) {
					System.out.println("Scanning player inventory and hopefully charging them " + value);
					EconUtils.findCashInInventory(player, value);
				}
			}
		}		
		return true;
	}

}
