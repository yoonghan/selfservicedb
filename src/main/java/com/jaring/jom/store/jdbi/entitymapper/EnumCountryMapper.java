package com.jaring.jom.store.jdbi.entitymapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.jaring.jom.store.jdbi.entity.EnumCountryBean;

public class EnumCountryMapper implements ResultSetMapper<EnumCountryBean>{
	@Override
	public EnumCountryBean map(int index, ResultSet rs, StatementContext ctx	)
			throws SQLException {
		EnumCountryBean ec = new EnumCountryBean();
		ec.setEnumCountryId(rs.getString("enumCountryId"));
		ec.setCountry(rs.getString("country"));
		ec.setState(rs.getString("state"));
		return ec;
	}
}
