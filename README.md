# API + UI Testing Automation Framework
This is an API testing automation framework. Utilizing the RestAssured library in order to 
send and parse  HTTP requests and responses, it offers a flexible command-based testing 
environment along with specific test type selection potentiality. Also, through the use of Java-Selenium along with 
the Web Driver Manager dependency, the framework is capable of executing UI browser automated test cases. 
The framework automatically generates an HTML based execution report after each test is completed. 
It also updates the test case execution status of the corresponding manual test cases found in JIRA.


### Pre-requisite Downloads/Installations
The system must have following tools and plugins integrated to be able to fully utilize this framework. 
* Chrome or Firefox browser
* Git v2.0+
* JDK v1.8 + 
* Apache Maven v3.0+
 


### Project Dependencies 
This testing automation framework depends on following 
external libraries.
- API Automation :  **RestAssured Library** 
- Browser UI Automation : **Java-Selenium Library**
- JSON data query :  **JsonPath Library**
- Test case creation & management :   **TestNG Library** 
- Test Execution Report :  **Extent Spark Report** 
- Test Data Generation :  **Java Faker Library** 

### Project Folder Structure 
```
|-reports                        #  stores all the generated test execution reports 
|-pom.xml                        #  project object model file for the maven project configuration
|-testng.xml                     #  configuration files for the test structures and test case managements 
|-src
   |---test
         |----java
                |-[+]base        #  all commonly re-used base classes are stored here 
                |-[+]pages       #  all UI page object models are stored here            
                |-[+]testcases   #  all test classes that contains test cases are stored here
                |-[+]utility     #  all the utility classes are stored here

         |----resources         
                |-[d]payloads    #  all the json files used in testcases are stored here 
              
|-.gitignore                     #  git ignore config file 
|-READMD.md                      #  you are currently viewing this file 
```


### Framework Class Diagrams 
![internals](/images/Framework.png)


### Set-up Instructions 
You can use this framework by integrating into existing Jenkins pipeline. Recommended jenkins set-up is as
follows:
![screenshot](/images/test_execution_setup.png)


### Test Triggering Commands
All the test triggering is done through maven commands, this framework supports multiple different types of 
test executions such as smoke, regression, and end-to-end on different possible environment such as QA, Staging, and 
UAT.

If you would like to execute a specific test that is stated on testng.xml file
Choose from the following command: 

For invoking UI Smoke test:
```
mvn test -DtestType="UISmoke"
```
For invoking API Smoke test:
```
mvn test -DtestType="APISmoke"
```

