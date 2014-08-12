package co.uk.silvania.cities.core.npc.spawner;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import co.uk.silvania.cities.core.FlenixCities_Core;
import co.uk.silvania.cities.core.npc.EntityBanker;

public class BankerSpawnerItem extends NPCPeripheral {

	public BankerSpawnerItem() {
		super();
		this.setCreativeTab(FlenixCities_Core.tabEcon);
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		EntityBanker entity = new EntityBanker(world);
		if (!world.isRemote) {
			entity.posX = x;
			entity.posY = y;
			entity.posZ = z;
			entity.setLocationAndAngles((double)x + 0.5, (double)y + 1, (double)z + 0.5, 0.0F, 0.0F);
			entity.onSpawnWithEgg(null);
			world.spawnEntityInWorld(entity);
			if (!player.capabilities.isCreativeMode) {
				item.stackSize--;
			}
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean canWear() {
		return false;
	}
	
	@Override
	public String npcName() {
		return "Banker";
	}
}
