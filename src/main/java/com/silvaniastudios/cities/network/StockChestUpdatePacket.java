package com.silvaniastudios.cities.network;

import com.silvaniastudios.cities.core.CityConfig;
import com.silvaniastudios.cities.econ.EconUtils;
import com.silvaniastudios.cities.econ.store.container.ContainerStockChest;
import com.silvaniastudios.cities.econ.store.entity.TileEntityStockChest;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class StockChestUpdatePacket implements IMessage {
	
	public static String toggleBuy;
	public static String toggleSell;
	public static String buyFundLimit;
	public static EconUtils econ = new EconUtils();

	public StockChestUpdatePacket() {}
	
	public StockChestUpdatePacket(String buy, String sell, String fundLimit) {
		toggleBuy = buy;
		toggleSell = sell;
		buyFundLimit = fundLimit;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		toggleBuy = ByteBufUtils.readUTF8String(buf);
		toggleSell = ByteBufUtils.readUTF8String(buf);
		buyFundLimit = ByteBufUtils.readUTF8String(buf);		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, "" + toggleBuy);
		ByteBufUtils.writeUTF8String(buf, "" + toggleSell);
		ByteBufUtils.writeUTF8String(buf, "" + buyFundLimit);
	}
	
	public static class Handler implements IMessageHandler<StockChestUpdatePacket, IMessage> {

		@Override
		public IMessage onMessage(StockChestUpdatePacket message, MessageContext ctx) {
			if (CityConfig.debugMode) {
				System.out.println("Packet recieved from client regarding Stock Chests!");
				System.out.println("String 1: " + StockChestUpdatePacket.toggleBuy); 
				System.out.println("String 2: " + StockChestUpdatePacket.toggleSell); 
				System.out.println("String 3: " + StockChestUpdatePacket.buyFundLimit); 
			}
			
			EntityPlayer player = ctx.getServerHandler().player;
			
			String str1 = "" + StockChestUpdatePacket.toggleBuy;
			String str2 = "" + StockChestUpdatePacket.toggleSell;
			String str3 = "" + StockChestUpdatePacket.buyFundLimit;
			
			if (player.openContainer instanceof ContainerStockChest) {
				ContainerStockChest container = (ContainerStockChest) player.openContainer;
				TileEntityStockChest tileEntity = container.te;
				
				if (tileEntity != null) {					
					tileEntity.buying = Boolean.valueOf(str1);
					tileEntity.selling = Boolean.valueOf(str2);
					tileEntity.buyFundLimit = econ.parseDouble(str3);

					//TODO tileEntity.getDescriptionPacket();
					
					if (CityConfig.debugMode) {
						System.out.println("Stock Chest description packet triggered.");
					}
				}
			}
			return null;
		}
	}
}