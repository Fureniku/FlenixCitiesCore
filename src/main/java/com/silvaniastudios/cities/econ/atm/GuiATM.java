package com.silvaniastudios.cities.econ.atm;

import org.lwjgl.opengl.GL11;

import com.silvaniastudios.cities.core.CityConfig;
import com.silvaniastudios.cities.core.FlenixCities;
import com.silvaniastudios.cities.econ.DebitCardItem;
import com.silvaniastudios.cities.econ.EconUtils;
import com.silvaniastudios.cities.network.ATMWithdrawPacket;
import com.silvaniastudios.cities.network.ServerBalancePacket;
import com.silvaniastudios.cities.network.SoundPacket;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class GuiATM extends GuiScreen {

    private static final ResourceLocation texture = new ResourceLocation("flenixcities", "textures/gui/atm.png");
    public EconUtils econ = new EconUtils();

    protected int xSize = 232;
    protected int ySize = 242;
    
    String enteredPin = "";
    String pinAttempt = "1";
    String guiStage = "1";
    String balance = "0";
    String withdrawCustom = "";
    int withdrawAmount;
    int initBalance;
    
    private GuiButton button0;
    private GuiButton button1;
    private GuiButton button2;
    private GuiButton button3;
    private ATMButton button4;
    private ATMButton button5;
    private ATMButton button6;
    private ATMButton button7;
    private ATMButton button8;
    private ATMButton button9;
    private RedButton buttonCancel;
    private YellowButton buttonClear;
    private GreenButton buttonConfirm;
    
    private ATMButtonLeft buttonTopLeft;
    private ATMButtonLeft buttonTopMidLeft;
    private ATMButtonLeft buttonBottomMidLeft;
    private ATMButtonLeft buttonBottomLeft;
    
    private ATMButtonRight buttonTopRight;
    private ATMButtonRight buttonTopMidRight;
    private ATMButtonRight buttonBottomMidRight;
    private ATMButtonRight buttonBottomRight;
    
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
    	System.out.println("initGui");
    	buttonList.clear();
    	int guiLeft = width / 2;
    	int guiTop = height / 2;
    	FlenixCities.network.sendToServer(new SoundPacket("flenixcities:cardInsert"));
    	buttonList.add(button7 = new ATMButton(1, guiLeft + 21, guiTop + 109, 24, 15, "7")); // 7
    	buttonList.add(button8 = new ATMButton(2, guiLeft + 53, guiTop + 109, 24, 15, "8")); // 8
    	buttonList.add(button9 = new ATMButton(3, guiLeft + 85, guiTop + 109, 24, 15, "9")); // 9
    	buttonList.add(buttonCancel = new RedButton(4, guiLeft + 121, guiTop + 109, 24, 15, "")); // Cancel
    	buttonList.add(button4 = new ATMButton(5, guiLeft + 21, guiTop + 133, 24, 15, "4")); // 4
    	buttonList.add(button5 = new ATMButton(6, guiLeft + 53, guiTop + 133, 24, 15, "5")); // 5
    	buttonList.add(button6 = new ATMButton(7, guiLeft + 85, guiTop + 133, 24, 15, "6")); // 6
    	buttonList.add(buttonClear = new YellowButton(8, guiLeft + 121, guiTop + 133, 24, 15, "")); // Clear
    	buttonList.add(button1 = new ATMButton(9, guiLeft + 21, guiTop + 157, 24, 15, "1")); // 1
    	buttonList.add(button2 = new ATMButton(10, guiLeft + 53, guiTop + 157, 24, 15, "2")); // 2
    	buttonList.add(button3 = new ATMButton(11, guiLeft + 85, guiTop + 157, 24, 15, "3")); // 3
    	buttonList.add(buttonConfirm = new GreenButton(12, guiLeft + 121, guiTop + 157, 24, 15, "")); // Confirm
    	buttonList.add(button0 = new ATMButton(13, guiLeft + 53, guiTop + 181, 24, 15, "0")); // 0
    	
    	buttonList.add(buttonTopLeft = new ATMButtonLeft(14, guiLeft - 21, guiTop - 7, 24, 15, "")); //Top-Left
    	buttonList.add(buttonTopMidLeft = new ATMButtonLeft(16, guiLeft - 21, guiTop + 20, 24, 15, "")); //Mid-Upper Left
    	buttonList.add(buttonBottomMidLeft = new ATMButtonLeft(18, guiLeft - 21, guiTop + 47, 24, 15, "")); //Mid-Lower Left
    	buttonList.add(buttonBottomLeft = new ATMButtonLeft(20, guiLeft - 21, guiTop + 74, 24, 15, "")); //Bottom Left
    	
    	buttonList.add(buttonTopRight = new ATMButtonRight(15, guiLeft + 173, guiTop - 7, 24, 15, "")); //Top-Right
    	buttonList.add(buttonTopMidRight = new ATMButtonRight(17, guiLeft + 173, guiTop + 20, 24, 15, "")); //Mid-Upper Right
    	buttonList.add(buttonBottomMidRight = new ATMButtonRight(19, guiLeft + 173, guiTop + 47, 24, 15, "")); //Mid-Lower Right
    	buttonList.add(buttonBottomRight = new ATMButtonRight(21, guiLeft + 173, guiTop + 74, 24, 15, "")); //Bottom Right
    }
    
    public void actionPerformed(GuiButton guibutton) {
    	initBalance = econ.parseInt(ServerBalancePacket.balanceAmount);
    	if (CityConfig.debugMode) {
    		System.out.println("Balance available in GUI! Balance: $" + initBalance);
    	}
    	//Plays the sound, and while it's at it also updates client balance.
    	//Updating the balance on every button press means withdrawls etc will update the balance, and makes it effectively bulletproof
    	//against latency issues.
    	FlenixCities.network.sendToServer(new SoundPacket("flenixcities:atmButton"));
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
    		FlenixCities.network.sendToServer(new SoundPacket("flenixcities:atmButton"));
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
	    		int customWithdrawFinal = econ.parseInt(withdrawCustom);
	            
	            withdrawAmount = customWithdrawFinal;
	            withdrawFunds(withdrawAmount);
	            
	    		if (initBalance >= withdrawAmount) {
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
    	
		if (econ.parseInt(guiStage) >= 8) {
			switch(guibutton.id) {
	    	case 4: //Cancel
	    		guiStage = "1";
	    		break;
    		case 16:
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
    
    @Override
    public void updateScreen() {
    	/*button0.visible = true;
    	button1.visible = true;
    	button2.visible = true;
    	button3.visible = true;
    	button4.visible = true;
    	button5.visible = true;
    	button6.visible = true;
    	button7.visible = true;
    	button8.visible = true;
    	button9.visible = true;*/
    }
    
    private void withdrawFunds(int amt) {
		if (initBalance >= withdrawAmount) {
			guiStage = "5";
			FlenixCities.network.sendToServer(new ATMWithdrawPacket(amt));
		} else {
			guiStage = "6";
		}
    }
    
    private void isPinLongEnough (String pinCode) {
    	if (pinCode.length() == 5)
    		this.authenticatePin(pinCode);
    }
        
    private void authenticatePin (String pinCode) {
		EntityPlayerSP player = Minecraft.getMinecraft().player;
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
    	}
    }
    private int tick = 0;
    
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    @Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int x = (width-xSize) / 2;
        int y = (height-ySize) / 2;
        int right = 28;
        int up = -88;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        
        button0.drawButton(mc, mouseX, mouseY, partialTicks);
        button1.drawButton(mc, mouseX, mouseY, partialTicks);
        button2.drawButton(mc, mouseX, mouseY, partialTicks);
        button3.drawButton(mc, mouseX, mouseY, partialTicks);
    	
    	fontRenderer.drawString("ATM", x-21, x-30, 0x404040);
    	String bal = econ.formatBalance(initBalance);
    	String shortAmt = "" + (withdrawAmount - econ.parseDouble(bal));
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
	    	fontRenderer.drawString("ATM", x-21+right, x-30+up, 0x404040);
	    	fontRenderer.drawString("Welcome!", x+68+right, x-2+up, 0x007F0E);
	    	fontRenderer.drawString("Please enter your PIN,", x+32+right, x+8+up, 0x007F0E);
	    	fontRenderer.drawString("followed by 'Confirm'", x+37+right, x+18+up, 0x007F0E);
    		if (enteredPin.length() == 0) {
    			fontRenderer.drawString(underScore, x+78+right, y+48+up, 0x007F0E);
    		} else if (enteredPin.length() == 1) {
    			fontRenderer.drawString("*" + underScore, 78, 48, 0x007F0E);
    		} else if (enteredPin.length() == 2) {
    			fontRenderer.drawString("**" + underScore, 78, 48, 0x007F0E);
    		} else if (enteredPin.length() == 3) {
    			fontRenderer.drawString("***" + underScore, 78, 48, 0x007F0E);
    		} else if (enteredPin.length() >= 4) {
    			fontRenderer.drawString("****", 78, 48, 0x007F0E);
    		}
	    	if (pinAttempt.equals("4")) {
		    	fontRenderer.drawString("Attempt 3 of 3.", 52, 68, 0x007F0E);
	    		fontRenderer.drawString("Card declined!", 53, 78, 0x7F0000);
	    	} else {
	    		fontRenderer.drawString("Attempt " + pinAttempt + " of 3.", x+52, x+68, 0x007F0E);
	    	}
    	}
    	if (guiStage.equals("2")) {
        	fontRenderer.drawString("ATM", -21, -30, 0x404040);
        	fontRenderer.drawString("Withdraw", 12, -3, 0x007F0E);
        	fontRenderer.drawString("Balance", 126, -3, 0x007F0E);
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
    		fontRenderer.drawString("$" + bal, 26, 18, 0x007F0E);
        	fontRenderer.drawString("Back", 12, 78, 0x007F0E);
    	}
    	if (guiStage.equals("5")) {
        	fontRenderer.drawString("ATM", -21, -30, 0x404040);
    		fontRenderer.drawString("Withdrawl Successful!", 35, 8, 0x007F0E);
    		fontRenderer.drawString("You have withdrawn", 39, 18, 0x007F0E);
    		fontRenderer.drawString("$" + econ.formatBalance(withdrawAmount) + " " + CityConfig.currencyLargePlural, 50, 28, 0x007F0E);
    		fontRenderer.drawString("Press Confirm to continue.", 22, 58, 0x007F0E);

    	}
    	if (guiStage.equals("6")) {
        	fontRenderer.drawString("ATM", -21, -30, 0x404040);
    		fontRenderer.drawString("Insufficient Funds!", 41, -2, 0x7F0000);
    		fontRenderer.drawString("$" + shortAmt + " more needed!", 43, 8, 0x7F0000);
    		fontRenderer.drawString("Withdraw Less", 97, 24, 0x007F0E);
    		fontRenderer.drawString("Return to Menu", 90, 51, 0x007F0E);
        	fontRenderer.drawString("Eject Card", 109, 78, 0x007F0E);
    	}
    	if (guiStage.equals("7")) {
        	fontRenderer.drawString("ATM", -21, -30, 0x404040);
    		fontRenderer.drawString("Please enter the amount", 26, -12, 0xFFD800);
    		fontRenderer.drawString("you wish to withdraw:", 37, -2, 0xFFD800);
    		fontRenderer.drawString("$" + withdrawCustom, 72, 24, 0x007F0E);
        	fontRenderer.drawString("Back", 12, 78, 0x007F0E);
    	}
    	if (econ.parseInt(guiStage) >= 8) {
        	fontRenderer.drawString("ATM", -21, -30, 0x404040);
    		fontRenderer.drawString("   Invalid Selection    ", 26, -12, 0xFFD800);
        	fontRenderer.drawString("Main Menu", 12, 24, 0x007F0E);
    	}
    }
    
    /*@Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.mc.getTextureManager().bindTexture(texture);
            int x = (width - xSize) / 2;
            int y = (height - ySize) / 2;
            this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }*/
    
    @Override
    public void onGuiClosed() {
    	System.out.println("Closed GUI!");
    }
}