package com.jaring.jom.store.jdbi.caches;
/**
 * Disable caching
 */
//package com.self.care.store.jdbi.caches;
//
//import com.self.care.store.jdbi.caches.impl.AbstractQuerySingleResultCache;
//import com.self.care.store.jdbi.entity.UserBean;
//import com.self.care.store.jdbi.impl.JDBISetting;
//import com.self.care.store.jdbi.sql.UserJDBI;
//
//public class GoogleUserCache extends AbstractQuerySingleResultCache<UserBean, UserJDBI>{
//	
//	static final class Singleton{
//		public static final GoogleUserCache instance = new GoogleUserCache();
//	}
//	
//	private GoogleUserCache() {
//		super(JDBISetting.IMG_CONNECTION_SERVICE, UserJDBI.class,"user");
//	}
//
//	public static GoogleUserCache getInstance(){
//		return Singleton.instance;
//	}
//
//	@Override
//	protected UserBean getReturnValue(String key, UserJDBI sqlConnectionObject) {
//		UserBean value = sqlConnectionObject.selectViaGoogleId(key);
//		return value;
//	}
//	
//	@Override
//	public UserBean getDefaultValueIfNull() {
//		return new UserBean();
//	}
//
//	@Override
//	protected UserBean cloneCopy(UserBean toCloneValue) {
//		return toCloneValue.clone();
//	}
//}
