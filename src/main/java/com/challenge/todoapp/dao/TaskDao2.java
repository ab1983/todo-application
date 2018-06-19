package com.challenge.todoapp.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.challenge.todoapp.model.Task;


public interface TaskDao2 extends CrudRepository<Task, BigInteger> {
	@Query("from Task a where a.userId=:userId")
	public Iterable<Task> findByUserId(@Param("userId") BigInteger userId);
}
