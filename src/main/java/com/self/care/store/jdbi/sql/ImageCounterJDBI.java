package com.self.care.store.jdbi.sql;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import com.self.care.store.jdbi.impl.BasicJDBICommand;

public interface ImageCounterJDBI extends BasicJDBICommand<Integer>{
	
	@SqlUpdate("insert into image_counter (imageId, counter) values (:imageId, :counter)")
	void insert(@Bind("imageId") String imageId, @Bind("counter") Integer counter);
	
	@SqlUpdate("update image_counter set counter = counter+1 where imageId = :imageId")
	int updateCounter(@Bind("imageId") String imageId);

	@SqlQuery("select counter from image_counter where imageId = :imageId")
	Integer select(@Bind("imageId") String tagId);

}
