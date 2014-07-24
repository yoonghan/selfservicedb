package com.jaring.jom.store.jdbi.entity;

public class EnumRatingBean implements Cloneable{
	private Integer enumRatingId;
	private Integer rating;
	
	public Integer getRating() {
		return rating;
	}
	
	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Integer getEnumRatingId() {
		return enumRatingId;
	}

	public void setEnumRatingId(Integer enumRatingId) {
		this.enumRatingId = enumRatingId;
	}
	
	public EnumRatingBean clone(){
		EnumRatingBean enumRatingBean = new EnumRatingBean();
		enumRatingBean.setRating(rating);
		enumRatingBean.setEnumRatingId(enumRatingId);
		return enumRatingBean;
	}
}
