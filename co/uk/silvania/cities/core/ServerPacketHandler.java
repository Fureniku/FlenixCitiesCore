package co.uk.silvania.cities.core;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.output.ByteArrayOutputStream;

import co.uk.silvania.cities.econ.DebitCardItem;
import co.uk.silvania.cities.econ.EconUtils;
import co.uk.silvania.cities.econ.store.entity.TileEntityAdminShop;
import co.uk.silvania.cities.econ.store.entity.TileEntityFloatingShelves;
import net.minecraft.block.BlockContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class ServerPacketHandler implements IPacketHandler {
	
	String strValue = "";
	String dcPlayerName = "";
	String dcDirection = "";
	String pktID = "";
	
	String str1;
	String str2;
	String str3;
	String str4;
	String str5;
	String str6;
	String str7;
	String str8;
	
	boolean withdrawSuccess;
	boolean foundPlugin = false;
	public static String playerName = "";
	public static String newPin = "";
	double doubleValue;
	double dcAmount;
	
	
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		System.out.println("Packet get! Channel: " + packet.channel);
        if (packet.channel.equals("FCitiesPackets")) {
            handleRandom(packet, player);
        } else if (packet.channel.equals("FCDigiCoinPkt")) {
        	handleDigiCoin(packet, player);
        } else if (packet.channel.equals("FCCardPin")) {
        	handleCardPin(packet, player);
        } else if (packet.channel.equals("FCShopPacket")) {
        	handleShopPacket(packet, player);
        } else if (packet.channel.equals("FCSalePacket")) {
        	handleSalePacket(packet, player);
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
	
	private void handleShopPacket(Packet250CustomPayload packet, Player player) {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
        EntityPlayer entityPlayer = (EntityPlayer) player;
        World world = entityPlayer.worldObj;
        
		try {
			str1 = dis.readUTF();
			str2 = dis.readUTF();
			str3 = dis.readUTF();
			str4 = dis.readUTF();
			str5 = dis.readUTF();
			str6 = dis.readUTF();
			str7 = dis.readUTF();
			str8 = dis.readUTF();
			int x = dis.readInt();
			int y = dis.readInt();
			int z = dis.readInt();
			TileEntity tileEntity = (TileEntity) world.getBlockTileEntity(x, y, z);
			if (tileEntity instanceof TileEntityFloatingShelves) {
				System.out.println("Floating Shelves Prices Recieved");
				TileEntityFloatingShelves tileShelves = (TileEntityFloatingShelves) world.getBlockTileEntity(x, y, z);
				tileShelves.buyPrice1 = EconUtils.parseDouble(str1);
				tileShelves.sellPrice1 = EconUtils.parseDouble(str2);
				tileShelves.buyPrice2 = EconUtils.parseDouble(str3);
				tileShelves.sellPrice2 = EconUtils.parseDouble(str4);
				tileShelves.buyPrice3 = EconUtils.parseDouble(str5);
				tileShelves.sellPrice3 = EconUtils.parseDouble(str6);
				tileShelves.buyPrice4 = EconUtils.parseDouble(str7);
				tileShelves.sellPrice4 = EconUtils.parseDouble(str8);
			} else {
				System.out.println("Admin Prices Recieved");
				TileEntityAdminShop tileAdmin = (TileEntityAdminShop) world.getBlockTileEntity(x, y, z);
				tileAdmin.buyPrice1 = EconUtils.parseDouble(str1);
				tileAdmin.sellPrice1 = EconUtils.parseDouble(str2);
				tileAdmin.buyPrice2 = EconUtils.parseDouble(str3);
				tileAdmin.sellPrice2 = EconUtils.parseDouble(str4);
				tileAdmin.buyPrice3 = EconUtils.parseDouble(str5);
				tileAdmin.sellPrice3 = EconUtils.parseDouble(str6);
				tileAdmin.buyPrice4 = EconUtils.parseDouble(str7);
				tileAdmin.sellPrice4 = EconUtils.parseDouble(str8);
			}

		} catch  (IOException e) {
            System.out.println("Failed to read packet [Shops]");
        } finally {}
	}
	
	private void handleSalePacket(Packet250CustomPayload packet, Player player) {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
        EntityPlayer entityPlayer = (EntityPlayer) player;
        World world = entityPlayer.worldObj;
        System.out.println("Sale Packet Get!");
        
		try {
			String pktId = dis.readUTF();
			int id = dis.readInt();
			int qty = dis.readInt();
			int x = dis.readInt();
			int y = dis.readInt();
			int z = dis.readInt();
			TileEntity tileEntity = (TileEntity) world.getBlockTileEntity(x, y, z);
			System.out.println("Pkt ID: " + pktId);
			if (tileEntity instanceof TileEntityFloatingShelves) {
				TileEntityFloatingShelves tileShelves = (TileEntityFloatingShelves) world.getBlockTileEntity(x, y, z);
				if (pktId.equalsIgnoreCase("buttonSwitch")) {
					tileShelves.tabButton = id;
				} else if (pktId.equalsIgnoreCase("salePacket")) {
					System.out.println("Sell Item process begun!");
					tileShelves.sellItem(id, qty, entityPlayer);
				} else if (pktId.equalsIgnoreCase("buyPacket")) {
					System.out.println("Buy Item process begun!");
					tileShelves.buyItem(id, qty, entityPlayer);
				}
			} else {
				TileEntityAdminShop tileAdmin = (TileEntityAdminShop) world.getBlockTileEntity(x, y, z);
				if (pktId.equalsIgnoreCase("buttonSwitch")) {
					tileAdmin.tabButton = id;
				} else if (pktId.equalsIgnoreCase("salePacket")) {
					System.out.println("Sell Item process begun!");
					tileAdmin.sellItem(id, qty, entityPlayer);
				} else if (pktId.equalsIgnoreCase("buyPacket")) {
					System.out.println("Buy Item process begun!");
					tileAdmin.buyItem(id, qty, entityPlayer);
				}
			}
		} catch  (IOException e) {
            System.out.println("Failed to read packet [Shops]");
        } finally {}
	}
	
	private void handleAdminShopPacket(Packet250CustomPayload packet, Player player) {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
        EntityPlayer entityPlayer = (EntityPlayer) player;
        World world = entityPlayer.worldObj;
        
		try {
			str1 = dis.readUTF();
			str2 = dis.readUTF();
			str3 = dis.readUTF();
			str4 = dis.readUTF();
			str5 = dis.readUTF();
			str6 = dis.readUTF();
			str7 = dis.readUTF();
			str8 = dis.readUTF();
			int x = dis.readInt();
			int y = dis.readInt();
			int z = dis.readInt();
			
			TileEntityAdminShop tileAdmin = (TileEntityAdminShop) world.getBlockTileEntity(x, y, z);
			tileAdmin.buyPrice1 = EconUtils.parseDouble(str1);
			tileAdmin.sellPrice1 = EconUtils.parseDouble(str2);
			tileAdmin.buyPrice2 = EconUtils.parseDouble(str3);
			tileAdmin.sellPrice2 = EconUtils.parseDouble(str4);
			tileAdmin.buyPrice3 = EconUtils.parseDouble(str5);
			tileAdmin.sellPrice3 = EconUtils.parseDouble(str6);
			tileAdmin.buyPrice4 = EconUtils.parseDouble(str7);
			tileAdmin.sellPrice4 = EconUtils.parseDouble(str8);
			tileAdmin.sellItem(1, 1, (EntityPlayer) player);

		} catch  (IOException e) {
            System.out.println("Failed to read packet [Shops]");
        } finally {}
	}
	
	private void handleAdminSalePacket(Packet250CustomPayload packet, Player player) {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
        EntityPlayer entityPlayer = (EntityPlayer) player;
        World world = entityPlayer.worldObj;
        
		try {
			String pktId = dis.readUTF();
			int id = dis.readInt();
			int qty = dis.readInt();
			int x = dis.readInt();
			int y = dis.readInt();
			int z = dis.readInt();
			
			TileEntityFloatingShelves tileEntity = (TileEntityFloatingShelves) world.getBlockTileEntity(x, y, z);
			if (pktId.equalsIgnoreCase("buttonSwitch")) {
				tileEntity.tabButton = id;
			} else if (pktId.equalsIgnoreCase("salePacket")) {
				tileEntity.sellItem(id, qty, entityPlayer);
			} else if (pktId.equalsIgnoreCase("buyPacket")) {
				
			}
		} catch  (IOException e) {
            System.out.println("Failed to read packet [Shops]");
        } finally {}
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
        	strValue = dis.readUTF();
        	doubleValue = dis.readDouble();
        	
        	if (CityConfig.debugMode == true) {
        		System.out.println("FC Packet Received: " + strValue + " " + doubleValue);
        	}
			
			if (strValue.equals("failedPin")) {
				if (entityPlayer.getHeldItem().getItem() == CoreItems.debitCardNew) {
					ItemStack item = entityPlayer.getHeldItem();
					--item.stackSize;
					((EntityPlayerMP) player).sendContainerToPlayer(entityPlayer.inventoryContainer); //TODO TODO TODO SEND ON GUI CLOSE!
		        	if (CityConfig.debugMode == true) {
		        		System.out.println("STEALIN YO' CARD BIATCH!");
		        	}
				}
			} else if (strValue.equalsIgnoreCase("removeCard")) {
				if (entityPlayer.getHeldItem().getItem() == CoreItems.debitCardNew) {
					ItemStack item = entityPlayer.getHeldItem();
					--item.stackSize;
				}
				((EntityPlayerMP) player).sendContainerToPlayer(entityPlayer.inventoryContainer); //TODO TODO TODO SEND ON GUI CLOSE!
			} else if (strValue.equalsIgnoreCase("updateInventory")) {
				((EntityPlayerMP) player).sendContainerToPlayer(entityPlayer.inventoryContainer);
				if (CityConfig.debugMode) {
					System.out.println("Updating player inventory.");
				}
			} else if (strValue.equalsIgnoreCase("shopUpdatePacket")) {

				
			} else {
			
				NBTTagCompound nbt = NBTConfig.getTagCompoundInFile(NBTConfig.getWorldConfig(world));
				double currentBalance = 0;
				NBTTagCompound playernbt = nbt.getCompoundTag(victimPlayer);
				if (nbt.hasKey(victimPlayer)) {
					if (playernbt.hasKey("Balance")) {
						currentBalance = playernbt.getDouble("Balance");
					}
				}
				
				if (strValue.equals("atmWithdraw")) {
		        	if (CityConfig.debugMode == true) {
		        		System.out.println("You want to withdraw " + doubleValue + ". Checking you have enough...");
		        	}
	            	if (currentBalance >= doubleValue) {
	                	if (CityConfig.debugMode == true) {
	                		System.out.println("You do!");
	                	}
	            		withdrawSuccess = true;
	            		
	            		//Save ATM Value as a second double which we can safely modify, but remember the original amount.
	            		//This means we can deduct the withdrawn amount from their balance later.
	            		double strValue = doubleValue;
	            		
	            		EconUtils.giveChange(strValue, 0, (EntityPlayer) player);

	            		double modifiedBalance = currentBalance - doubleValue;
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
	            } else if (strValue.contains("changePin")) {
	            	String finalPin = strValue.substring(9);
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
	        double shortAmount = doubleValue - packetBalance;
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