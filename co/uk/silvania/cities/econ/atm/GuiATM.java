package co.uk.silvania.cities.econ.atm;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.cities.ClientPacketHandler;
import co.uk.silvania.cities.core.CityConfig;
import co.uk.silvania.cities.core.CoreItems;
import co.uk.silvania.cities.econ.DebitCardItem;
import co.uk.silvania.cities.econ.EconUtils;
import cpw.mods.fml.common.network.PacketDispatcher;

public class GuiATM extends GuiContainer {

    private static final ResourceLocation texture = new ResourceLocation("flenixcities", "textures/gui/atm.png");

    
    public GuiATM (InventoryPlayer inventoryPlayer, TileEntityATMEntity tileEntity, World world, int x, int y, int z) {
    	super(new ContainerATM(inventoryPlayer, tileEntity));
    }
    
    protected int xSize = 232;
    protected int ySize = 242;
    
    String enteredPin = "";
    String newPin = "";
    String confirmNewPin = "";
    String pinAttempt = "1";
    String guiStage = "1";
    String balance = "0";
    String withdrawCustom = "";
    String depositDCCustom = "";
    String withdrawDCCustom = "";
    double withdrawAmount;
    String digicoinDepositAmount = "";
    double initBalance;
    
    //GUI Stage changes the current active screen. It enables different features on the buttons, and changes the text.
    //Stage 1 asks for pin and waits for it to be entered.
    //Stage 2 is an options menu, asking for your next move.
    //Stage 3 is the Withdraw screen, the buttons will give money and remove from your NBT
    //Stage 4 is the Balance screen, and shows your balance.
    //Stage 5 is the withdraw confirm screen.
    //Stage 6 is the insufficient funds error message.
    //From here still needs to be coded.
    //Stage 7 is the DigiCoin Transfer screen.
    //Stage 8 is the Change PIN screen.
    //Stage 9 is EMPTY (ex-deposit screen)
    //Stage 10 is the Withdraw X Amount screen.
    //Stage 11 is the DigiCoin Withdraw screen
    //Stage 12 is the DigiCoin Deposit screen
    //Stage 13 is the DigiCoin confirm screen
    //Stage 14 is the PIN confirm screen.
    //Stage 15 is the PIN change complete screen.
    //Stage 16 is the PIN change failed screen.
    //Each stage has a TO-DO list so it can be easily found in eclipse. This is a damn long class, so added this for sanity.
    //TODO Text String position/colours for 7-16, all active code for 7-16, NBT Balance Display in-ATM.
    
    @Override
    public void initGui() {
    	super.initGui();
    	initBalance = initBalance + ClientPacketHandler.initBal;
    	buttonList.add(new ATMButton(1, guiLeft + 21, guiTop + 109, 24, 15, "7")); // 7
    	buttonList.add(new ATMButton(2, guiLeft + 53, guiTop + 109, 24, 15, "8")); // 8
    	buttonList.add(new ATMButton(3, guiLeft + 85, guiTop + 109, 24, 15, "9")); // 9
    	buttonList.add(new RedButton(4, guiLeft + 121, guiTop + 109, 24, 15, "")); // Cancel
    	buttonList.add(new ATMButton(5, guiLeft + 21, guiTop + 133, 24, 15, "4")); // 4
    	buttonList.add(new ATMButton(6, guiLeft + 53, guiTop + 133, 24, 15, "5")); // 5
    	buttonList.add(new ATMButton(7, guiLeft + 85, guiTop + 133, 24, 15, "6")); // 6
    	buttonList.add(new YellowButton(8, guiLeft + 121, guiTop + 133, 24, 15, "")); // Clear
    	buttonList.add(new ATMButton(9, guiLeft + 21, guiTop + 157, 24, 15, "1")); // 1
    	buttonList.add(new ATMButton(10, guiLeft + 53, guiTop + 157, 24, 15, "2")); // 2
    	buttonList.add(new ATMButton(11, guiLeft + 85, guiTop + 157, 24, 15, "3")); // 3
    	buttonList.add(new GreenButton(12, guiLeft + 121, guiTop + 157, 24, 15, "")); // Confirm
    	buttonList.add(new ATMButton(13, guiLeft + 53, guiTop + 181, 24, 15, "0")); // 0
    	
    	buttonList.add(new ATMButtonLeft(14, guiLeft - 21, guiTop - 7, 24, 15, "")); //Top-Left
    	buttonList.add(new ATMButtonLeft(16, guiLeft - 21, guiTop + 20, 24, 15, "")); //Mid-Upper Left
    	buttonList.add(new ATMButtonLeft(18, guiLeft - 21, guiTop + 47, 24, 15, "")); //Mid-Lower Left
    	buttonList.add(new ATMButtonLeft(20, guiLeft - 21, guiTop + 74, 24, 15, "")); //Bottom Left
    	
    	buttonList.add(new ATMButtonRight(15, guiLeft + 173, guiTop - 7, 24, 15, "")); //Top-Right
    	buttonList.add(new ATMButtonRight(17, guiLeft + 173, guiTop + 20, 24, 15, "")); //Mid-Upper Right
    	buttonList.add(new ATMButtonRight(19, guiLeft + 173, guiTop + 47, 24, 15, "")); //Mid-Lower Right
    	buttonList.add(new ATMButtonRight(21, guiLeft + 173, guiTop + 74, 24, 15, "")); //Bottom Right
    }
    
    public void actionPerformed(GuiButton guibutton) {
    	/*
    	 *  TODO Asks for PIN
    	 */
    	if (guiStage.equals("1")) {
    		switch(guibutton.id) {
	    	case 1:
	    		enteredPin = enteredPin + "7";
	    		this.isPinLongEnough(enteredPin);
	    		break;
			case 2:
				enteredPin = enteredPin + "8";
	    		this.isPinLongEnough(enteredPin);
				break;
	    	case 3:
	    		enteredPin = enteredPin + "9";
	    		this.isPinLongEnough(enteredPin);
	    		break;
	    	case 4:
	    		break;
	    	case 5:
	    		enteredPin = enteredPin + "4";
	    		this.isPinLongEnough(enteredPin);
	    		break;
	    	case 6:
	    		enteredPin = enteredPin + "5";
	    		this.isPinLongEnough(enteredPin);
	    		break;
	    	case 7:
	    		enteredPin = enteredPin + "6";
	    		this.isPinLongEnough(enteredPin);
	    		break;
	    	case 8:
	    		enteredPin = "";
	    		break;
	    	case 9:
	    		enteredPin = enteredPin + "1";
	    		this.isPinLongEnough(enteredPin);
	    		break;
	    	case 10:
	    		enteredPin = enteredPin + "2";
	    		this.isPinLongEnough(enteredPin);
	    		break;
	    	case 11:
	    		enteredPin = enteredPin + "3";
	    		this.isPinLongEnough(enteredPin);
	    		break;
	    	case 12:
	    		enteredPin = enteredPin + "c";
	    		this.isPinLongEnough(enteredPin);
	    		break;
	    	case 13:
	    		enteredPin = enteredPin + "0";
	    		this.isPinLongEnough(enteredPin);
	    		break;
	    	}
    	}
    	
    	/*
    	 *  TODO Options menu- what do next?
    	 */
    	if (guiStage.equals("2")) {
            ByteArrayOutputStream bt = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(bt);
            
    		try {
            	if (CityConfig.debugMode == true) {
            		System.out.println("Going to check if DigiCoin is installed");
            	}
    			out.writeUTF("digiCoinInstallCheck");
    			out.writeDouble(0);
    			out.writeUTF("");
    			Packet250CustomPayload packet = new Packet250CustomPayload("FCDigiCoinPkt", bt.toByteArray());
	            	
    			PacketDispatcher.sendPacketToServer(packet);
    			if (CityConfig.debugMode == true) {
    				System.out.println("DigiCoin check packet sent!");
    			}
    		}
    		catch (IOException ex) {
    			System.out.println("Packet Failed!");
    		}
    		switch(guibutton.id) {
	    	case 4:
	    		guiStage = "1";
	    		break;
	    	case 14:
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("Open Withdraw GUI!");
	    		}
	    		guiStage = "3";
	    		break;
	    	case 15:
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("Open Balance GUI");
	    		}
	    		guiStage = "4";
	    		break;
	    	case 16:
            	if (ClientPacketHandler.foundPlugin == true) {
            		if (CityConfig.debugMode == true) {
            			System.out.println("Open DigiCoin GUI");
            		}
            		guiStage = "7";
            	} else if (ClientPacketHandler.foundPlugin == false) {
            		guiStage = "9";
            	}
	    		break;
	    	case 17:
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("Open Change Pin GUI");
	    		}
	    		guiStage = "8";
	    		break;
	    	case 21:
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("Eject Card");
	    		}
	    		guiStage = "1";
	    	}
    	}
    	
    	/*
    	 *  TODO Withdraw Screen. Sends a packet with the withdraw amount, which checks you have enough. If so, it'll give you the cash and remove from your NBT.
    	 */
    	if (guiStage.equals("3")) {
            ByteArrayOutputStream bt = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(bt);
            double currentBal = ClientPacketHandler.initBal;
            
    		switch(guibutton.id) {
	    	case 4:
	    		guiStage = "1";
	    		break;
    		case 16:
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("Withdraw 10");
	    		}
	    		
	    		withdrawAmount = 10.0;
	    		if (currentBal >= withdrawAmount) {
	    			guiStage = "5";
	    		} else {
	    			guiStage = "6";
	    		}
	    		
	            try {
	            	out.writeUTF("atmWithdraw");
	            	out.writeDouble(withdrawAmount);
	            	Packet250CustomPayload packet = new Packet250CustomPayload("FCitiesPackets", bt.toByteArray());
	            	
	            	PacketDispatcher.sendPacketToServer(packet);
		    		if (CityConfig.debugMode == true) {
		    			System.out.println("Packet sent! withdraw " + withdrawAmount);
		    		}
	            }
	            catch (IOException ex) {
	            	System.out.println("Packet Failed!");
	            }
	    		break;
	    	case 17:
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("Withdraw 20");
	    		}
	    		
	    		withdrawAmount = 20.0;
	    		if (currentBal >= withdrawAmount) {
	    			guiStage = "5";
	    		} else {
	    			guiStage = "6";
	    		}
	    		
	            try {
	            	out.writeUTF("atmWithdraw");
	            	out.writeDouble(withdrawAmount);
	            	Packet250CustomPayload packet = new Packet250CustomPayload("FCitiesPackets", bt.toByteArray());
	            	
	            	PacketDispatcher.sendPacketToServer(packet);
		    		if (CityConfig.debugMode == true) {
		    			System.out.println("Packet sent! withdraw" + withdrawAmount);
		    		}
	            }
	            catch (IOException ex) {
	            	System.out.println("Packet Failed!");
	            }
	    		break;
	    	case 18:
            	if (CityConfig.debugMode == true) {
            		System.out.println("Withdraw 50");
            	}
            		
	    		withdrawAmount = 50.0;
	    		if (currentBal >= withdrawAmount) {
	    			guiStage = "5";
	    		} else {
	    			guiStage = "6";
	    		}
	    		
	            try {
	            	out.writeUTF("atmWithdraw");
	            	out.writeDouble(withdrawAmount);
	            	Packet250CustomPayload packet = new Packet250CustomPayload("FCitiesPackets", bt.toByteArray());
	            	
	            	PacketDispatcher.sendPacketToServer(packet);
		    		if (CityConfig.debugMode == true) {
		    			System.out.println("Packet sent! withdraw" + withdrawAmount);
		    		}
	            }
	            catch (IOException ex) {
	            	System.out.println("Packet Failed!");
	            }
	    		break;
	    	case 19:
            	if (CityConfig.debugMode == true) {
            		System.out.println("Withdraw 100");
    			}
	    		withdrawAmount = 100.0;
	    		if (currentBal >= withdrawAmount) {
	    			guiStage = "5";
	    		} else {
	    			guiStage = "6";
	    		}
	    		
	            try {
	            	out.writeUTF("atmWithdraw");
	            	out.writeDouble(withdrawAmount);
	            	Packet250CustomPayload packet = new Packet250CustomPayload("FCitiesPackets", bt.toByteArray());
	            	
	            	PacketDispatcher.sendPacketToServer(packet);
		    		if (CityConfig.debugMode == true) {
		    			System.out.println("Packet sent! withdraw " + withdrawAmount);
		    		}
	            }
	            catch (IOException ex) {
	            	System.out.println("Packet Failed!");
	            }
	    		break;
	    	case 20:
            	if (CityConfig.debugMode == true) {
            		System.out.println("Withdraw 250");
            	}

	    		withdrawAmount = 250.0;
	    		if (currentBal >= withdrawAmount) {
	    			guiStage = "5";
	    		} else {
	    			guiStage = "6";
	    		}
	    		
	            try {
	            	out.writeUTF("atmWithdraw");
	            	out.writeDouble(withdrawAmount);
	            	Packet250CustomPayload packet = new Packet250CustomPayload("FCitiesPackets", bt.toByteArray());
	            	
	            	PacketDispatcher.sendPacketToServer(packet);
		    		if (CityConfig.debugMode == true) {
		    			System.out.println("Packet sent! withdraw " + withdrawAmount);
		    		}
	            }
	            catch (IOException ex) {
	            	System.out.println("Packet Failed!");
	            }
	    		break;
	    	case 21:
	    		System.out.println("Define Amount");
	    		guiStage = "10";
	    		break;
	    	}
    	}
    	
    	/*
    	 *  TODO balance screen.
    	 */
    	if (guiStage.equals("4")) {
    		switch(guibutton.id){
	    	case 4:
	    		guiStage = "1";
	    		break;
    		case 20:
    			guiStage = "2";
    			break;
    		}
    	}
    	
    	/*
    	 *  TODO Confirm withdrawl.
    	 */
    	if (guiStage.equals("5")) {
    		switch(guibutton.id) {
    		case 4:
    			guiStage = "1";
    			break;
    		case 12:
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("Returned to menu.");
	    		}
    			guiStage = "2";
    			break;
        	}
    	} 
    	
    	/*
    	 *  TODO Not enough moolah.
    	 */
    	if (guiStage.equals("6")) {
    		switch(guibutton.id) {
    		case 4:
    			guiStage = "1";
    			break;
    		case 17:
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("Withdraw Different Amount");
	    		}
    			guiStage = "3";
    			break;
    		case 19:
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("Returned to menu.");
	    		}
    			guiStage = "2";
    			break;
    		case 21:
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("Ejected card.");
	    		}
    			guiStage = "1";
    			break;
        	}
    	} 
    	
    	/*
    	 *  TODO DigiCoin transfer screen
    	 */
    	if (guiStage.equals("7")) {
    		switch(guibutton.id) {
    		case 4:
    			guiStage = "1";
    			break;
    		case 18:
    			if (CityConfig.debugMode == true) {
    				System.out.println("DigiCoin Deposit Screen");
    			}
    			guiStage = "12";
    			break;
    		case 19:
    			if (CityConfig.debugMode == true) {
    				System.out.println("DigiCoin Withdraw Screen");
    			}
    			guiStage = "7";
    			break;
    		case 20:
    			guiStage = "2";
    			break;
    		case 21:
    			guiStage = "7";
    			break;
        	}
    	} 
    	
    	/*
    	 *  TODO Change PIN Code
    	 */
    	if (guiStage.equals("8")) {
    		switch(guibutton.id) {
	    	case 1:
	    		newPin = newPin + "7";
	    		break;
			case 2:
				newPin = newPin + "8";
				break;
	    	case 3:
	    		newPin = newPin + "9";
	    		break;
	    	case 4:
	    		guiStage = "1";
	    		break;
	    	case 5:
	    		newPin = newPin + "4";
	    		break;
	    	case 6:
	    		newPin = newPin + "5";
	    		break;
	    	case 7:
	    		newPin = newPin + "6";
	    		break;
	    	case 8:
	    		newPin = "";
	    		break;
	    	case 9:
	    		newPin = newPin + "1";
	    		break;
	    	case 10:
	    		newPin = newPin + "2";
	    		break;
	    	case 11:
	    		newPin = newPin + "3";
	    		break;
	    	case 12:
	    		if (newPin.length() == 4) {
                	if (CityConfig.debugMode == true) {
                		System.out.println("New Pin (1) currently: " + newPin);
                	}
	        		guiStage = "14";
	    		}
	    		break;
	    	case 13:
	    		newPin = newPin + "0";
	    		break;
    		case 21:
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("Ejected card.");
	    		}
    			guiStage = "1";
    			break;
    		case 20:
    			guiStage = "2";
    			break;
        	}
    	}
    	
		if (guiStage.equals("9")) {
			switch(guibutton.id) {
	    	case 4: //Cancel
	    		guiStage = "1";
	    		break;
    		case 20:
    			guiStage = "2";
    			break;
    		case 21:
    			guiStage = "7";
    			break;
    		}
		}
    	
    	/*
    	 *  TODO Withdraw X amount screen
    	 */
    	if (guiStage.equals("10")) {
            ByteArrayOutputStream bt = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(bt);
    		switch(guibutton.id) {
	    	case 9:
	    		withdrawCustom = withdrawCustom + "1";
	    		break;
	    	case 10:
	    		withdrawCustom = withdrawCustom + "2";
	    		break;
	    	case 11:
	    		withdrawCustom = withdrawCustom + "3";
	    		break;
	    	case 5:
	    		withdrawCustom = withdrawCustom + "4";
	    		break;
	    	case 6:
	    		withdrawCustom = withdrawCustom + "5";
	    		break;
	    	case 7:
	    		withdrawCustom = withdrawCustom + "6";
	    		break;
	    	case 1:
	    		withdrawCustom = withdrawCustom + "7";
	    		break;
			case 2:
				withdrawCustom = withdrawCustom + "8";
				break;
	    	case 3:
	    		withdrawCustom = withdrawCustom + "9";
	    		break;
	    	case 13:
	    		withdrawCustom = withdrawCustom + "0";
	    		break;
	    	case 8: //Clear
	    		withdrawCustom = "";
	    		break;
	    	case 12: //Confirm
	    		double customWithdrawFinal = EconUtils.parseDouble(withdrawCustom);
	            try {
	            	out.writeUTF("atmWithdraw");
	            	out.writeDouble(customWithdrawFinal);
	            	Packet250CustomPayload packet = new Packet250CustomPayload("FCitiesPackets", bt.toByteArray());
	            	
	            	PacketDispatcher.sendPacketToServer(packet);
		    		if (CityConfig.debugMode == true) {
		    			System.out.println("Packet sent! withdraw" + customWithdrawFinal);
		    		}
	            }
	            catch (IOException ex) {
	            	System.out.println("Packet Failed!");
	            }
	            withdrawAmount = customWithdrawFinal;
	            
	    		if (ClientPacketHandler.initBal >= withdrawAmount) {
	    			guiStage = "5";
	    		} else {
	    			guiStage = "6";
	    		}
	            withdrawCustom = "";
	    		break;
	    	case 4: //Cancel
	    		guiStage = "1";
	    		withdrawCustom = "";
	    		break;
    		case 20:
    			guiStage = "2";
    			withdrawCustom = "";
    			break;
    		}
    	}
    	
    	/*
    	 *  TODO DigiCoin Withdraw Screen
    	 */
    	
    	if (guiStage.equals("11")) {
            ByteArrayOutputStream bt = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(bt);
            
    		switch(guibutton.id) {
	    	case 9:
	    		withdrawDCCustom = withdrawDCCustom + "1";
	    		break;
	    	case 10:
	    		withdrawDCCustom = withdrawDCCustom + "2";
	    		break;
	    	case 11:
	    		withdrawDCCustom = withdrawDCCustom + "3";
	    		break;
	    	case 5:
	    		withdrawDCCustom = withdrawDCCustom + "4";
	    		break;
	    	case 6:
	    		withdrawDCCustom = withdrawDCCustom + "5";
	    		break;
	    	case 7:
	    		withdrawDCCustom = withdrawDCCustom + "6";
	    		break;
	    	case 1:
	    		withdrawDCCustom = withdrawDCCustom + "7";
	    		break;
			case 2:
				withdrawDCCustom = withdrawDCCustom + "8";
				break;
	    	case 3:
	    		withdrawDCCustom = withdrawDCCustom + "9";
	    		break;
	    	case 13:
	    		withdrawDCCustom = withdrawDCCustom + "0";
	    		break;
	    	case 8: //Clear
	    		withdrawDCCustom = "";
	    		break;
	    	case 12: //Confirm
	    		double customWithdrawDCFinal = EconUtils.parseDouble(withdrawDCCustom);
	    		try {
                	if (CityConfig.debugMode == true) {
                		System.out.println("Send the packet with value of " + withdrawDCCustom);
                	}
	    			out.writeUTF("digicoinWithdraw");
	    			out.writeDouble(customWithdrawDCFinal);
	    			out.writeUTF("" + Minecraft.getMinecraft().getSession().getUsername());
	    			Packet250CustomPayload packet = new Packet250CustomPayload("FCDigiCoinPkt", bt.toByteArray());
		            	
	    			PacketDispatcher.sendPacketToServer(packet);
	    			if (CityConfig.debugMode == true) {
	    				System.out.println("Packet sent! DigiCoin Deposit " + customWithdrawDCFinal);
	    			}
	    		}
	    		catch (IOException ex) {
	    			System.out.println("Packet Failed!");
	    		}
	    		withdrawAmount = customWithdrawDCFinal;
	    		guiStage = "13";
	    		withdrawDCCustom = "";
	    		break;
	    	case 4: //Cancel
	    		guiStage = "1";
	    		withdrawDCCustom = "";
	    		break;
    		case 20:
    			guiStage = "2";
	    		withdrawDCCustom = "";
    			break;
	    	}
    	}
    	
    	/*
    	 * TODO DigiCoin Deposit Screen
    	 */
    	if (guiStage.equals("12")) {
            ByteArrayOutputStream bt = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(bt);
            
    		switch(guibutton.id) {
	    	case 9:
	    		depositDCCustom = depositDCCustom + "1";
	    		break;
	    	case 10:
	    		depositDCCustom = depositDCCustom + "2";
	    		break;
	    	case 11:
	    		depositDCCustom = depositDCCustom + "3";
	    		break;
	    	case 5:
	    		depositDCCustom = depositDCCustom + "4";
	    		break;
	    	case 6:
	    		depositDCCustom = depositDCCustom + "5";
	    		break;
	    	case 7:
	    		depositDCCustom = depositDCCustom + "6";
	    		break;
	    	case 1:
	    		depositDCCustom = depositDCCustom + "7";
	    		break;
			case 2:
				depositDCCustom = depositDCCustom + "8";
				break;
	    	case 3:
	    		depositDCCustom = depositDCCustom + "9";
	    		break;
	    	case 13:
	    		depositDCCustom = depositDCCustom + "0";
	    		break;
	    	case 8: //Clear
	    		depositDCCustom = "";
	    		break;
	    	case 12: //Confirm
	    		double customDepositDCFinal = EconUtils.parseDouble(depositDCCustom);
	    		if (ClientPacketHandler.initBal >= customDepositDCFinal) {
		    		try {
	                	if (CityConfig.debugMode == true) {
	                		System.out.println("Send the packet with value of " + depositDCCustom);
	                	}
		    			out.writeUTF("digicoinDeposit");
		            	out.writeDouble(customDepositDCFinal);
		            	out.writeUTF("" + Minecraft.getMinecraft().getSession().getUsername());
		            	Packet250CustomPayload packet = new Packet250CustomPayload("FCDigiCoinPkt", bt.toByteArray());
		            	
		            	PacketDispatcher.sendPacketToServer(packet);
			    		if (CityConfig.debugMode == true) {
			    			System.out.println("Packet sent! DigiCoin Deposit " + customDepositDCFinal);
			    		}
		            }
		            catch (IOException ex) {
		            	System.out.println("Packet Failed!");
		            }
		            withdrawAmount = customDepositDCFinal;
		    		guiStage = "13";
		    		depositDCCustom = "";
	    		}
	    		break;
	    	case 4: //Cancel
	    		guiStage = "1";
	    		depositDCCustom = "";
	    		break;
    		case 20:
    			guiStage = "2";
    			depositDCCustom = "";
    			break;
	    	}
    	}
    	
		if (guiStage.equals("13")) {
			switch(guibutton.id) {
			case 4: //Cancel
				guiStage = "1";
				break;
			case 20:
				guiStage = "2";
				break;
			}
    	}
		
		/*
    	 *  TODO Change PIN Code
    	 */
		
    	if (guiStage.equals("14")) {
    		switch(guibutton.id) {
	    	case 1:
	    		confirmNewPin = confirmNewPin + "7";
	    		break;
			case 2:
				confirmNewPin = confirmNewPin + "8";
				break;
	    	case 3:
	    		confirmNewPin = confirmNewPin + "9";
	    		break;
	    	case 4:
	    		guiStage = "1";
	    		break;
	    	case 5:
	    		confirmNewPin = confirmNewPin + "4";
	    		break;
	    	case 6:
	    		confirmNewPin = confirmNewPin + "5";
	    		break;
	    	case 7:
	    		confirmNewPin = confirmNewPin + "6";
	    		break;
	    	case 8:
	    		confirmNewPin = "";
	    		break;
	    	case 9:
	    		confirmNewPin = confirmNewPin + "1";
	    		break;
	    	case 10:
	    		confirmNewPin = confirmNewPin + "2";
	    		break;
	    	case 11:
	    		confirmNewPin = confirmNewPin + "3";
	    		break;
	    	case 12:
            	if (CityConfig.debugMode == true) {
            		System.out.println("Confirming the new PIN, value is: " + confirmNewPin);
            	}
	    		if (confirmNewPin.length() == 4) {
                	if (CityConfig.debugMode == true) {
                		System.out.println("New Pin currently: " + confirmNewPin);
                	}
	    		}
	    		if (newPin.length() == 4 && confirmNewPin.length() == 4) {
                	if (CityConfig.debugMode == true) {
                		System.out.println("PINs are: " + newPin + " & " + confirmNewPin);
                	}
	    			if (newPin.equals(confirmNewPin)) {
	                	if (CityConfig.debugMode == true) {
	                		System.out.println("And they match!");
	                	}
		    			guiStage = "15";
		    			
		    			//Let the server know the PIN has been changed.
		    			//Not that secure right now, I'll fix that later.
		                ByteArrayOutputStream bt = new ByteArrayOutputStream();
		                DataOutputStream out = new DataOutputStream(bt);
		                try {
		                	out.writeUTF("changePin");
		                	out.writeUTF(newPin);
		                	out.writeUTF("" + Minecraft.getMinecraft().getSession().getUsername());
		                	
		                	Packet250CustomPayload packet = new Packet250CustomPayload("FCCardPin", bt.toByteArray());
		                	PacketDispatcher.sendPacketToServer(packet);
		                	if (CityConfig.debugMode == true) {
		                		System.out.println("Balance Packet sent! Value: " + newPin);
		                	}
		                	newPin = "";
		                	confirmNewPin = "";
		                }
		                catch (IOException ex) {
		                	System.out.println("Packet Failed!");
		                }
	    			} else {
	    				System.out.println("But they don't match!");
	                	newPin = "";
	                	confirmNewPin = "";
	    				guiStage = "16";
	    			}
	    		}
	    		break;
	    	case 13:
	    		confirmNewPin = confirmNewPin + "0";
	    		break;
    		case 21:
    			guiStage = "1";
    			break;
    		case 20:
    			guiStage = "2";
    			break;
        	}
    	}
    	
		if (guiStage.equals("15")) {
			switch(guibutton.id) {
	    	case 4: //Cancel
	    		guiStage = "1";
	    		withdrawCustom = "";
	    		break;
    		case 20:
    			guiStage = "2";
    			withdrawCustom = "";
    			break;
    		}
		}
		
		if (guiStage.equals("16")) {
			switch(guibutton.id) {
	    	case 4: //Cancel
	    		guiStage = "1";
	    		withdrawCustom = "";
	    		break;
    		case 20:
    			guiStage = "2";
    			withdrawCustom = "";
    			break;
    		}
		}
		
		//Debug Messages
		switch(guibutton.id) {
		case 1:
			if (CityConfig.debugMode == true) {
				System.out.println("You pressed 7");
			}
			break;
		case 2:
			if (CityConfig.debugMode == true) {
				System.out.println("You pressed 8");
			}
			break;
		case 3:
			if (CityConfig.debugMode == true) {
				System.out.println("You pressed 9");
			}
			break;
		case 4:
			if (CityConfig.debugMode == true) {
				System.out.println("You pressed Cancel");
			}
			break;
		case 5:
			if (CityConfig.debugMode == true) {
				System.out.println("You pressed 4");
			}
			break;
		case 6:
			if (CityConfig.debugMode == true) {
				System.out.println("You pressed 5");
			}
			break;
		case 7:
			if (CityConfig.debugMode == true) {
				System.out.println("You pressed 6");
			}
			break;
		case 8:
			if (CityConfig.debugMode == true) {
				System.out.println("You pressed Clear");
			}
			break;
		case 9:
			if (CityConfig.debugMode == true) {
				System.out.println("You pressed 1");
			}
			break;
		case 10:
			if (CityConfig.debugMode == true) {
				System.out.println("You pressed 2");
			}
			break;
		case 11:
			if (CityConfig.debugMode == true) {
				System.out.println("You pressed 3");
			}
			break;
		case 12:
			if (CityConfig.debugMode == true) {
				System.out.println("You pressed Confirm");
			}
			break;
		case 13:
			if (CityConfig.debugMode == true) {
				System.out.println("You pressed 0");
			}
			break;
		case 14:
			if (CityConfig.debugMode == true) {
				System.out.println("You pressed the Top-Left ATM Button");
			}
			break;
		case 15:
			if (CityConfig.debugMode == true) {
				System.out.println("You pressed the Top-Right ATM Button");
			}
			break;
		case 16:
			if (CityConfig.debugMode == true) {
				System.out.println("You pressed the Mid-Upper-Left ATM Button");
			}
			break;
		case 17:
			if (CityConfig.debugMode == true) {
				System.out.println("You pressed the Mid-Upper-Right ATM Button");
			}
			break;
		case 18:
			if (CityConfig.debugMode == true) {
				System.out.println("You pressed the Mid-Lower-Left ATM Button");
			}
			break;
		case 19:
			if (CityConfig.debugMode == true) {
				System.out.println("You pressed the Mid-Lower-Right ATM Button");
			}
			break;
		case 20:
			if (CityConfig.debugMode == true) {
				System.out.println("You pressed the Bottom-Left ATM Button");
			}
			break;
		case 21:
			if (CityConfig.debugMode == true) {
				System.out.println("You pressed the Bottom-Right ATM Button");
			}
			break;
		}

    }
    
    private void isPinLongEnough (String pinCode) {
    	if (pinCode.length() == 5)
    		this.authenticatePin (pinCode);
    }
        
    private void authenticatePin (String pinCode) {
		EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
    	if (pinCode.equals(DebitCardItem.checkCardPin(player) + "c")) {
    		if (CityConfig.debugMode == true) {
    			System.out.println("Correct Pin!");
    		}
    		guiStage = "2";
    		enteredPin = "";
    		pinAttempt = "1";
    	} else if (pinAttempt.equals("1")) {
    		enteredPin = "";
    		pinAttempt = "2";
    	} else if (pinAttempt.equals("2")) {
    		enteredPin = "";
    		pinAttempt = "3";
    	} else if (pinAttempt.equals("3")) {
    		enteredPin = "";
    		pinAttempt = "4";
    		
            ByteArrayOutputStream bt = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(bt);
    		
            try {
            	out.writeUTF("failedPin");
            	Packet250CustomPayload packet = new Packet250CustomPayload("FCitiesPackets", bt.toByteArray());
            	PacketDispatcher.sendPacketToServer(packet);
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("Packet sent! Failed Pin");
	    		}
            }
            catch (IOException ex) {
            	System.out.println("Packet Failed!");
            }
    	}
    }
    
    private void consumeCard (EntityPlayer player) {
        ItemStack item = player.getHeldItem();
    	if (player.getHeldItem().getItem() == CoreItems.debitCard) {
            --item.stackSize;
    	}
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2) {
    	if (guiStage.equals("1")) {
	    	fontRenderer.drawString("ATM", -21, -30, 0x404040);
	    	fontRenderer.drawString("Welcome!", 68, -2, 0x007F0E);
	    	fontRenderer.drawString("Please enter your PIN,", 32, 8, 0x007F0E);
	    	fontRenderer.drawString("followed by 'Confirm'", 37, 18, 0x007F0E);
    		if (enteredPin.length() == 1) {
    			fontRenderer.drawString("*", 78, 48, 0x007F0E);
    		} else if (enteredPin.length() == 2) {
    			fontRenderer.drawString("**", 78, 48, 0x007F0E);
    		} else if (enteredPin.length() == 3) {
    			fontRenderer.drawString("***", 78, 48, 0x007F0E);
    		} else if (enteredPin.length() >= 4) {
    			fontRenderer.drawString("****", 78, 48, 0x007F0E);
    		}
	    	if (pinAttempt.equals("1")) {
		    	fontRenderer.drawString("Attempt 1 of 3.", 52, 68, 0x007F0E);
	    	}
	    	if (pinAttempt.equals("2")) {
		    	fontRenderer.drawString("Attempt 2 of 3.", 52, 68, 0xFFD800);
	    	}
	    	if (pinAttempt.equals("3")) {
		    	fontRenderer.drawString("Attempt 3 of 3.", 52, 68, 0xFF6A00);
	    	}
	    	if (pinAttempt.equals("4")) {
		    	fontRenderer.drawString("Attempt 3 of 3.", 52, 68, 0x007F0E);
	    		fontRenderer.drawString("Card declined!", 53, 78, 0x7F0000);
	    	}
    	}
    	if (guiStage.equals("2")) {
        	fontRenderer.drawString("ATM", -21, -30, 0x404040);
        	fontRenderer.drawString("Withdraw", 12, -3, 0x007F0E);
        	fontRenderer.drawString("Balance", 126, -3, 0x007F0E);
        	if (ClientPacketHandler.foundPlugin == true) {
        		fontRenderer.drawString("DigiCoin", 12, 24, 0x007F0E);
        	} else if (ClientPacketHandler.foundPlugin == false) {
        		fontRenderer.drawString("DigiCoin", 12, 24, 0x404040);
        	}
        	fontRenderer.drawString("Change PIN", 109, 24, 0x007F0E);
        	fontRenderer.drawString("Eject Card", 109, 78, 0x007F0E);
    	}
    	if (guiStage.equals("3")) {
        	fontRenderer.drawString("ATM", -21, -30, 0x404040);
    		fontRenderer.drawString("Please select the amount", 26, -12, 0xFFD800);
	    	fontRenderer.drawString("you wish to withdraw.", 37, -2, 0xFFD800);
        	fontRenderer.drawString("10", 12, 24, 0x007F0E);
        	fontRenderer.drawString("50", 12, 51, 0x007F0E);
        	fontRenderer.drawString("250", 12, 78, 0x007F0E);
        	fontRenderer.drawString("20", 153, 24, 0x007F0E);
        	fontRenderer.drawString("100", 147, 51, 0x007F0E);
        	fontRenderer.drawString("Input Amount", 100, 78, 0x007F0E);
    	}
    	if (guiStage.equals("4")) {
    		fontRenderer.drawString("ATM", -21, -30, 0x404040);
    		fontRenderer.drawString("Your current balance is: ", 26, 8, 0x007F0E);
    		fontRenderer.drawString("" + ClientPacketHandler.initBal, 26, 18, 0x007F0E);
        	fontRenderer.drawString("Back", 12, 78, 0x007F0E);
    	}
    	if (guiStage.equals("5")) {
        	fontRenderer.drawString("ATM", -21, -30, 0x404040);
    		fontRenderer.drawString("Withdrawl Successful!", 35, 8, 0x007F0E);
    		fontRenderer.drawString("You have withdrawn", 39, 18, 0x007F0E);
    		fontRenderer.drawString(withdrawAmount + " " + CityConfig.currencyLargePlural, 50, 28, 0x007F0E);
    		fontRenderer.drawString("Press Confirm to continue.", 22, 58, 0x007F0E);

    	}
    	if (guiStage.equals("6")) {
        	fontRenderer.drawString("ATM", -21, -30, 0x404040);
    		fontRenderer.drawString("Insufficient Funds!", 41, -2, 0x7F0000);
    		fontRenderer.drawString(ClientPacketHandler.shortValue + " more needed!", 43, 8, 0x7F0000);
    		fontRenderer.drawString("Withdraw Less", 97, 24, 0x007F0E);
    		fontRenderer.drawString("Return to Menu", 90, 51, 0x007F0E);
        	fontRenderer.drawString("Eject Card", 109, 78, 0x007F0E);
    	}
    	if (guiStage.equals("7")) {
        	fontRenderer.drawString("ATM - DigiCoin", -21, -30, 0x404040);
    		fontRenderer.drawString("Welcome to the DigiCoin", 32, -12, 0x007F0E);
    		fontRenderer.drawString("Transfer Screen!", 44, -2, 0x007F0E);
    		fontRenderer.drawString("Please select an option:", 28, 18, 0xFFD800);
	    	fontRenderer.drawString("Deposit", 12, 51, 0x007F0E);
	    	//Temporarily doesn't work.
	    	fontRenderer.drawString("Withdraw", 123, 51, 0x404040);
        	fontRenderer.drawString("Back", 12, 78, 0x007F0E);
    	}
    	if (guiStage.equals("8")) {
        	fontRenderer.drawString("ATM", -21, -30, 0x404040);
    		fontRenderer.drawString("Please enter your new PIN.", 20, -12, 0xFFD800);
    		fontRenderer.drawString("" + newPin, 74, 48, 0x007F0E);
    		fontRenderer.drawString("Cancel", 12, 78, 0x007F0E);
    	}
    	if (guiStage.equals("9")) {
        	fontRenderer.drawString("ATM - DigiCoin", -21, -30, 0x404040);
    		fontRenderer.drawString("DigiCoin is not installed on this", 12, -12, 0xFFD800);
    		fontRenderer.drawString("server.", 12, -2, 0xFFD800);
    		fontRenderer.drawString("Please install MCPC+, then add", 12, 18, 0xFFD800);
    		fontRenderer.drawString("the DigiCoin plugin to your", 12, 28, 0xFFD800);
    		fontRenderer.drawString("plugins folder.", 12, 38, 0xFFD800);
    		fontRenderer.drawString("Back", 12, 78, 0x007F0E);
    		fontRenderer.drawString("Continue Anyway", 82, 78, 0x007F0E);
    	}
    	if (guiStage.equals("10")) {
        	fontRenderer.drawString("ATM", -21, -30, 0x404040);
    		fontRenderer.drawString("Please enter the amount", 26, -12, 0xFFD800);
    		fontRenderer.drawString("you wish to withdraw:", 37, -2, 0xFFD800);
    		fontRenderer.drawString("" + withdrawCustom, 72, 24, 0x007F0E);
        	fontRenderer.drawString("Back", 12, 78, 0x007F0E);
    	}
    	if (guiStage.equals("11")) {
        	fontRenderer.drawString("ATM - DigiCoin", -21, -30, 0x404040);
    		fontRenderer.drawString("Please enter the amount", 26, -12, 0xFFD800);
	    	fontRenderer.drawString("you wish to withdraw.", 37, -2, 0xFFD800);
        	fontRenderer.drawString("" + withdrawDCCustom, 72, 24, 0x007F0E);
        	fontRenderer.drawString("Back", 12, 78, 0x007F0E);
    	}
    	if (guiStage.equals("12")) {
        	fontRenderer.drawString("ATM - DigiCoin", -21, -30, 0x404040);
    		fontRenderer.drawString("Please enter the amount", 26, -12, 0xFFD800);
	    	fontRenderer.drawString("you wish to deposit.", 37, -2, 0xFFD800);
        	fontRenderer.drawString("" + depositDCCustom, 72, 24, 0x007F0E);
        	fontRenderer.drawString("Back", 12, 78, 0x007F0E);
    	}
    	if (guiStage.equals("13")) {
        	fontRenderer.drawString("ATM - DigiCoin", -21, -30, 0x404040);
        	fontRenderer.drawString("Congratulations;", 47, -12, 0xFFD800);
        	fontRenderer.drawString("Transfer Successful!", 34, -2, 0xFFD800);
        	fontRenderer.drawString("Back", 12, 78, 0x007F0E);
    	}
    	if (guiStage.equals("14")) {
    		fontRenderer.drawString("ATM", -21, -30, 0x404040);
    		fontRenderer.drawString("Please confirm your PIN", 26, -12, 0xFFD800);
    		fontRenderer.drawString("" + confirmNewPin, 74, 48, 0x007F0E);
    		fontRenderer.drawString("Cancel", 12, 78, 0x007F0E);
    	}
    	if (guiStage.equals("15")) {
        	fontRenderer.drawString("ATM", -21, -30, 0x404040);
    		fontRenderer.drawString("Your PIN has been changed!", 16, -12, 0x007F0E);
    		fontRenderer.drawString("Back", 12, 78, 0x007F0E);
    	}
    	
    	if (guiStage.equals("16")) {
        	fontRenderer.drawString("ATM", -21, -30, 0x404040);
        	fontRenderer.drawString("   PIN Codes do not match!   ", 18, -2, 0x7F0000);
    		fontRenderer.drawString("Back", 12, 78, 0x007F0E);
    	}
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.mc.getTextureManager().bindTexture(texture);
            int x = (width - xSize) / 2;
            int y = (height - ySize) / 2;
            this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}