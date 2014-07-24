package com.jaring.jom.store.jdbi.entitymapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.jaring.jom.logging.impl.Log;
import com.jaring.jom.logging.log.LogFactory;
import com.jaring.jom.store.jdbi.caches.DBCache;
import com.jaring.jom.store.jdbi.entity.ImageBean;

public class ImageMapper implements ResultSetMapper<ImageBean> {

	private final Log log = LogFactory.getLogger(this.getClass().getName());
	
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
			ib.setCountry(DBCache.INSTANCE.getEnumCountry().getValue(enumCountryId).clone());
		} catch (ExecutionException e1) {
			log.info("Unable to get countryId:"+enumCountryId);
		}
		
		String userId = rs.getString("cUser");
		ib.setUserId(userId);
		try {
			ib.setUser(DBCache.INSTANCE.getUser().getValue(userId).clone());
		} catch (ExecutionException e) {
			log.info("Unable to get userId:"+userId);
		}
		
		Integer enumRatingId = rs.getInt("enumRatingId");
		ib.setEnumRatingId(enumRatingId);
		try {
			ib.setRating(DBCache.INSTANCE.getEnumRating().getValue(enumRatingId.toString()).clone());
		} catch (ExecutionException e) {
			log.info("Unable to get ratingId:"+enumRatingId);
		}
		
		return ib;
	}

}
