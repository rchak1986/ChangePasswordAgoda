# ChangePasswordAgoda
code repository for agoda home test for Rahul Chakraborty - rchak1986@gmail.com

# Project Confirguration, Execution & Report
## Configuration
1. Install Maven & add maven bin path into the environmental path varibale
2. Install Java - JDK and add JDK bin path into environmental path variable
3. Install eclipse and add Maven plugin
4. Install TestNG from eclipse Market Place

## Import Project - ChangePasswordAgoda
1. Clone or download the project from the git repo - https://github.com/rchak1986/ChangePasswordAgoda
2. Unzip the folder
3. Open Eclipse and import the project

## Execution
1. Open command prompt and change the directory using command 
```cd```
2. Build and Execute project
```mvn clean install```

## Report
1. The TestNG report will be generated int the following path: ```project_Directory\target\surefire-reports```
  Sample Report: https://github.com/rchak1986/ChangePasswordAgoda/blob/master/changePassword/target/surefire-reports/emailable-report.html

# Core Functions

## src/main/java/changePasswordCore/ChangePassword.java
### ```public void setOldPassword(String oldPassword)```
Method to set any given string as the old system password. [mock function]
### ```public boolean verifyOldPassword(String oldPassword)```
Method to verify old password against any given string. [mock function]
### ```private boolean checkPasswordRule(String password)```
Method to check if any given new password qualifies all the given rules.

Password requirement
1. At least 18 alphanumeric characters and list of special chars !@#$&*
2. At least 1 Upper case, 1 lower case ,least 1 numeric, 1 special character
3. No duplicate repeat characters more than 4
4. No more than 4 special characters
5. 50 % of password should not be a number
6. password is not similar to old password < 80% match.
### ```private int checkSimilarityFactor(String oldPassword, String newPassword)```
Method to determine the similarity factor. [Point 6 in password requirement]

### ```public boolean changePassword(String oldPassword, String newPassword)```
This is the core function which calls all other functions internally to determine if the new password is a valid one and can be changed.

# Test Cases and Test Data
The Unit Test Scenarios includes the following automated test scenarios and test data combination:

## Valid Scenarios:

1. exact 18 char - alpha numeric - spcl char in range - ```AbxdjkwesdQ@!#2389```
2. exact 18 char - aplha numeric - 4 spcl char - ```AbxdjkwesdQ#$&*213```
3. exact 18 char - alpha numeric - 4 duplicate chars - ```AjkaewasjAkqz!#$12```
4. exact 18 char - alpha numeric - 9 numers - ```aSEtmwasy!@#12345678```
5. more than 18 chars - alpha numeric - 9 numbers, 4 duplicates, 4 spcl char - ```123456789!@#$xbXexdeX```

## Invalid Scenarios:

1. Less than 18 char with valid criteria - ```Abxres@123```
2. 18 Char - with other special chars - ```Absdewet%^()<>1234```
3. 18 char - all caps - ```ABCDEFGEWS@!#12341```
4. 18 char - all small - ```abcdefdswa@!#12341```
5. 18 char - 5 in range spcl char - ```AbxdjkwesQ!#$&*213```
6. 18 char - 10 numbers - ```1234567890@&$asert```
7. 18 char - 5 duplicates - ```AjakAewAsjkqa!#$12```
8. 18 char - no numbers - ```AbcdEfgHIjkl!@#mno```
9. 18 char - no alphabets - ```123456789011!@#123```
10. 18 char - no special char - ```AbcdEFGijklmnoPQR1```
11. Old Password not matched - ```123456789!@#$xbXexdeX```


## Similarity test:

1. less than 80% similarity old: ```ABCDefgh@#$1234567``` - New: ```ABCDefgh&*!9876567```
2. 80% similarity old: ```ABCDefgh@#$1234567``` - New: ```ABCDefgh@#$1234909```
3. 90+ % similarity old: ```ABCDefgh@#$1234567``` - New: ```ABCDefgh@#$1234560```
