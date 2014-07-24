package com.jaring.jom.store.jdbi.sql;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.jaring.jom.store.jdbi.entity.MenuListBean;
import com.jaring.jom.store.jdbi.entitymapper.MenuListMapper;
import com.jaring.jom.store.jdbi.impl.BasicJDBICommand;

@RegisterMapper(MenuListMapper.class)
public interface MenuListJDBI  extends BasicJDBICommand{
	
	@SqlQuery("select menuId, level, levelOrder from menugroup_menu where menuGroupId = :menuGroupId"
			+ " order by level, levelOrder")
	List<MenuListBean> select(@Bind("menuGroupId") Integer menuGroupId);
}
