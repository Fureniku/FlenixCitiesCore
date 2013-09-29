package co.uk.silvania.cities.core.client;

import co.uk.silvania.cities.core.CommonProxy;
import co.uk.silvania.cities.core.blocks.*;
import co.uk.silvania.cities.core.blocks.atm.TileEntityATMEntity;
import co.uk.silvania.cities.core.blocks.entity.TileEntityFloatingShelves;
import co.uk.silvania.cities.core.client.models.BankerModel;
import co.uk.silvania.cities.core.client.models.TileEntityATMRenderer;
import co.uk.silvania.cities.core.client.models.TileEntityFloatingShelvesRenderer;
import co.uk.silvania.cities.core.npc.EntityBanker;
import co.uk.silvania.cities.core.npc.RenderBanker;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ClientProxy extends CommonProxy {
	
	public static int PosterRenderID;
        
    @Override
    public void registerRenderThings() {
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityATMEntity.class, new TileEntityATMRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFloatingShelves.class, new TileEntityFloatingShelvesRenderer());
    	
        RenderingRegistry.registerEntityRenderingHandler(EntityBanker.class, new RenderBanker(new BankerModel(), 0.5F));
        EntityRegistry.registerGlobalEntityID(EntityBanker.class, "Banker", EntityRegistry.findGlobalUniqueEntityId(), 3515848, 12102);
    }        
    
    /*@Override
    public void registerRenderers() {
    	PosterRenderID = RenderingRegistry.getNextAvailableRenderId();
    	RenderingRegistry.registerBlockHandler(PosterRenderID, new PosterRender());
    }*/
}