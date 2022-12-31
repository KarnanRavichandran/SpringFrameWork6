package com.paul.rest.webservices.restfulwebservices.welcomecontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	@GetMapping(path = "/")
	public String returnSomethingAtRootUrl() {
		return "Congratulations!"; 
	}

}
