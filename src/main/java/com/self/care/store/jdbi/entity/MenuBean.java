package com.self.care.store.jdbi.entity;

import java.sql.Date;

public class MenuBean{
	private Short menuId;
	private String toolTip;
	private String textDisplay;
	private String imageURI;
	private String linkURI;
	private Integer enumTypeId;
	private Date cDate;
	private Date mDate;
	
	public Short getMenuId() {
		return menuId;
	}
	
	public void setMenuId(Short menuId) {
		this.menuId = menuId;
	}
	
	public String getToolTip() {
		return toolTip;
	}
	
	public void setToolTip(String toolTip) {
		this.toolTip = toolTip;
	}

	public String getTextDisplay() {
		return textDisplay;
	}

	public void setTextDisplay(String textDisplay) {
		this.textDisplay = textDisplay;
	}

	public String getImageURI() {
		return imageURI;
	}

	public void setImageURI(String imageURI) {
		this.imageURI = imageURI;
	}

	public String getLinkURI() {
		return linkURI;
	}

	public void setLinkURI(String linkURI) {
		this.linkURI = linkURI;
	}

	public Integer getEnumTypeId() {
		return enumTypeId;
	}

	public void setEnumTypeId(Integer enumTypeId) {
		this.enumTypeId = enumTypeId;
	}

	public Date getcDate() {
		return cDate;
	}

	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}

	public Date getmDate() {
		return mDate;
	}

	public void setmDate(Date mDate) {
		this.mDate = mDate;
	}
}
