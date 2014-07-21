package com.self.care.store.jdbi.sql;

import java.sql.Timestamp;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.self.care.store.jdbi.entity.UserBean;
import com.self.care.store.jdbi.entitymapper.UserMapper;
import com.self.care.store.jdbi.impl.BasicJDBICommand;

@RegisterMapper(UserMapper.class)
public interface UserJDBI extends BasicJDBICommand<UserBean>{
	
	@SqlUpdate("insert into user (identity, typeMap, email, name, status,"
			+ "googleEmail, googleLink, googleAuthId, googlePicture, "
			+ "facebookEmail, facebookLink, facebookAuthId, facebookGender, "
			+ "lastLoginTypeMap, lastLoginTimeStamp, lastModifiedTimeStamp) "
			+ "values "
			+ "(:identity, :typeMap, :email, :name, 1, "
			+ ":googleEmail, :googleLink, :googleAuthId, :googlePicture, "
			+ ":facebookEmail, :facebookLink, :facebookAuthId, :facebookGender, "
			+ ":lastLoginTypeMap, :lastLoginTimeStamp, :lastModifiedTimeStamp )")
	@GetGeneratedKeys
	long insert( 
			@Bind("identity") String identity, 
			@Bind("typeMap") Integer typeMap, 
			@Bind("email") String email,
			@Bind("name") String name,
			@Bind("googleEmail") String googleEmail,
			@Bind("googleLink") String googleLink,
			@Bind("googleAuthId") String googleAuthId,
			@Bind("googlePicture") String googlePicture,
			@Bind("facebookEmail") String facebookEmail,
			@Bind("facebookLink") String facebookLink,
			@Bind("facebookAuthId") String facebookAuthId,
			@Bind("facebookGender") String facebookGender,
			@Bind("lastLoginTypeMap") Integer lastLoginTypeMap,
			@Bind("lastLoginTimeStamp") Timestamp lastLoginTimeStamp,
			@Bind("lastModifiedTimeStamp") Timestamp lastModifiedTimeStamp
			);
	
	@SqlUpdate("update user set typeMap=:typeMap, email=:email, name=:name, "
			+ "facebookEmail=:facebookEmail, facebookLink=:facebookLink, facebookAuthId=:facebookAuthId, facebookGender=:facebookGender, "
			+ "lastLoginTypeMap=:lastLoginTypeMap, lastLoginTimeStamp=:lastLoginTimeStamp, lastModifiedTimeStamp=:lastModifiedTimeStamp "
			+ " where "
			+ " userId = :userId")
	int updateFacebookAcct(@Bind("userId") Long userId,
			@Bind("typeMap") Integer typeMap, 
			@Bind("email") String email,
			@Bind("name") String name,
			@Bind("facebookEmail") String facebookEmail,
			@Bind("facebookLink") String facebookLink,
			@Bind("facebookAuthId") String facebookAuthId,
			@Bind("facebookGender") String facebookGender,
			@Bind("lastLoginTypeMap") Integer lastLoginTypeMap,
			@Bind("lastLoginTimeStamp") Timestamp lastLoginTimeStamp,
			@Bind("lastModifiedTimeStamp") Timestamp lastModifiedTimeStamp
			);

	@SqlUpdate("update user set typeMap=:typeMap, email=:email, name=:name, "
			+ "googleEmail=:googleEmail, googleLink=:googleLink, googleAuthId=:googleAuthId, googlePicture=:googlePicture, "
			+ "lastLoginTypeMap=:lastLoginTypeMap, lastLoginTimeStamp=:lastLoginTimeStamp, lastModifiedTimeStamp=:lastModifiedTimeStamp "
			+ " where "
			+ " userId = :userId")
	int updateGoogleAcct(@Bind("userId") Long userId,
			@Bind("typeMap") Integer typeMap, 
			@Bind("email") String email,
			@Bind("name") String name,
			@Bind("googleEmail") String googleEmail,
			@Bind("googleLink") String googleLink,
			@Bind("googleAuthId") String googleAuthId,
			@Bind("googlePicture") String googlePicture,
			@Bind("lastLoginTypeMap") Integer lastLoginTypeMap,
			@Bind("lastLoginTimeStamp") Timestamp lastLoginTimeStamp,
			@Bind("lastModifiedTimeStamp") Timestamp lastModifiedTimeStamp
			);
	
	@SqlUpdate("delete from user where userId = :userId")
	int deleteUser(@Bind("userId") Long userId);
	
	@SqlQuery("select count(*) from user")
	int countUser();
	
	@SqlQuery("select userId,identity,typeMap,email,name,status from user where userId = :userId")
	UserBean select(@Bind("userId") Long userId);
	
	@SqlQuery("select userId,identity,typeMap,email,name,googleAuthId,status from user where googleAuthId = :googleAuthId")
	UserBean selectViaGoogleId(@Bind("googleAuthId") String googleAuthId);
	
	@SqlQuery("select userId,identity,typeMap,email,name,facebookAuthId,status from user where facebookAuthId = :facebookAuthId")
	UserBean selectViaFacebookId(@Bind("facebookAuthId") String facebookAuthId);
}
