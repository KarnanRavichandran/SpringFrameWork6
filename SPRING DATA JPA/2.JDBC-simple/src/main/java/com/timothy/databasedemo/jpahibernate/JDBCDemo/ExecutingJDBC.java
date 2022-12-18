package com.timothy.databasedemo.jpahibernate.JDBCDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ExecutingJDBC implements CommandLineRunner {
    @Autowired
    JDBCREPOSITORY repository;

    @Override
    public void run(String... args) throws Exception {
        repository.insert();
    }
}
