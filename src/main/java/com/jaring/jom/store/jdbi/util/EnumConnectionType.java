package com.jaring.jom.store.jdbi.util;

enum EnumConnectionType {
	CONNECTION_QUERY(1),
	DATASOURCE(2);
	
	final int type;
	
	EnumConnectionType(int type){
		this.type = type;
	}
	
	public int value() {
        return type;
    }
	
    public static EnumConnectionType valueOf(int value)
            throws IllegalArgumentException {
        for (EnumConnectionType item : values()) {
            if (item.value() == value) {
                return item;
            }
        }
        throw new IllegalArgumentException("No enum const EnumConnectionType with value " + value);
    }
}
