package co.uk.silvania.cities.core.npc;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderBanker extends RenderLiving
{
    private static final ResourceLocation field_110833_a = new ResourceLocation("flenixcities", "textures/entities/npcbanker.png");

    public RenderBanker(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2);
    }

    protected ResourceLocation func_110832_a(EntityBanker entity)
    {
        return field_110833_a;
    }

    protected ResourceLocation func_110775_a(Entity entity)
    {
        return this.func_110832_a((EntityBanker)entity);
    }
}