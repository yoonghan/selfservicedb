package com.jaring.jom.store.jdbi.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ExecutionException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.skife.jdbi.v2.DBI;

import com.jaring.jom.logging.impl.Log;
import com.jaring.jom.logging.log.LogFactory;
import com.jaring.jom.store.jdbi.impl.JDBISetting;
import com.jaring.jom.store.jdbi.impl.PropertyFiles;
import com.jaring.jom.util.common.PropertyLoaderUtil;

public class DataSourceHandler {
	private final HashMap<String, DBI> DATA_SOURCE_CACHE;
	
	private final Log log = LogFactory.getLogger(this.getClass().getName()); 
	
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
			DBPropertyBean propBean = new DBPropertyBean(dsName);
			try {
				new PropertyLoaderUtil().loadProperty(PropertyFiles.DB_PROP, propBean);
				
				if(propBean != null)
					dbConn = getConnection(dsName, propBean);
				
				if(dbConn != null){
					DATA_SOURCE_CACHE.put(dsName, dbConn);
				}
				
			} catch (ClassNotFoundException | IllegalAccessException| IOException e) {
				log.error("Database properties no readable"+PropertyFiles.CACHE_PROP, e);;
			}
			
		}
		return dbConn;
	}
	
	private DBI getDataSourceCache(String dsName){
		return DATA_SOURCE_CACHE.get(dsName);
	}

	private synchronized DBI getConnection(final String dsName, DBPropertyBean propBean){
		
		//retry obtaining source as method is synchronized for wait.
		DBI dbi = getDataSourceCache(dsName);
		
		if(dbi == null){
			
			switch(propBean.getConnectionType()){
			
			//Database connection using query strings.
			case CONNECTION_QUERY:
				dbi = connectViaConnectionQuery(dsName, propBean);
				break;
			//Database connection using data source.
			case DATASOURCE:
				dbi = connectViaDataSource(dsName, propBean);
				break;
			}
			
			
			if(dbi == null){
				log.info("Connection is set improperly. Check on "+dsName);
				
				System.exit(-1);		//Fail the system. Should not proceed.
			}
		}
		
		return dbi;
	}

	private DBI connectViaConnectionQuery(String dsName, DBPropertyBean propBean) {
		String jdbc_url = propBean.getConnectionURL();
		
		log.info("Connected to JDBC:"+jdbc_url);
		
		return new DBI(jdbc_url,propBean.getConnectionUserName(), propBean.getConnectionPassword());
	}

	private DBI connectViaDataSource(String dsName, DBPropertyBean propBean) {
		Context initContext;
		DataSource datasource = null;
		
		try {
			
			Hashtable<String, String> connectionSetup = new Hashtable<String,String>();
			connectionSetup.put("jnp.timeout", JDBISetting.CONNECTION_TIMEOUT);
			connectionSetup.put("jnp.sotimeout", JDBISetting.CONNECTION_TIMEOUT);
			
			initContext = new InitialContext(connectionSetup);
			datasource  = (DataSource)initContext.lookup(propBean.getContextLookup());
			
			log.info("Connected to data source:"+dsName);
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return 
			datasource == null?
				null:
				new DBI(datasource);
	}
}