package com.jaring.jom.store.jdbi.entity;

import java.sql.Timestamp;

import com.jaring.jom.store.jdbi.caches.impl.ImmutableMapper;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableUserBean;

public class UserBean implements ImmutableMapper<ImmutableUserBean>, Cloneable{
	private Long       userId;
	private String     identity;
	private Integer    typeMap;
	private Short      status;
	private String     email;
	private String     name;
	
	private String     googleEmail;
	private String     googleLink;
	private String     googleAuthId;
	private String     googlePicture;
	
	private String     facebookEmail;
	private String     facebookLink;
	private String     facebookAuthId;
	private String     facebookGender;
	
	private Integer    lastLoginTypeMap;
	private Timestamp  lastLoginTimeStamp;
	private Timestamp  lastModifiedTimeStamp;
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getIdentity() {
		return identity;
	}
	
	public void setIdentity(String identity) {
		if(identity!=null && identity.length() > 200)
			identity = identity.substring(0, 200);
		this.identity = identity;
	}

	public Integer getTypeMap() {
		return typeMap;
	}

	public void setTypeMap(Integer typeMap) {
		this.typeMap = typeMap;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGoogleLink() {
		return googleLink;
	}

	public void setGoogleLink(String googleLink) {
		this.googleLink = googleLink;
	}

	public String getGoogleAuthId() {
		return googleAuthId;
	}

	public void setGoogleAuthId(String googleAuthId) {
		this.googleAuthId = googleAuthId;
	}

	public String getGooglePicture() {
		return googlePicture;
	}

	public void setGooglePicture(String googlePicture) {
		this.googlePicture = googlePicture;
	}

	public String getGoogleEmail() {
		return googleEmail;
	}

	public void setGoogleEmail(String googleEmail) {
		this.googleEmail = googleEmail;
	}

	public Timestamp getLastLoginTimeStamp() {
		return lastLoginTimeStamp;
	}

	public void setLastLoginTimeStamp(Timestamp lastLoginTimeStamp) {
		this.lastLoginTimeStamp = lastLoginTimeStamp;
	}

	public Timestamp getLastModifiedTimeStamp() {
		return lastModifiedTimeStamp;
	}

	public void setLastModifiedTimeStamp(Timestamp lastModifiedTimeStamp) {
		this.lastModifiedTimeStamp = lastModifiedTimeStamp;
	}

	public Integer getLastLoginTypeMap() {
		return lastLoginTypeMap;
	}

	public void setLastLoginTypeMap(Integer lastLoginTypeMap) {
		this.lastLoginTypeMap = lastLoginTypeMap;
	}

	public String getFacebookEmail() {
		return facebookEmail;
	}

	public void setFacebookEmail(String facebookEmail) {
		this.facebookEmail = facebookEmail;
	}

	public String getFacebookLink() {
		return facebookLink;
	}

	public void setFacebookLink(String facebookLink) {
		this.facebookLink = facebookLink;
	}

	public String getFacebookAuthId() {
		return facebookAuthId;
	}

	public void setFacebookAuthId(String facebookAuthId) {
		this.facebookAuthId = facebookAuthId;
	}

	public String getFacebookGender() {
		return facebookGender;
	}

	public void setFacebookGender(String facebookGender) {
		this.facebookGender = facebookGender;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}
	
	public UserBean clone(){
		UserBean clonedUserBean = new UserBean();
		clonedUserBean.setUserId(userId);
		clonedUserBean.setIdentity(identity);
		clonedUserBean.setTypeMap(typeMap);
		clonedUserBean.setStatus(status);
		clonedUserBean.setEmail(email);
		clonedUserBean.setName(name);
		
		clonedUserBean.setGoogleAuthId(googleAuthId);
		clonedUserBean.setGoogleEmail(googleEmail);
		clonedUserBean.setGoogleLink(googleLink);
		clonedUserBean.setGooglePicture(googlePicture);
		
		clonedUserBean.setFacebookAuthId(facebookAuthId);
		clonedUserBean.setFacebookEmail(facebookEmail);
		clonedUserBean.setFacebookGender(facebookGender);
		clonedUserBean.setFacebookLink(facebookLink);

		clonedUserBean.setLastLoginTimeStamp(lastLoginTimeStamp);
		clonedUserBean.setLastLoginTypeMap(lastLoginTypeMap);
		clonedUserBean.setLastModifiedTimeStamp(lastModifiedTimeStamp);

		return clonedUserBean;
	}

	@Override
	public ImmutableUserBean mapper() {
		return new ImmutableUserBean(userId, identity, lastLoginTypeMap, status, email, name, googleAuthId, facebookAuthId);
	}
}
