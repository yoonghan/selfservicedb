package com.self.care.store.jdbi.caches;

import com.self.care.store.jdbi.caches.impl.AbstractQuerySingleResultCache;
import com.self.care.store.jdbi.entity.MenuBean;
import com.self.care.store.jdbi.entity.immutable.ImmutableMenuBean;
import com.self.care.store.jdbi.impl.JDBISetting;
import com.self.care.store.jdbi.sql.MenuJDBI;
import com.self.service.logging.impl.Log;
import com.self.service.logging.log.LogFactory;

public class MenuCache extends AbstractQuerySingleResultCache<MenuBean, ImmutableMenuBean, MenuJDBI> {

	private final Log log = LogFactory.getLogger(this.getClass().getName());
	
	static final class Singleton{
		public static final MenuCache instance = new MenuCache();
	}

	public static MenuCache getInstance(){
		return Singleton.instance;
	}
	
	private MenuCache() {
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
