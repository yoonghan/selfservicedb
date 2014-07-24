package com.jaring.jom.store.jdbi.caches;

import com.jaring.jom.logging.impl.Log;
import com.jaring.jom.logging.log.LogFactory;
import com.jaring.jom.store.jdbi.caches.impl.AbstractQuerySingleResultCache;
import com.jaring.jom.store.jdbi.entity.UserBean;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableUserBean;
import com.jaring.jom.store.jdbi.impl.JDBISetting;
import com.jaring.jom.store.jdbi.sql.UserJDBI;

public class UserCache extends AbstractQuerySingleResultCache<UserBean, ImmutableUserBean, UserJDBI>{
	
	private final Log log = LogFactory.getLogger("com.self.care.store.jdbi.caches.UserCache");
	
	UserCache() {
		super(JDBISetting.IMG_CONNECTION_SERVICE, UserJDBI.class,"user");
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
