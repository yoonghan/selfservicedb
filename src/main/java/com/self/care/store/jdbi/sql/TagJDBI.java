package com.self.care.store.jdbi.sql;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import com.self.care.store.jdbi.impl.BasicJDBICommand;

public interface TagJDBI extends BasicJDBICommand<Short>{
	
	@SqlUpdate("insert into tag (tagId, counter) values (:tagId, :counter)")
	void insert(@Bind("tagId") String tagId, @Bind("counter") Short counter);
	
	@SqlUpdate("update tag set counter = counter+1 where tagId = :tagId")
	int updateCounter(@Bind("tagId") String tagId);

	@SqlQuery("select counter from tag where tagId = :tagId")
	Short select(@Bind("tagId") String tagId);

	@SqlQuery("select tagId from tag")
	List<String> findAll();
}
