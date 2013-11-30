package co.uk.silvania.cities.core.client;

import co.uk.silvania.cities.core.CommonProxy;
import co.uk.silvania.cities.core.blocks.*;
import co.uk.silvania.cities.core.blocks.entity.TileEntityFloatingShelves;
import co.uk.silvania.cities.core.client.models.BankerModel;
import co.uk.silvania.cities.core.client.models.TileEntityATMRenderer;
import co.uk.silvania.cities.core.client.models.TileEntityFloatingShelvesRenderer;
import co.uk.silvania.cities.core.client.npcspawner.NPCSpawnerRenderer;
import co.uk.silvania.cities.core.npc.EntityBanker;
import co.uk.silvania.cities.core.npc.RenderBanker;
import co.uk.silvania.cities.core.npc.spawner.NPCSpawnerEntity;
import co.uk.silvania.cities.econ.atm.TileEntityATMEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ClientProxy extends CommonProxy {
	
	public static int PosterRenderID;
	String userName = Minecraft.getMinecraft().getSession().getUsername();
	
	@Override
	public boolean banCheck() {
    	System.out.println("This player's username is... " + userName + "!");
    	System.out.println("Now, have they been good? Let's take a look...");
    	if (userName.equalsIgnoreCase("jesselevi") 
    			|| userName.equalsIgnoreCase("mister__wolters") 
    			|| userName.equalsIgnoreCase("1victor2000") 
    			|| userName.equalsIgnoreCase("sophie_sushi") 
    			|| userName.equalsIgnoreCase("sephiroku")) {
    		return true;
    	} else
    		return false;
	}
        
    @Override
    public void registerRenderThings() {
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityATMEntity.class, new TileEntityATMRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFloatingShelves.class, new TileEntityFloatingShelvesRenderer());
    	
    	ClientRegistry.bindTileEntitySpecialRenderer(NPCSpawnerEntity.class, new NPCSpawnerRenderer());
    	
        RenderingRegistry.registerEntityRenderingHandler(EntityBanker.class, new RenderBanker(new BankerModel(), 0.5F));
    }        
}