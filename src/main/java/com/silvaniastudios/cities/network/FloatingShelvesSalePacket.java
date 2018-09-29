package com.silvaniastudios.cities.network;

import com.silvaniastudios.cities.core.CityConfig;
import com.silvaniastudios.cities.econ.store.container.ContainerFloatingShelves;
import com.silvaniastudios.cities.econ.store.entity.TileEntityFloatingShelves;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class FloatingShelvesSalePacket implements IMessage {
	
	private static String packetId;
	private static int slotId;
	private static int x;
	private static int y;
	private static int z;
	
	public FloatingShelvesSalePacket() {}
	
	public FloatingShelvesSalePacket(String pktId, int slot, int xPos, int yPos, int zPos) {
		packetId = pktId;
		slotId = slot;
		x = xPos;
		y = yPos;
		z = zPos;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		packetId = ByteBufUtils.readUTF8String(buf);
		slotId = ByteBufUtils.readVarShort(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, packetId);	
		ByteBufUtils.writeVarShort(buf, slotId);
	}
	
	public static class Handler implements IMessageHandler<FloatingShelvesSalePacket, IMessage> {

		@Override
		public IMessage onMessage(FloatingShelvesSalePacket message, MessageContext ctx) {
			String pktId = FloatingShelvesSalePacket.packetId;
			int slotId = FloatingShelvesSalePacket.slotId;
			int x = FloatingShelvesSalePacket.x;
			int y = FloatingShelvesSalePacket.y;
			int z = FloatingShelvesSalePacket.z;
			
			EntityPlayer player = ctx.getServerHandler().player;

			if (CityConfig.debugMode) {
				System.out.println("Pkt ID: " + pktId);
				System.out.println("Data: Slot ID:" + slotId + ", X: " + x + ", Y: " + y + ", Z: " + z);
			}
			if (player.openContainer instanceof ContainerFloatingShelves) {
				ContainerFloatingShelves container = (ContainerFloatingShelves) player.openContainer;

				TileEntityFloatingShelves tileShop = container.te;
				if (tileShop != null) {
					/* TODO if (pktId.equalsIgnoreCase("salePacket")) {
						tileShop.sellItem(slotId, player);
					} else if (pktId.equalsIgnoreCase("buyPacket")) {
						tileShop.buyItem(slotId, player);
					}*/
				}
			}
			return null;
		}
	}
}
