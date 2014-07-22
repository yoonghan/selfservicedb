package com.self.care.store.jdbi.caches;

import com.self.care.store.jdbi.caches.impl.AbstractQuerySingleResultCache;
import com.self.care.store.jdbi.entity.EnumRatingBean;
import com.self.care.store.jdbi.entity.immutable.ImmutableEnumRatingBean;
import com.self.care.store.jdbi.impl.JDBISetting;
import com.self.care.store.jdbi.sql.EnumRatingJDBI;

public class EnumRatingCache extends AbstractQuerySingleResultCache<EnumRatingBean, ImmutableEnumRatingBean, EnumRatingJDBI> {

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
	public ImmutableEnumRatingBean getDefaultValueIfNull() {
		return new ImmutableEnumRatingBean();
	}

	@Override
	protected ImmutableEnumRatingBean getImmutableValue(
			EnumRatingBean returnValue) {
		return new ImmutableEnumRatingBean(
				returnValue.getEnumRatingId(),
				returnValue.getRating());
	}
}
