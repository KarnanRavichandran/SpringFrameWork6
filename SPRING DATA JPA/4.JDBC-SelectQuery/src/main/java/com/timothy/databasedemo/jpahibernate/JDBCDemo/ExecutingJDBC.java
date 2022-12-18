package com.timothy.databasedemo.jpahibernate.JDBCDemo;

import com.timothy.databasedemo.jpahibernate.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ExecutingJDBC implements CommandLineRunner {
    @Autowired
    JDBCREPOSITORY repository;

    @Override
    public void run(String... args) throws Exception {
       repository.insert(new Course(1,"Learn Spring","Paul Timothy"));
       repository.insert(new Course(2,"Learn AWS","Paul Timothy"));
       repository.insert(new Course(3,"Learn SQL","Paul Timothy"));

       repository.deleteById(1);
        System.out.println(repository.selectById(2));
        System.out.println(repository.selectById(3));
    }
}
