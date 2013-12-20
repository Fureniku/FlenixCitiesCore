package co.uk.silvania.cities.api;

import net.minecraft.block.BlockStationary;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;

public class StillLiquid extends BlockStationary implements IFluidBlock {
	
	public StillLiquid(int id) {
		super(id, Material.water);
		this.setHardness(100.0F);
		this.setLightOpacity(0);
		this.setTickRandomly(true);
	}
	
	@Override
	public int getRenderType() {
		return 4;
	}

    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        entity.motionX *= 0.8D;
    	entity.motionY *= 0.8D;
        entity.motionZ *= 0.8D;
    }

	@Override
	public Fluid getFluid() {
		return null;
	}

	@Override
	public FluidStack drain(World world, int x, int y, int z, boolean doDrain) {
		return null;
	}

	@Override
	public boolean canDrain(World world, int x, int y, int z) {
		return false;
	}
}