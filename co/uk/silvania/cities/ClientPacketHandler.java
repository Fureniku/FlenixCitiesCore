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

public class ClientPacketHandler implements IPacketHandler {
	
	public static String balance = "";
	
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        //again we need to check for the proper channel
        if (packet.channel.equals("FCitiesPackets")) {
            handleRandom(packet, player);
        }
    }

    private void handleRandom(Packet250CustomPayload packet, Player player) {
        EntityPlayer p = (EntityPlayer) player;
        World world = p.worldObj;
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
        System.out.println("Packet get");
        try {
            //here's that first integer again.
        	int balID = dis.readInt();
        	
        	switch (balID) {
        		case 1:
        			String balance = dis.readUTF();
        			System.out.println(balance);
        	}
            System.out.println("Packet Received: " + balID);
        }
        catch  (IOException e) {
            System.out.println("Failed to read packet");
        }
        finally {}
    }
    
    public static String atmBalance() {
        double currentBalance = 0;
        NBTTagCompound playernbt = new NBTTagCompound();
        if (playernbt.hasKey("Balance")) {
            currentBalance = playernbt.getDouble("Balance");
        }
        String atmBalance = "" + currentBalance;
		return atmBalance;
    	
    }
}