package com.challenge.todoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.todoapp.bo.UserBo;
import com.challenge.todoapp.exception.TodoException;
import com.challenge.todoapp.model.User;
import com.challenge.todoapp.service.AccessService;


@RestController
@RequestMapping(path="/todo-api/access")
public class AccessController {
	
	@Autowired
	AccessService accessService;
	
	@PostMapping(value = "/login")
	@ResponseBody
	@CrossOrigin(origins = "http://localhost:4200")
	public UserBo doLogin(@RequestBody User user) throws TodoException{
		return accessService.doLogin(user.getUserLogin(), user.getPassword());
	}
	
	@GetMapping(value = "/logoff")
	@ResponseBody
	public String doLogoff() {
		System.out.println("teste1");
		return "oi";
	}
}
