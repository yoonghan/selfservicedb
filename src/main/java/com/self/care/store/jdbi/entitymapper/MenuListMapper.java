package com.self.care.store.jdbi.entitymapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.self.care.store.jdbi.caches.MenuCache;
import com.self.care.store.jdbi.entity.MenuListBean;
import com.self.service.logging.log.LogUtil;

public class MenuListMapper implements ResultSetMapper<MenuListBean> {

	private final String CLASS_NAME = this.getClass().getName();
	
	@Override
	public MenuListBean map(int index, ResultSet rs, StatementContext ctx)
			throws SQLException {

		MenuListBean mlb = new MenuListBean();
		
		Short menuId = rs.getShort("menuId");
		
		try {
			mlb.setMenu(MenuCache.getInstance().getValue(""+menuId));
		} catch (ExecutionException e) {
			LogUtil.getInstance(CLASS_NAME).error("Invalid menuItem"+menuId,e);
		}
		
		mlb.setMenuId(menuId);
		mlb.setLevelOrder(rs.getShort("levelOrder"));
		mlb.setLevel(rs.getShort("level"));
		
		return mlb;
	}
}