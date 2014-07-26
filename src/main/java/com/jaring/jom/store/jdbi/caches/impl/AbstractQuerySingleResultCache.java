package com.jaring.jom.store.jdbi.caches.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.skife.jdbi.v2.DBI;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;
import com.jaring.jom.logging.impl.Log;
import com.jaring.jom.logging.log.LogFactory;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableCustomList;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableInteger;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableShort;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableString;
import com.jaring.jom.store.jdbi.impl.BasicJDBICommand;
import com.jaring.jom.store.jdbi.impl.JDBISetting;
import com.jaring.jom.store.jdbi.impl.PropertyFiles;
import com.jaring.jom.store.jdbi.util.DataSourceHandler;
import com.jaring.jom.util.common.PropertyLoaderUtil;

public abstract class AbstractQuerySingleResultCache<T extends Immutable<?>, U extends BasicJDBICommand> {
	
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
	
	@SuppressWarnings("unchecked")
	private T queryValue(String key){
		Object returnValue = null;
		try {
			DBI dbConn = DataSourceHandler.getInstance().getDataSource(JDBI_NAME);
			U object = dbConn.open(SQL_OBJECT);
			returnValue = getReturnValue(key, object);
			object.close();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		T immutableValue = null;
		
		if(returnValue == null){
			immutableValue = DEFAULT_NULL_VALUE;
		}else{
			immutableValue = (T)convertValueToImmutable(returnValue);
		}
		
		return immutableValue;
	}
	
	public T getValue(String key) throws ExecutionException{
		T result = RESULT_SOURCE_CACHE.get(key);
		return result;
	}

	public void refreshCache(){
		RESULT_SOURCE_CACHE.invalidateAll();
	}
	
	public void refreshOnIdCache(String id){
		if(id !=null && id.isEmpty() == false)
			RESULT_SOURCE_CACHE.invalidate(id);
	}

	/**
	 * Override this to get value from cache.
	 * @param key
	 * @return
	 */
	protected abstract Object getReturnValue(String key, U sqlConnectionObject);
	
	/**
	 * Override to get default null values.
	 * @return
	 */
	protected abstract T getDefaultValueIfNull();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Object convertValueToImmutable(Object value) {
		
		Object converted = null;
		if(value instanceof String){
			converted = (new ImmutableString((String)value));
			
		}else if(value instanceof Short){
			converted = (new ImmutableShort((Short)value));
			
		}else if(value instanceof Integer){
			converted = (new ImmutableInteger((Integer)value));
			
		}else if(value instanceof ImmutableMapper){
			converted = ((ImmutableMapper<T>)value).mapper();
			
		}else if(value instanceof List){
			List<?> tempList = (List<?>) value;
			List<Object> convertedList = new ArrayList<Object>(tempList.size());
			
			for(Object listValue : tempList){
				
				Object convertedValue = convertValueToImmutable(listValue);
				
				if(convertedValue != null){
					convertedList.add(convertedValue);
				}
			}

			converted = new ImmutableCustomList(ImmutableList.copyOf(convertedList));
		}else{
			log.equals("Unable to convert object:"+value);
		}
		
		return converted;
	}

}
