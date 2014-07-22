package com.self.care.store.jdbi.sql;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.self.care.store.jdbi.entity.EnumCountryBean;
import com.self.care.store.jdbi.entitymapper.EnumCountryMapper;
import com.self.care.store.jdbi.impl.BasicJDBICommand;

@RegisterMapper(EnumCountryMapper.class)
public interface EnumCountryJDBI extends BasicJDBICommand{
	
	@SqlQuery("select enumCountryId, country, state from enum_country where enumCountryId = :enumCountryId")
	EnumCountryBean select(@Bind("enumCountryId") String enumCountryId);
	
}
