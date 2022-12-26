package com.timothy.securitydemo.HelloWorld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class HelloWorldResource {
    @GetMapping("/hello-world")
    public String HelloWorld(){
        return "HelloWorld" + "    http://localhost:8080/logout";
    }
}
