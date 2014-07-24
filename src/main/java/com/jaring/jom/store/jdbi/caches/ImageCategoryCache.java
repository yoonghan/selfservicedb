package com.jaring.jom.store.jdbi.caches;

import java.util.List;

import com.jaring.jom.store.jdbi.caches.impl.AbstractQuerySingleResultCache;
import com.jaring.jom.store.jdbi.entity.ImageBean;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableImageList;
import com.jaring.jom.store.jdbi.impl.JDBISetting;
import com.jaring.jom.store.jdbi.sql.ImageCategoryJDBI;

public class ImageCategoryCache extends AbstractQuerySingleResultCache<List<ImageBean>, ImmutableImageList, ImageCategoryJDBI> {

	ImageCategoryCache() {
		super(JDBISetting.IMG_CONNECTION_SERVICE, ImageCategoryJDBI.class,"imagecategory");
	}

	@Override
	protected List<ImageBean> getReturnValue(String key, ImageCategoryJDBI sqlConnectionObject) {
		List<ImageBean> value = sqlConnectionObject.select(key);
		return value;
	}

	@Override
	public ImmutableImageList getDefaultValueIfNull() {
		return new ImmutableImageList();
	}

	@Override
	protected ImmutableImageList getImmutableValue(List<ImageBean> returnValue) {
		return new ImmutableImageList(returnValue);
	}

}
