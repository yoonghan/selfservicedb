package com.jaring.jom.store.jdbi.caches;

import java.util.List;

import com.jaring.jom.store.jdbi.caches.impl.AbstractQueryMultiResultCache;
import com.jaring.jom.store.jdbi.entity.CategoryBean;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableString;
import com.jaring.jom.store.jdbi.impl.JDBISetting;
import com.jaring.jom.store.jdbi.sql.CategoryJDBI;


public class CategoryCache extends AbstractQueryMultiResultCache<String, ImmutableString, CategoryBean, CategoryJDBI> {
	
	CategoryCache() {
		super(JDBISetting.IMG_CONNECTION_SERVICE, CategoryJDBI.class, "category");
	}

	@Override
	protected String getReturnValue(String key, CategoryJDBI sqlConnectionObject) {
		String value = sqlConnectionObject.select(key);
		return value;
	}

	@Override
	protected ImmutableString getImmutableValue(String returnValue) {
		return new ImmutableString(returnValue);
	}

	@Override
	protected ImmutableString getDefaultValueIfNull() {
		return new ImmutableString("");
	}

	@Override
	protected List<CategoryBean> getReturnAllValue(CategoryJDBI sqlConnection) {
		return sqlConnection.findAll();
	}

}