package com.challenge.todoapp.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.todoapp.bo.TaskBo;
import com.challenge.todoapp.dao.TaskDao;
import com.challenge.todoapp.dao.TaskDao2;
import com.challenge.todoapp.exception.TodoException;
import com.challenge.todoapp.model.Task;

@Service
public class TaskService {
	
	@Autowired
	private AccessService accessService;
	
	@Autowired
	private TaskDao2 taskDao;
	
	public void save(TaskBo taskBo) throws TodoException {
		if(taskBo.getId() == null) {
			insert(taskBo);
		}else {
			update(taskBo);
		}
	}
	
	public void delete(Long taskId) throws TodoException {
		try {			
			taskDao.deleteById(BigInteger.valueOf(taskId));
		} catch (Throwable e) {
			throw new TodoException("Delete Task failed.", e);
		}
		
	}

	
	private void insert(TaskBo taskBo) throws TodoException {
		try {			
			Task task = new Task();
			task.setCreated(new Date());
			task.setDescription(taskBo.getDescription());
			task.setDone(taskBo.isDone());	
			task.setUserId(accessService.getLoggedUser().getId());
			taskDao.save(task);
		} catch (Throwable e) {
			throw new TodoException("Insert Task failed.", e);
		}
		
	}
	
	private void update(TaskBo taskBo) throws TodoException {
		Task task = taskDao.findById(BigInteger.valueOf(taskBo.getId())).orElseThrow(() -> new TodoException("No task found with id " + taskBo.getId()));
		try {
			task.setLastUpdate(new Date());
			task.setDescription(taskBo.getDescription());
			task.setDone(taskBo.isDone());
			taskDao.save(task);
		} catch (Throwable e) {
			throw new TodoException("Update Task failed.", e);
		}
	}
	
	public List<TaskBo> listByUser(Long userId){
		List<TaskBo> listTaskBo = new ArrayList<>();
		taskDao.findByUserId(BigInteger.valueOf(userId)).forEach(t->{
			TaskBo taskBo = new TaskBo();
			taskBo.setId(t.getId().longValue());
			taskBo.setDescription(t.getDescription());
			taskBo.setLastUpdate(t.getLastUpdate());
			taskBo.setDone(t.isDone());
			listTaskBo.add(taskBo);
		});
		return listTaskBo;
	}

}
