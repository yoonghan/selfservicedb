package com.jaring.jom.store.jdbi.entity.immutable;

import com.jaring.jom.store.jdbi.caches.impl.Immutable;
import com.jaring.jom.store.jdbi.entity.CategoryBean;

public class ImmutableCategoryBean implements Immutable<CategoryBean>{
	private String categoryId;
	private String name;
	private String description;
	private Integer counter;
	
	public ImmutableCategoryBean(
			String categoryId,
			String name,
			String description,
			Integer counter){
		this.categoryId = categoryId;
		this.name = name;
		this.description = description;
		this.counter = counter;
	}
	
	public String getCategoryId() {
		return categoryId;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Integer getCounter() {
		return counter;
	}

	public CategoryBean clone(){
		CategoryBean catBean = new CategoryBean();
		catBean.setCategoryId(categoryId);
		catBean.setCounter(counter);
		catBean.setDescription(description);
		catBean.setName(name);
		return catBean;
	}
}
