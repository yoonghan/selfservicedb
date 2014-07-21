package com.self.care.store.jdbi.caches.impl;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.skife.jdbi.v2.DBI;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.self.care.store.jdbi.impl.BasicJDBICommand;
import com.self.care.store.jdbi.impl.JDBISetting;
import com.self.care.store.jdbi.impl.PropertyFiles;
import com.self.care.store.jdbi.util.DataSourceHandler;
import com.self.service.logging.impl.Log;
import com.self.service.logging.log.LogFactory;
import com.self.service.util.common.PropertyLoaderUtil;

public abstract class AbstractQuerySingleResultCache<T, U extends BasicJDBICommand<T>> {
	
	private final Log log = LogFactory.getLogger("com.self.care.store.jdbi.caches.impl.AbstractQuerySingleResultCache");
	
	private final LoadingCache<String, T> RESULT_SOURCE_CACHE;
	private final String JDBI_NAME;
	private final Class<U> SQL_OBJECT;
	private final T DEFAULT_NULL_VALUE = getDefaultValueIfNull();
	
	private LoadingCache<String, T> initCacheLoader(int cacheSize, int timeValue, TimeUnit timeUnit){
		return CacheBuilder.newBuilder()
		.maximumSize(cacheSize)
		.expireAfterWrite(timeValue, timeUnit)
		.build(
			new CacheLoader<String, T>(){
				public T load(String key) {
					return queryValue(key);
				}
			}
		);
	}
	
	@SuppressWarnings("unused")
	private AbstractQuerySingleResultCache(){
		//disable initiate.
		JDBI_NAME=null;
		SQL_OBJECT=null;
		this.RESULT_SOURCE_CACHE = initCacheLoader(JDBISetting.RESULT_CACHE_SIZE, JDBISetting.TIME_DEFAULT_VALUE, JDBISetting.TIME_DEFAULT_UNIT);
	}
	
	protected AbstractQuerySingleResultCache(String JDBI_NAME, Class<U> SQL_OBJECT, String cacheSettingName){
		this.JDBI_NAME = JDBI_NAME;
		this.SQL_OBJECT = SQL_OBJECT;

		CachePropertyBean cacheBean = new CachePropertyBean(cacheSettingName);
		
		try {
			new PropertyLoaderUtil().loadProperty(PropertyFiles.CACHE_PROP, cacheBean);
		} catch (IOException | ClassNotFoundException | IllegalAccessException e) {
			log.warn("Load property error, loading default values:"+e.getMessage());
		}
		
		this.RESULT_SOURCE_CACHE = initCacheLoader(JDBISetting.RESULT_CACHE_SIZE, cacheBean.getTimeValue(), cacheBean.getTimeUnit());
		log.info("Cache Instance created.");
	}
	
	/**
	 * Override this to get value from cache.
	 * @param key
	 * @return
	 */
	protected abstract T getReturnValue(String key, U sqlConnectionObject);
	
	private T queryValue(String key){
		T returnValue = null;
		try {
			DBI dbConn = DataSourceHandler.getInstance().getDataSource(JDBI_NAME);
			U object = dbConn.open(SQL_OBJECT);
			returnValue = getReturnValue(key, object);
			object.close();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		if(returnValue == null){
			return DEFAULT_NULL_VALUE;
		}
		
		return returnValue;
	}
	
	public T getValue(String key, boolean cloneCopy) throws ExecutionException{
		T result = RESULT_SOURCE_CACHE.get(key);

		return cloneCopy == false? 
				result: 
				cloneCopy(result);
	}
	
	protected abstract T cloneCopy(T toCloneValue);

	public void refreshCache(){
		RESULT_SOURCE_CACHE.invalidateAll();
	}
	
	public void refreshOnIdCache(String id){
		if(id !=null && id.isEmpty() == false)
			RESULT_SOURCE_CACHE.invalidate(id);
	}

	protected abstract T getDefaultValueIfNull();
}
