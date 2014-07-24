package com.jaring.jom.store.jdbi.dao;

import java.util.concurrent.ExecutionException;

import org.skife.jdbi.v2.DBI;

import com.google.common.base.Optional;
import com.jaring.jom.logging.impl.Log;
import com.jaring.jom.logging.log.LogFactory;
import com.jaring.jom.store.jdbi.caches.DBCache;
import com.jaring.jom.store.jdbi.entity.UserBean;
import com.jaring.jom.store.jdbi.impl.JDBISetting;
import com.jaring.jom.store.jdbi.sql.UserJDBI;
import com.jaring.jom.store.jdbi.util.DataSourceHandler;

public class UserDAO {

	private final Log log = LogFactory.getLogger(getClass().getName());

	public Optional<UserBean> getUserViaGoogle(String userId) {
		UserBean userBean = null;

		DBI dbConn;
		try {
			dbConn = DataSourceHandler.getInstance().getDataSource(
					JDBISetting.IMG_CONNECTION_SERVICE);
			UserJDBI userJDBI = dbConn.open(UserJDBI.class);
			userBean = userJDBI.selectViaGoogleId(userId);
			userJDBI.close();
			
		} catch (ExecutionException e) {
			log.error("Unable to retrieve user info via gmail", e);
		}

		return Optional.fromNullable(userBean);
	}
	
	public Optional<UserBean> getUserViaFacebook(String userId) {
		UserBean userBean = null;

		DBI dbConn;
		try {
			dbConn = DataSourceHandler.getInstance().getDataSource(
					JDBISetting.IMG_CONNECTION_SERVICE);
			UserJDBI userJDBI = dbConn.open(UserJDBI.class);
			userBean = userJDBI.selectViaFacebookId(userId);
			userJDBI.close();
			
		} catch (ExecutionException e) {
			log.error("Unable to retrieve user info via gmail", e);
		}

		return Optional.fromNullable(userBean);
	}

	public long insertUser(UserBean userBean) {
		
		long userId = -1;
		
		try {
			DBI dbConn = DataSourceHandler.getInstance().getDataSource(
					JDBISetting.IMG_CONNECTION_SERVICE);
			UserJDBI userJDBI = dbConn.open(UserJDBI.class);
			
			String facebookId = userBean.getFacebookAuthId();
			String googleId = userBean.getGoogleAuthId();
			
			userId = userJDBI.insert(
					userBean.getIdentity(),
					userBean.getTypeMap(), userBean.getEmail(),
					userBean.getName(), 
					userBean.getGoogleEmail(),userBean.getGoogleLink(),
					googleId, userBean.getGooglePicture(),
					userBean.getFacebookEmail(), userBean.getFacebookLink(),
					facebookId, userBean.getFacebookGender(),
					userBean.getLastLoginTypeMap(),
					userBean.getLastLoginTimeStamp(),
					userBean.getLastModifiedTimeStamp());
			
			DBCache.INSTANCE.getUser().refreshOnIdCache(""+userId);
			
			userJDBI.close();
		} catch (ExecutionException e) {
			log.error("Error in inserting user data:", e);
		}
		
		return userId;
	}

	public long updateOrInsertUser(UserBean userBean) {
		
		long userId = -1;
		
		if(userBean.getUserId() == null){
			//this means user have not been created, execute above.
			userId = insertUser(userBean);
		}else{
			try {
				
				DBI dbConn = DataSourceHandler.getInstance().getDataSource(
						JDBISetting.IMG_CONNECTION_SERVICE);
				UserJDBI userJDBI = dbConn.open(UserJDBI.class);
				
				int updatedRecord = 0;
				
				/**
				 * Check
				 * If facebook user exist, update facebook.
				 * If google   user exist, update google.
				 */
				if(userBean.getGoogleAuthId() != null){
					updatedRecord = userJDBI.updateGoogleAcct(
							userBean.getUserId(),userBean.getTypeMap(), userBean.getEmail(),userBean.getName(),
							userBean.getGoogleEmail(),userBean.getGoogleLink(),
							userBean.getGoogleAuthId(),userBean.getGooglePicture(),
							userBean.getLastLoginTypeMap(),
							userBean.getLastLoginTimeStamp(),
							userBean.getLastModifiedTimeStamp());
				}
				
				if(userBean.getFacebookAuthId() != null){
					
					updatedRecord = userJDBI.updateFacebookAcct(
							userBean.getUserId(),userBean.getTypeMap(), userBean.getEmail(),userBean.getName(),
							userBean.getFacebookEmail(), userBean.getFacebookLink(),
							userBean.getFacebookAuthId(), userBean.getFacebookGender(),
							userBean.getLastLoginTypeMap(),
							userBean.getLastLoginTimeStamp(),
							userBean.getLastModifiedTimeStamp());
				}
				
				userId = userBean.getUserId();
				
				//If no records were updated, means it have to insert.
				if(updatedRecord == 0)
					userId = insertUser(userBean);
				else{
					DBCache.INSTANCE.getUser().refreshOnIdCache(""+userId);
				}
				
				userJDBI.close();
			} catch (ExecutionException e) {
				log.error("Error in inserting user data:", e);
			}
		}
		
		return userId;
	}
	
	/**
	 * Use for test users.
	 * @param userId
	 */
	public int actualUserDelete(Long userId){
		
		int rowsDeleted = 0;
		
		try {
			DBI dbConn = DataSourceHandler.getInstance().getDataSource(
					JDBISetting.IMG_CONNECTION_SERVICE);
			UserJDBI userJDBI = dbConn.open(UserJDBI.class);
			
			rowsDeleted = userJDBI.deleteUser(userId);
			
			DBCache.INSTANCE.getUser().refreshOnIdCache(""+userId);
			
			userJDBI.close();
		} catch (ExecutionException e) {
			log.error("Error in deleting user data:", e);
		}
		
		return rowsDeleted;
	}
	
	/**
	 * Return number of records.
	 */
	public int getCount(){
		
		int rowCount=0;
		
		try {
			DBI dbConn = DataSourceHandler.getInstance().getDataSource(
					JDBISetting.IMG_CONNECTION_SERVICE);
			UserJDBI userJDBI = dbConn.open(UserJDBI.class);
			
			rowCount = userJDBI.countUser();
			
			userJDBI.close();
		} catch (ExecutionException e) {
			log.error("Error in inserting user data:", e);
		}
		
		return rowCount;
	}
}
