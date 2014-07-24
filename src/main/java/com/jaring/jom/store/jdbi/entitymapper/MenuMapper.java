package com.jaring.jom.store.jdbi.entitymapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.jaring.jom.store.jdbi.entity.MenuBean;

public class MenuMapper  implements ResultSetMapper<MenuBean> {

	@Override
	public MenuBean map(int index, ResultSet rs, StatementContext ctx)
			throws SQLException {
		MenuBean mb = new MenuBean();
		
		mb.setMenuId(rs.getShort("menuId"));
		mb.setToolTip(rs.getString("toolTip"));
		mb.setTextDisplay(rs.getString("textDisplay"));
		mb.setImageURI(rs.getString("imageURI"));
		mb.setLinkURI(rs.getString("linkURI"));
		mb.setEnumTypeId(rs.getInt("enumTypeId"));
		
		return mb;
	}

}
