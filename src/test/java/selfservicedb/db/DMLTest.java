package selfservicedb.db;

import java.util.concurrent.ExecutionException;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import com.jaring.jom.store.jdbi.caches.DBCache;
import com.jaring.jom.store.jdbi.dao.DBDAO;
import com.jaring.jom.store.jdbi.entity.UserBean;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableUserBean;


public class DMLTest {
	
	private final String USER_VALUE = "test";
	private final String USER_VALUE_2 = "test2";

	@Test
	public void userInsertGoogleFacebook() throws ExecutionException{
		
		UserBean userBean = new UserBean();
		userBean.setEmail(USER_VALUE);
		userBean.setGoogleAuthId(USER_VALUE);
		userBean.setGoogleLink(USER_VALUE);
		userBean.setGooglePicture(USER_VALUE);
		userBean.setIdentity(USER_VALUE);
		userBean.setName(USER_VALUE);
		userBean.setTypeMap(-1);
		
		long userId = DBDAO.INSTANCE.getUser().insertUser(userBean);
	
		//update user with facebook
		userBean.setUserId(userId);
		userBean.setEmail(USER_VALUE_2);
		userBean.setFacebookAuthId(USER_VALUE_2);
		userBean.setFacebookLink(USER_VALUE_2);
		userBean.setFacebookEmail(USER_VALUE_2);
		userBean.setFacebookGender(USER_VALUE_2);
		userBean.setIdentity(USER_VALUE_2);
		userBean.setName(USER_VALUE_2);
		userBean.setStatus((short)1);
		userBean.setTypeMap(-1);
		
		DBDAO.INSTANCE.getUser().updateOrInsertUser(userBean);
		
		ImmutableUserBean userBeanUpdated = DBCache.INSTANCE.getUser().getValue(""+userId);
		Assert.assertEquals(userBeanUpdated.getEmail(),userBean.getEmail());
		
		UserBean googleUpdated = DBDAO.INSTANCE.getUser().getUserViaGoogle(userBean.getGoogleAuthId()).get();
		Assert.assertEquals(googleUpdated.getGoogleAuthId(),userBean.getGoogleAuthId());
		
		UserBean facebookUpdated = DBDAO.INSTANCE.getUser().getUserViaFacebook(userBean.getFacebookAuthId()).get();
		Assert.assertEquals(facebookUpdated.getFacebookAuthId(),userBean.getFacebookAuthId());
		
		DBDAO.INSTANCE.getUser().actualUserDelete(userId);
	}
	
	@Ignore
	@Test
	public void userUpdate2(){
		
		//update user with google and facebook. In real life we only have either facebook/google.
		UserBean userBean = new UserBean();
		userBean.setEmail(USER_VALUE);
		userBean.setGoogleAuthId(USER_VALUE);
		userBean.setGoogleLink(USER_VALUE);
		userBean.setGooglePicture(USER_VALUE);
		userBean.setFacebookAuthId(USER_VALUE_2);
		userBean.setFacebookLink(USER_VALUE_2);
		userBean.setFacebookEmail(USER_VALUE_2);
		userBean.setFacebookGender(USER_VALUE_2);
		userBean.setIdentity(USER_VALUE);
		userBean.setName(USER_VALUE);
		userBean.setStatus((short)1);
		userBean.setTypeMap(-1);
		
		long userId = DBDAO.INSTANCE.getUser().updateOrInsertUser(userBean);

		DBDAO.INSTANCE.getUser().actualUserDelete(userId);
		
		Assert.assertFalse(userId == -1); // make sure there is no insert.
	}

}
