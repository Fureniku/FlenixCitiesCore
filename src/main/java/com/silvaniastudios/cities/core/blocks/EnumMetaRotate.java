package com.silvaniastudios.cities.core.blocks;

import net.minecraft.util.IStringSerializable;

public enum EnumMetaRotate implements IStringSerializable {
	id0(0, "0"),
	id1(1, "1"),
	id2(2, "2"),
	id3(3, "3");
	
	private static final EnumMetaRotate[] META_LOOKUP = new EnumMetaRotate[values().length];
	private final int meta;
	private final String name;
	
	private EnumMetaRotate(int meta, String name) {
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
	
	public static EnumMetaRotate byMetadata(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }
        
        return META_LOOKUP[meta];
    }
	
	static {
        for (EnumMetaRotate type: values()) {
            META_LOOKUP[type.getMetadata()] = type;
        }
    }
}
