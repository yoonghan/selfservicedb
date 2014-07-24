package com.jaring.jom.store.jdbi.caches;

import java.util.List;

import com.jaring.jom.store.jdbi.caches.impl.AbstractQueryMultiResultCache;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableShort;
import com.jaring.jom.store.jdbi.impl.JDBISetting;
import com.jaring.jom.store.jdbi.sql.TagJDBI;

public class TagCache extends AbstractQueryMultiResultCache<Short, ImmutableShort, String, TagJDBI> {

	TagCache() {
		super(JDBISetting.IMG_CONNECTION_SERVICE, TagJDBI.class, "tag");
	}

	@Override
	protected Short getReturnValue(String key, TagJDBI sqlConnectionObject) {
		Short value = sqlConnectionObject.select(key);
		return value;
	}

	@Override
	public ImmutableShort getDefaultValueIfNull() {
		return new ImmutableShort((short) -1);
	}

	@Override
	protected List<String> getReturnAllValue(TagJDBI sqlConnectionObject) {
		return sqlConnectionObject.findAll();
	}

	@Override
	protected ImmutableShort getImmutableValue(Short returnValue) {
		return new ImmutableShort(returnValue);
	}

}
