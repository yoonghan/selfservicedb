package com.jaring.jom.store.jdbi.entity.immutable;


import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.jaring.jom.store.jdbi.caches.impl.Immutable;

public class ImmutableCustomList<T extends Immutable<?>> implements Immutable<List<T>>{

	private final ImmutableList<T> list;
	
	public ImmutableCustomList(){
		this.list = ImmutableList.copyOf(new ArrayList<T>(0));
	}
	
	public ImmutableCustomList(ImmutableList<T> list){
		this.list = list;
	}
	
	public ImmutableList<T> getList() {
		return list;
	}
	
	public List<T> clone(){
		//will not clone.
		return null;
	}
}
