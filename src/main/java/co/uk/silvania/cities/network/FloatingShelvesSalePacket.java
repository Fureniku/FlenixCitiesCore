package co.uk.silvania.cities.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import co.uk.silvania.cities.core.CityConfig;
import co.uk.silvania.cities.econ.store.container.ContainerFloatingShelves;
import co.uk.silvania.cities.econ.store.entity.TileEntityFloatingShelves;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

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
			String pktId = message.packetId;
			int slotId = message.slotId;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			World world = ctx.getServerHandler().playerEntity.worldObj;
			EntityPlayer player = ctx.getServerHandler().playerEntity;

			if (CityConfig.debugMode) {
				System.out.println("Pkt ID: " + pktId);
				System.out.println("Data: Slot ID:" + slotId + ", X: " + x + ", Y: " + y + ", Z: " + z);
			}
			if (player.openContainer instanceof ContainerFloatingShelves) {
				ContainerFloatingShelves container = (ContainerFloatingShelves) player.openContainer;

				TileEntityFloatingShelves tileShop = container.te;
				if (tileShop != null) {
					if (pktId.equalsIgnoreCase("salePacket")) {
						tileShop.sellItem(slotId, player);
					} else if (pktId.equalsIgnoreCase("buyPacket")) {
						tileShop.buyItem(slotId, player);
					}
				}
			}
			return null;
		}
	}
}
