package changePasswordCore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangePassword {
	private String filePath = System. getProperty("user.dir")+"/src/main/resources/tempPass.properties";
	private String defaultOldPassword = "Abcd@123";
	private String passwordKey="system.password";
	private double similarityFactor = 0.8;
	private String specialCharRange = "!@#$&*";
	private double maxNumbersFactor=2;
	private int maxSpecialCharAllowed = 4;
	private int minLetterAndDigitCount = 1; //minimum 1 lower, 1 upper, 1 special and 1 digit
	private int maxDuplicateChar=4;
	private int minPasswordLength=18;
	
	public void setOldPassword(String oldPassword){
		File f = new File(filePath);
		if (f.exists())f.delete();
		
		try {
			if (!f.getParentFile().exists())f.getParentFile().mkdirs();
			f.createNewFile();
			OutputStream output = new FileOutputStream(filePath);
			Properties prop = new Properties();

	        prop.setProperty(passwordKey, oldPassword);
	        prop.store(output,null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean verifyOldPassword(String oldPassword){
		boolean flag=false;
		File f = new File(filePath);
		if (!f.exists())setOldPassword(defaultOldPassword);
		try (InputStream input = new FileInputStream(filePath)) {
            Properties prop = new Properties();
            prop.load(input);

            if (prop.getProperty(passwordKey).equals(oldPassword)){
            	System.out.println("Old Password Successfully verified.");
            	flag=true;
            }else{
            	System.out.println("Old Password does not match.");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }       

		return flag;
	}
	
	public boolean changePassword(String oldPassword, String newPassword){
		boolean flag=false;
		double oldLength = oldPassword.length();
		if (verifyOldPassword(oldPassword)){
			if (checkPasswordRule(newPassword)){
				double actualSimilarityFactor = checkSimilarityFactor(oldPassword,newPassword)/oldLength;
				if (actualSimilarityFactor<similarityFactor){
					System.out.println("Password changed successfully.");
					flag=true;
				}else{
					System.out.println("Similarity factor between old and new password is more than " + similarityFactor);
				}
			}
		}
				
		return flag;
	}

	private boolean checkPasswordRule(String password) 
	{
		int newPasswordLength = password.length();
		boolean flag=false;
	    if(newPasswordLength>=minPasswordLength)
	    {
			Pattern lcaseLetters = Pattern.compile("[a-z]");
			Pattern ucaseLetters = Pattern.compile("[A-Z]");
			Pattern digit = Pattern.compile("[0-9]");
			Pattern specialChar = Pattern.compile ("["+specialCharRange+"]");

			Matcher hasLCaseLetter = lcaseLetters.matcher(password);
			Matcher hasUCaseLetter = ucaseLetters.matcher(password);
			Matcher hasDigit = digit.matcher(password);
			Matcher hasSpecialChar = specialChar.matcher(password);
			int lCaseCount=0;
			int uCaseCount=0;
			int digitCount=0;
			int specialCharCount=0;
			   
			while (hasLCaseLetter.find()){lCaseCount++;}
			while (hasUCaseLetter.find()){uCaseCount++;}
			while (hasDigit.find()){digitCount++;}
			while (hasSpecialChar.find()){specialCharCount++;}
			
			if (lCaseCount>=minLetterAndDigitCount 
					&& uCaseCount>=minLetterAndDigitCount 
					&& digitCount>=minLetterAndDigitCount 
					&& specialCharCount>=minLetterAndDigitCount 
					&& specialCharCount<=maxSpecialCharAllowed){
				if (digitCount<newPasswordLength/maxNumbersFactor){
					if (!checkDuplicateCharacterRule(password)){
					   flag=true;
					}else{
						System.out.println("Password should not contain duplicate repeat characters more than " + maxDuplicateChar);
					}
				}else{
					System.out.println("50 % of the Password should not be a number.");
				}
			}else{
				System.out.println("Password must contain at least "+minLetterAndDigitCount+
						" upper case, "+minLetterAndDigitCount+" lower case "
								+ "and special characters counts should be between "+minLetterAndDigitCount+
								" to " + maxSpecialCharAllowed);
			}
			   
			return flag;
	    }else{
	    	System.out.println("Password length must be at least "+minPasswordLength+" characters.");
	    	return false;
	    }
	}
	
	private boolean checkDuplicateCharacterRule(String password){
		boolean flag=false;
		char[] chars = password.toLowerCase().toCharArray();        
        Map<Character, Integer> map = new HashMap<>();
        
        for(char c : chars)
        {
            if(map.containsKey(c)) {
            	int counter = map.get(c);
                map.put(c, ++counter);
            } else {
                map.put(c, 1);
            }
        }
        
        for(char c : map.keySet()) {
            if(map.get(c) > maxDuplicateChar) {
                flag=true;
            }
        }
        return flag;
	}
	
	private int checkSimilarityFactor(String oldPassword, String newPassword)  
    { 
		char[] oldArr = oldPassword.toLowerCase().toCharArray();
		char[] newArr = newPassword.toLowerCase().toCharArray();
		int oldPasswordLength = oldPassword.length();
		int newPasswordLength = newPassword.length();
        int matchArr[][] = new int[oldPasswordLength + 1][newPasswordLength + 1]; 
        int numOfMatches = 0;  
          
        for (int i = 0; i <= oldPasswordLength; i++)  
        { 
            for (int j = 0; j <= newPasswordLength; j++)  
            { 
                if (i == 0 || j == 0) 
                	matchArr[i][j] = 0; 
                else if (oldArr[i - 1] == newArr[j - 1]) 
                { 
                	matchArr[i][j] = matchArr[i - 1][j - 1] + 1; 
                    numOfMatches = Integer.max(numOfMatches, matchArr[i][j]); 
                }  
                else
                	matchArr[i][j] = 0; 
            } 
        } 
        return numOfMatches; 
    } 
}
