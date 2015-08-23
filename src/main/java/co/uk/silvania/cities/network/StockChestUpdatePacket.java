package co.uk.silvania.cities.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import co.uk.silvania.cities.core.CityConfig;
import co.uk.silvania.cities.econ.EconUtils;
import co.uk.silvania.cities.econ.store.container.ContainerStockChest;
import co.uk.silvania.cities.econ.store.entity.TileEntityStockChest;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class StockChestUpdatePacket implements IMessage {
	
	public static String toggleBuy;
	public static String toggleSell;
	public static String buyFundLimit;

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
				System.out.println("String 1: " + message.toggleBuy); 
				System.out.println("String 2: " + message.toggleSell); 
				System.out.println("String 3: " + message.buyFundLimit); 
			}
			World world = ctx.getServerHandler().playerEntity.worldObj;
			EntityPlayer player = ctx.getServerHandler().playerEntity;
			
			String str1 = "" + message.toggleBuy;
			String str2 = "" + message.toggleSell;
			String str3 = "" + message.buyFundLimit;
			
			if (player.openContainer instanceof ContainerStockChest) {
				ContainerStockChest container = (ContainerStockChest) player.openContainer;
				TileEntityStockChest tileEntity = container.te;
				
				if (tileEntity != null) {					
					tileEntity.buying = Boolean.valueOf(str1);
					tileEntity.selling = Boolean.valueOf(str2);
					tileEntity.buyFundLimit = EconUtils.parseDouble(str3);

					tileEntity.getDescriptionPacket();
					
					if (CityConfig.debugMode) {
						System.out.println("Stock Chest description packet triggered.");
					}
				}
			}
			return null;
		}
	}
}