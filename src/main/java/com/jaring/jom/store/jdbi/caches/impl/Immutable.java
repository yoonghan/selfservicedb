package com.jaring.jom.store.jdbi.caches.impl;

public interface Immutable<T>{
	//place holder to inform that this class is not modifiable.
	public T clone();
}
