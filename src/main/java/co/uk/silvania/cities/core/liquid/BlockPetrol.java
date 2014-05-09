package co.uk.silvania.cities.core.liquid;

import co.uk.silvania.cities.core.FlenixCities_Core;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPetrol extends BlockFluidClassic {

	public BlockPetrol(int id, Fluid fluid) {
		super(id, fluid, Material.water);
		this.setDensity(750);
		this.setTemperature(300);
	}
	
	@SideOnly(Side.CLIENT)
	protected Icon stillIcon;
	@SideOnly(Side.CLIENT)
	protected Icon flowingIcon;
	
	@Override
	public Icon getIcon(int side, int meta) {
		return (side == 0 || side == 1) ? stillIcon : flowingIcon;
	}
		
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister icon) {
		stillIcon = icon.registerIcon(FlenixCities_Core.modid + ":PetrolStill");
		flowingIcon = icon.registerIcon(FlenixCities_Core.modid + ":PetrolFlowing");
	}
	
    @Override
    public boolean isFlammable(IBlockAccess world, int x, int y, int z, int meta, ForgeDirection face) {
    	return true;
    }
    
    @Override
    public int getFlammability(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face) {
    	return 300;
    }
    
    @Override
    public int getFireSpreadSpeed(World world, int x, int y, int z, int metadata, ForgeDirection face) {
    	return 250000000;
    }
	
	@Override
	public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
		if (world.getBlockMaterial(x, y, z).isLiquid()) return false;
		return super.canDisplace(world, x, y, z);
	}
	
    @Override
    public boolean displaceIfPossible(World world, int x, int y, int z) {
            if (world.getBlockMaterial(x,  y,  z).isLiquid()) return false;
            return super.displaceIfPossible(world, x, y, z);
    }
	

}
