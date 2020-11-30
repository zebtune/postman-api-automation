# Postman API - Collection Testing 
Automation task

## Description
Automation scripts for testing Postman API, based on Maven for depenendency management and building tests, and TestNG(Java).

## Technologies / Tools
Since the project is created with Maven, all of the libraries/dependencies are included in the pom.xml file.
- IDE - Intellij IDEA
- Java Version - (latest) openjdk version 15.0.1
- Apache Maven 3.6.3
- TestNG 7.1.0
- REST Assured 4.3.0 

## Running the Test
#### 1. Inside Intellij (or Eclipse)
The first way of running the test is inside IDE (preferably in Intellij) just by running the main class or method which you can find in 	***src/test/java/com/main/TestRunner.java.*** The main method will trigger all other test classes ***(src/test/java/com/scripts/)*** using TestNG. 
#### 2. With Maven
The second way of running the test is with Maven (assuming you have Maven set up on your machine) with Mavens Surefire plugin that is included in pom.xml. The test will get triggered through generated  testng.xml file. By using the terminal (or command prompt on Windows), navigate to project directory and type in:
```bash
mvn compile
```
This will compile the source code, and check if the build is stable, then after the project is compiled you type in
```bash
mvn test
```
which will execute the test. 
### Authentication 
Since these tests are accessing my personal Postman API workspace, to successfully execute the tests, authorization with API key is required. I am very well aware that leaving sensitive data (like passwords, tokens, access keys) in public repositories should be avoided for security reasons,but I had to find a solution where my worskpace is accessible by the code. I avoided hardcoding the API key value inside the code itself that's why I created properties file ***(which you can find in resources --> config.properties)*** where the api key is stored. The ApiKey.java class ***(src/test/java/com/auth/ApiKey.java)*** is responsible for reading the values from the config.properties. It might not be the best solution (like setting key as environment variable) but it's better than leaving the key stored as variable inside the code. 

<strong>Note</strong>: There is no sensitive information/data inside my personal Postman worskpace that can be harmed 
## Test Report
Each test run will generate reports that you can find inside ***test-output --> index.html***. Currently the latest successful test run is uploaded. 
