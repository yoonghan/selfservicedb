package selfservicedb.bean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

import com.jaring.jom.store.jdbi.entity.CategoryBean;
import com.jaring.jom.store.jdbi.entity.EnumCountryBean;
import com.jaring.jom.store.jdbi.entity.EnumRatingBean;
import com.jaring.jom.store.jdbi.entity.ImageBean;
import com.jaring.jom.store.jdbi.entity.MenuBean;
import com.jaring.jom.store.jdbi.entity.MenuListBean;
import com.jaring.jom.store.jdbi.entity.UserBean;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableEnumCountryBean;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableEnumRatingBean;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableImageBean;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableImageList;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableMenuBean;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableMenuList;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableMenuListBean;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableUserBean;


public class ValidateBean {
	/**
	 * This test is to carry out for recursive loops in getter setter. 
	 * A very frequent error.
	 */
	@Test
	public void testAllImmutableBean(){
		invokeMethod(new ImmutableEnumCountryBean());
		invokeMethod(new ImmutableEnumRatingBean());
		invokeMethod(new ImmutableImageBean());
		invokeMethod(new ImmutableImageList());
		invokeMethod(new ImmutableMenuBean());
		invokeMethod(new ImmutableMenuList());
		invokeMethod(new ImmutableMenuListBean());
		invokeMethod(new ImmutableUserBean());
		invokeMethod(new CategoryBean());
		invokeMethod(new EnumCountryBean());
		invokeMethod(new EnumRatingBean());
		invokeMethod(new ImageBean());
		invokeMethod(new MenuBean());
		invokeMethod(new MenuListBean());
		invokeMethod(new UserBean());
	}
	
	
	public void invokeMethod(Object bean){
		Method[] methods = bean.getClass().getMethods(); //all the getter methods.
		methods = filterName(methods);
		
		//all immutableBeans have to be invoked, to make sure there is no recursive loop
		try {
			for(Method method: methods){
				method.invoke(bean);
			}
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	private Method[] filterName(Method[] methods) {
		ArrayList<Method> filteredMethod = new ArrayList<Method>(methods.length);
		Method[] returnFilteredMethod = new Method[0];
		
		for(Method method: methods){
			if(method.getName().startsWith("get")
					&& method.getGenericParameterTypes().length == 0){
				filteredMethod.add(method);
			}
		}
		
		return filteredMethod.toArray(returnFilteredMethod);
	}
}
