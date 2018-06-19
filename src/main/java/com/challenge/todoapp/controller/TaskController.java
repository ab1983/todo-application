package com.challenge.todoapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.todoapp.bo.TaskBo;
import com.challenge.todoapp.exception.TodoException;
import com.challenge.todoapp.service.TaskService;


@RestController
@RequestMapping("/todo-api/task")
public class TaskController {
	
	@Autowired
	TaskService taskService;
	
	@PostMapping(value = "/save")
	@ResponseBody
	public void save(@RequestBody TaskBo taskBo) throws TodoException{
		taskService.save(taskBo);
	}
	
	@GetMapping(value = "/delete/{taskId}")
	@ResponseBody
	public void delete(@PathVariable Long taskId) throws TodoException {
		taskService.delete(taskId);
	}
	
	@GetMapping(value = "/list/{userId}")
	@ResponseBody
	public List<TaskBo> list(@PathVariable Long userId) throws TodoException {
		return taskService.listByUser(userId);
	}	
}
