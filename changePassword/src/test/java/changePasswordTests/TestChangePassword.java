package changePasswordTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import changePasswordCore.ChangePassword;

public class TestChangePassword extends BaseTest{
	@Test
    public void TC01CheckValidPassword() {
		ChangePassword cp = new ChangePassword();
		Assert.assertTrue(cp.changePassword("Abcd@123", "abcdefgghijklWW@#123"),"Password should have been changed successfully");
    }
}
