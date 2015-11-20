package co.uk.silvania.cities.core.client;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import co.uk.silvania.cities.core.CommonProxy;
import co.uk.silvania.cities.core.CoreBlocks;
import co.uk.silvania.cities.core.client.models.*;
import co.uk.silvania.cities.core.client.npcspawner.NPCSpawnerRenderer;
import co.uk.silvania.cities.core.client.renders.LightBlockRenderer;
import co.uk.silvania.cities.core.client.renders.LightBlockRotateRenderer;
import co.uk.silvania.cities.core.client.renders.WalkwayRenderer;
import co.uk.silvania.cities.core.npc.EntityBanker;
import co.uk.silvania.cities.core.npc.RenderBanker;
import co.uk.silvania.cities.core.npc.spawner.NPCSpawnerEntity;
import co.uk.silvania.cities.econ.atm.TileEntityATMEntity;
import co.uk.silvania.cities.econ.store.entity.*;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy {
	
	public static int lightBlockRenderID;
	public static int lightBlockRotateRenderID;
	public static int walkwayRenderID;

	@SideOnly(Side.CLIENT)
    @Override
    public void registerRenderThings() {
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityATMEntity.class, new TileEntityATMRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFloatingShelves.class, new TileEntityFloatingShelvesRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAdminShop.class, new AdminShelvesRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStockChest.class, new StockChestRenderer());
    	
    	MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(CoreBlocks.adminShopBlock), new AdminShelvesItemRenderer());
    	MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(CoreBlocks.floatingShelvesBlock), new AdminShelvesItemRenderer());
    	MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(CoreBlocks.atmBlock), (IItemRenderer) new ATMItemRenderer());
    	
    	ClientRegistry.bindTileEntitySpecialRenderer(NPCSpawnerEntity.class, new NPCSpawnerRenderer());
    	
        RenderingRegistry.registerEntityRenderingHandler(EntityBanker.class, new RenderBanker(new BankerModel(), 0.5F));
        
        lightBlockRenderID = RenderingRegistry.getNextAvailableRenderId();
        lightBlockRotateRenderID = RenderingRegistry.getNextAvailableRenderId();
        walkwayRenderID = RenderingRegistry.getNextAvailableRenderId();
        
        RenderingRegistry.registerBlockHandler(CoreBlocks.lightingBlocks.getRenderType(), new LightBlockRenderer());
        RenderingRegistry.registerBlockHandler(CoreBlocks.lightingRotateBlocks.getRenderType(), new LightBlockRotateRenderer());
        RenderingRegistry.registerBlockHandler(CoreBlocks.oakWalkway.getRenderType(), new WalkwayRenderer());
    }        
}