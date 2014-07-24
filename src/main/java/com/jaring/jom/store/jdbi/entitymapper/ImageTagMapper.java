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

public class ImageTagMapper implements ResultSetMapper<ImageBean>{

	final Log log = LogFactory.getLogger(this.getClass().getName());
	
	@Override
	public ImageBean map(int index, ResultSet rs, StatementContext ctx)
			throws SQLException {
		
		String imageId = rs.getString("imageId");
		
		ImageBean ib = null;
		
		try {
			ib = DBCache.INSTANCE.getImage().getValue(imageId).clone();
		} catch (ExecutionException e) {
			log.error("Unable to get value for:"+imageId);
		}
		return ib;
	}
	
}
