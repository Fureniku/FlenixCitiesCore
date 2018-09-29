package com.silvaniastudios.cities.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ShelfRenderPacket implements IMessage {
	
	private String text;
	
	public ShelfRenderPacket() { }
	
	public ShelfRenderPacket(String text) {
		this.text = text;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		text = ByteBufUtils.readUTF8String(buf);
	}

	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, text);
	}
	
	public static class Handler implements IMessageHandler<ShelfRenderPacket, IMessage> {
		
		@Override
		public IMessage onMessage(ShelfRenderPacket message, MessageContext ctx) {
			return null;
		}
	}
}
