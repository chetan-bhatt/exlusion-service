#
#
#This script assumes that JAVA 8 and MAVEN (3.3.x) is installed in the environment. If not please install them and include them in the path.
# 
# To check java version please execute the below command
# java -version
#
# To check the maven version installed, please execute the below command
# mvn -version
#
#Once java and maven are installed the below commands builds and starts the spring application.
#
#

# Builds the application, executes the unit tests.
mvn clean install


# Starts the spring application.
mvn spring-boot:run


