# DroneApp
REST API implementation for Drone App

System Requirements

Java SE Development Kit 1.8
To build the drone app it is necessary that you have Maven 3 or later.

Build instructions:

1. Install Java SE Development Kit 1.8 (if not already avialable)
2. Install Apache Maven 3.x.x(https://maven.apache.org/download.cgi#) (if not already avialable)
3. Get a clone from https://github.com/shyabithd/DroneApp.git or download the source
4. Run the one of the below maven commands from DroneApp directory,
    mvn clean install (To build the binary and source with the tests)
    mvn clean install -Dmaven.test.skip=true (To build the binary and source, without running any of the unit tests)
5. You can find the executable in DroneApp/target directory.
6. Executable name: drone-1.0-SNAPSHOT.jar
7. Sample database has been created with sample data which you can find in database directory inside DroneApp directory. 
   Executable and database must be in the same directory hierarchy, so copy it inside target directory where executable is located. 
9. Run below command to run the application: java -jar drone-1.0-SNAPSHOT.jar

Project Resources

Following document which you find in the resources directory contains application configuration: application.properties
Service port is defaulted to 9000
H2 DB is used for storing data.
Business configs can be modified as desired.

Content
#Application Configs
spring.application.name = DroneAppService
server.port = 9000

#DB Configs
spring.datasource.url=jdbc:h2:./database/data
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.h2.console.settings.trace=false
spring.h2.console.settings.web-allow-others=false

#Business Configs
battery_low_limit=25
drone_count=10

Logging
Audit/History logs can be found under a directory logs which will be createdd relative to the executable(binary) directory.

Please access below URL once the application is started to view full definition of exposed APIs.
http://localhost:9000/swagger-ui/index.html#/drone-controller

Refer below screenshot for API definitions
![image](https://user-images.githubusercontent.com/33624222/188271196-1c1d8a1e-bf94-4acf-8797-4cffcd9645dd.png)

Example API calls:
Register a drone                       - curl -X POST http://localhost:9000/drones -H "Content-Type:Application/json" -d '{"serialNumber":"121","droneModel":"LightWeight","weight":100,"batteryCapacity":0,"droneState":"IDLE"}'

Load an existing drone                 - curl -X PUT http://localhost:9000/drones/100/packages -H "Content-Type:Application/json" -d '[{"name":"asda","weight":90,"code":"SQQE","imgName":"00"}]'

List packages in a drone               - curl -X GET http://localhost:9000/drones/100/packages

Get battery capacity of a drone        - curl -X GET http://localhost:9000/drones/13/battery-capacity

List Idle drones available for loading - curl -X GET http://localhost:9000/drones/?state=IDLE
