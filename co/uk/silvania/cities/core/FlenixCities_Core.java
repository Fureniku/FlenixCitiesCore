package co.uk.silvania.cities.core;

import co.uk.silvania.cities.GuiHandler;
import co.uk.silvania.cities.ServerPacketHandler;
import co.uk.silvania.cities.WorldGen;
import co.uk.silvania.cities.ClientPacketHandler;
import co.uk.silvania.cities.core.blocks.atm.TileEntityATMEntity;
import co.uk.silvania.cities.core.blocks.entity.TileEntityFloatingShelves;
import co.uk.silvania.cities.core.items.CraftingIngredientItems;
import co.uk.silvania.cities.core.npc.EntityBanker;
import net.minecraft.block.Block;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
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

@Mod(modid=FlenixCities_Core.modid, name="FlenixCities", version="0.4.1")
@NetworkMod(clientSideRequired=true, serverSideRequired=false, 
	clientPacketHandlerSpec = @SidedPacketHandler(channels={"FCitiesPackets"}, packetHandler = ClientPacketHandler.class),
	serverPacketHandlerSpec = @SidedPacketHandler(channels={"FCitiesPackets"}, packetHandler = ServerPacketHandler.class))
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
	
	//public static Block verticalPoster1;
	//public static Block verticalPoster2;
	//public static Block verticalPoster3;
	//public static Block verticalPoster4;
	
	//public static Block horizontalPoster1;
	//public static Block horizontalPoster2;
	//public static Block horizontalPoster3;
	//public static Block horizontalPoster4;

	
	public static WorldGen worldGen = new WorldGen();

	//And finally the worldgen
	//public static WorldGen worldGen = new WorldGen();

	@EventHandler
	public void serverStart(FMLServerStartingEvent event) {
		MinecraftServer server = MinecraftServer.getServer();
	}
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	
    	configPath = event.getModConfigurationDirectory() + "/FlenixCities/";
    	CityConfig.init(configPath);
    	
    	//Start entity stuff here 
    	//EntityList.addMapping(EntityBanker.class, "Banker", EntityRegistry.findGlobalUniqueEntityId(), 3515848, 12102);
        proxy.registerRenderThings();
        proxy.registerRenderers();
        //proxy.entityStuff();
        //End it here.
        
    	NetworkRegistry.instance().registerGuiHandler(this, cityGuiHandler);
        CoreBlocks.init();
        CoreItems.init();
    }
               
    @EventHandler
    public void Init(FMLInitializationEvent event) {
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
    	} else {
    		System.out.println("Yes! They have been a good player. Let's load the mod for them :)");
	        proxy.registerBlocks();
	        proxy.addNames();
	        proxy.addRecipes();
	        
	        MinecraftForge.EVENT_BUS.register(new EventDrops());
	        
	        GameRegistry.registerTileEntity(TileEntityATMEntity.class, "tileEntityATM");
	        GameRegistry.registerTileEntity(TileEntityFloatingShelves.class, "tileEntityFloatingShelves");
	        
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
	        OreDictionary.registerOre("oreSapphire", new ItemStack(CoreBlocks.sapphireOre));
	        OreDictionary.registerOre("ingotCopper", new ItemStack(CoreItems.copperIngot));
	        OreDictionary.registerOre("ingotTin", new ItemStack(CoreItems.tinIngot));
	        OreDictionary.registerOre("ingotSilver", new ItemStack(CoreItems.silverIngot));
	        OreDictionary.registerOre("ingotTitanium", new ItemStack(CoreItems.titaniumIngot));
	        OreDictionary.registerOre("ingotTecmonium", new ItemStack(CoreItems.tecmoniumIngot));
	        OreDictionary.registerOre("gemRuby", new ItemStack(CoreItems.rubyItem));
	        OreDictionary.registerOre("gemCrystal", new ItemStack(CoreItems.crystalItem));
	        OreDictionary.registerOre("gemSapphire", new ItemStack(CoreItems.sapphireItem));
    	}
    }


    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	int posterRenderID = RenderingRegistry.getNextAvailableRenderId();
    	//TODO fix posters
    }
};