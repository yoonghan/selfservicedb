package com.self.care.store.jdbi.sql;

import java.sql.Date;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.self.care.store.jdbi.entity.UserBean;
import com.self.care.store.jdbi.entitymapper.UserMapper;
import com.self.care.store.jdbi.impl.BasicJDBICommand;

@RegisterMapper(UserMapper.class)
public interface UserJDBI extends BasicJDBICommand<UserBean>{
	
	@SqlUpdate("insert into user (userId, identity, cDate, mDate) values (:userId, :identity, :cDate, :mDate)")
	void insert(@Bind("userId") String userId, @Bind("identity") String identity, @Bind("cDate") Date cDate, @Bind("mDate") Date mDate);

	@SqlQuery("select identity,userId from user where userId = :userId")
	UserBean select(@Bind("userId") String userId);
	
}
