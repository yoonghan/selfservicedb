package com.self.care.store.jdbi.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.self.service.util.log.LogUtil;

class DBPropertyReader {

	private final String PROPERTY_FILENAME="/selfservicedb.properties";
	private final String CLASS_LOCATION="com.self.care.store.jdbi.util.DBPropertyReader";
	
	private EnumConnectionType connectionType;
	private String connectionURL;
	private String connectionUserName;
	private String connectionPassword;
	private String contextLookup;
	
	public DBPropertyReader(String connName){
		initProperties(connName);
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
	
	private void initProperties(String connName) {
		Properties prop = new Properties();
		try {
			prop.load(loadFile());
			loadPropertiesToVariable(prop, connName);
		} catch (IOException | ClassNotFoundException e) {
			LogUtil.getInstance(CLASS_LOCATION).error("Load property error", e);
		}
	}
	
	private void loadPropertiesToVariable(Properties prop, String connName){
		int connectionType = 1;
		try{
			connectionType = Integer.parseInt(prop.getProperty(connName+".conn.type"),10);
		}catch(NumberFormatException nfe){
			LogUtil.getInstance(CLASS_LOCATION).error("Parsing wrong connection"+prop.getProperty(connName+".conn.type"));
		}
		this.setConnectionType(EnumConnectionType.valueOf(connectionType));
		this.setConnectionURL(prop.getProperty(connName+".conn.url"));
		this.setConnectionUserName(prop.getProperty(connName+".conn.username"));
		this.setConnectionPassword(prop.getProperty(connName+".conn.password"));
		this.setContextLookup(prop.getProperty(connName+".conn.lookup"));	
	}

	public EnumConnectionType getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(EnumConnectionType connectionType) {
		this.connectionType = connectionType;
	}

	public String getConnectionURL() {
		return connectionURL;
	}

	public void setConnectionURL(String connectionURL) {
		this.connectionURL = connectionURL;
	}

	public String getConnectionUserName() {
		return connectionUserName;
	}

	public void setConnectionUserName(String connectionUserName) {
		this.connectionUserName = connectionUserName;
	}

	public String getConnectionPassword() {
		return connectionPassword;
	}

	public void setConnectionPassword(String connectionPassword) {
		this.connectionPassword = connectionPassword;
	}

	public String getContextLookup() {
		return contextLookup;
	}

	public void setContextLookup(String contextLookup) {
		this.contextLookup = contextLookup;
	}
}
