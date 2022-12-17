content negotation is how a consumer expects a response, (representation)

Add Dependency:

<dependency>
	<groupId>com.fasterxml.jackson.dataformat</groupId>
	<artifactId>jackson-dataformat-xml</artifactId>
</dependency>

- as json or xml format. (Using Accept header - application/xml, application/json,..)

- in different Languages. (Using Accept-Language Header -en,es,nl,fr..)

Run your application, go talend-api- tester

<----For different formats:---->

-Add Get method and path

-Click on Header and Type header: Accept and value: application/xml (By default the response is json)

-Now send the Request to see the xml or json response.

<----For different languages---->

-Add message.properties in resource folder.

-Add Get method and path

-Click on Header and Type header: Accept-language and value: en or es or nl

-Now send the Request.

