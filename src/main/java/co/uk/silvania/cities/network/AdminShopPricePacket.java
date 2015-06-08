package co.uk.silvania.cities.network;

import co.uk.silvania.cities.core.FlenixCities_Core;
import co.uk.silvania.cities.econ.EconUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class AdminShopPricePacket implements IMessage {
	
	private String ownerName;
	private double buyPrice1;
	private double sellPrice1;
	private double buyPrice2;
	private double sellPrice2;
	private double buyPrice3;
	private double sellPrice3;
	private double buyPrice4;
	private double sellPrice4;
	
	public AdminShopPricePacket() {}
	
	public AdminShopPricePacket(String owner, double b1, double s1, double b2, double s2, double b3, double s3, double b4, double s4) {
		ownerName = owner;
		buyPrice1 = b1;
		sellPrice1 = s1;
		buyPrice2 = b2;
		sellPrice2 = s2;
		buyPrice3 = b3;
		sellPrice3 = s3;
		buyPrice4 = b4;
		sellPrice4 = s4;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		ownerName = ByteBufUtils.readUTF8String(buf);
		buyPrice1 = EconUtils.parseDouble(ByteBufUtils.readUTF8String(buf));
		sellPrice1 = EconUtils.parseDouble(ByteBufUtils.readUTF8String(buf));
		buyPrice2 = EconUtils.parseDouble(ByteBufUtils.readUTF8String(buf));
		sellPrice2 = EconUtils.parseDouble(ByteBufUtils.readUTF8String(buf));
		buyPrice3 = EconUtils.parseDouble(ByteBufUtils.readUTF8String(buf));
		sellPrice3 = EconUtils.parseDouble(ByteBufUtils.readUTF8String(buf));
		buyPrice4 = EconUtils.parseDouble(ByteBufUtils.readUTF8String(buf));
		sellPrice4 = EconUtils.parseDouble(ByteBufUtils.readUTF8String(buf));
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, ownerName);
		ByteBufUtils.writeUTF8String(buf, "" + buyPrice1);
		ByteBufUtils.writeUTF8String(buf, "" + sellPrice1);
		ByteBufUtils.writeUTF8String(buf, "" + buyPrice2);
		ByteBufUtils.writeUTF8String(buf, "" + sellPrice2);
		ByteBufUtils.writeUTF8String(buf, "" + buyPrice3);
		ByteBufUtils.writeUTF8String(buf, "" + sellPrice3);
		ByteBufUtils.writeUTF8String(buf, "" + buyPrice4);
		ByteBufUtils.writeUTF8String(buf, "" + sellPrice4);
	}
	
	public static class Handler implements IMessageHandler<AdminShopPricePacket, IMessage> {

		@Override
		public IMessage onMessage(AdminShopPricePacket message, MessageContext ctx) {
			System.out.println(String.format("Packet recieved! 9 bits of data? %s %s %s %s %s etc", message.ownerName, message.buyPrice1, message.sellPrice1, message.buyPrice2, message.sellPrice2));
			return null;
		}
	}
}
