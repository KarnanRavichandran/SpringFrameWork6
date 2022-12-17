package com.timothy.restfulwebservices.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    private UserDaoService service;

    public UserResource(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        return service.findOne(id);
    }


    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
       // public void createUser(@RequestBody User user){
        //service.save(user); //to make use of the id value we gonna convert it to a saved variable
        User savedUser=service.save(user);
        //return ResponseEntity.created(null).build();  //avoid this line, if you don't want to enhance status
        // if we want to make the user to get the url of the newly made resource, we need to implement a Http header
        // Location header, basically the location is a part of the mapping above appended with {id},
        //To get the mapping uri of this method we use ServletUriComponentsBuilder
        URI location= ServletUriComponentsBuilder.
                        fromCurrentRequest().path("/{id}").
                        buildAndExpand(savedUser.getId()).
                        toUri();
        return ResponseEntity.created(location).build();
    }


}
