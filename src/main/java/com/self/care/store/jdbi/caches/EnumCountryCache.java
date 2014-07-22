package com.self.care.store.jdbi.caches;

import com.self.care.store.jdbi.caches.impl.AbstractQuerySingleResultCache;
import com.self.care.store.jdbi.entity.EnumCountryBean;
import com.self.care.store.jdbi.entity.immutable.ImmutableEnumCountryBean;
import com.self.care.store.jdbi.impl.JDBISetting;
import com.self.care.store.jdbi.sql.EnumCountryJDBI;

public class EnumCountryCache extends AbstractQuerySingleResultCache<EnumCountryBean, ImmutableEnumCountryBean, EnumCountryJDBI> {

	static final class Singleton{
		public static final EnumCountryCache instance = new EnumCountryCache();
	}
	
	private EnumCountryCache() {
		super(JDBISetting.IMG_CONNECTION_SERVICE, EnumCountryJDBI.class, "enumcountry");
	}

	public static EnumCountryCache getInstance(){
		return Singleton.instance;
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

	@Override
	protected ImmutableEnumCountryBean getImmutableValue(
			EnumCountryBean returnValue) {
		return new ImmutableEnumCountryBean(
				returnValue.getEnumCountryId(),
				returnValue.getCountry(),
				returnValue.getState());
	}
}
