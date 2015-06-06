package co.uk.silvania.cities.network;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ATMPacket implements IMessage {
	
	public int withdrawAmount;
	
	public ATMPacket(int withdrawAmt) {
		withdrawAmount = withdrawAmt;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		withdrawAmount = ByteBufUtils.readVarShort(buf);
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeVarShort(buf, withdrawAmount);	
	}
	
	public static class Handler implements IMessageHandler<ATMPacket, IMessage> {

		@Override
		public IMessage onMessage(ATMPacket message, MessageContext ctx) {
			System.out.println(String.format("Received %s from %s", message.withdrawAmount, ctx.getServerHandler().playerEntity.getDisplayName()));
			return null;
		}
		
	}

}
