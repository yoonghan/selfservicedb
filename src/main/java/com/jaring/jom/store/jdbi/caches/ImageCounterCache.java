package com.jaring.jom.store.jdbi.caches;

import com.jaring.jom.store.jdbi.caches.impl.AbstractQuerySingleResultCache;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableInteger;
import com.jaring.jom.store.jdbi.impl.JDBISetting;
import com.jaring.jom.store.jdbi.sql.ImageCounterJDBI;

public class ImageCounterCache extends AbstractQuerySingleResultCache<Integer, ImmutableInteger, ImageCounterJDBI>{
	
	ImageCounterCache() {
		super(JDBISetting.IMG_CONNECTION_SERVICE, ImageCounterJDBI.class,"imagecounter");
	}
	
	@Override
	protected Integer getReturnValue(String key, ImageCounterJDBI sqlConnectionObject) {
		Integer value = sqlConnectionObject.select(key);
		return value;
	}

	@Override
	public ImmutableInteger getDefaultValueIfNull() {
		return new ImmutableInteger(-1);
	}

	@Override
	protected ImmutableInteger getImmutableValue(Integer returnValue) {
		return new ImmutableInteger(returnValue);
	}
}
