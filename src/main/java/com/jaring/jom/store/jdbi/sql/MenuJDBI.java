package com.jaring.jom.store.jdbi.sql;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.jaring.jom.store.jdbi.entity.MenuBean;
import com.jaring.jom.store.jdbi.entitymapper.MenuMapper;
import com.jaring.jom.store.jdbi.impl.BasicJDBICommand;

@RegisterMapper(MenuMapper.class)
public interface MenuJDBI  extends BasicJDBICommand{

	@SqlQuery("select menuId, toolTip, textDisplay, imageURI, linkURI, enumTypeId from menu where menuId = :menuId")
	MenuBean select(@Bind("menuId") Integer menuId);
}
