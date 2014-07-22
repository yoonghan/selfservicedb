
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
import com.self.care.store.jdbi.entity.immutable.ImmutableEnumCountryBean;
import com.self.care.store.jdbi.entity.immutable.ImmutableEnumRatingBean;
import com.self.care.store.jdbi.entity.immutable.ImmutableImageBean;
import com.self.care.store.jdbi.entity.immutable.ImmutableImageList;
import com.self.care.store.jdbi.entity.immutable.ImmutableInteger;
import com.self.care.store.jdbi.entity.immutable.ImmutableMenuBean;
import com.self.care.store.jdbi.entity.immutable.ImmutableMenuList;
import com.self.care.store.jdbi.entity.immutable.ImmutableShort;
import com.self.care.store.jdbi.entity.immutable.ImmutableString;
import com.self.care.store.jdbi.entity.immutable.ImmutableUserBean;

public class DatabaseTest{
	
	/**
	 * Make sure the is no logging reappearing for caching again.
	 */
	public static void main(String[] args){
		try {
			System.out.println("***START---");
			ImmutableEnumCountryBean value = EnumCountryCache.getInstance().getValue("2");
			System.out.println("country:"+value.getCountry() + ", state:"+value.getState());
			value = EnumCountryCache.getInstance().getValue("1");
			System.out.println("country:"+value.getCountry() + ", state:"+value.getState());
			value = EnumCountryCache.getInstance().getValue("2");
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
			ImmutableEnumCountryBean ecb = EnumCountryCache.getInstance().getValue(DEFAULT_KEY_VALUE);
			Assert.assertEquals(TEST_STRING_VALUE, ecb.getCountry());
			
			ImmutableEnumRatingBean erb = EnumRatingCache.getInstance().getValue(DEFAULT_KEY_VALUE);
			Assert.assertEquals(TEST_INT_VALUE, erb.getRating());
			
			ImmutableString cat = CategoryCache.getInstance().getValue(DEFAULT_KEY_VALUE);
			Assert.assertEquals("", cat.getString());
			
			ImmutableShort tag = TagCache.getInstance().getValue(DEFAULT_KEY_VALUE);
			Assert.assertEquals(TEST_SHORT_VALUE, tag.getShort());
			
			ImmutableUserBean ub = UserCache.getInstance().getValue(DEFAULT_KEY_VALUE);
			Assert.assertEquals(TEST_STRING_VALUE, ub.getIdentity());
			
			ImmutableImageBean ib = ImageCache.getInstance().getValue(DEFAULT_KEY_VALUE);
			Assert.assertEquals(TEST_STRING_VALUE, ib.getName());
			
			ImmutableImageList lib = ImageCategoryCache.getInstance().getValue(DEFAULT_KEY_VALUE);
			Assert.assertEquals(1, lib.getArrayObject().size());
			if(lib.getArrayObject().size()==1)
				Assert.assertEquals(TEST_STRING_VALUE, lib.getArrayObject().get(0).getName());
			
			ImmutableInteger it = ImageCounterCache.getInstance().getValue(DEFAULT_KEY_VALUE);
			Assert.assertEquals(TEST_INT_VALUE, it.getInteger());
			
			ImmutableImageList lib2 =ImageTagCache.getInstance().getValue(DEFAULT_KEY_VALUE);
			Assert.assertEquals(1, lib2.getArrayObject().size());
			if(lib2.getArrayObject().size()==1)
				Assert.assertEquals(TEST_STRING_VALUE, lib.getArrayObject().get(0).getName());
			
			ImmutableMenuBean menu = MenuCache.getInstance().getValue(DEFAULT_KEY_VALUE);
			Assert.assertEquals(TEST_STRING_VALUE, menu.getTextDisplay());
			
			ImmutableMenuList menuList = MenuListCache.getInstance().getValue(DEFAULT_KEY_VALUE);
			Assert.assertEquals(1, menuList.getArrayObject().size());
			if(menuList.getArrayObject().size()==1)
				Assert.assertEquals(TEST_STRING_VALUE, menuList.getArrayObject().get(0).getMenu().getTextDisplay());
			
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
			
			ImmutableUserBean userBean = UserCache.getInstance().getValue("-2");
			Assert.assertTrue(userBean != null);
			
			ImmutableMenuBean mbean = MenuCache.getInstance().getValue("-1");
			MenuCache.getInstance().refreshCache();
			ImmutableMenuBean mbean2 = MenuCache.getInstance().getValue("-1");
			Assert.assertFalse(mbean==mbean2);
			
			ImmutableMenuList mbean3 = MenuListCache.getInstance().getValue("-1");
			MenuListCache.getInstance().refreshCache();
			ImmutableMenuList mbean4 = MenuListCache.getInstance().getValue("-1");
			Assert.assertFalse(mbean3==mbean4);
			
			
			mbean = MenuCache.getInstance().getValue("-1");
			mbean2 = MenuCache.getInstance().getValue("-1");
			Assert.assertTrue(mbean==mbean2);
			
			mbean3 = MenuListCache.getInstance().getValue("-1");
			mbean4 = MenuListCache.getInstance().getValue("-1");
			Assert.assertTrue(mbean3==mbean4);
			
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
	}
	
}
