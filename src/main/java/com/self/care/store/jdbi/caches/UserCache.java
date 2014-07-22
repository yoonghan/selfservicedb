package com.self.care.store.jdbi.caches;

import com.self.care.store.jdbi.caches.impl.AbstractQuerySingleResultCache;
import com.self.care.store.jdbi.entity.UserBean;
import com.self.care.store.jdbi.entity.immutable.ImmutableUserBean;
import com.self.care.store.jdbi.impl.JDBISetting;
import com.self.care.store.jdbi.sql.UserJDBI;
import com.self.service.logging.impl.Log;
import com.self.service.logging.log.LogFactory;

public class UserCache extends AbstractQuerySingleResultCache<UserBean, ImmutableUserBean, UserJDBI>{
	
	private final Log log = LogFactory.getLogger("com.self.care.store.jdbi.caches.UserCache");
	
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
		UserBean value = null;
		try{
			long keyVal = Long.parseLong(key,10);
			value = sqlConnectionObject.select(keyVal);
		}catch(Exception e){
			log.error("Invalid key for parsing captured:"+key, e);
		}
		
		return value;
	}
	
	@Override
	public ImmutableUserBean getDefaultValueIfNull() {
		return new ImmutableUserBean();
	}

	@Override
	protected ImmutableUserBean getImmutableValue(UserBean returnValue) {
		ImmutableUserBean immutableUserBean = new ImmutableUserBean(returnValue.getUserId(),
				returnValue.getIdentity(),
				returnValue.getTypeMap(),
				returnValue.getStatus(),
				returnValue.getEmail(),
				returnValue.getName(),
				returnValue.getGoogleAuthId(),
				returnValue.getFacebookAuthId());
		return immutableUserBean;
	}
}
