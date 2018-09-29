/*package com.silvaniastudios.cities.core.client;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.lwjgl.opengl.GL11;

import com.silvaniastudios.cities.econ.EconUtils;
import com.silvaniastudios.cities.econ.store.entity.IStoreBlock;
import com.silvaniastudios.cities.econ.store.entity.StockChestBlock;
import com.silvaniastudios.cities.econ.store.entity.TileEntityAdminShop;
import com.silvaniastudios.cities.econ.store.entity.TileEntityFloatingShelves;
import com.silvaniastudios.cities.econ.store.entity.TileEntityStockChest;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class StoreStockInfoRender extends Gui {

	public Minecraft mc;
	protected FontRenderer fontRendererObj;
	public EconUtils econ = new EconUtils();
	
	public StoreStockInfoRender(Minecraft mc) {
		super();
		this.mc = mc;
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderStock(RenderGameOverlayEvent.Pre event) {
		EntityClientPlayerMP player = mc.player;
		WorldClient world = mc.world;
		
		Vec3d vec3 = player.getPosition(1.0F);
		Vec3d lookVec = player.getLookVec();
		MovingObjectPosition mop = mc.renderViewEntity.rayTrace(7, 1.0F);
		
		ScaledResolution res = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
		
		FontRenderer font = mc.fontRenderer;
		
		if (mop != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
			int x = mop.blockX;
			int y = mop.blockY;
			int z = mop.blockZ;
			Block block = world.getBlock(x, y, z);
			
			if (block instanceof IStoreBlock) {
				TileEntity te = world.getTileEntity(x, y, z);
				
				if (te instanceof TileEntityAdminShop) {
					TileEntityAdminShop adminShop = (TileEntityAdminShop) te;
					
					int lookingAtSlot = quadrantHit(mop.hitVec, x, y, z, mop.sideHit);
					 
					ItemStack item = adminShop.getStackInSlot(lookingAtSlot);
					int meta = world.getBlockMetadata(x, y, z);
					
					NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
					nf.setMinimumFractionDigits(2);
					nf.setMaximumFractionDigits(2);
					nf.setRoundingMode(RoundingMode.HALF_UP);
					
					String buy1 = nf.format(econ.parseDouble("" + adminShop.buyPrice1));
					String buy2 = nf.format(econ.parseDouble("" + adminShop.buyPrice2));
					String buy3 = nf.format(econ.parseDouble("" + adminShop.buyPrice3));
					String buy4 = nf.format(econ.parseDouble("" + adminShop.buyPrice4));
					
					String sell1 = nf.format(econ.parseDouble("" + adminShop.sellPrice1));
					String sell2 = nf.format(econ.parseDouble("" + adminShop.sellPrice2));
					String sell3 = nf.format(econ.parseDouble("" + adminShop.sellPrice3));
					String sell4 = nf.format(econ.parseDouble("" + adminShop.sellPrice4));
					
					String costBuy = "";
					String costSell = "";
					String qty = "";
					
					if (item != null) {
						qty = " (" + item.stackSize + ") ";
					}
					
					if (lookingAtSlot == 0) {
						costBuy = buy1;
						costSell = sell1;
					} else if (lookingAtSlot == 1) {
						costBuy = buy2;
						costSell = sell2;
					} else if (lookingAtSlot == 2) {
						costBuy = buy3;
						costSell = sell3;
					} else if (lookingAtSlot == 3) {
						costBuy = buy4;
						costSell = sell4;
					}
					
					
					if (shopFront(mop.sideHit, meta)) {
						//System.out.println("Side hit: " + mop.sideHit);
						//System.out.println("Hit Vector: " + mop.hitVec);
						
						if (item != null) {
							GL11.glPushMatrix();
							GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
							GL11.glDisable(GL11.GL_LIGHTING);
							GL11.glScalef(0.5F, 0.5F, 0.5F);
							renderToolTip(item, res.getScaledWidth() + 10, res.getScaledHeight() + 10, (qty + ", " + EnumChatFormatting.GREEN + "Buy: " + EnumChatFormatting.GOLD + "$" + costBuy + ", " + EnumChatFormatting.YELLOW + "Sell: " + EnumChatFormatting.GOLD + "$" + costSell), player, font);
							GL11.glPopMatrix();
						}
					}
				} else if (te instanceof TileEntityFloatingShelves) {
					TileEntityFloatingShelves playerShop = (TileEntityFloatingShelves) te;
					
					int lookingAtSlot = quadrantHit(mop.hitVec, x, y, z, mop.sideHit);
					 
					ItemStack item = playerShop.getStackInSlot(lookingAtSlot);
					int meta = world.getBlockMetadata(x, y, z);
					
					NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
					nf.setMinimumFractionDigits(2);
					nf.setMaximumFractionDigits(2);
					nf.setRoundingMode(RoundingMode.HALF_UP);
					
					String buy1 = nf.format(econ.parseDouble("" + playerShop.buyPrice1));
					String buy2 = nf.format(econ.parseDouble("" + playerShop.buyPrice2));
					String buy3 = nf.format(econ.parseDouble("" + playerShop.buyPrice3));
					String buy4 = nf.format(econ.parseDouble("" + playerShop.buyPrice4));
					
					String sell1 = nf.format(econ.parseDouble("" + playerShop.sellPrice1));
					String sell2 = nf.format(econ.parseDouble("" + playerShop.sellPrice2));
					String sell3 = nf.format(econ.parseDouble("" + playerShop.sellPrice3));
					String sell4 = nf.format(econ.parseDouble("" + playerShop.sellPrice4));
					
					String costBuy = "";
					String costSell = "";
					String qty = "";
					
					if (item != null) {
						qty = " (" + item.stackSize + ") ";
					}
					
					if (lookingAtSlot == 0) {
						costBuy = buy1;
						costSell = sell1;
					} else if (lookingAtSlot == 1) {
						costBuy = buy2;
						costSell = sell2;
					} else if (lookingAtSlot == 2) {
						costBuy = buy3;
						costSell = sell3;
					} else if (lookingAtSlot == 3) {
						costBuy = buy4;
						costSell = sell4;
					}
					
					
					if (shopFront(mop.sideHit, meta)) {
						if (item != null) {
							GL11.glPushMatrix();
							GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
							GL11.glDisable(GL11.GL_LIGHTING);
							GL11.glScalef(0.5F, 0.5F, 0.5F);
							renderToolTip(item, res.getScaledWidth() + 10, res.getScaledHeight() + 10, (qty + ", " + EnumChatFormatting.GREEN + "Buy: " + EnumChatFormatting.GOLD + "$" + costBuy + ", " + EnumChatFormatting.YELLOW + "Sell: " + EnumChatFormatting.GOLD + "$" + costSell), player, font);
							GL11.glPopMatrix();
						}
					}
				}
			} else if (block instanceof StockChestBlock) {
				TileEntity te = world.getTileEntity(x, y, z);
				
				if (te instanceof TileEntityStockChest) {
					TileEntityStockChest stockChest = (TileEntityStockChest) te;
					
					ItemStack item = stockChest.getStackInSlot(stockChest.invSize - 1);
					
					if (mop.sideHit == 1) {
						if (item != null) {
							GL11.glPushMatrix();
							GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
							GL11.glDisable(GL11.GL_LIGHTING);
							GL11.glScalef(0.5F, 0.5F, 0.5F);
							renderToolTip(item, res.getScaledWidth() + 10, res.getScaledHeight() + 10, "", player, font);
							GL11.glPopMatrix();
						}
					}
				}
			}
		}
	}
	
	public boolean shopFront(int side, int meta) {
		if (side == 5 && meta == 3) {
			return true;
		}
		if (side == 3 && meta == 0) {
			return true;
		}
		if (side == 4 && meta == 1) {
			return true;
		}
		if (side == 2 && meta == 2) {
			return true;
		}
		return false;
	}
	
	public int quadrantHit(Vec3 vector, int x, int y, int z, int side) {
		double xCoord = vector.xCoord - (int) x;
		double yCoord = vector.yCoord - (int) y;
		double zCoord = vector.zCoord - (int) z;
		
		
		//Side 2: X 0.9 (left) 0.0 (right)		Y 0.9 (top) 63.0 (base)		Z 0.5 (all)
		if (side == 2) { //Player facing SOUTH, shop shelves facing NORTH
			if (xCoord < 0.5) {
				if (yCoord < 0.5) {
					return 3;
				} else {
					return 1;
				}
			} else { 
				if (yCoord < 0.5) {
					return 2;
				} else {
					return 0;
				}
			}
		}
		
		//Side 3: X 0.0 (left) 0.9 (right)		Y 0.9 (top) 0.0 (base)		Z 0.5 (all)
		if (side == 3) { //Player facing NORTH, shop shelves facing SOUTH
			if (xCoord < 0.5) {
				if (yCoord < 0.5) {
					return 2;
				} else {
					return 0;
				}
			} else { 
				if (yCoord < 0.5) {
					return 3;
				} else {
					return 1;
				}
			}
		}
		
		//Side 4: X 0.5 (all)					Y 0.9 (top) 0.0 (base)		Z 0.0 (left) 0.9 (right)
		if (side == 4) { //Player facing EAST, shop shelves facing WEST
			if (zCoord < 0.5) {
				if (yCoord < 0.5) {
					return 2;
				} else {
					return 0;
				}
			} else { 
				if (yCoord < 0.5) {
					return 3;
				} else {
					return 1;
				}
			}
		}
		
		//Side 5: X 0.5 (all)					Y 0.9 (top) 0.0 (base) 		Z 0.9 (left) 0.0 (right))
		if (side == 5) { //Player facing WEST, shop shelves facing EAST.
			if (zCoord < 0.5) {
				if (yCoord < 0.5) {
					return 3;
				} else {
					return 1;
				}
			} else { 
				if (yCoord < 0.5) {
					return 2;
				} else {
					return 0;
				}
			}
		}
		return 0;
		
	}
	
    public void renderToolTip(ItemStack item, int posX, int posZ, String str, EntityClientPlayerMP player, FontRenderer fnt) {
    	int tooltipX = posX;
    	int tooltipY = posZ;
    	
    	List list = item.getTooltip(player, false);
    	
    	for (int k = 0; k < list.size(); ++k)
        {
            if (k == 0)
            {
                list.set(k, item.getRarity().rarityColor + (String)list.get(k));
            }
            else
            {
                list.set(k, EnumChatFormatting.GRAY + (String)list.get(k));
            }
        }

        drawHoveringText(list, posX, posZ, fnt, str);
    }
    
    public static RenderItem itemRender = new RenderItem();
    
    public void drawHoveringText(List list, int x, int z, FontRenderer fnt, String str) {
        if (!list.isEmpty()) {
            int k = 0;
            list.set(0, list.get(0) + str);
            
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                String s = (String)iterator.next();
                int l = fnt.getStringWidth(s);

                if (l > k) {
                    k = l;
                }
            }

            int j2 = x + 12;
            int k2 = z - 12;
            int i1 = 8;

            if (list.size() > 1) {
                i1 += 2 + (list.size() - 1) * 10;
            }

            int width = this.mc.displayWidth;
            int height = this.mc.displayHeight;
            
            if (j2 + k > width) {
                j2 -= 28 + k;
            }

            if (k2 + i1 + 6 > height) {
                k2 = height - i1 - 6;
            }

            int j1 = -267386864;
            this.drawGradientRect(j2 - 3, k2 - 4, j2 + k + 3, k2 - 3, j1, j1);
            this.drawGradientRect(j2 - 3, k2 + i1 + 3, j2 + k + 3, k2 + i1 + 4, j1, j1);
            this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 + i1 + 3, j1, j1);
            this.drawGradientRect(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, j1, j1);
            this.drawGradientRect(j2 + k + 3, k2 - 3, j2 + k + 4, k2 + i1 + 3, j1, j1);
            int k1 = 1347420415;
            int l1 = (k1 & 16711422) >> 1 | k1 & -16777216;
            this.drawGradientRect(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, k1, l1);
            this.drawGradientRect(j2 + k + 2, k2 - 3 + 1, j2 + k + 3, k2 + i1 + 3 - 1, k1, l1);
            this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 - 3 + 1, k1, k1);
            this.drawGradientRect(j2 - 3, k2 + i1 + 2, j2 + k + 3, k2 + i1 + 3, l1, l1);

            for (int i2 = 0; i2 < list.size(); ++i2) {
                String s1 = (String)list.get(i2);
                fnt.drawStringWithShadow(s1, j2, k2, -1);

                if (i2 == 0) {
                    k2 += 2;
                }

                k2 += 10;
            }
        }
    }
}*/
