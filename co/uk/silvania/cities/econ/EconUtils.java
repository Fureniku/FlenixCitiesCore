package co.uk.silvania.cities.econ;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import co.uk.silvania.cities.core.CityConfig;
import co.uk.silvania.cities.core.NBTConfig;
import co.uk.silvania.cities.econ.money.ItemNote;
import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class EconUtils {
		
	//Use to convert things like a string to a double, usable by the economy.
	//VERY useful for example in the ATM, keying in values from the buttons.
	public static double parseDouble(String s) {
		try { 
			return Double.parseDouble(s);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}
	
	//This one is used to turn the PIN from a string to an int when changing it in the ATM.
	public static int parseInt(String s) {
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}

	//Quick n' easy method of getting the players balance.
	public static double getBalance(EntityPlayer player, World world) {
        ByteArrayOutputStream bt = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bt);
		
		String victimPlayer = DebitCardItem.checkCardOwner(player);
        NBTTagCompound nbt = NBTConfig.getTagCompoundInFile(NBTConfig.getWorldConfig(world));
        double balance = 0;
        if (nbt.hasKey(victimPlayer)) {
            NBTTagCompound playernbt = nbt.getCompoundTag(victimPlayer);
            if (playernbt.hasKey("Balance")) {
                balance = playernbt.getDouble("Balance");
            }
        }
        
        try {
        	out.writeUTF("PlayerBalance");
        	out.writeDouble(balance);
        	Packet250CustomPayload packet = new Packet250CustomPayload("FCitiesPackets", bt.toByteArray());
        	
        	PacketDispatcher.sendPacketToServer(packet);
    		if (CityConfig.debugMode == true) {
    			System.out.println("Packet sent! Server-Side Balance: " + balance);
    		}
        }
        catch (IOException ex) {
        	System.out.println("Packet Failed!");
        }
        
        return balance;
	}
	
	
	/*public void countInventory(EntityPlayer player) {
		if (player.inventory.mainInventory instanceof ItemNote) {
			
		}
	}*/
	
	public void giveXAmount() {
		
	}
}
