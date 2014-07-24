package com.jaring.jom.store.jdbi.entitymapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.jaring.jom.logging.impl.Log;
import com.jaring.jom.logging.log.LogFactory;
import com.jaring.jom.store.jdbi.caches.DBCache;
import com.jaring.jom.store.jdbi.entity.MenuListBean;

public class MenuListMapper implements ResultSetMapper<MenuListBean> {

	private final Log log = LogFactory.getLogger(this.getClass().getName());
	
	@Override
	public MenuListBean map(int index, ResultSet rs, StatementContext ctx)
			throws SQLException {

		MenuListBean mlb = new MenuListBean();
		
		Short menuId = rs.getShort("menuId");
		
		try {
			mlb.setMenu(DBCache.INSTANCE.getMenu().getValue(""+menuId).clone());
		} catch (ExecutionException e) {
			log.error("Invalid menuItem"+menuId,e);
		}
		
		mlb.setMenuId(menuId);
		mlb.setLevelOrder(rs.getShort("levelOrder"));
		mlb.setLevel(rs.getShort("level"));
		
		return mlb;
	}
}
