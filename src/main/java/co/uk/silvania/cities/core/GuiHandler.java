package co.uk.silvania.cities.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import co.uk.silvania.cities.econ.atm.ContainerATM;
import co.uk.silvania.cities.econ.atm.GuiATM;
import co.uk.silvania.cities.econ.atm.GuiATMNoCard;
import co.uk.silvania.cities.econ.atm.TileEntityATMEntity;
import co.uk.silvania.cities.econ.store.container.ContainerAdminShop;
import co.uk.silvania.cities.econ.store.container.ContainerFloatingShelves;
import co.uk.silvania.cities.econ.store.container.ContainerStockChest;
import co.uk.silvania.cities.econ.store.container.GuiAdminShop;
import co.uk.silvania.cities.econ.store.container.GuiFloatingShelves;
import co.uk.silvania.cities.econ.store.container.GuiStockChest;
import co.uk.silvania.cities.econ.store.entity.TileEntityAdminShop;
import co.uk.silvania.cities.econ.store.entity.TileEntityFloatingShelves;
import co.uk.silvania.cities.econ.store.entity.TileEntityStockChest;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	public GuiHandler() {}
	
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		switch(id) {
			case 0: {
				if(tileEntity instanceof TileEntityATMEntity) {
					return new ContainerATM(player.inventory, (TileEntityATMEntity) tileEntity);
				}	
			}
			case 1: {
				if(tileEntity instanceof TileEntityFloatingShelves) {
					return new ContainerFloatingShelves(player.inventory, (TileEntityFloatingShelves) tileEntity);
				}	
			}
			case 2: {
				if(tileEntity instanceof TileEntityATMEntity) {
					return new ContainerATM(player.inventory, (TileEntityATMEntity) tileEntity);
				}	
			}
			case 3: {
				if(tileEntity instanceof TileEntityAdminShop) {
					return new ContainerAdminShop(player.inventory, (TileEntityAdminShop) tileEntity);
				}	
			}
			case 4: {
				if(tileEntity instanceof TileEntityStockChest) {
					return new ContainerStockChest(player.inventory, (TileEntityStockChest) tileEntity);
				}
			}
		}
		return null;	
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
    	TileEntity tileEntity = world.getTileEntity(x, y, z);
    	switch(id) {
    		case 0: {
    			if (tileEntity instanceof TileEntityATMEntity) {
    				return new GuiATM(player.inventory, (TileEntityATMEntity) tileEntity, world, x, y, z);
    			}	
    		}        	
    		case 1: {
    			if (tileEntity instanceof TileEntityFloatingShelves) {
    				return new GuiFloatingShelves(player.inventory, (TileEntityFloatingShelves) tileEntity);
    			}	
    		}
    		case 2: {
    			if (tileEntity instanceof TileEntityATMEntity) {
    				System.out.println("Opening no card GUI");
    				return new GuiATMNoCard(player.inventory, (TileEntityATMEntity) tileEntity);
    			}	
    		}
    		case 3: {
    			if (tileEntity instanceof TileEntityAdminShop) {
    				return new GuiAdminShop(player.inventory, (TileEntityAdminShop) tileEntity);
    			}	
    		}
    		case 4: {
    			if (tileEntity instanceof TileEntityStockChest) {
    				return new GuiStockChest(player.inventory, (TileEntityStockChest) tileEntity);
    			}
    		}
    	}
    	return null;
    }
}