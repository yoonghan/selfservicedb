package com.jaring.jom.store.jdbi.entitymapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.jaring.jom.store.jdbi.entity.UserBean;

public class UserMapper implements ResultSetMapper<UserBean>{

	@Override
	public UserBean map(int index, ResultSet rs, StatementContext ctx)
			throws SQLException {
		UserBean ub = new UserBean();
		ub.setIdentity(rs.getString("identity"));
		ub.setUserId(rs.getLong("userId"));
		ub.setTypeMap(rs.getInt("typeMap"));
		ub.setEmail(rs.getString("email"));
		ub.setName(rs.getString("name"));
		ub.setStatus(rs.getShort("status"));
		
		try{
			ub.setFacebookAuthId(rs.getString("facebookAuthId"));
		}catch(SQLException sqle){
			//do nothing if this is not selected.
		}
		
		try{
			ub.setGoogleAuthId(rs.getString("googleAuthId"));
		}catch(SQLException sqle){
			//do nothing if this is not selected.
		}
		
		return ub;
	}

}
