package changePasswordTests;

import org.testng.annotations.*;
import changePasswordCore.ChangePassword;

public class BaseTest {
	@BeforeTest
	public void setupTest(){
		ChangePassword cp = new ChangePassword();
		cp.setOldPassword("Abcd@123");
	}
}
