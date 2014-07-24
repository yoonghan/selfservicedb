package com.jaring.jom.store.jdbi.entitymapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.jaring.jom.store.jdbi.entity.CategoryBean;

public class CategoryMapper implements ResultSetMapper<CategoryBean>{

	@Override
	public CategoryBean map(int index, ResultSet rs, StatementContext ctx	)
			throws SQLException {
		CategoryBean cb = new CategoryBean();
		cb.setCategoryId(rs.getString("categoryId"));
		cb.setCounter(rs.getInt("counter"));
		cb.setDescription(rs.getString("description"));
		cb.setName(rs.getString("name"));
		cb.setEnumStatusId(rs.getShort("enumStatusId"));
		return cb;
	}
	
}
