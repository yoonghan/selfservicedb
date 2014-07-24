package com.jaring.jom.store.jdbi.entity.immutable;

import com.jaring.jom.store.jdbi.caches.impl.Immutable;

public class ImmutableString implements Immutable<String> {
	
	private final String value;
	
	public ImmutableString(String value){
		this.value = value;
	}
	
	public String getString(){
		return value;
	}
	
	public String clone(){
		return value;
	}
}
