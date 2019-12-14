package com.silvaniastudios.cities.core.client;

import com.silvaniastudios.cities.core.CommonProxy;
import com.silvaniastudios.cities.core.FurenikusCities;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {
    
	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(FurenikusCities.MODID + ":" + id, "inventory"));
	}
	
	@Override
	public void preInit() {
		//MinecraftForge.EVENT_BUS.register(ModelBakeHandler.instance);
	}
	
	@Override
	public void openGui(int guiId) {
		if (guiId == 0) {
		}
	}
}