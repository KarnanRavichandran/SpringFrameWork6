package com.timothy.databasedemo.jpahibernate.JPADEMO;

import com.timothy.databasedemo.jpahibernate.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ExecutingJPA implements CommandLineRunner {
    @Autowired
     JPAREPOSITORY jparepository;

    @Override
    public void run(String... args) throws Exception {
//       jparepository.insert(new Course(1,"Learn Spring","Paul Timothy"));
//       jparepository.insert(new Course(2,"Learn nothing","Paul Timothy"));
//       jparepository.insert(new Course(3,"Learn SQL","Paul Timothy"));
//
//       jparepository.deleteById(1);
//        System.out.println(jparepository.findById(2));
//        System.out.println(jparepository.findById(3));
    }
}
