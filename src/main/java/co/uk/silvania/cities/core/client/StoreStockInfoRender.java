package co.uk.silvania.cities.core.client;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.cities.econ.EconUtils;
import co.uk.silvania.cities.econ.store.entity.IStoreBlock;
import co.uk.silvania.cities.econ.store.entity.TileEntityAdminShop;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class StoreStockInfoRender extends Gui {

	public Minecraft mc;
	protected FontRenderer fontRendererObj;
	
	public StoreStockInfoRender(Minecraft mc) {
		super();
		this.mc = mc;
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderStock(RenderGameOverlayEvent.Pre event) {
		EntityClientPlayerMP player = mc.thePlayer;
		WorldClient world = mc.theWorld;
		
		Vec3 vec3 = player.getPosition(1.0F);
		Vec3 lookVec = player.getLookVec();
		MovingObjectPosition mop = mc.renderViewEntity.rayTrace(7, 1.0F);//world.rayTraceBlocks(vec3, lookVec);
		
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
					
					String buy1 = nf.format(EconUtils.parseDouble("" + adminShop.buyPrice1));
					String buy2 = nf.format(EconUtils.parseDouble("" + adminShop.buyPrice2));
					String buy3 = nf.format(EconUtils.parseDouble("" + adminShop.buyPrice3));
					String buy4 = nf.format(EconUtils.parseDouble("" + adminShop.buyPrice4));
					
					String cost = "";
					
					if (lookingAtSlot == 0) {
						cost = buy1;
					} else if (lookingAtSlot == 1) {
						cost = buy2;
					} else if (lookingAtSlot == 2) {
						cost = buy3;
					} else if (lookingAtSlot == 3) {
						cost = buy4;
					}
					
					
					if (shopFront(mop.sideHit, meta)) {
						//System.out.println("Side hit: " + mop.sideHit);
						//System.out.println("Hit Vector: " + mop.hitVec);
						System.out.println("Render gets buy price 1 as " + buy1 + " and cost as " + cost);
						
						if (item != null) {
							GL11.glPushMatrix();
							GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
							GL11.glDisable(GL11.GL_LIGHTING);
							GL11.glScalef(0.5F, 0.5F, 0.5F);
							font.drawString(item.getDisplayName() + ", $" + cost, res.getScaledWidth() + 10, res.getScaledHeight() + 10, 0xFFFFFF);
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
		double xCoord = vector.xCoord - Math.abs(x);
		double yCoord = vector.yCoord - Math.abs(y);
		double zCoord = vector.zCoord - Math.abs(z);
		
		//Side 2: X 0.9 (left) 0.0 (right)		Y 0.9 (top) 63.0 (base)		Z 0.5 (all)
		if (side == 2) {
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
		if (side == 3) {
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
		if (side == 4) {
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
		if (side == 5) {
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
}
