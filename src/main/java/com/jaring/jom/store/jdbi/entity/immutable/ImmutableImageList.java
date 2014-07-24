package com.jaring.jom.store.jdbi.entity.immutable;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.jaring.jom.store.jdbi.caches.impl.Immutable;
import com.jaring.jom.store.jdbi.entity.ImageBean;

public class ImmutableImageList implements Immutable<List<ImageBean>> {
	public final ImmutableList<ImmutableImageBean> listArray;
	
	public ImmutableImageList(){
		this.listArray = ImmutableList.copyOf(new ArrayList<ImmutableImageBean>(0));
	}
	
	public ImmutableImageList(List<ImageBean> imageBeanList){
		
		List<ImmutableImageBean> tmpList = new ArrayList<ImmutableImageBean>(imageBeanList.size());
		for(ImageBean imageBean: imageBeanList){
			tmpList.add(new ImmutableImageBean(
					imageBean.getImageId(),
					imageBean.getName(),
					imageBean.getLocation(),
					imageBean.getExposure(),
					imageBean.getSettings(),
					imageBean.getTools(),
					imageBean.getMetaDate(),
					imageBean.getDescription(),
					imageBean.getURI()
					));
		}
		
		ImmutableList<ImmutableImageBean> immutableArray = ImmutableList.copyOf(tmpList);
		
		
		this.listArray = immutableArray;
	}
	
	public ImmutableList<ImmutableImageBean> getArrayObject(){
		return this.listArray;
	}
	
	public List<ImageBean> clone(){
		List<ImageBean> imageBeanList = new ArrayList<ImageBean>(listArray.size());
		
		for(ImmutableImageBean immImg: listArray){
			imageBeanList.add(immImg.clone());
		}
		return imageBeanList;
	}
}
