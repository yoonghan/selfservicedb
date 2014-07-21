package com.self.care.store.jdbi.caches;

import com.self.care.store.jdbi.caches.impl.AbstractQuerySingleResultCache;
import com.self.care.store.jdbi.impl.JDBISetting;
import com.self.care.store.jdbi.sql.ImageCounterJDBI;

public class ImageCounterCache extends AbstractQuerySingleResultCache<Integer, ImageCounterJDBI>{

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
	public Integer getDefaultValueIfNull() {
		return new Integer(-1);
	}

	@Override
	protected Integer cloneCopy(Integer toCloneValue) {
		return new Integer(toCloneValue);
	}
}
