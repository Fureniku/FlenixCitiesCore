package co.uk.silvania.cities.core.npc;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderBanker extends RenderLiving {
    private static final ResourceLocation bankerTexture = new ResourceLocation("flenixcities", "textures/entities/npcbanker.png");

    public RenderBanker(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    protected ResourceLocation getBankerTexture(EntityBanker entity) {
        return bankerTexture;
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return this.getBankerTexture((EntityBanker)entity);
	}
}