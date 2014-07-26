package selfservicedb.db;

import java.util.List;
import java.util.concurrent.ExecutionException;

import junit.framework.Assert;

import org.junit.Test;

import com.jaring.jom.store.jdbi.caches.DBCache;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableCategoryBean;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableCustomList;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableEnumCountryBean;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableEnumRatingBean;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableImageBean;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableInteger;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableMenuBean;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableMenuListBean;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableShort;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableString;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableUserBean;

public class DatabaseQueryTest{
	
	/**
	 * Make sure the is no logging reappearing for caching again.
	 */
	public static void main(String[] args){
		try {
			System.out.println("***START---");
			ImmutableEnumCountryBean value = DBCache.INSTANCE.getEnumCountry().getValue("2");
			System.out.println("country:"+value.getCountry() + ", state:"+value.getState());
			value = DBCache.INSTANCE.getEnumCountry().getValue("1");
			System.out.println("country:"+value.getCountry() + ", state:"+value.getState());
			value = DBCache.INSTANCE.getEnumCountry().getValue("2");
			System.out.println("country:"+value.getCountry() + ", state:"+value.getState());
			System.out.println("***END---");
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test all single connections.
	 */
	@Test
	public void testFindSingleValueConnections(){
		
		final String TEST_STRING_VALUE="TEST";
		final Integer TEST_INT_VALUE=new Integer(1);
		final Short TEST_SHORT_VALUE=new Short((short)1);
		final String DEFAULT_KEY_VALUE="-1";
		
		try {
			ImmutableEnumCountryBean ecb = DBCache.INSTANCE.getEnumCountry().getValue(DEFAULT_KEY_VALUE);
			Assert.assertEquals(TEST_STRING_VALUE, ecb.getCountry());
			
			ImmutableEnumRatingBean erb = DBCache.INSTANCE.getEnumRating().getValue(DEFAULT_KEY_VALUE);
			Assert.assertEquals(TEST_INT_VALUE, erb.getRating());
			
			ImmutableString cat = DBCache.INSTANCE.getCategory().getValue(DEFAULT_KEY_VALUE);
			Assert.assertEquals("", cat.getString());
			
			ImmutableShort tag = DBCache.INSTANCE.getTag().getValue(DEFAULT_KEY_VALUE);
			Assert.assertEquals(TEST_SHORT_VALUE, tag.getShort());
			
			ImmutableUserBean ub = DBCache.INSTANCE.getUser().getValue(DEFAULT_KEY_VALUE);
			Assert.assertEquals(TEST_STRING_VALUE, ub.getIdentity());
			
			ImmutableImageBean ib = DBCache.INSTANCE.getImage().getValue(DEFAULT_KEY_VALUE);
			Assert.assertEquals(TEST_STRING_VALUE, ib.getName());
			
			ImmutableCustomList<ImmutableImageBean> lib = DBCache.INSTANCE.getImageCategory().getValue(DEFAULT_KEY_VALUE);
			Assert.assertEquals(1, lib.getList().size());
			if(lib.getList().size()==1)
				Assert.assertEquals(TEST_STRING_VALUE, lib.getList().get(0).getName());
			
			ImmutableInteger it = DBCache.INSTANCE.getImageCounter().getValue(DEFAULT_KEY_VALUE);
			Assert.assertEquals(TEST_INT_VALUE, it.getInteger());
			
			ImmutableCustomList<ImmutableImageBean> lib2 = DBCache.INSTANCE.getImageTag().getValue(DEFAULT_KEY_VALUE);
			Assert.assertEquals(1, lib2.getList().size());
			if(lib2.getList().size()==1){
				Assert.assertEquals(TEST_STRING_VALUE, lib2.getList().get(0).getName());
			}
			
			
			ImmutableMenuBean menu = DBCache.INSTANCE.getMenu().getValue(DEFAULT_KEY_VALUE);
			Assert.assertEquals(TEST_STRING_VALUE, menu.getTextDisplay());
			
			ImmutableCustomList<ImmutableMenuListBean> menuList = DBCache.INSTANCE.getMenuList().getValue(DEFAULT_KEY_VALUE);
			Assert.assertEquals(1, menuList.getList().size());
			if(menuList.getList().size()==1)
				Assert.assertEquals(TEST_STRING_VALUE, menuList.getList().get(0).getMenu().getTextDisplay());
			
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test if records to select all.
	 */
	@Test
	public void testFindAllConnections(){
		DBCache.INSTANCE.getCategory().getAll();
		Assert.assertTrue(true);
		
		List<ImmutableString> tags = DBCache.INSTANCE.getTag().getAll();
		Assert.assertTrue(tags.size() >= 1);
		
		List<ImmutableCategoryBean> categories = DBCache.INSTANCE.getCategory().getAll();
		Assert.assertTrue(categories.size() == 0);
	}
	
	/**
	 * Test cache refresh
	 */
	@Test
	public void testRefresh(){
		DBCache.INSTANCE.getEnumCountry().refreshCache();
		DBCache.INSTANCE.getCategory().refreshCache();
		DBCache.INSTANCE.getImageCategory().refreshCache();
		DBCache.INSTANCE.getCategory().clearFindAllCache();
	}
	
	/**
	 * Test cache can be cleared
	 */
	@Test
	public void testInvalidateCache(){
		try {
			
			ImmutableUserBean userBean = DBCache.INSTANCE.getUser().getValue("-2");
			Assert.assertTrue(userBean != null);
			
			ImmutableMenuBean mbean = DBCache.INSTANCE.getMenu().getValue("-1");
			DBCache.INSTANCE.getMenu().refreshCache();
			ImmutableMenuBean mbean2 = DBCache.INSTANCE.getMenu().getValue("-1");
			Assert.assertFalse(mbean==mbean2);
			
			ImmutableCustomList<ImmutableMenuListBean> mbean3 = DBCache.INSTANCE.getMenuList().getValue("-1");
			DBCache.INSTANCE.getMenuList().refreshCache();
			ImmutableCustomList<ImmutableMenuListBean> mbean4 = DBCache.INSTANCE.getMenuList().getValue("-1");
			Assert.assertFalse(mbean3==mbean4);
			
			
			mbean = DBCache.INSTANCE.getMenu().getValue("-1");
			mbean2 = DBCache.INSTANCE.getMenu().getValue("-1");
			Assert.assertTrue(mbean==mbean2);
			
			mbean3 = DBCache.INSTANCE.getMenuList().getValue("-1");
			mbean4 = DBCache.INSTANCE.getMenuList().getValue("-1");
			Assert.assertTrue(mbean3==mbean4);
			
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
	}	
}
