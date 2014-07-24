package com.jaring.jom.store.jdbi.entity.immutable;

import com.jaring.jom.store.jdbi.caches.impl.Immutable;
import com.jaring.jom.store.jdbi.entity.EnumRatingBean;

public class ImmutableEnumRatingBean implements Immutable<EnumRatingBean> {
	
	private final Integer enumRatingId;
	private final Integer rating;
	

	public ImmutableEnumRatingBean(){
		this.enumRatingId = null;
		this.rating = null;
	}
	
	public ImmutableEnumRatingBean(Integer enumRatingId,
			 Integer rating){
		this.enumRatingId = enumRatingId;
		this.rating = rating;
	}
	
	public Integer getRating() {
		return this.rating;
	}

	public Integer getEnumRatingId() {
		return this.enumRatingId;
	}

	public EnumRatingBean clone(){
		EnumRatingBean enumRatingBean = new EnumRatingBean();
		enumRatingBean.setEnumRatingId(enumRatingId);
		enumRatingBean.setRating(enumRatingId);
		return enumRatingBean;
	}
}
