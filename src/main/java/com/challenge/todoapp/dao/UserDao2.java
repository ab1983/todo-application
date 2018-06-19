package com.challenge.todoapp.dao;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.challenge.todoapp.model.User;


public interface UserDao2 extends CrudRepository<User, BigInteger> {
	@Query("from User a where a.userLogin=:userLogin and a.password=:password")
	public Optional<User> findByUserLoginAndPassword(@Param("userLogin") String userLogin, @Param("password") String password);
}
