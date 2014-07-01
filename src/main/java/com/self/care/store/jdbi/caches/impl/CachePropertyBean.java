package com.self.care.store.jdbi.caches.impl;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.self.care.store.jdbi.impl.JDBISetting;
import com.self.care.store.jdbi.impl.PropertyFiles;
import com.self.service.logging.log.LogUtil;
import com.self.service.util.impl.PropertyMapperImpl;

class CachePropertyBean implements PropertyMapperImpl {

	private final String CLASS_LOCATION = CachePropertyBean.class.getName();
	
	private final String CACHE_NAME;

	private int timeValue = JDBISetting.TIME_DEFAULT_VALUE;
	private TimeUnit timeUnit = JDBISetting.TIME_DEFAULT_UNIT;
	
	public CachePropertyBean(String cacheName){
		CACHE_NAME=cacheName;
	}
	
	public void map(Properties property) throws IllegalAccessException {
		String timeUnit = property.getProperty(CACHE_NAME + PropertyFiles.TIME_UNIT_KEY);
		String timeValue = property.getProperty(CACHE_NAME + PropertyFiles.TIME_VALUE_KEY);
		try{
			int time=Integer.parseInt(timeValue, 10);
			setTimeValue(time);
		}catch(Exception e){
			LogUtil.getInstance(CLASS_LOCATION).warn("Reset "+CACHE_NAME+", Invalid time setting:"+timeValue);
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
				LogUtil.getInstance(CLASS_LOCATION).warn("Reset "+CACHE_NAME+", Invalid time unit:"+timeUnit);
			}
		}else{
			LogUtil.getInstance(CLASS_LOCATION).warn("Reset "+CACHE_NAME+", Invalid time unit not set");
		}
		
		LogUtil.getInstance(CLASS_LOCATION).info("Setting :"+CACHE_NAME+ PropertyFiles.TIME_UNIT_KEY+"="+timeUnit+","+CACHE_NAME+ PropertyFiles.TIME_VALUE_KEY+"="+timeValue);
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
		return TimeUnit.MILLISECONDS.convert(getTimeValue(), getTimeUnit());
	}

}