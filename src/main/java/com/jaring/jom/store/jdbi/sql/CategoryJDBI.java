package com.jaring.jom.store.jdbi.sql;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.jaring.jom.store.jdbi.entity.CategoryBean;
import com.jaring.jom.store.jdbi.entitymapper.CategoryMapper;
import com.jaring.jom.store.jdbi.impl.BasicJDBICommand;
import com.jaring.jom.store.jdbi.impl.EnumStatus;

@RegisterMapper(CategoryMapper.class)
public interface CategoryJDBI extends BasicJDBICommand{
	
	@SqlUpdate("insert into category (categoryId, name, description, ,enumStatusId, counter) values (:categoryId, :name, :description, :enumStatusId, :counter)")
	void insert(@BindBean CategoryBean categoryBean);
	
	@SqlUpdate("update category set name = :name, description = :description where categoryId = :categoryId")
	int update(@Bind("name") String name, @Bind("description") String description, @Bind("categoryId") String categoryId);
	
	@SqlUpdate("update category set counter = counter+1 where categoryId = :categoryId")
	int updateCounter(@Bind("categoryId") String categoryId);

	@SqlQuery("select name from category where categoryId = :categoryId and enumStatusId="+EnumStatus.VISIBLE)
	String select(@Bind("categoryId") String categoryId);
	
	@SqlQuery("select categoryId,name,description,counter from category where enumStatusId="+EnumStatus.VISIBLE)
	List<CategoryBean> findAll();
	
}
