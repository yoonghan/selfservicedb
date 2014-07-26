package com.jaring.jom.store.jdbi.caches;

import com.jaring.jom.store.jdbi.caches.impl.AbstractQuerySingleResultCache;
import com.jaring.jom.store.jdbi.entity.EnumCountryBean;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableEnumCountryBean;
import com.jaring.jom.store.jdbi.impl.JDBISetting;
import com.jaring.jom.store.jdbi.sql.EnumCountryJDBI;

public class EnumCountryCache extends AbstractQuerySingleResultCache<ImmutableEnumCountryBean, EnumCountryJDBI> {

	EnumCountryCache() {
		super(JDBISetting.IMG_CONNECTION_SERVICE, EnumCountryJDBI.class, "enumcountry");
	}
	
	@Override
	protected EnumCountryBean getReturnValue(String key, EnumCountryJDBI sqlConnectionObject) {
		EnumCountryBean value = sqlConnectionObject.select(key);
		return value;
	}

	@Override
	public ImmutableEnumCountryBean getDefaultValueIfNull() {
		return new ImmutableEnumCountryBean();
	}

}
