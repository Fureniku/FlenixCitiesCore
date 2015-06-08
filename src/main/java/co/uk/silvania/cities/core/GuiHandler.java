package co.uk.silvania.cities.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import co.uk.silvania.cities.econ.atm.ContainerATM;
import co.uk.silvania.cities.econ.atm.GuiATM;
import co.uk.silvania.cities.econ.atm.GuiATMNoCard;
import co.uk.silvania.cities.econ.atm.TileEntityATMEntity;
import co.uk.silvania.cities.econ.store.container.ContainerAdminShop;
import co.uk.silvania.cities.econ.store.container.GuiAdminShop;
import co.uk.silvania.cities.econ.store.entity.TileEntityAdminShop;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	public GuiHandler() {}
	
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		switch(id) {
			case 0: {
				TileEntity tileEntity = world.getTileEntity(x, y, z);
				if(tileEntity instanceof TileEntityATMEntity) {
					return new ContainerATM(player.inventory, (TileEntityATMEntity) tileEntity);
				}	
			}
			case 1: {
				TileEntity tileEntity = world.getTileEntity(x, y, z);
				//if(tileEntity instanceof TileEntityFloatingShelves) {
					//return new ContainerFloatingShelves(player.inventory, (TileEntityFloatingShelves) tileEntity);
				//}	
			}
			/*case 2: {
				TileEntity tileEntity = world.getTileEntity(x, y, z);
				if(tileEntity instanceof TileEntityATMEntity) {
					return new ContainerATM(player.inventory, (TileEntityATMEntity) tileEntity);
				}	
			}*/
			case 3: {
				TileEntity tileEntity = world.getTileEntity(x, y, z);
				if(tileEntity instanceof TileEntityAdminShop) {
					return new ContainerAdminShop(player.inventory, (TileEntityAdminShop) tileEntity);
				}	
			}
		}
		return null;	
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
    	switch(id) {
    		case 0: {
    			TileEntity tileEntity = world.getTileEntity(x, y, z);
    			if (tileEntity instanceof TileEntityATMEntity) {
    				return new GuiATM(player.inventory, (TileEntityATMEntity) tileEntity, world, x, y, z);
    			}	
    		}        	
    		case 1: {
    			TileEntity tileEntity = world.getTileEntity(x, y, z);
    			//if (tileEntity instanceof TileEntityFloatingShelves) {
    				//return new GuiFloatingShelves(player.inventory, (TileEntityFloatingShelves) tileEntity);
    			//}	
    		}
    		case 2: {
    			TileEntity tileEntity = world.getTileEntity(x, y, z);
    			if (tileEntity instanceof TileEntityATMEntity) {
    				System.out.println("Opening no card GUI");
    				return new GuiATMNoCard(player.inventory, (TileEntityATMEntity) tileEntity);
    			}	
    		}
    		case 3: {
    			TileEntity tileEntity = world.getTileEntity(x, y, z);
    			if (tileEntity instanceof TileEntityAdminShop) {
    				return new GuiAdminShop(player.inventory, (TileEntityAdminShop) tileEntity);
    			}	
    		}
    	}
    	return null;
    }
}