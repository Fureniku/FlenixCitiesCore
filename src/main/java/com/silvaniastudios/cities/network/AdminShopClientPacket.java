package com.silvaniastudios.cities.network;

import com.silvaniastudios.cities.core.CityConfig;
import com.silvaniastudios.cities.econ.EconUtils;
import com.silvaniastudios.cities.econ.store.container.ContainerAdminShop;
import com.silvaniastudios.cities.econ.store.entity.TileEntityAdminShop;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class AdminShopClientPacket implements IMessage {
	
	public static String buyPrice1;
	public static String sellPrice1;
	public static String buyPrice2;
	public static String sellPrice2;
	public static String buyPrice3;
	public static String sellPrice3;
	public static String buyPrice4;
	public static String sellPrice4;
	public static int x;
	public static int y;
	public static int z;
	
	public static EconUtils econ = new EconUtils();
	
	public AdminShopClientPacket() {}
	
	public AdminShopClientPacket(String b1, String s1, String b2, String s2, String b3, String s3, String b4, String s4, int xPos, int yPos, int zPos) {
		buyPrice1 = b1;
		sellPrice1 = s1;
		buyPrice2 = b2;
		sellPrice2 = s2;
		buyPrice3 = b3;
		sellPrice3 = s3;
		buyPrice4 = b4;
		sellPrice4 = s4;
		x = xPos;
		y = yPos;
		z = zPos;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		buyPrice1 = ByteBufUtils.readUTF8String(buf);
		sellPrice1 = ByteBufUtils.readUTF8String(buf);
		buyPrice2 = ByteBufUtils.readUTF8String(buf);
		sellPrice2 = ByteBufUtils.readUTF8String(buf);
		buyPrice3 = ByteBufUtils.readUTF8String(buf);
		sellPrice3 = ByteBufUtils.readUTF8String(buf);
		buyPrice4 = ByteBufUtils.readUTF8String(buf);
		sellPrice4 = ByteBufUtils.readUTF8String(buf);
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, "" + buyPrice1);
		ByteBufUtils.writeUTF8String(buf, "" + sellPrice1);
		ByteBufUtils.writeUTF8String(buf, "" + buyPrice2);
		ByteBufUtils.writeUTF8String(buf, "" + sellPrice2);
		ByteBufUtils.writeUTF8String(buf, "" + buyPrice3);
		ByteBufUtils.writeUTF8String(buf, "" + sellPrice3);
		ByteBufUtils.writeUTF8String(buf, "" + buyPrice4);
		ByteBufUtils.writeUTF8String(buf, "" + sellPrice4);
	}
	
	public static class Handler implements IMessageHandler<AdminShopClientPacket, IMessage> {

		@Override
		public IMessage onMessage(AdminShopClientPacket message, MessageContext ctx) {
			if (CityConfig.debugMode) {
				System.out.println("Packet recieved from client regarding prices in shops!");
			}
			EntityPlayer player = ctx.getServerHandler().player;
			
			String str1 = "" + AdminShopClientPacket.buyPrice1;
			String str2 = "" + AdminShopClientPacket.sellPrice1;
			String str3 = "" + AdminShopClientPacket.buyPrice2;
			String str4 = "" + AdminShopClientPacket.sellPrice2;
			String str5 = "" + AdminShopClientPacket.buyPrice3;
			String str6 = "" + AdminShopClientPacket.sellPrice3;
			String str7 = "" + AdminShopClientPacket.buyPrice4;
			String str8 = "" + AdminShopClientPacket.sellPrice4;
			
			if (player.openContainer instanceof ContainerAdminShop) {
				ContainerAdminShop container = (ContainerAdminShop) player.openContainer;
				TileEntityAdminShop tileAdmin = container.te;
				
				if (tileAdmin != null) {
					tileAdmin.buyPrice1 = econ.parseDouble(str1);
					tileAdmin.sellPrice1 = econ.parseDouble(str2);
					tileAdmin.buyPrice2 = econ.parseDouble(str3);
					tileAdmin.sellPrice2 = econ.parseDouble(str4);
					tileAdmin.buyPrice3 = econ.parseDouble(str5);
					tileAdmin.sellPrice3 = econ.parseDouble(str6);
					tileAdmin.buyPrice4 = econ.parseDouble(str7);
					tileAdmin.sellPrice4 = econ.parseDouble(str8);
					
					//Update all players nearby of shop changes (Avoids localized scamming by bumping the price after players come into range)
					//TODO tileAdmin.getDescriptionPacket();
					if (CityConfig.debugMode) {
						System.out.println("Prices have been set, and description packet triggered.");
					}
				} else {
					System.out.println("TE is null. Sadface :(");
				}
			}
			return null;
		}
	}
}
