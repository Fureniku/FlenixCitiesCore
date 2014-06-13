package co.uk.silvania.cities.core;

import java.io.File;

import co.uk.silvania.cities.api.npc.CommonSidedProxy;
import co.uk.silvania.cities.core.items.CraftingIngredientItems;
import co.uk.silvania.cities.core.npc.EntityBanker;
import co.uk.silvania.cities.core.npc.spawner.NPCSpawnerEntity;
import co.uk.silvania.cities.core.world.WorldGen;
import co.uk.silvania.cities.econ.VillageHandlerBlacksmith;
import co.uk.silvania.cities.econ.atm.TileEntityATMEntity;
import co.uk.silvania.cities.econ.store.VanillaItemValueConfig;
import co.uk.silvania.cities.econ.store.entity.TileEntityAdminShop;
import co.uk.silvania.cities.econ.store.entity.TileEntityFloatingShelves;
import net.minecraft.block.Block;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid=FlenixCities_Core.modid, dependencies="after:BuildCraft|Core;after:BuildCraft|Energy", name="FlenixCities", version="0.8.8c")
@NetworkMod(clientSideRequired=true, serverSideRequired=false, 
	clientPacketHandlerSpec = @SidedPacketHandler(channels={"FCitiesPackets", "FCDigiCoinPkt", "FCCardPin", "FCShopPacket", "FCSalePacket"}, packetHandler = ClientPacketHandler.class),
	serverPacketHandlerSpec = @SidedPacketHandler(channels={"FCitiesPackets", "FCDigiCoinPkt", "FCCardPin", "FCShopPacket", "FCSalePacket"}, packetHandler = ServerPacketHandler.class))
public class FlenixCities_Core { 
	
	public static final String modid = "flenixcities";
	
    @Instance(FlenixCities_Core.modid)
    public static FlenixCities_Core instance;
    public static GuiHandler cityGuiHandler = new GuiHandler();

    @SidedProxy(clientSide="co.uk.silvania.cities.core.client.ClientProxy", serverSide="co.uk.silvania.cities.core.CommonProxy")
    public static CommonProxy proxy;
    
	public static CreativeTabs tabCity = new CreativeTabs("tabCity") {
		public ItemStack getIconItemStack() {
			return new ItemStack(CoreBlocks.skyscraperBlocks, 1, 0);
		}
	};
	
	public static CreativeTabs tabEcon = new CreativeTabs("tabEcon") {
		public ItemStack getIconItemStack() {
			return new ItemStack(CoreBlocks.atmBlock, 1, 0);
		}
	};
	
	public static String configPath;
	
	public static WorldGen worldGen = new WorldGen();

	//And finally the worldgen
	//public static WorldGen worldGen = new WorldGen();

	@EventHandler
	public void serverStart(FMLServerStartingEvent event) {
		MinecraftServer server = MinecraftServer.getServer();
	}
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	File cfgfile = new File(event.getModConfigurationDirectory(), "FlenixCities");
    	System.out.println("Checking to see if you're illegally using Technic...");
    	if (cfgfile.getAbsolutePath().toLowerCase().contains(".technic")) {
    		if (cfgfile.getAbsolutePath().toLowerCase().contains("lifemmo")) {
    			System.out.println("Technic has been detected!");
    			System.out.println("Ah, but I see you're using LifeMMO. In that case, an agreement has been reached. Carry on.");
    		} else if (cfgfile.getAbsolutePath().toLowerCase().contains("pixelmon-the-new-age")) {
    			System.out.println("Technic has been detected!");
    			System.out.println("Ah, but I see you're using Pixelmon: The New Age. In that case, an agreement has been reached. Carry on.");
    		} else if (cfgfile.getAbsolutePath().toLowerCase().contains("businesselite")) {
    			System.out.println("Technic has been detected!");
    			System.out.println("Ah, but I see you're using Business Elite. In that case, an agreement has been reached. Carry on.");
    		} else if (cfgfile.getAbsolutePath().toLowerCase().contains("sims-")) {
    			System.out.println("Technic has been detected!");
    			System.out.println("Ah, but I see you're using one of the Sims- packs. In that case, an agreement has been reached. Carry on.");
    		} else if (cfgfile.getAbsolutePath().toLowerCase().contains("econony-plus")) {
    			System.out.println("Technic has been detected!");
    			System.out.println("Ah, but I see you're using the Economy+ pack. In that case, an agreement has been reached. Carry on.");
    		} else if (cfgfile.getAbsolutePath().toLowerCase().contains("kbts-drugs-and-guns-pack")) {
    			System.out.println("Technic has been detected!");
    			System.out.println("Ah, but I see you're using KBT's Drugs and Guns. In that case, an agreement has been reached. Carry on.");
    		} else {
    			System.out.println("##########################################################");
    			System.out.println("##########################################################");
    			System.out.println("##########################################################");
	    		System.out.println("Technic has been detected!");
	    		System.out.println("First, I'd like to thank Gregorius Techneticies for kindly sharing this code with me");
	    		System.out.println("I have decided to block use of Technic due to the Technic Communities horrible approach to us modders");
	    		System.out.println("If such a time comes that they treat us with just a TINY bit of respect, I'll remove this code.");
	    		System.out.println("Until then, Technic is not allowed. Please use FTB or ATLauncher - both are much better anyway.");
	    		System.out.println("www.feed-the-beast.org - www.atlauncher.com");
	    		System.out.println("If you wish to discuss using my mods in Technic, please email me as below.");
	    		System.out.println("I DO let certain people bypass this restriction, and my code allows for that; LifeMMO is a perfect example.");
	    		System.out.println("To meet the criteria you must A) be a public pack, B) be professional, and C) follow the license of all mods you use.");
	    		System.out.println("I reserve the right to deny any Technic pack at any time for any reason I deem fit.");
	    		System.out.println("");
	    		System.out.println("Yours, Flenix (flenix@silvania.co.uk)");
	    		System.out.println("PS. please include your Technic Pack link (The one you give to players for your pack) when contacting me!");
	    		System.out.println("##########################################################");
	    		System.out.println("##########################################################");
	    		System.out.println("##########################################################");
	    		return;
    		}
    	}
    	configPath = event.getModConfigurationDirectory() + "/FlenixCities/";
    	CityConfig.init(configPath);
    	VanillaItemValueConfig.init(configPath + "Prices/");
        
    	NetworkRegistry.instance().registerGuiHandler(this, cityGuiHandler);
        CoreBlocks.init();
        CoreItems.init();
        
        proxy.registerRenderThings();
        proxy.registerRenderers();
        
    	if (proxy.banCheck() == true) {
       		System.out.println("*** IMPORTANT! READ THIS ***");
       		System.out.println("*** IMPORTANT! READ THIS ***");
       		System.out.println("*** IMPORTANT! READ THIS ***");
       		System.out.println("My anti-griefer code has detected you are a griefer, or have somehow negatively impacted my mods.");
       		System.out.println("Due to this, the mod's blocks will no longer load for you.");
       		System.out.println("Upon loading your world, any of my mods blocks will be permanantely removed.");
       		System.out.println("You brought this upon yourself, it's entirely your fault. Enjoy!");
       		System.out.println("*** IMPORTANT! READ THIS ***");
       		System.out.println("*** IMPORTANT! READ THIS ***");
       		System.out.println("*** IMPORTANT! READ THIS ***");
       		return;
    	} else {
    		System.out.println("Yes! They have been a good player. Let's load the mod for them :)");
	        proxy.registerBlocks();
	        proxy.addNames();
	        proxy.entityStuff();
	        
	        MinecraftForge.EVENT_BUS.register(new EventDrops());
	        MinecraftForge.EVENT_BUS.register(new EventListener());
	        
	        GameRegistry.registerTileEntity(TileEntityATMEntity.class, "tileEntityATM");
	        GameRegistry.registerTileEntity(TileEntityFloatingShelves.class, "tileEntityFloatingShelves");
	        GameRegistry.registerTileEntity(TileEntityAdminShop.class, "tileEntityAdminShop");
	        GameRegistry.registerTileEntity(NPCSpawnerEntity.class, "npcSpawnerBlock");
	        
	        LanguageRegistry.instance().addStringLocalization("itemGroup.tabEcon", "en_US", "Cities: Economy");            
	        LanguageRegistry.instance().addStringLocalization("itemGroup.tabCity", "en_US", "Cities: Blocks");
	        
	        GameRegistry.registerWorldGenerator(new WorldGen());
	        
	        OreDictionary.registerOre("oreCopper", new ItemStack(CoreBlocks.copperOre));
	        OreDictionary.registerOre("oreTin", new ItemStack(CoreBlocks.tinOre));
	        OreDictionary.registerOre("oreSilver", new ItemStack(CoreBlocks.silverOre));
	        OreDictionary.registerOre("oreTitanium", new ItemStack(CoreBlocks.titaniumOre));
	        OreDictionary.registerOre("oreRuby", new ItemStack(CoreBlocks.rubyOre));
	        OreDictionary.registerOre("oreTecmonium", new ItemStack(CoreBlocks.tecmoniumOre));
	        OreDictionary.registerOre("oreCrystal", new ItemStack(CoreBlocks.crystalOre));
	        //OreDictionary.registerOre("oreSapphire", new ItemStack(CoreBlocks.sapphireOre));
	        OreDictionary.registerOre("ingotCopper", new ItemStack(CoreItems.copperIngot));
	        OreDictionary.registerOre("ingotTin", new ItemStack(CoreItems.tinIngot));
	        OreDictionary.registerOre("ingotSilver", new ItemStack(CoreItems.silverIngot));
	        OreDictionary.registerOre("ingotImpureTitanium", new ItemStack(CoreItems.titaniumIngot));
	        OreDictionary.registerOre("ingotTecmonium", new ItemStack(CoreItems.tecmoniumIngot));
	        OreDictionary.registerOre("gemRuby", new ItemStack(CoreItems.rubyItem));
	        OreDictionary.registerOre("gemCrystal", new ItemStack(CoreItems.crystalItem));
	        //OreDictionary.registerOre("gemSapphire", new ItemStack(CoreItems.sapphireItem));
    	}
    }
               
    @EventHandler
    public void Init(FMLInitializationEvent event) {
        proxy.addRecipes();
    }


    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	//int posterRenderID = RenderingRegistry.getNextAvailableRenderId();
    	//TODO fix posters
    }
};