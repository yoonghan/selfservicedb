package com.self.care.store.jdbi.entity;

import java.sql.Date;

public class ImageBean{
	private String imageId;
	private String name;
	private String location;
	
	private String exposure;
	private String settings;
	private String tools;
	private String metaDate;
	private String description;
	private Date   cTime;
	private String cUser;
	private Date   mTime;
	private String URI;
	
	private Short enumStatusId;
	
	private String userId;
	private String enumCountryId;
	private Integer enumRatingId;
	
	private EnumCountryBean country;
	private EnumRatingBean rating;
	private UserBean user;

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getExposure() {
		return exposure;
	}

	public void setExposure(String exposure) {
		this.exposure = exposure;
	}

	public String getSettings() {
		return settings;
	}

	public void setSettings(String settings) {
		this.settings = settings;
	}

	public String getTools() {
		return tools;
	}

	public void setTools(String tools) {
		this.tools = tools;
	}

	public String getMetaDate() {
		return metaDate;
	}

	public void setMetaDate(String metaDate) {
		this.metaDate = metaDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public String getcUser() {
		return cUser;
	}

	public void setcUser(String cUser) {
		this.cUser = cUser;
	}

	public Date getmTime() {
		return mTime;
	}

	public void setmTime(Date mTime) {
		this.mTime = mTime;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public EnumCountryBean getCountry() {
		return country;
	}

	public void setCountry(EnumCountryBean country) {
		this.country = country;
	}

	public EnumRatingBean getRating() {
		return rating;
	}

	public void setRating(EnumRatingBean rating) {
		this.rating = rating;
	}

	public String getURI() {
		return URI;
	}

	public void setURI(String uRI) {
		URI = uRI;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Short getEnumStatusId() {
		return enumStatusId;
	}

	public void setEnumStatusId(Short enumStatusId) {
		this.enumStatusId = enumStatusId;
	}

	public String getEnumCountryId() {
		return enumCountryId;
	}

	public void setEnumCountryId(String enumCountryId) {
		this.enumCountryId = enumCountryId;
	}

	public Integer getEnumRatingId() {
		return enumRatingId;
	}

	public void setEnumRatingId(Integer enumRatingId) {
		this.enumRatingId = enumRatingId;
	}
	
	
}
