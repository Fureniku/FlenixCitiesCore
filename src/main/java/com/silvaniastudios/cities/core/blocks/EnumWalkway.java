package com.silvaniastudios.cities.core.blocks;

import net.minecraft.util.IStringSerializable;

public enum EnumWalkway implements IStringSerializable {
	STANDARD(0, "standard"),
	SNOW(1, "snow"),
	LEAF(2, "leaf"),
	LIGHT(3, "light");
	
	private static final EnumWalkway[] META_LOOKUP = new EnumWalkway[values().length];
	private final int meta;
	private final String name;
	
	private EnumWalkway(int meta, String name) {
		this.meta = meta;
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	public int getMetadata() {
        return this.meta;
    }
	
	public static EnumWalkway byMetadata(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }
        
        return META_LOOKUP[meta];
    }
	
	static {
        for (EnumWalkway type: values()) {
            META_LOOKUP[type.getMetadata()] = type;
        }
    }
}
