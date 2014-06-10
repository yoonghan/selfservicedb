package com.self.care.store.jdbi.caches.impl;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.self.care.store.jdbi.impl.JDBISetting;
import com.self.care.store.jdbi.impl.PropertyFiles;
import com.self.service.util.log.LogUtil;
import com.self.service.util.log.PropertyLoaderUtil;

class CachePropertyReader {

	private final String PROPERTY_FILENAME=PropertyFiles.CACHE_PROP;
	private final String CLASS_LOCATION="com.self.care.store.jdbi.caches.impl.CachePropertyReader";
	
	private int timeValue = JDBISetting.TIME_DEFAULT_VALUE;
	private TimeUnit timeUnit = JDBISetting.TIME_DEFAULT_UNIT;
	
	public CachePropertyReader(String cacheName){
		initProperties(cacheName);
	}
	
	private void initProperties(String cacheName) {
		try {
			Properties prop = new PropertyLoaderUtil().loadProperty(null, CLASS_LOCATION, PROPERTY_FILENAME);
			loadPropertiesToVariable(prop, cacheName);
		} catch (IOException | ClassNotFoundException e) {
			LogUtil.getInstance(CLASS_LOCATION).warn("Load property error, loading default values:"+e.getMessage());
			e.printStackTrace();
			setTimeValue(JDBISetting.TIME_DEFAULT_VALUE);
			setTimeUnit(JDBISetting.TIME_DEFAULT_UNIT);		
		}
	}
	
	private void loadPropertiesToVariable(Properties prop, String cacheName){
		String timeUnit = prop.getProperty(cacheName + PropertyFiles.TIME_UNIT_KEY);
		String timeValue = prop.getProperty(cacheName + PropertyFiles.TIME_VALUE_KEY);
		try{
			int time=Integer.parseInt(timeValue, 10);
			setTimeValue(time);
		}catch(Exception e){
			LogUtil.getInstance(CLASS_LOCATION).warn("Reset "+cacheName+", Invalid time setting:"+timeValue);
		}
		
		if(timeUnit != null){
			switch(timeUnit){
			case "1":
				setTimeUnit(TimeUnit.MINUTES);
				break;
			case "2":
				setTimeUnit(TimeUnit.HOURS);
				break;
			case "3":
				setTimeUnit(TimeUnit.DAYS);
				break;
			default:
				LogUtil.getInstance(CLASS_LOCATION).warn("Reset "+cacheName+", Invalid time unit:"+timeUnit);
			}
		}else{
			LogUtil.getInstance(CLASS_LOCATION).warn("Reset "+cacheName+", Invalid time unit not set");
		}
		
		LogUtil.getInstance(CLASS_LOCATION).info("Setting :"+cacheName+ PropertyFiles.TIME_UNIT_KEY+"="+timeUnit+","+cacheName+ PropertyFiles.TIME_VALUE_KEY+"="+timeValue);
	}

	public int getTimeValue() {
		return timeValue;
	}

	public void setTimeValue(int timeValue) {
		this.timeValue = timeValue;
	}

	public TimeUnit getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
	}
	
	public long getConvertedTime(){
		return TimeUnit.MILLISECONDS.convert(timeValue, timeUnit);
	}

}
