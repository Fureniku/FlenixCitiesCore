package com.silvaniastudios.cities.network;

import com.silvaniastudios.cities.core.CityConfig;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ServerBalancePacket implements IMessage {
	
	public static String balanceAmount = "";
	
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
			if (CityConfig.debugMode) {
				System.out.println(String.format("Received %s", ServerBalancePacket.balanceAmount));
			}
			return null;
		}
		
	}

}
