package co.uk.silvania.cities;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import co.uk.silvania.cities.core.CoreItems;
import co.uk.silvania.cities.core.NBTConfig;
import co.uk.silvania.cities.econ.DebitCardItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class ServerPacketHandler implements IPacketHandler {
	
	String withdrawAmount = "";

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        if (packet.channel.equals("FCitiesPackets")) {
            handleRandom(packet, player);
        }
    }

    private void handleRandom(Packet250CustomPayload packet, Player player) {
        EntityPlayer entityPlayer = (EntityPlayer) player;
        World world = entityPlayer.worldObj;
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
        System.out.println("Packet get");
        //Used for a stolen card. Defines the player who OWNES the card, not always the one holding it.
        String victimPlayer = DebitCardItem.checkCardOwner(entityPlayer);
        try {
        	withdrawAmount = dis.readUTF();
			System.out.println("Packet Received: " + withdrawAmount);
			
			if (withdrawAmount.equals("failedPin")) {
				if (entityPlayer.getHeldItem().getItem() == CoreItems.debitCard) {
					ItemStack item = entityPlayer.getHeldItem();
                    --item.stackSize;
                    System.out.println("STEALIN YO' CARD BIATCH!");
				}
			} else {
			
	            NBTTagCompound nbt = NBTConfig.getTagCompoundInFile(NBTConfig.getWorldConfig(world));
	            double currentBalance = 0;
	            NBTTagCompound playernbt = nbt.getCompoundTag(victimPlayer);
	            if (nbt.hasKey(victimPlayer)) {
	            	if (playernbt.hasKey("Balance")) {
	                    currentBalance = playernbt.getDouble("Balance");
	                }
	            }
				if (withdrawAmount.equals("withdraw10") && currentBalance >= 10.00) {
					System.out.println("Checking if you clicked withdraw 10...");
					entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note1000));
					double modifiedBalance = currentBalance - 10.0;
					playernbt.setDouble("Balance", modifiedBalance);
					nbt.setCompoundTag(victimPlayer, playernbt);
					NBTConfig.saveConfig(nbt, NBTConfig.getWorldConfig(world));
					System.out.println("You did! I'll give you 10.");
				} else if (withdrawAmount.equals("withdraw20") && currentBalance >= 20.00) {
					entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note2000));
					double modifiedBalance = currentBalance - 20.0;
					playernbt.setDouble("Balance", modifiedBalance);
										nbt.setCompoundTag(victimPlayer, playernbt);
					NBTConfig.saveConfig(nbt, NBTConfig.getWorldConfig(world));
				} else if (withdrawAmount.equals("withdraw50") && currentBalance >= 20.00) {
					entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note5000));
					double modifiedBalance = currentBalance - 50.0;
					playernbt.setDouble("Balance", modifiedBalance);
										nbt.setCompoundTag(victimPlayer, playernbt);
					NBTConfig.saveConfig(nbt, NBTConfig.getWorldConfig(world));
				} else if (withdrawAmount.equals("withdraw100") && currentBalance >= 100.00) {
					entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note10000));
					double modifiedBalance = currentBalance - 100.0;
					playernbt.setDouble("Balance", modifiedBalance);
										nbt.setCompoundTag(victimPlayer, playernbt);
					NBTConfig.saveConfig(nbt, NBTConfig.getWorldConfig(world));
				} else if (withdrawAmount.equals("withdraw250") && currentBalance >= 250.00) {
					entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note10000, 2, 0));
					entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note5000));
					double modifiedBalance = currentBalance - 250.0;
					playernbt.setDouble("Balance", modifiedBalance);
										nbt.setCompoundTag(victimPlayer, playernbt);
					NBTConfig.saveConfig(nbt, NBTConfig.getWorldConfig(world));
				} else if (withdrawAmount.equals("withdraw500") && currentBalance >= 500.00) {
					entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note10000, 5, 0));
					double modifiedBalance = currentBalance - 500.0;
					playernbt.setDouble("Balance", modifiedBalance);
										nbt.setCompoundTag(victimPlayer, playernbt);
					NBTConfig.saveConfig(nbt, NBTConfig.getWorldConfig(world));
				} else {
					entityPlayer.addChatMessage("Get some money, then we'll talk.");
				}
	        }
        }
        catch  (IOException e) {
            System.out.println("Failed to read packet");
        }
        finally {}
    }
}