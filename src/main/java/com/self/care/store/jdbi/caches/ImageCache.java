package com.self.care.store.jdbi.caches;

import com.self.care.store.jdbi.caches.impl.AbstractQuerySingleResultCache;
import com.self.care.store.jdbi.entity.ImageBean;
import com.self.care.store.jdbi.entity.immutable.ImmutableImageBean;
import com.self.care.store.jdbi.impl.JDBISetting;
import com.self.care.store.jdbi.sql.ImageJDBI;

public class ImageCache extends AbstractQuerySingleResultCache<ImageBean, ImmutableImageBean, ImageJDBI> {

	static final class Singleton{
		public static final ImageCache instance = new ImageCache();
	}

	public static ImageCache getInstance(){
		return Singleton.instance;
	}
	
	private ImageCache() {
		super(JDBISetting.IMG_CONNECTION_SERVICE, ImageJDBI.class,"image");
	}

	@Override
	protected ImageBean getReturnValue(String key, ImageJDBI sqlConnectionObject) {
		ImageBean value = sqlConnectionObject.select(key);
		return value;
	}

	@Override
	public ImmutableImageBean getDefaultValueIfNull() {
		return new ImmutableImageBean();
	}

	@Override
	protected ImmutableImageBean getImmutableValue(ImageBean returnValue) {
		return new ImmutableImageBean(
				returnValue.getImageId(),
				returnValue.getName(),
				returnValue.getLocation(),
				returnValue.getExposure(),
				returnValue.getSettings(),
				returnValue.getTools(),
				returnValue.getMetaDate(),
				returnValue.getDescription(),
				returnValue.getURI()
				);
	}
}
