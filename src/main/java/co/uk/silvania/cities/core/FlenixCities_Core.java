package co.uk.silvania.cities.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import co.uk.silvania.cities.core.npc.spawner.NPCSpawnerEntity;
import co.uk.silvania.cities.econ.atm.TileEntityATMEntity;
import co.uk.silvania.cities.network.ATMPacket;
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

@Mod(modid=FlenixCities_Core.modid, dependencies="after:BuildCraft|Core;after:BuildCraft|Energy", name="FlenixCities", version="0.10.0")
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
		public ItemStack getIconItemStack() {
			return new ItemStack(CoreBlocks.atmBlock, 1, 0);
		}

		@Override
		public Item getTabIconItem() {
			return new ItemStack(CoreBlocks.atmBlock, 1, 0).getItem();
		}
	};
	
	public static String configPath;
	public static SimpleNetworkWrapper network;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	network = NetworkRegistry.INSTANCE.newSimpleChannel("FlenixCitiesCore_ATM");
    	//Handler class, Packet class, Packet ID (+1), RECIEVING Side
    	network.registerMessage(ATMPacket.Handler.class, ATMPacket.class, 0, Side.SERVER);    	
    	
    	configPath = event.getModConfigurationDirectory() + "/FlenixCities/";
    	CityConfig.init(configPath);
        
    	NetworkRegistry.INSTANCE.registerGuiHandler(this, cityGuiHandler);
        CoreBlocks.init();
        CoreItems.init();
        
        proxy.registerRenderThings();
        proxy.registerRenderers();

	    proxy.registerBlocks();
	    proxy.addNames();
	    proxy.entityStuff();
	        
	    MinecraftForge.EVENT_BUS.register(new EventDrops());
	        
	    GameRegistry.registerTileEntity(TileEntityATMEntity.class, "tileEntityATM");
	    //TODO GameRegistry.registerTileEntity(TileEntityFloatingShelves.class, "tileEntityFloatingShelves");
	    //GameRegistry.registerTileEntity(TileEntityAdminShop.class, "tileEntityAdminShop");
	    GameRegistry.registerTileEntity(NPCSpawnerEntity.class, "npcSpawnerBlock");
    }
               
    @EventHandler
    public void Init(FMLInitializationEvent event) {
        proxy.addRecipes();
    }


    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {}
};