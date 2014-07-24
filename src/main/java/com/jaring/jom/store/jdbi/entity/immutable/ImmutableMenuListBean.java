package com.jaring.jom.store.jdbi.entity.immutable;

import com.jaring.jom.store.jdbi.caches.impl.Immutable;
import com.jaring.jom.store.jdbi.entity.MenuBean;
import com.jaring.jom.store.jdbi.entity.MenuListBean;

public class ImmutableMenuListBean implements Immutable<MenuListBean>{

	private final Short menuId;
	private final ImmutableMenuBean menu;
	private final Short level;
	private final Short levelOrder;
	
	public ImmutableMenuListBean(){
		this.menuId = null;
		this.menu = null;
		this.level = null;
		this.levelOrder = null;
	}
	
	public ImmutableMenuListBean(
			Short menuId,
			MenuBean menu,
			Short level,
			Short levelOrder
			){
		this.menuId = menuId;
		this.menu = new ImmutableMenuBean(
				menu.getMenuId(),
				menu.getToolTip(),
				menu.getTextDisplay(),
				menu.getImageURI(),
				menu.getLinkURI(),
				menu.getEnumTypeId());
		this.level = level;
		this.levelOrder = levelOrder;
	}
	
	public Short getMenuId(){
		return this.menuId;
	}
	
	public ImmutableMenuBean getMenu(){
		return this.menu;
	}
	
	public Short getLevel(){
		return this.level;
	}
	
	public Short getLevelOrder(){
		return this.levelOrder;
	}
	
	public MenuListBean clone(){
		MenuListBean mlb = new MenuListBean();
		mlb.setLevel(level);
		mlb.setLevelOrder(levelOrder);
		mlb.setMenuId(menuId);
		mlb.setMenu(menu.clone());
		
		return mlb;
	}
}
