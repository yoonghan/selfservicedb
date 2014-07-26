package com.jaring.jom.store.jdbi.caches;

import java.util.List;

import com.jaring.jom.logging.impl.Log;
import com.jaring.jom.logging.log.LogFactory;
import com.jaring.jom.store.jdbi.caches.impl.AbstractQuerySingleResultCache;
import com.jaring.jom.store.jdbi.entity.MenuListBean;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableCustomList;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableMenuListBean;
import com.jaring.jom.store.jdbi.impl.JDBISetting;
import com.jaring.jom.store.jdbi.sql.MenuListJDBI;

public class MenuListCache extends AbstractQuerySingleResultCache<ImmutableCustomList<ImmutableMenuListBean>, MenuListJDBI> {

	private final Log log = LogFactory.getLogger(this.getClass().getName());
		
	MenuListCache() {
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
	protected ImmutableCustomList<ImmutableMenuListBean> getDefaultValueIfNull() {
		return new ImmutableCustomList<ImmutableMenuListBean>();
	}

}
