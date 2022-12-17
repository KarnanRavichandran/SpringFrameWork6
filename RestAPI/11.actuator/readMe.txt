Actuator,
	used to monitor and manage our spring application in production.

Add dependency,
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-actuator</artifactId>
	</dependency>
Go to,
http://localhost:8080/actuator

By default, actuator only exposes health of our application.

Add management.endpoints.web.exposure.include=* in application.properties