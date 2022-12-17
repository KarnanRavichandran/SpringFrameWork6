When we send the response,
 we also need to provide the customers with some useful links together with the response.

HATEOAS - HyperMedia as the Engine of Application state.

Add Dependency,
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-hateoas</artifactId>
	</dependency>

Change the return type of handler method as wrapping that class,
	EntityModel<User>

EntityModel<User> entityModel = EntityModel.of(user);

WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers()); 
//We need to get the URL path of the method we want to display - 
	-manually coding the link may cause problems, if we change the url in future.
	-So we get the link using the  linkTo(methodOn(this.getClass()).retrieveAllUsers());

entityModel.add(link.withRel("all-users")); //Title for the link

return entityModel;
