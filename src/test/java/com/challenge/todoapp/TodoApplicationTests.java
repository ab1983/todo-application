package com.challenge.todoapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.challenge.todoapp.bo.TaskBo;
import com.challenge.todoapp.exception.TodoException;
import com.challenge.todoapp.service.AccessService;
import com.challenge.todoapp.service.TaskService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TodoApplicationTests {

	@Autowired
	private TaskService taskService;
	@Autowired
	private AccessService accessService;	
	//@Test
	public void contextLoads() {
	}
	
	@Test
	public void crudTask() {
		try {
			//LOGIN TEST
			accessService.populateDatabase();
			accessService.doLogin("test", "pwd123");
			assertNotNull("Login is not working.",accessService.getLoggedUser());
			
			//INSERT AND LIST TEST
			TaskBo taskBo = new TaskBo();
			taskBo.setDescription("Unit Test");
			taskService.save(taskBo);
			List<TaskBo> taskBoList = taskService.listByUser(accessService.getLoggedUser().getId().longValue());
			assertNotNull("Task list is null after insert.", taskBoList);
			assertEquals("Db is empty after insert.", 1, taskBoList.size());
			
			//UPDATE TEST
			taskBo= taskBoList.get(0);
			taskBo.setDescription("Unit Test Update");
			taskBo.setDone(true);
			taskService.save(taskBo);
			taskBoList = taskService.listByUser(accessService.getLoggedUser().getId().longValue());
			taskBo= taskBoList.get(0);
			assertEquals("Db is not empty after delete.", "Unit Test Update", taskBo.getDescription());
			
			//DELETE TEST
			taskService.delete(taskBoList.get(0).getId());
			taskBoList = taskService.listByUser(accessService.getLoggedUser().getId().longValue());
			assertEquals("Db is not empty after delete.", 0, taskBoList.size());
		} catch (TodoException e) {
			assertNull("Exception "+e.getMessage(), e);
		}
	}

}
