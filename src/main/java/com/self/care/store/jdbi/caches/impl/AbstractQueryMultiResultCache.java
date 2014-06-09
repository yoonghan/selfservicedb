package com.self.care.store.jdbi.caches.impl;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

import org.skife.jdbi.v2.DBI;

import com.self.care.store.jdbi.impl.BasicJDBICommand;
import com.self.care.store.jdbi.util.DataSourceHandler;

public abstract class AbstractQueryMultiResultCache<T, V, U extends BasicJDBICommand<T>> 
		extends AbstractQuerySingleResultCache<T, U>{
	
	private final String JDBI_NAME;
	private final Class<U> SQL_OBJECT;
	private final String CACHE_KEYWORD = ".all";
	
	private transient List<V> listOfRecords = null;
	
	protected AbstractQueryMultiResultCache(String JDBI_NAME, Class<U> SQL_OBJECT, String cacheSettingName) {
		super(JDBI_NAME, SQL_OBJECT, cacheSettingName);
		this.JDBI_NAME=JDBI_NAME;
		this.SQL_OBJECT=SQL_OBJECT;
		
		startRecordRefresher(cacheSettingName+CACHE_KEYWORD);
	}

	private void startRecordRefresher(String cacheSettingName) {
		Timer timer = new Timer();
		CachePropertyReader cacheReader = new CachePropertyReader(cacheSettingName);
		
		timer.scheduleAtFixedRate(new TimerTask(){
			public void run() {
				clearFindAllCache();
			}
		}, cacheReader.getConvertedTime(), cacheReader.getConvertedTime());
	}
	
	public List<V> getAll(){
		List<V> returnedValue = listOfRecords;
		
		if(returnedValue == null){
			try {
				DBI dbConn = DataSourceHandler.getInstance().getDataSource(JDBI_NAME);
				U object = dbConn.open(SQL_OBJECT);
				
				//Not to create synchronized here. Let the values get concurrent update if needed.
				returnedValue = getReturnAllValue(object);
				listOfRecords = returnedValue;
				object.close();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		return returnedValue;
	}
	
	public void clearFindAllCache(){
		List<V> clearRecord = listOfRecords;
		listOfRecords = null;
		clearRecord.clear();
	}
	
	protected abstract List<V> getReturnAllValue(U sqlConnection);
}
