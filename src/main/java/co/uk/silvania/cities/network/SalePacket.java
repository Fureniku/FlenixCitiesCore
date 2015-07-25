package co.uk.silvania.cities.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import co.uk.silvania.cities.core.CityConfig;
import co.uk.silvania.cities.core.FlenixCities_Core;
import co.uk.silvania.cities.econ.EconUtils;
import co.uk.silvania.cities.econ.store.container.ContainerAdminShop;
import co.uk.silvania.cities.econ.store.entity.TileEntityAdminShop;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class SalePacket implements IMessage {
	
	private static String packetId;
	private static int slotId;
	private static int x;
	private static int y;
	private static int z;
	
	public SalePacket() {}
	
	public SalePacket(String pktId, int slot, int xPos, int yPos, int zPos) {
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
	
	public static class Handler implements IMessageHandler<SalePacket, IMessage> {

		@Override
		public IMessage onMessage(SalePacket message, MessageContext ctx) {
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
			if (player.openContainer instanceof ContainerAdminShop) {
				ContainerAdminShop container = (ContainerAdminShop) player.openContainer;
				//container.te.xCoord

				TileEntityAdminShop tileAdmin = container.te;//(TileEntityAdminShop) world.getTileEntity(x, y, z);
				if (tileAdmin != null) {
					if (pktId.equalsIgnoreCase("salePacket")) {
						tileAdmin.sellItem(slotId, player);
					} else if (pktId.equalsIgnoreCase("buyPacket")) {
						tileAdmin.buyItem(slotId, player);
					}
				}
			}
			return null;
		}
	}
}
