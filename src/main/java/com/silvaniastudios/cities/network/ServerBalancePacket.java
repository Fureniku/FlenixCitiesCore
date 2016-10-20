package com.silvaniastudios.cities.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

import com.silvaniastudios.cities.core.CityConfig;
import com.silvaniastudios.cities.econ.EconUtils;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

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
				System.out.println(String.format("Received %s", message.balanceAmount));
			}
			return null;
		}
		
	}

}
