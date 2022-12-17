package com.timothy.restfulwebservices.jpa;


import com.timothy.restfulwebservices.user.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Integer> {

}