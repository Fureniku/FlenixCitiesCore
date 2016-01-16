package co.uk.silvania.cities.econ;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import co.uk.silvania.cities.core.CityConfig;
import co.uk.silvania.cities.econ.EconUtils;
import myessentials.economy.IEconManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class MEC_EconomyHandler implements IEconManager {
	
	private EconUtils econ;
	private String uuid;
	
	public MEC_EconomyHandler(UUID uuid) {
		this.setPlayer(uuid);
		System.out.println("UUID in constructor: " + uuid);
		this.uuid = uuid.toString();
	}
	
	public MEC_EconomyHandler() {}
	@Override public void setPlayer(UUID uuid) {}

	@Override
	public void addToWallet(int amountToAdd) {
		econ.depositToAccountViaUUID(uuid, amountToAdd);
	}

	@Override
	public int getWallet() {
		return (int) Math.floor(econ.getBalanceViaUUID(uuid));
	}

	@Override
	public boolean removeFromWallet(int amountToSubtract) {
		return econ.chargeAccountViaUUID(uuid, amountToSubtract);
	}

	@Override
	public void setWallet(int setAmount, EntityPlayer player) {
		//Intentionally blank. I don't want other mods overriding player balances, add and subtract are enough.
	}

	@Override
	public String currency(int amount) {
		if (amount < 2) {
			return CityConfig.currencyLarge;
		}
		return CityConfig.currencyLargePlural;
	}

	@Override
	public String getMoneyString() {
		return currency(getWallet());
	}

	@Override
	public void save() {}

	@Override
	public Map<String, Integer> getItemTables() {
		return null;
	}
}
