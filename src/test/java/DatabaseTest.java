
import java.util.List;
import java.util.concurrent.ExecutionException;

import junit.framework.Assert;

import org.junit.Test;

import com.self.care.store.jdbi.caches.CategoryCache;
import com.self.care.store.jdbi.caches.EnumCountryCache;
import com.self.care.store.jdbi.caches.EnumRatingCache;
import com.self.care.store.jdbi.caches.ImageCache;
import com.self.care.store.jdbi.caches.ImageCategoryCache;
import com.self.care.store.jdbi.caches.ImageCounterCache;
import com.self.care.store.jdbi.caches.ImageTagCache;
import com.self.care.store.jdbi.caches.MenuCache;
import com.self.care.store.jdbi.caches.MenuListCache;
import com.self.care.store.jdbi.caches.TagCache;
import com.self.care.store.jdbi.caches.UserCache;
import com.self.care.store.jdbi.entity.EnumCountryBean;
import com.self.care.store.jdbi.entity.EnumRatingBean;
import com.self.care.store.jdbi.entity.ImageBean;
import com.self.care.store.jdbi.entity.MenuBean;
import com.self.care.store.jdbi.entity.MenuListBean;
import com.self.care.store.jdbi.entity.UserBean;

public class DatabaseTest{
	
	/**
	 * Make sure the is no logging reappearing for caching again.
	 */
	public static void main(String[] args){
		try {
			System.out.println("***START---");
			EnumCountryBean value = EnumCountryCache.getInstance().getValue("2",false);
			System.out.println("country:"+value.getCountry() + ", state:"+value.getState());
			value = EnumCountryCache.getInstance().getValue("1",false);
			System.out.println("country:"+value.getCountry() + ", state:"+value.getState());
			value = EnumCountryCache.getInstance().getValue("2",false);
			System.out.println("country:"+value.getCountry() + ", state:"+value.getState());
			System.out.println("***END---");
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAllConnections(){
		
		final String TEST_STRING_VALUE="TEST";
		final Integer TEST_INT_VALUE=new Integer(1);
		final Short TEST_SHORT_VALUE=new Short((short)1);
		final String DEFAULT_KEY_VALUE="-1";
		
		try {
			EnumCountryBean ecb = EnumCountryCache.getInstance().getValue(DEFAULT_KEY_VALUE,false);
			Assert.assertEquals(TEST_STRING_VALUE, ecb.getCountry());
			
			EnumRatingBean erb = EnumRatingCache.getInstance().getValue(DEFAULT_KEY_VALUE,false);
			Assert.assertEquals(TEST_INT_VALUE, erb.getRating());
			
			String cat = CategoryCache.getInstance().getValue(DEFAULT_KEY_VALUE,false);
			Assert.assertEquals("", cat);
			
			Short tag = TagCache.getInstance().getValue(DEFAULT_KEY_VALUE,false);
			Assert.assertEquals(TEST_SHORT_VALUE, tag);
			
			UserBean ub = UserCache.getInstance().getValue(DEFAULT_KEY_VALUE,false);
			Assert.assertEquals(TEST_STRING_VALUE, ub.getIdentity());
			
			ImageBean ib = ImageCache.getInstance().getValue(DEFAULT_KEY_VALUE,false);
			Assert.assertEquals(TEST_STRING_VALUE, ib.getName());
			
			List<ImageBean> lib = ImageCategoryCache.getInstance().getValue(DEFAULT_KEY_VALUE,false);
			Assert.assertEquals(1, lib.size());
			if(lib.size()==1)
				Assert.assertEquals(TEST_STRING_VALUE, lib.get(0).getName());
			
			Integer it = ImageCounterCache.getInstance().getValue(DEFAULT_KEY_VALUE,false);
			Assert.assertEquals(TEST_INT_VALUE, it);
			
			List<ImageBean> lib2 =ImageTagCache.getInstance().getValue(DEFAULT_KEY_VALUE,false);
			Assert.assertEquals(1, lib2.size());
			if(lib2.size()==1)
				Assert.assertEquals(TEST_STRING_VALUE, lib.get(0).getName());
			
			MenuBean menu = MenuCache.getInstance().getValue(DEFAULT_KEY_VALUE,false);
			Assert.assertEquals(TEST_STRING_VALUE, menu.getTextDisplay());
			
			List<MenuListBean> menuList = MenuListCache.getInstance().getValue(DEFAULT_KEY_VALUE,false);
			Assert.assertEquals(1, menuList.size());
			if(menuList.size()==1)
				Assert.assertEquals(TEST_STRING_VALUE, menuList.get(0).getMenu().getTextDisplay());
			
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindAllConnections(){
		CategoryCache.getInstance().getAll();
		Assert.assertTrue(true);
		
		List<String> tags = TagCache.getInstance().getAll();
		Assert.assertTrue(tags.size() >= 1);
	}
	
	@Test
	public void testRefresh(){
		EnumCountryCache.getInstance().refreshCache();
		CategoryCache.getInstance().refreshCache();
		ImageCategoryCache.getInstance().refreshCache();
		CategoryCache.getInstance().clearFindAllCache();
	}
	
	@Test
	public void testInvalidateCache(){
		try {
			
			UserBean userBean = UserCache.getInstance().getValue("-2", false);
			Assert.assertTrue(userBean != null);
			
			MenuBean mbean = MenuCache.getInstance().getValue("-1",false);
			MenuCache.getInstance().refreshCache();
			MenuBean mbean2 = MenuCache.getInstance().getValue("-1",false);
			Assert.assertFalse(mbean==mbean2);
			
			List<MenuListBean> mbean3 = MenuListCache.getInstance().getValue("-1",false);
			MenuListCache.getInstance().refreshCache();
			List<MenuListBean> mbean4 = MenuListCache.getInstance().getValue("-1",false);
			Assert.assertFalse(mbean3==mbean4);
			
			
			mbean = MenuCache.getInstance().getValue("-1",false);
			mbean2 = MenuCache.getInstance().getValue("-1",false);
			Assert.assertTrue(mbean==mbean2);
			
			mbean3 = MenuListCache.getInstance().getValue("-1",false);
			mbean4 = MenuListCache.getInstance().getValue("-1",false);
			Assert.assertTrue(mbean3==mbean4);
			
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
	}
	
}
