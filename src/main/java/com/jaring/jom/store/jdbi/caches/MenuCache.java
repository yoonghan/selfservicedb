package com.jaring.jom.store.jdbi.caches;

import com.jaring.jom.logging.impl.Log;
import com.jaring.jom.logging.log.LogFactory;
import com.jaring.jom.store.jdbi.caches.impl.AbstractQuerySingleResultCache;
import com.jaring.jom.store.jdbi.entity.MenuBean;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableMenuBean;
import com.jaring.jom.store.jdbi.impl.JDBISetting;
import com.jaring.jom.store.jdbi.sql.MenuJDBI;

public class MenuCache extends AbstractQuerySingleResultCache<MenuBean, ImmutableMenuBean, MenuJDBI> {

	private final Log log = LogFactory.getLogger(this.getClass().getName());
	
	MenuCache() {
		super(JDBISetting.IMG_CONNECTION_SERVICE, MenuJDBI.class,"menu");
	}

	@Override
	protected MenuBean getReturnValue(String menuId, MenuJDBI sqlConnectionObject) {
		Integer intMenuId = 0;
		try{
			intMenuId = Integer.parseInt(menuId,10);
		}catch(Exception e){
			log.error("Invalid menuId:"+menuId);
		}
		
		MenuBean menuBean = sqlConnectionObject.select(intMenuId);
		return menuBean;
	}

	@Override
	protected ImmutableMenuBean getDefaultValueIfNull() {
		return new ImmutableMenuBean();
	}

	@Override
	protected ImmutableMenuBean getImmutableValue(MenuBean returnValue) {
		
		return 	new ImmutableMenuBean(returnValue.getMenuId(),
				returnValue.getToolTip(),
				returnValue.getTextDisplay(),
				returnValue.getImageURI(),
				returnValue.getLinkURI(),
				returnValue.getEnumTypeId());
	}

}
