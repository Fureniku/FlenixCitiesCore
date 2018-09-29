package com.silvaniastudios.cities.econ.money;

import com.silvaniastudios.cities.core.FlenixCities;
import com.silvaniastudios.cities.core.items.CitiesItemBase;

public class ItemMoney extends CitiesItemBase {
	
    public int moneyValue;

    public ItemMoney(int money, String name) {
        super(name, 50);
        this.moneyValue = money;
        this.setCreativeTab(FlenixCities.tabEcon);
    }

    public int getMoneyValue() {
    	return moneyValue;
    }

}