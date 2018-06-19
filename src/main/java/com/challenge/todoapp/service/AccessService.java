package com.challenge.todoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.todoapp.bo.UserBo;
import com.challenge.todoapp.dao.UserDao;
import com.challenge.todoapp.dao.UserDao2;
import com.challenge.todoapp.exception.TodoException;
import com.challenge.todoapp.model.User;

@Service
public class AccessService {
	
	private User user;
	
	@Autowired
	private UserDao2 userDao;	
	
	/**
	 * This method checks if the userLogin and password exist in the database. So, when the user is found
	 * the user is kept in an attribute during the session and the access is authorized.
	 * @param userLogin
	 * @param password
	 * @return
	 * @throws TodoException
	 */
	public UserBo doLogin(String userLogin, String password) throws TodoException {
		
		User user = userDao.findByUserLoginAndPassword(userLogin, password).orElseThrow(() -> new TodoException("Invalid login or password."));
		UserBo userBo = new UserBo();
		userBo.setLogin(user.getUserLogin());
		userBo.setName(user.getUserName());
		userBo.setId(user.getId().longValue());
		this.user = user;
		return userBo;
	}
	
	public User getLoggedUser() {
		return this.user;
	}

	/**
	 * This method populate the database with a user default to be user during the tests.
	 */
	public void populateDatabase() {
		User user = new User();
		user.setUserName("Test");
		user.setUserLogin("test");
		user.setPassword("pwd123");
		userDao.save(user);
	}

}
