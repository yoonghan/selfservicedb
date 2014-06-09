package com.self.care.store.jdbi.caches.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.self.care.store.jdbi.impl.JDBISetting;
import com.self.service.util.log.LogUtil;

class CachePropertyReader {

	private final String PROPERTY_FILENAME="/selfservicecaching.properties";
	private final String CLASS_LOCATION="com.self.care.store.jdbi.caches.impl.CachePropertyReader";
	
	private int timeValue = JDBISetting.TIME_DEFAULT_VALUE;
	private TimeUnit timeUnit = JDBISetting.TIME_DEFAULT_UNIT;
	
	private final String TIME_UNIT_KEY = ".timeUnit";
	private final String TIME_VALUE_KEY = ".timeValue";
	
	public CachePropertyReader(String cacheName){
		initProperties(cacheName);
	}
	
	private InputStream loadFile() throws ClassNotFoundException, FileNotFoundException{
		InputStream input = Class.forName(
				CLASS_LOCATION)
				.getClassLoader().getResourceAsStream(
						PROPERTY_FILENAME);
		if (input == null)
			input = Class.forName(
					CLASS_LOCATION).getClass()
					.getResourceAsStream(PROPERTY_FILENAME);
		if (input == null)
			input = new java.io.FileInputStream("."+PROPERTY_FILENAME);
		return input;
	}
	
	private void initProperties(String cacheName) {
		Properties prop = new Properties();
		try {
			prop.load(loadFile());
			loadPropertiesToVariable(prop, cacheName);
		} catch (IOException | ClassNotFoundException e) {
			LogUtil.getInstance(CLASS_LOCATION).warn("Load property error, loading default values:"+e.getMessage());
			e.printStackTrace();
			setTimeValue(JDBISetting.TIME_DEFAULT_VALUE);
			setTimeUnit(JDBISetting.TIME_DEFAULT_UNIT);		
		}
	}
	
	private void loadPropertiesToVariable(Properties prop, String cacheName){
		String timeUnit = prop.getProperty(cacheName + TIME_UNIT_KEY);
		String timeValue = prop.getProperty(cacheName + TIME_VALUE_KEY);
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
		
		LogUtil.getInstance(CLASS_LOCATION).info("Setting :"+cacheName+TIME_UNIT_KEY+"="+timeUnit+","+cacheName+TIME_VALUE_KEY+"="+timeValue);
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
