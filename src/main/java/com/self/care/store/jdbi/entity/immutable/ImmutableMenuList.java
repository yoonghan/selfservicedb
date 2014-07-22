package com.self.care.store.jdbi.entity.immutable;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.self.care.store.jdbi.caches.impl.Immutable;
import com.self.care.store.jdbi.entity.MenuListBean;

public class ImmutableMenuList implements Immutable<List<MenuListBean>> {

	public final ImmutableList<ImmutableMenuListBean> listArray;
	
	public ImmutableMenuList(){
		this.listArray = ImmutableList.copyOf(new ArrayList<ImmutableMenuListBean>(0));
	}
	
	public ImmutableMenuList(List<MenuListBean> menuListBean){
		
		List<ImmutableMenuListBean> tmpList = new ArrayList<ImmutableMenuListBean>(menuListBean.size());
		for(MenuListBean menuBean: menuListBean){
			tmpList.add(
					new ImmutableMenuListBean(
						menuBean.getMenuId(),
						menuBean.getMenu(),
						menuBean.getLevel(),
						menuBean.getLevelOrder()
					));
		}
		
		ImmutableList<ImmutableMenuListBean> immutableArray = ImmutableList.copyOf(tmpList);
		
		this.listArray = immutableArray;
	}
	
	public ImmutableList<ImmutableMenuListBean> getArrayObject(){
		return this.listArray;
	}
	
	public List<MenuListBean> clone(){
		List<MenuListBean> menuBeanList = new ArrayList<MenuListBean>(listArray.size());
		
		for(ImmutableMenuListBean immImg: listArray){
			menuBeanList.add(immImg.clone());
		}
		return menuBeanList;
	}
}
