package co.uk.silvania.cities.core.client;

import net.minecraft.client.Minecraft;
import co.uk.silvania.cities.core.CommonProxy;
import co.uk.silvania.cities.core.client.models.*;
import co.uk.silvania.cities.core.client.npcspawner.NPCSpawnerRenderer;
import co.uk.silvania.cities.core.npc.EntityBanker;
import co.uk.silvania.cities.core.npc.RenderBanker;
import co.uk.silvania.cities.core.npc.spawner.NPCSpawnerEntity;
import co.uk.silvania.cities.econ.atm.TileEntityATMEntity;
import co.uk.silvania.cities.econ.store.entity.*;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	
	public static int PosterRenderID;
	String userName = Minecraft.getMinecraft().getSession().getUsername();
	
	@Override
	public boolean banCheck() {
    	System.out.println("This player's username is... " + userName + "!");
    	System.out.println("Now, have they been good? Let's take a look...");
    	if (userName.equalsIgnoreCase("shobu9")) {
    		return true;
    	} else
    		return false;
	}
        
    @Override
    public void registerRenderThings() {
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityATMEntity.class, new TileEntityATMRenderer());
    	//ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFloatingShelves.class, new TileEntityFloatingShelvesRenderer());
    	//ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAdminShop.class, new AdminShelvesRenderer());
    	//ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAutoShelf.class, new TileEntityAutoShelfRenderer());
    	
    	//TODO MinecraftForgeClient.registerItemRenderer(CoreBlocks.adminShopBlock.blockID, new AdminShelvesItemRenderer());
    	//MinecraftForgeClient.registerItemRenderer(CoreBlocks.floatingShelvesBlock.blockID, new AdminShelvesItemRenderer());
    	//MinecraftForgeClient.registerItemRenderer(CoreBlocks.atmBlock.blockID, new ATMItemRenderer());
    	
    	ClientRegistry.bindTileEntitySpecialRenderer(NPCSpawnerEntity.class, new NPCSpawnerRenderer());
    	
        RenderingRegistry.registerEntityRenderingHandler(EntityBanker.class, new RenderBanker(new BankerModel(), 0.5F));
    }        
}