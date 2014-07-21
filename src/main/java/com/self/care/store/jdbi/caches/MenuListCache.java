package com.self.care.store.jdbi.caches;

import java.util.ArrayList;
import java.util.List;

import com.self.care.store.jdbi.caches.impl.AbstractQuerySingleResultCache;
import com.self.care.store.jdbi.entity.MenuListBean;
import com.self.care.store.jdbi.impl.JDBISetting;
import com.self.care.store.jdbi.sql.MenuListJDBI;
import com.self.service.logging.impl.Log;
import com.self.service.logging.log.LogFactory;

public class MenuListCache extends AbstractQuerySingleResultCache<List<MenuListBean>, MenuListJDBI> {

	private final Log log = LogFactory.getLogger(this.getClass().getName());
	
	static final class Singleton{
		public static final MenuListCache instance = new MenuListCache();
	}

	public static MenuListCache getInstance(){
		return Singleton.instance;
	}
	
	private MenuListCache() {
		super(JDBISetting.IMG_CONNECTION_SERVICE, MenuListJDBI.class,"menuList");
	}

	protected List<MenuListBean> getReturnValue(String menuGroupId, MenuListJDBI sqlConnectionObject) {
		Integer intMenuGroupId = 0;
		try{
			intMenuGroupId = Integer.parseInt(menuGroupId,10);
		}catch(Exception e){
			log.error("Invalid menuGroupId:"+menuGroupId);
		}
		
		List<MenuListBean> menuListBean = sqlConnectionObject.select(intMenuGroupId);
		return menuListBean;
	}

	@Override
	protected List<MenuListBean> getDefaultValueIfNull() {
		return new ArrayList<MenuListBean>(0);
	}

	@Override
	protected List<MenuListBean> cloneCopy(List<MenuListBean> toCloneValue) {
		return toCloneValue;
	}

}
