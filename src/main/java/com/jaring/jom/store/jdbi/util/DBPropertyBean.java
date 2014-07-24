package com.jaring.jom.store.jdbi.util;

import java.util.Properties;

import com.jaring.jom.logging.impl.Log;
import com.jaring.jom.logging.log.LogFactory;
import com.jaring.jom.store.jdbi.impl.PropertyFiles;
import com.jaring.jom.util.impl.PropertyMapperImpl;

class DBPropertyBean implements PropertyMapperImpl {

	private final Log log = LogFactory.getLogger(this.getClass().getName());
	
	private EnumConnectionType connectionType;
	private String connectionURL;
	private String connectionUserName;
	private String connectionPassword;
	private String contextLookup;
	
	private final String CONN_NAME;
	
	public DBPropertyBean(String connName){
		CONN_NAME=connName;
	}
	
	
	@Override
	public void map(Properties property) throws IllegalAccessException {
		int connectionType = 1;
		try{
			connectionType = Integer.parseInt(property.getProperty(CONN_NAME+PropertyFiles.CONN_TYPE),10);
		}catch(NumberFormatException nfe){
			log.error("Parsing wrong connection"+property.getProperty(CONN_NAME+".conn.type"));
		}
		this.setConnectionType(EnumConnectionType.valueOf(connectionType));
		this.setConnectionURL(property.getProperty(CONN_NAME+PropertyFiles.CONN_URL));
		this.setConnectionUserName(property.getProperty(CONN_NAME+PropertyFiles.CONN_USERNAME));
		this.setConnectionPassword(property.getProperty(CONN_NAME+PropertyFiles.CONN_PASSWORD));
		this.setContextLookup(property.getProperty(CONN_NAME+PropertyFiles.CONN_LOOKUP));	
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
