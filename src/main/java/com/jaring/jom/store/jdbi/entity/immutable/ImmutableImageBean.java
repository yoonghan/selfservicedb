package com.jaring.jom.store.jdbi.entity.immutable;

import com.jaring.jom.store.jdbi.caches.impl.Immutable;
import com.jaring.jom.store.jdbi.entity.ImageBean;

public class ImmutableImageBean implements Immutable<ImageBean> {
	
	private final String imageId;
	private final String name;
	private final String location;
	private final String exposure;
	private final String settings;
	private final String tools;
	private final String metaDate;
	private final String description;
	private final String URI;
	
	public ImmutableImageBean(){
		imageId = null;
		name = null;
		location = null;
		exposure = null;
		settings = null;
		tools = null;
		metaDate = null;
		description = null;
		URI = null;
	}
	
	public ImmutableImageBean(
			String imageId,
			String name,
			String location,
			String exposure,
			String settings,
			String tools,
			String metaDate,
			String description,
			String URI){
		this.imageId = imageId;
		this.name = name;
		this.location = location;
		this.exposure = exposure;
		this.settings = settings;
		this.tools = tools;
		this.metaDate = metaDate;
		this.description = description;
		this.URI = URI;
	}
	
	public String getImageId() {
		return this.imageId;
	}

	public String getName() {
		return this.name;
	}

	public String getLocation() {
		return this.location;
	}

	public String getExposure() {
		return this.exposure;
	}

	public String getSettings() {
		return this.settings;
	}

	public String getTools() {
		return this.tools;
	}

	public String getMetaDate() {
		return this.metaDate;
	}

	public String getDescription() {
		return this.description;
	}
	
	public String getURI(){
		return this.URI;
	}
	
	public ImageBean clone(){
		ImageBean imageBean = new ImageBean();
		imageBean.setImageId(imageId);
		imageBean.setName(name);
		imageBean.setLocation(location);
		imageBean.setExposure(exposure);
		imageBean.setSettings(settings);
		imageBean.setTools(tools);
		imageBean.setMetaDate(metaDate);
		imageBean.setDescription(description);
		imageBean.setURI(URI);
		return imageBean;
	}
}
