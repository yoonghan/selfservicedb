package com.self.care.store.jdbi.util;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ExecutionException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.skife.jdbi.v2.DBI;

import com.self.care.store.jdbi.impl.JDBISetting;
import com.self.service.logging.log.LogUtil;

public class DataSourceHandler {
	private final HashMap<String, DBI> DATA_SOURCE_CACHE;
	
	//Create a property reader that read database only when needed. once done let garbage collector to claim this.
	private DBPropertyReader propReader;
	
	private final String CLASSNAME = this.getClass().getName(); 
	
	private static class Singleton{
		public static final DataSourceHandler instance = new DataSourceHandler(EnumConnectionType.DATASOURCE, null); 
	}
	
	private DataSourceHandler(EnumConnectionType connectionType, String connectionDetails){
		DATA_SOURCE_CACHE = new HashMap<String,DBI>(JDBISetting.DATASOURCE_CACHE_SIZE);
	}
	
	public static DataSourceHandler getInstance(){
		return Singleton.instance;
	}
	
	public DBI getDataSource(String dsName) throws ExecutionException{
		DBI dbConn = getDataSourceCache(dsName);
		
		if( dbConn == null){
			propReader = loadProperty(dsName);
			
			if(propReader != null)
				dbConn = getConnection(dsName);
			
			if(dbConn != null){
				DATA_SOURCE_CACHE.put(dsName, dbConn);
			}
			
		}
		return dbConn;
	}
	
	private DBPropertyReader loadProperty(String dsName) {
		return new DBPropertyReader(dsName);
	}
	
	private DBI getDataSourceCache(String dsName){
		return DATA_SOURCE_CACHE.get(dsName);
	}

	private synchronized DBI getConnection(final String dsName){
		
		//retry obtaining source as method is synchronized for wait.
		DBI dbi = getDataSourceCache(dsName);
		
		if(dbi == null){
			
			switch(propReader.getConnectionType()){
			
			//Database connection using query strings.
			case CONNECTION_QUERY:
				dbi = connectViaConnectionQuery(dsName);
				break;
			//Database connection using data source.
			case DATASOURCE:
				dbi = connectViaDataSource(dsName);
				break;
			}
			
			
			if(dbi == null){
				LogUtil.getInstance(CLASSNAME).info("Connection is set improperly. Check on "+dsName);
				
				System.exit(-1);		//Fail the system. Should not proceed.
			}
		}
		
		return dbi;
	}

	private DBI connectViaConnectionQuery(String dsName) {
		String jdbc_url = propReader.getConnectionURL();
		
		LogUtil.getInstance(CLASSNAME).info("Connected to JDBC:"+jdbc_url);
		
		return new DBI(jdbc_url,propReader.getConnectionUserName(), propReader.getConnectionPassword());
	}

	private DBI connectViaDataSource(String dsName) {
		Context initContext;
		DataSource datasource = null;
		
		try {
			
			Hashtable<String, String> connectionSetup = new Hashtable<String,String>();
			connectionSetup.put("jnp.timeout", JDBISetting.CONNECTION_TIMEOUT);
			connectionSetup.put("jnp.sotimeout", JDBISetting.CONNECTION_TIMEOUT);
			
			initContext = new InitialContext(connectionSetup);
			datasource  = (DataSource)initContext.lookup(propReader.getContextLookup());
			
			LogUtil.getInstance(CLASSNAME).info("Connected to data source:"+dsName);
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return 
			datasource == null?
				null:
				new DBI(datasource);
	}
}