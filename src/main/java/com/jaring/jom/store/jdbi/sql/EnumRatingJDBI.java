package com.jaring.jom.store.jdbi.sql;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.jaring.jom.store.jdbi.entity.EnumRatingBean;
import com.jaring.jom.store.jdbi.entitymapper.EnumRatingMapper;
import com.jaring.jom.store.jdbi.impl.BasicJDBICommand;

@RegisterMapper(EnumRatingMapper.class)
public interface EnumRatingJDBI extends BasicJDBICommand{
	
	@SqlQuery("select enumRatingId, rating from enum_rating where enumRatingId = :enumRatingId")
	EnumRatingBean select(@Bind("enumRatingId") String ratingId);
	
}
