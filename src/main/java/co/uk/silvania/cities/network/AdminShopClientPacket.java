package co.uk.silvania.cities.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import co.uk.silvania.cities.core.CityConfig;
import co.uk.silvania.cities.econ.EconUtils;
import co.uk.silvania.cities.econ.store.entity.TileEntityAdminShop;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

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
				System.out.println("String 1: " + message.buyPrice1); 
				System.out.println("String 2: " + message.sellPrice1); 
				System.out.println("String 3: " + message.buyPrice2); 
				System.out.println("String 4: " + message.sellPrice2); 
				System.out.println("String 5: " + message.buyPrice3); 
				System.out.println("String 6: " + message.sellPrice3); 
				System.out.println("String 7: " + message.buyPrice4); 
				System.out.println("String 8: " + message.sellPrice4); 
			}
			World world = ctx.getServerHandler().playerEntity.worldObj;
			EntityPlayer player = ctx.getServerHandler().playerEntity;
			
			String str1 = "" + message.buyPrice1;
			String str2 = "" + message.sellPrice1;
			String str3 = "" + message.buyPrice2;
			String str4 = "" + message.sellPrice2;
			String str5 = "" + message.buyPrice3;
			String str6 = "" + message.sellPrice3;
			String str7 = "" + message.buyPrice4;
			String str8 = "" + message.sellPrice4;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			
			TileEntityAdminShop tileAdmin = (TileEntityAdminShop) world.getTileEntity(x, y, z);
			tileAdmin.buyPrice1 = EconUtils.parseDouble(str1);
			tileAdmin.sellPrice1 = EconUtils.parseDouble(str2);
			tileAdmin.buyPrice2 = EconUtils.parseDouble(str3);
			tileAdmin.sellPrice2 = EconUtils.parseDouble(str4);
			tileAdmin.buyPrice3 = EconUtils.parseDouble(str5);
			tileAdmin.sellPrice3 = EconUtils.parseDouble(str6);
			tileAdmin.buyPrice4 = EconUtils.parseDouble(str7);
			tileAdmin.sellPrice4 = EconUtils.parseDouble(str8);
			return null;
		}
	}
}
