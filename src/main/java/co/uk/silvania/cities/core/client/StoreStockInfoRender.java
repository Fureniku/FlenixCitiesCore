package co.uk.silvania.cities.core.client;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.cities.econ.store.entity.AdminShopBlock;
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
		MovingObjectPosition mop = world.rayTraceBlocks(vec3, lookVec);
		
		FontRenderer font = mc.fontRenderer;
		
		if (mop != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
			int x = mop.blockX;
			int y = mop.blockY;
			int z = mop.blockZ;
			Block block = world.getBlock(x, y, z);
			System.out.println("Hit block! X: " + x + ", Y: " + y + ", Z: " + z + ", Block: " + block);
			
			if (block instanceof IStoreBlock) {
				System.out.println("Is a store!");
				
				TileEntity te = world.getTileEntity(x, y, z);
				
				if (te instanceof TileEntityAdminShop) {
					TileEntityAdminShop adminShop = (TileEntityAdminShop) te;
					 
					ItemStack item = adminShop.getStackInSlot(0);
					
					if (item != null) {
						GL11.glPushMatrix();
						GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
						GL11.glDisable(GL11.GL_LIGHTING);
						GL11.glScalef(0.5F, 0.5F, 0.5F);
						font.drawString(item.getDisplayName(), 10, 10, 0xFFFFFF);
						GL11.glPopMatrix();
					}					
				}
			}
		}
	}
	
	public MovingObjectPosition rayTrace(EntityLivingBase entity, double par1, float par3) {
		Vec3 vec3 = entity.getPosition(par3);
		if (entity.getEyeHeight() != 0.12F) { 
			vec3.yCoord += entity.getEyeHeight();
		}
		
		Vec3 vec31 = entity.getLook(par3);
		Vec3 vec32 = vec3.addVector(vec31.xCoord * par1, vec31.yCoord * par1, vec31.zCoord * par1);
		
		return entity.worldObj.rayTraceBlocks(vec3, vec32, false);
	}
	
	
	
	
	/*protected void renderToolTip(ItemStack item, int x, int z) {
        List list = item.getTooltip(this.mc.thePlayer, this.mc.gameSettings.advancedItemTooltips);

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

        FontRenderer font = item.getItem().getFontRenderer(item);
        drawHoveringText(list, p_146285_2_, p_146285_3_, (font == null ? fontRendererObj : font));
    }
	
	protected void drawHoveringText(List p_146283_1_, int p_146283_2_, int p_146283_3_, FontRenderer font)
    {
        if (!p_146283_1_.isEmpty())
        {
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            RenderHelper.disableStandardItemLighting();
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            int k = 0;
            Iterator iterator = p_146283_1_.iterator();

            while (iterator.hasNext())
            {
                String s = (String)iterator.next();
                int l = font.getStringWidth(s);

                if (l > k)
                {
                    k = l;
                }
            }

            int j2 = p_146283_2_ + 12;
            int k2 = p_146283_3_ - 12;
            int i1 = 8;

            if (p_146283_1_.size() > 1)
            {
                i1 += 2 + (p_146283_1_.size() - 1) * 10;
            }

            if (j2 + k > this.width)
            {
                j2 -= 28 + k;
            }

            if (k2 + i1 + 6 > this.height)
            {
                k2 = this.height - i1 - 6;
            }

            this.zLevel = 300.0F;
            itemRender.zLevel = 300.0F;
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

            for (int i2 = 0; i2 < p_146283_1_.size(); ++i2)
            {
                String s1 = (String)p_146283_1_.get(i2);
                font.drawStringWithShadow(s1, j2, k2, -1);

                if (i2 == 0)
                {
                    k2 += 2;
                }

                k2 += 10;
            }

            this.zLevel = 0.0F;
            itemRender.zLevel = 0.0F;
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            RenderHelper.enableStandardItemLighting();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        }
    }*/
}
