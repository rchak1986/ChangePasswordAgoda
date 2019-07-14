package changePasswordTests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import changePasswordCore.ChangePassword;

public class TestValidScenarios extends BaseTest {
	@Test
    public void TC01CheckSpecialCharacterRange() {
		ChangePassword cp = new ChangePassword();
		Assert.assertTrue(cp.changePassword("Abcd@123", "AbxdjkwesdQ@!#2389"),"Password should have been changed successfully - Special Characters in Range");
		Reporter.log("Test Data used: " + "AbxdjkwesdQ@!#2389");
    }
	
	@Test
    public void TC02CheckSpecialCharacterCount() {
		ChangePassword cp = new ChangePassword();
		Assert.assertTrue(cp.changePassword("Abcd@123", "AbxdjkwesdQ#$&*213"),"Password should have been changed successfully - Special Characters in Range & matches count");
		Reporter.log("Test Data used: " + "AbxdjkwesdQ#$&*213");
	}
	
	@Test
    public void TC03DuplicateCharactersInRange() {
		ChangePassword cp = new ChangePassword();
		Assert.assertTrue(cp.changePassword("Abcd@123", "AjkaewasjAkqz!#$12"),"Password should have been changed successfully - Duplicate Characters in Range");
		Reporter.log("Test Data used: " + "AjkaewasjAkqz!#$12");
	}
	
	@Test
    public void TC04NumberOfDigitsInRange() {
		ChangePassword cp = new ChangePassword();
		Assert.assertTrue(cp.changePassword("Abcd@123", "aSEtmwasy!@#12345678"),"Password should have been changed successfully - Digits Count in Range");
		Reporter.log("Test Data used: " + "aSEtmwasy!@#12345678");
	}
	
	@Test
    public void TC05AllValidCriteria() {
		ChangePassword cp = new ChangePassword();
		Assert.assertTrue(cp.changePassword("Abcd@123", "123456789!@#$xbXexdeX"),"Password should have been changed successfully - All Valid criterias met");
		Reporter.log("Test Data used: " + "123456789!@#$xbXexdeX");
	}
}
