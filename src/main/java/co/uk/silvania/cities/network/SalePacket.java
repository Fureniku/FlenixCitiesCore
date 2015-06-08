package co.uk.silvania.cities.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import co.uk.silvania.cities.core.CityConfig;
import co.uk.silvania.cities.core.FlenixCities_Core;
import co.uk.silvania.cities.econ.EconUtils;
import co.uk.silvania.cities.econ.store.entity.TileEntityAdminShop;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class SalePacket implements IMessage {
	
	private String packetId;
	private int slotId;
	private int x;
	private int y;
	private int z;
	
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
		x = ByteBufUtils.readVarShort(buf);
		y = ByteBufUtils.readVarShort(buf);
		z = ByteBufUtils.readVarShort(buf);
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, packetId);	
		ByteBufUtils.writeVarShort(buf, slotId);
		ByteBufUtils.writeVarShort(buf, x);
		ByteBufUtils.writeVarShort(buf, y);
		ByteBufUtils.writeVarShort(buf, z);
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
			EntityPlayer entityPlayer = ctx.getServerHandler().playerEntity;
			
			TileEntity tileEntity = (TileEntity) world.getTileEntity(x, y, z);
			if (CityConfig.debugMode) {
				System.out.println("Pkt ID: " + pktId);
				System.out.println("Data: Slot ID:" + slotId + ", X: " + x + ", Y: " + y + ", Z: " + z);
			}
			TileEntityAdminShop tileAdmin = (TileEntityAdminShop) world.getTileEntity(x, y, z);
			if (pktId.equalsIgnoreCase("salePacket")) {
				tileAdmin.sellItem(slotId, 1, entityPlayer);
			} else if (pktId.equalsIgnoreCase("buyPacket")) {
				tileAdmin.buyItem(slotId, 1, entityPlayer);
			}
			return null;
		}
	}
}
