package com.self.care.store.jdbi.entitymapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.self.care.store.jdbi.caches.EnumCountryCache;
import com.self.care.store.jdbi.caches.EnumRatingCache;
import com.self.care.store.jdbi.caches.UserCache;
import com.self.care.store.jdbi.entity.ImageBean;
import com.self.service.logging.log.LogUtil;

public class ImageMapper implements ResultSetMapper<ImageBean> {

	private final String CLASS_NAME = this.getClass().getName();
	
	@Override
	public ImageBean map(int index, ResultSet rs, StatementContext ctx)
			throws SQLException {
		ImageBean ib = new ImageBean();
		
		ib.setcTime(rs.getDate("cTime"));
		ib.setcUser(rs.getString("cUser"));
		ib.setDescription(rs.getString("description"));
		ib.setExposure(rs.getString("exposure"));
		ib.setImageId(rs.getString("imageId"));
		ib.setLocation(rs.getString("location"));
		ib.setMetaDate(rs.getString("metaDate"));
		ib.setmTime(rs.getDate("mTime"));
		ib.setName(rs.getString("name"));
		ib.setSettings(rs.getString("settings"));
		ib.setTools(rs.getString("tools"));
		ib.setURI(rs.getString("URI"));
		ib.setEnumStatusId(rs.getShort("enumStatusId"));
		
		String enumCountryId = rs.getString("enumCountryId");
		ib.setEnumCountryId(enumCountryId);
		try {
			ib.setCountry(EnumCountryCache.getInstance().getValue(enumCountryId));
		} catch (ExecutionException e1) {
			LogUtil.getInstance(CLASS_NAME).info("Unable to get countryId:"+enumCountryId);
		}
		
		String userId = rs.getString("cUser");
		ib.setUserId(userId);
		try {
			ib.setUser(UserCache.getInstance().getValue(userId));
		} catch (ExecutionException e) {
			LogUtil.getInstance(CLASS_NAME).info("Unable to get userId:"+userId);
		}
		
		Integer enumRatingId = rs.getInt("enumRatingId");
		ib.setEnumRatingId(enumRatingId);
		try {
			ib.setRating(EnumRatingCache.getInstance().getValue(enumRatingId.toString()));
		} catch (ExecutionException e) {
			LogUtil.getInstance(CLASS_NAME).info("Unable to get ratingId:"+enumRatingId);
		}
		
		return ib;
	}

}
