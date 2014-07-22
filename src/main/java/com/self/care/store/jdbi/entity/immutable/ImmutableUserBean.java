package com.self.care.store.jdbi.entity.immutable;

import com.self.care.store.jdbi.caches.impl.Immutable;
import com.self.care.store.jdbi.entity.UserBean;

public class ImmutableUserBean implements Immutable<UserBean> {
	private final Long       userId;
	private final String     identity;
	private final Integer    typeMap;
	private final Short      status;
	private final String     email;
	private final String     name;
	private final String     googleAuthId;
	private final String     facebookAuthId;
	
	public ImmutableUserBean(){
		this.userId = null;
		this.identity = null;
		this.typeMap = null;
		this.status = null;
		this.email = null;
		this.name = null;
		this.googleAuthId = null;
		this.facebookAuthId = null;
	}

	public ImmutableUserBean(Long userId, 
			String identity,
			Integer typeMap,
			Short status,
			String email,
			String name,
			String googleAuthId,
			String facebookAuthId){
		this.userId = userId;
		this.identity = identity;
		this.typeMap = typeMap;
		this.status = status;
		this.email = email;
		this.name = name;
		this.googleAuthId = googleAuthId;
		this.facebookAuthId = facebookAuthId;
	}
	
	public Long getUserId() {
		return userId;
	}

	public String getGoogleAuthId() {
		return googleAuthId;
	}

	public String getFacebookAuthId() {
		return facebookAuthId;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public Short getStatus() {
		return status;
	}

	public Integer getTypeMap() {
		return typeMap;
	}

	public String getIdentity() {
		return identity;
	}
	
	public UserBean clone(){
		UserBean userBean = new UserBean();
		userBean.setUserId(userId);
		userBean.setIdentity(identity);
		userBean.setTypeMap(typeMap);
		userBean.setStatus(status);
		userBean.setEmail(email);
		userBean.setName(name);
		userBean.setGoogleAuthId(googleAuthId);
		userBean.setFacebookAuthId(facebookAuthId);
		userBean.setEmail(email);
		return userBean.clone();
	}
}
