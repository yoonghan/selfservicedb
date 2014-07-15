package com.self.care.store.jdbi.entitymapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.self.care.store.jdbi.caches.ImageCache;
import com.self.care.store.jdbi.entity.ImageBean;
import com.self.service.logging.impl.Log;
import com.self.service.logging.log.LogFactory;

public class ImageTagMapper implements ResultSetMapper<ImageBean>{

	final Log log = LogFactory.getLogger(this.getClass().getName());
	
	@Override
	public ImageBean map(int index, ResultSet rs, StatementContext ctx)
			throws SQLException {
		
		String imageId = rs.getString("imageId");
		
		ImageBean ib = null;
		
		try {
			ib = ImageCache.getInstance().getValue(imageId);
		} catch (ExecutionException e) {
			log.error("Unable to get value for:"+imageId);
		}
		return ib;
	}
	
}
