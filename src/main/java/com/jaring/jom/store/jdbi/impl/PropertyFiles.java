package com.jaring.jom.store.jdbi.impl;

public interface PropertyFiles {
	public final static String DB_PROP="/selfservicedb.properties";
	public final static String CACHE_PROP="/selfservicecaching.properties";
	
	//for db properties.
	public final static String CONN_TYPE=".conn.type";
	public final static String CONN_URL=".conn.url";
	public final static String CONN_USERNAME=".conn.username";
	public final static String CONN_PASSWORD=".conn.password";
	public final static String CONN_LOOKUP=".conn.lookup";
	
	//for cache properties.
	public final static String TIME_UNIT_KEY = ".timeUnit";
	public final static String TIME_VALUE_KEY = ".timeValue";
	public final static String WORLD_WAITING_IN_SECONDS_VALUE = ".worldWaitingInSeconds";
	public final String CACHE_MULTI_KEYWORD = ".all";
}
