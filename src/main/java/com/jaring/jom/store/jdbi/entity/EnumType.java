package com.jaring.jom.store.jdbi.entity;

public enum EnumType {
	IMAGE(1),
	TEXT(2),
	INTERNAL_TEXT_LINK(3),
	EXTERNAL_TEXT_LINK(4),
	IMAGE_INTERNAL_LINK(5),
	IMAGE_EXTERNAL_LINK(6);
	
	final Integer typeId;
	
	EnumType(Integer typeId){
		this.typeId = typeId;
	}
	
	public int value() {
        return typeId;
    }
	
    public static EnumType valueOf(Integer value)
            throws IllegalArgumentException {
        for (EnumType item : values()) {
            if (item.value() == value) {
                return item;
            }
        }
        throw new IllegalArgumentException("No enum const EnumType with value " + value);
    }
}
