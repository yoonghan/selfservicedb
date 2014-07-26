package com.jaring.jom.store.jdbi.caches;

import com.jaring.jom.store.jdbi.caches.impl.AbstractQuerySingleResultCache;
import com.jaring.jom.store.jdbi.entity.EnumRatingBean;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableEnumRatingBean;
import com.jaring.jom.store.jdbi.impl.JDBISetting;
import com.jaring.jom.store.jdbi.sql.EnumRatingJDBI;

public class EnumRatingCache extends AbstractQuerySingleResultCache<ImmutableEnumRatingBean, EnumRatingJDBI> {
	
	EnumRatingCache() {
		super(JDBISetting.IMG_CONNECTION_SERVICE, EnumRatingJDBI.class, "enumrating");
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

}
