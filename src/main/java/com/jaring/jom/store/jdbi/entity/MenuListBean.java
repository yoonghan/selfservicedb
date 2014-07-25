package com.jaring.jom.store.jdbi.entity;

public class MenuListBean implements Cloneable{
	
	private Short menuId;
	private MenuBean menu;
	private Short level;
	private Short levelOrder;
	
	public Short getMenuId() {
		return menuId;
	}
	
	public void setMenuId(Short menuId) {
		this.menuId = menuId;
	}
	
	public Short getLevel() {
		return level;
	}
	
	public void setLevel(Short level) {
		this.level = level;
	}
	
	public MenuBean getMenu() {
		return menu;
	}
	
	public void setMenu(MenuBean menu) {
		this.menu = menu;
	}
	
	public Short getLevelOrder() {
		return levelOrder;
	}
	
	public void setLevelOrder(Short levelOrder) {
		this.levelOrder = levelOrder;
	}
	
	public MenuListBean clone(){
		MenuListBean mlb = new MenuListBean();
		mlb.setLevel(level);
		mlb.setLevelOrder(levelOrder);
		mlb.setMenu(menu);
		mlb.setMenuId(menuId);
		return mlb;
	}
}