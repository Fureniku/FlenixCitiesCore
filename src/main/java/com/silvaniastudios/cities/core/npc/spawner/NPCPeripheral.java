package com.silvaniastudios.cities.core.npc.spawner;

import com.silvaniastudios.cities.core.FlenixCities_Core;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class NPCPeripheral extends Item {
	
	public NPCPeripheral() {
		super();
	}

	public String npcName() {
		return "";
	}
	
	public boolean canEquip() {
		return true;
	}
	
	public boolean canWear() {
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}

}
