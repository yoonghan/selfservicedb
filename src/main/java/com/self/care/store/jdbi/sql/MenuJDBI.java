package com.self.care.store.jdbi.sql;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.self.care.store.jdbi.entity.MenuBean;
import com.self.care.store.jdbi.entitymapper.MenuMapper;
import com.self.care.store.jdbi.impl.BasicJDBICommand;

@RegisterMapper(MenuMapper.class)
public interface MenuJDBI  extends BasicJDBICommand{

	@SqlQuery("select menuId, toolTip, textDisplay, imageURI, linkURI, enumTypeId from menu where menuId = :menuId")
	MenuBean select(@Bind("menuId") Integer menuId);
}
