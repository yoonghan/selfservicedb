package com.self.care.store.jdbi.caches;

import com.self.care.store.jdbi.caches.impl.AbstractQuerySingleResultCache;
import com.self.care.store.jdbi.entity.EnumRatingBean;
import com.self.care.store.jdbi.impl.JDBISetting;
import com.self.care.store.jdbi.sql.EnumRatingJDBI;

public class EnumRatingCache extends AbstractQuerySingleResultCache<EnumRatingBean, EnumRatingJDBI> {

	static final class Singleton{
		public static final EnumRatingCache instance = new EnumRatingCache();
	}
	
	private EnumRatingCache() {
		super(JDBISetting.IMG_CONNECTION_SERVICE, EnumRatingJDBI.class, "enumrating");
	}

	public static EnumRatingCache getInstance(){
		return Singleton.instance;
	}
	
	@Override
	protected EnumRatingBean getReturnValue(String key, EnumRatingJDBI sqlConnectionObject) {
		EnumRatingBean value = sqlConnectionObject.select(key);
		return value;
	}

	@Override
	public EnumRatingBean getDefaultValueIfNull() {
		return new EnumRatingBean();
	}

	@Override
	protected EnumRatingBean cloneCopy(EnumRatingBean toCloneValue) {
		return toCloneValue;
	}
}
