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
	public static double invBalance;
	public static int newPin;
	public static boolean foundPlugin;
	
	public static String buyPrice1;
	public static String sellPrice1;
	public static String buyPrice2;
	public static String sellPrice2;
	public static String buyPrice3;
	public static String sellPrice3;
	public static String buyPrice4;
	public static String sellPrice4;
	
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
        	} else if (pktID.equalsIgnoreCase("ShortAmount")) {
        		shortValue = balance;
        	} else if (pktID.equalsIgnoreCase("InventoryBalance")) {
        		invBalance = balance;
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
    
    private void handleShopPacket(Packet250CustomPayload packet, Player player) {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
        System.out.println("Packet get [CLIENT-SHOP]");
        try {
        	ownerName = dis.readUTF();
        	buyPrice1 = "" + dis.readDouble();
        	sellPrice1 = "" +  dis.readDouble();
        	buyPrice2 = "" + dis.readDouble();
        	sellPrice2 = "" + dis.readDouble();
        	buyPrice3 = "" + dis.readDouble();
        	sellPrice3 = "" + dis.readDouble();
        	buyPrice4 = "" + dis.readDouble();
        	sellPrice4 = "" + dis.readDouble();
        	
        } catch (IOException e) {
        	System.out.println("Failed to read packet [Shop]");
        }
        finally {}
    }
}