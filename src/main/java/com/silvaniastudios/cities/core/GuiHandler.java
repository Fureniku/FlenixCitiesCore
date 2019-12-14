package com.silvaniastudios.cities.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	public GuiHandler() {}
	
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		//TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
		switch(id) {
			
		}
		return null;	
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
    	//TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
    	switch(id) {
    		
    	}
    	return null;
    }
}