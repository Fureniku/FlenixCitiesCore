package co.uk.silvania.cities.core.blocks.atm;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.cities.ClientPacketHandler;
import co.uk.silvania.cities.core.CityConfig;
import co.uk.silvania.cities.core.CoreItems;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;



public class GuiATM extends GuiContainer {

    private static final ResourceLocation texture = new ResourceLocation("flenixcities", "textures/gui/atm.png");
    String atmBal = ClientPacketHandler.atmBalance();

    public GuiATM (InventoryPlayer inventoryPlayer, TileEntityATMEntity tileEntity, World world, int x, int y, int z) {
    	super(new ContainerATM(inventoryPlayer, tileEntity));
    }
    
    protected int xSize = 232;
    protected int ySize = 242;
    
    String enteredPin = "";
    String pinAttempt = "1";
    String guiStage = "1";
    String balance = "0";
    String withdrawAmount = "";
    
    //GUI Stage changes the current active screen. It enables different features on the buttons, and changes the text.
    //Stage 1 asks for pin and waits for it to be entered.
    //Stage 2 is an options menu, asking for your next move.
    //Stage 3 is the Withdraw screen, the buttons will give money and remove from your NBT
    //Stage 4 is the Balance screen, and shows your balance.
    //Stage 5 is the withdraw confirm screen.
    //Stage 6 is the insufficient funds error message.
    
    @Override
    public void initGui() {
    	super.initGui();
    	buttonList.add(new ATMButton(1, guiLeft + 21, guiTop + 109, 24, 15, "7"));
    	buttonList.add(new ATMButton(2, guiLeft + 53, guiTop + 109, 24, 15, "8"));
    	buttonList.add(new ATMButton(3, guiLeft + 85, guiTop + 109, 24, 15, "9"));
    	buttonList.add(new RedButton(4, guiLeft + 121, guiTop + 109, 24, 15, ""));
    	buttonList.add(new ATMButton(5, guiLeft + 21, guiTop + 133, 24, 15, "4"));
    	buttonList.add(new ATMButton(6, guiLeft + 53, guiTop + 133, 24, 15, "5"));
    	buttonList.add(new ATMButton(7, guiLeft + 85, guiTop + 133, 24, 15, "6"));
    	buttonList.add(new YellowButton(8, guiLeft + 121, guiTop + 133, 24, 15, ""));
    	buttonList.add(new ATMButton(9, guiLeft + 21, guiTop + 157, 24, 15, "1"));
    	buttonList.add(new ATMButton(10, guiLeft + 53, guiTop + 157, 24, 15, "2"));
    	buttonList.add(new ATMButton(11, guiLeft + 85, guiTop + 157, 24, 15, "3"));
    	buttonList.add(new GreenButton(12, guiLeft + 121, guiTop + 157, 24, 15, ""));
    	buttonList.add(new ATMButton(13, guiLeft + 53, guiTop + 181, 24, 15, "0"));
    	
    	buttonList.add(new ATMButtonLeft(14, guiLeft - 21, guiTop - 7, 24, 15, ""));
    	buttonList.add(new ATMButtonLeft(16, guiLeft - 21, guiTop + 20, 24, 15, ""));
    	buttonList.add(new ATMButtonLeft(18, guiLeft - 21, guiTop + 47, 24, 15, ""));
    	buttonList.add(new ATMButtonLeft(20, guiLeft - 21, guiTop + 74, 24, 15, ""));
    	
    	buttonList.add(new ATMButtonRight(15, guiLeft + 173, guiTop - 7, 24, 15, ""));
    	buttonList.add(new ATMButtonRight(17, guiLeft + 173, guiTop + 20, 24, 15, ""));
    	buttonList.add(new ATMButtonRight(19, guiLeft + 173, guiTop + 47, 24, 15, ""));
    	buttonList.add(new ATMButtonRight(21, guiLeft + 173, guiTop + 74, 24, 15, ""));
    }
    
    public void actionPerformed(GuiButton guibutton) {
    	if (guiStage.equals("1")) {
    		switch(guibutton.id) {
	    	case 1:
	    		enteredPin = enteredPin + "7";
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("You pressed 7!");
	    		}
	    		this.isPinLongEnough(enteredPin);
	    		break;
			case 2:
				enteredPin = enteredPin + "8";
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("You pressed 8!");
	    		}
	    		this.isPinLongEnough(enteredPin);
				break;
	    	case 3:
	    		enteredPin = enteredPin + "9";
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("You pressed 9!");
	    		}
	    		this.isPinLongEnough(enteredPin);
	    		break;
	    	case 4:
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("You pressed Cancel :(");
	    		}
	    		break;
	    	case 5:
	    		enteredPin = enteredPin + "4";
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("You pressed 4!");
	    		}
	    		this.isPinLongEnough(enteredPin);
	    		break;
	    	case 6:
	    		enteredPin = enteredPin + "5";
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("You pressed 5!");
	    		}
	    		this.isPinLongEnough(enteredPin);
	    		break;
	    	case 7:
	    		enteredPin = enteredPin + "6";
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("You pressed 6!");
	    		}
	    		this.isPinLongEnough(enteredPin);
	    		break;
	    	case 8:
	    		enteredPin = "";
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("You pressed clear!");
	    		}
	    		break;
	    	case 9:
	    		enteredPin = enteredPin + "1";
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("You pressed 1!");
	    		}
	    		this.isPinLongEnough(enteredPin);
	    		break;
	    	case 10:
	    		enteredPin = enteredPin + "2";
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("You pressed 2!");
	    		}
	    		this.isPinLongEnough(enteredPin);
	    		break;
	    	case 11:
	    		enteredPin = enteredPin + "3";
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("You pressed 3!");
	    		}
	    		this.isPinLongEnough(enteredPin);
	    		break;
	    	case 12:
	    		enteredPin = enteredPin + "c";
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("You pressed confirm!");
	    		}
	    		this.isPinLongEnough(enteredPin);
	    		break;
	    	case 13:
	    		enteredPin = enteredPin + "0";
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("You pressed 0!");
	    		}
	    		this.isPinLongEnough(enteredPin);
	    		break;
	    	}
    	}
    	if (guiStage.equals("2")) {
    		switch(guibutton.id) {
	    	case 4:
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("You pressed Cancel :(");
	    		}
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
	    	case 21:
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("Eject Card");
	    		}
	    		guiStage = "1";
	    	}
    	}
    	if (guiStage.equals("3")) {
            ByteArrayOutputStream bt = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(bt);
            
    		switch(guibutton.id) {
	    	case 4:
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("You pressed Cancel :(");
	    		}
	    		guiStage = "1";
	    		break;
    		case 16:
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("Withdraw 10");
	    		}
	    		withdrawAmount = "10";
	    		guiStage = "5";
	    		
	            try {
	            	out.writeUTF("withdraw" + withdrawAmount);
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
	    	case 17:
	    		System.out.println("Withdraw 20");
	    		withdrawAmount = "20";
	    		guiStage = "5";
	    		
	            try {
	            	out.writeUTF("withdraw" + withdrawAmount);
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
	    		System.out.println("Withdraw 50");
	    		withdrawAmount = "50";
	    		guiStage = "5";
	    		
	            try {
	            	out.writeUTF("withdraw" + withdrawAmount);
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
	    		System.out.println("Withdraw 100");
	    		withdrawAmount = "100";
	    		guiStage = "5";
	    		
	            try {
	            	out.writeUTF("withdraw" + withdrawAmount);
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
	    	case 20:
	    		System.out.println("Withdraw 250");
	    		withdrawAmount = "250";
	    		guiStage = "5";
	    		
	            try {
	            	out.writeUTF("withdraw" + withdrawAmount);
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
	    	case 21:
	    		System.out.println("Withdraw 500");
	    		withdrawAmount = "500";
	    		guiStage = "5";
	    		
	            try {
	            	out.writeUTF("withdraw" + withdrawAmount);
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
	    	}
    	}
    	if (guiStage.equals("4")) {
    		switch(guibutton.id){
	    	case 4:
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("You pressed Cancel :(");
	    		}
	    		guiStage = "1";
    		}
    	}
    	if (guiStage.equals("5")) {
    		switch(guibutton.id) {
    		case 4:
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("You pressed Cancel :(");
	    		}
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
    	if (guiStage.equals("6")) {
    		switch(guibutton.id) {
    		case 4:
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("You pressed Cancel :(");
	    		}
    			guiStage = "1";
    			break;
    		case 19:
	    		if (CityConfig.debugMode == true) {
	    			System.out.println("Withdraw Different Amount");
	    		}
    			guiStage = "3";
    			break;
    		case 20:
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
    }
    
    private void isPinLongEnough (String pinCode) {
    	if (pinCode.length() == 5)
    		this.authenticatePin (pinCode);
    }
    
    private void authenticatePin (String pinCode) {
    	if (pinCode.equals("1234c")) {
    		if (CityConfig.debugMode == true) {
    			System.out.println("Correct Pin!");
    		}
    		guiStage = "2";
    		enteredPin = "";
    		pinAttempt = "1";
    	} else if (pinAttempt.equals("1")) {
    		if (CityConfig.debugMode == true) {
    			System.out.println("Your pin is incorrect! (Try 1 of 3)");
    		}
    		enteredPin = "";
    		pinAttempt = "2";
    	} else if (pinAttempt.equals("2")) {
    		if (CityConfig.debugMode == true) {
    			System.out.println("Your pin is incorrect! (Try 2 of 3)");
    		}
    		enteredPin = "";
    		pinAttempt = "3";
    	} else if (pinAttempt.equals("3")) {
    		if (CityConfig.debugMode == true) {
    			System.out.println("Your pin is incorrect! (Try 3 of 3 - Keeping card.)");
    		}
    		enteredPin = "";
    		pinAttempt = "4";
    		
            /*ByteArrayOutputStream bt = new ByteArrayOutputStream();
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
            }*/
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
	    	if (pinAttempt.equals("1")) {
		    	fontRenderer.drawString(enteredPin, 74, 48, 0x007F0E);
		    	fontRenderer.drawString("Attempt " + pinAttempt + " of 3.", 52, 68, 0x007F0E);
	    	}
	    	if (pinAttempt.equals("2")) {
		    	fontRenderer.drawString(enteredPin, 74, 48, 0x007F0E);
		    	fontRenderer.drawString("Attempt " + pinAttempt + " of 3.", 52, 68, 0xFFD800);
	    	}
	    	if (pinAttempt.equals("3")) {
		    	fontRenderer.drawString(enteredPin, 74, 48, 0x007F0E);
		    	fontRenderer.drawString("Attempt " + pinAttempt + " of 3.", 52, 68, 0xFF6A00);
	    	}
	    	if (pinAttempt.equals("4")) {
		    	fontRenderer.drawString("Attempt 3 of 3.", 52, 68, 0x007F0E);
	    		fontRenderer.drawString("Card declined!", 53, 78, 0x7F0000);
	    	}
    	}
    	if (guiStage.equals("2")) {
        	fontRenderer.drawString("ATM", -21, -30, 0x404040);
        	fontRenderer.drawString("Withdraw", 12, -3, 0x007F0E);
        	fontRenderer.drawString("Balance", 121, -3, 0x007F0E);
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
        	fontRenderer.drawString("500", 147, 78, 0x007F0E);
    	}
    	if (guiStage.equals("4")) {
        	fontRenderer.drawString("ATM", -21, -30, 0x404040);
    		fontRenderer.drawString("Your current balance is:", 26, 8, 0x007F0E);
    		fontRenderer.drawString(atmBal, 26, 18, 0x007F0E);
    	}
    	if (guiStage.equals("5")) {
        	fontRenderer.drawString("ATM", -21, -30, 0x404040);
    		fontRenderer.drawString("Withdrawl Successful!", 35, 8, 0x007F0E);
    		fontRenderer.drawString("You have withdrawn", 39, 18, 0x007F0E);
    		fontRenderer.drawString(withdrawAmount + " " + CityConfig.currencyLargePlural, 62, 28, 0x007F0E);
    		fontRenderer.drawString("Press Confirm to continue.", 22, 58, 0x007F0E);

    	}
    	if (guiStage.equals("6")) {
        	fontRenderer.drawString("ATM", -21, -30, 0x404040);
    		fontRenderer.drawString("Insufficient Funds!", 26, 8, 0x7F0000);
    		fontRenderer.drawString("Withdraw Less", 105, 24, 0x007F0E);
    		fontRenderer.drawString("Return to Menu", 93, 51, 0x007F0E);
        	fontRenderer.drawString("Eject Card", 109, 78, 0x007F0E);
    	}
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.mc.func_110434_K().func_110577_a(texture);
            int x = (width - xSize) / 2;
            int y = (height - ySize) / 2;
            this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}