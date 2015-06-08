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
			EntityPlayer entityPlayer = ctx.getServerHandler().playerEntity;

			if (CityConfig.debugMode) {
				System.out.println("Pkt ID: " + pktId);
				System.out.println("Data: Slot ID:" + slotId + ", X: " + x + ", Y: " + y + ", Z: " + z);
			}
			
			TileEntityAdminShop tileAdmin = (TileEntityAdminShop) world.getTileEntity(x, y, z);
			if (pktId.equalsIgnoreCase("salePacket")) {
				System.out.println("Attempt sell item");
				tileAdmin.ownerName = "Fucktard";
				System.out.println("Set owner name to fucktard.");
				System.out.println("Lets check that shall we? What's his name again? " + tileAdmin.ownerName);
				System.out.println("Alright now, wasn't that fun? Let's try something else. " + tileAdmin.buyPrice1);
				System.out.println("The itch has(n't) been cured!");
				//sellItem(slotId, entityPlayer, x, y, z);
			} else if (pktId.equalsIgnoreCase("buyPacket")) {
				//tileAdmin.buyItem(slotId, 1, entityPlayer);
			}
			return null;
		}
		
		public void sellItem(int slotId, EntityPlayer entityPlayer, int x, int y, int z) {
			World world = entityPlayer.worldObj;
			TileEntityAdminShop tileAdmin = (TileEntityAdminShop) world.getTileEntity(x, y, z);
			double itemCost = 0;
			if (slotId == 1) {
				itemCost = tileAdmin.buyPrice1;
			}
			if (slotId == 2) {
				itemCost = tileAdmin.buyPrice2;
			}
			if (slotId == 3) {
				itemCost = tileAdmin.buyPrice3;
			}
			if (slotId == 4) {
				itemCost = tileAdmin.buyPrice4;
			}
			double invCash = EconUtils.getInventoryCash(entityPlayer);
			int giveAmount = 1;
			ItemStack item = tileAdmin.getStackInSlot(slotId - 1);
			boolean hasSpace = EconUtils.inventoryHasSpace(entityPlayer, item);
			
			if (invCash >= itemCost && hasSpace) {
				//Two birds, one stone. Charges the player for us, then tells us how much they paid so we can calculate change.
				double paidAmount = EconUtils.findCashInInventoryWithChange(entityPlayer, itemCost); //Complex code to charge the player's inventory
				if (paidAmount < 0) {
					return;
				} else {
					while (giveAmount >= 1) {
						entityPlayer.inventory.addItemStackToInventory(item.copy());
						giveAmount--;
					}
					System.out.println(entityPlayer.getDisplayName() + " bought " + item.stackSize + " " + item.getDisplayName() + " from the server, for $" + EconUtils.formatBalance(itemCost));
					entityPlayer.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You bought " + EnumChatFormatting.GOLD + item.stackSize + " " + item.getDisplayName() + EnumChatFormatting.GREEN + " from the server for " + EnumChatFormatting.DARK_GREEN + "$" + EconUtils.formatBalance(itemCost) + "!"));
				}
				//Disabled due to money dupe glitch, will be fixed when I come back to FC in a week or so.
			}// else {
				if (invCash < itemCost) {
					double bankBalance = EconUtils.getBalance(entityPlayer, entityPlayer.getEntityWorld());
					if (bankBalance >= itemCost && hasSpace) {
						if (EconUtils.hasOwnCard(entityPlayer)) {
							if (EconUtils.chargePlayerAnywhere(entityPlayer, itemCost)) {
								while (giveAmount >= 1) {
									entityPlayer.inventory.addItemStackToInventory(new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));
									giveAmount--;
								}
								entityPlayer.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You bought " + EnumChatFormatting.GOLD + item.stackSize + " " + item.getDisplayName() + EnumChatFormatting.GREEN + " from the server for " + EnumChatFormatting.DARK_GREEN + "$" + EconUtils.formatBalance(itemCost) + "!"));
								entityPlayer.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You didn't have enough money with you, so the cost was split between your cash, and your bank balance. Your remaining bank balance is $" + EnumChatFormatting.GOLD + EconUtils.formatBalance(EconUtils.getBalance(entityPlayer, world))));
							}
						}
					} else if (bankBalance >= itemCost && hasSpace) {
						if (EconUtils.hasOwnCard(entityPlayer)) {
							if (EconUtils.payBalanceByCard(entityPlayer, itemCost)) {
								while (giveAmount >= 1) {
									entityPlayer.inventory.addItemStackToInventory(new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));
									giveAmount--;
								}
								entityPlayer.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You bought " + EnumChatFormatting.GOLD + item.stackSize + " " + item.getDisplayName() + EnumChatFormatting.GREEN + " from the server for " + EnumChatFormatting.DARK_GREEN + "$" + EconUtils.formatBalance(itemCost) + "!"));
								entityPlayer.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You didn't have enough money with you, so it was charged to your bank account instead. Your remaining bank balance is $" + EnumChatFormatting.GOLD + EconUtils.formatBalance(EconUtils.getBalance(entityPlayer, world))));
							}
						}
					} else {
						if (hasSpace) {
							entityPlayer.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "You do not have enough money to do that! Next time, why not pay by card?"));
						}
					}
				}
			if (!hasSpace) {
				entityPlayer.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "You do not have enough free inventory space to buy that!"));
			}
		}
	}
}
