package com.challenge.todoapp.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import com.challenge.todoapp.model.User;


public interface UserDao extends MongoRepository<User, Long> {
	@Query("from User a where a.userLogin=:userLogin and a.password=:password")
	public Optional<User> findByUserLoginAndPassword(@Param("userLogin") String userLogin, @Param("password") String password);
}
