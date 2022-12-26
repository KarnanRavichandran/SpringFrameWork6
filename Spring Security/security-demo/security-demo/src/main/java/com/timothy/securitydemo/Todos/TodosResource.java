package com.timothy.securitydemo.Todos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
@RestController
@Slf4j
public class TodosResource {

    private static final List<Todo> listTodos=List.of(new Todo("paul","Get AWS"),
            new Todo("timothy","Get Azure"));
    @GetMapping("/todos")
    public List<Todo> allTodos() {
        return listTodos;
    }
    @GetMapping("/users/{username}/todos")
    public Todo specificTodos(@PathVariable String username) {
        return listTodos.get(0);
    }
    @PostMapping("/users/{username}/todos")
    public void specificTodos(@PathVariable String username, @RequestBody Todo todo) {
        log.info("created {}",username,todo);
    }
}
record Todo(String userName,String description){}