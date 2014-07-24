package com.jaring.jom.store.jdbi.entity.immutable;

import com.jaring.jom.store.jdbi.caches.impl.Immutable;
import com.jaring.jom.store.jdbi.entity.EnumCountryBean;

public class ImmutableEnumCountryBean implements Immutable<EnumCountryBean> {
	private final String enumCountryId;
	private final String country;
	private final String state;
	
	public ImmutableEnumCountryBean(){
		this.enumCountryId = null;
		this.country = null;
		this.state = null;
	}
	
	public ImmutableEnumCountryBean(String enumCountryId,
			String country,
			String state){
		this.enumCountryId = enumCountryId;
		this.country = country;
		this.state = state;
	}
	
	public String getEnumCountryId() {
		return this.enumCountryId;
	}

	public String getCountry() {
		return this.country;
	}

	public String getState() {
		return this.state;
	}
	public EnumCountryBean clone(){
		EnumCountryBean enumCountryBean = new EnumCountryBean();
		enumCountryBean.setCountry(country);
		enumCountryBean.setEnumCountryId(enumCountryId);
		enumCountryBean.setState(state);
		return enumCountryBean;
	}
}
