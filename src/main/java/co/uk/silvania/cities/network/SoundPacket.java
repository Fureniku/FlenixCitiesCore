package co.uk.silvania.cities.network;

import co.uk.silvania.cities.core.CityConfig;
import co.uk.silvania.cities.econ.EconUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
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
			if (CityConfig.debugMode) {
				System.out.println(player.getDisplayName() + " is requesting a balance update.");
			}
			return new ServerBalancePacket("" + EconUtils.getBalance(player, world));
		}
	}
}
