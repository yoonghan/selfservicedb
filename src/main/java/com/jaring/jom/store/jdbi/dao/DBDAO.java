package com.jaring.jom.store.jdbi.dao;

public enum DBDAO {
	INSTANCE;
	
	final UserDAO user = new UserDAO();
	
	public UserDAO getUser(){
		return user;
	}
}
