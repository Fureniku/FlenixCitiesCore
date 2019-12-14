package com.silvaniastudios.cities.core;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(modid=FurenikusCities.MODID, version=FurenikusCities.VERSION)
public class FurenikusCities { 
	
	public static final String MODID = "furenikuscities";
	public static final String VERSION = "0.1.0";
	
    @Instance(FurenikusCities.MODID)
    public static FurenikusCities instance;
    public static GuiHandler cityGuiHandler = new GuiHandler();

    @SidedProxy(clientSide="com.silvaniastudios.cities.core.client.ClientProxy", serverSide="com.silvaniastudios.cities.core.CommonProxy")
    public static CommonProxy proxy;
    
    public static CreativeTabs tabCity = new CreativeTabs("tabCity") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(CoreBlocks.rebar, 1, 0);
		}
	};
	
	public static CreativeTabs tabBlocks = new CreativeTabs("tabBlocks") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(CoreBlocks.floor_block, 1, 6);
		}
	};
	
	public static CreativeTabs tabDecorative = new CreativeTabs("tabDecorative") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(CoreBlocks.plant_1, 1, 0);
		}
	};
	
	public static CreativeTabs tabLighting = new CreativeTabs("tabLighting") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(CoreBlocks.lamp_ornate_skyscraper_white, 1, 0);
		}
	};
	
	public static CreativeTabs tabResearch = new CreativeTabs("tabResearch") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(CoreBlocks.rsch_grill, 1, 0);
		}
	};
	

	
	public static String configPath;
	public static SimpleNetworkWrapper network;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	configPath = event.getModConfigurationDirectory() + "/Fureniku's Cities/";
    	CityConfig.init(configPath);
        
    	NetworkRegistry.INSTANCE.registerGuiHandler(this, cityGuiHandler);
    	
    	proxy.preInit();
    }
               
    @EventHandler
    public void Init(FMLInitializationEvent event) {}


    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {}
    
    @Mod.EventBusSubscriber
	public static class RegistrationHandler {
		
		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			CoreItems.register(event.getRegistry());
			CoreBlocks.registerItemBlocks(event.getRegistry());
		}
		
		@SubscribeEvent
		public static void registerModels(ModelRegistryEvent event) {
			OBJLoader.INSTANCE.addDomain(MODID);
			CoreItems.registerModels();
			CoreBlocks.registerModels();
		}
		
		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			CoreBlocks.register(event.getRegistry());
		}
	}
};