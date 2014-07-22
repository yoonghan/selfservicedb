package com.self.care.store.jdbi.entity.immutable;

import com.self.care.store.jdbi.caches.impl.Immutable;

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
