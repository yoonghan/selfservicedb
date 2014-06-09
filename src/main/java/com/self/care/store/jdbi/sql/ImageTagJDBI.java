package com.self.care.store.jdbi.sql;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.self.care.store.jdbi.entity.ImageBean;
import com.self.care.store.jdbi.entitymapper.ImageTagMapper;
import com.self.care.store.jdbi.impl.BasicJDBICommand;

@RegisterMapper(ImageTagMapper.class)
public interface ImageTagJDBI extends BasicJDBICommand<List<ImageBean>>{
	
	@SqlUpdate("insert into image_tag (imageId, tagId) values (:imageId, :tagId)")
	void insert(@Bind("imageId") String imageId, @Bind("tagId") String tagId);
	
	@SqlUpdate("delete from image_tag where imageId = :imageId and tagId = :tagId")
	void delete(@Bind("imageId") String imageId, @Bind("tagId") String tagId);

	@SqlQuery("select imageId from image_tag where tagId = :tagId")
	List<ImageBean> select(@Bind("tagId") String tagId);

}
