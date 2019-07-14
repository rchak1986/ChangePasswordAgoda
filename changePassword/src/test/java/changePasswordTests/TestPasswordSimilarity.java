package changePasswordTests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import changePasswordCore.ChangePassword;

public class TestPasswordSimilarity {
	@Test
    public void TC17TestLessThan80PSimilarity() {
		ChangePassword cp = new ChangePassword();
		cp.setOldPassword("ABCDefgh@#$1234567");
		Assert.assertTrue(cp.changePassword("ABCDefgh@#$1234567", "ABCDefgh&*!9876567"),"Password should have been changed successfully - Less than similarity factor");
		Reporter.log("Test Data used: " + "ABCDefgh&*!9876567");
	}
	
	@Test
    public void TC18Test80PSimilarity() {
		ChangePassword cp = new ChangePassword();
		cp.setOldPassword("ABCDefgh@#$1234567");
		Assert.assertFalse(cp.changePassword("ABCDefgh@#$1234567", "ABCDefgh@#$1234909"),"Password should NOT have been changed successfully - equal to similarity factor");
		Reporter.log("Test Data used: " + "ABCDefgh@#$1234909");
	}
	
	@Test
    public void TC19TestMoreThan80PSimilarity() {
		ChangePassword cp = new ChangePassword();
		cp.setOldPassword("ABCDefgh@#$1234567");
		Assert.assertFalse(cp.changePassword("ABCDefgh@#$1234567", "ABCDefgh@#$1234990"),"Password should NOT have been changed successfully - more than similarity factor");
		Reporter.log("Test Data used: " + "ABCDefgh@#$1234990");
	}
	
	@Test
    public void TC20NewPasswordNull() {
		ChangePassword cp = new ChangePassword();
		cp.setOldPassword("ABCDefgh@#$1234567");
		Assert.assertFalse(cp.changePassword("ABCDefgh@#$1234567", null),"Password should NOT have been changed successfully - new password null");
		Reporter.log("Test Data used: " + "Null");
	}
}
