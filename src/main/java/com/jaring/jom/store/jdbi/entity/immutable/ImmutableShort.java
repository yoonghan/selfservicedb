package com.jaring.jom.store.jdbi.entity.immutable;

import com.jaring.jom.store.jdbi.caches.impl.Immutable;

public class ImmutableShort implements Immutable<Short>{
	
	private final Short value;
	
	public ImmutableShort(Short value){
		this.value=value;
	}
	
	public Short getShort(){
		return value;
	}
	
	public Short clone(){
		return value;
	}
}
