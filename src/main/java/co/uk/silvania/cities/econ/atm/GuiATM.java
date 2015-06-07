package co.uk.silvania.cities.econ.atm;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.cities.core.CityConfig;
import co.uk.silvania.cities.core.FlenixCities_Core;
import co.uk.silvania.cities.econ.DebitCardItem;
import co.uk.silvania.cities.econ.EconUtils;
import co.uk.silvania.cities.network.ATMWithdrawPacket;
import co.uk.silvania.cities.network.ServerBalancePacket;
import co.uk.silvania.cities.network.SoundPacket;

public class GuiATM extends GuiContainer {

    private static final ResourceLocation texture = new ResourceLocation("flenixcities", "textures/gui/atm.png");

    
    public GuiATM (InventoryPlayer inventoryPlayer, TileEntityATMEntity tileEntity, World world, int x, int y, int z) {
    	super(new ContainerATM(inventoryPlayer, tileEntity));
    }
    
    protected int xSize = 232;
    protected int ySize = 242;
    
    String enteredPin = "";
    String pinAttempt = "1";
    String guiStage = "1";
    String balance = "0";
    String withdrawCustom = "";
    int withdrawAmount;
    double initBalance;
    
    //GUI Stage changes the current active screen. It enables different features on the buttons, and changes the text.
    //Stage 1 asks for pin and waits for it to be entered.
    //Stage 2 is an options menu, asking for your next move.
    //Stage 3 is the Withdraw screen, the buttons will give money and remove from your NBT
    //Stage 4 is the Balance screen, and shows your balance.
    //Stage 5 is the withdraw confirm screen.
    //Stage 6 is the insufficient funds error message.
    //Stage 7 is the Withdraw X Amount screen.
    //Stage 8+ informs of an invalid selection (due to removed options in 1.7.10 (DigiCoin, PIN change))
    //Each stage has a TO-DO list so it can be easily found in eclipse. This is a damn long class, so added this for sanity.
    //TODO Text String position/colours for 7-16, all active code for 7-16, NBT Balance Display in-ATM.
    
    @Override
    public void initGui() {
    	super.initGui();
    	initBalance = EconUtils.parseDouble(ServerBalancePacket.balanceAmount);
    	System.out.println("Balance available in GUI! Balance: $" + initBalance);
    	FlenixCities_Core.network.sendToServer(new SoundPacket("flenixcities:cardInsert"));
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
    	FlenixCities_Core.network.sendToServer(new SoundPacket("flenixcities:atmButton"));
    	//TODO Asks for PIN
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
    	

    	//TODO Options menu- what do next?
    	if (guiStage.equals("2")) {

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
    	

    	//TODO Withdraw Screen. Sends a packet with the withdraw amount, which checks you have enough. If so, it'll give you the cash and remove from your NBT.
    	if (guiStage.equals("3")) {            
    		switch(guibutton.id) {
	    	case 4:
	    		guiStage = "1";
	    		break;
    		case 16:
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("Withdraw 10");
	    		}
	    		withdrawAmount = 10;
	    		withdrawFunds(withdrawAmount);
	    		break;
	    	case 17:
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("Withdraw 20");
	    		}
	    		withdrawAmount = 20;
	    		withdrawFunds(withdrawAmount);
	    		break;
	    	case 18:
            	if (CityConfig.debugMode == true) {
            		System.out.println("Withdraw 50");
            	}
            	withdrawAmount = 50;
            	withdrawFunds(withdrawAmount);
	    		break;
	    	case 19:
            	if (CityConfig.debugMode == true) {
            		System.out.println("Withdraw 100");
    			}
	    		withdrawAmount = 100;
	    		withdrawFunds(withdrawAmount);
	    		break;
	    	case 20:
            	if (CityConfig.debugMode == true) {
            		System.out.println("Withdraw 250");
            	}

	    		withdrawAmount = 250;
	    		withdrawFunds(withdrawAmount);
	    		break;
	    	case 21:
	    		System.out.println("Define Amount");
	    		guiStage = "7";
	    		break;
	    	}
    	}
    	
    	//TODO balance screen.
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
    	
    	//TODO Confirm withdrawl.
    	if (guiStage.equals("5")) {
    		FlenixCities_Core.network.sendToServer(new SoundPacket("flenixcities:atmButton"));
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
    	
    	//TODO Not enough moolah.
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
    	
    	//TODO Withdraw X amount screen
    	if (guiStage.equals("7")) {
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
	    		int customWithdrawFinal = EconUtils.parseInt(withdrawCustom);
	            
	            withdrawAmount = customWithdrawFinal;
	            withdrawFunds(withdrawAmount);
	            
	    		//if (ClientPacketHandler.initBal >= withdrawAmount) {
	    		//	guiStage = "5";
	    		//} else {
	    			guiStage = "6";
	    		//}
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
    	
		if (EconUtils.parseInt(guiStage) >= 8) {
			switch(guibutton.id) {
	    	case 4: //Cancel
	    		guiStage = "1";
	    		break;
    		case 14:
    			guiStage = "2";
    			break;
			}
		}
		
		//Debug Messages
		if (CityConfig.debugMode == true) {
			switch(guibutton.id) {
			case 1:
				System.out.println("You pressed 7");
				break;
			case 2:
				System.out.println("You pressed 8");
				break;
			case 3:
				System.out.println("You pressed 9");
				break;
			case 4:
				System.out.println("You pressed Cancel");
				break;
			case 5:
				System.out.println("You pressed 4");
				break;
			case 6:
				System.out.println("You pressed 5");
				break;
			case 7:
				System.out.println("You pressed 6");
				break;
			case 8:
				System.out.println("You pressed Clear");
				break;
			case 9:
				System.out.println("You pressed 1");
				break;
			case 10:
				System.out.println("You pressed 2");
				break;
			case 11:
				System.out.println("You pressed 3");
				break;
			case 12:
				System.out.println("You pressed Confirm");
				break;
			case 13:
				System.out.println("You pressed 0");
				break;
			case 14:
				System.out.println("You pressed the Top-Left ATM Button");
				break;
			case 15:
				System.out.println("You pressed the Top-Right ATM Button");
				break;
			case 16:
				System.out.println("You pressed the Mid-Upper-Left ATM Button");
				break;
			case 17:
				System.out.println("You pressed the Mid-Upper-Right ATM Button");
				break;
			case 18:
				System.out.println("You pressed the Mid-Lower-Left ATM Button");
				break;
			case 19:
				System.out.println("You pressed the Mid-Lower-Right ATM Button");
				break;
			case 20:
				System.out.println("You pressed the Bottom-Left ATM Button");
				break;
			case 21:
				System.out.println("You pressed the Bottom-Right ATM Button");
				break;
			}
		}
    }
    
    private void withdrawFunds(int amt) {
		if (initBalance >= withdrawAmount) {
			guiStage = "5";
			FlenixCities_Core.network.sendToServer(new ATMWithdrawPacket(amt));
		} else {
			guiStage = "6";
		}
    }
    
    private void isPinLongEnough (String pinCode) {
    	if (pinCode.length() == 5)
    		this.authenticatePin(pinCode);
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
    		
            this.mc.displayGuiScreen(null);
            /*ByteArrayOutputStream bt = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(bt);
    		
            try {
            	Thread.sleep(3000);
            } catch (InterruptedException ex) {
            	Thread.currentThread().interrupt();
            }
            System.out.println("after that little nap we'll send the packet");
            
            try {
            	out.writeUTF("failedPin");
            	out.writeDouble(0);
            	Packet250CustomPayload packet = new Packet250CustomPayload("FCitiesPackets", bt.toByteArray());
            	PacketDispatcher.sendPacketToServer(packet);
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("Packet sent! Failed Pin");
	    		}
            }
            catch (IOException ex) {
            	System.out.println("Packet Failed!");
            }*/
    	}
    }
    private int tick = 0;
    
    @Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2) {
    	fontRendererObj.drawString("ATM", -21, -30, 0x404040);
    	String bal = EconUtils.formatBalance(initBalance);
    	String shortAmt = "" + (withdrawAmount - EconUtils.parseDouble(bal));
    	//double initBal = ClientPacketHandler.initBal;
    	String underScore = "";
    	if (tick < 80) {
    		tick++;
    	} else if (tick >= 80) {
    		tick = 0;
    	}
    	
    	if (tick < 40) {
    		underScore = "_";
    	}
    	
    	if (guiStage.equals("1")) {
	    	fontRendererObj.drawString("ATM", -21, -30, 0x404040);
	    	fontRendererObj.drawString("Welcome!", 68, -2, 0x007F0E);
	    	fontRendererObj.drawString("Please enter your PIN,", 32, 8, 0x007F0E);
	    	fontRendererObj.drawString("followed by 'Confirm'", 37, 18, 0x007F0E);
    		if (enteredPin.length() == 0) {
    			fontRendererObj.drawString(underScore, 78, 48, 0x007F0E);
    		} else if (enteredPin.length() == 1) {
    			fontRendererObj.drawString("*" + underScore, 78, 48, 0x007F0E);
    		} else if (enteredPin.length() == 2) {
    			fontRendererObj.drawString("**" + underScore, 78, 48, 0x007F0E);
    		} else if (enteredPin.length() == 3) {
    			fontRendererObj.drawString("***" + underScore, 78, 48, 0x007F0E);
    		} else if (enteredPin.length() >= 4) {
    			fontRendererObj.drawString("****", 78, 48, 0x007F0E);
    		}
	    	if (pinAttempt.equals("1")) {
		    	fontRendererObj.drawString("Attempt 1 of 3.", 52, 68, 0x007F0E);
	    	}
	    	if (pinAttempt.equals("2")) {
		    	fontRendererObj.drawString("Attempt 2 of 3.", 52, 68, 0xFFD800);
	    	}
	    	if (pinAttempt.equals("3")) {
		    	fontRendererObj.drawString("Attempt 3 of 3.", 52, 68, 0xFF6A00);
	    	}
	    	if (pinAttempt.equals("4")) {
		    	fontRendererObj.drawString("Attempt 3 of 3.", 52, 68, 0x007F0E);
	    		fontRendererObj.drawString("Card declined!", 53, 78, 0x7F0000);
	    	}
    	}
    	if (guiStage.equals("2")) {
        	fontRendererObj.drawString("ATM", -21, -30, 0x404040);
        	fontRendererObj.drawString("Withdraw", 12, -3, 0x007F0E);
        	fontRendererObj.drawString("Balance", 126, -3, 0x007F0E);
        	fontRendererObj.drawString("Change PIN", 109, 24, 0x007F0E);
        	fontRendererObj.drawString("Eject Card", 109, 78, 0x007F0E);
    	}
    	if (guiStage.equals("3")) {
        	fontRendererObj.drawString("ATM", -21, -30, 0x404040);
    		fontRendererObj.drawString("Please select the amount", 26, -12, 0xFFD800);
	    	fontRendererObj.drawString("you wish to withdraw.", 37, -2, 0xFFD800);
        	fontRendererObj.drawString("10", 12, 24, 0x007F0E);
        	fontRendererObj.drawString("50", 12, 51, 0x007F0E);
        	fontRendererObj.drawString("250", 12, 78, 0x007F0E);
        	fontRendererObj.drawString("20", 153, 24, 0x007F0E);
        	fontRendererObj.drawString("100", 147, 51, 0x007F0E);
        	fontRendererObj.drawString("Input Amount", 100, 78, 0x007F0E);
    	}
    	if (guiStage.equals("4")) {
    		fontRendererObj.drawString("ATM", -21, -30, 0x404040);
    		fontRendererObj.drawString("Your current balance is: ", 26, 8, 0x007F0E);
    		fontRendererObj.drawString("$" + bal, 26, 18, 0x007F0E);
        	fontRendererObj.drawString("Back", 12, 78, 0x007F0E);
    	}
    	if (guiStage.equals("5")) {
        	fontRendererObj.drawString("ATM", -21, -30, 0x404040);
    		fontRendererObj.drawString("Withdrawl Successful!", 35, 8, 0x007F0E);
    		fontRendererObj.drawString("You have withdrawn", 39, 18, 0x007F0E);
    		fontRendererObj.drawString("$" + EconUtils.formatBalance(withdrawAmount) + " " + CityConfig.currencyLargePlural, 50, 28, 0x007F0E);
    		fontRendererObj.drawString("Press Confirm to continue.", 22, 58, 0x007F0E);

    	}
    	if (guiStage.equals("6")) {
        	fontRendererObj.drawString("ATM", -21, -30, 0x404040);
    		fontRendererObj.drawString("Insufficient Funds!", 41, -2, 0x7F0000);
    		fontRendererObj.drawString("$" + shortAmt + " more needed!", 43, 8, 0x7F0000);
    		fontRendererObj.drawString("Withdraw Less", 97, 24, 0x007F0E);
    		fontRendererObj.drawString("Return to Menu", 90, 51, 0x007F0E);
        	fontRendererObj.drawString("Eject Card", 109, 78, 0x007F0E);
    	}
    	if (guiStage.equals("7")) {
        	fontRendererObj.drawString("ATM", -21, -30, 0x404040);
    		fontRendererObj.drawString("Please enter the amount", 26, -12, 0xFFD800);
    		fontRendererObj.drawString("you wish to withdraw:", 37, -2, 0xFFD800);
    		fontRendererObj.drawString("$" + withdrawCustom, 72, 24, 0x007F0E);
        	fontRendererObj.drawString("Back", 12, 78, 0x007F0E);
    	}
    	if (EconUtils.parseInt(guiStage) >= 8) {
        	fontRendererObj.drawString("ATM", -21, -30, 0x404040);
    		fontRendererObj.drawString("   Invalid Selection    ", 26, -12, 0xFFD800);
        	fontRendererObj.drawString("Main Menu", 12, 24, 0x007F0E);
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
    
    @Override
    public void onGuiClosed() {
    	System.out.println("Closed GUI!");
    }
}