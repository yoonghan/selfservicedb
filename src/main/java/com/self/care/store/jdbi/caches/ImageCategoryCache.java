package com.self.care.store.jdbi.caches;

import java.util.ArrayList;
import java.util.List;

import com.self.care.store.jdbi.caches.impl.AbstractQuerySingleResultCache;
import com.self.care.store.jdbi.entity.ImageBean;
import com.self.care.store.jdbi.impl.JDBISetting;
import com.self.care.store.jdbi.sql.ImageCategoryJDBI;

public class ImageCategoryCache extends AbstractQuerySingleResultCache<List<ImageBean>, ImageCategoryJDBI> {

	static final class Singleton{
		public static final ImageCategoryCache instance = new ImageCategoryCache();
	}

	public static ImageCategoryCache getInstance(){
		return Singleton.instance;
	}
	
	private ImageCategoryCache() {
		super(JDBISetting.IMG_CONNECTION_SERVICE, ImageCategoryJDBI.class,"imagecategory");
	}

	@Override
	protected List<ImageBean> getReturnValue(String key, ImageCategoryJDBI sqlConnectionObject) {
		List<ImageBean> value = sqlConnectionObject.select(key);
		return value;
	}

	@Override
	public List<ImageBean> getDefaultValueIfNull() {
		return new ArrayList<ImageBean>(0);
	}
}
