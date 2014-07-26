package com.jaring.jom.store.jdbi.caches;

import java.util.List;

import com.jaring.jom.store.jdbi.caches.impl.AbstractQuerySingleResultCache;
import com.jaring.jom.store.jdbi.entity.ImageBean;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableCustomList;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableImageBean;
import com.jaring.jom.store.jdbi.impl.JDBISetting;
import com.jaring.jom.store.jdbi.sql.ImageTagJDBI;

public class ImageTagCache extends AbstractQuerySingleResultCache<ImmutableCustomList<ImmutableImageBean>, ImageTagJDBI> {
	
	ImageTagCache() {
		super(JDBISetting.IMG_CONNECTION_SERVICE, ImageTagJDBI.class,"imagetag");
	}
	
	@Override
	protected List<ImageBean> getReturnValue(String key, ImageTagJDBI sqlConnectionObject) {
		List<ImageBean> value = sqlConnectionObject.select(key);
		return value;
	}

	@Override
	public ImmutableCustomList<ImmutableImageBean> getDefaultValueIfNull() {
		return new ImmutableCustomList<ImmutableImageBean>();
	}

}
