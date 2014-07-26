package com.jaring.jom.store.jdbi.caches.impl;

public interface ImmutableMapper<T extends Immutable<?>> {
	public T mapper();
}
