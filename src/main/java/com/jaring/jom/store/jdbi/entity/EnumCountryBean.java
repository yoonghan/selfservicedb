package com.jaring.jom.store.jdbi.entity;

import com.jaring.jom.store.jdbi.caches.impl.ImmutableMapper;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableEnumCountryBean;

public class EnumCountryBean implements ImmutableMapper<ImmutableEnumCountryBean>{
	private String enumCountryId;
	private String country;
	private String state;
	
	public String getEnumCountryId() {
		return enumCountryId;
	}
	
	public void setEnumCountryId(String enumCountryId) {
		this.enumCountryId = enumCountryId;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}

	@Override
	public ImmutableEnumCountryBean mapper() { 
		return new ImmutableEnumCountryBean(enumCountryId, country, state);
	}
}
