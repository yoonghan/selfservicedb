package com.jaring.jom.store.jdbi.caches;

import com.jaring.jom.store.jdbi.caches.impl.AbstractQuerySingleResultCache;
import com.jaring.jom.store.jdbi.entity.ImageBean;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableImageBean;
import com.jaring.jom.store.jdbi.impl.JDBISetting;
import com.jaring.jom.store.jdbi.sql.ImageJDBI;

public class ImageCache extends AbstractQuerySingleResultCache<ImageBean, ImmutableImageBean, ImageJDBI> {

	ImageCache() {
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
