package com.self.care.store.jdbi.caches;

import java.util.List;

import com.self.care.store.jdbi.caches.impl.AbstractQueryMultiResultCache;
import com.self.care.store.jdbi.entity.immutable.ImmutableShort;
import com.self.care.store.jdbi.impl.JDBISetting;
import com.self.care.store.jdbi.sql.TagJDBI;

public class TagCache extends AbstractQueryMultiResultCache<Short, ImmutableShort, String, TagJDBI> {

	static final class Singleton {
		public static final TagCache instance = new TagCache();
	}

	public static TagCache getInstance() {
		return Singleton.instance;
	}

	private TagCache() {
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
