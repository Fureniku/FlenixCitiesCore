package co.uk.silvania.cities.network;

import co.uk.silvania.cities.econ.EconUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ServerBalancePacket implements IMessage {
	
	public static String balanceAmount;
	
	public ServerBalancePacket() {}
	
	public ServerBalancePacket(String bal) {
		balanceAmount = bal;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		balanceAmount = ByteBufUtils.readUTF8String(buf);
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, balanceAmount);	
	}
	
	public static class Handler implements IMessageHandler<ServerBalancePacket, IMessage> {

		@Override
		public IMessage onMessage(ServerBalancePacket message, MessageContext ctx) {
			System.out.println(String.format("Received %s", message.balanceAmount));
			return null;
		}
		
	}

}
