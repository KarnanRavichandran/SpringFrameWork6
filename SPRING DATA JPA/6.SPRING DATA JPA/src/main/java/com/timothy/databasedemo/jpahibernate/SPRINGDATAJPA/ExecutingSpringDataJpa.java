package com.timothy.databasedemo.jpahibernate.SPRINGDATAJPA;

import com.timothy.databasedemo.jpahibernate.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ExecutingSpringDataJpa implements CommandLineRunner {
    @Autowired
    SPRINGDATAJPAREPOSITORY springdatajparepository;

    @Override
    public void run(String... args) throws Exception {
        springdatajparepository.save(new Course(1,"Learn Spring","Paul Timothy"));
        springdatajparepository.save(new Course(2,"Learn nothing","Paul Timothy"));
        springdatajparepository.save(new Course(3,"Learn summa iru da","Paul Timothy"));

        springdatajparepository.deleteById(1l);

        System.out.println(springdatajparepository.findById(2l));
        System.out.println(springdatajparepository.findById(3l));
    }
}
