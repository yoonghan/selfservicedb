package com.jaring.jom.store.jdbi.entitymapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.jaring.jom.store.jdbi.entity.EnumRatingBean;

public class EnumRatingMapper implements ResultSetMapper<EnumRatingBean>{

	@Override
	public EnumRatingBean map(int index, ResultSet rs, StatementContext ctx)
			throws SQLException {
		EnumRatingBean erb = new EnumRatingBean();
		erb.setRating(rs.getInt("rating"));
		erb.setEnumRatingId(rs.getInt("enumRatingId"));
		return erb;
	}

}
