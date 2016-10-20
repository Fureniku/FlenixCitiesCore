package com.silvaniastudios.cities.core;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

import com.silvaniastudios.cities.core.client.StoreStockInfoRender;
import com.silvaniastudios.cities.core.npc.spawner.NPCSpawnerEntity;
import com.silvaniastudios.cities.econ.atm.TileEntityATMEntity;
import com.silvaniastudios.cities.econ.store.entity.TileEntityAdminShop;
import com.silvaniastudios.cities.econ.store.entity.TileEntityFloatingShelves;
import com.silvaniastudios.cities.econ.store.entity.TileEntityStockChest;
import com.silvaniastudios.cities.network.*;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid=FlenixCities_Core.modid, dependencies="after:BuildCraft|Core;after:BuildCraft|Energy", name="FlenixCities", version="0.17.0")
public class FlenixCities_Core { 
	
	public static final String modid = "flenixcities";
	
    @Instance(FlenixCities_Core.modid)
    public static FlenixCities_Core instance;
    public static GuiHandler cityGuiHandler = new GuiHandler();

    @SidedProxy(clientSide="co.uk.silvania.cities.core.client.ClientProxy", serverSide="co.uk.silvania.cities.core.CommonProxy")
    public static CommonProxy proxy;
    
	public static CreativeTabs tabCity = new CreativeTabs("tabCity") {
		@Override
		public Item getTabIconItem() {
			return new ItemStack(CoreBlocks.skyscraperBlocks, 1, 0).getItem();
		}
	};
	
	public static CreativeTabs tabEcon = new CreativeTabs("tabEcon") {
		@Override
		public Item getTabIconItem() {
			return new ItemStack(CoreItems.note10000, 1, 0).getItem();
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
        CoreBlocks.init();
        CoreItems.init();
        
	    proxy.registerBlocks();
	    proxy.entityStuff();
	    
        proxy.registerRenderThings();
        proxy.registerRenderers();
	        
	    MinecraftForge.EVENT_BUS.register(new EventDrops());
	    if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
	    	MinecraftForge.EVENT_BUS.register(new StoreStockInfoRender(Minecraft.getMinecraft()));
	    }
	        
	    GameRegistry.registerTileEntity(TileEntityATMEntity.class, "tileEntityATM");
	    GameRegistry.registerTileEntity(TileEntityFloatingShelves.class, "tileEntityFloatingShelves");
	    GameRegistry.registerTileEntity(TileEntityAdminShop.class, "tileEntityAdminShop");
	    GameRegistry.registerTileEntity(NPCSpawnerEntity.class, "npcSpawnerBlock");
	    GameRegistry.registerTileEntity(TileEntityStockChest.class, "tileEntityStockChest");
    }
               
    @EventHandler
    public void Init(FMLInitializationEvent event) {
        proxy.addRecipes();
    }


    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {}
};