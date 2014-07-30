package com.jaring.jom.store.jdbi.caches.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

import org.skife.jdbi.v2.DBI;

import com.google.common.collect.ImmutableList;
import com.jaring.jom.logging.impl.Log;
import com.jaring.jom.logging.log.LogFactory;
import com.jaring.jom.store.jdbi.impl.BasicJDBICommand;
import com.jaring.jom.store.jdbi.impl.PropertyFiles;
import com.jaring.jom.store.jdbi.util.DataSourceHandler;
import com.jaring.jom.util.common.PropertyLoaderUtil;

import static com.jaring.jom.store.jdbi.impl.PropertyFiles.*;

public abstract class AbstractQueryMultiResultCache<T extends Immutable<?>, V extends Immutable<?>, U extends BasicJDBICommand> 
		extends AbstractQuerySingleResultCache<T, U>{
	
	private final Log log = LogFactory.getLogger(this.getClass().getName());
	
	private final String JDBI_NAME;
	private final Class<U> SQL_OBJECT;
	private final ImmutableList<V> DEFAULT_VALUE = ImmutableList.copyOf(new ArrayList<V>());
	private final AtomicBoolean REFRESH_CACHE = new AtomicBoolean(true);
	private ScheduledExecutorService executor;
	
	private transient ImmutableList<V> listOfRecords = null;
	private CachePropertyBean cacheBean = null;
	
	
	protected AbstractQueryMultiResultCache(String JDBI_NAME, Class<U> SQL_OBJECT, String cacheSettingName) {
		super(JDBI_NAME, SQL_OBJECT, cacheSettingName);
		this.JDBI_NAME=JDBI_NAME;
		this.SQL_OBJECT=SQL_OBJECT;
		
		init(cacheSettingName+CACHE_MULTI_KEYWORD);
	}

	private void init(String cacheSettingName) {
		
		//set cache bean
		cacheBean = new CachePropertyBean(cacheSettingName);
		
		PropertyLoaderUtil.loadProperty(PropertyFiles.CACHE_PROP, cacheBean);
		
		//set executor thread
		executor = Executors.newSingleThreadScheduledExecutor();
		
	}

	public void startRecordRefresher() {
		executor.schedule(new Runnable(){
			public void run() {
				clearFindAllCache();
			}
		}, cacheBean.getConvertedTime(), cacheBean.getTimeUnit());
	}
	
	public ImmutableList<V> getAll(){
		ImmutableList<V> returnedValue = listOfRecords;
		
		if(REFRESH_CACHE.compareAndSet(true, false)){
			try {
				DBI dbConn = DataSourceHandler.getInstance().getDataSource(JDBI_NAME);
				U object = dbConn.open(SQL_OBJECT);
				
				returnedValue = getImmutableList(getReturnAllValue(object));
				
				listOfRecords = returnedValue;
				startRecordRefresher();
				
				object.close();
			} catch (ExecutionException e) {
				e.printStackTrace();
				clearFindAllCache();
			}
		}else if(returnedValue == null){
			sleep();
			returnedValue = listOfRecords;
			if(returnedValue == null){
				returnedValue = DEFAULT_VALUE; //create an empty default value.
				log.error("Cache slept "+cacheBean.getWorldWaitingInSecondsValue()+" and still couldn't get value!");
			}
		}
		
		return returnedValue;
	}
	
	/**
	 * While someone go and get the value; we make the rest of the world sleep.
	 */
	private void sleep(){
		try{
			Thread.sleep(cacheBean.getWorldWaitingInSecondsValue());
		}catch(Exception e){
			log.warn("User fail to get value - "+this.getClass().getName());
		}
	}
	
	private ImmutableList<V> getImmutableList(List<?> returnAllValues) {
		List<V> convertedList = new ArrayList<V>();
		
		if(returnAllValues != null){
			for(Object value : returnAllValues){
				
				@SuppressWarnings("unchecked")
				V convertedValue = (V)convertValueToImmutable(value);
				
				if(convertedValue != null){
					convertedList.add(
							convertedValue
							);
				}
			}
		}
		ImmutableList<V> immutableArray = ImmutableList.copyOf(convertedList);
		return immutableArray;
	}
	
	public void clearFindAllCache(){
		REFRESH_CACHE.set(true);
	}
	
	protected abstract List<?> getReturnAllValue(U sqlConnection);
}