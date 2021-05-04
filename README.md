# Captcha Attendance Tracker.

## Why?
In current pandemic situation many teaching institute started there online classes. One of the things that was missing is how we use to have attendance in lecture but it is not there any more. This application tries to solve that problem.

## How?
After login, every logged in student gets a Captcha page. We have used google provided ReCaptcha APIs, user will be presented with multiple captcha during the lecture, user can complete these captcha and record there attendance. Admin can set the frequency of Captcha per lecture and min correct captchas to record attendance. 

## Features
### Admin
1. Can register any users, Students and Faculties. 
2. Can view attendance.

### Faculty
1. Can register students.
2. Can view attendance. 

### Student.
1. Can record attendance.

### Prerequisite.
1. Java 8
2. Maven
3. Git
4. Eclipse
5. Browser to test.

## Runnning application.
1. Checkout project to any directory.  
```git clone <repo>```
2. Run following commands in command prompt.
```
cd CaptchaAttendance
CaptchaAttendance\src\main\resources\hostentry.bat
./mvnw spring-boot:run
```
3. Open http://sirtattendance.com and login with admin/password to create faculty and students, and logout. 
4. Track attendance. Login as student and verify captcha. 
5. View report. Login as faculty and click on attendance. 
6. Optional - Import project to Eclipse.  
   a. Import to eclipse. Go to file-> import project -> import existing maven project.

## Configuration points. 
1. Open application.properties. 
2. Change threshold value for min success captcha required to mark attendance.
3. Change nextCaptcha to change frequency of captcha to display to students. 

## Accessing DB.
Since we are using embedded H2 DB. It is just a datafile and configured in application.properties with spring.datasource.url.  
1. Open http://sirtattendance.com/h2-console.
2. URL: jdbc:h2:file:C:/data/demo
3. Username: sa
4. Password: password 