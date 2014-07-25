package com.jaring.jom.store.jdbi.impl;

import java.util.concurrent.TimeUnit;

public interface JDBISetting {
	
	//Caching.
	public static final int DATASOURCE_CACHE_SIZE = 2;
	public static final int RESULT_CACHE_SIZE = 100;
	public static final int TIME_DEFAULT_VALUE = 7;
	public static final TimeUnit TIME_DEFAULT_UNIT = TimeUnit.DAYS;
	public static final int WORLD_WAITING_IN_SECONDS_VALUE = 3;
	public static final String CONNECTION_TIMEOUT = "60000"; //setup for both read and connect
	
	//Database connection
	public static final String IMG_CONNECTION_SERVICE = "image_mysql";
	
}
