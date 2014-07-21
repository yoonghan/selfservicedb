import java.util.concurrent.ExecutionException;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import com.self.care.store.jdbi.caches.UserCache;
import com.self.care.store.jdbi.dao.UserDAO;
import com.self.care.store.jdbi.entity.UserBean;


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
		
		long userId = UserDAO.getInstance().insertUser(userBean);
	
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
		
		UserDAO.getInstance().updateOrInsertUser(userBean);
		
		UserBean userBeanUpdated = UserCache.getInstance().getValue(""+userId,false);
		Assert.assertEquals(userBeanUpdated.getEmail(),userBean.getEmail());
		
		UserBean googleUpdated = UserDAO.getInstance().getUserViaGoogle(userBean.getGoogleAuthId());
		Assert.assertEquals(googleUpdated.getGoogleAuthId(),userBean.getGoogleAuthId());
		
		UserBean facebookUpdated = UserDAO.getInstance().getUserViaFacebook(userBean.getFacebookAuthId());
		Assert.assertEquals(facebookUpdated.getFacebookAuthId(),userBean.getFacebookAuthId());
		
		UserDAO.getInstance().actualUserDelete(userId);
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
		
		long userId = UserDAO.getInstance().updateOrInsertUser(userBean);

		UserDAO.getInstance().actualUserDelete(userId);
		
		Assert.assertFalse(userId == -1); // make sure there is no insert.
	}

}
