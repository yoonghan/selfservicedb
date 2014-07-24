package com.jaring.jom.store.jdbi.entity.immutable;

import com.jaring.jom.store.jdbi.caches.impl.Immutable;

public class ImmutableInteger implements Immutable<Integer>{
	
	private final Integer value;
	
	public ImmutableInteger(Integer value){
		this.value=value;
	}
	
	public Integer getInteger(){
		return value;
	}
	
	public Integer clone(){
		return value;
	}
}
