package com.jaring.jom.store.jdbi.entity;

import com.jaring.jom.store.jdbi.caches.impl.ImmutableMapper;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableEnumRatingBean;

public class EnumRatingBean implements ImmutableMapper<ImmutableEnumRatingBean>{
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

	@Override
	public ImmutableEnumRatingBean mapper() { 
		return new ImmutableEnumRatingBean(enumRatingId, rating);
	}
}
