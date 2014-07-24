package com.jaring.jom.store.jdbi.entity.immutable;

import com.jaring.jom.store.jdbi.caches.impl.Immutable;
import com.jaring.jom.store.jdbi.entity.MenuBean;

public class ImmutableMenuBean implements Immutable<MenuBean> {
	private final Short menuId;
	private final String toolTip;
	private final String textDisplay;
	private final String imageURI;
	private final String linkURI;
	private final Integer enumTypeId;
	
	public ImmutableMenuBean(){
		this.menuId = null;
		this.toolTip = null;
		this.textDisplay = null;
		this.imageURI = null;
		this.linkURI = null;
		this.enumTypeId = null;
	}
	
	public ImmutableMenuBean(
			Short menuId,
			String toolTip,
			String textDisplay,
			String imageURI,
			String linkURI,
			Integer enumTypeId){
		this.menuId = menuId;
		this.toolTip = toolTip;
		this.textDisplay = textDisplay;
		this.imageURI = imageURI;
		this.linkURI = linkURI;
		this.enumTypeId = enumTypeId;
	}
	
	public Short getMenuId() {
		return this.menuId;
	}
		
	public String getToolTip() {
		return this.toolTip;
	}

	public String getTextDisplay() {
		return this.textDisplay;
	}

	public String getImageURI() {
		return this.imageURI;
	}

	public String getLinkURI() {
		return this.linkURI;
	}

	public Integer getEnumTypeId() {
		return this.enumTypeId;
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
}
