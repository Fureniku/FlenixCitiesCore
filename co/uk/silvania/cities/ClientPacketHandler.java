package co.uk.silvania.cities;

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
        	if (pktID.equals("InitBalance")) {
        		initBal = balance;
        		System.out.println("initbal's value is " + initBal);
        	} else if (pktID.equals("ShortAmount")) {
        		shortValue = balance;
        	}
        } catch (IOException e) {
        	System.out.println("Failed to read packet");
        } try {
        	int pin = dis.readInt();
        	newPin = pin;
        } catch  (IOException e) {
            System.out.println("Failed to read packet");
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
        	System.out.println("Failed to read packet");
        }
        finally {}
    }
}