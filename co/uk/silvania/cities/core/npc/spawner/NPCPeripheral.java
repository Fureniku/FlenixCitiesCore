package co.uk.silvania.cities.core.npc.spawner;

import co.uk.silvania.cities.core.FlenixCities_Core;
import co.uk.silvania.cities.core.npc.EntityBanker;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class NPCPeripheral extends Item {
	
	public NPCPeripheral(int id) {
		super(id);
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
	public void registerIcons(IconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}

}
