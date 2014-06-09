package com.self.care.store.jdbi.impl;

import java.util.concurrent.TimeUnit;

public interface JDBISetting {
	
	//Caching.
	public static final int DATASOURCE_CACHE_SIZE = 2;
	public static final int RESULT_CACHE_SIZE = 100;
	public static final int TIME_DEFAULT_VALUE = 7;
	public static final TimeUnit TIME_DEFAULT_UNIT = TimeUnit.DAYS;
	public static final String CONNECTION_TIMEOUT = "60000"; //setup for both read and connect
	
	//Database connection
	public static final String IMG_CONNECTION_SERVICE = "image_mysql";
	
}
