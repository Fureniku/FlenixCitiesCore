package co.uk.silvania.cities.network;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

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
