package com.silvaniastudios.cities.network;

import com.silvaniastudios.cities.core.CityConfig;
import com.silvaniastudios.cities.core.FlenixCities;
import com.silvaniastudios.cities.econ.EconUtils;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ATMWithdrawPacket implements IMessage {
	
	private int withdrawAmount;
	public static EconUtils econ = new EconUtils();
	
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
			EntityPlayer player = (EntityPlayer) ctx.getServerHandler().player;
			econ.withdrawFunds(message.withdrawAmount, player);
			FlenixCities.network.sendTo(new ServerBalancePacket(""+econ.getBalance(player)), (EntityPlayerMP) player);
			if (CityConfig.debugMode) {
				System.out.println(String.format("Received %s from %s", message.withdrawAmount, ctx.getServerHandler().player.getDisplayName()));
			}
			return null;
		}
	}
}
