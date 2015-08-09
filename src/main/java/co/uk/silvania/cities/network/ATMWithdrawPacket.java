package co.uk.silvania.cities.network;

import co.uk.silvania.cities.core.CityConfig;
import co.uk.silvania.cities.core.FlenixCities_Core;
import co.uk.silvania.cities.econ.EconUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ATMWithdrawPacket implements IMessage {
	
	private int withdrawAmount;
	
	public ATMWithdrawPacket() {}
	
	public ATMWithdrawPacket(int withdrawAmt) {
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
	
	public static class Handler implements IMessageHandler<ATMWithdrawPacket, IMessage> {

		@Override
		public IMessage onMessage(ATMWithdrawPacket message, MessageContext ctx) {
			EntityPlayer player = (EntityPlayer) ctx.getServerHandler().playerEntity;
			EconUtils.withdrawFunds(message.withdrawAmount, player, player.worldObj);
			FlenixCities_Core.network.sendTo(new ServerBalancePacket(""+EconUtils.getBalance(player, player.worldObj)), (EntityPlayerMP) player);
			if (CityConfig.debugMode) {
				System.out.println(String.format("Received %s from %s", message.withdrawAmount, ctx.getServerHandler().playerEntity.getDisplayName()));
			}
			return null;
		}
	}
}
