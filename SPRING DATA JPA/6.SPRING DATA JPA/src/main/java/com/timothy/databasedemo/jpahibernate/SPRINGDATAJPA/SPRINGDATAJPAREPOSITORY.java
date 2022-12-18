package com.timothy.databasedemo.jpahibernate.SPRINGDATAJPA;


import com.timothy.databasedemo.jpahibernate.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SPRINGDATAJPAREPOSITORY extends JpaRepository<Course,Long> {

}
