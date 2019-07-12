package changePasswordCore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangePassword {
	public static boolean changePassword(String oldPassword, String newPassword){
		boolean flag=false;
		double oldLength = oldPassword.length(); 
		double similarityFactor = 0.8;
		if (checkPasswordRule(newPassword)) flag=true;
		if (checkSimilarityFactor(oldPassword,newPassword)/oldLength>similarityFactor){
			flag=true;
		}else{
			System.out.println("Similarity factor between old and new password is more than 80%");
			flag=false;
		}
		
		return flag;
	}

	public static boolean checkPasswordRule(String password) 
	{
		int newPasswordLength = password.length();
		boolean flag=false;
	    if(newPasswordLength>=18)
	    {
			Pattern lcaseLetters = Pattern.compile("[a-z]");
			Pattern ucaseLetters = Pattern.compile("[A-Z]");
			Pattern digit = Pattern.compile("[0-9]");
			Pattern specialChar = Pattern.compile ("[!@#$&*]");

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
			double divident = 2;
			if (lCaseCount>=1 && uCaseCount>=1 && digitCount>=1 && specialCharCount>=1 && specialCharCount<=4){
				if (digitCount<newPasswordLength/divident){
					if (!checkDuplicateCharacterRule(password)){
						System.out.println("Password changed successfully.");
					   flag=true;
					}else{
						System.out.println("Password should not contain duplicate repeat characters more than 4");
					}
				}else{
					System.out.println("50 % of the Password should not be a number.");
				}
			}else{
				System.out.println("Password must contain at least 1 upper case, 1 lower case and special characters counts should be between 1 to 4");
			}
			   
			return flag;
	    }else{
	    	System.out.println("Password length must be at least 18 characters.");
	    	return false;
	    }
	}
	
	public static boolean checkDuplicateCharacterRule(String password){
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
            if(map.get(c) > 4) {
                flag=true;
            }
        }
        return flag;
	}
	
	public static int checkSimilarityFactor(String oldPassword, String newPassword)  
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
