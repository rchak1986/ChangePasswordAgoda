package changePasswordTests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import changePasswordCore.ChangePassword;

public class TestInvalidScenarios extends BaseTest {
	@Test
    public void TC06LessThanMinChar() {
		ChangePassword cp = new ChangePassword();
		Assert.assertFalse(cp.changePassword("Abcd@123", "Abxres@123"),"Password should NOT have been changed successfully - Less than min char");
		Reporter.log("Test Data used: " + "Abxres@123");
	}
	
	@Test
    public void TC07SpecialCharOutOfAllowedRange() {
		ChangePassword cp = new ChangePassword();
		Assert.assertFalse(cp.changePassword("Abcd@123", "Absdewet%^()<>1234"),"Password should NOT have been changed successfully - no minimum in range special characters");
		Reporter.log("Test Data used: " + "Absdewet%^()<>1234");
	}
	
	@Test
    public void TC08AllCaptialLetters() {
		ChangePassword cp = new ChangePassword();
		Assert.assertFalse(cp.changePassword("Abcd@123", "ABCDEFGEWS@!#12341"),"Password should NOT have been changed successfully - no minimum lower case characters");
		Reporter.log("Test Data used: " + "ABCDEFGEWS@!#12341");
	}
	
	@Test
    public void TC09AllLowerCaseLetters() {
		ChangePassword cp = new ChangePassword();
		Assert.assertFalse(cp.changePassword("Abcd@123", "abcdefdswa@!#12341"),"Password should NOT have been changed successfully - no minimum upper case characters");
		Reporter.log("Test Data used: " + "abcdefdswa@!#12341");
	}
	
	@Test
    public void TC10SpecialCharsBeyondMaxRange() {
		ChangePassword cp = new ChangePassword();
		Assert.assertFalse(cp.changePassword("Abcd@123", "AbxdjkwesQ!#$&*213"),"Password should NOT have been changed successfully - special character counts beyond allowed range");
		Reporter.log("Test Data used: " + "AbxdjkwesQ!#$&*213");
	}
	
	@Test
    public void TC11DigitsBeyondMaxCount() {
		ChangePassword cp = new ChangePassword();
		Assert.assertFalse(cp.changePassword("Abcd@123", "1234567890@&$asert"),"Password should NOT have been changed successfully - Number of digit is more than the max limit");
		Reporter.log("Test Data used: " + "1234567890@&$asert");
	}
	
	@Test
    public void TC12DuplicatesBeyondMaxCount() {
		ChangePassword cp = new ChangePassword();
		Assert.assertFalse(cp.changePassword("Abcd@123", "AjakAewAsjkqa!#$12"),"Password should NOT have been changed successfully - Number of duplicates is more than the max limit");
		Reporter.log("Test Data used: " + "AjakAewAsjkqa!#$12");
	}
	
	@Test
    public void TC13NoNumbers() {
		ChangePassword cp = new ChangePassword();
		Assert.assertFalse(cp.changePassword("Abcd@123", "AbcdEfgHIjkl!@#mno"),"Password should NOT have been changed successfully - Count of minimum digits is not met");
		Reporter.log("Test Data used: " + "AbcdEfgHIjkl!@#mno");
	}
	
	@Test
    public void TC14NoAlphabets() {
		ChangePassword cp = new ChangePassword();
		Assert.assertFalse(cp.changePassword("Abcd@123", "123456789011!@#123"),"Password should NOT have been changed successfully - Count of minimum alphabets is not met");
		Reporter.log("Test Data used: " + "123456789011!@#123");
	}
	
	@Test
    public void TC15NoSpecialCharacters() {
		ChangePassword cp = new ChangePassword();
		Assert.assertFalse(cp.changePassword("Abcd@123", "AbcdEFGijklmnoPQR1"),"Password should NOT have been changed successfully - Count of minimum special characters is not met");
		Reporter.log("Test Data used: " + "AbcdEFGijklmnoPQR1");
	}
	
	@Test
    public void TC16OldPasswordNotMatched() {
		ChangePassword cp = new ChangePassword();
		Assert.assertFalse(cp.changePassword("Xyzser@123", "123456789!@#$xbXexdeX"),"Password should NOT have been changed successfully - old password not matched");
		Reporter.log("Test Data used: " + "123456789!@#$xbXexdeX");
	}
}
