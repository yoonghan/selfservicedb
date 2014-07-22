package com.self.care.store.jdbi.entity;

public class EnumCountryBean implements Cloneable{
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
	
	public EnumCountryBean clone(){
		EnumCountryBean enumCountryBean = new EnumCountryBean();
		enumCountryBean.setState(state);
		enumCountryBean.setCountry(country);
		enumCountryBean.setEnumCountryId(enumCountryId);
		return enumCountryBean;
	}
}
