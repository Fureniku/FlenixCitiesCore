package co.uk.silvania.cities.core;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import co.uk.silvania.cities.econ.atm.GuiATM;;

public class ClientPacketHandler implements IPacketHandler {
	
	String pktID = "";
	double balance;
	public static double initBal;
	public static double shortValue;
	public static int newPin;
	public static boolean foundPlugin;
	
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        if (packet.channel.equals("FCitiesPackets")) {
            handleRandom(packet, player);
        } else if (packet.channel.equals("FCDigiCoinPkt")) {
        	handleDigiCoin(packet, player);
        } else if (packet.channel.equals("FCShopPacket")) {
        	handleShopPacket(packet, player);
        }
    }

    private void handleRandom(Packet250CustomPayload packet, Player player) {
        EntityPlayer p = (EntityPlayer) player;
        World world = p.worldObj;
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
        System.out.println("Packet get [CLIENT]");
        try {
        	String pktID = dis.readUTF();
        	double balance = dis.readDouble();
        	
        	System.out.println("FC Client Packet Received: " + pktID + " " + balance);
        	if (pktID.equalsIgnoreCase("InitBalance")) {
        		initBal = balance;
        		System.out.println("initbal's value is " + initBal);
        	} else if (pktID.equalsIgnoreCase("ShortAmount")) {
        		shortValue = balance;
        	}
        } catch (IOException e) {
        	System.out.println("Failed to read packet [Balance]");
        }
        finally {}
    }
    
    private void handleDigiCoin(Packet250CustomPayload packet, Player player) {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
        System.out.println("Packet get [CLIENT-DIGICOIN]");
        try {
        	String pktID = dis.readUTF();
        	foundPlugin = dis.readBoolean();
        	
        	System.out.println("FC Client Packet Received: " + pktID + " " + foundPlugin);
        } catch (IOException e) {
        	System.out.println("Failed to read packet [DigiCoin Check]");
        }
        finally {}
    }
    
	public static String ownerName;
	
	public static String item1;
	public static int item1Qty;
	public static double item1BuyValue ;
	public static double item1SellValue;
	
	public static String item2;;
	public static int item2Qty;
	public static double item2BuyValue;
	public static double item2SellValue;
	
	public static String item3;
	public static int item3Qty;
	public static double item3BuyValue;
	public static double item3SellValue;
	
	public static String item4;
	public static int item4Qty;
	public static double item4BuyValue;
	public static double item4SellValue;
    
    private void handleShopPacket(Packet250CustomPayload packet, Player player) {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
        System.out.println("Packet get [CLIENT-SHOP]");
        try {
        	String pktID = dis.readUTF();
        	ownerName = dis.readUTF();
        	
        	item1 = dis.readUTF();
        	item1Qty = dis.readInt();
        	item1BuyValue = dis.readDouble();
        	item1SellValue = dis.readDouble();
        	
        	item2 = dis.readUTF();
        	item2Qty = dis.readInt();
        	item2BuyValue = dis.readDouble();
        	item2SellValue = dis.readDouble();
        	
        	item3 = dis.readUTF();
        	item3Qty = dis.readInt();
        	item3BuyValue = dis.readDouble();
        	item3SellValue = dis.readDouble();
        	
        	item4 = dis.readUTF();
        	item4Qty = dis.readInt();
        	item4BuyValue = dis.readDouble();
        	item4SellValue = dis.readDouble();
        	
        	System.out.println("FC Client Packet Received: " + pktID + " " + ownerName);
        	if (pktID.equalsIgnoreCase("InitBalance")) {
        		initBal = balance;
        		System.out.println("initbal's value is " + initBal);
        	} else if (pktID.equalsIgnoreCase("ShortAmount")) {
        		shortValue = balance;
        	}
        } catch (IOException e) {
        	System.out.println("Failed to read packet [Balance]");
        }
        finally {}
    }
}