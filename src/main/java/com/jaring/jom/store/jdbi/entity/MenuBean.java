package com.jaring.jom.store.jdbi.entity;

import com.jaring.jom.store.jdbi.caches.impl.ImmutableMapper;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableMenuBean;

public class MenuBean implements ImmutableMapper<ImmutableMenuBean>{
	private Short menuId;
	private String toolTip;
	private String textDisplay;
	private String imageURI;
	private String linkURI;
	private Integer enumTypeId;
	
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
	
	public MenuBean clone(){
		MenuBean menuBean = new MenuBean();
		menuBean.setEnumTypeId(enumTypeId);
		menuBean.setImageURI(imageURI);
		menuBean.setLinkURI(linkURI);
		menuBean.setMenuId(menuId);
		menuBean.setTextDisplay(textDisplay);
		menuBean.setToolTip(toolTip);
		
		return menuBean;
	}

	@Override
	public ImmutableMenuBean mapper() {
		return new ImmutableMenuBean(menuId, toolTip, textDisplay, imageURI, linkURI, enumTypeId);
	}
}
