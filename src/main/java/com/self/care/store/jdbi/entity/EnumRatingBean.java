package com.self.care.store.jdbi.entity;

public class EnumRatingBean {
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
}
