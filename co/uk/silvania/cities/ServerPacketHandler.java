package co.uk.silvania.cities;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.output.ByteArrayOutputStream;

import co.uk.silvania.cities.core.CityConfig;
import co.uk.silvania.cities.core.CoreItems;
import co.uk.silvania.cities.core.NBTConfig;
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
	boolean foundPlugin = true;
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
		System.out.println("PIN Change request received");
		
		try {
			pktID = dis.readUTF();
			newPin = dis.readUTF();
			playerName = dis.readUTF();
			System.out.println("Final Pin: " + newPin + " set for player " + playerName);
		} catch  (IOException e) {
            System.out.println("Failed to read packet");
        }
        finally {}
	}
	
	private void handleDigiCoin(Packet250CustomPayload packet, Player player) {
        EntityPlayer entityPlayer = (EntityPlayer) player;
        World world = entityPlayer.worldObj;
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
        System.out.println("Forge-side DigiCoin Packet get");
        //Used for a stolen card. Defines the player who OWNES the card, not always the one holding it.
        String victimPlayer = DebitCardItem.checkCardOwner(entityPlayer);
        
        NBTTagCompound nbt = NBTConfig.getTagCompoundInFile(NBTConfig.getWorldConfig(world));
        double currentBalance = 0;
        NBTTagCompound playernbt = nbt.getCompoundTag(victimPlayer);
        if (nbt.hasKey(victimPlayer)) {
        	if (playernbt.hasKey("Balance")) {
                currentBalance = playernbt.getDouble("Balance");
                System.out.println("DigiCoin Integration Packet Manager currently has the balance as: " + currentBalance);
            }
        }
                
        try {
        	dcDirection = dis.readUTF();
        	dcAmount = dis.readDouble();
			dcPlayerName = dis.readUTF();
			double modifiedBalance = 0;
			if (dcDirection.equals("digicoinDeposit")) {
				System.out.println("Forge-Side DigiCoin Deposit Packet Received: " + dcAmount + " " + dcPlayerName);
				modifiedBalance = currentBalance - dcAmount;
			} else if (dcDirection.equals("digicoinWithdrawConfirm")) {
	    		System.out.println("Forge-Side DigiCoin Withdrawl Packet Received: " + dcAmount + " " + dcPlayerName);
	    		if (dcPlayerName.equals(victimPlayer)) {
	    			System.out.println("Adding " + currentBalance + " to " + dcAmount + " to get " + currentBalance + dcAmount);
	    			modifiedBalance = currentBalance + dcAmount;
	    		}
			} else if (dcDirection.equals("digicoinWithdraw")) {
				modifiedBalance = currentBalance;
				System.out.println("Sending the withdraw request to DigiCoin, value of " + modifiedBalance);
			} else if (dcDirection.equals("digiCoinInstallCheck")) {
				//TODO replace this. Change it to send a packet to DigiCoin
            	try {
            		Class c = Class.forName("co.uk.silvania.cities.digicoin.DigiCoin");
                	if (CityConfig.debugMode == true) {
                		System.out.println("Performing check");
                	}
            	} catch (ClassNotFoundException e) {
            		foundPlugin = false;
            		if (CityConfig.debugMode == true) {
            			System.out.println("Had a look, couldn't find it. Sorry :(");
            		}
            	}
            	if (CityConfig.debugMode == true) {
            		System.out.println("Was DigiCoin detected? " + foundPlugin);
            	}
    			pluginCheckRespondPacket(entityPlayer, world, player);
    			
    			Packet p = PacketDispatcher.getPacket("" + foundPlugin, new byte[1]);
                PacketDispatcher.sendPacketToPlayer(p, player);
			}
			playernbt.setDouble("Balance", modifiedBalance);
    		nbt.setCompoundTag(victimPlayer, playernbt);
			NBTConfig.saveConfig(nbt, NBTConfig.getWorldConfig(world));

        } catch  (IOException e) {
            System.out.println("Failed to read packet");
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
				if (entityPlayer.getHeldItem().getItem() == CoreItems.debitCard) {
					ItemStack item = entityPlayer.getHeldItem();
					--item.stackSize;
		        	if (CityConfig.debugMode == true) {
		        		System.out.println("STEALIN YO' CARD BIATCH!");
		        	}
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
	            		while (atmWithdraw >= 100) {
	            			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note10000));
	            			atmWithdraw = atmWithdraw - 100;
	                    	if (CityConfig.debugMode == true) {
	                    		System.out.println("Withdrawing 100! Still left to withdraw: " + atmWithdraw);
	                    	}
	            		}
	            		while (atmWithdraw >= 50) {
	            			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note5000));
	            			atmWithdraw = atmWithdraw - 50;
	                    	if (CityConfig.debugMode == true) {
	                    		System.out.println("Withdrawing 50! Still left to withdraw: " + atmWithdraw);
	                    	}
	            		}
	            		while (atmWithdraw >= 20) {
	            			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note2000));
	            			atmWithdraw = atmWithdraw - 20;
	                    	if (CityConfig.debugMode == true) {
	                    		System.out.println("Withdrawing 20! Still left to withdraw: " + atmWithdraw);
	                    	}
	            		}
	            		while (atmWithdraw >= 10) {
	            			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note1000));
	            			atmWithdraw = atmWithdraw - 10;
	                    	if (CityConfig.debugMode == true) {
	                    		System.out.println("Withdrawing 10! Still left to withdraw: " + atmWithdraw);
	                    	}
	            		}
	            		while (atmWithdraw >= 5) {
	            			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note500));
	            			atmWithdraw = atmWithdraw - 5;
	                    	if (CityConfig.debugMode == true) {
	                    		System.out.println("Withdrawing 5! Still left to withdraw: " + atmWithdraw);
	                    	}
	                    }
	            		while (atmWithdraw >= 2) {
	            			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note200));
	            			atmWithdraw = atmWithdraw - 2;
	                    	if (CityConfig.debugMode == true) {
	                    		System.out.println("Withdrawing 2! Still left to withdraw: " + atmWithdraw);
	                    	}
	            		}
	            		while (atmWithdraw >= 1) {
	            			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note100));
	            			atmWithdraw = atmWithdraw - 1;
	                    	if (CityConfig.debugMode == true) {
	                    		System.out.println("Withdrawing 1! Still left to withdraw: " + atmWithdraw);
	                    	}
	            		}
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
            System.out.println("Failed to read packet");
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