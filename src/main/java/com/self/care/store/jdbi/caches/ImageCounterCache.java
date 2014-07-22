package com.self.care.store.jdbi.caches;

import com.self.care.store.jdbi.caches.impl.AbstractQuerySingleResultCache;
import com.self.care.store.jdbi.entity.immutable.ImmutableInteger;
import com.self.care.store.jdbi.impl.JDBISetting;
import com.self.care.store.jdbi.sql.ImageCounterJDBI;

public class ImageCounterCache extends AbstractQuerySingleResultCache<Integer, ImmutableInteger, ImageCounterJDBI>{

	static final class Singleton{
		public static final ImageCounterCache instance = new ImageCounterCache();
	}

	public static ImageCounterCache getInstance(){
		return Singleton.instance;
	}
	
	private ImageCounterCache() {
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
