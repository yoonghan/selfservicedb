package com.jaring.jom.store.jdbi.sql;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.jaring.jom.store.jdbi.entity.EnumCountryBean;
import com.jaring.jom.store.jdbi.entitymapper.EnumCountryMapper;
import com.jaring.jom.store.jdbi.impl.BasicJDBICommand;

@RegisterMapper(EnumCountryMapper.class)
public interface EnumCountryJDBI extends BasicJDBICommand{
	
	@SqlQuery("select enumCountryId, country, state from enum_country where enumCountryId = :enumCountryId")
	EnumCountryBean select(@Bind("enumCountryId") String enumCountryId);
	
}
