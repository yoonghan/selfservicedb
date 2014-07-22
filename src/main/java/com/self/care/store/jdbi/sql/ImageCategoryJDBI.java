package com.self.care.store.jdbi.sql;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.self.care.store.jdbi.entity.ImageBean;
import com.self.care.store.jdbi.entitymapper.ImageCategoryMapper;
import com.self.care.store.jdbi.impl.BasicJDBICommand;

@RegisterMapper(ImageCategoryMapper.class)
public interface ImageCategoryJDBI extends BasicJDBICommand{
	
	@SqlUpdate("insert into image_category (imageId, categoryId) values (:imageId, :categoryId)")
	void insert(@Bind("imageId") String imageId, @Bind("categoryId") String categoryId);
	
	@SqlUpdate("delete from image_category where imageId = :imageId and categoryId = :categoryId")
	void delete(@Bind("imageId") String imageId, @Bind("categoryId") String categoryId);

	@SqlQuery("select imageId from image_category where categoryId = :categoryId")
	List<ImageBean> select(@Bind("categoryId") String categoryId);
	
}
