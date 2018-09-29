package com.silvaniastudios.cities.core.npc;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBanker extends RenderLiving<EntityBanker> {
    private static final ResourceLocation bankerTexture = new ResourceLocation("flenixcities", "textures/entities/npcbanker.png");

    public RenderBanker(ModelBase par1ModelBase, float par2) {
        super(null, par1ModelBase, par2);
    }

    protected ResourceLocation getBankerTexture(EntityBanker entity) {
        return bankerTexture;
    }

	@Override
	protected ResourceLocation getEntityTexture(EntityBanker entity) {
		return bankerTexture;
	}
}