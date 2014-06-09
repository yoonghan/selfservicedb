package com.self.care.store.jdbi.sql;

import java.util.Iterator;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.self.care.store.jdbi.entity.ImageBean;
import com.self.care.store.jdbi.entitymapper.ImageMapper;
import com.self.care.store.jdbi.impl.BasicJDBICommand;
import com.self.care.store.jdbi.impl.EnumStatus;

@RegisterMapper(ImageMapper.class)
public interface ImageJDBI extends BasicJDBICommand<ImageBean>{
	
	@SqlUpdate("insert into image (imageId, name, location, enumCountryId, enumRatingId, exposure, settings, tools, metaDate, description, cUser, cTime, mTime, URI)"
			+" values (:imageId, :name, :location, :enumCountryId, :enumRatingId, :exposure, :settings, :tools, :metaDate, :description, :cUser, :cTime, :mTime, :URI)")
	void insert(@BindBean ImageBean bean);
	
	@SqlUpdate("update into image set"
			+ "imageId=:imageId, "
			+ "name=:name, "
			+ "location=:location, "
			+ "enumCountryId=:enumCountryId, "
			+ "enumRatingId=:enumRatingId, "
			+ "exposure=:exposure, "
			+ "settings=:settings, "
			+ "tools=:tools, "
			+ "metaDate=:metaDate, "
			+ "description=:description, "
			+ "enumStatusId=:enumStatusId, "
			+ "cUser=:cUser, "
			+ "cDate=:cDate, "
			+ "URI=:URI "
			+ " where imageId = :imageId")
	void update(@BindBean ImageBean bean);

	@SqlQuery("select imageId, name, location, enumCountryId, enumRatingId, exposure, settings, tools, metaDate, description, enumStatusId, cUser, cTime, mTime, URI "
			+ " from image where imageId=:imageId and enumStatusId="+EnumStatus.VISIBLE)
	ImageBean select(@Bind("imageId") String imageId);
	
	@SqlQuery("select imageId, name, location, countryId, ratingId, exposure, settings, tools, metaDate, description, enumStatusId, cUser, cTime, mTime, URI "
			+ " from image and enumStatusId="+EnumStatus.VISIBLE)
	Iterator<ImageBean> findAllNames();
}
