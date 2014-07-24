package com.jaring.jom.store.jdbi.caches;

public enum DBCache {
	INSTANCE;
	
	final CategoryCache catCache            = new CategoryCache();
	final EnumCountryCache enumCountry      = new EnumCountryCache();
	final EnumRatingCache enumRating        = new EnumRatingCache();
	final ImageCache image                  = new ImageCache();
	final ImageCategoryCache  imageCategory = new ImageCategoryCache();
	final ImageCounterCache    imageCounter = new ImageCounterCache();
	final ImageTagCache imageTag            = new ImageTagCache();
	final MenuCache menu                    = new MenuCache();
	final MenuListCache menuList            = new MenuListCache();
	final TagCache tag                      = new TagCache();
	final UserCache user                    = new UserCache();
	
	DBCache(){
		//load all.
	}
	
	public CategoryCache getCategory(){
		return catCache;
	}
	
	public EnumCountryCache getEnumCountry(){
		return enumCountry;
	}
	
	public EnumRatingCache getEnumRating(){
		return enumRating;
	}
	
	public ImageCache getImage(){
		return image;
	}
	
	public ImageCategoryCache getImageCategory(){
		return imageCategory;
	}
	
	public ImageCounterCache getImageCounter(){
		return imageCounter;
	}
	
	public ImageTagCache getImageTag(){
		return imageTag;
	}

	public MenuCache getMenu(){
		return menu;
	}
	
	public MenuListCache getMenuList(){
		return menuList;
	}
	
	public TagCache getTag(){
		return tag;
	}
	
	public UserCache getUser(){
		return user;
	}
}
