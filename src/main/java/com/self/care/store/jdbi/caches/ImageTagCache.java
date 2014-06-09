package com.self.care.store.jdbi.caches;

import java.util.ArrayList;
import java.util.List;

import com.self.care.store.jdbi.caches.impl.AbstractQuerySingleResultCache;
import com.self.care.store.jdbi.entity.ImageBean;
import com.self.care.store.jdbi.impl.JDBISetting;
import com.self.care.store.jdbi.sql.ImageTagJDBI;

public class ImageTagCache extends AbstractQuerySingleResultCache<List<ImageBean>, ImageTagJDBI> {

	static final class Singleton{
		public static final ImageTagCache instance = new ImageTagCache();
	}

	public static ImageTagCache getInstance(){
		return Singleton.instance;
	}
	
	private ImageTagCache() {
		super(JDBISetting.IMG_CONNECTION_SERVICE, ImageTagJDBI.class,"imagetag");
	}
	
	@Override
	protected List<ImageBean> getReturnValue(String key, ImageTagJDBI sqlConnectionObject) {
		List<ImageBean> value = sqlConnectionObject.select(key);
		return value;
	}

	@Override
	public List<ImageBean> getDefaultValueIfNull() {
		return new ArrayList<ImageBean>(0);
	}
	
}
