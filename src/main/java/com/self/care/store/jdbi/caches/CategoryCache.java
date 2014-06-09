package com.self.care.store.jdbi.caches;

import java.util.List;

import com.self.care.store.jdbi.caches.impl.AbstractQueryMultiResultCache;
import com.self.care.store.jdbi.entity.CategoryBean;
import com.self.care.store.jdbi.impl.JDBISetting;
import com.self.care.store.jdbi.sql.CategoryJDBI;

public class CategoryCache extends AbstractQueryMultiResultCache<String, CategoryBean, CategoryJDBI> {
	
	static final class Singleton{
		public static final CategoryCache instance = new CategoryCache();
	}
	
	private CategoryCache() {
		super(JDBISetting.IMG_CONNECTION_SERVICE, CategoryJDBI.class, "category");
	}

	public static CategoryCache getInstance(){
		return Singleton.instance;
	}
	
	@Override
	protected String getReturnValue(String key, CategoryJDBI sqlConnectionObject) {
		String value = sqlConnectionObject.select(key);
		return value;
	}

	@Override
	protected String getDefaultValueIfNull() {
		return "";
	}

	@Override
	protected List<CategoryBean> getReturnAllValue(CategoryJDBI sqlConnection) {
		return sqlConnection.findAll();
	}

}
