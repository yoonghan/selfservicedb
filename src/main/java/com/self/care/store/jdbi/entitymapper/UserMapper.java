package com.self.care.store.jdbi.entitymapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.self.care.store.jdbi.entity.UserBean;

public class UserMapper implements ResultSetMapper<UserBean>{

	@Override
	public UserBean map(int index, ResultSet rs, StatementContext ctx)
			throws SQLException {
		UserBean ub = new UserBean();
		ub.setIdentity(rs.getString("identity"));
		ub.setUserId(rs.getString("userId"));
		return ub;
	}

}
