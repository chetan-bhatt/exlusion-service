# Exclusion-service

Exclusion service for registering users, exluding users if in the exclusion list or already resigtered.

## Pre-requisites

1) Install Java 8 and set JAVA_HOME, verify with below command.

	- java -version (v1.8.x)

2) Download and install Maven version 3.3.x and verify with below command.

	- mvn -version (v3.3.x)

3) Please ensure both Java and Maven are included in the path.


## Build and launch.

1) Navigate to the project directory and execute the start.sh script.(Grant the execute permission to the script, if not already set).
   This will build the application, exeucte the junit test cases and launch the application on the default port i.e 8080.

	- ./start.sh
	
## Test the appliacation

1) Make a call to the exposed /register API for registering users. Below is an example of the curl command. This is a happy path which registers the user and in reponse body the user registered is returned

	curl --header "Content-Type: application/json" --request POST --data '{"username":"xyz", "password":"xyzZ1", "dateOfBirth": "2000-10-10", "ssn": "12232343"}' http://localhost:8080/register
	

## Assumptions

1) Currently the application starts using http protocol.

2) The exposed '/register' API is not authenticated.

3) Currently in-memory cache is used for storing user details. This cache will get cleared once the application restarts. This is a simple cache which will cease to work if multiple instances of application is launched on multiple port.class

4) For keeping it simple, each user is uniquely identified by their dateOfBirth and ssn.

5) Currently junit test is written using @SpringBootTest.


## Improvements

1) We can use secured protocol https for starting the application.

2) We can have some authentication mechanism (jwt or other may be) for authenticating the exposed API.abstract

3) We could use some better cache with eviction policies or even better if we use real db for persistence.class

4) We could use other tools for integration testing and it should be a separate project. This project must contain only junit tests with mocking included if required.
