package com.silvaniastudios.cities.core;

import com.silvaniastudios.cities.network.ATMWithdrawPacket;
import com.silvaniastudios.cities.network.AdminShopClientPacket;
import com.silvaniastudios.cities.network.AdminShopPricePacket;
import com.silvaniastudios.cities.network.FloatingShelvesClientPacket;
import com.silvaniastudios.cities.network.FloatingShelvesPricePacket;
import com.silvaniastudios.cities.network.FloatingShelvesSalePacket;
import com.silvaniastudios.cities.network.SalePacket;
import com.silvaniastudios.cities.network.ServerBalancePacket;
import com.silvaniastudios.cities.network.SoundPacket;
import com.silvaniastudios.cities.network.StockChestUpdatePacket;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
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
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid=FlenixCities.MODID, version=FlenixCities.VERSION)
public class FlenixCities { 
	
	public static final String MODID = "flenixcities";
	public static final String VERSION = "0.20.0";
	
    @Instance(FlenixCities.MODID)
    public static FlenixCities instance;
    public static GuiHandler cityGuiHandler = new GuiHandler();

    @SidedProxy(clientSide="com.silvaniastudios.cities.core.client.ClientProxy", serverSide="com.silvaniastudios.cities.core.CommonProxy")
    public static CommonProxy proxy;
    
	public static CreativeTabs tabCity = new CreativeTabs("tabCity") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(CoreBlocks.rebar, 1, 0);
		}
	};
	
	public static CreativeTabs tabDecorative = new CreativeTabs("tabDecorative") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(CoreBlocks.floor_block, 1, 6);
		}
	};
	
	public static CreativeTabs tabEcon = new CreativeTabs("tabEcon") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(CoreItems.note10000, 1, 0);
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
    	network = NetworkRegistry.INSTANCE.newSimpleChannel("FlenixCitiesCore");
    	//Handler class, Packet class, Packet ID (+1), RECIEVING Side
    	network.registerMessage(ATMWithdrawPacket.Handler.class, ATMWithdrawPacket.class, 0, Side.SERVER);
    	network.registerMessage(SoundPacket.Handler.class, SoundPacket.class, 1, Side.SERVER);
    	network.registerMessage(ServerBalancePacket.Handler.class, ServerBalancePacket.class, 2, Side.CLIENT);
    	network.registerMessage(AdminShopPricePacket.Handler.class, AdminShopPricePacket.class, 3, Side.CLIENT);
    	network.registerMessage(SalePacket.Handler.class, SalePacket.class, 4, Side.SERVER);
    	network.registerMessage(AdminShopClientPacket.Handler.class, AdminShopClientPacket.class, 5, Side.SERVER);
    	network.registerMessage(FloatingShelvesPricePacket.Handler.class, FloatingShelvesPricePacket.class, 6, Side.CLIENT);
    	network.registerMessage(FloatingShelvesClientPacket.Handler.class, FloatingShelvesClientPacket.class, 7, Side.SERVER);
    	network.registerMessage(FloatingShelvesSalePacket.Handler.class, FloatingShelvesSalePacket.class, 8, Side.SERVER);
    	network.registerMessage(StockChestUpdatePacket.Handler.class, StockChestUpdatePacket.class, 9, Side.SERVER);
    	
    	configPath = event.getModConfigurationDirectory() + "/FlenixCities/";
    	CityConfig.init(configPath);
        
    	NetworkRegistry.INSTANCE.registerGuiHandler(this, cityGuiHandler);
	        
	    MinecraftForge.EVENT_BUS.register(new EventDrops());
	    /*if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
	    	MinecraftForge.EVENT_BUS.register(new StoreStockInfoRender(Minecraft.getMinecraft()));
	    }*/
	    
	    //GameRegistry.registerTileEntity(TileEntityFloatingShelves.class, "tileEntityFloatingShelves");
	    //GameRegistry.registerTileEntity(TileEntityAdminShop.class, "tileEntityAdminShop");
	    //GameRegistry.registerTileEntity(NPCSpawnerEntity.class, "npcSpawnerBlock");
	    //GameRegistry.registerTileEntity(TileEntityStockChest.class, "tileEntityStockChest");
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
			CoreItems.registerModels();
			CoreBlocks.registerModels();
		}
		
		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			CoreBlocks.register(event.getRegistry());
		}
	}
};