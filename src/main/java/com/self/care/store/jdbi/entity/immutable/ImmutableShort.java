package com.self.care.store.jdbi.entity.immutable;

import com.self.care.store.jdbi.caches.impl.Immutable;

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
