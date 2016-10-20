package com.silvaniastudios.cities.econ;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import com.silvaniastudios.cities.core.CityConfig;

import myessentials.economy.api.IEconManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class MEC_EconomyHandler implements IEconManager {
	
	private EconUtils econ = new EconUtils();
	private String uuid;
	public EntityPlayer player;
	
	public MEC_EconomyHandler(UUID uuid) {
		this.setPlayer(uuid);
		System.out.println("UUID in constructor: " + uuid);
		this.uuid = uuid.toString();
	}
	
	public MEC_EconomyHandler() {}
	
	@Override
	public void setPlayer(UUID uuid) {
		ArrayList<EntityPlayer> players = (ArrayList<EntityPlayer>) MinecraftServer.getServer().getConfigurationManager().playerEntityList;
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getUniqueID().equals(uuid)) {
				System.out.println("Player found!");
				player = players.get(i);
			}
		}
		System.out.println("Player may or may not have been set.");
	}

	@Override
	public void addToWallet(int amountToAdd) {
		econ.depositToAccount(player, amountToAdd);
		//econ.depositToAccountViaUUID(uuid, amountToAdd);
	}

	@Override
	public int getWallet() {
		System.out.println("Getting wallet for UUID: " + uuid + " / Player: " + player.getDisplayName());
		double bal = econ.getBalance(player);
		System.out.println("Balance got! It's " + bal);
		return (int) bal;// econ.getBalanceViaUUID(uuid);
	}

	@Override
	public boolean removeFromWallet(int amountToSubtract) {
		return econ.chargeBalance(player, amountToSubtract);
		//return econ.chargeAccountViaUUID(uuid, amountToSubtract);
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
