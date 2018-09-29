package com.silvaniastudios.cities.network;

import com.silvaniastudios.cities.core.FlenixCities;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

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
			EntityPlayer player = (EntityPlayer) ctx.getServerHandler().player;
			SoundEvent sound = new SoundEvent(new ResourceLocation(FlenixCities.MODID, message.soundName));
			player.world.playSound(player, player.getPosition(), sound, SoundCategory.MASTER, 1.0F, 1.0F);
			return null;
		}
	}
}
