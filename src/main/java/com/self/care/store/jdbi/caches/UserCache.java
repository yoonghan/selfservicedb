package com.self.care.store.jdbi.caches;

import com.self.care.store.jdbi.caches.impl.AbstractQuerySingleResultCache;
import com.self.care.store.jdbi.entity.UserBean;
import com.self.care.store.jdbi.impl.JDBISetting;
import com.self.care.store.jdbi.sql.UserJDBI;

public class UserCache extends AbstractQuerySingleResultCache<UserBean, UserJDBI>{
	
	static final class Singleton{
		public static final UserCache instance = new UserCache();
	}
	
	private UserCache() {
		super(JDBISetting.IMG_CONNECTION_SERVICE, UserJDBI.class,"user");
	}

	public static UserCache getInstance(){
		return Singleton.instance;
	}

	@Override
	protected UserBean getReturnValue(String key, UserJDBI sqlConnectionObject) {
		UserBean value = sqlConnectionObject.select(key);
		return value;
	}
	
	@Override
	public UserBean getDefaultValueIfNull() {
		return new UserBean();
	}
}
