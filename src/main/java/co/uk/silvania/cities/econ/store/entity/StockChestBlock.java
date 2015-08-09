package co.uk.silvania.cities.econ.store.entity;

import co.uk.silvania.cities.core.FlenixCities_Core;
import co.uk.silvania.cities.econ.EconUtils;
import co.uk.silvania.cities.network.AdminShopPricePacket;
import co.uk.silvania.cities.network.ServerBalancePacket;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class StockChestBlock extends BlockContainer {

	public TileEntityStockChest te;
	
	public StockChestBlock() {
		super(Material.iron);
		this.setCreativeTab(FlenixCities_Core.tabEcon);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityStockChest();
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float j, float k, float l) {
        player.openGui(FlenixCities_Core.instance, 4, world, x, y, z);
        return true;
    }
	
	

}
