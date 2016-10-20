package com.silvaniastudios.cities.core.client;

import com.silvaniastudios.cities.core.CommonProxy;
import com.silvaniastudios.cities.core.CoreBlocks;
import com.silvaniastudios.cities.core.client.models.ATMItemRenderer;
import com.silvaniastudios.cities.core.client.models.AdminShelvesItemRenderer;
import com.silvaniastudios.cities.core.client.models.BankerModel;
import com.silvaniastudios.cities.core.client.models.StockChestRenderer;
import com.silvaniastudios.cities.core.client.models.TileEntityATMRenderer;
import com.silvaniastudios.cities.core.client.models.TileEntityAdminShopRenderer;
import com.silvaniastudios.cities.core.client.models.TileEntityFloatingShelvesRenderer;
import com.silvaniastudios.cities.core.client.npcspawner.NPCSpawnerRenderer;
import com.silvaniastudios.cities.core.client.renders.CornerPostRenderer;
import com.silvaniastudios.cities.core.client.renders.LightBlockRenderer;
import com.silvaniastudios.cities.core.client.renders.LightBlockRotateRenderer;
import com.silvaniastudios.cities.core.client.renders.OpenStairsRenderer;
import com.silvaniastudios.cities.core.client.renders.RailingRenderer;
import com.silvaniastudios.cities.core.client.renders.Slope225HorizontalARenderer;
import com.silvaniastudios.cities.core.client.renders.Slope225HorizontalBRenderer;
import com.silvaniastudios.cities.core.client.renders.Slope225VerticalRenderer;
import com.silvaniastudios.cities.core.client.renders.Slope45Renderer;
import com.silvaniastudios.cities.core.client.renders.WalkwayFenceRenderer;
import com.silvaniastudios.cities.core.client.renders.WalkwayStairsRenderer;
import com.silvaniastudios.cities.core.client.renders.WalkwayStairsTraditionalRenderer;
import com.silvaniastudios.cities.core.client.renders.WalkwayTraditionalRenderer;
import com.silvaniastudios.cities.core.npc.EntityBanker;
import com.silvaniastudios.cities.core.npc.RenderBanker;
import com.silvaniastudios.cities.core.npc.spawner.NPCSpawnerEntity;
import com.silvaniastudios.cities.econ.atm.TileEntityATMEntity;
import com.silvaniastudios.cities.econ.store.entity.TileEntityAdminShop;
import com.silvaniastudios.cities.econ.store.entity.TileEntityFloatingShelves;
import com.silvaniastudios.cities.econ.store.entity.TileEntityStockChest;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {
	
	public static int lightBlockRenderID;
	public static int lightBlockRotateRenderID;
	
	public static int walkwayFenceRenderID;
	public static int walkwayFenceSnowyRenderID;
	public static int walkwayFenceJungleRenderID;
	public static int walkwayStairsRenderID;
	public static int railingRenderID;
	public static int railingSnowyRenderID;
	public static int railingJungleRenderID;
	public static int railingStairsRenderID;
	public static int openStairsRenderID;
	public static int slope45RenderID;
	public static int slope225HorizontalARenderID;
	public static int slope225HorizontalBRenderID;
	public static int slope225VerticalRenderID;
	public static int cornerPostRenderID;
	
	public static int walkwayTraditionalRenderID;
	public static int walkwayStairsTraditionalRenderID;
    

	@SideOnly(Side.CLIENT)
    @Override
    public void registerRenderThings() {
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityATMEntity.class, new TileEntityATMRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFloatingShelves.class, new TileEntityFloatingShelvesRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAdminShop.class, new TileEntityAdminShopRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStockChest.class, new StockChestRenderer());
    	
    	MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(CoreBlocks.adminShopBlock), new AdminShelvesItemRenderer());
    	MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(CoreBlocks.floatingShelvesBlock), new AdminShelvesItemRenderer());
    	MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(CoreBlocks.atmBlock), (IItemRenderer) new ATMItemRenderer());
    	
    	ClientRegistry.bindTileEntitySpecialRenderer(NPCSpawnerEntity.class, new NPCSpawnerRenderer());
    	
        RenderingRegistry.registerEntityRenderingHandler(EntityBanker.class, new RenderBanker(new BankerModel(), 0.5F));
        
        lightBlockRenderID = RenderingRegistry.getNextAvailableRenderId();
        lightBlockRotateRenderID = RenderingRegistry.getNextAvailableRenderId();

        walkwayFenceRenderID = RenderingRegistry.getNextAvailableRenderId();
		walkwayFenceSnowyRenderID = RenderingRegistry.getNextAvailableRenderId();
		walkwayFenceJungleRenderID = RenderingRegistry.getNextAvailableRenderId();
		walkwayStairsRenderID = RenderingRegistry.getNextAvailableRenderId();
		railingRenderID = RenderingRegistry.getNextAvailableRenderId();
		railingSnowyRenderID = RenderingRegistry.getNextAvailableRenderId();
		railingJungleRenderID = RenderingRegistry.getNextAvailableRenderId();
		railingStairsRenderID = RenderingRegistry.getNextAvailableRenderId();
		openStairsRenderID = RenderingRegistry.getNextAvailableRenderId();
		slope45RenderID = RenderingRegistry.getNextAvailableRenderId();
		slope225HorizontalARenderID = RenderingRegistry.getNextAvailableRenderId();
		slope225HorizontalBRenderID = RenderingRegistry.getNextAvailableRenderId();
		slope225VerticalRenderID = RenderingRegistry.getNextAvailableRenderId();
		cornerPostRenderID = RenderingRegistry.getNextAvailableRenderId();
        
		walkwayTraditionalRenderID = RenderingRegistry.getNextAvailableRenderId();
		walkwayStairsTraditionalRenderID = RenderingRegistry.getNextAvailableRenderId();
        
        RenderingRegistry.registerBlockHandler(CoreBlocks.lightingBlocks.getRenderType(), new LightBlockRenderer());
        RenderingRegistry.registerBlockHandler(CoreBlocks.lightingRotateBlocks.getRenderType(), new LightBlockRotateRenderer());
        
        RenderingRegistry.registerBlockHandler(walkwayFenceRenderID, new WalkwayFenceRenderer(false, false));
		RenderingRegistry.registerBlockHandler(walkwayFenceSnowyRenderID, new WalkwayFenceRenderer(true, false));
		RenderingRegistry.registerBlockHandler(walkwayFenceJungleRenderID, new WalkwayFenceRenderer(false, true));
		RenderingRegistry.registerBlockHandler(walkwayStairsRenderID, new WalkwayStairsRenderer());
		RenderingRegistry.registerBlockHandler(openStairsRenderID, new OpenStairsRenderer());
		RenderingRegistry.registerBlockHandler(railingRenderID, new RailingRenderer(false, false));
		RenderingRegistry.registerBlockHandler(railingSnowyRenderID, new RailingRenderer(true, false));
		RenderingRegistry.registerBlockHandler(railingJungleRenderID, new RailingRenderer(false, true));
		RenderingRegistry.registerBlockHandler(slope45RenderID, new Slope45Renderer());
		RenderingRegistry.registerBlockHandler(slope225HorizontalARenderID, new Slope225HorizontalARenderer());
		RenderingRegistry.registerBlockHandler(slope225HorizontalBRenderID, new Slope225HorizontalBRenderer());
		RenderingRegistry.registerBlockHandler(slope225VerticalRenderID, new Slope225VerticalRenderer());
		RenderingRegistry.registerBlockHandler(cornerPostRenderID, new CornerPostRenderer());
		
		RenderingRegistry.registerBlockHandler(walkwayTraditionalRenderID, new WalkwayTraditionalRenderer());
		RenderingRegistry.registerBlockHandler(walkwayStairsTraditionalRenderID, new WalkwayStairsTraditionalRenderer());
        
        RenderingRegistry.registerBlockHandler(CoreBlocks.oakWalkwayStairs.getRenderType(), new WalkwayStairsRenderer());
    }        
}