package co.uk.silvania.cities.core;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.output.ByteArrayOutputStream;

import co.uk.silvania.cities.econ.DebitCardItem;
import co.uk.silvania.cities.econ.EconUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class ServerPacketHandler implements IPacketHandler {
	
	String atmWithdraw = "";
	String dcPlayerName = "";
	String dcDirection = "";
	String pktID = "";
	boolean withdrawSuccess;
	boolean foundPlugin = false;
	public static String playerName = "";
	public static String newPin = "";
	double atmValue;
	double dcAmount;
	
	
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        if (packet.channel.equals("FCitiesPackets")) {
            handleRandom(packet, player);
        } else if (packet.channel.equals("FCDigiCoinPkt")) {
        	handleDigiCoin(packet, player);
        } else if (packet.channel.equals("FCCardPin")) {
        	handleCardPin(packet, player);
        	System.out.println("Received PIN packet to Server");
        }
	}
	
	private void handleCardPin(Packet250CustomPayload packet, Player player) {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
    	if (CityConfig.debugMode == true) {
    		System.out.println("PIN Change request received");
    	}
		try {
			pktID = dis.readUTF();
			newPin = dis.readUTF();
			playerName = dis.readUTF();
        	if (CityConfig.debugMode == true) {
        		System.out.println("Final Pin: " + newPin + " set for player " + playerName);
        	}
		} catch  (IOException e) {
            System.out.println("Failed to read packet [PIN Change]");
        }
        finally {}
	}
	
	private void handleDigiCoin(Packet250CustomPayload packet, Player player) {
        EntityPlayer entityPlayer = (EntityPlayer) player;
        World world = entityPlayer.worldObj;
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
        if (CityConfig.debugMode == true) {
        	System.out.println("Forge-side DigiCoin Packet get");
        }
        //Used for a stolen card. Defines the player who OWNES the card, not always the one holding it.
        String victimPlayer = DebitCardItem.checkCardOwner(entityPlayer);
    	if (CityConfig.debugMode == true) {
    		System.out.println("Before we even check it, EconUtils claims the balance to be " + EconUtils.getBalance(entityPlayer, world));
    	}
        
        NBTTagCompound nbt = NBTConfig.getTagCompoundInFile(NBTConfig.getWorldConfig(world));
        double currentBalance = 0;
        NBTTagCompound playernbt = nbt.getCompoundTag(victimPlayer);
        if (nbt.hasKey(victimPlayer)) {
        	if (playernbt.hasKey("Balance")) {
                currentBalance = playernbt.getDouble("Balance");
            	if (CityConfig.debugMode == true) {
            		System.out.println("The balance is being read as " + playernbt.getDouble("Balance") + ", and saved as " + currentBalance);
            	}
            }
        }
        if (CityConfig.debugMode == true) {
        	System.out.println("Before we touch the packet checks, "+ victimPlayer + "'s balance is: " + currentBalance);
        }
                
        try {
        	dcDirection = dis.readUTF();
        	dcAmount = dis.readDouble();
			dcPlayerName = dis.readUTF();
			if (dcDirection.equals("digicoinDeposit")) {
				double modifiedBalance = 0;
		        if (CityConfig.debugMode == true) {
		        	System.out.println("Forge-Side DigiCoin Deposit Packet Received: " + dcAmount + " " + dcPlayerName);
		        }
				modifiedBalance = currentBalance - dcAmount;
		        if (CityConfig.debugMode == true) {
		        	System.out.println("Numbers! Original Balance: " + currentBalance + " minus deposit amount (" + dcAmount + ") Gives us " + modifiedBalance);
		        }
				playernbt.setDouble("Balance", modifiedBalance);
	    		nbt.setCompoundTag(victimPlayer, playernbt);
				NBTConfig.saveConfig(nbt, NBTConfig.getWorldConfig(world));
			} else if (dcDirection.equals("digicoinWithdrawConfirm")) {
				double modifiedBalance = 0;
            	if (CityConfig.debugMode == true) {
            		System.out.println("Forge-Side DigiCoin Withdrawl Packet Received: " + dcAmount + " " + dcPlayerName);
            	}
	    		if (dcPlayerName.equals(victimPlayer)) {
	            	if (CityConfig.debugMode == true) {
	            		System.out.println("Adding " + currentBalance + " to " + dcAmount + " to get " + currentBalance + dcAmount);
	            	}
	    			modifiedBalance = currentBalance + dcAmount;
	    			playernbt.setDouble("Balance", modifiedBalance);
	        		nbt.setCompoundTag(victimPlayer, playernbt);
	    			NBTConfig.saveConfig(nbt, NBTConfig.getWorldConfig(world));
	    		}
			} else if (dcDirection.equals("digicoinWithdraw")) {
				double modifiedBalance = 0;
				modifiedBalance = currentBalance;
            	if (CityConfig.debugMode == true) {
            		System.out.println("Sending the withdraw request to DigiCoin, value of " + modifiedBalance);
            	}
			} else if (dcDirection.equals("digiCoinInstallCheck")) {
				//TODO replace this. Change it to send a packet to DigiCoin
            	if (CityConfig.debugMode == true) {
            		System.out.println("Was DigiCoin detected? " + foundPlugin);
            	}
    			pluginCheckRespondPacket(entityPlayer, world, player);
    			
    			Packet p = PacketDispatcher.getPacket("" + foundPlugin, new byte[1]);
                PacketDispatcher.sendPacketToPlayer(p, player);
			} else {
				if (CityConfig.debugMode == true) {
					System.out.println("Not sure what you wanted me to do here...");
					System.out.println("UTF 1: " + dcDirection);
					System.out.println("Double: " + dcAmount);
					System.out.println("UTF 2: " + dcPlayerName);
				}
			}

        } catch  (IOException e) {
            System.out.println("Failed to read packet [DigiCoin -> Forge]");
        }
        finally {}
	}

    private void handleRandom(Packet250CustomPayload packet, Player player) {
        EntityPlayer entityPlayer = (EntityPlayer) player;
        World world = entityPlayer.worldObj;
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
    	if (CityConfig.debugMode == true) {
    		System.out.println("FlenixCities Packet get");
    	}
        //Used for a stolen card. Defines the player who OWNES the card, not always the one holding it.
        String victimPlayer = DebitCardItem.checkCardOwner(entityPlayer);
        try {
        	atmWithdraw = dis.readUTF();
        	atmValue = dis.readDouble();
        	
        	if (CityConfig.debugMode == true) {
        		System.out.println("FC Packet Received: " + atmWithdraw + " " + atmValue);
        	}
			
			if (atmWithdraw.equals("failedPin")) {
				if (entityPlayer.getHeldItem().getItem() == CoreItems.debitCardNew) {
					ItemStack item = entityPlayer.getHeldItem();
					--item.stackSize;
					((EntityPlayerMP) player).sendContainerToPlayer(entityPlayer.inventoryContainer); //TODO TODO TODO SEND ON GUI CLOSE!
		        	if (CityConfig.debugMode == true) {
		        		System.out.println("STEALIN YO' CARD BIATCH!");
		        	}
				}
			} else if (atmWithdraw.equalsIgnoreCase("removeCard")) {
				if (entityPlayer.getHeldItem().getItem() == CoreItems.debitCardNew) {
					ItemStack item = entityPlayer.getHeldItem();
					--item.stackSize;
				}
				((EntityPlayerMP) player).sendContainerToPlayer(entityPlayer.inventoryContainer); //TODO TODO TODO SEND ON GUI CLOSE!
				System.out.println("Updating Inventory!");
			} else if (atmWithdraw.equalsIgnoreCase("updateInventory")) {
				((EntityPlayerMP) player).sendContainerToPlayer(entityPlayer.inventoryContainer);
				if (CityConfig.debugMode) {
					System.out.println("Updating player inventory.");
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
				
				if (atmWithdraw.equals("atmWithdraw")) {
		        	if (CityConfig.debugMode == true) {
		        		System.out.println("You want to withdraw " + atmValue + ". Checking you have enough...");
		        	}
	            	if (currentBalance >= atmValue) {
	                	if (CityConfig.debugMode == true) {
	                		System.out.println("You do!");
	                	}
	            		withdrawSuccess = true;
	            		
	            		//Save ATM Value as a second double which we can safely modify, but remember the original amount.
	            		//This means we can deduct the withdrawn amount from their balance later.
	            		double atmWithdraw = atmValue;
	            		
	            		EconUtils.giveChange(atmWithdraw, 0, (EntityPlayer) player);

	            		double modifiedBalance = currentBalance - atmValue;
	                	if (CityConfig.debugMode == true) {
	                		System.out.println("Setting balance to " + modifiedBalance);
	                	}
	            		playernbt.setDouble("Balance", modifiedBalance);
	            		nbt.setCompoundTag(victimPlayer, playernbt);
						NBTConfig.saveConfig(nbt, NBTConfig.getWorldConfig(world));
						respondPacket(entityPlayer, world, player);
						
						Packet p = PacketDispatcher.getPacket("" + modifiedBalance, new byte[1]);
			            PacketDispatcher.sendPacketToPlayer(p, player);
			            
	            	} else {
	            		withdrawSuccess = false;
	            		respondPacket(entityPlayer, world, player);
	            	}
	            } else if (atmWithdraw.contains("changePin")) {
	            	String finalPin = atmWithdraw.substring(9);
	            	if (CityConfig.debugMode == true) {
	            		System.out.println("Final Pin: " + finalPin);
	            	}
	            }
			} 
        } catch  (IOException e) {
            System.out.println("Failed to read packet [ATM Withdrawl]");
        } finally {}
    }
    
    
    @SuppressWarnings("resource")
	public void respondPacket(EntityPlayer entityPlayer, World world, Player player) {
    	if (CityConfig.debugMode == true) {
    		System.out.println("Gonna try and send a respond packet");
    		System.out.println("Was the withdraw successful? " + withdrawSuccess);
    	}
    	if (withdrawSuccess) {
        	if (CityConfig.debugMode == true) {
        		System.out.println("Withdraw successful!");
        	}
	        ByteArrayOutputStream bt = new ByteArrayOutputStream();
			DataOutputStream out = new DataOutputStream(bt);
	        NBTTagCompound nbt = NBTConfig.getTagCompoundInFile(NBTConfig.getWorldConfig(world));
	        double packetBalance = 0;
	        String victimPlayer = DebitCardItem.checkCardOwner(entityPlayer);
	        NBTTagCompound playernbt = nbt.getCompoundTag(victimPlayer);
	        if (nbt.hasKey(victimPlayer)) {
	        	if (playernbt.hasKey("Balance")) {
	                packetBalance = playernbt.getDouble("Balance");
	            }
	        }
        	if (CityConfig.debugMode == true) {   
        		System.out.println("So far so good, the balance is showing as " + packetBalance);
        	}
	        try {
	        	out.writeUTF("InitBalance");
	        	out.writeDouble(packetBalance);
	        	
	        	Packet250CustomPayload packet = new Packet250CustomPayload("FCitiesPackets", bt.toByteArray());
	        	        	
	        	PacketDispatcher.sendPacketToPlayer(packet, player);
	        	if (CityConfig.debugMode == true) {
	        		System.out.println("Responce Balance Packet sent! Value: " + packetBalance);
	        	}
	        }
	        catch (IOException ex) {
	        	System.out.println("Packet Failed!");
	        }
    	} else {
        	if (CityConfig.debugMode == true) {
        		System.out.println("Withdraw failed!");
        	}
        	ByteArrayOutputStream bt = new ByteArrayOutputStream();
			DataOutputStream out = new DataOutputStream(bt);
	        NBTTagCompound nbt = NBTConfig.getTagCompoundInFile(NBTConfig.getWorldConfig(world));
	        double packetBalance = 0;
	        String victimPlayer = DebitCardItem.checkCardOwner(entityPlayer);
	        NBTTagCompound playernbt = nbt.getCompoundTag(victimPlayer);
	        if (nbt.hasKey(victimPlayer)) {
	        	if (playernbt.hasKey("Balance")) {
	                packetBalance = playernbt.getDouble("Balance");
	            }
	        }
	        double shortAmount = atmValue - packetBalance;
        	if (CityConfig.debugMode == true) {
        		System.out.println("So far so good, the short amount is showing as " + shortAmount);
        	}
	        try {
	        	out.writeUTF("ShortAmount");
	        	out.writeDouble(shortAmount);
	        	
	        	Packet250CustomPayload packet = new Packet250CustomPayload("FCitiesPackets", bt.toByteArray());
	        	        	
	        	PacketDispatcher.sendPacketToPlayer(packet, player);
	        	if (CityConfig.debugMode == true) {
	        		System.out.println("Responce Balance Packet sent! Value: " + shortAmount);
	        	}
	        }
	        catch (IOException ex) {
	        	System.out.println("Packet Failed!");
	        }
    	}
    }
    
    @SuppressWarnings("resource")
	public void pluginCheckRespondPacket(EntityPlayer entityPlayer, World world, Player player) {
    	if (CityConfig.debugMode == true) {
    		System.out.println("Gonna try and send a respond packet");
    	}
        ByteArrayOutputStream bt = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(bt);
        try {
	       	out.writeUTF("pluginCheck");
	       	out.writeBoolean(foundPlugin);
        	Packet250CustomPayload packet = new Packet250CustomPayload("FCDigiCoinPkt", bt.toByteArray());
	        	        	
        	PacketDispatcher.sendPacketToPlayer(packet, player);
        	if (CityConfig.debugMode == true) {
        		System.out.println("Responce Plugin Packet sent! Value: " + foundPlugin);
        	}
        }
        catch (IOException ex) {
        	System.out.println("Packet Failed!");
        }
    }
}