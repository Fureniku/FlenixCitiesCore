package com.silvaniastudios.cities.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.silvaniastudios.cities.core.CityConfig;
import com.silvaniastudios.cities.econ.EconUtils;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class SoundPacket implements IMessage {
	
	private String soundName;
	
	public SoundPacket() {}
	
	public SoundPacket(String snd) {
		soundName = snd;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		soundName = ByteBufUtils.readUTF8String(buf);	
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, soundName);
	}
	
	public static class Handler implements IMessageHandler<SoundPacket, IMessage> {

		@Override
		public IMessage onMessage(SoundPacket message, MessageContext ctx) {
			EntityPlayer player = (EntityPlayer) ctx.getServerHandler().playerEntity;
			World world = ctx.getServerHandler().playerEntity.worldObj;
			player.worldObj.playSoundAtEntity(player, message.soundName, 1, 1);
			return null;
		}
	}
}
