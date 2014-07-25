package com.jaring.jom.store.jdbi.entity;

import com.jaring.jom.store.jdbi.caches.impl.ImmutableMapper;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableCategoryBean;

public class CategoryBean implements ImmutableMapper<ImmutableCategoryBean>{
	private String categoryId;
	private String name;
	private String description;
	private Integer counter;
	private Short enumStatusId;
	
	public String getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getCounter() {
		return counter;
	}
	
	public void setCounter(Integer counter) {
		this.counter = counter;
	}
	
	public Short getEnumStatusId() {
		return enumStatusId;
	}
	
	public void setEnumStatusId(Short enumStatusId) {
		this.enumStatusId = enumStatusId;
	}
	
	public ImmutableCategoryBean mapper() {
		return new ImmutableCategoryBean(categoryId, name, description, counter);
	}
}
