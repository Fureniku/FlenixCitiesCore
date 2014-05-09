package co.uk.silvania.cities.core;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EventListener {
	
	@SideOnly(Side.CLIENT)
    @ForgeSubscribe
	public void postStitch(TextureStitchEvent.Post event) {
    	System.out.println("Registering FlenixCities Fluid Textures for tank usage");
    	CoreBlocks.fluidOil.setIcons(CoreBlocks.blockOil.getBlockTextureFromSide(0), CoreBlocks.blockOil.getBlockTextureFromSide(1));
    	CoreBlocks.fluidPetrol.setIcons(CoreBlocks.blockPetrol.getBlockTextureFromSide(0), CoreBlocks.blockPetrol.getBlockTextureFromSide(1));
    	CoreBlocks.fluidDiesel.setIcons(CoreBlocks.blockDiesel.getBlockTextureFromSide(0), CoreBlocks.blockDiesel.getBlockTextureFromSide(1));
    	CoreBlocks.fluidRedDiesel.setIcons(CoreBlocks.blockRedDiesel.getBlockTextureFromSide(0), CoreBlocks.blockRedDiesel.getBlockTextureFromSide(1));
    }
}
