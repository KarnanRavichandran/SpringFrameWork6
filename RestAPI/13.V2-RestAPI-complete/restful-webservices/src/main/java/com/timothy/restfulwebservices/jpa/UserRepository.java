package com.timothy.restfulwebservices.jpa;

import com.timothy.restfulwebservices.user.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Integer> {

}
